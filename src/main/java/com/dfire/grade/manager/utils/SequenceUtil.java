package com.dfire.grade.manager.utils;

import java.util.UUID;

/**
 * User:huangtao
 * Date:2016-03-23
 * description：用于字符串操作
 */
public class SequenceUtil {
    /**
     * 生成32位随机字符串
     *
     * @return
     */
    public static String getSequence() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString().trim().replace("-", "");
    }

    public static void main(String[] args) {
        SequenceUtil sequenceUtil = new SequenceUtil();
        System.out.print(sequenceUtil.getSequence());
    }
}
