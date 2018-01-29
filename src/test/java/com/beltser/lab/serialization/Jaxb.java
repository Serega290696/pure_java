package com.beltser.lab.serialization;

import org.junit.jupiter.api.Test;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

public class Jaxb {
    @Test
    void marchall() throws JAXBException {
        RootClass o = new RootClass();
        o.setArray(new int[]{1, 3, 7, 15});
        o.setStringList(Arrays.asList("one", "two", "three"));
        o.setWeapon(14);

        String packageName = o.getClass().getPackage().getName();
        JAXBContext jc = JAXBContext.newInstance(packageName);
        Marshaller marshaller = jc.createMarshaller();
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        File file = new File("marchalled.xml");
        marshaller.marshal(o, file);
        RootClass unmarshaled = (RootClass) unmarshaller.unmarshal(file);
        System.out.println("unmarshaled.getStringList() = " + unmarshaled.getWeapon());
        System.out.println("unmarshaled.getStringList() = " + unmarshaled.getStringList());
        System.out.println("unmarshaled.getStringList() = " + Arrays.toString(unmarshaled.getArray()));
    }

    @XmlRootElement
    @XmlAccessorType(XmlAccessType.NONE)
    @XmlType(name = "dataObj")
    public static class RootClass {
        @XmlElement
        private int weapon;

        @XmlElement
        @XmlElementWrapper(name = "elems")
        private int[] array;
        @XmlElement
        @XmlElementWrapper(name = "myList")
        private List<String> stringList;

        public int getWeapon() {
            return weapon;
        }

        public void setWeapon(int weapon) {
            this.weapon = weapon;
        }

        public int[] getArray() {
            return array;
        }

        public void setArray(int[] array) {
            this.array = array;
        }

        public List<String> getStringList() {
            return stringList;
        }

        public void setStringList(List<String> stringList) {
            this.stringList = stringList;
        }
    }
}
