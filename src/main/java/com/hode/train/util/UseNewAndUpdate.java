package com.hode.train.util;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hode.train.model.TrainPersonModel;
import com.hode.train.model.TrainStudentDetailModel;

public class UseNewAndUpdate extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    @SuppressWarnings("unchecked")
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("utf-8");

        HttpSession session = req.getSession();
        List<TrainStudentDetailModel> existStudents = (List<TrainStudentDetailModel>) session
                .getAttribute("existStudents");

        List<TrainPersonModel> existPicPersons = TrainUtil
                .useNewAndUpdate(existStudents);
        session.setAttribute("existPicPersons", existPicPersons);

        String returnStr = "";
        if (existPicPersons != null && existPicPersons.size() > 0) {
            for (TrainPersonModel trainPersonModel : existPicPersons) {
                returnStr += "<tr><td>用户名："
                        + trainPersonModel.getStrPersonName() + "</td>";
                returnStr += "<td>身份证号："
                        + trainPersonModel.getStrPersonID() + "</td></tr>";

                returnStr += "<tr height=300>";

                returnStr += "<td width=300>";
                if (!MyStringUtil.isNullStr(trainPersonModel.getStrPic1())) {
                    returnStr += "<a href='" + trainPersonModel.getStrPic1()
                            + "' target='_blank'><img src='"
                            + trainPersonModel.getStrPic1()
                            + "' width=250 border=0 ></a>";
                } else {
                    returnStr += "无相片照片";
                }
                returnStr += "</td>";

                returnStr += "<td width=300>";
                if (!MyStringUtil.isNullStr(trainPersonModel.getStrPic2())) {
                    returnStr += "<a href='" + trainPersonModel.getStrPic2()
                            + "' target='_blank'><img src='"
                            + trainPersonModel.getStrPic2()
                            + "' width=250 border=0 ></a>";
                } else {
                    returnStr += "无学历照片";
                }
                returnStr += "</td></tr>";

                returnStr += "<tr height=300>";

                returnStr += "<td width=300>";
                if (!MyStringUtil.isNullStr(trainPersonModel.getStrPic3())) {
                    returnStr += "<a href='" + trainPersonModel.getStrPic3()
                            + "' target='_blank'><img src='"
                            + trainPersonModel.getStrPic3()
                            + "' width=250 border=0 ></a>";
                } else {
                    returnStr += "无体检表照片";
                }
                returnStr += "</td>";

                returnStr += "<td width=300>";
                if (!MyStringUtil.isNullStr(trainPersonModel.getStrPic4())) {
                    returnStr += "<a href='" + trainPersonModel.getStrPic4()
                            + "' target='_blank'><img src='"
                            + trainPersonModel.getStrPic4()
                            + "' width=250 border=0 ></a>";
                } else {
                    returnStr += "无相关工作经历证明照片";
                }
                returnStr += "</td></tr>";
            }

            returnStr += "<tr>";
            returnStr += "<td><input type='button' value='导入档案库中已存在的图片信息' onclick='importRecordPic()'></td>";
            returnStr += "<td><input type='button' value='忽略档案库图片信息' onclick='ignoreRecordPic()'></td>";
            returnStr += "</tr>";
        }

        resp.getWriter().write(returnStr);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
