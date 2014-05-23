/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainApplyCourse;
import com.hode.train.model.TrainApplyDetailModel;
import com.hode.train.model.TrainApplyItem;
import com.hode.train.model.TrainApplyMaterial;
import com.hode.train.model.TrainApplyModel;
import com.hode.train.model.TrainApplyTeacher;
import com.hode.train.model.TrainTeacher;
import com.hode.train.model.Traincourse;
import com.hode.train.service.TrainApplyService;
import com.hode.train.util.JsonUtil;
import com.hode.train.util.KeyUtil;
import com.hode.train.util.MyStringUtil;
import com.hode.train.util.TrainUtil;
import com.opensymphony.webwork.ServletActionContext;
import com.opensymphony.xwork.ActionContext;

public class TrainApplyAction extends AbstractBaseAction {
    private static final long serialVersionUID = -5099485108162270953L;
    TrainApplyDetailModel trainApplyDetailInfo = new TrainApplyDetailModel();

    private Traincourse traincourse;
    private List<Map<String, Object>> courseInfoList;
    private TrainTeacher trainTeacher;

    public TrainApplyDetailModel getTrainApplyDetailInfo() {
        return trainApplyDetailInfo;
    }

    public void setTrainApplyDetailInfo(
            TrainApplyDetailModel trainApplyDetailInfo) {
        this.trainApplyDetailInfo = trainApplyDetailInfo;
    }

    TrainApplyItem trainApplyItem = new TrainApplyItem();

    public TrainApplyItem getTrainApplyItem() {
        return trainApplyItem;
    }

    public void setTrainApplyItem(TrainApplyItem trainApplyItem) {
        this.trainApplyItem = trainApplyItem;
    }

    TrainApplyMaterial trainApplyMaterial = new TrainApplyMaterial();

    public TrainApplyMaterial getTrainApplyMaterial() {
        return trainApplyMaterial;
    }

    public void setTrainApplyMaterial(TrainApplyMaterial trainApplyMaterial) {
        this.trainApplyMaterial = trainApplyMaterial;
    }

    TrainApplyTeacher trainApplyTeacher = new TrainApplyTeacher();

    public TrainApplyTeacher getTrainApplyTeacher() {
        return trainApplyTeacher;
    }

    public void setTrainApplyTeacher(TrainApplyTeacher trainApplyTeacher) {
        this.trainApplyTeacher = trainApplyTeacher;
    }

    TrainApplyCourse trainApplyCourse = new TrainApplyCourse();

    public TrainApplyCourse getTrainApplyCourse() {
        return trainApplyCourse;
    }

    public void setTrainApplyCourse(TrainApplyCourse trainApplyCourse) {
        this.trainApplyCourse = trainApplyCourse;
    }

    public void initOptDB() {
        setIntNeedAttr(1);
        setStrAttrPath("information/trainapply/");
        setInsertAndGetID(true);
    }

    public int intSelectType; // 选择类型

    protected void createObjInfo() {
        objInfo = new TrainApplyModel();
    }

    protected void createObjSearch() {
        objSearch = new TrainApplyModel();
    }

