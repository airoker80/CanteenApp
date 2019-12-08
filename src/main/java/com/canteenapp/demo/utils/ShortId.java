package com.canteenapp.demo.utils;

import java.util.Random;

public class ShortId {
    private static char[] BASE62 = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".toCharArray();

    private static Random RANDOM = new Random();

    public static String random62() {
        return random62(8);
    }

    public static String random62(int length) {
        final StringBuilder sb = new StringBuilder(length);

        for (int i = 0; i < length; i++) {
            sb.append(BASE62[RANDOM.nextInt(62)]);
        }

        return sb.toString();
    }
}
