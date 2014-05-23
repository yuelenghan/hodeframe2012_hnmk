/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.file.FileUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainScoreDetailModel;
import com.hode.train.model.TrainScoreModel;
import com.hode.train.service.TrainScoreService;
import com.hode.train.util.MyStringUtil;
import com.hode.train.util.TrainUtil;
import com.opensymphony.webwork.ServletActionContext;

public class TrainScoreAction extends AbstractBaseAction {

    private static final long serialVersionUID = 4402177624327683200L;
    TrainScoreDetailModel trainScoreDetailInfo = new TrainScoreDetailModel();
    public int intSelectType; // 选择类型

    private String strSourceFileName = ""; //
    private String strFileOldFileName = ""; // 原始文件
    private String postStrScoreExam;
    private String postStrScoreTrainCount;

    public TrainScoreDetailModel getTrainScoreDetailInfo() {
        return trainScoreDetailInfo;
    }

    public void setTrainScoreDetailInfo(
            TrainScoreDetailModel trainScoreDetailInfo) {
        this.trainScoreDetailInfo = trainScoreDetailInfo;
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

    protected void createObjInfo() {
        objInfo = new TrainScoreModel();
    }

    protected void createObjSearch() {
        objSearch = new TrainScoreModel();
    }

    /***
     * 数据导入，预处理显示
     */
    public String addImportPreFix() {
        fileAttrUpload.setStrOldAttContent("");
        fileAttrUpload.setFileUploadModel(fileAttrUploadModel);
        fileAttrUpload.uploadSingleFile("tmpData/train/score/",
                getStrDirPath(), 0, 0, 0);
        strFileOldFileName = fileAttrUpload.getStrOldFileName();
        strSourceFileName = fileAttrUpload.getStrNewAttContent();

        return SUCCESS;
    }

    public String addImportFinish() {
        // 处理提交过来的培训期次+班级
        String[] trainCountAndClass = postStrScoreTrainCount.split(",");
        String trainCount = trainCountAndClass[0].trim();
        String trainCountClass = "";
        if (trainCountAndClass.length > 1) {
            trainCountClass = trainCountAndClass[1].trim();
        }

        // 根据培训期次生成考试期次
        // 生成规则：培训期次+班级+考试场次
        // 例：安全生产管理人员一班001
        String scoreCount = trainCount + trainCountClass + "00"
                + postStrScoreExam;

        ((TrainScoreModel) objInfo).setStrScoreCount(scoreCount); // 设置生成的考试期次
        ((TrainScoreModel) objInfo).setStrScoreExam(postStrScoreExam); // 设置提交过来的考试场次
        if (!MyStringUtil.isNullStr(trainCountClass)) {
            // 分班
            ((TrainScoreModel) objInfo).setStrTrainCountClass(trainCountClass);
        }

        ((TrainScoreModel) objInfo).setIntCreateUserID(getUserSessionModel()
                .getIntUserID());
        ((TrainScoreModel) objInfo).setStrCreateUserName(getUserSessionModel()
                .getStrName());
        ((TrainScoreModel) objInfo).setIntCreateGroupID(getUserSessionModel()
                .getIntGroupID());
        ((TrainScoreModel) objInfo).setStrCreateGroupName(getUserSessionModel()
                .getStrGroupName());
        ((TrainScoreModel) objInfo).setIntCreateUnitID(getUserSessionModel()
                .getIntUnitID());
        ((TrainScoreModel) objInfo).setStrCreateUnitName(getUserSessionModel()
                .getStrUnitName());
        ((TrainScoreModel) objInfo).setStrCreateDate(getNowDateByFormat(""));

        ((TrainScoreModel) objInfo).setStrScoreTrainCount(trainCount); // 设置提交过来的培训期次
        int scoreType = ((TrainScoreModel) objInfo).getIntScoreType();

        if (scoreType == 0) {
            if (ibatisService.checkObjectExists(objInfo)) {
                addActionError("您所添加的该“考试期次下的考试场次”已经存在，请重新选择!");
                return ERROR;
            }

            if (objInfo.getIntID() > 0) {
                ibatisService.updateObject(objInfo);
                intInsertID = objInfo.getIntID();
                FileUtil.clearTmpDir(SysConfig.strHodeRealPath
                        + "information/train/score/" + intInsertID);
                ibatisService.deleteObject("deleteDetailObject", intInsertID);
                ibatisService.deleteObject("deleteDetailObject1", intInsertID);
                objInfo = (TrainScoreModel) ibatisService
                        .getObjectInfo(objInfo);
            } else {
                intInsertID = ibatisService.insertObjectAndGetID(objInfo);
            }
            System.out.println("intInsertID:" + intInsertID);
            if (intInsertID > 0) {
                objInfo.setIntID(intInsertID);
                ((TrainScoreService) ibatisService).addImportFinish(
                        (TrainScoreModel) objInfo, strSourceFileName);
            }
        } else {
            // 导入补考信息
            if (!ibatisService.checkObjectExists(objInfo)) {
                addActionError("您所导入的该“考试期次下的考试场次”不存在，请重新选择!");
                return ERROR;
            }

            objInfo = (TrainScoreModel) ((TrainScoreService) ibatisService)
                    .getObjectInfo("getTrainScoreByScoreCount", objInfo);

            ((TrainScoreService) ibatisService).addImportFinish2(
                    (TrainScoreModel) objInfo, strSourceFileName);
        }
        return SUCCESS;
    }

    public void initDataAfterDelete() {
        intInsertID = objInfo.getIntID();
        FileUtil.clearTmpDir(SysConfig.strHodeRealPath
                + "information/train/score/" + intInsertID);
        ibatisService.deleteObject("deleteDetailObject", intInsertID);
        ibatisService.deleteObject("deleteDetailObject1", intInsertID);
        ibatisService.deleteObject("deleteDetailObject2", intInsertID);
    }

    // ////////////////////////////////////////////////三级机构 start
    // ////////////////////////////
    // //////////////////////////////////三级机构---工作人员(待上报办班申请)
    public String showCheckList() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int intOldSelectType = StringUtil.ObjectToInt(request.getSession()
                .getAttribute(("searchTrainScoreType_session")));
        intSelectType = (intSelectType > 0) ? intSelectType : intOldSelectType;
        System.out.println(intSelectType
                + "========================================intSelectType");
        request.getSession().setAttribute("searchTrainScoreType_session",
                intSelectType + "");
        switch (intSelectType) {
            case 1:// 三级机构(待上报办班申请)
                ((TrainScoreModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainScoreModel) objSearch).setStrCheckFlag("0");
                break;
            case 2:// 三级机构(已上报办班申请)
                ((TrainScoreModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainScoreModel) objSearch).setStrCheckFlag("1,9");
                break;
            case 3:// 省培训中心(待批准办班申请)
                ((TrainScoreModel) objSearch).setStrCheckFlag("1");
                break;
            case 4:// 省培训中心(已批准办班申请)
                ((TrainScoreModel) objSearch).setStrCheckFlag("9");
                break;
            default:// 三级机构(待上报办班申请)
                ((TrainScoreModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainScoreModel) objSearch).setStrCheckFlag("0");
                break;
        }

        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22 || getUserSessionModel().getIntUserID() == 28) {

        } else {
            ((TrainScoreModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        return super.showPageList();
    }

    // ////////////////////////////////////////////////三级机构 end
    // ////////////////////////////
    // ////////////////////////////////////////////////三级机构 end
    // ////////////////////////////
    public String checkInfo() {
        int intNextFlag = ((TrainScoreModel) objInfo).getIntNextFlag();
        System.out.println(intNextFlag + "-------------------intNextFlag");
        if (intNextFlag == 1)// 不同意
        {
            ((TrainScoreModel) objInfo).setIntCheckFlag(0);
            ibatisService.updateObject("checkInfo_0", objInfo);
        } else if (intNextFlag == 2 || intNextFlag == 3) // 同意
        {
            int intOldCheckFlag = ((TrainScoreModel) objInfo).getIntCheckFlag();
            switch (intOldCheckFlag) {
                case 0:// 三级机构
                    ((TrainScoreModel) objInfo).setIntCheckFlag(1);
                    ibatisService.updateObject("checkInfo_1", objInfo);
                    break;
                case 1:// 省培训中心
                    ((TrainScoreModel) objInfo).setIntCheckFlag(9);
                    ibatisService.updateObject("checkInfo_9", objInfo);
                    ibatisService.updateObject("checkInfo_91", objInfo);
                    ibatisService.updateObject("checkInfo_92", objInfo);

                    TrainScoreModel trainScoreModel = (TrainScoreModel) ibatisService
                            .getObjectInfo(objInfo);

                    ibatisService.updateObject("updateCert", trainScoreModel);
                    break;
            }
        }
        TrainUtil.insertLog(intNextFlag, objInfo.getIntID(), "train_score",
                ((TrainScoreModel) objInfo).getStrCheckVal(),
                getUserSessionModel());
        return SUCCESS;
    }

    public String showSearchList() {

        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {

        } else {
            ((TrainScoreModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        return super.showPageList();
    }

    @SuppressWarnings({ "unchecked" })
    public String showDetailSearchList() {
        int intTotal = 0;
        int intGroupID = getUserSessionModel().getIntGroupID();
        if (intGroupID != 12 && intGroupID != 1) {
            // 不是省中心用户，并且不是系统管理员
            List<Integer> mainIDList = new ArrayList<Integer>();
            mainIDList = ((TrainScoreService) ibatisService).getObjectList(
                    "getMainIDByGroupID", intGroupID);
            if (mainIDList != null && mainIDList.size() > 0) {
                trainScoreDetailInfo.setMainIDList(mainIDList);

                intTotal = ibatisService.getRecordCount("getDetailRecordCount",
                        trainScoreDetailInfo);

                objInfoList = ibatisService
                        .getPageObjectList("getDetailPageObjectList",
                                trainScoreDetailInfo,
                                pagination.getIntStartNum(),
                                pagination.getIntLineNum());
            } else {
                objInfoList = null;
            }
        } else {
            intTotal = ibatisService.getRecordCount("getDetailRecordCount",
                    trainScoreDetailInfo);

            objInfoList = ibatisService.getPageObjectList(
                    "getDetailPageObjectList", trainScoreDetailInfo,
                    pagination.getIntStartNum(), pagination.getIntLineNum());
        }

        pagination.setUserSessionDM(getUserSessionModel());
        pagination.setIntTotalNum(intTotal);

        return SUCCESS;
    }

    public String getPostStrScoreExam() {
        return postStrScoreExam;
    }

    public void setPostStrScoreExam(String postStrScoreExam) {
        this.postStrScoreExam = postStrScoreExam;
    }

    public String getPostStrScoreTrainCount() {
        return postStrScoreTrainCount;
    }

    public void setPostStrScoreTrainCount(String postStrScoreTrainCount) {
        this.postStrScoreTrainCount = postStrScoreTrainCount;
    }

}