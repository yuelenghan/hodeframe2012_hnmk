package com.hode.train.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageUpload extends HttpServlet {

    private static final long serialVersionUID = 5603392562213223189L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        FileBiz biz = new FileBiz();
        String uploadPath = ConstantUtil.TRAIN_PIC_ROOT_PATH + "0/";// 获取文件路径
        resp.setCharacterEncoding("utf-8");
        String idCard = req.getParameter("idCard").toUpperCase();
        String imgId = req.getParameter("imgId");
        try {
            biz.uploads(req, uploadPath, idCard, imgId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
