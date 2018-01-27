package com.beltser.lab.multithreadingagain;

public class Main {
    private static final Object monitor = new Object();
    private volatile static boolean condition = false;

    public static void main(String[] args) {

        Thread.currentThread().getState();
        Thread.currentThread().getStackTrace();

        // synchronized block
        //      while loop
        //          wait
        //      condition = false
        //      target action

        // ------

        // try notify:
        // condition = true;
        // notify()



        Thread t1 = create(() -> {
            synchronized (monitor) {
                System.out.println("Started. " + Thread.currentThread().getName());
                while (!condition && !Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("In synchronized block" + Thread.currentThread().getName());
                        monitor.wait();
                        System.out.println("Notified. " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Finished. " + Thread.currentThread().getName());
            }
        });
        Thread t2 = create(() -> {
            synchronized (monitor) {
                System.out.println("Started. " + Thread.currentThread().getName());
                while (!condition && !Thread.currentThread().isInterrupted()) {
                    try {
                        System.out.println("In synchronized block" + Thread.currentThread().getName());
                        monitor.wait();
                        System.out.println("Notified. " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Finished. " + Thread.currentThread().getName());
            }
        });

        t1.start();
        t2.start();


    }

    private static Thread create(Runnable r) {
        return new Thread(r);
    }
}
