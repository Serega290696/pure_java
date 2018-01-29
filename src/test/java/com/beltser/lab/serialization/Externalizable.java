package com.beltser.lab.serialization;

import org.junit.jupiter.api.Test;

import java.io.*;

public class Externalizable {

    @Test
    void serialise() {
        ForSerial s = new ForSerial();
        s.setI(42);
        s.setS("secret here");
        s.setArray(new String[]{"a", "bbb", "qwe"});
        s.setSuperClassString("WOW");
        s.setObject(new InjectedObject(true));
        try {
            File file = new File("serial_data.bin");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);
            s.writeExternal(out);
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
        ForSerial serial = new ForSerial();
        serial.readExternal(in);
        System.out.println(serial);
        System.out.println("serial.getI() = " + serial.getI());
        System.out.println("serial.getS() = " + serial.getS());
        System.out.println("serial.getObject() = " + serial.getObject());
        System.out.println("serial.getSuperClassString() = " + serial.getSuperClassString());
        in.close();
    }

    static class NotSerial {
        private String superClassString;

        public String getSuperClassString() {
            return superClassString;
        }

        public void setSuperClassString(String superClassString) {
            this.superClassString = superClassString;
        }
    }

    static class ForSerial extends NotSerial implements java.io.Externalizable {
        private int i;
        private String s;
        transient private String[] array;
        transient private InjectedObject object;

        private void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
            out.writeObject(getSuperClassString()); // any custom information
        }

        private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
            setSuperClassString((String) in.readObject()); // restore custom object
        }

        public int getI() {
            return i;
        }

        public void setI(int i) {
            this.i = i;
        }

        public String getS() {
            return s;
        }

        public void setS(String s) {
            this.s = s;
        }

        public String[] getArray() {
            return array;
        }

        public void setArray(String[] array) {
            this.array = array;
        }

        public InjectedObject getObject() {
            return object;
        }

        public void setObject(InjectedObject object) {
            this.object = object;
        }

        @Override
        public void writeExternal(ObjectOutput out) throws IOException {
            out.writeObject("Tyt bul Vasya");
        }

        @Override
        public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
            setS((String) in.readObject());
        }
    }

    static class InjectedObject {
        private boolean b;

        public InjectedObject(boolean b) {
            this.b = b;
        }

        @Override
        public String toString() {
            return "InjectedObject {b = " + b + "}";
        }
    }

}
