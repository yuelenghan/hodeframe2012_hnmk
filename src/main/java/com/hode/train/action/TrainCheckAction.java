/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import java.io.File;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.config.SysConfig;
import com.hode.framework.commons.file.FileUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainCheckDetailModel;
import com.hode.train.model.TrainCheckModel;
import com.hode.train.service.TrainCheckService;
import com.hode.train.util.KeyUtil;
import com.hode.train.util.MyDateUtil;
import com.hode.train.util.MyStringUtil;
import com.hode.train.util.TrainUtil;
import com.opensymphony.webwork.ServletActionContext;

public class TrainCheckAction extends AbstractBaseAction {
    private static final long serialVersionUID = -681483111392803258L;
    TrainCheckDetailModel trainCheckDetailInfo = new TrainCheckDetailModel();

    public TrainCheckDetailModel getTrainCheckDetailInfo() {
        return trainCheckDetailInfo;
    }

    public void setTrainCheckDetailInfo(
            TrainCheckDetailModel trainCheckDetailInfo) {
        this.trainCheckDetailInfo = trainCheckDetailInfo;
    }

    public void initOptDB() {
        setIntNeedAttr(1);
        setStrAttrPath("information/traincheck/");
        setInsertAndGetID(true);
    }

    public int intSelectType; // 选择类型

    protected void createObjInfo() {
        objInfo = new TrainCheckModel();
    }

    protected void createObjSearch() {
        objSearch = new TrainCheckModel();
    }

