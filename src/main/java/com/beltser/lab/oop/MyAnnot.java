package com.beltser.lab.oop;

@interface MyAnnot {
    // var: primitives, String and Enums
    int version() default 1;

    String name() default "abc";

    Prior prior() default Prior.MEDIUM;

    static enum Prior {
        MEDIUM;
    }
}
