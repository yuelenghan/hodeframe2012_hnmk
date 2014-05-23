package com.hode.train.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.format.PageOrientation;
import jxl.write.Border;
import jxl.write.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.service.GlobeSpringContext;
import com.hode.train.model.TrainApplyModel;
import com.hode.train.model.TrainCertModel;
import com.hode.train.model.TrainScoreModel;
import com.hode.train.model.TrainStudentDetailScoreVO;
import com.hode.train.service.TrainCertService;

@SuppressWarnings("deprecation")
public class DownloadCertExcel extends HttpServlet {

	private static final long serialVersionUID = -8565891621865920219L;
	private static TrainCertService trainCertService = (TrainCertService) (GlobeSpringContext
            .getApplicationContext().getBean("trainCertIbatisServiceProxy"));

    @SuppressWarnings("rawtypes")
    public static void createExcel(HttpServletRequest request,
                                   String strTrainCount, String strTrainCountClass, String strExcelFile) {
        try {
            TrainApplyModel trainApplyModel = TrainUtil.getTrainApplyByCode(
                    strTrainCount, strTrainCountClass);

            TrainCertModel trainCertModel = new TrainCertModel();
            trainCertModel.setStrTrainCount(strTrainCount);
            if (!MyStringUtil.isNullStr(strTrainCountClass)) {
                trainCertModel.setStrTrainCountClass(strTrainCountClass);
            }

            int trainApplyID = Integer.parseInt(trainCertService.getObjectInfo(
                    "getTrainApplyID", trainCertModel).toString());

            trainCertModel.setIntTrainApplyID(trainApplyID);
            trainCertModel.setIntCertCode_1(1);
            List searchResultList = trainCertService.getObjectList(
                    "getSelectCert", trainCertModel);

            File directory = new File(SysConfig.strHodeRealPath + "/tmp/");
            if (!directory.exists()) {
                directory.mkdirs();
            }

            File file = new File(SysConfig.strHodeRealPath + "/tmp/"
                    + strExcelFile);
            WritableWorkbook wwb = null;
            try {
                wwb = Workbook.createWorkbook(file);
            } catch (Exception e) {
                return;
            }
            WritableSheet ws = wwb.createSheet("制证表", 0);
            ws.setPageSetup(PageOrientation.LANDSCAPE, 0, 0);
            SheetSettings sheetS = ws.getSettings();
            sheetS.setTopMargin(0.4);

            jxl.write.WritableFont wf1 = new jxl.write.WritableFont(
                    WritableFont.createFont("黑体"), 22, WritableFont.BOLD, false);// 22号粗体
            jxl.write.WritableFont wf4 = new jxl.write.WritableFont(
                    WritableFont.createFont("宋体"), 12, WritableFont.BOLD, false);// 12号粗体
            jxl.write.WritableFont wf5 = new jxl.write.WritableFont(
                    WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD,
                    false);// 12号正常
            jxl.write.WritableFont wf6 = new jxl.write.WritableFont(
                    WritableFont.createFont("宋体"), 12, WritableFont.NO_BOLD,
                    false);// 12号正常

            jxl.write.WritableCellFormat wcfF1 = new jxl.write.WritableCellFormat(
                    wf1);
            jxl.write.WritableCellFormat wcfF4 = new jxl.write.WritableCellFormat(
                    wf4);
            jxl.write.WritableCellFormat wcfF5 = new jxl.write.WritableCellFormat(
                    wf5);
            jxl.write.WritableCellFormat wcfF6 = new jxl.write.WritableCellFormat(
                    wf6);
            ws.setRowView(0, 800);
            ws.setRowView(1, 600);
            wcfF1.setAlignment(jxl.format.Alignment.CENTRE);
            wcfF4.setAlignment(jxl.format.Alignment.CENTRE);
            wcfF5.setAlignment(jxl.format.Alignment.CENTRE);
            wcfF6.setAlignment(jxl.format.Alignment.LEFT);

            wcfF4.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcfF5.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);
            wcfF6.setVerticalAlignment(jxl.format.VerticalAlignment.CENTRE);

            wcfF1.setWrap(true);
            wcfF4.setWrap(true);
            wcfF5.setWrap(true);
            wcfF6.setWrap(true);

            // wcfF1.setBorder( Border.ALL, BorderLineStyle.THIN);
            wcfF4.setBorder(Border.ALL, BorderLineStyle.THIN);
            wcfF5.setBorder(Border.ALL, BorderLineStyle.THIN);
            wcfF6.setBorder(Border.ALL, BorderLineStyle.THIN);

            int num = 1; // 表头
            ws.mergeCells(0, 0, 14, 0);
            Label labelHead99 = new Label(0, 0, "制证表", wcfF1);
            ws.addCell(labelHead99);
            Label labelHead0 = new Label(0, 1, "序号", wcfF4);
            Label labelHead1 = new Label(1, 1, "证书编号", wcfF4);
            Label labelHead2 = new Label(2, 1, "姓名", wcfF4);
            Label labelHead3 = new Label(3, 1, "身份证", wcfF4);
            Label labelHead4 = new Label(4, 1, "性别", wcfF4);
            Label labelHead5 = new Label(5, 1, "文化程度", wcfF4);
            Label labelHead6 = new Label(6, 1, "职称", wcfF4);
            Label labelHead7 = new Label(7, 1, "单位及职务", wcfF4);
            Label labelHead8 = new Label(8, 1, "单位类型", wcfF4);
            Label labelHead9 = new Label(9, 1, "培训日期从", wcfF4);
            Label labelHead10 = new Label(10, 1, "培训日期到", wcfF4);
            Label labelHead11 = new Label(11, 1, "发证日期", wcfF4);
            Label labelHead12 = new Label(12, 1, "培训类别", wcfF4);
            Label labelHead13 = new Label(13, 1, "培训项目", wcfF4);
            Label labelHead14 = new Label(14, 1, "培训单位", wcfF4);

            ws.setColumnView(0, 8);
            ws.setColumnView(1, 10);
            ws.setColumnView(2, 10);
            ws.setColumnView(3, 10);
            ws.setColumnView(4, 10);
            ws.setColumnView(5, 10);
            ws.setColumnView(6, 10);
            ws.setColumnView(7, 10);
            ws.setColumnView(8, 10);
            ws.setColumnView(9, 10);
            ws.setColumnView(10, 10);
            ws.setColumnView(11, 10);
            ws.setColumnView(12, 10);
            ws.setColumnView(13, 10);
            ws.setColumnView(14, 10);

            ws.addCell(labelHead0);
            ws.addCell(labelHead1);
            ws.addCell(labelHead2);
            ws.addCell(labelHead3);
            ws.addCell(labelHead4);
            ws.addCell(labelHead5);
            ws.addCell(labelHead6);
            ws.addCell(labelHead7);
            ws.addCell(labelHead8);
            ws.addCell(labelHead9);
            ws.addCell(labelHead10);
            ws.addCell(labelHead11);
            ws.addCell(labelHead12);
            ws.addCell(labelHead13);
            ws.addCell(labelHead14);
            num = 2; // 行数
            for (int i = 0; i < searchResultList.size(); i++) {
                Label labelBody0 = null;
                Label labelBody1 = null;
                Label labelBody2 = null;
                Label labelBody3 = null;
                Label labelBody4 = null;
                Label labelBody5 = null;
                Label labelBody6 = null;
                Label labelBody7 = null;
                Label labelBody8 = null;
                Label labelBody9 = null;
                Label labelBody10 = null;
                Label labelBody11 = null;
                Label labelBody12 = null;
                Label labelBody13 = null;
                Label labelBody14 = null;

                trainCertModel = (TrainCertModel) searchResultList.get(i);

                labelBody0 = new Label(0, num, (i + 1) + "", wcfF6);// 序号
                labelBody1 = new Label(1, num,
                        trainCertModel.getStrCertCode_1(), wcfF6);// 证书编号
                labelBody2 = new Label(2, num,
                        trainCertModel.getStrStudentName_1(), wcfF6);// 姓名
                labelBody3 = new Label(3, num,
                        trainCertModel.getStrStudentIDCode_1(), wcfF6);// 身份证
                labelBody4 = new Label(4, num,
                        trainCertModel.getStrStudentSex_1(), wcfF6);// 性别
                labelBody5 = new Label(5, num,
                        trainCertModel.getStrStudentDegree_1(), wcfF6);// 文化程度
                labelBody6 = new Label(6, num,
                        trainCertModel.getStrStudentTitles_1(), wcfF6);// 职称
                labelBody7 = new Label(7, num,
                        trainCertModel.getStrStudentPost_1(), wcfF6);// 单位及职务
                labelBody8 = new Label(8, num,
                        trainCertModel.getStrStudentUnitType_1(), wcfF6);// 单位类型

                labelBody9 = new Label(9, num,
                        trainApplyModel.getStrTrainStartDate(), wcfF6);// 培训日期从
                labelBody10 = new Label(10, num,
                        trainApplyModel.getStrTrainEndDate(), wcfF6);// 培训日期到

                labelBody11 = new Label(11, num,
                        trainCertModel.getStrCertDate_1(), wcfF6);// 发证日期
                labelBody12 = new Label(12, num,
                        trainApplyModel.getStrTrainType(), wcfF6);// 培训类别
                labelBody13 = new Label(13, num,
                        trainApplyModel.getStrTrainObjectDesc(), wcfF6);// 培训项目，用培训对象来代替
                labelBody14 = new Label(14, num,
                        trainApplyModel.getStrTrainUnitName(), wcfF6);// 培训单位

                ws.addCell(labelBody0);
                ws.addCell(labelBody1);
                ws.addCell(labelBody2);
                ws.addCell(labelBody3);
                ws.addCell(labelBody4);
                ws.addCell(labelBody5);
                ws.addCell(labelBody6);
                ws.addCell(labelBody7);
                ws.addCell(labelBody8);
                ws.addCell(labelBody9);
                ws.addCell(labelBody10);
                ws.addCell(labelBody11);
                ws.addCell(labelBody12);
                ws.addCell(labelBody13);
                ws.addCell(labelBody14);
                num++;
                if (i > 4000)
                    break;
            }// for(int nn=0;nn<intTotalCount;nn++)

            wwb.write();
            wwb.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("....");
        }

    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String strTrainCount = (req.getParameter("strTrainCount"));
        String strTrainCountClass = req.getParameter("strTrainCountClass");
        String exportType = req.getParameter("exportType");

