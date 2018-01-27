package com.beltser.lab.mathematics;

import java.util.Random;

public class Distribution {
    private static final int[] resultTest = new int[20];

    public static void main(String[] args) {
        Random r = new Random();
        for (int i = 0; i < 10000; i++) {
            double randomNumber = (double) r.nextGaussian();
            accumulateTestResult(randomNumber, -5, 5);
        }
        showResult();

    }

    private static void accumulateTestResult(double custormersPerSim, double minValue, double maxValue) {
        for (int j = 0; j < resultTest.length; j++) {
            if (inBounds(custormersPerSim, minValue+(maxValue-minValue) / (double)resultTest.length * j,
                    minValue + (maxValue-minValue) / (double)resultTest.length * (j + 1))) {
                resultTest[j]++;
                break;
            }
        }
    }
    private static boolean inBounds(double value, double lowBound, double highBound) {
        return lowBound <= value && value <= highBound;
    }


    private static void showResult() {
        for (int i = 0; i < resultTest.length; i++) {
            System.out.println(i + ". " + resultTest[i]);
        }
    }
}
