/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.model.IbatisModel;
import com.hode.train.model.TrainApplyModel;
import com.hode.train.model.TrainCertDetailModel;
import com.hode.train.model.TrainCertModel;
import com.hode.train.model.TrainCheckDetailModel;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.util.MyDateUtil;
import com.hode.train.util.MyStringUtil;
import com.hode.train.util.QRCodeUtil;
import com.hode.train.util.TrainUtil;
import com.opensymphony.webwork.ServletActionContext;

public class TrainCertAction extends AbstractBaseAction {

    private static final long serialVersionUID = -929385795553302845L;
    private int studentIntID;
    private TrainCertDetailModel trainCertDetailModel;

    protected void createObjInfo() {
        objInfo = new TrainCertModel();
    }

    protected void createObjSearch() {
        objSearch = new TrainCertModel();
    }

    // /////////////////有成绩待受理
    public String showList1() {
        ((TrainCertModel) objSearch).setStrIsNewVal("1");
        return super.showPageList();
    }

    public String optForm1() {
        objInfo = (IbatisModel) ibatisService.getObjectInfo(getObjInfo());
        return SUCCESS;
    }

    public String optData1() {
        ibatisService.updateObject("optData1", getObjInfo());
        return SUCCESS;
    }

    // /////////////////有成绩待受理
    public String showList2() {
        ((TrainCertModel) objSearch).setStrIsNewVal("2,3,4");
        return super.showPageList();
    }

    public String optForm2() {

        objInfo = (IbatisModel) ibatisService.getObjectInfo(getObjInfo());
        ((TrainCertModel) objSearch)
                .setStrTrainCount(((TrainCertModel) objInfo).getStrTrainCount());
        ((TrainCertModel) objSearch)
                .setIntTrainApplyID(((TrainCertModel) objInfo).getIntTrainApplyID());
        int intIsImport_1 = ((TrainCertModel) objSearch).getIntIsImport_1();
        int intIsImport1_1 = ((TrainCertModel) objSearch).getIntIsImport1_1();

        String strToDay = DateUtil.getNowDateByFormat("yyyy-MM-dd");
        if (intIsImport_1 > 0) {
            String strTrainStartDate = DateUtil.getAfterDateByStringAndFormat(
                    strToDay, "yyyy-MM-dd", "year", -intIsImport_1);
            ((TrainCertModel) objSearch)
                    .setStrTrainStartDate(strTrainStartDate);
        }
        if (intIsImport1_1 > 0) {
            String strTrainEndDate = DateUtil.getAfterDateByStringAndFormat(
                    strToDay, "yyyy-MM-dd", "year", -intIsImport1_1);
            ((TrainCertModel) objSearch).setStrTrainEndDate(strTrainEndDate);
        }
        objInfoList = ibatisService.getObjectList("getSelectCert", objSearch);
        return SUCCESS;
    }

    public String optData2() {

        int intArr[] = ((TrainCertModel) objInfo).getIntIDArr();
        if (intArr != null && intArr.length > 0) {
            HttpServletRequest request = ServletActionContext.getRequest();
            TrainCertModel trainCertModel = new TrainCertModel();
            for (int i = 0; i < intArr.length; i++) {
                trainCertModel.setIntID(intArr[i]);// ID
                // trainCertModel.setIntCertAllow_1(((TrainCertModel)objInfo).getIntCertAllowArr()[i]);
                trainCertModel.setIntCertAllow_1(1);
                String[] obj = request
                        .getParameterValues("objInfo.intCertAllowArr_"
                                + intArr[i]);
                if (obj != null && obj.length > 0) {
                    trainCertModel.setIntCertAllow_1(StringUtil
                            .ObjectToInt(obj[0]));
                }
                trainCertModel.setStrCertRemark_1(((TrainCertModel) objInfo)
                        .getStrCertRemarkArr()[i]);
                String strUpdateSQL = "";
                if (((TrainCertModel) objInfo).getIntIsBKArr()[i] == 1)// 补考
                {
                    trainCertModel.setIntIsNew(2);
                    strUpdateSQL += "intCertDQ=0,"; // 当期
                    strUpdateSQL += "intCertBK=1,"; // 补考
                    if (((TrainCertModel) objInfo).getStrWJArr()[i] != null
                            && ((TrainCertModel) objInfo).getStrWJArr()[i]
                            .length() > 0) {
                        strUpdateSQL += "intCertWJ2=1,"; // 违纪
                    } else {
                        strUpdateSQL += "intCertWJ2=0,"; // 违纪
                    }
                    // getIntCertDQ_search
                } else {
                    strUpdateSQL += "intCertDQ=0,"; // 当期
                    strUpdateSQL += "intCertBK=0,"; // 补考
                    if (((TrainCertModel) objInfo).getStrWJArr()[i] != null
                            && ((TrainCertModel) objInfo).getStrWJArr()[i]
                            .length() > 0) {
                        strUpdateSQL += "intCertWJ1=1,"; // 违纪
                    } else {
                        strUpdateSQL += "intCertWJ1=0,"; // 违纪
                    }
                    trainCertModel.setStrCondition(strUpdateSQL);
                    trainCertModel.setIntIsNew(1);
                }
                ibatisService.updateObject("updateDetail2Info", trainCertModel);
            }// for(int i=0;i<dataArr.length;i++)
        }
        ((TrainCertModel) objInfo).setIntIsNew(3);
        ibatisService.updateObject("optData", getObjInfo());
        return SUCCESS;
    }

