package com.dfire.grade.manager.utils;

import org.apache.commons.httpclient.util.EncodingUtil;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * User:huangtao
 * Date:2016-03-24
 * description：
 */
public class MessageDigestUtil {
    private String MD5Way = "MD5";

    public byte[] getByteCode(String str) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance(MD5Way);
        messageDigest.update(str.getBytes());
        return messageDigest.digest();
    }

    /**
     * @param str1
     * @param str2
     * @return
     * @throws NoSuchAlgorithmException
     */
    public boolean Verify(String str1, String str2) throws NoSuchAlgorithmException {
        byte[] nowBytes = getByteCode(str1);
        return false;
    }

    public String getStrCode(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest messageDigest = MessageDigest.getInstance(MD5Way);
        messageDigest.update(str.getBytes());
        byte[] byteArray = str.getBytes("UTF-8");
        byte[] md5Bytes = messageDigest.digest();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & oxff;
            if (val < 16) {
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(val));
        }
        return buffer.toString().trim();
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        MessageDigestUtil util = new MessageDigestUtil();
        String str = "aefedrgtfrhyt6f";
        byte[] strByte = str.getBytes();
        byte[] myByte = util.getByteCode(str);
        String nyStr = util.getStrCode(str);
        System.out.println(nyStr);
        for (int i = 0; i < myByte.length; i++) {
            System.out.print(myByte[i]);
        }
        for (int i = 0; i < strByte.length; i++) {
            System.out.print(strByte[i]);
        }

    }
}
