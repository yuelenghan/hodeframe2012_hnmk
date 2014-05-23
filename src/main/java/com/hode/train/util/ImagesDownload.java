package com.hode.train.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImagesDownload extends HttpServlet {

    private static final long serialVersionUID = -3553050676629108382L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String id = req.getParameter("id");
        try {
            // 压缩文件夹
            String directoryPath = ConstantUtil.TRAIN_PIC_ROOT_PATH + id;
            if (new File(directoryPath).exists()) {
                String zipFile = directoryPath + ".zip";
                ZipUtil.compress(directoryPath, zipFile);

                // 下载
                File file = new File(zipFile);
                InputStream fis = new BufferedInputStream(new FileInputStream(
                        zipFile));
                byte[] bytes = new byte[2048];
                int length = 0;

                if (!resp.isCommitted()) {
                    resp.reset();
                }
                resp.setContentType("application/zip");
                resp.setContentLength((int) file.length());
                resp.setHeader("Content-Dispositon",
                        "attachment;filename=test.zip");
                ServletOutputStream out = resp.getOutputStream();
                while ((length = fis.read(bytes)) > 0) {
                    out.write(bytes, 0, length);
                }
                out.flush();
                out.close();
                fis.close();
            } else {
                resp.setCharacterEncoding("utf-8");
                resp.getWriter().write("此培训期次没有图片信息！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
