package com.beltser.lab.testing.junit5;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class JUnit5 {
    @Test
    void test() {
        assertAll(
                () -> assertEquals(method(), "greetings"),
                () -> assertSame(method(), "greetings"),
                () -> assertNotNull(method())
        );
        assertThrows(RuntimeException.class, this::fail, "I'm not fail!");

    }

    private String method() {
        return "greetings";
    }

    private String fail() {
        throw new RuntimeException("sorry, i`m fail");
    }
}
