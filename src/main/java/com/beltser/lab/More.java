package com.beltser.lab;

import java.util.concurrent.TimeUnit;

public class More {

    static volatile boolean finish = false;

    static int m() {
        int i = 0;
        i += 2;
        i += 3;
        int x = 1;
        i += x;
        return i;
    }

    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            for (int i = 0; i < 1000_000; i++) {
                m();
            }
            finish = true;
        });
        thread.start();
        for (int i = 0; !finish; i++) {
            try {
                TimeUnit.MILLISECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            thread.interrupt();
        }

//        Object o = new Object() {
//            @Override
//            public int hashCode() {
//                return 123;
//            }
//        };
//        System.out.println(o.hashCode());
//        System.out.println(System.identityHashCode(o));
//        if (null == null) {
//            System.out.printf("www");
//        }
//        System.out.println(int.class);
//        int a = 2;
//        int[] a1 = new int[2];
//        a1.clone();
//        System.out.println("".hashCode());
//        System.out.println("1".hashCode());
//        System.out.println(new String("1").hashCode());
//        System.out.println("134".hashCode());
//        System.out.println("134134134".hashCode());
//        System.out.println("134134134".hashCode());
////        Console console =
////                System.console();
////        System.identityHashCode()
////        console.readLine();
    }


}
