package com.beltser.lab.justjava;

public class MainFromMain {
    static int countDown = 10;
    public static void main(String... args) {
        System.out.println("MainFromMain.main");
        countDown--;
        if(countDown > 0) {
            main();
        }
    }
}
