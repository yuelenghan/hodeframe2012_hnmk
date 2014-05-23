package com.hode.train.util;

import java.util.Random;

/**
 * 随机数工具
 *
 * @author lh
 *
 */
public class RandomUtil {

    /**
     * 生成随机数字符串
     *
     * @param num
     *            随机数位数
     * @return
     */
    public static String genRandom(int num) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < num; i++) {
            sb.append(Math.abs(random.nextInt() % 10));
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 16; i++) {
            System.out.println(genRandom(6));
        }
    }
}
