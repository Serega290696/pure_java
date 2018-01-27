package com.beltser.lab.mathematics;

import java.util.BitSet;

public class Factorial {
    public static void main(String[] args) {
        double z = 2.5;
        double factorial = factorial(z);
//        double gamma = gamma(z);
        System.out.println("factorial = " + factorial);
        ;
    }

    private static double factorial(double z) {
        return gamma(z + 1);
    }

//    static double gamma(double z) {
//        double tmp1 = Math.sqrt(2 * Math.PI / z);
//        double tmp2 = z + 1.0 / (12 * z - 1.0 / (10 * z));
//        tmp1 = Math.pow(z / Math.E, z);
//        tmp2 = Math.pow(tmp1 / Math.E, z);
//        return tmp1 * tmp2;
//    }

    static double logGamma(double x) {
        double tmp = (x - 0.5) * Math.log(x + 4.5) - (x + 4.5);
        double ser = 1.0 + 76.18009173    / (x + 0)   - 86.50532033    / (x + 1)
                + 24.01409822    / (x + 2)   -  1.231739516   / (x + 3)
                +  0.00120858003 / (x + 4)   -  0.00000536382 / (x + 5);
        return tmp + Math.log(ser * Math.sqrt(2 * Math.PI));
    }
    static double gamma(double x) { return Math.exp(logGamma(x)); }
}
