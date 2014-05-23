package com.hode.train.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hode.train.model.TrainPersonModel;

public class ImportRecordPic extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("gbk");

        HttpSession session = req.getSession();
        List<TrainPersonModel> existPicPersons = (List<TrainPersonModel>) session
                .getAttribute("existPicPersons");
        PrintWriter pw = resp.getWriter();
        try {
            TrainUtil.importRecordPic(existPicPersons);
            pw.write("success");
        } catch (Exception e) {
            e.printStackTrace();
            pw.write("error");
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
