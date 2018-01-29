package com.beltser.lab.oop;

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    @MyAnnot
    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    @MyAnnot
    public void setAge(int age) {
        this.age = age;
    }
}
