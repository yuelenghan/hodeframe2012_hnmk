package com.hode.train.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.hode.train.model.TrainUnitModel;

public class GetAllTrainUnit extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        @SuppressWarnings("rawtypes")
        List list = TrainUtil.getAllTrainUnit();

        List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();

        for (int i = 0; i < list.size(); i++) {
            TrainUnitModel trainUnitModel = (TrainUnitModel) list.get(i);

            Map<String, String> map = new HashMap<String, String>();
            map.put("unitID", trainUnitModel.getIntID()+"");
            map.put("unitName", trainUnitModel.getStrName());

            returnList.add(map);
        }

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
