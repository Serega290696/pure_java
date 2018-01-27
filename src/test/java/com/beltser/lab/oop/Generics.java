package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Generics {


    @Test
    void name() {
        // ================================================
        // ================================================
//        ArrayList<Integer>[] arrayOfLists = new ArrayList<Integer>[2]; // compile-time error!
        // ================================================
        Object[] strings = new String[2];
        strings[0] = "hi";   // OK
        strings[1] = 100;    // An ArrayStoreException is thrown.
        // ================================================
        Object[] stringLists = new Object[2];
//        stringLists = new List<String>[2]; // compiler error, but pretend it's allowed
        //// it's forbidden cause we cant detect the ArrayStoreException at runtime:
        stringLists[0] = new ArrayList<Integer>();  // An ArrayStoreException should be thrown, but the runtime can't detect it.
        stringLists[1] = new ArrayList<String>();   // OK
        // ================================================
        // ================================================
        class Example {
            // compile-time ERROR.
            // The overloads would all share the same classfile representation and will generate a compile-time error.
//            public void print(List<String> strSet) { }
            public void print(List<Integer> intSet) { }
        }
    }

    /*
    Wildcard Guidelines:
1. An "in" variable is defined with an upper bounded wildcard, using the extends keyword.
2. An "out" variable is defined with a lower bounded wildcard, using the super keyword.
3. In the case where the "in" variable can be accessed using methods defined in the Object class, use an unbounded wildcard.
4. In the case where the code needs to access the variable as both an "in" and an "out" variable, do not use a wildcard.
     */
    void in(List<? extends Number> list) {
//        list.add(new Object()); // compilation ERROR
//        list.add(new Double(3)); // compilation ERROR
//        list.add(3); // compilation ERROR
//        list.set(0, list.get(0)); // compilation ERROR
        // list like read-only (almost). You can remove, clean and add null
        list.add(null);
        list.remove(0);
    }

    void out(List<? super Number> list) {
//        list.add(new Object()); // compilation ERROR
        list.add(new Double(3)); // OK
        list.add(3); // OK
    }

    void foo(List<?> i) {
//        i.add(new Object()); // compilation ERROR
//        i.add("abc"); // compilation ERROR
//        i.set(0, i.get(0)); // compilation ERROR
    }

    void a() {
        class NaturalNumber {
            private int n;
        }
        class EvenNumber extends NaturalNumber {

        }
        List<EvenNumber> le = new ArrayList<>();
        List<? extends NaturalNumber> ln = le;
//        ln.add(new NaturalNumber(35));  // compile-time error

        /*
         Generic-и создавались для избежания ошибок типов
         и в даннной ситуации они справились со своей задачей.
         Когда список парных чисел наследуется от списка
         натуральных чисел - это логично. Также логично что в список
         парных чисел нельзя вставить натуральное значение.
         */
    }

    static class A<T extends Number> {
        // while class contain generic, constructor contain its own another generic
        // for invocation:
        /*
         - A<Number> a = new <String>A("abc"); // обязательно присвоить значение или
            передать куда то чтобы было ясно какой тип ожидается
         - A<Number> a = new <>A("abc"); // diamond syntax. Тоже самое.
         -
          */
        public <T> A(T arg) {
            System.out.println(arg);
        }
    }
}


