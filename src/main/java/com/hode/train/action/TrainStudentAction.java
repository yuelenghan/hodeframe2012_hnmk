/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.file.FileUtil;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.*;
import com.hode.train.service.TrainStudentService;
import com.hode.train.util.*;
import com.opensymphony.webwork.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrainStudentAction extends AbstractBaseAction {

    private static final long serialVersionUID = -3946903355124764708L;
    TrainStudentDetailModel trainStudentDetailInfo = new TrainStudentDetailModel();
    public int intSelectType; // 选择类型
    private int searchType; // 查询类型 1.查询违纪人员

    private String strSourceFileName = ""; //
    private String strFileOldFileName = ""; // 原始文件
    private String strUnZipDir = ""; // 解压目录

    private String importType = ""; // 图片信息导入方式 0：全新的导入 1：增量导入

    private String checkType = ""; // 校验方式 0：检查重复信息 1：检查人员和图片是否匹配

    private String checkResult = ""; // 校验结果

    private String strTel = ""; // 级别

    private List<TrainApplyModel> trainApplyList;

    @SuppressWarnings("rawtypes")
    private List signUpDetailList;

    private List<TrainPersonModel> personList;
    private TrainPersonModel trainPersonModel;

    private List<TrainStudentDetailModel> existStudents;

    private List<TrainStudentModel> studentList;

    public TrainStudentDetailModel getTrainStudentDetailInfo() {
        return trainStudentDetailInfo;
    }

    public void setTrainStudentDetailInfo(
            TrainStudentDetailModel trainStudentDetailInfo) {
        this.trainStudentDetailInfo = trainStudentDetailInfo;
    }

    public int getIntSelectType() {
        return intSelectType;
    }

    public void setIntSelectType(int intSelectType) {
        this.intSelectType = intSelectType;
    }

    public String getStrSourceFileName() {
        return (strSourceFileName == null) ? "" : strSourceFileName;
    }

    public void setStrSourceFileName(String strSourceFileName) {
        this.strSourceFileName = strSourceFileName;
    }

    public String getStrFileOldFileName() {
        return (strFileOldFileName == null) ? "" : strFileOldFileName;
    }

    public void setStrFileOldFileName(String strFileOldFileName) {
        this.strFileOldFileName = strFileOldFileName;
    }

    public String getStrUnZipDir() {
        return (strUnZipDir == null) ? "" : strUnZipDir;
    }

    public void setStrUnZipDir(String strUnZipDir) {
        this.strUnZipDir = strUnZipDir;
    }

    protected void createObjInfo() {
        objInfo = new TrainStudentModel();
    }

    protected void createObjSearch() {
        objSearch = new TrainStudentModel();
    }

    /**
     * 基础信息导入，预处理显示
     */
    public String addImportPreFix() {

        if (ibatisService.checkObjectExists(getObjInfo())) {
            addActionError("您所添加的“培训期次”已经存在，请重新输入!");
            return ERROR;
        }

        fileAttrUpload.setStrOldAttContent("");
        fileAttrUpload.setFileUploadModel(fileAttrUploadModel);
        fileAttrUpload.uploadSingleFile("tmpData/train/student/",
                getStrDirPath(), 0, 0, 0);
        strFileOldFileName = fileAttrUpload.getStrOldFileName();
        strSourceFileName = fileAttrUpload.getStrNewAttContent();

        return SUCCESS;
    }

    /**
     * 图片信息导入，解压、复制到相应的目录下，并自动修改图片名称，更新数据库信息
     */
    public String addImagePreFix() {
        fileAttrUpload.setStrOldAttContent("");
        fileAttrUpload.setFileUploadModel(fileAttrUploadModel);
        try {
            fileAttrUpload.uploadSingleFile("tmpData/train/student/",
                    getStrDirPath(), 0, 0, 0);
        } catch (Exception e2) {
            addActionError("上传文件出错！请确保每张图片的大小不超过200K，总大小不超过30M！");
            e2.printStackTrace();
            return ERROR;
        }
        strFileOldFileName = fileAttrUpload.getStrOldFileName();
        strSourceFileName = fileAttrUpload.getStrNewAttContent();

        // 解压
        strUnZipDir = FileUtil.getFileName(strSourceFileName);
        UnZip unZip = new UnZip();
        unZip.setEncoding("GB2312");
        unZip.setOverwrite(true);
        unZip.zip(SysConfig.strHodeRealPath + strSourceFileName,
                SysConfig.strHodeRealPath + strUnZipDir);

        int intTrainApplyID = ((TrainStudentModel) objInfo)
                .getIntTrainApplyID();
        TrainStudentModel tranStudentModel = TrainUtil
                .getStudentModelByTrainApplyId(intTrainApplyID);
        String fromPath = SysConfig.strHodeRealPath + strUnZipDir;
        String toPath = ConstantUtil.TRAIN_PIC_ROOT_PATH
                + tranStudentModel.getIntID();

        // 校验解压缩得到的文件夹结构是否满足模版的要求
        try {
            if (!MyFileUtil.checkExampleConstruct(fromPath)) {
                addActionError("模板格式错误！双击模板打开以后应该直接看到pic1、pic2、pic3、pic4,请检查模板格式！");
                return ERROR;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
            addActionError("模板校验出错！");
            return ERROR;
        }

        // 全新导入
        if (importType.trim().equals("0")) {
            // 清空以前的图片文件夹
            if (new File(toPath).exists()) {
                MyFileUtil.clearDirectory(new File(toPath));
            }

            // 清除以前的图片记录
            ((TrainStudentService) ibatisService).updateObject("clearPicInfo",
                    tranStudentModel.getIntID());
        }

        @SuppressWarnings("unchecked")
        List<TrainStudentDetailModel> studentDetailList = TrainUtil
                .getStudentDetailByIntMainID(tranStudentModel.getIntID());

        if (studentDetailList == null || studentDetailList.size() == 0) {
            addActionError("基础信息为空！");
            return ERROR;
        }

        List<File> fileListPic1 = new ArrayList<File>();
        List<File> fileListPic2 = new ArrayList<File>();
        List<File> fileListPic3 = new ArrayList<File>();
        List<File> fileListPic4 = new ArrayList<File>();
        try {
            fileListPic1 = MyFileUtil.getAllJPGFiles(new File(fromPath
                    + "/pic1"), fileListPic1);
            fileListPic2 = MyFileUtil.getAllJPGFiles(new File(fromPath
                    + "/pic2"), fileListPic2);
            fileListPic3 = MyFileUtil.getAllJPGFiles(new File(fromPath
                    + "/pic3"), fileListPic3);
            fileListPic4 = MyFileUtil.getAllJPGFiles(new File(fromPath
                    + "/pic4"), fileListPic4);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("文件列表获取错误");
            return ERROR;
        }

        int result1 = 1;
        int result2 = 1;
        int result3 = 1;
        int result4 = 1;

        // 处理照片
        if (fileListPic1 != null && fileListPic1.size() > 0) {
            result1 = ProcessImageUtil.processImageRelation(fileListPic1,
                    studentDetailList, 1);
        }

        // 处理学历
        if (fileListPic2 != null && fileListPic2.size() > 0) {
            result2 = ProcessImageUtil.processImageRelation(fileListPic2,
                    studentDetailList, 2);
        }

        // 处理体检表
        if (fileListPic3 != null && fileListPic3.size() > 0) {
            result3 = ProcessImageUtil.processImageRelation(fileListPic3,
                    studentDetailList, 3);
        }

        // 处理工作证明
        if (fileListPic4 != null && fileListPic4.size() > 0) {
            result4 = ProcessImageUtil.processImageRelation(fileListPic4,
                    studentDetailList, 4);
        }

        // 清空临时文件
        MyFileUtil.deleteFile(new File(SysConfig.strHodeRealPath
                + strSourceFileName));
        MyFileUtil.deleteDirectory(new File(SysConfig.strHodeRealPath
                + strUnZipDir));

        // 返回结果
        StringBuffer resultStr = new StringBuffer();
        if (result1 == 0 && result2 == 0 && result3 == 0 && result4 == 0) {
            return SUCCESS;
        } else {
            switch (result1) {
                case -1:
                    resultStr.append("<font color='red'>照片处理失败！</font>");
                    break;
                case 0:
                    resultStr.append("<font color='green'>照片处理成功！</font>");
                    break;
                case 1:
                    resultStr.append("<font color='red'>照片数量少于人员数量！</font>");
                    break;
                case 2:
                    resultStr.append("<font color='red'>照片数量多于人员数量！</font>");
                    break;
            }

            resultStr.append("<br>");

            switch (result2) {
                case -1:
                    resultStr.append("<font color='red'>学历处理失败！</font>");
                    break;
                case 0:
                    resultStr.append("<font color='green'>学历处理成功！</font>");
                    break;
                case 1:
                    resultStr.append("<font color='red'>学历数量少于人员数量！</font>");
                    break;
                case 2:
                    resultStr.append("<font color='red'>学历数量多于人员数量！</font>");
                    break;
            }

            resultStr.append("<br>");

            switch (result3) {
                case -1:
                    resultStr.append("<font color='red'>体检表处理失败！</font>");
                    break;
                case 0:
                    resultStr.append("<font color='green'>体检表处理成功！</font>");
                    break;
                case 1:
                    resultStr.append("<font color='red'>体检表数量少于人员数量！</font>");
                    break;
                case 2:
                    resultStr.append("<font color='red'>体检表数量多于人员数量！</font>");
                    break;
            }

            resultStr.append("<br>");

            switch (result4) {
                case -1:
                    resultStr.append("<font color='red'>工作经历处理失败！</font>");
                    break;
                case 0:
                    resultStr.append("<font color='green'>工作经历处理成功！</font>");
                    break;
                case 1:
                    resultStr.append("<font color='red'>工作经历数量少于人员数量！</font>");
                    break;
                case 2:
                    resultStr.append("<font color='red'>工作经历数量多于人员数量！</font>");
                    break;
            }

            addActionError(resultStr.toString());
            return ERROR;
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    public String addImportFinish() {

        if (ibatisService.checkObjectExists(getObjInfo())) {
            addActionError("您所添加的“培训期次”已经存在，请重新输入!");
            return ERROR;
        }

        ((TrainStudentModel) objInfo).setIntCreateUserID(getUserSessionModel()
                .getIntUserID());
        ((TrainStudentModel) objInfo)
                .setStrCreateUserName(getUserSessionModel().getStrName());
        ((TrainStudentModel) objInfo).setIntCreateGroupID(getUserSessionModel()
                .getIntGroupID());
        ((TrainStudentModel) objInfo)
                .setStrCreateGroupName(getUserSessionModel().getStrGroupName());
        ((TrainStudentModel) objInfo).setIntCreateUnitID(getUserSessionModel()
                .getIntUnitID());
        ((TrainStudentModel) objInfo)
                .setStrCreateUnitName(getUserSessionModel().getStrUnitName());
        ((TrainStudentModel) objInfo).setStrCreateDate(DateUtil
                .getNowDateByFormat(""));

        int intTrainApplyID = ((TrainStudentModel) objInfo)
                .getIntTrainApplyID();
        //System.out.println("intTrainApplyID ====================== " + intTrainApplyID);
        if (intTrainApplyID > 0) {
            TrainApplyModel trainApplyModel = TrainUtil
                    .getTrainApplyByID(intTrainApplyID);
            ((TrainStudentModel) objInfo).setStrTrainUnitName(trainApplyModel
                    .getStrTrainUnitName());// 培训单位
            //	System.out.println("trainApplyModel.getStrTrainUnitName() ======================" + trainApplyModel.getStrTrainUnitName());
//			String trainUnitId = this.ibatisService
//					.getObjectInfo("getRbacGroupByDesc",
//							trainApplyModel.getStrTrainUnitName()).toString();
            String trainUnitId = String.valueOf(trainApplyModel.getIntCreateGroupID());
            ((TrainStudentModel) objInfo).setIntTrainUnitID(Integer
                    .parseInt(trainUnitId)); // 培训单位id
            ((TrainStudentModel) objInfo).setStrTrainStartDate(trainApplyModel
                    .getStrTrainStartDate()); // 培训时间--开始时间
            ((TrainStudentModel) objInfo).setStrTrainEndDate(trainApplyModel
                    .getStrTrainEndDate()); // 培训时间--结束时间
            ((TrainStudentModel) objInfo).setStrTrainCount(trainApplyModel
                    .getStrTrainCount());// 培训期数
            ((TrainStudentModel) objInfo).setStrTrainType(trainApplyModel
                    .getStrTrainType()); // 培训类别 培训 再培训
            ((TrainStudentModel) objInfo).setStrTrainObject(trainApplyModel
                    .getStrTrainObject()); // 培训对象
            ((TrainStudentModel) objInfo).setStrTrainObjectDesc(trainApplyModel
                    .getStrTrainObjectDesc()); // 培训对象 描述
            ((TrainStudentModel) objInfo).setStrTrainRange(trainApplyModel
                    .getStrTrainRange()); // 培训机构资质
            ((TrainStudentModel) objInfo).setStrTrainCode(trainApplyModel
                    .getStrTrainCode()); // 资质证书号码
            ((TrainStudentModel) objInfo).setStrTrainUserNum(trainApplyModel
                    .getStrTrainUserNum()); // 培训班计划人数

        }
        if (objInfo.getIntID() > 0) {
            ibatisService.updateObject(objInfo);
            intInsertID = objInfo.getIntID();
            FileUtil.clearTmpDir(SysConfig.strHodeRealPath
                    + "information/train/student/" + intInsertID);
            ibatisService.deleteObject("deleteDetailObject", intInsertID);

            objInfo = (TrainStudentModel) ibatisService.getObjectInfo(objInfo);
        } else {
            intInsertID = ibatisService.insertObjectAndGetID(objInfo);
        }
        // System.out.println("intInsertID:" + intInsertID);
        if (intInsertID > 0) {
            objInfo.setIntID(intInsertID);
            try {
                existStudents = ((TrainStudentService) ibatisService)
                        .addImportFinish((TrainStudentModel) objInfo,
                                strSourceFileName);

                Map sessionMap = this.getSessionModel();
                sessionMap.put("existStudents", existStudents);
            } catch (Exception e) {
                e.printStackTrace();
                addActionError("参培人员导入失败！请检查导入模版是否和下载模版相匹配！");
                return ERROR;
            }
        }
        return SUCCESS;
    }

    public void initDataAfterDelete() {
        intInsertID = objInfo.getIntID();
        FileUtil.clearTmpDir(SysConfig.strHodeRealPath
                + "information/train/student/" + intInsertID);
        ibatisService.deleteObject("deleteDetailObject", intInsertID);
    }

    // ////////////////////////////////////////////////三级机构 start
    // ////////////////////////////
    // //////////////////////////////////三级机构---工作人员(待上报办班申请)
    public String showCheckList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int intOldSelectType = StringUtil.ObjectToInt(request.getSession()
                .getAttribute(("searchTrainStudentType_session")));
        intSelectType = (intSelectType > 0) ? intSelectType : intOldSelectType;
        System.out.println(intSelectType
                + "========================================intSelectType");
        request.getSession().setAttribute("searchTrainStudentType_session",
                intSelectType + "");

        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {

        } else {
            ((TrainStudentModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }
        // ((TrainStudentModel)objSearch).setIntTrainUnitID(getUserSessionModel().getIntGroupID());
        return super.showPageList();
    }

    public String showSearchList() {
        System.out.println(searchType);

        HttpServletRequest request = ServletActionContext.getRequest();

        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {
        } else {
            ((TrainStudentModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }
        String flag = request.getParameter("flag");
        if (!MyStringUtil.isNullStr(flag) && flag.trim().equals("3")) {
            // 查询班组长
            ((TrainStudentModel) objSearch).setStrTrainObjectDesc("班组长");
        }
        // ((TrainStudentModel)objSearch).setIntTrainUnitID(getUserSessionModel().getIntGroupID());
        return super.showPageList();
    }

    // ////////////////////////////////////////////////三级机构 end
    // ////////////////////////////
    public String updateDetailInfo() {

        int intArr[] = trainStudentDetailInfo.getIntIDArr();
        if (intArr != null && intArr.length > 0) {
            TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
            for (int i = 0; i < intArr.length; i++) {
                trainStudentDetailModel.setIntID(intArr[i]);// ID

                trainStudentDetailModel.setIntIsLeader(trainStudentDetailInfo
                        .getIntIDLeaderArr()[i]);
                trainStudentDetailModel
                        .setStrStudentRemark(trainStudentDetailInfo
                                .getStrStudentRemarkArr()[i]);
                ibatisService.updateObject("updateDetailInfo",
                        trainStudentDetailModel);
                // trainStudentDetailModel.setIntIsLeader(0);
                // String[] obj =
                // request.getParameterValues("trainStudentDetailInfo.intIDLeaderArr_"+intArr[i]);
                // if(obj!= null && obj.length > 0)
                // {
                // trainStudentDetailModel.setIntIsLeader(StringUtil.ObjectToInt(obj[0]));
                // }

            }// for(int i=0;i<dataArr.length;i++)
        }
        ibatisService.updateObject("updateDetailInfo2", trainStudentDetailInfo);
        return SUCCESS;
    }

    public String viewDetailForm() {
        trainStudentDetailInfo = (TrainStudentDetailModel) ibatisService
                .getObjectInfo("viewDetail", trainStudentDetailInfo);
        objInfo = new TrainStudentModel();
        objInfo.setIntID(trainStudentDetailInfo.getIntMainID());
        return super.showUpdateFrm();
    }

    // /////////////////////////////1. 人员信息查询
    public String searchStaticReport1() {
        pagination.setUserSessionDM(getUserSessionModel());

        int intStartBirthday_search = ((TrainStudentModel) objSearch)
                .getIntStartBirthday_search();
        int intEndBirthday_search = ((TrainStudentModel) objSearch)
                .getIntEndBirthday_search();
        String strToday = DateUtil.getNowDateByFormat("yyyy-MM-dd");
        if (intStartBirthday_search > 0) {
            String strBirthDay1_search = DateUtil
                    .getAfterDateByStringAndFormat(strToday, "yyyy-MM-dd",
                            "year", -intStartBirthday_search);
            ((TrainStudentModel) objSearch)
                    .setStrBirthDay2_search(strBirthDay1_search);
        }
        if (intEndBirthday_search > 0) {
            String strBirthDay2_search = DateUtil
                    .getAfterDateByStringAndFormat(strToday, "yyyy-MM-dd",
                            "year", -intEndBirthday_search);
            ((TrainStudentModel) objSearch)
                    .setStrBirthDay1_search(strBirthDay2_search);
        }
        ((TrainStudentModel) objSearch).setIntCertCode_search(0);
        int intTotal = ibatisService.getRecordCount("getStaticReportCount",
                objSearch);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("getStaticReportList",
                objSearch, pagination.getIntStartNum(),
                pagination.getIntLineNum());
        return SUCCESS;
    }

    // ////////////////////////////2. 证书信息查询
    public String searchStaticReport2() {
        pagination.setUserSessionDM(getUserSessionModel());

        int intStartBirthday_search = ((TrainStudentModel) objSearch)
                .getIntStartBirthday_search();
        int intEndBirthday_search = ((TrainStudentModel) objSearch)
                .getIntEndBirthday_search();
        String strToday = DateUtil.getNowDateByFormat("yyyy-MM-dd");
        if (intStartBirthday_search > 0) {
            String strBirthDay1_search = DateUtil
                    .getAfterDateByStringAndFormat(strToday, "yyyy-MM-dd",
                            "year", -intStartBirthday_search);
            ((TrainStudentModel) objSearch)
                    .setStrBirthDay2_search(strBirthDay1_search);
        }
        if (intEndBirthday_search > 0) {
            String strBirthDay2_search = DateUtil
                    .getAfterDateByStringAndFormat(strToday, "yyyy-MM-dd",
                            "year", -intEndBirthday_search);
            ((TrainStudentModel) objSearch)
                    .setStrBirthDay1_search(strBirthDay2_search);
        }

        ((TrainStudentModel) objSearch).setIntCertCode_search(1);
        int intTotal = ibatisService.getRecordCount("getStaticReportCount",
                objSearch);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("getStaticReportList",
                objSearch, pagination.getIntStartNum(),
                pagination.getIntLineNum());
        return SUCCESS;
    }

    public String searchStaticReport3() {

        String strTrainStartDate = ((TrainStudentModel) objSearch)
                .getStrTrainStartDate();
        String strTrainEndDate = ((TrainStudentModel) objSearch)
                .getStrTrainEndDate();
        String strToYear = DateUtil.getNowDateByFormat("yyyy");
        if (strTrainStartDate == null || strTrainStartDate.length() < 5) {
            ((TrainStudentModel) objSearch).setStrTrainStartDate(strToYear
                    + "-01-01");
        }
        if (strTrainEndDate == null || strTrainEndDate.length() < 5) {
            ((TrainStudentModel) objSearch).setStrTrainEndDate(strToYear
                    + "-12-31");
        }
        return SUCCESS;

    }

    public String searchStaticReport4() {
        String strTrainStartDate = ((TrainStudentModel) objSearch)
                .getStrTrainStartDate();
        String strTrainEndDate = ((TrainStudentModel) objSearch)
                .getStrTrainEndDate();
        String strToYear = DateUtil.getNowDateByFormat("yyyy");
        if (strTrainStartDate == null || strTrainStartDate.length() < 5) {
            ((TrainStudentModel) objSearch).setStrTrainStartDate(strToYear
                    + "-01-01");
        }
        if (strTrainEndDate == null || strTrainEndDate.length() < 5) {
            ((TrainStudentModel) objSearch).setStrTrainEndDate(strToYear
                    + "-12-31");
        }
        return SUCCESS;

    }

    // /////////////////////////////管理员修改数据
    @SuppressWarnings("unchecked")
    public String showDetailList() {
        int groupID = getUserSessionModel().getIntGroupID();

        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {

        } else {
            // 如果是培训机构
            String groupName = getUserSessionModel().getStrGroupName();
            trainStudentDetailInfo.setFlag("pxjg");
            List<Integer> mainIDList = ibatisService
                    .getObjectList("getIntMainIDListByGroupID2", groupID);
            if (mainIDList == null) {
                mainIDList = new ArrayList<Integer>();
            }
            mainIDList.add(0);
            trainStudentDetailInfo.setMainIDList(mainIDList);
            //trainStudentDetailInfo.setIntMainID(0);
            trainStudentDetailInfo.setStrTrainUnitName(groupName);
        }

        pagination.setUserSessionDM(getUserSessionModel());
        int intTotal = ibatisService.getRecordCount("showDetailCount",
                trainStudentDetailInfo);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("showDetailList",
                trainStudentDetailInfo, pagination.getIntStartNum(),
                pagination.getIntLineNum());
        return SUCCESS;

    }

    public String editDetailForm() {
        trainStudentDetailInfo = (TrainStudentDetailModel) ibatisService
                .getObjectInfo("getObjectDetailInfo", trainStudentDetailInfo);
        return SUCCESS;
    }

    public String deleteDetail() {
        try {
            trainStudentDetailInfo = (TrainStudentDetailModel) ibatisService
                    .getObjectInfo("getStudentDetailByIntID",
                            trainStudentDetailInfo.getIntID());

            // 删除人员详细信息
            ((TrainStudentService) ibatisService).deleteObject(
                    "deleteStudentDetail", trainStudentDetailInfo);

            // 更新对应培训期次下的人数
            ibatisService.updateObject("updateStudentCount",
                    trainStudentDetailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String editDetail() {

        String strBirthDay = "1900-00-00";
        String strIDCard = trainStudentDetailInfo.getStrStudentIDCode();
        if (strIDCard.length() == 18) {
            strBirthDay = strIDCard.substring(6, 10) + "-"
                    + strIDCard.substring(10, 12) + "-"
                    + strIDCard.substring(12, 14);
        } else if (strIDCard.length() == 15) {
            strBirthDay = "19" + strIDCard.substring(6, 8) + "-"
                    + strIDCard.substring(8, 10) + "-"
                    + strIDCard.substring(10, 12);
        }
        trainStudentDetailInfo.setStrBirthDay(strBirthDay);

        ibatisService
                .updateObject("updateDetailObject", trainStudentDetailInfo);

        // 如果把该用户修改为是限制名单，在限制名单中增加该用户的信息，默认有效期为1年
        // if(trainStudentDetailInfo.getIntIsBlackUser() == 1) {
        // TrainUserBlackModel trainUserBlackModel = new TrainUserBlackModel();
        // trainUserBlackModel.setStrStudentName(trainStudentDetailInfo.getStrStudentName());
        // trainUserBlackModel.setStrStudentIDCode(trainStudentDetailInfo.getStrStudentIDCode());
        // trainUserBlackModel.setStrEndDate(MyDateUtil.getCulYear()+"-12-31");
        //
        // trainUserBlackModel.setIntCreateUserID(getUserSessionModel().getIntUserID());
        // trainUserBlackModel.setStrCreateUserName(getUserSessionModel().getStrName());
        // trainUserBlackModel.setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        // trainUserBlackModel.setStrCreateGroupName(getUserSessionModel().getStrGroupName());
        // trainUserBlackModel.setIntCreateUnitID(getUserSessionModel().getIntUnitID());
        // trainUserBlackModel.setStrCreateUnitName(getUserSessionModel().getStrUnitName());
        // trainUserBlackModel.setStrCreateDate(getNowDateByFormat(""));
        // ibatisService.insertObject("insertBlackUser", trainUserBlackModel);
        // }
        return SUCCESS;
    }

    public String showSignUpDetail() {
        int userGroupId = this.getUserSessionModel().getIntGroupID();
        String userGroupName = TrainUtil.getGroupNameById(userGroupId);

        trainStudentDetailInfo.setIntIsSignUp(1); // 只查询网上报名的学员信息
        if (userGroupId != 12 && userGroupId != 1) {
            // 不是省中心用户，并且不是系统管理员
            trainStudentDetailInfo.setStrTrainUnitName(userGroupName); // 只查询当前培训机构的报名信息
        }

        pagination.setUserSessionDM(getUserSessionModel());
        int intTotal = ibatisService.getRecordCount("signUpDetailCount",
                trainStudentDetailInfo);
        pagination.setIntTotalNum(intTotal);

        signUpDetailList = ibatisService.getPageObjectList("getSignUpDetail",
                trainStudentDetailInfo, pagination.getIntStartNum(),
                pagination.getIntLineNum());
        return SUCCESS;
    }

    public String deleteSignUpDetail() {
        // 删除报名信息
        ibatisService.deleteObject("deleteSignUpDetailObject",
                trainStudentDetailInfo.getIntID());

        showSignUpDetail();
        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String checkStudentDetail() {
        int intTrainApplyID = ((TrainStudentModel) objInfo)
                .getIntTrainApplyID();

        int intMainID = Integer.parseInt(((TrainStudentService) ibatisService)
                .getObjectInfo("getMainIDByTrainApplyID", intTrainApplyID)
                .toString());

        List<TrainStudentDetailModel> studentList = ((TrainStudentService) ibatisService)
                .getObjectList("getStudentDetailByIntMainID", intMainID);

        if (studentList != null && studentList.size() > 0) {
            List<TrainStudentDetailRepeatVO> studentRepeatList = ((TrainStudentService) ibatisService)
                    .getObjectList("checkRepeatInfo", intMainID);
            if (checkType.trim().equals("0")) {
                // 校验重复信息
                if (studentRepeatList != null && studentRepeatList.size() > 0) {
                    try {
                        for (int i = 0; i < studentRepeatList.size(); i++) {
                            TrainStudentDetailRepeatVO vo = studentRepeatList
                                    .get(i);
                            String studentID = vo.getStrStudentIDCode(); // 身份证号
                            int num = vo.getRepeatNum(); // 重复次数

                            TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
                            trainStudentDetailModel.setIntMainID(intMainID);
                            trainStudentDetailModel
                                    .setStrStudentIDCode(studentID);
                            List<TrainStudentDetailModel> repeatDetailList = ((TrainStudentService) ibatisService)
                                    .getObjectList("getRepeatDetail",
                                            trainStudentDetailModel);

                            // 删除重复记录，保留最新数据
                            for (int j = 0; j < num - 1; j++) {
                                int intID = repeatDetailList.get(j).getIntID();
                                ((TrainStudentService) ibatisService)
                                        .deleteObject(
                                                "deleteStudentDetailByID",
                                                intID);
                            }

                            // 更新该期次下的实际人数
                            ((TrainStudentService) ibatisService).updateObject(
                                    "updateStudentRealCount", intMainID);
                        }

                        checkResult = "<font color='green'>有重复记录，并且已成功删除！</font>";
                        return SUCCESS;
                    } catch (Exception e) {
                        checkResult = "<font color='red'>删除重复记录出错！</font>";
                        e.printStackTrace();
                        addActionError(checkResult);
                        return ERROR;
                    }
                } else {
                    checkResult = "<font color='green'>该期次下没有重复的人员信息！</font>";
                    return SUCCESS;
                }
            } else {
                if (studentRepeatList != null && studentRepeatList.size() > 0) {
                    // 有重复信息先清除重复信息
                    checkResult = "<font color='red'>该期次下有重复的人员信息，请先清除！</font>";
                } else {
                    // 校验图片和人员是否匹配
                    String imagePath = ConstantUtil.TRAIN_PIC_ROOT_PATH
                            + intMainID;
                    String pic1Path = imagePath + "/pic1";
                    String pic2Path = imagePath + "/pic2";
                    String pic3Path = imagePath + "/pic3";
                    String pic4Path = imagePath + "/pic4";

                    String result1 = "";
                    String result2 = "";
                    String result3 = "";
                    String result4 = "";
                    try {
                        result1 = ProcessImageUtil.checkImage(studentList,
                                pic1Path);
                        result2 = ProcessImageUtil.checkImage(studentList,
                                pic2Path);
                        result3 = ProcessImageUtil.checkImage(studentList,
                                pic3Path);
                        result4 = ProcessImageUtil.checkImage(studentList,
                                pic4Path);

                        if (!result1.equals("")) {
                            if (result1.equals("success")) {
                                checkResult = "<font color='green'>照片全部匹配！</font>";
                            } else if (result1.equals("null")) {
                                checkResult = "<font color='red'>该期次不存在照片信息！</font>";
                            } else {
                                checkResult = "<font color='red'>" + result1
                                        + "没有照片信息！</font>";
                            }
                        }

                        checkResult += "<br>";

                        if (!result2.equals("")) {
                            if (result2.equals("success")) {
                                checkResult += "<font color='green'>学历全部匹配！</font>";
                            } else if (result2.equals("null")) {
                                checkResult += "<font color='red'>该期次不存在学历信息！</font>";
                            } else {
                                checkResult += "<font color='red'>" + result2
                                        + "没有学历信息！</font>";
                            }
                        }

                        checkResult += "<br>";

                        if (!result3.equals("")) {
                            if (result3.equals("success")) {
                                checkResult += "<font color='green'>体检表全部匹配！</font>";
                            } else if (result3.equals("null")) {
                                checkResult += "<font color='red'>该期次不存在体检表信息！</font>";
                            } else {
                                checkResult += "<font color='red'>" + result3
                                        + "没有体检表信息！</font>";
                            }
                        }

                        checkResult += "<br>";

                        if (!result4.equals("")) {
                            if (result4.equals("success")) {
                                checkResult += "<font color='green'>工作经历全部匹配！</font>";
                            } else if (result4.equals("null")) {
                                checkResult += "<font color='red'>该期次不存在工作经历信息！</font>";
                            } else {
                                checkResult += "<font color='red'>" + result4
                                        + "没有工作经历信息！</font>";
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        checkResult = "<font color='red'>校验图片信息出错！</font>";
                        addActionError(checkResult);
                        return ERROR;
                    }
                }
            }
            return SUCCESS;
        } else {
            checkResult = "<font color='red'>该期次下没有对应的参培人员！</font>";
            addActionError(checkResult);
            return ERROR;
        }
    }

    public String showStudentList() {
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {
        } else {
            ((TrainStudentModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        if (intSelectType == 0) {
            intSelectType = 1;
        }

        objInfoList = ((TrainStudentService) ibatisService)
                .showPageStudentList((TrainStudentModel) objSearch,
                        intSelectType, pagination);

        int intTotal = 0;
        intTotal = ibatisService.getRecordCount("getStudentCount",
                (TrainStudentModel) objSearch);

        pagination.setUserSessionDM(getUserSessionModel());
        pagination.setIntTotalNum(intTotal);

        return SUCCESS;
    }

    public String submitStudentInfo() {
        try {
            ((TrainStudentModel) objInfo).setIntStatus(2);
            ibatisService.updateObject("updateStatus", objInfo);

            // 人员信息归档
            TrainUtil.savePerson((TrainStudentModel) objInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public String revokeStudentInfo() {
        try {
            TrainStudentModel trainStudentModel = (TrainStudentModel) ibatisService
                    .getObjectInfo(objInfo);

            if (!((TrainStudentService) ibatisService)
                    .revokeCheck(trainStudentModel)) {
                addActionError("该期次已经通过考核申请，不能撤销！");
                return ERROR;
            }

            trainStudentModel.setIntStatus(1);
            ibatisService.updateObject("updateStatus", trainStudentModel);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }
        return SUCCESS;
    }

    public int getSearchType() {
        return searchType;
    }

    public void setSearchType(int searchType) {
        this.searchType = searchType;
    }

    @SuppressWarnings("rawtypes")
    public List getSignUpDetailList() {
        return signUpDetailList;
    }

    @SuppressWarnings("rawtypes")
    public void setSignUpDetailList(List signUpDetailList) {
        this.signUpDetailList = signUpDetailList;
    }

    public String getImportType() {
        return importType;
    }

    public void setImportType(String importType) {
        this.importType = importType;
    }

    public List<TrainApplyModel> getTrainApplyList() {
        return trainApplyList;
    }

    public void setTrainApplyList(List<TrainApplyModel> trainApplyList) {
        this.trainApplyList = trainApplyList;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public String getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(String checkResult) {
        this.checkResult = checkResult;
    }

    public String getStrTel() {
        return strTel;
    }

    public void setStrTel(String strTel) {
        this.strTel = strTel;
    }

    public List<TrainPersonModel> getPersonList() {
        return personList;
    }

    public void setPersonList(List<TrainPersonModel> personList) {
        this.personList = personList;
    }

    public TrainPersonModel getTrainPersonModel() {
        return trainPersonModel;
    }

    public void setTrainPersonModel(TrainPersonModel trainPersonModel) {
        this.trainPersonModel = trainPersonModel;
    }

    public List<TrainStudentDetailModel> getExistStudents() {
        return existStudents;
    }

    public void setExistStudents(List<TrainStudentDetailModel> existStudents) {
        this.existStudents = existStudents;
    }

    public List<TrainStudentModel> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<TrainStudentModel> studentList) {
        this.studentList = studentList;
    }
}