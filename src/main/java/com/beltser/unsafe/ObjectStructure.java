package com.beltser.unsafe;

import sun.misc.Unsafe;

public class ObjectStructure {
    private int x;
    private Integer y;

    public static void main(String[] args) {
        Unsafe u = Helper.get();

        long obj = Helper.o2a(new ObjectStructure());
        for (int i = 0; i < 1; i++)
            System.out.print(u.getByte(obj + i) + " ");
    }

}
