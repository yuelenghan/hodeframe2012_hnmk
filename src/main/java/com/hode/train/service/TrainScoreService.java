/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;

import java.io.File;

import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.file.FileWrite;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainScoreDetailModel;
import com.hode.train.model.TrainScoreModel;
import com.hode.train.util.CommonUtil;
import com.hode.train.util.MyFileUtil;
import com.hode.train.util.MyStringUtil;

public class TrainScoreService extends IbatisService {

    public void addImportFinish(TrainScoreModel trainScoreModel,
                                String strScoreFile) {

        String dataArr[][] = CommonUtil.getBodyListByExcelName(strScoreFile, 1);
        int intCount = 0;
        int intScoreUseNum1 = 0;
        String strScoreExam = trainScoreModel.getStrScoreExam();// 考试场次
        String strScoreTrainCount = trainScoreModel.getStrScoreTrainCount();// 对应的培训期次
        String strScoreTrainCountClass = trainScoreModel
                .getStrTrainCountClass(); // 班级
        String strScoreCount = trainScoreModel.getStrScoreCount(); // 考试期次
        if (MyStringUtil.isNullStr(strScoreTrainCountClass)) {
            strScoreTrainCountClass = "";
        }
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < dataArr.length; i++) {
            String strIDCard = dataArr[i][2];
            TrainScoreDetailModel trainScoreDetailModel = new TrainScoreDetailModel();
            trainScoreDetailModel.setIntMainID(trainScoreModel.getIntID());
            // String strScoreCount = dataArr[i][7];//考试期次
            // String strTrainCount = dataArr[i][5]; //培训期次
            // String strTrainCountClass = dataArr[i][6]; //班级
            // strTrainCountClass = strTrainCountClass.trim();

            if (strIDCard != null && strIDCard.length() > 12) {
                trainScoreDetailModel.setStrStudentName(dataArr[i][1]);// 考生姓名
                trainScoreDetailModel.setStrStudentIDCode(strIDCard
                        .toUpperCase());// 考生身份证号码
                trainScoreDetailModel.setStrTrainCount(strScoreTrainCount);// 培训期次

                trainScoreDetailModel.setStrScoreCount(strScoreCount);
                trainScoreDetailModel.setStrScoreExam(strScoreExam); // 考试场次
                trainScoreDetailModel.setStrScoreTrainCount(strScoreTrainCount); // 对应的培训期次
                trainScoreDetailModel.setFltExamScore1(StringUtil
                        .ObjectToFloat(dataArr[i][3]));// 理论成绩
                trainScoreDetailModel.setFltExamScore2(StringUtil
                        .ObjectToFloat(dataArr[i][4]));// 实操成绩
                trainScoreDetailModel.setFltTopScore(StringUtil
                        .ObjectToFloat(dataArr[i][3]));// 理论成绩--最好成绩
                trainScoreDetailModel.setStrExamRule(dataArr[i][6]);// 是否违纪
                trainScoreDetailModel.setStrRemark(dataArr[i][7]);// 备注
                trainScoreDetailModel.setIntIsPass(0);
                if (dataArr[i][5] != null
                        && (dataArr[i][5].equals("是")
                        || dataArr[i][5].equals("补") || dataArr[i][5]
                        .equals("补考"))) {
                    trainScoreDetailModel.setIntIsTmpExam(2);
                    intScoreUseNum1++;
                } else {
                    trainScoreDetailModel.setIntIsTmpExam(1);
                }
                if (dataArr[i][6] != null && dataArr[i][6].length() > 1) // 只要是违纪，成绩都是0分
                {
                    trainScoreDetailModel.setFltExamScore1(0);// 理论成绩
                    trainScoreDetailModel.setFltExamScore2(0);// 实操成绩
                    trainScoreDetailModel.setFltTopScore(0);// 理论成绩--最好成绩
                }
                intCount++;
                if (this.getRecordCount("checkExamObject",
                        trainScoreDetailModel) > 0) {
                    this.insertObject("updateExcelObject",
                            trainScoreDetailModel);
                } else {
                    this.insertObject("insertExcelObject",
                            trainScoreDetailModel);
                }

            }
        }
        trainScoreModel.setIntScoreUseNum1(intScoreUseNum1); // 补考人数
        trainScoreModel.setIntScoreUseNum(intCount); // 考试人数
        trainScoreModel.setStrTrainCount(strScoreTrainCount);
        this.updateObject("updateCount", trainScoreModel);
        FileWrite fw = new FileWrite();
        fw.WriteFile(SysConfig.strHodeRealPath + "tmpData/train/score/",
                trainScoreModel.getIntID() + ".txt", strBuffer.toString());
        // FileUtil.clearTmpDir(SysConfig.strHodeRealPath+"tmpData/train/score/");
        MyFileUtil.clearDirectory(new File(SysConfig.strHodeRealPath
                + "tmpData/train/score/"));
    }

    public void addImportFinish2(TrainScoreModel trainScoreModel,
                                 String strScoreFile) {
        String dataArr[][] = CommonUtil.getBodyListByExcelName(strScoreFile, 1);
        int intCount = 0;
        int intScoreUseNum1 = 0;
        String strScoreExam = trainScoreModel.getStrScoreExam();// 考试场次
        String strScoreTrainCount = trainScoreModel.getStrTrainCount();// 对应的培训期次
        String strScoreTrainCountClass = trainScoreModel
                .getStrTrainCountClass(); // 班级
        String strScoreCount = trainScoreModel.getStrScoreCount(); // 考试期次
        if (MyStringUtil.isNullStr(strScoreTrainCountClass)) {
            strScoreTrainCountClass = "";
        }
        StringBuffer strBuffer = new StringBuffer();
        for (int i = 0; i < dataArr.length; i++) {
            String strIDCard = dataArr[i][2];
            TrainScoreDetailModel trainScoreDetailModel = new TrainScoreDetailModel();
            trainScoreDetailModel.setIntMainID(trainScoreModel.getIntID());
            // String strScoreCount = dataArr[i][7];//考试期次
            // String strTrainCount = dataArr[i][5]; //培训期次
            // String strTrainCountClass = dataArr[i][6]; //班级
            // strTrainCountClass = strTrainCountClass.trim();

            if (strIDCard != null && strIDCard.length() > 12) {
                trainScoreDetailModel.setStrStudentName(dataArr[i][1]);// 考生姓名
                trainScoreDetailModel.setStrStudentIDCode(strIDCard
                        .toUpperCase());// 考生身份证号码
                trainScoreDetailModel.setStrTrainCount(strScoreTrainCount);// 培训期次

                trainScoreDetailModel.setStrScoreCount(strScoreCount);
                trainScoreDetailModel.setStrScoreExam(strScoreExam); // 考试场次
                trainScoreDetailModel.setStrScoreTrainCount(strScoreTrainCount); // 对应的培训期次
                trainScoreDetailModel.setFltExamScore1(StringUtil
                        .ObjectToFloat(dataArr[i][3]));// 理论成绩
                trainScoreDetailModel.setFltExamScore2(StringUtil
                        .ObjectToFloat(dataArr[i][4]));// 实操成绩
                trainScoreDetailModel.setFltTopScore(StringUtil
                        .ObjectToFloat(dataArr[i][3]));// 理论成绩--最好成绩
                trainScoreDetailModel.setStrExamRule(dataArr[i][6]);// 是否违纪
                trainScoreDetailModel.setStrRemark(dataArr[i][7]);// 备注
                trainScoreDetailModel.setIntIsPass(0);
                if (dataArr[i][5] != null
                        && (dataArr[i][5].equals("是")
                        || dataArr[i][5].equals("补") || dataArr[i][5]
                        .equals("补考"))) {
                    trainScoreDetailModel.setIntIsTmpExam(2);
                    intScoreUseNum1++;
                } else {
                    trainScoreDetailModel.setIntIsTmpExam(1);
                }
                if (dataArr[i][6] != null && dataArr[i][6].length() > 1) // 只要是违纪，成绩都是0分
                {
                    trainScoreDetailModel.setFltExamScore1(0);// 理论成绩
                    trainScoreDetailModel.setFltExamScore2(0);// 实操成绩
                    trainScoreDetailModel.setFltTopScore(0);// 理论成绩--最好成绩
                }
                intCount++;
                if (this.getRecordCount("checkExamObject",
                        trainScoreDetailModel) > 0) {
                    this.insertObject("updateExcelObject",
                            trainScoreDetailModel);
                } else {
                    this.insertObject("insertExcelObject",
                            trainScoreDetailModel);
                }

            }
        }

        trainScoreModel.setIntScoreUseNum1(trainScoreModel.getIntScoreUseNum1()
                + intScoreUseNum1); // 补考人数
        trainScoreModel.setIntScoreUseNum(trainScoreModel.getIntScoreUseNum() + intCount); // 考试人数
        trainScoreModel.setStrTrainCount(strScoreTrainCount);
        this.updateObject("updateCount", trainScoreModel);
        FileWrite fw = new FileWrite();
        fw.WriteFile(SysConfig.strHodeRealPath + "tmpData/train/score/",
                trainScoreModel.getIntID() + ".txt", strBuffer.toString());
        // FileUtil.clearTmpDir(SysConfig.strHodeRealPath+"tmpData/train/score/");
        MyFileUtil.clearDirectory(new File(SysConfig.strHodeRealPath
                + "tmpData/train/score/"));
    }
}