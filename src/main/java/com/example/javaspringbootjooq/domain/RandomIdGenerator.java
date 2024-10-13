package com.example.javaspringbootjooq.domain;

import java.util.Random;

public class RandomIdGenerator {

    public static int generate() {
        return new Random().nextInt(1000);
    }
}
