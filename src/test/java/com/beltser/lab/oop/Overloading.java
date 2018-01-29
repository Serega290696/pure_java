package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

public class Overloading {

    @Test
    void entry1() {
        byte b = (byte) 3;
        promotion(b); // promote to double or lower

        long l = Long.MAX_VALUE-3;
        promotionLongToFloat(l);
    }

    private void promotionLongToFloat(float f) {
        System.out.println(f);
        System.out.println(Long.MAX_VALUE -3 == f);
    }

    private void promotion(double x) {

    }

    private void a(int x) {

    }

}
