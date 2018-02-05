package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

public class Oop {

    @Test
    void whoIs() {
        Clazz c = new SubClass();
        System.out.println(c.x + " " + c.getX());
        SubClass s = new SubClass();
        System.out.println(s.x + " " + s.getX());
    }

    static class Clazz{
        int x = 2;
        public int getX(){
            return x;
        }
    }

    static class SubClass extends Clazz{
        int x = 1;
        public int getX(){
            return x;
        }
    }



    @Test
    void inherited() {
        A a = new B();
        a.test(10);
        // test(Integer) isn't polymorphic, then test(Object) invoked.
    }

    static class A {
        public void test(Object o) {
            System.out.println("Object");
        }
    }

    static class B extends A {
        public void test(Integer i) {
            System.out.println("B.test");
        }
    }

}
