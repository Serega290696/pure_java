package com.beltser.lab;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CollectionsFramework {
    private static <K, V> void  printMap(Map<K, V> map) {
        for (Map.Entry<K, V> kvEntry : map.entrySet()) {
            System.out.println(kvEntry.getKey() + " -> " + kvEntry.getValue());
        }
    }

    @Test
    void backedByCollections() {
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "a");
        map.put(2, "b");
        map.put(3, "c");
        map.put(27, "zzz");
        HashMap<Object, Object> copy = new HashMap<>(map);
        HashMap<Object, Object> copy2 = new HashMap<>(map);
        printMap(map);
        printMap(copy);
//        map.remove(1);
//        map.remove(3);
//        map.put(4, "$$$");
        copy.keySet().retainAll(Arrays.asList(1, 27));
        System.out.println("=======");
        printMap(map);
        printMap(copy);
    }
}
