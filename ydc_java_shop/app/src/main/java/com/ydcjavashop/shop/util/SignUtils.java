package com.ydcjavashop.shop.util;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.ydc.config.Constant;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


public class SignUtils {

    private static String UTF_8 = "UTF-8";

    public static HashMap<String, String> getParams(HashMap<String, String> paramsData, String token) {
        HashMap<String, String> params = new HashMap<>();
        params.put("appId", Constant.appId);
        if (paramsData != null) {
            String data = new Gson().toJson(paramsData);
            params.put("data", data);
        }
        if (!TextUtils.isEmpty(token)) {
            params.put("sessionId", token);
        }
        params.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        params.put("sig", SignUtils.sign(params, Constant.SECRETKEY));
        return params;

    }

    /**
     * 使用<code>secret</code>对paramValues按以下算法进行签名： <br/>
     * uppercase(hex(sha1(secretkey1value1key2value2...secret))
     *
     * @param paramValues 参数列表
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, String secret) {
        return sign(paramValues, null, secret);
    }

    /**
     * 对paramValues进行签名，其中ignoreParamNames这些参数不参与签名
     *
     * @param paramValues
     * @param ignoreParamNames
     * @param secret
     * @return
     */
    public static String sign(Map<String, String> paramValues, List<String> ignoreParamNames, String secret) {
        try {
            StringBuilder sb = new StringBuilder();
            List<String> paramNames = new ArrayList<String>(paramValues.size());
            paramNames.addAll(paramValues.keySet());
            if (ignoreParamNames != null && ignoreParamNames.size() > 0) {
                for (String ignoreParamName : ignoreParamNames) {
                    paramNames.remove(ignoreParamName);
                }
            }
            Collections.sort(paramNames);

            sb.append(secret);
            for (String paramName : paramNames) {
                sb.append(paramName).append(paramValues.get(paramName));
            }
            sb.append(secret);
            System.out.println("原始的签名：" + sb.toString());
            byte[] sha1Digest = getSHA1Digest(sb.toString());
            System.out.println("sha-1的二进制码：" + String.valueOf(sha1Digest));
            return byte2hex(sha1Digest);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String utf8Encoding(String value, String sourceCharsetName) {
        try {
            return new String(value.getBytes(sourceCharsetName), UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private static byte[] getSHA1Digest(String data) throws IOException {
        byte[] bytes = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            bytes = md.digest(data.getBytes(UTF_8));
        } catch (GeneralSecurityException gse) {
            throw new IOException(gse.getMessage());
        }
        return bytes;
    }

    //    private static byte[] getMD5Digest(String data) throws IOException {
    //        byte[] bytes = null;
    //        try {
    //            MessageDigest md = MessageDigest.getInstance("MD5");
    //            bytes = md.digest(data.getBytes(UTF_8));
    //        } catch (GeneralSecurityException gse) {
    //            throw new IOException(gse.getMessage());
    //        }
    //        return bytes;
    //    }

    /**
     * 二进制转十六进制字符串
     *
     * @param bytes
     * @return
     */
    private static String byte2hex(byte[] bytes) {
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if (hex.length() == 1) {
                sign.append("0");
            }
            sign.append(hex.toUpperCase());
        }
        System.out.println("最终的签名：" + sign);
        return sign.toString();
    }

    public static String getUUID() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().toUpperCase();
    }


}
