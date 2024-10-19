package com.example.javaspringbootjooq.utils;

import java.util.List;

public class ListUtil {

    public static <T> T getOrNull(List<T> list, int index) {
        if (index >= 0 && index < list.size()) {
            return list.get(index);
        }
        return null;
    }
}
