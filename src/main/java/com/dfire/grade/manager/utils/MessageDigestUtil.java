package com.dfire.grade.manager.utils;


import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
public class MessageDigestUtil {
    private static final String MD5Way = "MD5";

    /**
     * MD5加密
     *
     * @param str 被加密字符串
     * @return
     * @throws NoSuchAlgorithmException
     * @throws UnsupportedEncodingException
     */
    public static String getStrCode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(MD5Way);
        messageDigest.update(str.getBytes());
        byte[] md5Bytes = messageDigest.digest();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(val));
        }
        return buffer.toString().trim();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigestUtil util = new MessageDigestUtil();
        String str = "42345345";
        String sd = util.getStrCode(str);
        System.out.println(sd);
    }
}
