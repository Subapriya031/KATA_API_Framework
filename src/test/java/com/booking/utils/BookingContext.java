package com.booking.utils;

import java.util.*;


public class BookingContext {
    private static final Map<String, String> context = new HashMap<>();

    public BookingContext() {
        // TODO Auto-generated constructor stub
    }
    public static String get(String key) {
        return context.get(key);
    }

    public static void clear() {
        context.clear();
    }

    public static void set(String key, String value) {
        context.put(key, value);

    }
}
