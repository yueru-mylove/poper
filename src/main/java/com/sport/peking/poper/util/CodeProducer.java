package com.sport.peking.poper.util;

import java.util.Random;

public class CodeProducer {


    public static String loginCode() {
        int flag = new Random().nextInt(999999);
        if (flag < 100000) {
            flag += 100000;
        }
        System.out.println(flag);

        return String.valueOf(flag);
    }
}
