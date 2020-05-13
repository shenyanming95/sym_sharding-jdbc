package com.sym.util;

import org.apache.commons.lang3.RandomStringUtils;

/**
 * @author shenyanming
 * @date 2020/5/13 21:02.
 */

public class RandomUtil {

    private final static String STRING_PATTERN = "qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM0123456789";

    public static String getRandomString(){
        return getRandomString(6);
    }

    public static String getRandomString(int length){
        return RandomStringUtils.random(length, STRING_PATTERN);
    }
}
