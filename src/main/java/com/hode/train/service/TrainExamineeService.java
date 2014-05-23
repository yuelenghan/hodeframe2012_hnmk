/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;
import java.util.ArrayList;
import java.util.List;

import com.hode.framework.service.IbatisService;
import com.hode.train.model.ApplyExamineeModel;
import com.hode.train.model.ExamineePapersModel;
import com.hode.train.model.TrainExamineeModel;
import com.hode.train.model.TrainExamineeSearch;
import com.hode.train.model.TrainPapersModel;
import com.hode.train.util.CommonUtil;
import com.hode.train.util.TrainUtil;

public class TrainExamineeService extends IbatisService
{


    /**
     * 添加考生信息
     * @param trainExamineeModel
     */
    public void insertExaminee(TrainExamineeModel trainExamineeModel){
        this.insertObjectAndGetID(trainExamineeModel);
    }

    /**
     * 查询考生信息
     * @return 考生列表
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public List<TrainExamineeSearch> getExamineeList(int intTrainApplyID){
        List results = new ArrayList();
        TrainExamineeModel examinee = new TrainExamineeModel();
        examinee.setIntTrainApplyID(intTrainApplyID);
        results = this.getPageObjectList(examinee, 0, 10);
        List<TrainExamineeSearch> searchList = new ArrayList();
        List<TrainPapersModel> paperlist = new ArrayList();
        for(int i=0;i<results.size();i++){
            TrainExamineeModel bean = new TrainExamineeModel();
            TrainPapersModel papers = new TrainPapersModel();
            TrainExamineeSearch search = new TrainExamineeSearch();
            bean = (TrainExamineeModel)results.get(i);
            papers.setIntTrainApplyID(intTrainApplyID);
            papers.setStrIdentity(bean.getStrIdentity());
            paperlist = TrainUtil.getTrainPapers(papers);
            if(paperlist!=null&&paperlist.size()>0&&paperlist.get(0)!=null){
                search.setStrClasses(paperlist.get(0).getStrClasses());
                search.setStrTrainUnit(paperlist.get(0).getStrTrainUnit());
                search.setIsMakeUp(paperlist.get(0).getIsMakeUp());
                search.setStrExamType(paperlist.get(0).getStrExamType());
            }else{
                search.setStrClasses("");
                search.setStrTrainUnit("");
                search.setIsMakeUp("");
                search.setStrExamType("");
            }
            search.setIntID(bean.getIntID());
            search.setIntTrainApplyID(bean.getIntTrainApplyID());
            search.setStrExamineeName(bean.getStrExamineeName());
            search.setStrExamineeSex(bean.getStrExamineeSex());
            search.setStrIdentity(bean.getStrIdentity());
            search.setStrExamineeUnit(bean.getStrExamineeUnit());
            search.setStrJob(bean.getStrJob());

            searchList.add(search);
        }
        return searchList;
    }

    /**
     * 根据ID查出该考生信息
     * @param intID
     * @return
     */
    public TrainExamineeModel getExamineeID(int intID){
        TrainExamineeModel bean = new TrainExamineeModel();
        bean =  (TrainExamineeModel)this.getObjectInfo("getObjectInfo",intID);
        return bean;
    }

    public void updateExaminee(TrainExamineeModel model){
        this.updateObject(model);
    }

