package com.beltser.lab.justjava;

import org.junit.jupiter.api.Test;

public class New {
    @Test
    void newVsClassForName() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        // Deprecated
        Class.forName("i'm deprecated").newInstance();
        // Create java.lang.reflect via constructor
        New.class.getDeclaredConstructors();
        //
    }
}
