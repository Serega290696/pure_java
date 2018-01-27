package com.beltser.lab;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.function.IntSupplier;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Simple {
    public static void main(String[] args) {
//        System.out.println(new O());
//        System.out.println(new A());
//        System.out.println(new B());
//        System.out.println("Simple hellos");
//        System.out.println(System.class.getClassLoader());
//        System.out.println(String.class.getClassLoader());
//        System.out.println(sun.misc.Unsafe.class.getClassLoader());
//        System.out.println(Simple.class.getClassLoader());
//        System.out.println(System.getProperty("java.version"));
//        new Simple().aa();
//
        Supplier<Integer> s = new Supplier<Integer>() {
            int count = 0;

            @Override
            public Integer get() {
                return count++;
            }
        };
        IntSupplier sint = new IntSupplier() {
            int count = 0;

            @Override
            public int getAsInt() {
                return count++;
            }
        };
        Object o, o2;
        o = o2 = new Object();
        int i1, i2, i3 = 1, i4 = 3;
        i1 = i2 = i3 = i4 = i4 + 1;
//        Stream.generate(s)
        IntStream.range(1, 10)
//                .peek(i -> System.out.print(i + "."))
//                .mapToObj(String::valueOf)
                .mapToObj(i -> (i % 3 == 0 ? "Fizz" : i))
                .forEach(i -> System.out.print(i + "."));
        ;


//        for (int i = 1; i < 101; i++) {
//            if (i % 3 == 0 && i % 5 == 0) {
//                System.out.println("FizzBazz");
//            } else if (i % 3 == 0) {
//                System.out.println("Fizz");
//            } else if (i % 5 == 0) {
//                System.out.println("Bazz");
//            }
//            System.out.println(i);
//        }

//        Map map =
//        new HashMap<>().replaceAll();
    }

    interface Ii {
    }

    enum Enu implements Ii {}

    private void aa() {
        Condition c = new Condition() {
            @Override
            public void await() throws InterruptedException {

            }

            @Override
            public void awaitUninterruptibly() {

            }

            @Override
            public long awaitNanos(long nanosTimeout) throws InterruptedException {
                return 0;
            }

            @Override
            public boolean await(long time, TimeUnit unit) throws InterruptedException {
                return false;
            }

            @Override
            public boolean awaitUntil(Date deadline) throws InterruptedException {
                return false;
            }

            @Override
            public void signal() {

            }

            @Override
            public void signalAll() {

            }
        };
        Thread thread = new Thread(() -> {
            synchronized (monitor) {
                System.out.println("a");
                try {
//                    c.await();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("b");
        });
        Thread thread2 = new Thread(() -> {
            synchronized (monitor) {
                System.out.println("a2");
                try {
//                    while (true){}
                    c.await();
                    monitor.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println("b2");
        });
        thread2.start();
//        try {
//            Thread.sleep(0);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread.start();
//        thread.interrupt();
    }

    public interface I {
        public static void m() {
        }
    }

    private static final Object monitor = new Object();

    static class O extends Observable {

    }

    static class A {
        @Override
        public String toString() {
            return "test";
        }
    }

    static class B extends A {

    }
}