    // /////////////////证书已生成
    public String showList3() {
        // ((TrainCertModel)objSearch).setStrTrainType("培训");
        ((TrainCertModel) objSearch).setStrIsNewVal("3,4");
        return super.showPageList();
    }

    public String optData3() {
//		try {
//			((TrainCertModel) objInfo)
//					.setStrTrainCount(MyStringUtil
//							.changeEncode(((TrainCertModel) objInfo)
//									.getStrTrainCount()));
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
        objInfoList = ibatisService.getObjectList("getSelectCert", objInfo);
        if (objInfoList != null && objInfoList.size() > 0) {
            TrainCertModel trainCertModel = new TrainCertModel();
            for (int j = 0; j < objInfoList.size(); j++) {
                trainCertModel = (TrainCertModel) objInfoList.get(j);
                if (trainCertModel.getIntCertAllow_1() == 1) {
                    TrainUtil.updateCertCode(trainCertModel.getIntID_1(),
                            trainCertModel.getIntCertCode_1(),
                            trainCertModel.getStrStudentCertCode_1(),
                            trainCertModel.getStrStudentIDCode_1());
                }
            }
        }
        ((TrainCertModel) objInfo).setIntIsNew(4);
        ibatisService.updateObject("optData", getObjInfo());
        return SUCCESS;

    }

    // /////////////////证书导出
    public String showList4() {
        // ((TrainCertModel)objSearch).setStrTrainType("培训");
        ((TrainCertModel) objSearch).setStrIsNewVal("4,5");
        String strTrainUnitName = getUserSessionModel().getStrUnitName();
        int groupID = getUserSessionModel().getIntGroupID();
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22 || groupID==1 || groupID == 12) {

        } else {
            ((TrainCertModel) objSearch).setStrTrainUnitName(strTrainUnitName);
        }

