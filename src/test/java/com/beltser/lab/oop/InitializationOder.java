package com.beltser.lab.oop;

import com.beltser.lab.auxiliary_entities.ClassInitializationOrder;
import com.beltser.lab.auxiliary_entities.EnumInitialization;
import com.beltser.lab.auxiliary_entities.EnumInitializationSimple;
import org.junit.jupiter.api.Test;

public class InitializationOder {
    /*
       1. All static var and blocks in declaration order.
       2. all var and non-static block in declaration order.
       3. Constructor

    For enum:
       1. all var and non-static block in declaration order.
       2. Constructor
          ^ - for EVERY instance!
       3. All static var and blocks in declaration order.

     */
    @Test
    void staticEnumInitializationOrder() {
        System.out.println("Start");
        EnumInitialization second = EnumInitialization.SECOND;
        System.out.println("Finish");
    }

    @Test
    void staticEnumInitializationOrderSimple() {
        System.out.println("Start");
        EnumInitializationSimple second = EnumInitializationSimple.SECOND;
        System.out.println("Finish");
    }

    @Test
    void classInitializationOrderSimple() {
        System.out.println("Start");
        ClassInitializationOrder classInitializationOrder = new ClassInitializationOrder("test");
        System.out.println("Finish");
    }
}
