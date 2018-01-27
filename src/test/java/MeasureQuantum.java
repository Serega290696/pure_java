import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.LockSupport;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;

class MeasureQuantum {

    volatile static int dominThread;
    volatile static AtomicLong counter = new AtomicLong();
    volatile static ArrayList<Long> periods = new ArrayList<>();
    volatile static boolean exit;
    volatile static long lastTime;

    /*
    If run with 'java A' then threads work almost simultaniously.
    But! If set all thread on one CPU core with: java A & taskset -pc 1 $!
    than threads executes pseudo-sequentially and for optimization
    processor executes bulk commands, near 1_500_000 operations (CAS) or
    10+ millis lasts before scheduler decide to change thread.

    Command for execute on one core:
        javac A.java && java A & taskset -pc 0 $!
        # number is the core number
     */
    public static void main(String[] args) {
        System.out.println("\nStart. . ." );
        lastTime = System.nanoTime();
        Thread t1 = new Thread(() -> {
            while (!exit) {
                if (dominThread != 1) {
                    periods.add(System.nanoTime() - lastTime);
                    dominThread = 1;
                    lastTime = System.nanoTime();
                }
//                long micros = System.nanoTime() ;
//                counter.set(micros);
//                counter.incrementAndGet();
            }
        });
        Thread t2 = new Thread(() -> {
            while (!exit) {
                if (dominThread != 2) {
                    periods.add(System.nanoTime() - lastTime);
                    dominThread = 2;
                    lastTime = System.nanoTime();
                }
//                try {
//                    TimeUnit.MICROSECONDS.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                long micros = System.nanoTime() ;
//                counter.set(micros);
//                counter.incrementAndGet();
            }
        });
        t1.start();
        t2.start();

        try {
            TimeUnit.MILLISECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        exit = true;
//        periods.forEach(System.out::println);
        LongSummaryStatistics info = periods.stream().collect(Collectors.summarizingLong(Long::longValue));
        System.out.println("Millis: " + info.getAverage() / 1_000_000);
    }

}
