package com.longyi.shopping.common;

public class CodeUtils {
    public static String orderCode() {
        String str =""+System.currentTimeMillis();
        str += (int) ((Math.random() * 9 + 1) * 100);
        return str;
    }
}