        try {
            strTrainCount = MyStringUtil.changeEncode(strTrainCount);
            strTrainCountClass = MyStringUtil.changeEncode(strTrainCountClass);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String strExcelFile = Calendar.getInstance().getTimeInMillis() + ".xls";
        File file = new File(SysConfig.strHodeRealPath + "/tmp/"
                + strExcelFile);

        if (exportType.trim().equals("zz")) {
            createExcel(req, strTrainCount, strTrainCountClass, strExcelFile);
        } else if (exportType.trim().equals("fz")) {
            try {
                createExcel2(req, strTrainCount, strTrainCountClass,
                        strExcelFile);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        resp.reset();
        resp.setContentType("application/vnd.ms-excel");
        resp.setHeader("content-disposition", "attachment; filename="
                + strExcelFile);

        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        try {
            bis = new BufferedInputStream(new FileInputStream(
                    SysConfig.strHodeRealPath + "tmp/" + strExcelFile));
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
            if(file.exists()) {
                file.delete();
            }
        }
    }

    private void createExcel2(HttpServletRequest req, String strTrainCount,
                              String strTrainCountClass, String strExcelFile) throws Exception {
        TrainApplyModel trainApplyModel = TrainUtil.getTrainApplyByCode(
                strTrainCount, strTrainCountClass);
        TrainScoreModel trainScoreModel = new TrainScoreModel();
        trainScoreModel.setStrTrainCount(strTrainCount);
        if(!MyStringUtil.isNullStr(strTrainCountClass)) {
            trainScoreModel.setStrTrainCountClass(strTrainCountClass);
        }
        trainScoreModel = TrainUtil.getTrainScoreByTrainCount(trainScoreModel);

        // 读取模板文件
        POIFSFileSystem fs = new POIFSFileSystem(new FileInputStream(
                SysConfig.strHodeRealPath + "information/example/style.xls"));
        HSSFWorkbook wb = new HSSFWorkbook(fs);
        HSSFSheet sheet = wb.getSheetAt(0);

        // 第一行
        HSSFRow row_1 = sheet.getRow(0);
        HSSFCell cell_11 = row_1.getCell(0);
        cell_11.setCellValue("安全培训考核发证统计表(" +strTrainCount+ ")");

        // 第二行
        HSSFRow row_2 = sheet.getRow(1);
        HSSFCell cell_21 = row_2.getCell(0);
        cell_21.setCellValue("培训机构(公章):"+trainApplyModel.getStrTrainUnitName() +
                "    培训类别:"+trainApplyModel.getStrTrainObjectDesc()+"培训" +
                "    考核单位或考核点(公章):河南煤矿安全监察局安培中心");

        // 第三行
        HSSFRow row_3 = sheet.getRow(2);
        HSSFCell cell_31 = row_3.getCell(0);
        cell_31.setCellValue("培训时间:" + MyDateUtil.processStrDate(trainApplyModel.getStrTrainStartDate())
                + "至" + MyDateUtil.processStrDate(trainApplyModel.getStrTrainEndDate())
                + "    考核日期:"+MyDateUtil.processStrDate(trainScoreModel.getStrScoreDate())
                + "    监考人:      "
                + "    试卷编号:      ");

        // 创建排版样式
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
        style.setBorderRight(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);

        // 创建字体样式
        HSSFFont font = wb.createFont();
        font.setFontName("宋体");
        font.setFontHeightInPoints((short) 10);

        style.setFont(font);

        // 得到报表的结果集
        List<TrainStudentDetailScoreVO> list = new ArrayList<TrainStudentDetailScoreVO>();
        list = TrainUtil.getStudentDetailScoreByTrainApplyID(trainApplyModel.getIntID());
        if (list != null && list.size() > 0) {
            for(int i=0; i<list.size(); i++) {
                TrainStudentDetailScoreVO vo = list.get(i);
                // 从第7行开始
                HSSFRow row = sheet.createRow(i + 6);
                float score1 = vo.getFltExamScore1();
                float score2 = vo.getFltExamScore2();
                if(score1 == 0 && score2 == 0) {
                    // 结果集中的内容都有证书，所以正考成绩都为0，为补考
                    // 不存在没有成绩但是有证书的人
                    float score3 = vo.getFltExamScore3();
                    float score4 = vo.getFltExamScore4();
                    if(score3 == 0 && score4 == 0) {
                        continue;
                    } else {
                        row.createCell(8).setCellValue(score3);
                        row.createCell(9).setCellValue(score4);
                    }
                } else {
                    row.createCell(8).setCellValue(score1);
                    row.createCell(9).setCellValue(score2);
                }

                row.createCell(0).setCellValue(i+1);
                row.createCell(1).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentPost()));
                row.createCell(2).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentIDAddr()));
                row.createCell(3).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentName()));
                row.createCell(4).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentSex()));
                row.createCell(5).setCellValue(vo.getIntStudentAge());
                row.createCell(6).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentTitles()));
                row.createCell(7).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentDegree()));

                row.createCell(10).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentIDCode()));
                row.createCell(11).setCellValue(MyStringUtil.processNullStr(vo.getStrCertCode()));
                row.createCell(12).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentHealth()));
                row.createCell(13).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentFirstDate()));
                row.createCell(14).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentViolate()));
                row.createCell(15).setCellValue(MyStringUtil.processNullStr(vo.getStrStudentRelation()));

                for(int j=0; j<row.getLastCellNum(); j++) {
                    row.getCell(j).setCellStyle(style);
                }
            }
        }

        File directory = new File(SysConfig.strHodeRealPath + "/tmp/");
        if (!directory.exists()) {
            directory.mkdirs();
        }

        File file = new File(SysConfig.strHodeRealPath + "/tmp/"
                + strExcelFile);

        OutputStream out = new FileOutputStream(file);
        wb.write(out);
        out.flush();
        out.close();
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doGet(req, resp);
    }
}
