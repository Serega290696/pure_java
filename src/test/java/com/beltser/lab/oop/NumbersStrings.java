package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.stream.Stream;

public class NumbersStrings {
    static int m(byte a, int b) {
        return a + b;
    }

    static int m(short a, short b) {
        return a - b;
    }

    @Test
    void parsing() {
        Integer decode1 = Integer.decode("10");
        Integer decode2 = Integer.decode("#10");
        Integer decode3 = Integer.decode("0x10");
        System.out.println("decode = " + decode1 + " = " + decode2 + " = " + decode3);
        int i = Integer.parseInt("0x10"); // ERROR: NumberFormatException
        System.out.println("i = " + i);
    }

    @Test
    void typesConversation() {
        System.out.println(12.0 * 12); // double
        System.out.println(12 * 12.0); // double
        System.out.println(12.0 / 12); // double
        System.out.println(12 / 12.0); // double
    }

    @Test
    void wtf() {
        obj(get());
    }

    private void obj(Object s) {
        System.out.println("NumbersStrings.obj - Object");
    }

    private void obj(Number s) {
        System.out.println("NumbersStrings.obj - Number");
    }

    private void obj(Integer s) {
        System.out.println("NumbersStrings.obj - Integer");
    }

    private <T> T get() {
        return null;
    }

    @Test
    void name() {
        Number n1 = new Integer(3); // ok
        Number n2 = (Number) new Object(); // RUNTIME error
        // Потому и требуется явное преобразование. Т.к. тут могут
        // возникнут ошибки и программист должен явно подтвердить,
        // что он знает что делает. Иначе было бы неявное преобразование
        // как и в случае с Object obj = string;
    }

}
