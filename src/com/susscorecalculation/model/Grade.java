package com.susscorecalculation.model;

import java.util.Arrays;
import java.util.stream.Collectors;

public enum Grade {
    A(90, 100, 'A'),
    B(80, 89, 'B'),
    C(70, 79, 'C'),
    D(60, 69, 'D'),
    F(0, 60, 'F');

    private final int min;
    private final int max;
    private final char grade;

    Grade(int min, int max, char grade) {
        this.min = min;
        this.max = max;
        this.grade = grade;
    }

    public static Grade from(double grade) {
        try {
            return Arrays.stream(values())
                    .filter(g -> g.max >= grade && g.min <= grade)
                    .collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }

    @Override
    public String toString() {
        return String.valueOf(grade);
    }
}
