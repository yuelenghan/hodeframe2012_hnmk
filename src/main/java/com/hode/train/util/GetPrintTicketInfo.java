package com.hode.train.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.hode.train.model.TrainStudentModel;

public class GetPrintTicketInfo extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String trainCount = req.getParameter("trainCount");
        String trainCountClass = req.getParameter("trainCountClass");
        String num = req.getParameter("num");

        List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();

        TrainStudentModel trainStudentModel = new TrainStudentModel();
        trainStudentModel.setStrTrainCount(trainCount);
        if(!MyStringUtil.isNullStr(trainCountClass)) {
            trainStudentModel.setStrTrainCountClass(trainCountClass);
        }
        returnList = TrainUtil.getPrintTicketInfo(trainStudentModel, Integer.parseInt(num));

        try {
            JSONObject json = JsonUtil.listToJson(returnList);
            resp.setCharacterEncoding("UTF-8");
            resp.setContentType("application/json");
            resp.getWriter().write(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