    public String add() {
        String strTrainStartDate = ((TrainApplyModel) objInfo)
                .getStrTrainStartDate();
        String strTrainEndDate = ((TrainApplyModel) objInfo)
                .getStrTrainEndDate();
        ((TrainApplyModel) objInfo).getStrTrainTern();
        String trainObject = ((TrainApplyModel) objInfo).getStrTrainObject();
        String trainObjectDesc = this.ibatisService.getObjectInfo(
                "getDictionaryById", trainObject).toString();
        strTrainStartDate = strTrainStartDate.replace("-", ".");
        strTrainStartDate = strTrainStartDate.substring(2);
        strTrainEndDate = strTrainEndDate.replace("-", ".");
        strTrainEndDate = strTrainEndDate.substring(5);

        if (trainObjectDesc.substring(0, 2).equals("煤矿")) {
            trainObjectDesc = trainObjectDesc.substring(2);
        }
        if ((trainObjectDesc.substring(trainObjectDesc.length() - 2, trainObjectDesc.length()))
                .equals("作业")) {
            trainObjectDesc = trainObjectDesc.substring(0, trainObjectDesc.length() - 2);
        }

        ((TrainApplyModel) objInfo).setStrTrainObject(trainObject);
        ((TrainApplyModel) objInfo).setStrTrainObjectDesc(trainObjectDesc);

        String ifClass = ((TrainApplyModel) objInfo).getStrIfClass();
        if (ifClass.equals("0")) {
            if (ibatisService.checkObjectExists(getObjInfo())) {
                addActionError("您所添加的“培训期次”已经存在，请重新输入!");
                return ERROR;
            }
        } else {
            String trainCountCLass = ((TrainApplyModel) objInfo)
                    .getStrTrainCountClass();
            if (MyStringUtil.isNullStr(trainCountCLass)) {
                addActionError("您选择了“分班”，请填写班级名称!");
                return ERROR;
            }
            int num = ibatisService.getRecordCount(
                    "getTrainCountClassRecordCount", objInfo);
            if (num > 0) {
                addActionError("您所添加的“培训期次和班级”已经存在，请重新输入!");
                return ERROR;
            }
        }

        ((TrainApplyModel) objInfo).setIntCreateUserID(getUserSessionModel()
                .getIntUserID());
        ((TrainApplyModel) objInfo).setStrCreateUserName(getUserSessionModel()
                .getStrName());
        ((TrainApplyModel) objInfo).setIntCreateGroupID(getUserSessionModel()
                .getIntGroupID());
        ((TrainApplyModel) objInfo).setStrCreateGroupName(getUserSessionModel()
                .getStrGroupName());
        ((TrainApplyModel) objInfo).setIntCreateUnitID(getUserSessionModel()
                .getIntUnitID());
        ((TrainApplyModel) objInfo).setStrCreateUnitName(getUserSessionModel()
                .getStrUnitName());
        ((TrainApplyModel) objInfo).setStrCreateDate(getNowDateByFormat(""));

        super.add();

        trainApplyDetailInfo.setIntTrainApplyID(intInsertID);

        try {
            ((TrainApplyService) ibatisService)
                    .addInsertDetail(trainApplyDetailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("插入课程和教师信息失败！");
            return ERROR;
        }

        return SUCCESS;

    }

    public String update() {
        String strTrainStartDate = ((TrainApplyModel) objInfo)
                .getStrTrainStartDate();
        String strTrainEndDate = ((TrainApplyModel) objInfo)
                .getStrTrainEndDate();
        String trainObject = ((TrainApplyModel) objInfo).getStrTrainObject();
        String trainObjectDesc = this.ibatisService.getObjectInfo(
                "getDictionaryById", trainObject).toString();
        strTrainStartDate = strTrainStartDate.replace("-", ".");
        strTrainStartDate = strTrainStartDate.substring(2);
        strTrainEndDate = strTrainEndDate.replace("-", ".");
        strTrainEndDate = strTrainEndDate.substring(5);

        if (trainObjectDesc.substring(0, 2).equals("煤矿")) {
            trainObjectDesc = trainObjectDesc.substring(2);
        }
        if ((trainObjectDesc.substring(trainObjectDesc.length() - 2, trainObjectDesc.length()))
                .equals("作业")) {
            trainObjectDesc = trainObjectDesc.substring(0, trainObjectDesc.length() - 2);
        }

        ((TrainApplyModel) objInfo).setStrTrainObject(trainObject);
        ((TrainApplyModel) objInfo).setStrTrainObjectDesc(trainObjectDesc);

        String ifClass = ((TrainApplyModel) objInfo).getStrIfClass();
        if (ifClass.equals("0")) {
            if (ibatisService.checkObjectExists(getObjInfo())) {
                addActionError("您所添加的“培训期次”已经存在，请重新输入!");
                return ERROR;
            }
            ((TrainApplyModel) objInfo).setStrTrainCountClass("");
        } else {
            String trainCountCLass = ((TrainApplyModel) objInfo)
                    .getStrTrainCountClass();
            if (MyStringUtil.isNullStr(trainCountCLass)) {
                addActionError("您选择了“分班”，请填写班级名称!");
                return ERROR;
            }
            int num = ibatisService.getRecordCount(
                    "getTrainCountClassRecordCount", objInfo);
            if (num > 0) {
                addActionError("您所添加的“培训期次和班级”已经存在，请重新输入!");
                return ERROR;
            }
        }

        super.update();

        ibatisService.deleteObject("deleteDetailObject", objInfo.getIntID()
                + "");
        trainApplyDetailInfo.setIntTrainApplyID(objInfo.getIntID());

        try {
            ((TrainApplyService) ibatisService)
                    .addInsertDetail(trainApplyDetailInfo);
        } catch (Exception e) {
            e.printStackTrace();
            addActionError("插入课程和教师信息失败！");
            return ERROR;
        }

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
                .getAttribute(("searchTrainApplyType_session")));
        intSelectType = (intSelectType > 0) ? intSelectType : intOldSelectType;
        System.out.println(intSelectType
                + "========================================intSelectType");
        request.getSession().setAttribute("searchTrainApplyType_session",
                intSelectType + "");
        switch (intSelectType) {
            case 1:// 三级机构(待上报办班申请)
                ((TrainApplyModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainApplyModel) objSearch).setStrCheckFlag("0");
                break;
            case 2:// 三级机构(已上报办班申请)
                ((TrainApplyModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainApplyModel) objSearch).setStrCheckFlag("1,9");
                break;
            case 3:// 省培训中心(待批准办班申请)
                ((TrainApplyModel) objSearch).setStrCheckFlag("1");
                break;
            case 4:// 省培训中心(已批准办班申请)
                ((TrainApplyModel) objSearch).setStrCheckFlag("9");
                break;
            default:// 三级机构(待上报办班申请)
                ((TrainApplyModel) objSearch)
                        .setIntCreateUserID(getUserSessionModel().getIntUserID());
                ((TrainApplyModel) objSearch).setStrCheckFlag("0");
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
            ((TrainApplyModel) objSearch)
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
            ((TrainApplyModel) objSearch)
                    .setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        }

        return super.showPageList();
    }

