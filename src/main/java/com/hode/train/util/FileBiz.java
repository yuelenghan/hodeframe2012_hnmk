package com.hode.train.util;

import java.io.File;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileBiz {

    public String[] upload(HttpServletRequest request, String uploadPath,
                           boolean flag) throws Exception {
        String returnStr = "数据文件预览错误！";
        File tmpDir = new File("c:\\temp"); // 初始化上传文件的临时存放目录,必须是绝对路径
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        String fname = "";
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
            factory.setSizeThreshold(1 * 1024 * 1024);
            // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
            factory.setRepository(tmpDir);
            ServletFileUpload sfu = new ServletFileUpload(factory);
            // 指定单个上传文件的最大尺寸,单位:字节，这里设为1000Mb
            sfu.setFileSizeMax(1000 * 1024 * 1024);
            // 指定一次上传多个文件的总尺寸,单位:字节，这里设为2000Mb
            sfu.setSizeMax(2000 * 1024 * 1024);
            sfu.setHeaderEncoding("UTF-8"); // 设置编码，因为jsp页面的编码是utf-8的
            List<FileItem> fileItems = sfu.parseRequest(request);// 解析request请求
            uploadPath = uploadPath + "tmpData\\train\\student\\"; // 选定上传的目录此处为当前目录
            if (!new File(uploadPath).isDirectory()) {
                new File(uploadPath).mkdirs(); // 选定上传的目录此处为当前目录，没有则创建
            }

            int leng = fileItems.size();
            for (int n = 0; n < leng; n++) {
                FileItem item = fileItems.get(n); // 从集合中获得一个文件流
                // 如果是普通表单字段
                if (item.isFormField()) {
                    String name = item.getFieldName(); // 获得该字段名称
                    String value = item.getString("utf-8"); // 获得该字段值
                    System.out.println(name + "-------" + value);
                } else if (item.getName().length() > 0) { // 如果为文件域
                    String iname = item.getName().substring(
                            item.getName().lastIndexOf(".")); // 文件扩展名
                    fname = new Date().getTime() + iname; // 文件全名

                    item.write(new File(uploadPath, fname)); // 写入文件

                    if (flag) {
                        returnStr = CommonUtil.getHTMLByExcelName(
                                "tmpData/train/student/" + fname, false,
                                100);
                    }
                }
            }

        }

        String[] returnStrs = new String[2];
        returnStrs[0] = returnStr;
        returnStrs[1] = fname;
        return returnStrs;
    }

    public void uploads(HttpServletRequest request, String uploadPath,
                        String idCard, String imgId) throws Exception {
        File tmpDir = new File("c:\\temp");
        if (!tmpDir.exists()) {
            tmpDir.mkdirs();
        }
        if (ServletFileUpload.isMultipartContent(request)) {
            DiskFileItemFactory factory = new DiskFileItemFactory();
            // 指定在内存中缓存数据大小,单位为byte,这里设为1Mb
            factory.setSizeThreshold(1 * 1024 * 1024);
            // 设置一旦文件大小超过getSizeThreshold()的值时数据存放在硬盘的目录
            factory.setRepository(tmpDir);
            ServletFileUpload sfu = new ServletFileUpload(factory);
            // 指定单个上传文件的最大尺寸,单位:字节，这里设为5Mb
            sfu.setFileSizeMax(5 * 1024 * 1024);
            // 指定一次上传多个文件的总尺寸,单位:字节，这里设为10Mb
            sfu.setSizeMax(10 * 1024 * 1024);
            sfu.setHeaderEncoding("UTF-8"); // 设置编码，因为jsp页面的编码是utf-8的
            List<FileItem> fileItems = sfu.parseRequest(request);
            if (imgId.trim().equals("image1")) {
                uploadPath += "pic1/";
            }
            if (imgId.trim().equals("image2")) {
                uploadPath += "pic2/";
            }
            if (imgId.trim().equals("image3")) {
                uploadPath += "pic3/";
            }
            if (imgId.trim().equals("image4")) {
                uploadPath += "pic4/";
            }

            if (!new File(uploadPath).isDirectory()) {
                new File(uploadPath).mkdirs();
            }
            int leng = fileItems.size();
            for (int n = 0; n < leng; n++) {
                FileItem item = fileItems.get(n); // 从集合中获得一个文件流
                // 如果是普通表单字段
                if (item.isFormField()) {
                    String name = item.getFieldName(); // 获得该字段名称
                    String value = item.getString("utf-8"); // 获得该字段值
                    if (name.trim().equals("studentIDCode")) {
                        idCard = value.toUpperCase();
                    }
                    // System.out.println(name + " -- " + value);
                } else if (item.getName().length() > 0) { // 如果为文件域
                    String iname = item.getName().substring(
                            item.getName().lastIndexOf(".")); // 文件扩展名
                    iname = iname.toUpperCase();
                    String fname = idCard + iname; // 文件全名

                    item.write(new File(uploadPath, fname)); // 写入文件
                }
            }
        }
    }

}
