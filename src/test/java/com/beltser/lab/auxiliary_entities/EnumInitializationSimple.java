package com.beltser.lab.auxiliary_entities;

public enum EnumInitializationSimple {
    FIRST("11"),
    SECOND("12"),
    THIRD("13");
    /*
     *  1. var
     *  2. STATIC var
     *  3. STATIC block
     *  4. constructor
     *  5. var
     *  6. STATIC var
     *  7. STATIC block
     *
     */

    public String secretNumber1 = init1();

    public static final String static1 = staticInit1();

    static {
        System.out.println("static block - block-1");
    }

    EnumInitializationSimple(String number) {
        System.out.println("Constructor");
    }

    public String secretNumber2 = init2();
    public static final String static2 = staticInit2();

    static {
        System.out.println("static block - block-2");
    }

    private String init1() {
        System.out.println("method - 'init-1'");
        return "init-1";
    }

    private String init2() {
        System.out.println("method - 'init-2'");
        return "init-2";
    }

    private String init34() {
        System.out.println("method - 'init-34'");
        return "init-34";
    }

    private String init5() {
        System.out.println("method - 'init-5'");
        return "init-5";
    }

    private String init6() {
        System.out.println("method - 'init-6'");
        return "init-6";
    }

    private static String staticInit1() {
        System.out.println("static method - 'static init-1'");
        return "i'm static";
    }

    private static String staticInit2() {
        System.out.println("static method - 'static init-2'");
        return "i'm static";
    }
}
