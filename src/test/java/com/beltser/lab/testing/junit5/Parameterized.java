package com.beltser.lab.testing.junit5;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class Parameterized {

    @ParameterizedTest
    @ValueSource(strings = { "Hello", "World", "hello", "world", "123", " a " })
    void ppp(String args) {
        Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Matcher m = p.matcher(args);
        assertTrue(m.matches());
    }
}
