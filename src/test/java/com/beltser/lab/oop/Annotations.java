package com.beltser.lab.oop;

import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.reflect.Method;

public class Annotations {
    @Test
    void inheritens() {

        for (Method method : Annotations.class.getDeclaredMethods()) {
            if (method.getName().equals("justMethod")) {
                System.out.println("find it");
                Annotation[] ans = method.getDeclaredAnnotations();
                for (Annotation an : ans) {
                    System.out.println("Detect annotation: " + an.getClass());
                }
                if (method.isAnnotationPresent(Child.class)) {
                    Child annotation = method.getAnnotation(Child.class);
                    if (annotation != null) {
                        System.out.println(annotation.surname());
                        System.out.println(annotation.annotationType().getAnnotation(Parent.class).name());
                    }
                }
            }
        }
    }

    @Child
    private void justMethod() {

    }

    @Retention(RetentionPolicy.RUNTIME)
    @interface Parent {
        String name() default "unknown";
    }

    @Parent()
    @Retention(RetentionPolicy.RUNTIME)
    @interface Child {
        String surname() default "unknown-2";
        String name() default "aa";
    }
}
