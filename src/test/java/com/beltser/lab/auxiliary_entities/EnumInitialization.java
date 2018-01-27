package com.beltser.lab.auxiliary_entities;

public enum EnumInitialization {
    FIRST("11"),
    SECOND("12"),
    THIRD("13");
    /*
     *  1. var
     *  2. STATIC var
     *  3. STATIC block
     *  4. var
     *  5. STATIC var
     *  6. STATIC block
     *  7. var (via method)
     *  8. var (default)
     *  9. var (default)
     *  10. var (default)
     *  11. constructor
     *
     */
    public String secretNumber5 = init5();

    public static final String static1 = staticInit1();

    static {
        System.out.println("static block - block-1");
    }

    public String secretNumber6 = init6();

    public static final String static2 = staticInit2();

    static {
        System.out.println("static block - block-2");
    }

    public String secretNumber1 = init1();

    public String secretNumber2 = "14";

    EnumInitialization(String number) {
        System.out.println("Constructor");
        System.out.println("var2 value: " + secretNumber2);
        System.out.println("--------------------------------");
        this.secretNumber2 = number;
    }

    public String secretNumber3 = init34();
    {
        System.out.println("block - 'block-1'");
    }
    public String secretNumber4 = init34();

    private String init1() {
        System.out.println("method - 'init-1'");
        return "init-1";
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
