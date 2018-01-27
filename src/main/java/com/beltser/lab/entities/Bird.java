package com.beltser.lab.entities;

public class Bird {
    private String color = "red";

    public void sing() {
        System.out.println("La-la-la");
    }

    public String getColor() {
        return color;
    }

    public void sayColor() {
        System.out.println("color = " + color);
    }
}