    // ////////////////////////////////////////////////三级机构 end
    // ////////////////////////////
    public String checkInfo() {
        int intNextFlag = ((TrainApplyModel) objInfo).getIntNextFlag();
        if (intNextFlag == 1)// 不同意
        {
            ((TrainApplyModel) objInfo).setIntCheckFlag(0);
            ibatisService.updateObject("checkInfo_0", objInfo);
        } else if (intNextFlag == 2 || intNextFlag == 3) // 同意
        {
            int intOldCheckFlag = ((TrainApplyModel) objInfo).getIntCheckFlag();
            switch (intOldCheckFlag) {
                case 0:// 三级机构
                    ((TrainApplyModel) objInfo).setIntCheckFlag(1);
                    ibatisService.updateObject("checkInfo_1", objInfo);
                    break;
                case 1:// 省培训中心
                    ((TrainApplyModel) objInfo).setIntCheckFlag(9);
                    ((TrainApplyModel) objInfo)
                            .setStrFinishIdea(((TrainApplyModel) objInfo)
                                    .getStrCheckVal());
                    ((TrainApplyModel) objInfo)
                            .setStrCheckDate(getNowDateByFormat("yyyy-MM-dd"));
                    ibatisService.updateObject("checkInfo_9", objInfo);
                    break;
            }
        }
        TrainUtil.insertLog(intNextFlag, objInfo.getIntID(), "train_apply",
                ((TrainApplyModel) objInfo).getStrCheckVal(),
                getUserSessionModel());
        return SUCCESS;
    }

    public String showTeacherInit() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String userID = request.getParameter("intCreateUser");
        String groupID = request.getParameter("intGroupID");
        TrainTeacher trainTeacher = new TrainTeacher();
        trainTeacher.setIntGroup(Integer.parseInt(groupID));
        trainTeacher.setIntCreateUser(Integer.parseInt(userID));
        @SuppressWarnings("unchecked")
        List<TrainTeacher> allTeachers = this.ibatisService.getObjectList(
                "getAllTeachers", trainTeacher);
        request.setAttribute("allTeachers", allTeachers);
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

    public void getCourseAndTeacher() {
        courseInfoList = ((TrainApplyService) ibatisService)
                .getCourseAndTeacher(traincourse);
        ActionContext ctx = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) ctx
                .get(ServletActionContext.HTTP_RESPONSE);

        try {
            JSONObject json = JsonUtil.listToJson(courseInfoList);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void getTeacherInfo() {
        Map<String, String> teacherInfo = ((TrainApplyService) ibatisService)
                .getTeacherInfo(trainTeacher);
        ActionContext ctx = ActionContext.getContext();
        HttpServletResponse response = (HttpServletResponse) ctx
                .get(ServletActionContext.HTTP_RESPONSE);

        try {
            JSONObject json = JsonUtil.mapToJson(teacherInfo);
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(json.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Traincourse getTraincourse() {
        return traincourse;
    }

    public void setTraincourse(Traincourse traincourse) {
        this.traincourse = traincourse;
    }

    public List<Map<String, Object>> getCourseInfoList() {
        return courseInfoList;
    }

    public void setCourseInfoList(List<Map<String, Object>> courseInfoList) {
        this.courseInfoList = courseInfoList;
    }

    public TrainTeacher getTrainTeacher() {
        return trainTeacher;
    }

    public void setTrainTeacher(TrainTeacher trainTeacher) {
        this.trainTeacher = trainTeacher;
    }

}