    @SuppressWarnings({ "unchecked", "rawtypes" })
    public void importExaminee(){

        String dataArr[][] = CommonUtil.getBodyListByExcelName("index.xls",1);
        for(int i=0;i<dataArr.length;i++)
        {
            String value = dataArr[i][1];
            String value1 = dataArr[i][2];
            System.out.println(value+"================"+value1);

        	/* 根据身份证找人 1.人不存在，需要新增并且获得该人的id 2.人存在，得到人的id */
            if(dataArr[i][2]!=null&&dataArr[i][2].toString().trim().length()>0){

                List<TrainExamineeModel> examineelist = new ArrayList();
                ApplyExamineeModel aemodel = new ApplyExamineeModel();
                TrainExamineeModel temodel = new TrainExamineeModel();

                examineelist = TrainUtil.checkIdentity(dataArr[i][2].toString().trim());
                if(examineelist.size()==0){
                    temodel.setStrExamineeName(dataArr[i][0]);//姓名
                    temodel.setStrExamineeSex(dataArr[i][1]);//性别
                    temodel.setStrIdentity(dataArr[i][2]);//身份证号
                    temodel.setStrHealth(dataArr[i][3]);//身体状况
                    temodel.setStrEducation(dataArr[i][4]);//学历
                    temodel.setStrMajor(dataArr[i][5]);//专业
                    temodel.setStrRelation(dataArr[i][6]);//联系方式
                    temodel.setStrExamineeUnit(dataArr[i][7]);//工作单位
                    temodel.setStrUnitType(dataArr[i][8]);//单位类别
                    temodel.setStrUnitAddr(dataArr[i][9]);//单位地址
                    temodel.setStrDepartment(dataArr[i][10]);//任职部门
                    temodel.setStrJob(dataArr[i][11]);//岗位/职务
                    temodel.setStrRecord(dataArr[i][12]);//违章记录

                    //TrainUtil.insertExaminee(temodel);
                }else if(examineelist.size()==1){
                    temodel.setIntID(examineelist.get(0).getIntID());
                    temodel.setStrHealth(dataArr[i][3]);
                    temodel.setStrRelation(dataArr[i][6]);

                    //TrainUtil.updateExamineePart(temodel);
                }

                ExamineePapersModel epmodel = new ExamineePapersModel();
                epmodel.setIntExamineeID(temodel.getIntID());
                List list = TrainUtil.getExamineePapersList(epmodel);
//        		System.out.println("list:"+list.size());
                if(list.size()==0){
                    TrainPapersModel tpmodel =  new TrainPapersModel();
                    tpmodel.setStrQualificationType(dataArr[i][13]);
                    tpmodel.setStrWorkType(dataArr[i][14]);
                    tpmodel.setStrOperation(dataArr[i][15]);
                    tpmodel.setStrWorkAge(dataArr[i][16]);
                    tpmodel.setStrTrainUnit(dataArr[i][17]);
                    tpmodel.setStrClasses(dataArr[i][18]);
                    tpmodel.setStrExamType(dataArr[i][19]);
                    tpmodel.setStrAdmissionNo(dataArr[i][21]);
                    tpmodel.setDbTheoryMark(Double.parseDouble(dataArr[i][22]));
                    tpmodel.setDbOperMark(dataArr[i][23]);
                    //tpmodel.setStr(dataArr[i][24]);
                    tpmodel.setStrFirstDate(dataArr[i][25]);
                    tpmodel.setStrOpenDate(dataArr[i][26]);
                    tpmodel.setStrOpenHouse(dataArr[i][27]);
                    tpmodel.setStrLimitStart(dataArr[i][28]);
                    tpmodel.setStrLimitEnd(dataArr[i][29]);
                    tpmodel.setStrPapersID(dataArr[i][30]);
                    tpmodel.setStrReCheckID(dataArr[i][31]);
                    tpmodel.setStrDelayID(dataArr[i][32]);
                    tpmodel.setStrOneTrain(dataArr[i][33]);
                    tpmodel.setStrSecondTrain(dataArr[i][34]);
                    tpmodel.setStrCheckRecord1(dataArr[i][35]);
                    tpmodel.setStrCheckRecord2(dataArr[i][36]);
                    tpmodel.setIsRelation(dataArr[i][37]);
                    tpmodel.setStrEmploymentType(dataArr[i][38]);
                    tpmodel.setStrHandleNo(dataArr[i][39]);
                    tpmodel.setStrRecordNo(dataArr[i][40]);
                    tpmodel.setStrRemarks1(dataArr[i][41]);
                    tpmodel.setStrRemarks2(dataArr[i][42]);

                    //TrainUtil.insertPapers(tpmodel);
                    aemodel.setIntExamineeID(temodel.getIntID());
                    aemodel.setIntTrainApplyID(tpmodel.getIntID());
                    aemodel.setIsMakeUp(dataArr[i][20]);
                    TrainUtil.insertApplyExaminee(aemodel);
//        			tpmodel.set
                }else if(list.size()==1){

                }
            }
        }

    }

}