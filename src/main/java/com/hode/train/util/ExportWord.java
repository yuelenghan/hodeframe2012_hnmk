package com.hode.train.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xwpf.usermodel.ParagraphAlignment;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import com.hode.framework.commons.config.SysConfig;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.model.TrainStudentModel;
import com.hode.train.model.TrainUserApplyDetailModel;
import com.hode.train.model.TrainUserApplyModel;
import com.hode.train.model.TrainUserChangeDetailModel;
import com.hode.train.model.TrainUserChangeModel;

public class ExportWord extends HttpServlet {

    private static final long serialVersionUID = 3602495190621430713L;

    public static void createUserApplyWord(String strCode, String strApplyDate,
                                           String strWordFile){
        TrainUserApplyModel trainUserApplyModel = TrainUtil
                .getTrainUserApplyByCode(strCode);

        @SuppressWarnings("rawtypes")
        List list = TrainUtil
                .getTrainUserApplyDetailByIntMainID(trainUserApplyModel
                        .getIntID());

        File directory = new File(SysConfig.strHodeRealPath + "/tmp/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(SysConfig.strHodeRealPath + "/tmp/" + strWordFile);

        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun r1 = p1.createRun();
        r1.addBreak();
        r1.setBold(true);
        r1.setText("审查情况说明");
        r1.setBold(true);
        r1.setFontFamily("宋体");
        r1.setFontSize(18);

        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun r2 = p2.createRun();
        r2.setText(strCode);
        r2.setFontFamily("Time New Roman");
        r2.setFontSize(14);

        XWPFParagraph p3 = doc.createParagraph();
        p3.setAlignment(ParagraphAlignment.LEFT);

        p3.setIndentationFirstLine(600);

        XWPFRun r3 = p3.createRun();
        r3.setText("由于下面人员安全资格证书丢失，"
                + "根据省局《关于印发〈河南煤矿安全监察局煤矿安全培训资格证书管理办法（试行）〉的通知》"
                + "（豫煤安人[2005]174号）文件、"
                + "《关于进一步明确安全生产培训行政许可有关问题的通知》（豫安监管人[2006]93号）文件的规定，"
                + "经煤矿培训中心审查，情况属实，予以补发。");
        r3.setFontFamily("宋体");
        r3.setFontSize(14);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TrainUserApplyDetailModel trainUserApplyDetailModel = (TrainUserApplyDetailModel) list
                        .get(i);

                String userName = trainUserApplyDetailModel.getStrUserName(); // 姓名
                String userPost = trainUserApplyDetailModel.getStrUserPost(); // 职务
                String userCode = trainUserApplyDetailModel.getStrUserCode(); // 身份证号
                String certCode = trainUserApplyDetailModel.getStrCertCode(); // 证书号
                String userUnitName = trainUserApplyDetailModel
                        .getStrUserUnitName(); // 工作单位

                // 根据身份证号得到学员详细
                TrainStudentDetailModel trainStudentDetailModel = TrainUtil
                        .getTrainStudentDetailByUserCode(userCode);

                XWPFParagraph p = doc.createParagraph();
                p.setAlignment(ParagraphAlignment.LEFT);

                p.setIndentationFirstLine(600);

                XWPFRun r = p.createRun();

                String text = "";

                // 学员的详细信息必须能和补证人员信息对应得上
                if (trainStudentDetailModel != null) {
                    TrainStudentModel trainStudentModel = TrainUtil
                            .getTrainStudentByIntMainID(trainStudentDetailModel
                                    .getIntMainID());

                    String trainStartDate = trainStudentModel
                            .getStrTrainStartDate(); // 培训开始时间
                    String trainEndDate = trainStudentModel
                            .getStrTrainEndDate(); // 培训结束时间

                    // 日期格式处理
                    String startDates[] = trainStartDate.split("-");
                    String endDates[] = trainEndDate.split("-");
                    String processStartDate = "";
                    String processEndDate = "";
                    for (int j = 0; j < startDates.length; j++) {
                        if (j == 0) {
                            processStartDate += startDates[j] + "年";
                        }
                        if (j == 1) {
                            int month = Integer.parseInt(startDates[j]);
                            processStartDate += month + "月";
                        }
                        if (j == 2) {
                            processStartDate += startDates[j] + "日";
                        }
                    }
                    for (int j = 0; j < endDates.length; j++) {
                        if (j == 0) {
                            processEndDate += endDates[j] + "年";
                        }
                        if (j == 1) {
                            int month = Integer.parseInt(endDates[j]);
                            processEndDate += month + "月";
                        }
                        if (j == 2) {
                            processEndDate += endDates[j] + "日";
                        }
                    }

                    String trainUnitName = trainStudentModel
                            .getStrTrainUnitName(); // 培训机构

                    text = userPost + userName + "于" + processStartDate + "～"
                            + processEndDate + "在" + trainUnitName + "培训，"
                            + "成绩合格，证书已办理，原证书编号" + certCode
                            + "，因故丢失，现补办。现工作单位为" + userUnitName + "，职务为"
                            + userPost + "。";

                } else {
                    text = "补证人员信息和学员信息没有对应，请检查身份证号是否输入正确！！";
                }

                r.setText(text);
                r.setFontFamily("宋体");
                r.setFontSize(14);
            }
        }

        XWPFParagraph p4 = doc.createParagraph();
        p4.setAlignment(ParagraphAlignment.RIGHT);

