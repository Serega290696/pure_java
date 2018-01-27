package com.beltser.lab.instrumentation_api;

import com.beltser.lab.entities.Bird;
import javassist.*;
import javassist.util.proxy.ProxyFactory;

import java.lang.reflect.Field;

public class JavaassistExample {
    public static void main(String[] args) {
//        injectFieldInBytecode("wifeName", "java.lang.String",
//                "Bird");
        ProxyFactory factory = new ProxyFactory();
        factory.setSuperclass(Bird.class);
        Class sing = factory.createClass(m -> {
            return !m.getName().equals("sing");
        });
        try {
            Bird instance = (Bird) sing.newInstance();
            instance.sayColor();
            instance.sing();
//            ((Proxy) instance).;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private static void injectFieldInBytecode(String fieldName, String fieldTypeName, String targetClassName) {
        ClassPool classPool = ClassPool.getDefault();
//        classPool.importPackage(
//                fieldTypeName.substring(0, fieldTypeName.indexOf('.'))
//                "com.beltser.instrumentation_api.classes"
//        );
        try {
            CtClass fieldType = classPool.get(fieldTypeName);
            CtClass targetClass = classPool.get(targetClassName);
            CtField field = new CtField(fieldType, fieldName, targetClass);
            targetClass.addField(field);
            System.out.println("Added field via javaassist: ");
            for (CtField ctField : targetClass.getDeclaredFields()) {
                System.out.println("\tctField: " + ctField.getSignature() + " " + ctField.getName());
            }
            System.out.println("But it not add to natural Bird class: ");
            for (Field field1 : Bird.class.getDeclaredFields()) {
                System.out.println("\tfield.getName() = " + field1.getName());
            }
        } catch (NotFoundException | CannotCompileException e) {
            e.printStackTrace();
        }
    }


}
