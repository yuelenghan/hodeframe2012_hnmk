package com.hode.train.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SignUp extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        resp.setCharacterEncoding("gbk");

        PrintWriter out = resp.getWriter();

        // 网上报名提交信息
        String trainUnit = req.getParameter("trainUnit"); // 培训机构
        String trainType = req.getParameter("trainType"); // 培训类别
        String studentPost = req.getParameter("studentPost"); // 单位及部门
        String studentTitle = req.getParameter("studentTitle"); // 职务
        String studentName = req.getParameter("studentName"); // 姓名
        String studentSex = req.getParameter("studentSex"); // 性别
        String strStudentAge = req.getParameter("studentAge"); // 年龄
        String studentIDCode = req.getParameter("studentIDCode").toUpperCase(); // 身份证号
        String studentIDAddr = req.getParameter("studentIDAddr"); // 身份证地址
        String studentDegree = req.getParameter("studentDegree"); // 文化程度
        String studentUnitType = req.getParameter("studentUnitType"); // 单位类型
        String studentRelation = req.getParameter("studentRelation"); // 联系电话
        String studentCertCode = req.getParameter("studentCertCode"); // 证书号码(再培训填写)
        String firstDate = req.getParameter("firstDate"); // 初次领证时间(再培训填写)
        String studentViolate = req.getParameter("studentViolate"); // 违章作业情况(再培训填写)
        String image1 = req.getParameter("image1_hd");
        String image2 = req.getParameter("image2_hd");
        String image3 = req.getParameter("image3_hd");
        String image4 = req.getParameter("image4_hd");
        String strRand = req.getParameter("strRand"); // 提交的验证码
        String valid = String.valueOf(req.getSession().getAttribute(
                "strSessionRand")); // session中的验证码

        if (MyStringUtil.isNullStr(strRand)) {
            out.write("验证码为空！");
        } else if (!strRand.trim().equals(valid)) {
            out.write("验证码错误！");
        } else {
            Map<String, String> map = new HashMap<String, String>();
            map.put("trainUnit", trainUnit);
            map.put("trainType", trainType);
            map.put("studentPost", studentPost);
            map.put("studentTitle", studentTitle);
            map.put("studentName", studentName);
            map.put("studentSex", studentSex);
            map.put("strStudentAge", strStudentAge);
            map.put("studentIDCode", studentIDCode);
            map.put("studentIDAddr", studentIDAddr);
            map.put("studentDegree", studentDegree);
            map.put("studentUnitType", studentUnitType);
            map.put("studentRelation", studentRelation);
            map.put("studentCertCode", studentCertCode);
            map.put("firstDate", firstDate);
            map.put("studentViolate", studentViolate);
            map.put("studentPic1", image1);
            map.put("studentPic2", image2);
            map.put("studentPic3", image3);
            map.put("studentPic4", image4);

            try {
                String msg = TrainUtil.signUp(map);
                if (msg != null && msg.trim().equals("success")) {
                    out.write("报名成功！");
                } else if (msg.trim().equals("exists")) {
                    out.write("该身份证号的学员信息已存在，请确认身份证号是否正确！");
                } else if (msg.trim().equals("error")) {
                    out.write("报名失败！");
                }
            } catch (Exception e) {
                e.printStackTrace();
                out.write("报名失败！");
            }
        }
        out.write("<input type='button' value='返回' onclick='history.go(-1)'>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