    public String add() {
        int trainApplyId = ((TrainCheckModel) objInfo).getIntTrainApplyID();
        String trainCountClass = "";
        if(((TrainCheckService) ibatisService).getObjectInfo("getTrainCountClassByApplyID", trainApplyId) != null) {
            trainCountClass = ((TrainCheckService) ibatisService).getObjectInfo("getTrainCountClassByApplyID", trainApplyId).toString();
        }

        if(MyStringUtil.isNullStr(trainCountClass)) {
            //不分班
            if (ibatisService.checkObjectExists(getObjInfo())) {
                addActionError("您所添加的“培训期次”已经存在，请重新输入!");
                return ERROR;
            }
        } else {
            //分班
            ((TrainCheckModel) objInfo).setStrTrainCountClass(trainCountClass);
        }

        String trainObjectDesc = ((TrainCheckModel) objInfo)
                .getStrTrainObjectDesc();
        String trainObject = this.ibatisService.getObjectInfo(
                "getDictionaryByDesc", trainObjectDesc).toString();


        ((TrainCheckModel) objInfo).setStrTrainObject(trainObject);
        ((TrainCheckModel) objInfo).setStrTrainObjectDesc(trainObjectDesc);

        ((TrainCheckModel) objInfo).setIntCreateUserID(getUserSessionModel()
                .getIntUserID());
        ((TrainCheckModel) objInfo).setStrCreateUserName(getUserSessionModel()
                .getStrName());
        ((TrainCheckModel) objInfo).setIntCreateGroupID(getUserSessionModel()
                .getIntGroupID());
        ((TrainCheckModel) objInfo).setStrCreateGroupName(getUserSessionModel()
                .getStrGroupName());
        ((TrainCheckModel) objInfo).setIntCreateUnitID(getUserSessionModel()
                .getIntUnitID());
        ((TrainCheckModel) objInfo).setStrCreateUnitName(getUserSessionModel()
                .getStrUnitName());
        ((TrainCheckModel) objInfo).setStrCreateDate(getNowDateByFormat(""));
        super.add();

        // 复制上传的附件
        String[] fileNames = TrainUtil.getCheckFileNames(objInfo.getIntID());
        if (fileNames != null) {
            String curDate = MyDateUtil.DateToString(new Date());
            String copyFromPath = SysConfig.strHodeRealPath
                    + "information/traincheck/" + curDate + "/";
            String copyToPath = "";
            String userDir = System.getProperty("user.dir");
            copyToPath = userDir.substring(0, userDir.lastIndexOf("\\"))
                    + "\\webapps\\hodeframe2012_hnmk\\information\\traincheck\\"
                    + curDate;
            File directory = new File(copyToPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileUtil.copyAllDir(copyFromPath, copyToPath);
        }

        trainCheckDetailInfo.setIntTrainCheckID(intInsertID);
        ((TrainCheckService) ibatisService)
                .addInsertDetail(trainCheckDetailInfo);
        return SUCCESS;
    }

    public String update() {
        if (ibatisService.checkObjectExists(getObjInfo())) {
            addActionError("您所添加的“培训期次”已经存在，请重新输入!");
            return ERROR;
        }
        super.update();

        // 复制上传的附件
        String[] fileNames = TrainUtil.getCheckFileNames(objInfo.getIntID());
        if (fileNames != null) {
            String curDate = MyDateUtil.DateToString(new Date());
            String copyFromPath = SysConfig.strHodeRealPath
                    + "information/traincheck/" + curDate + "/";
            String copyToPath = "";
            String userDir = System.getProperty("user.dir");
            copyToPath = userDir.substring(0, userDir.lastIndexOf("\\"))
                    + "\\webapps\\hodeframe2012_hnmk\\information\\traincheck\\"
                    + curDate;
            File directory = new File(copyToPath);
            if (!directory.exists()) {
                directory.mkdirs();
            }
            FileUtil.copyAllDir(copyFromPath, copyToPath);
        }

        ibatisService.deleteObject("deleteDetailObject", objInfo.getIntID()
                + "");
        trainCheckDetailInfo.setIntTrainCheckID(objInfo.getIntID());
        ((TrainCheckService) ibatisService)
                .addInsertDetail(trainCheckDetailInfo);
        return SUCCESS;
    }

    public String delete() {
        super.delete();
        ibatisService.deleteObject("deleteDetailObject", objInfo.getIntID()
                + "");
        return SUCCESS;
    }

    // ////////////////////////////////////////////////三级机构 start
    // ////////////////////////////
    // //////////////////////////////////三级机构---工作人员(待上报办班申请)
    public String showCheckList() {

        HttpServletRequest request = ServletActionContext.getRequest();
        int intOldSelectType = StringUtil.ObjectToInt(request.getSession()
                .getAttribute(("searchTrainCheckType_session")));
        intSelectType = (intSelectType > 0) ? intSelectType : intOldSelectType;
        System.out.println(intSelectType
                + "========================================intSelectType");
        request.getSession().setAttribute("searchTrainCheckType_session",
                intSelectType + "");
        switch (intSelectType) {
            case 1:// 三级机构(待上报办班申请)
                ((TrainCheckModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainCheckModel) objSearch).setStrCheckFlag("0");
                break;
            case 2:// 三级机构(已上报办班申请)
                ((TrainCheckModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainCheckModel) objSearch).setStrCheckFlag("1,9");
                break;
            case 3:// 省培训中心(待批准办班申请)
                ((TrainCheckModel) objSearch).setStrCheckFlag("1");
                break;
            case 4:// 省培训中心(已批准办班申请)
                ((TrainCheckModel) objSearch).setStrCheckFlag("9");
                break;
            default:// 三级机构(待上报办班申请)
                ((TrainCheckModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainCheckModel) objSearch).setStrCheckFlag("0");
                break;
        }
        StringUtil.in_array(11010100, getUserSessionModel()
                .getIntPrivilegeIDs());
        StringUtil.in_array(11010200, getUserSessionModel()
                .getIntPrivilegeIDs());
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {

        } else {
            ((TrainCheckModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        return super.showPageList();
    }

    public String showSearchList() {

        StringUtil.in_array(11010100, getUserSessionModel()
                .getIntPrivilegeIDs());
        StringUtil.in_array(11010200, getUserSessionModel()
                .getIntPrivilegeIDs());
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        if (bln21 || bln22) {

        } else {
            ((TrainCheckModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        return super.showPageList();
    }

    // ////////////////////////////////////////////////三级机构 end
    // ////////////////////////////
    public String checkInfo() {
        int intNextFlag = ((TrainCheckModel) objInfo).getIntNextFlag();
        if (intNextFlag == 1)// 不同意
        {
            ((TrainCheckModel) objInfo).setIntCheckFlag(0);
            ibatisService.updateObject("checkInfo_0", objInfo);
        } else if (intNextFlag == 2 || intNextFlag == 3) // 同意
        {
            int intOldCheckFlag = ((TrainCheckModel) objInfo).getIntCheckFlag();
            switch (intOldCheckFlag) {
                case 0:// 三级机构
                    ((TrainCheckModel) objInfo).setIntCheckFlag(1);
                    ibatisService.updateObject("checkInfo_1", objInfo);
                    break;
                case 1:// 省培训中心
                    ((TrainCheckModel) objInfo).setIntCheckFlag(9);
                    ((TrainCheckModel) objInfo)
                            .setStrFinishIdea(((TrainCheckModel) objInfo)
                                    .getStrCheckVal());
                    ((TrainCheckModel) objInfo)
                            .setStrCheckDate(getNowDateByFormat("yyyy-MM-dd"));
                    ibatisService.updateObject("checkInfo_9", objInfo);

                    ibatisService.insertObject("copyCert", objInfo);
                    break;
            }
        }
        TrainUtil.insertLog(intNextFlag, objInfo.getIntID(), "train_check",
                ((TrainCheckModel) objInfo).getStrCheckVal(),
                getUserSessionModel());
        return SUCCESS;
    }

    public String signForm() {
        // 检查数字证书
        String userName = getUserSessionModel().getStrUserAccount();
        String key = KeyUtil.privateKeyMap.get(userName);

        String keyStorePath = "d:/" + userName + ".keystore";
        String certificatePath = "d:/" + userName + ".cer";
        String alias = userName;
        String keyStorePassword = key;
        String privateKeyPassword = key;

        // 验证签名
        boolean status = false;
        try {
            byte[] data = "signForm".getBytes();
            byte[] encodedData = KeyUtil.encryptByPrivateKey(data,
                    keyStorePath, alias, keyStorePassword, privateKeyPassword);

            // 产生签名
            String sign = KeyUtil.sign(encodedData, keyStorePath, alias,
                    keyStorePassword, privateKeyPassword);
            // System.err.println("签名:\r" + sign);

            status = KeyUtil.verify(encodedData, sign, certificatePath);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("数字证书验证失败！");
            status = false;
        }

        if (status) {
            return SUCCESS;
        } else {
            addActionError("数字证书验证失败！");
            return ERROR;
        }
    }

    public String printTicket() {
        StringUtil.in_array(11010100, getUserSessionModel()
                .getIntPrivilegeIDs());
        StringUtil.in_array(11010200, getUserSessionModel()
                .getIntPrivilegeIDs());
        boolean bln21 = StringUtil.in_array(11020100, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心查看员
        boolean bln22 = StringUtil.in_array(11020200, getUserSessionModel()
                .getIntPrivilegeIDs());// 省中心审批员
        ((TrainCheckModel) objSearch).setStrCheckFlag("9");
        if (bln21 || bln22) {

        } else {
            ((TrainCheckModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        //设置打印准考证标识，用来过滤结果集，具体过滤细则见sql
        ((TrainCheckModel) objSearch).setIfPrintTicket("yes");

        return super.showPageList();
    }

}