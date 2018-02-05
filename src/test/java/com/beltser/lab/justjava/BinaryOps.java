package com.beltser.lab.justjava;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.format.SignStyle;

public class BinaryOps {
    @Test
    void shift() {
        int x = 0b0011100;
        printBinary(x);
        printBinary(x >> 2);
        printBinary(x << 2);
        printBinary(2 << x);
    }

    @Test
    void binaryRepresentation() {
        printBinary(-0);
        printBinary(-1);
        printBinary(-2);
        printBinary(-3);
        printBinary(-4);
        printBinary(Integer.MIN_VALUE, "min: ");
        printBinary(Integer.MAX_VALUE, "max: ");
        printBinary(Integer.MIN_VALUE << 2, "min<<2: ");
        printBinary(Integer.MAX_VALUE >> 2, "max>>2: ");
        printBinary(0b1000001 >> 2, "max>>2: ");
    }

    private void printBinary(int x) {
        System.out.println(x + ": " + Integer.toBinaryString(x));
    }

    private void printBinary(int x, String msg) {
        System.out.println(msg + Integer.toBinaryString(x));
    }


//    static class A {
//        public A method() throws  Throwable {
//            return new Single();
//        }
//    }
//
//    static class Single extends A {
//        public Single method(String str) throws RuntimeException { // 2
//            return new Single();
//        }
//
//        public Single method() throws Exception { //3
//            return new Ddd();
//        }
//    }
//
//    static class Ddd extends Single {
//        public void mеthod(Integer dіgit) throws ClassCastException {      // 4
//        }
//
//        public Ddd method() throws IOException {  // 5
//            return new Ddd();
//        }
//    }
}
