package com.beltser.lab;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class StreamApi {

    private final Random random = new Random();
    private final Supplier<Integer> intGenerator = () -> random.nextInt(10);

    @Test
    void avg() {
        List<Integer> integers = integers(20);
        Double avg1;
        Map<Integer, Long> avg2;
        Double avg3;
        avg1 = integers.stream().collect(Collectors.averagingInt(i -> i));
        IntSummaryStatistics collect = integers.stream().collect(Collectors.summarizingInt(i -> i));
        collect.getAverage();
        Function.identity(); // что получил то и вернул
//        System.out.println("avg = " + avg);
        for (int i = 0; i < integers.size(); i++) {
            System.out.println("integers.get(i) = " + integers.get(i));
        }
    }

    private List<Integer> integers(int size) {
        return Stream.generate(intGenerator).limit(size).collect(Collectors.toList());
    }

    @Test
    void groupBy() {
        Map<Integer, List<Integer>> unique = Stream.generate(intGenerator)
                .limit(20)
                .distinct()
                .collect(Collectors.groupingBy(el -> el))
//                .collect(Collectors.)
                ;
        integers(2).stream().collect(Collectors.groupingBy(Integer::intValue, Collectors.counting()));

        printMap(unique);
    }

    private void printMap(Map<Integer, List<Integer>> map) {
        System.out.println("Map size: " + map.size());
        for (Map.Entry<Integer, List<Integer>> pair : map.entrySet()) {
            System.out.println(pair.getKey() + " -> " + pair.getValue());
        }
    }
}