        return super.showPageList();
    }

    public String showSearchList() {
        String strTrainUnitName = getUserSessionModel().getStrUnitName();
        int groupID = getUserSessionModel().getIntGroupID();
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22 || groupID==1 || groupID == 12) {

        } else {
            ((TrainCertModel) objSearch).setStrTrainUnitName(strTrainUnitName);
        }

        return super.showPageList();
    }

    // ///////////////证书打印
    public String showList5() {
        ((TrainCertModel) objSearch).setStrIsNewVal("4,5");
        return super.showPageList();
    }

    public String viewCertForm() {
        objInfo = (IbatisModel) ibatisService.getObjectInfo(getObjInfo());
        ((TrainCertModel) objSearch)
                .setStrTrainCount(((TrainCertModel) objInfo).getStrTrainCount());
        ((TrainCertModel) objSearch)
                .setIntTrainApplyID(((TrainCertModel) objInfo).getIntTrainApplyID());
        objInfoList = ibatisService.getObjectList("getSelectCert", objSearch);
        return SUCCESS;
    }

    public String viewCertDetailForm() {
        //根据intID得到学员的详细信息
        TrainStudentDetailModel trainStudentDetailModel = (TrainStudentDetailModel) TrainUtil.getStudentDetailByIntID(studentIntID);
        String certDate = MyDateUtil.processStrDate(trainStudentDetailModel.getStrCertDate()); //发证日期
        String validDate = MyDateUtil.getValidDate(trainStudentDetailModel.getStrCertDate()); //有效期结束日期
        String certCode = trainStudentDetailModel.getStrCertCode(); //证书号码
        String studentRemark = trainStudentDetailModel.getStrStudentRemark(); //备注
        String studentPic1 = trainStudentDetailModel.getStrStudentPic1();
        String mainId = String.valueOf(trainStudentDetailModel.getIntMainID());

        //根据培训期次得到培训的详细信息
        String trainCount = trainStudentDetailModel.getStrTrainCount(); //培训期次
        TrainApplyModel trainApplyModel = TrainUtil.getApplyByStudentDetail(trainStudentDetailModel);
        @SuppressWarnings("rawtypes")
        List list = TrainUtil.getCheckDetailByTrainApplyID(trainApplyModel.getIntID());

        trainCertDetailModel = new TrainCertDetailModel();
        trainCertDetailModel.setCertDate(certDate); //发证日期
        trainCertDetailModel.setValidDate(validDate); //有效期结束日期
        trainCertDetailModel.setCertCode(certCode);  //证书号码
        trainCertDetailModel.setStudentRemark(studentRemark); //备注
        trainCertDetailModel.setTrainType(trainApplyModel.getStrTrainType()); //培训类别

        List<Map<String, String>> courseList = new ArrayList<Map<String, String>>();
        for(int i = 0; i < list.size(); i ++) {
            TrainCheckDetailModel trainCheckDetailModel = (TrainCheckDetailModel)list.get(i);
            Map<String, String> map = new HashMap<String, String>();
            map.put("courseName", trainCheckDetailModel.getStrSubjectName());
            map.put("courseTime", trainCheckDetailModel.getStrSubjectTime());

            courseList.add(map);
        }
        trainCertDetailModel.setCourseList(courseList); //课程信息
        trainCertDetailModel.setApplyStartDate(MyDateUtil.processStrDate(trainApplyModel.getStrTrainStartDate())); //培训开始日期
        trainCertDetailModel.setApplyEndDate(MyDateUtil.processStrDate(trainApplyModel.getStrTrainEndDate())); //培训结束日期
        trainCertDetailModel.setCulDate(MyDateUtil.getCulDate()); //当前日期
        trainCertDetailModel.setStudentName(trainStudentDetailModel.getStrStudentName());//姓名
        trainCertDetailModel.setSex(trainStudentDetailModel.getStrStudentSex());
        trainCertDetailModel.setTitleLevel(""); //职称
        trainCertDetailModel.setTitle(trainStudentDetailModel.getStrStudentTitles()); //职务
        trainCertDetailModel.setPost(trainStudentDetailModel.getStrStudentPost()); //单位
        trainCertDetailModel.setDegree(trainStudentDetailModel.getStrStudentDegree()); //文化程度
        String idCard = trainStudentDetailModel.getStrStudentIDCode();
        trainCertDetailModel.setIdCard(idCard); //身份证号
        trainCertDetailModel.setUnitType(trainStudentDetailModel.getStrStudentUnitType()); //单位类型

        String strTrainObjectDesc = trainApplyModel.getStrTrainObjectDesc(); // 培训对象
        trainCertDetailModel.setQualifications(strTrainObjectDesc); //资格类型

        if(!MyStringUtil.isNullStr(studentPic1)) {
            trainCertDetailModel.setStudentPic1Path("../../information/train/student/" + mainId + "/" + studentPic1);
            trainCertDetailModel.setIfPic("1");
        }


        // 班组长增加二维码防伪
        if(strTrainObjectDesc.indexOf("班组长") > -1) {
            String contents = "姓名：" + trainStudentDetailModel.getStrStudentName() + "\n"
                    + "身份证号：" + idCard + "\n"
                    + "培训期次：" + trainCount +"\n"
                    + "证书号码：" + certCode;
            int width = 30;
            int height = 30;
            //	String s = System.getProperty("user.dir");
            //String imgDirectory = s.substring(0,s.lastIndexOf("\\"))+"\\Tomcat 6.0\\webapps\\hodeframe2012_hnmk\\information\\QRCode";
            String imgDirectory = SysConfig.strHodeRealPath + "information/QRCode/";
            File directory = new File(imgDirectory);
            if(!directory.exists()) {
                directory.mkdirs();
            }
            String imgPath = imgDirectory + idCard + ".png";
            trainCertDetailModel.setQRCodePath("../../information/QRCode/" + idCard + ".png");
            QRCodeUtil.encode(contents, width, height, imgPath);
            trainCertDetailModel.setIfQRCode("1");
        }
        return SUCCESS;
    }

    public int getStudentIntID() {
        return studentIntID;
    }

    public void setStudentIntID(int studentIntID) {
        this.studentIntID = studentIntID;
    }

    public TrainCertDetailModel getTrainCertDetailModel() {
        return trainCertDetailModel;
    }

    public void setTrainCertDetailModel(TrainCertDetailModel trainCertDetailModel) {
        this.trainCertDetailModel = trainCertDetailModel;
    }
}