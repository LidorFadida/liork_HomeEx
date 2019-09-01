package com.company;

import java.util.HashMap;
import java.util.Map;

public enum ShapeDict {
    CIRCLE(1), RECTANGLE(2), TRIANGLE(3);

    private int value;
    private static Map<Integer,ShapeDict> map = new HashMap<>();

    static {
        for (ShapeDict shapeDict : ShapeDict.values()) {
            map.put(shapeDict.value, shapeDict);
        }
    }

    ShapeDict(int value) {
        this.value = value;
    }

    public static ShapeDict valueOf(int value) {
        return  map.get(value);
    }

    public  int getValue() {
        return value;
    }
}
