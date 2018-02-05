package com.beltser.lab;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class Multithreading {

    private static Thread createThreadWithActions(Runnable... r) {
        Thread thread = new Thread(() -> {
            for (Runnable runnable : r) {
                runnable.run();
            }
        });
        thread.setDaemon(false);
        return thread;
    }

    private static Thread[] createThreadsWithActions(int amount, Runnable... r) {
        Thread[] threads = new Thread[amount];
        for (int i = 0; i < amount; i++) {
            Thread thread = new Thread(() -> {
                for (Runnable runnable : r) {
                    runnable.run();
                }
            });
            thread.setDaemon(false);
            threads[i] = thread;
        }
        return threads;
    }

    @Test
    public void testThreadLocal() throws InterruptedException {
        Consumer<ThreadLocal<String>> reporter = o -> {
            System.out.println(Thread.currentThread().getName() + ": " + o.get());
        };
        ThreadLocal<String> perThreadSecret = new ThreadLocal<>();
        perThreadSecret.set("big deal at 12:00");
        reporter.accept(perThreadSecret);
        Thread t = createThreadWithActions(() -> {
            reporter.accept(perThreadSecret);
            perThreadSecret.set("big deal CANCELED");
            reporter.accept(perThreadSecret);
        });
        t.start();
        t.join();
        reporter.accept(perThreadSecret);
    }

    @Test
    public void isDeadlockHere() throws InterruptedException {
        /*
        1. T-1 in synchronized method-1. T-2 in synchronized method-2.
        2. T-1 try enter method-2.
        3. Deadlock.
         */
        class B {
            synchronized void methodB() {
                System.out.println(Thread.currentThread().getName() + ". In methodB()");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        class A {
            synchronized void methodA(B b) {
                System.out.println(Thread.currentThread().getName()
                        + ". Try enter from one synchronized method to another already blocked method. . .");
                b.methodB();
            }
        }
        final B b = new B();
        createThreadWithActions(() -> {
            b.methodB();
        }).start();
        createThreadWithActions(() -> {
            A a = new A();
            a.methodA(b);
        }).start();

        TimeUnit.SECONDS.sleep(1);
    }

    @Test
    public void isDeadlockHere2() throws InterruptedException {
        /*
        1. T-1 call synchronized method-1.
        1. T-1 call synchronized method-2 in the same class (the same monitor).
        2. T-1 try call synchronized method-1 successfully.
        3. No deadlock here: Reentrant lock - the same Thread can acquire the same monitor multiple times.
         */
        class A {
            synchronized void method1(boolean exit) {
                if (exit) {
                    return;
                }
                System.out.println("In A.");
                method2();
                System.out.println("No deadlock");
            }

            synchronized void method2() {
                System.out.println("In B.");
                method1(true);
            }
        }
        Thread t = createThreadWithActions(() -> {
            A a = new A();
            a.method1(false);
        });
        t.setDaemon(false);
        t.start();
        System.out.println(t.isDaemon());
        TimeUnit.SECONDS.sleep(1);
    }

    volatile int dominThread;
    volatile AtomicInteger counter = new AtomicInteger();
    volatile int counter2 = 0;
    volatile ArrayList<Long> periods = new ArrayList<>();


    volatile boolean isGoToSleep;
    static int x = 0;

    @Test
    public void threadStates() throws InterruptedException {
        /*
        https://stackoverflow.com/questions/27406200/visualvm-thread-states
        You can connect via VisualVM to observe threads state.

        while not starting: NEW
        while run: RUNNABLE
        while sleep: TIMED_WAITING
        while wait(): WAITING
         */

//        new ChangesListener<Integer>()
//                .listen(() -> {
//                            x++;
//                            return x;
//                        },
//                        (x, y) -> Math.abs(x - y) >= 10 ? 1 : 0,
//                        (last, prev) -> System.out.println("----New state: " + last + " x " + prev),
//                        10000);
//        sleep(10000000);
        ChangesListener<Thread.State> changesListener = new ChangesListener<>();
        Object o = new Object();
        Thread t = createThreadWithActions(() -> {
            System.out.println("While running: " + Thread.currentThread().getState());
            changesListener.eraseMsg();
            isGoToSleep = true;
            changesListener.msgForNextChange("In sync block: ");
            synchronized (o) {
                o.notifyAll();
            }
            changesListener.eraseMsg();
            changesListener.msgForNextChange("While sleeping: ");
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            changesListener.msgForNextChange("In sync block-2: ");
            synchronized (this) {
                changesListener.eraseMsg();
                try {
                    changesListener.msgForNextChange("While wait: ");
                    while (true) {
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        changesListener
                .listen(t::getState,
                        Comparator.naturalOrder(),
                        (last, msg) -> System.out.println("\t\t\tprint state with changes listener (unreliable): " + msg + last),
                        1);
        System.out.println("Before start: " + t.getState());
        changesListener.msgForNextChange("While running: ");
        t.start();
//        changesListener.eraseMsg();
        synchronized (o) {
            while (!isGoToSleep) {
                o.wait();
            }
        }
        TimeUnit.MILLISECONDS.sleep(100);
//        changesListener.msgForNextChange("While sleeping: ");
        System.out.println("While sleeping: " + t.getState());
        TimeUnit.MILLISECONDS.sleep(1000);
//        changesListener.eraseMsg();
        System.out.println("While wait(): " + t.getState());

        Thread main = Thread.currentThread();
        Thread syncBlockHolder = createThreadWithActions(() -> syncMethod(Runnables.sleep(1000)));
        syncBlockHolder.start();
        Thread printThreadStatusAfter100Millis
                = createThreadWithActions(
                () -> syncMethod(
                        Runnables.sleep(100),
                        () -> System.out.println("While blocked by sync section: "
                                + main.getState())));
        printThreadStatusAfter100Millis.start();
        syncMethod();

        createThreadWithActions(
                Runnables.sleep(100),
                Runnables.threadStatePrinter(main, "While park by LockSupport.park(...)"),
                () -> {
                    Object blocker = LockSupport.getBlocker(main);
                    System.out.println("Blocker: " + blocker);
                },
                () -> LockSupport.unpark(main),
                Runnables.sleep(100),
                Runnables.threadStatePrinter(main, "While UNpark by LockSupport.unpark()")
        ).start();
        LockSupport.park("im blocker");

        activeSleep(1000);
    }

    private static volatile AtomicReference<Thread> leaderThreadHolder
            = new AtomicReference<>();
    private final static AtomicLong counter3 = new AtomicLong(0);
    private final static List<Long> entranceInSyncBlockInRow = new ArrayList<>();

    @Test
    public void syncBlockFairness() {
        /*
        Как и ожидалось блок синхронизации крайне не справедлив.
        Один и тот же тред захватывает монитор около 1000 раз (кол-во колебается 200-4000)
        прежде, чем передать эстафету другому
         */

        final int mode = 0;
        /*
         0 - just loop
         1 - call yield() after iteration
         2 - call sleep(0) after iteration - useless
         */
        Object monitor = new Object();
        Thread[] toSyncBlock = createThreadsWithActions(
                20,
                Runnables.loop(
                        Runnables.syncBlock(
                                monitor,
                                () -> {
                                    if (leaderThreadHolder.get() != Thread.currentThread())
                                        entranceInSyncBlockInRow.add(counter3.getAndSet(0));
                                },
                                Runnables.hold(leaderThreadHolder)
//                                , Runnables.threadNamePrinter()
                                , counter3::incrementAndGet
                        )
//                        ,
//
//                        mode == 0 ?
//                                Runnables.empty() :
//                                mode == 1 ? Thread::yield : Runnables.sleep(0)
                ));
//        ChangesListener<Thread> listener = new ChangesListener<>();
//        listener.listen(leaderThreadHolder::get, (x, y) -> x == y ? 0 : 1,
//                (cur, msg) -> entranceInSyncBlockInRow.add(counter3.getAndSet(0)),
//                        -1
//                );
        Arrays.stream(toSyncBlock).forEach(Thread::start);
        sleep(100);
        synchronized (monitor) {
//            entranceInSyncBlockInRow.forEach(System.out::println);
            LongSummaryStatistics statistics = entranceInSyncBlockInRow.stream()
                    .filter(Objects::nonNull)
                    .collect(Collectors.summarizingLong(i -> i));
            System.out.println(statistics.getAverage());
            System.out.println("entranceInSyncBlockInRow = " + entranceInSyncBlockInRow.size());
        }
    }

    @Test
    public void future() throws ExecutionException, InterruptedException {
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                TimeUnit.MILLISECONDS.sleep(3000);
                return "Ho-ho-ho";
            }
        };
        FutureTask f = new FutureTask<String>(callable);
        f.run();
        System.out.println(f.isDone());
        System.out.println(f.isDone());
        System.out.println(f.get());

    }

    @Test
    public void aaa() {
        String[] s = {"a", "b"};
        int i = 1;
        System.out.println(s[i]);
        System.out.println(s[--i]);
        System.out.println(-4.2% 1);

    }

    private void activeSleep(int millis) {
        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < millis) ;
    }


    private synchronized static void syncMethod(Runnable... r) {
        for (Runnable runnable : r) {
            runnable.run();
        }
    }

    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static class Runnables {
        private static Runnable sleep(long millis) {
            return () -> {
                try {
                    TimeUnit.MILLISECONDS.sleep(millis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            };
        }

        private static Runnable threadStatePrinter() {
            return () -> System.out.println(Thread.currentThread().getState());
        }

        private static Runnable threadStatePrinter(Thread thread) {
            return () -> System.out.println(thread.getState());
        }

        private static Runnable threadStatePrinter(Thread thread, String msg) {
            return () -> System.out.println(msg + ": " + thread.getState());
        }

        private static Runnable threadNamePrinter() {
            return () -> System.out.println("Thread name: "
                    + Thread.currentThread().getName());
        }

        private static Runnable threadNamePrinter(Thread thread, String msg) {
            return () -> System.out.println(msg + ": " + thread.getName());
        }

        public static Runnable loop(Runnable... runnables) {
            return () -> {
                while (true) {
                    for (Runnable runnable : runnables) {
                        runnable.run();
                    }
                }
            };
        }

        public static Runnable syncMethod(Runnable... r) {
            return () -> syncMethod0(r);
        }

        private static synchronized void syncMethod0(Runnable... r) {
            for (Runnable runnable : r) {
                runnable.run();
            }
        }

        public static Runnable syncBlock(Object monitor, Runnable... runnables) {
            return () -> {
                synchronized (monitor) {
                    for (Runnable runnable : runnables) {
                        runnable.run();
                    }
                }
            };
        }

        public static Runnable hold(AtomicReference<Thread> leaderThreadHolder) {
            return () -> leaderThreadHolder.set(Thread.currentThread());
        }

        public static Runnable empty() {
            return () -> {
            };
        }
    }

    private static class ChangesListener<T> {
        private T prevValue;
        private volatile String msg;

        public void listen(Supplier<T> currentValue, Comparator<T> comparator,
                           BiConsumer<T, String> onChange, long checkingMicros) {
            Thread listenerThread = new Thread(() -> {
                while (true) {
                    T last;
                    try {
                        last = currentValue.get();
                    } catch (Exception e) {
                        e.printStackTrace();
                        continue;
                    }
                    if (last != null
                            && (prevValue == null || comparator.compare(prevValue, last) != 0)) {
                        onChange.accept(last, msg != null ? msg : "");
                        prevValue = last;
                    }
//                    onChange.accept(last, prevValue);
                    if (checkingMicros >= 0) {
                        try {
                            TimeUnit.MICROSECONDS.sleep(checkingMicros);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            listenerThread.setDaemon(true);
            listenerThread.start();
        }

        public void msgForNextChange(String msg) {
            this.msg = msg;
        }

        public void eraseMsg() {
            this.msg = null;
        }
    }
}
