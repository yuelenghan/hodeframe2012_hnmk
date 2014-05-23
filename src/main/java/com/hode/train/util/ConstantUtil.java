package com.hode.train.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConstantUtil {

    // 档案库图片硬盘根路径
    public static String RECORD_PIC_ROOT_PATH;

    // 档案库图片web访问根路径
    public static String RECORD_PIC_HTTP_ROOT_PATH;

    // 期次图片硬盘根路径
    public static String TRAIN_PIC_ROOT_PATH;

    // 期次图片web访问根路径
    public static String TRAIN_PIC_HTTP_ROOT_PATH;

    static {
        InputStream in = ConstantUtil.class.getResourceAsStream("/path.properties");
        Properties prop = new Properties();

        try {
            prop.load(in);
            RECORD_PIC_ROOT_PATH = prop.getProperty("record.pic.root.path").trim();
            RECORD_PIC_HTTP_ROOT_PATH = prop.getProperty("record.pic.http.root.path").trim();
            TRAIN_PIC_ROOT_PATH = prop.getProperty("train.pic.root.path").trim();
            TRAIN_PIC_HTTP_ROOT_PATH = prop.getProperty("train.pic.http.root.path").trim();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
}
