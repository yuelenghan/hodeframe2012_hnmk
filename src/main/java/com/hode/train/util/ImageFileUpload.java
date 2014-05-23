package com.hode.train.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hode.framework.commons.config.SysConfig;

public class ImageFileUpload extends HttpServlet {

    private static final long serialVersionUID = 5603392562213223189L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("gbk");
        resp.setContentType("text/html");

        PrintWriter pw = resp.getWriter();

        String flag = req.getParameter("flag");
        HttpSession session = req.getSession();
        if (!MyStringUtil.isNullStr(flag) && flag.trim().equals("1")) {
            // 导入图片信息请求
            String fname = session.getAttribute("fname").toString();
            String directory = fname.substring(0, fname.lastIndexOf("."));
            // System.out.println(fname);
            try {
                // 解压缩临时文件
                UnZip unZip = new UnZip();
                unZip.setEncoding("GB2312");
                unZip.setOverwrite(true);
                unZip.zip(SysConfig.strHodeRealPath + "tmpData/train/student/"
                        + fname, SysConfig.strHodeRealPath
                        + "tmpData/train/student/" + directory);

                // 校验解压缩得到的文件夹结构是否满足模版的要求
                if(!MyFileUtil.checkExampleConstruct(SysConfig.strHodeRealPath
                        + "tmpData/train/student/" + directory)) {
                    pw.write("error2");
                    return;
                }

                String fromPath = SysConfig.strHodeRealPath
                        + "tmpData/train/student/" + directory;
                String returnStr = TrainUtil.updataImageInfo(fromPath);

                if(returnStr.trim().equals("null")) {
                    pw.write("error");
                } else if(returnStr.trim().equals("success")) {
                    pw.write("success");
                } else {
                    pw.write(returnStr);
                }

            } catch (Exception e) {
                pw.write("error");
                e.printStackTrace();
            } finally {
                // 清除临时文件
                MyFileUtil.deleteFile(new File(SysConfig.strHodeRealPath
                        + "tmpData/train/student/" + fname));
                MyFileUtil.deleteDirectory(new File(SysConfig.strHodeRealPath
                        + "tmpData/train/student/" + directory));
            }
        } else {
            // 上传临时文件请求
            FileBiz biz = new FileBiz();
            String uploadPath = getServletContext().getRealPath("/");// 获取文件路径

            try {
                // 三个参数分别为request、上传路径、是否预览
                String[] returnStrs = biz.upload(req, uploadPath, false);
                // System.out.println(returnStrs[0]);
                session.setAttribute("fname", returnStrs[1]);
                pw.write("success");
            } catch (Exception e) {
                e.printStackTrace();
                pw.write("error");
            }
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
