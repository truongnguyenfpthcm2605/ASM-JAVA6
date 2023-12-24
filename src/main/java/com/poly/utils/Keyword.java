package com.poly.utils;

import java.util.Random;

public class Keyword {
    public static final String keyCode = "Mainkey";
    public static final String accountSession = "account";
    public static final String voucher = "keyVoucher";
    public static final String search = "keySearch";
    public static String keyCodeRandom() {
        Random ran = new Random();
        StringBuilder str = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            str.append(ran.nextInt(10));
        }
        return str.toString();
    }
}
