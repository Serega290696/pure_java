package com.beltser.lab.testing.junit5;

import org.junit.jupiter.api.*;

import java.util.EmptyStackException;
import java.util.Stack;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("JUnit 5 new features testing")
public class JUnit5Basics {
    // вместо @BeforeClass
    @BeforeAll
    static void initAll() {
        System.out.println("Before all");
    }

    // вместо @Before
    @BeforeEach
    void init() {
        System.out.println("Before each");
    }

    @Test
    void succeedingTest() {
    }

    @Test
    void failingTest() {
        fail("a failing test");
    }

    private void fail(String msg) {
        throw new RuntimeException(msg);
    }

    // Вместо @Ignore
    @Test
    @Disabled("for demonstration purposes")
    void skippedTest() {
        // not executed
    }

    // Новая аннотация для улучшения читаемости при выводе результатов тестов.
    @DisplayName("╯°□°）╯")
    @Test
    void testWithDisplayNameContainingSpecialCharacters() {}

    // вместо @After
    @AfterEach
    void tearDown() {
    }

    // вместо @AfterClass
    @AfterAll
    static void tearDownAll() {
    }

}
