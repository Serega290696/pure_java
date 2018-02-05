package com.beltser.lab.testing.junit5;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.RepeatedTest;

import java.util.EnumSet;
import java.util.concurrent.TimeUnit;


import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SomeAnnotations {
    @RepeatedTest(value = 5, name = "iteration {currentRepetition} of {totalRepetition}")
    void repeatedTest() {
        System.out.println("Этот тест будет запущен пять раз. ");
    }

    /////////////// ParameterizedTest ////////////////
    @Nested
    class ParameterizedTests {
//        @ParameterizedTest
//        @ValueSource(strings = {"Hello", "World"})
        void testWithStringParameter(String argument) {
            assertNotNull(argument);
        }

//        @ParameterizedTest
//        @CsvSource({"foo, 1", "bar, 2", "'baz, qux', 3"})
        void testWithCsvSource(String first, int second) {
            assertNotNull(first);
            assertNotEquals(0, second);
        }

//        @ParameterizedTest
//        @EnumSource(value = TimeUnit.class, names = {"DAYS", "HOURS"})
        void testWithEnumSourceInclude(TimeUnit timeUnit) {
            assertTrue(EnumSet.of(TimeUnit.DAYS, TimeUnit.HOURS).contains(timeUnit));
        }
    }
}
