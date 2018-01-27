package com.beltser.lab;

import org.junit.jupiter.api.Test;

public class Strings {
    @Test
    void removeDuplicatesLetters() {
        String input = "Good Oops, Bad Oops";
        String output = input.replaceAll("(?i)(\\p{L})\\1", "$1");
        System.out.println(output);
    }
}
