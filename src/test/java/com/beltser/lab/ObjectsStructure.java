package com.beltser.lab;

import org.junit.jupiter.api.Test;
//import sun.misc.Unsafe;
//import sun.reflect.Reflection;

import java.lang.reflect.Field;

public class ObjectsStructure {

//
//    @Test
//    void testSize() {
//        Unsafe u = T.get();
//
//        long obj = T.o2a(new Example());
//        for (int i = 0; i < 28; i++)
//            System.out.print(u.getByte(obj + i) + " ");
//    }
//
//    static class Example {
//        private int x;
//        private int y;
//        private int z;
//    }
//
//    static public class T {
//
//        public static Unsafe u;
//        private static long fieldOffset;
//        private static T instance = new T();
//        private Object obj;
//
//        static {
//            try {
//                Field f = Unsafe.class.getDeclaredField("theUnsafe");
//                f.setAccessible(true);
//
//                u = (Unsafe) f.get(null);
//                fieldOffset = u.objectFieldOffset(T.class.getDeclaredField("obj"));
//            } catch (Exception ex) {
//                throw new RuntimeException(ex);
//            }
//        }
//
//        ;
//
//        public synchronized static long o2a(Object o) {
//            instance.obj = o;
//            return u.getLong(instance, fieldOffset);
//        }
//
//        public synchronized static Object a2o(long address) {
//            u.putLong(instance, fieldOffset, address);
//            return instance.obj;
//        }
//
//        public static Unsafe get() {
//            return u;
//        }
//    }
}