        XWPFRun r4 = p4.createRun();
        r4.addBreak();
        r4.addBreak();
        r4.setText(strApplyDate);
        r4.setFontFamily("Time New Roman");
        r4.setFontSize(14);

        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            doc.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createUserChangeWord(String strCode, String strApplyDate,
                                      String strWordFile) {
        TrainUserChangeModel trainUserChangeModel = TrainUtil
                .getTrainUserChangeByCode(strCode);

        @SuppressWarnings("rawtypes")
        List list = TrainUtil
                .getTrainUserChangeDetailByIntMainID(trainUserChangeModel
                        .getIntID());

        File directory = new File(SysConfig.strHodeRealPath + "/tmp/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(SysConfig.strHodeRealPath + "/tmp/" + strWordFile);

        XWPFDocument doc = new XWPFDocument();

        XWPFParagraph p1 = doc.createParagraph();
        p1.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun r1 = p1.createRun();
        r1.addBreak();
        r1.setBold(true);
        r1.setText("审查情况说明");
        r1.setBold(true);
        r1.setFontFamily("宋体");
        r1.setFontSize(18);

        XWPFParagraph p2 = doc.createParagraph();
        p2.setAlignment(ParagraphAlignment.CENTER);

        XWPFRun r2 = p2.createRun();
        r2.setText(strCode);
        r2.setFontFamily("Time New Roman");
        r2.setFontSize(14);

        XWPFParagraph p3 = doc.createParagraph();
        p3.setAlignment(ParagraphAlignment.LEFT);

        p3.setIndentationFirstLine(600);

        XWPFRun r3 = p3.createRun();
        r3.setText("因单位名称和职务变动，下面人员安全资格证矿名与采矿许可证不符，"
                + "根据省煤监局《关于对安全资格证与采矿许可证矿名不符如何处理问题的批复》豫煤安人[2006]101号文的规定，"
                + "经煤矿培训中心审查，情况属实。变更内容如下：");

        r3.setFontFamily("宋体");
        r3.setFontSize(14);

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TrainUserChangeDetailModel trainUserChangeDetailModel = (TrainUserChangeDetailModel) list
                        .get(i);

                String userName = trainUserChangeDetailModel.getStrUserName(); // 姓名
                String fromTitle = trainUserChangeDetailModel
                        .getStrFromTitles(); // 原职务
                String toTitle = trainUserChangeDetailModel.getStrToTitles(); // 现职务
                String fromUnit = trainUserChangeDetailModel.getStrFromUnit(); // 原单位
                String toUnit = trainUserChangeDetailModel.getStrToUnit(); // 现单位

                XWPFParagraph p = doc.createParagraph();
                p.setAlignment(ParagraphAlignment.LEFT);

                p.setIndentationFirstLine(600);

                XWPFRun r = p.createRun();
                String text = toTitle + userName + "安全资格证书在备注页变更";
                if (!fromTitle.trim().equals(toTitle)
                        && !fromUnit.trim().equals(toUnit)) {
                    // 变更职务和单位
                    text += "职务和单位名称，职务由" + fromTitle + "变更为" + toTitle
                            + "；单位名称由" + fromUnit + "变更为" + toUnit + "。";
                } else if (!fromTitle.trim().equals(toTitle)
                        && fromUnit.trim().equals(toUnit)) {
                    // 变更职务
                    text += "职务，职务由" + fromTitle + "变更为" + toTitle + "。";
                } else if (fromTitle.trim().equals(toTitle)
                        && !fromUnit.trim().equals(toUnit)) {
                    // 变更单位
                    text += "单位名称，单位名称由" + fromUnit + "变更为" + toUnit + "。";
                }

                r.setText(text);
                r.setFontFamily("宋体");
                r.setFontSize(14);
            }
        }

        XWPFParagraph p4 = doc.createParagraph();
        p4.setAlignment(ParagraphAlignment.RIGHT);

        XWPFRun r4 = p4.createRun();
        r4.addBreak();
        r4.addBreak();
        r4.setText(strApplyDate);
        r4.setFontFamily("Time New Roman");
        r4.setFontSize(14);

        FileOutputStream out;
        try {
            out = new FileOutputStream(file);
            doc.write(out);
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String type = req.getParameter("type"); // 类型 1.人员补证word 2.人员变更word
        String strCode = req.getParameter("strCode");
        try {
            strCode = MyStringUtil.changeEncode(strCode);
        } catch (Exception e1) {
            e1.printStackTrace();
            System.err.println("字符转换错误！");
        }
        String strApplyDate = req.getParameter("strApplyDate");
        String strWordFile = strCode + ".docx";

        if (type.equals("1")) {
            try {
                createUserApplyWord(strCode, strApplyDate, strWordFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (type.equals("2")) {
            createUserChangeWord(strCode, strApplyDate, strWordFile);
        }

        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("content-disposition", "attachment; filename="
                + strWordFile);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(
                    SysConfig.strHodeRealPath + "tmp/" + strWordFile));
            bos = new BufferedOutputStream(resp.getOutputStream());
            byte[] buff = new byte[2048];
            int bytesread;
            while (-1 != (bytesread = bis.read(buff, 0, buff.length))) {
                bos.write(buff, 0, bytesread);
            }
        } catch (final IOException e) {
            System.out.println("出现ioexception." + e);
        } finally {
            if (bis != null)
                bis.close();
            if (bos != null)
                bos.close();
        }
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
