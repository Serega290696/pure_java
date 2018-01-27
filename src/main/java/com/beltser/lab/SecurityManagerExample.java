package com.beltser.lab;

import com.beltser.lab.entities.Bird;

import java.io.FilePermission;
import java.io.IOException;
import java.net.Socket;
import java.security.*;
import java.util.concurrent.TimeUnit;

public class SecurityManagerExample {

    public static void main(String[] args) {
        System.out.println("Default security manager: " + System.getSecurityManager());
//        setupSecurityManager();
//        setupSecurityManager2();
        // 3d way: -Djava.security.manager -Djava.security.policy==/usr/lib/jvm/java-9-oracle/lib/security/MY_CUSTOM.policy
        System.out.println("Current security manager: " + System.getSecurityManager());

//        System.out.println(System.getProperty("java.version"));
//        openForbiddenPort(); // forbidden
         new PrivilegedAction() {
            @Override
            public Object run() {
                System.out.println("Yuhuu");
                openForbiddenPort(); // forbidden
                return null;
            }
        };
        PrivilegedExceptionAction yuhuu =new PrivilegedExceptionAction<String >() {
            @Override
            public String  run() throws Exception {
                openForbiddenPort(); // forbidden
                return null;
            }
        };
        AllPermission all = new AllPermission();
        AccessControlContext context = AccessController.getContext();
        try {
            AccessController.doPrivileged(yuhuu);
        } catch (PrivilegedActionException e) {
            e.printStackTrace();
        }
        int []a;
//        stopThread(); // not forbidden
//        System.getSecurityManager().
//        Thread.currentThread().get
//        InnerClass2.hackAll(); // forbidden

//        System.out.println(Bird.class.getProtectionDomain().getCodeSource());
//        Permission permToCheck = new FilePermission("temp/*", "read");
//        SecurityManager security = System.getSecurityManager();
//        if (security != null)
//            security.checkPermission(permToCheck);
        System.out.println("Successfully");
    }

    private static void setupSecurityManager2() {
        System.setProperty("java.security.policy", "/usr/lib/jvm/java-9-oracle/lib/security/MY_CUSTOM.policy");
        System.setSecurityManager(new SecurityManager());
    }

    private static void setupSecurityManager() {
        SecurityManager s = new SecurityManager();
//        s.getSecurityContext()
//        FilePermission read = new FilePermission("/tmp/*", "read");
//        AccessController.doPrivileged()
        System.setSecurityManager(s);
    }

    private static void stopThread() {
        Thread thread = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        thread.start();
        thread.stop();
    }

    private static class InnerClass2 {
        public static void hackAll() {
            System.out.println(Bird.class.getProtectionDomain().getCodeSource());
            Permission permToCheck = new FilePermission("temp/*", "read");
            SecurityManager security = System.getSecurityManager();
            if (security != null)
                security.checkPermission(permToCheck);
        }
    }

    private static void openForbiddenPort() {
        try {
            new Socket("localhost", 124);
        } catch (IOException e) {
            System.out.println("Could not listen on port: 124");
            System.exit(-1);
        }
        System.out.println("Listening OK.");
    }


    static abstract class Parent {
        public void myMethod() {
        }
    }

    static abstract class A extends Parent {
        public abstract void myMethod();

        public abstract int hashCode();
    }

    static class B extends A {

        @Override
        public void myMethod() {

        }

        @Override
        public int hashCode() {
            return 0;
        }
    }
}
