package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class Overloading {

    @Test
    void entry1() {
        byte b = (byte) 3;
        promotion(b); // promote to double or lower

        long l = Long.MAX_VALUE - 3;
        promotionLongToFloat(l);
    }

    private void promotionLongToFloat(float f) {
        System.out.println(f);
        System.out.println(Long.MAX_VALUE - 3 == f);
    }

    private void promotion(double x) {

    }

    private void a(int x) {

    }

    @Test
    void checkPriority() {
        inh(gen());
    }

    private <T extends Object> T gen() {
        return null;
    }

    private void inh(Number o) {
        System.out.println("Overloading.inh: Object");
    }

    private void inh(Object o) {
        System.out.println("Overloading.inh: Object");
    }

    @Test
    void sneakyThrows() {
        throwsSneakyIOException();
    }

    private void throwsSneakyIOException() {
        sneakyThrow(new IOException("sneaky"));
    }

    private <E extends Throwable> void sneakyThrow(Throwable e) throws E {
        throw (E) e;
    }

//    private void inh(List o) {
//        System.out.println("Overloading.inh: List");
//    }
//    private void inh(AbstractList o) {
//        System.out.println("Overloading.inh: AbstractList");
//    }
//    private void inh(Vector o) {
//        System.out.println("Overloading.inh: Vector");
//    }
//    private void inh(Stack o) {
//        System.out.println("Overloading.inh: Stack");
//    }
}
