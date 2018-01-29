package com.beltser.lab.serialization;

import org.junit.jupiter.api.Test;

import java.io.*;

public class ComplexSerializable {

    @Test
    void serialise() {
        ComplexSerial s = new ComplexSerial(100);
        try {
            File file = new File("serial_data.bin");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            out.writeObject(s);
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    void read() throws Exception {
        File file = new File("serial_data.bin");
        FileInputStream fin = new FileInputStream(file);
        ObjectInputStream in = new ObjectInputStream(fin);
        ComplexSerial serial = ((ComplexSerial) in.readObject());
        System.out.println(serial);
        System.out.println("serial.getI() = " + serial.a);
        System.out.println("serial.getS() = " + serial.b);
        System.out.println("serial.getObject() = " + serial.getC());
        System.out.println("serial.getSuperClassString() = " + serial.getD());
        in.close();
    }

    static class ComplexNotSerial implements Serializable {
        protected int a;
        protected int b;

        public ComplexNotSerial(int a) {
            System.out.println("Has it invoked super constructor?");
            this.a = a;
        }

        public int getA() {
            return a;
        }

        public int getB() {
            return b;
        }
    }

    static class ComplexSerial extends ComplexNotSerial implements Serializable {
        protected int c;
        protected int d;

        public int getC() {
            return c;
        }

        public int getD() {
            return d;
        }

        public ComplexSerial(int d) {
            super(2);
            System.out.println("Has it invoked constructor?");
            this.c = 2 * a;
            this.b = 10;
            this.d = d;
            /*
            a = 2;
            b = 10;
            c = 4;
            d = inputD;
             */
            /*
            Нужно убедиться что:
            1. Super class constructor is invoked.
            2.
             */

        }
    }

}
