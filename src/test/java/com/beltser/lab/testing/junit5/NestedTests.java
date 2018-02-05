package com.beltser.lab.testing.junit5;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class NestedTests {
    @BeforeAll
    static void beforeAll() {
        System.out.println("NestedTests.beforeAll");
    }

    @BeforeEach
    void beforeEach() {
        System.out.println("NestedTests.beforeEach");
    }

    @Test
    void test() {
        System.out.println("NestedTests.test");
    }

    @org.junit.jupiter.api.Nested
    static class Nested {
        @BeforeAll
        static void beforeAll() {
            System.out.println("Nested.beforeAll");
        }

        @BeforeEach
        void beforeEach() {
            System.out.println("Nested.beforeEach");
        }

        @Test
        void nestedTest() {
            System.out.println("In nested test");
        }
    }
}
