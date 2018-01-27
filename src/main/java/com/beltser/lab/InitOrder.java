package com.beltser.lab;

public class InitOrder {
    public static void main(String[] args) {
        System.out.println("Welcome");
        initMethod(0);
        System.out.println("var1 = " + var1);
        System.out.println(Clazz1.var3);
    }

    static int var1 = initMethod(1);
    int var2 = initMethod(2);
    static class Clazz1 {
        static int var3 = initMethod(3);
        int var4 = initMethod(4);
    }

    static int initMethod(int number) {
        System.out.println("Init var #" + number);
        return number;
    }
}
