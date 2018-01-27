package com.beltser.lab;

public class Main {
    public static void main(String[] args) {

        // the same method define in both superclasses. Just redefine its.
        new C().m();
        System.out.println();

        // extend static method - FAIL
        // Why? It is NOT overriding - you cannot write @Override annotation.
        // This is just creation new method. Это логично, т.к. ты напрямую обращаешься к методу,
        // сразу будет известно какой метод запуститься
        new Parent().m();
        new Child().m();
        Parent.m();
        Child.m();
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
        Parent parent = new Parent(); // Upcasting - OK
        Parent parent2 = new Child(); // Upcasting - OK
//        Child child = new Parent(); // don`t think so - FAIL
        Child child = new Child();
//        Child child2 = (Child) new Parent(); // FAIL - downcasting, but thrown ClassCastException at runtime
        Child child3 = (Child) parent2; // downcasting - OK
        System.out.println(parent instanceof Parent); // true
        System.out.println(parent instanceof Child); // FALSE
        System.out.println(parent2 instanceof Parent); // true
        System.out.println(parent2 instanceof Child); // true
        System.out.println(child instanceof Parent); // true
        System.out.println(child instanceof Child); // true
        System.out.println(child3 instanceof Parent); // true
        System.out.println(child3 instanceof Child); // true

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
        public static void staticMethod() {

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
    static class Parent {
        public static void m() {
            System.out.println("O static method");
        }
    }

    static class Child extends Parent {
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

    interface I1 {
        default void m() {

        }
    }

    interface I2 {
        default void m() {

        }
    }

    class Impl implements I1, I2 {
        @Override
        public void m() {
            I1.super.m();
            I2.super.m();
        }
    }
}
