package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class Boxing {
    @Test
    void primitiveVsBox() {
        ArrayList<Integer> obj = new ArrayList<>();
        obj.remove(1); // its call remove(int index) instead of remove(Integer obj);
    }
}
