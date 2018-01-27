package com.beltser.lab.unsafe;

import sun.misc.Unsafe;

public class V {
    private Integer b = 3;
    private int a = 2;

    public static void main(String[] argv) throws Exception {
        Unsafe u = T.get();

        long obj = T.o2a(new V());
        for (int i = 0; i < 28; i++)
            System.out.print(u.getByte(obj + i) + " ");
    }
}
