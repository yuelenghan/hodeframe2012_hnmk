package com.hode.train.util;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ProcessSignUp extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");

        String ids = req.getParameter("ids");
        String trainApplyIDs = req.getParameter("trainApplyIDs");

        if(!MyStringUtil.isNullStr(ids) && !MyStringUtil.isNullStr(trainApplyIDs)) {
            String culUserId = req.getParameter("culUser");

            String[] id = ids.split(",");
            String[] trainApplyID = trainApplyIDs.split(",");

            String filePath = ConstantUtil.TRAIN_PIC_ROOT_PATH;
            for (int i = 0; i < id.length; i++) {
                try {
                    TrainUtil.processSignUp(Integer.parseInt(id[i]), Integer.parseInt(trainApplyID[i]), Integer.parseInt(culUserId),filePath);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
