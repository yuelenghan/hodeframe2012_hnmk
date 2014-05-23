package com.hode.train.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hode.framework.commons.config.SysConfig;
import com.hode.train.model.TrainStudentDetailModel;

public class DataFileUpload extends HttpServlet {

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
            // 导入基础信息请求
            String fname = session.getAttribute("fname").toString();
            // System.out.println(fname);

            String trainType2 = req.getParameter("trainType2");

            try {
                String dataArr[][] = CommonUtil.getBodyListByExcelName(
                        "tmpData/train/student/" + fname, 1);

                if (dataArr != null && dataArr.length > 0) {
                    List<Map<String, String>> list = new ArrayList<Map<String, String>>();
                    for (int i = 0; i < dataArr.length; i++) {
                        String strIDCard = dataArr[i][8]; // 身份证号
                        String strIDCardReg = "^(\\d{15}$|^\\d{18}$|^\\d{17}(\\d|X|x))$"; // 身份证号正则表达式
                        Pattern pattern = Pattern.compile(strIDCardReg);
                        Matcher matcher = pattern.matcher(strIDCard);

                        if (!MyStringUtil.isNullStr(strIDCard)
                                && matcher.matches()) {
                            Map<String, String> map = new HashMap<String, String>();

                            // 小写转大写,处理身份证号中带x的
                            strIDCard = strIDCard.toUpperCase();

                            map.put("trainUnit", req.getParameter("trainUnit"));
                            map.put("studentPost", dataArr[i][1]);
                            map.put("studentTitle", dataArr[i][6]);
                            map.put("studentName", dataArr[i][3]);
                            map.put("studentSex", dataArr[i][4]);
                            map.put("strStudentAge", dataArr[i][5]);
                            map.put("studentIDCode", strIDCard);
                            map.put("studentIDAddr", dataArr[i][2]);
                            map.put("studentDegree", dataArr[i][7]);
                            map.put("studentUnitType", dataArr[i][14]);
                            map.put("studentRelation", dataArr[i][13]);
                            map.put("studentCertCode", dataArr[i][9]);
                            map.put("firstDate", dataArr[i][11]);
                            map.put("studentViolate", dataArr[i][12]);
                            map.put("studentPic1", "");
                            map.put("studentPic2", "");
                            map.put("studentPic3", "");
                            map.put("studentPic4", "");

                            map.put("trainType2", trainType2);

                            list.add(map);
                        }
                    }

                    List<TrainStudentDetailModel> existStudents = TrainUtil
                            .signUpBatch(list);
                    session.setAttribute("existStudents", existStudents);

                    String tableStr = "";
                    if (existStudents != null && existStudents.size() > 0) {
                        for (int i = 0; i < existStudents.size(); i++) {
                            TrainStudentDetailModel student = existStudents.get(i);
                            tableStr += "<tr><td>" + (i + 1) + "</td>";
                            tableStr += "<td>"+student.getStrStudentPost()+"</td>";
                            tableStr += "<td>"+student.getStrStudentIDAddr()+"</td>";
                            tableStr += "<td>"+student.getStrStudentName()+"</td>";
                            tableStr += "<td>"+student.getStrStudentSex()+"</td>";
                            tableStr += "<td>"+student.getIntStudentAge()+"</td>";
                            tableStr += "<td>"+student.getStrStudentTitles()+"</td>";
                            tableStr += "<td>"+student.getStrStudentDegree()+"</td>";
                            tableStr += "<td>"+student.getStrStudentIDCode()+"</td>";
                            tableStr += "<td>"+student.getStrStudentCertCode()+"</td>";
                            tableStr += "<td>"+student.getStrStudentHealth()+"</td>";
                            tableStr += "<td>"+student.getStrStudentFirstDate()+"</td>";
                            tableStr += "<td>"+student.getStrStudentViolate()+"</td>";
                            tableStr += "<td>"+student.getStrStudentRelation()+"</td>";
                            tableStr += "<td>"+student.getStrStudentUnitType()+"</td></tr>";
                        }
                    }

                    pw.write(tableStr);
                    MyFileUtil.deleteFile(new File(SysConfig.strHodeRealPath
                            + "tmpData/train/student/" + fname));
                }
            } catch (Exception e) {
                pw.write("error");
                e.printStackTrace();
            }
        } else {
            // 上传临时文件请求
            FileBiz biz = new FileBiz();
            String uploadPath = getServletContext().getRealPath("/");// 获取文件路径

            try {
                // 三个参数分别为request、上传路径、是否预览
                String[] returnStrs = biz.upload(req, uploadPath, true);
                System.out.println(returnStrs[0]);
                session.setAttribute("fname", returnStrs[1]);
                pw.write(returnStrs[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }

}
