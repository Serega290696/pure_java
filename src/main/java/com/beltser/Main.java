package com.beltser;

public class Main {
    public static void main(String[] args) {

        // the same method define in both superclasses. Just redefine its.
        new C().m();
        System.out.println();

        // extend static method - FAIL
        // Why? It is NOT overriding - you cannot write @Override annotation.
        // This is just creation new method. Это логично, т.к. ты напрямую обращаешься к методу,
        // сразу будет известно какой метод запуститься
        new O().m();
        new P().m();
        O.m();
        P.m();
        System.out.println("It is not overriding. Just new method definition");

        // call static method in non-static class - FAIL
        new Main().new Si().m();
        System.out.println();

        // You can create new instance of inner class only via class-container
        Main main = new Main();
        Si si = main.new Si();
        System.out.println();

        // STATIC method in interface always DEFAULT
        I.staticMethod();
        System.out.println();

        // Upcasting
        O o = new P(); // Upcasting
        P p = new O(); // don`t think so
        Integer i = new Number(); // some weird
        Number n = new Integer(10); // OK (upcasting)
//        System.out.println("Start here.");
//        StringBuilder stringBuilder = new StringBuilder("start");
//        for (int i = 0; i < Integer.MAX_VALUE; i++) {
//            stringBuilder.append(i);
//            stringBuilder.delete(0, 1);
//        }
//
//        System.out.println("Finish");
    }


    /*
    inner interface always static
    fields always public static final.
    methods always public and NON-static.
     */
    static interface I {
        public static final int test = 1;

        public String method();
        // static methods always default
        public static void staticMethod(){

        }
    }


    interface A {

        default void m() {
            System.out.println("I am A");
        }
    }

    interface B {
        default void m() {
            System.out.println("I am B");
        }
    }

    // OVERRIDE method if multiple inheritance conflict
    static class C implements A, B {
        public void m() {
            A.super.m();
            System.out.println("I am C");
        }
    }

    // override static method - OK
    static class O {
        public static void m() {
            System.out.println("O static method");
        }
    }

    static class P extends O {
        public static void m() {
            System.out.println("P static method");
        }
    }

    static class Oi {
        public static void m() {
            System.out.println("O static method");
        }
    }
    static class Pi extends Oi {
        public static void m() {
            System.out.println("P static method");
        }
    }

    // You can get access to field via inner NON-static class
    private int secretField = 100;
    class Si {
        void m() {
            secretField = 0; // Ha-ha!
        }
    }
}
