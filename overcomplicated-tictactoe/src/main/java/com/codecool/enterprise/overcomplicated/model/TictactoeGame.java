package com.codecool.enterprise.overcomplicated.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TictactoeGame {

    private static final List<List<Integer>> WINCASES = new ArrayList<>();

    static {
        WINCASES.add(Arrays.asList(0,1,2));
        WINCASES.add(Arrays.asList(3,4,5));
        WINCASES.add(Arrays.asList(6,7,8));
        WINCASES.add(Arrays.asList(0,3,6));
        WINCASES.add(Arrays.asList(1,4,7));
        WINCASES.add(Arrays.asList(2,5,8));
        WINCASES.add(Arrays.asList(0,4,8));
        WINCASES.add(Arrays.asList(2,4,6));
    }

    public static List<List<Integer>> getWINCASES() {
        return WINCASES;
    }
}
