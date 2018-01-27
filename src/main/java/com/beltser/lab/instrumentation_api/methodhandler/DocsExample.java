package com.beltser.lab.instrumentation_api.methodhandler;

import com.beltser.lab.entities.Bird;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class DocsExample {

    static class A {

    }

    static class B extends A {

    }

    public void m(A a) {
        System.out.println("secret");
    }

    public static void main(String[] args) throws Throwable {
        Bird.class.getMethod("sing").invoke(new Bird());
        Object x, y;
        String s;
        int i;
        MethodType mt;
        MethodHandle mh;
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        MethodType methodType = MethodType.methodType(void.class, A.class);
        MethodHandle m = lookup.findVirtual(DocsExample.class, "m", methodType);
        m.invokeWithArguments(new DocsExample(), new A());

        // mt is (char,char)String
        mt = MethodType.methodType(String.class, char.class, char.class);
        mh = lookup.findVirtual(String.class, "replace", mt);
        s = (String) mh.invokeWithArguments("daddy", 'd', 'n');
        // invokeExact(Ljava/lang/String;CC)Ljava/lang/String;
        assertEquals(s, "nanny");

        // weakly typed invocation (using MHs.invoke)
        s = (String) mh.invokeWithArguments("sappy", 'p', 'v');
        assertEquals(s, "savvy");


        // mt is (Object[])List
        mt = MethodType.methodType(java.util.List.class, Object[].class);
        mh = lookup.findStatic(java.util.Arrays.class, "asList", mt);
        assert (mh.isVarargsCollector());
        x = mh.invoke("one", "two");
        // invoke(Ljava/lang/String;Ljava/lang/String;)Ljava/lang/Object;
        assertEquals(x, java.util.Arrays.asList("one", "two"));


        // mt is (Object,Object,Object)Object
        mt = MethodType.genericMethodType(3);
        mh = mh.asType(mt);
        x = mh.invokeExact((Object) 1, (Object) 2, (Object) 3);
        // invokeExact(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
        assertEquals(x, java.util.Arrays.asList(1, 2, 3));


        // mt is ()int
        mt = MethodType.methodType(int.class);
        mh = lookup.findVirtual(java.util.List.class, "size", mt);
        i = (int) mh.invokeExact(java.util.Arrays.asList(1, 2, 3));
        // invokeExact(Ljava/util/List;)I
        assert (i == 3);
        mt = MethodType.methodType(void.class, String.class);
        mh = lookup.findVirtual(java.io.PrintStream.class, "println", mt);
        mh.invokeExact(System.out, "Hello, world.");
        // invokeExact(Ljava/io/PrintStream;Ljava/lang/String;)V
    }

    private static void assertEquals(Object x, Object strings) {
    }
}
