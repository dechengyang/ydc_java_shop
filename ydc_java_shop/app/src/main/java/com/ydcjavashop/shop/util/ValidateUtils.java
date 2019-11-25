package com.ydcjavashop.shop.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by decheng.yang on 2018/2/22.
 */

public class ValidateUtils {
    /**
     * 判断手机号格式
     *
     * @param phoneNumber 手机号码
     * @return true(正确的手机号13 14 15 17 18);false 非
     */
    public static boolean checkPhoneNumber(String phoneNumber) {
        Pattern p = Pattern
                .compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9])|(16[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(phoneNumber);
        return m.matches();
    }
}
