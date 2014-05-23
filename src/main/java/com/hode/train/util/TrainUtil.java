package com.hode.train.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.framework.service.GlobeSpringContext;
import com.hode.rbac.action.RbacUtil;
import com.hode.rbac.model.GroupModel;
import com.hode.rbac.model.UserSessionModel;
import com.hode.train.model.ApplyExamineeModel;
import com.hode.train.model.ExamineePapersModel;
import com.hode.train.model.TrainApplyCourse;
import com.hode.train.model.TrainApplyDetailModel;
import com.hode.train.model.TrainApplyItem;
import com.hode.train.model.TrainApplyMaterial;
import com.hode.train.model.TrainApplyModel;
import com.hode.train.model.TrainApplyTeacher;
import com.hode.train.model.TrainCertLogModel;
import com.hode.train.model.TrainCertModel;
import com.hode.train.model.TrainCheckDetailModel;
import com.hode.train.model.TrainCheckModel;
import com.hode.train.model.TrainDictionaryTeacher;
import com.hode.train.model.TrainExamineeModel;
import com.hode.train.model.TrainItem;
import com.hode.train.model.TrainLogModel;
import com.hode.train.model.TrainMaterial;
import com.hode.train.model.TrainObject;
import com.hode.train.model.TrainPapersModel;
import com.hode.train.model.TrainPersonModel;
import com.hode.train.model.TrainScoreDetailModel;
import com.hode.train.model.TrainScoreModel;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.model.TrainStudentDetailScoreVO;
import com.hode.train.model.TrainStudentModel;
import com.hode.train.model.TrainTeacher;
import com.hode.train.model.TrainUnitModel;
import com.hode.train.model.TrainUserApplyDetailModel;
import com.hode.train.model.TrainUserApplyModel;
import com.hode.train.model.TrainUserChangeDetailModel;
import com.hode.train.model.TrainUserChangeModel;
import com.hode.train.model.Traincourse;
import com.hode.train.service.ApplyExamineeService;
import com.hode.train.service.ExamineePapersService;
import com.hode.train.service.TrainApplyCourseService;
import com.hode.train.service.TrainApplyDetailService;
import com.hode.train.service.TrainApplyItemService;
import com.hode.train.service.TrainApplyMaterialService;
import com.hode.train.service.TrainApplyService;
import com.hode.train.service.TrainApplyTeacherService;
import com.hode.train.service.TrainCertService;
import com.hode.train.service.TrainCheckDetailService;
import com.hode.train.service.TrainCheckService;
import com.hode.train.service.TrainExamineeService;
import com.hode.train.service.TrainLogService;
import com.hode.train.service.TrainPapersService;
import com.hode.train.service.TrainPersonService;
import com.hode.train.service.TrainScoreService;
import com.hode.train.service.TrainStudentService;
import com.hode.train.service.TrainUnitBaseService;
import com.hode.train.service.TrainUserApplyService;
import com.hode.train.service.TrainUserChangeService;

public class TrainUtil {
    private static TrainLogService trainLogService = (TrainLogService) (GlobeSpringContext
            .getApplicationContext().getBean("trainLogIbatisServiceProxy"));
    private static TrainApplyService trainApplyService = (TrainApplyService) (GlobeSpringContext
            .getApplicationContext().getBean("trainApplyIbatisServiceProxy"));
    private static TrainApplyDetailService trainApplyDetailService = (TrainApplyDetailService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainApplyDetailIbatisServiceProxy"));
    private static TrainStudentService trainStudentService = (TrainStudentService) (GlobeSpringContext
            .getApplicationContext().getBean("trainStudentIbatisServiceProxy"));
    private static TrainPersonService trainPersonService = (TrainPersonService) (GlobeSpringContext
            .getApplicationContext().getBean("trainPersonIbatisServiceProxy"));
    private static TrainCheckService trainCheckService = (TrainCheckService) (GlobeSpringContext
            .getApplicationContext().getBean("trainCheckIbatisServiceProxy"));
    private static TrainScoreService trainScoreService = (TrainScoreService) (GlobeSpringContext
            .getApplicationContext().getBean("trainScoreIbatisServiceProxy"));
    private static TrainUserApplyService trainUserApplyService = (TrainUserApplyService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainUserApplyIbatisServiceProxy"));
    private static TrainUserChangeService trainUserChangeService = (TrainUserChangeService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainUserChangeIbatisServiceProxy"));
    private static TrainCertService trainCertService = (TrainCertService) (GlobeSpringContext
            .getApplicationContext().getBean("trainCertIbatisServiceProxy"));
    private static TrainUnitBaseService trainUnitBaseService = (TrainUnitBaseService) (GlobeSpringContext
            .getApplicationContext().getBean("trainUnitBaseIbatisServiceProxy"));
    private static TrainApplyItemService trainApplyItemService = (TrainApplyItemService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainApplyItemIbatisServiceProxy"));
    private static TrainApplyMaterialService trainApplyMaterialService = (TrainApplyMaterialService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainApplyMaterialIbatisServiceProxy"));
    private static TrainApplyTeacherService trainApplyTeacherService = (TrainApplyTeacherService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainApplyTeacherIbatisServiceProxy"));
    private static TrainPapersService trainPapersService = (TrainPapersService) (GlobeSpringContext
            .getApplicationContext().getBean("trainPapersIbatisServiceProxy"));
    private static TrainExamineeService trainExamineeService = (TrainExamineeService) (GlobeSpringContext
            .getApplicationContext().getBean("trainExamineeIbatisServiceProxy"));
    private static ApplyExamineeService applyExamineeService = (ApplyExamineeService) (GlobeSpringContext
            .getApplicationContext().getBean("applyExamineeIbatisServiceProxy"));
    private static ExamineePapersService examineePapersService = (ExamineePapersService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("examineePapersIbatisServiceProxy"));
    private static TrainApplyCourseService trainApplyCourseService = (TrainApplyCourseService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainApplyCourseIbatisServiceProxy"));
    private static TrainCheckDetailService trainCheckDetailService = (TrainCheckDetailService) (GlobeSpringContext
            .getApplicationContext()
            .getBean("trainCheckDetailIbatisServiceProxy"));

    public static int intSessionMaxCertCode; // 证书编号
    public static float fltPassScore = 60;

    public static String[] getApplyFileNames(int intID) {
        String s = trainApplyService.getObjectInfo("getStrAttrContent", intID)
                .toString();
        if (!MyStringUtil.isNullStr(s)) {
            String[] files = s.split("\\|");
            String[] names = new String[files.length - 1];
            for (int i = 0; i < files.length - 1; i++) {
                String file = files[i + 1];
                String name = file.split("\\*")[0];
                name = name.substring(name.lastIndexOf("/") + 1, name.length());
                names[i] = name;
            }

            return names;
        }
        return null;
    }

    public static String[] getCheckFileNames(int intID) {
        String s = trainCheckService.getObjectInfo("getStrAttrContent", intID)
                .toString();
        if (!MyStringUtil.isNullStr(s)) {
            String[] files = s.split("\\|");
            String[] names = new String[files.length - 1];
            for (int i = 0; i < files.length - 1; i++) {
                String file = files[i + 1];
                String name = file.split("\\*")[0];
                name = name.substring(name.lastIndexOf("/") + 1, name.length());
                names[i] = name;
            }

            return names;
        }
        return null;
    }

    // /////////////////////////////////////办班申请信息 start
    public static TrainApplyModel getTrainApplyByID(int intID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();
        trainApplyModel.setIntID(intID);
        return (TrainApplyModel) trainApplyService
                .getObjectInfo(trainApplyModel);
    }

    // 基础信息导入
    @SuppressWarnings("rawtypes")
    public static List getTrainApplyListByGroupID(int intGroupID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();

        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainApplyModel.setIntCreateGroupID(intGroupID);
        }

        return trainApplyService.getObjectList("getTrainApplyListByGroupID",
                trainApplyModel);
    }

    // 考核申请
    @SuppressWarnings("rawtypes")
    public static List getTrainApplyListByGroupID2(int intGroupID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();

        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainApplyModel.setIntCreateGroupID(intGroupID);
        }

        return trainApplyService.getObjectList("getTrainApplyListByGroupID2",
                trainApplyModel);
    }

    // 图片信息导入
    @SuppressWarnings("rawtypes")
    public static List getTrainApplyListByGroupID3(int intGroupID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();

        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainApplyModel.setIntCreateGroupID(intGroupID);
        }

        return trainApplyService.getObjectList("getTrainApplyListByGroupID3",
                trainApplyModel);
    }

    // 人员信息校验
    @SuppressWarnings("rawtypes")
    public static List getTrainApplyListByGroupID4(int intGroupID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();

        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainApplyModel.setIntCreateGroupID(intGroupID);
        }

        return trainApplyService.getObjectList("getTrainApplyListByGroupID4",
                trainApplyModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainApplyListByIntTrainLow(int intTrainLow) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();
        trainApplyModel.setIntTrainLow(intTrainLow);
        return trainApplyService.getObjectList(
                "getTrainApplyListByintTrainLower", trainApplyModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainDetailApplyListByApplyID(int intTrainApplyID) {
        return trainApplyService.getObjectList("getDetailList", intTrainApplyID
                + "");
    }

    public static TrainApplyModel getTrainApplyByCode(String strTrainCount,
                                                      String strTrainCountClass) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();
        trainApplyModel.setStrTrainCount(strTrainCount);
        if (!MyStringUtil.isNullStr(strTrainCountClass)) {
            trainApplyModel.setStrTrainCountClass(strTrainCountClass);
        }
        return (TrainApplyModel) trainApplyService.getObjectInfo(
                "getTrainApplyByCode", trainApplyModel);
    }

    public static TrainUserApplyModel getTrainUserApplyByCode(String strCode) {
        TrainUserApplyModel trainUserApplyModel = new TrainUserApplyModel();
        trainUserApplyModel.setStrCode(strCode);
        return (TrainUserApplyModel) trainUserApplyService.getObjectInfo(
                "getTrainUserApplyByCode", trainUserApplyModel);
    }

    public static TrainUserChangeModel getTrainUserChangeByCode(String strCode) {
        TrainUserChangeModel trainUserChangeModel = new TrainUserChangeModel();
        trainUserChangeModel.setStrCode(strCode);
        return (TrainUserChangeModel) trainUserChangeService.getObjectInfo(
                "getTrainUserChangeByCode", trainUserChangeModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainUserApplyDetailByIntMainID(int intID) {
        TrainUserApplyDetailModel trainUserApplyDetailModel = new TrainUserApplyDetailModel();
        trainUserApplyDetailModel.setIntMainID(intID);
        return trainUserApplyService
                .getObjectList("getTrainUserApplyDetailByIntMainID",
                        trainUserApplyDetailModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainUserChangeDetailByIntMainID(int intID) {
        TrainUserChangeDetailModel trainUserChangeDetailModel = new TrainUserChangeDetailModel();
        trainUserChangeDetailModel.setIntMainID(intID);
        return trainUserChangeService.getObjectList(
                "getTrainUserChangeDetailByIntMainID",
                trainUserChangeDetailModel);
    }

    public static TrainStudentDetailModel getTrainStudentDetailByUserCode(
            String userCode) {
        TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
        trainStudentDetailModel.setStrStudentIDCode(userCode);
        return (TrainStudentDetailModel) trainStudentService.getObjectInfo(
                "getTrainStudentDetailByUserCode", trainStudentDetailModel);
    }

    public static TrainStudentModel getTrainStudentByIntMainID(int intMainID) {
        TrainStudentModel trainStudentModel = new TrainStudentModel();
        trainStudentModel.setIntID(intMainID);
        return (TrainStudentModel) trainStudentService.getObjectInfo(
                "getTrainStudentByIntMainID", trainStudentModel);
    }

    // /////////////////////////////////////办班申请信息 end

    // 办班申请：收费项目 start
    @SuppressWarnings("unchecked")
    public static List<TrainItem> trainItems(int intCreateUserId,
                                             int intCreateGroupId) {
        TrainItem ti = new TrainItem();
        ti.setIntCreateGroup(intCreateGroupId);
        ti.setIntCreateUser(intCreateUserId);
        return trainApplyItemService.getObjectList("getTrainItemList", ti);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainApplyItem> trainApplyItems(int trainApplyID) {
        TrainApplyItem tai = new TrainApplyItem();
        tai.setIntTrainApplyID(trainApplyID);
        return trainApplyItemService
                .getObjectList("getTrainApplyItemList", tai);
    }

    // 办班申请：收费项目 end

    // 办班申请：教材 start
    @SuppressWarnings("unchecked")
    public static List<TrainMaterial> trainMaterials(int intCreateUserId,
                                                     int intCreateGroupId, String intDictionary) {
        TrainMaterial tt = new TrainMaterial();
        tt.setIntCreateUser(intCreateUserId);
        tt.setIntDictionary(Integer.parseInt(intDictionary));
        tt.setIntCreateGroup(intCreateGroupId);
        return trainApplyMaterialService.getObjectList(tt);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainApplyMaterial> trainApplyMaterials(int trainApplyID) {
        TrainApplyMaterial tam = new TrainApplyMaterial();
        tam.setIntTrainApplyID(trainApplyID);
        return trainApplyMaterialService.getObjectList(
                "getTrainApplyMaterialList", tam);
    }

    // 办班申请：教材 end

    // 办班申请：教师 start
    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> trainTeachers(int intCreateUserId,
                                                   int intCreateGroupId) {
        TrainTeacher tt = new TrainTeacher();
        tt.setIntCreateUser(intCreateUserId);
        tt.setIntGroup(intCreateGroupId);
        return trainApplyTeacherService.getObjectList(tt);
    }

    // 办班申请：教师 end

    // 办班申请：课程 start
    @SuppressWarnings("unchecked")
    public static List<Traincourse> trainCourses(int intCreateUserId,
                                                 int intCreateGroupId, String intDictionary) {
        Traincourse tc = new Traincourse();
        tc.setIntCreateUser(intCreateUserId);
        tc.setIntCreateGroup(intCreateGroupId);
        tc.setIntDictionary(Integer.parseInt(intDictionary));
        return trainApplyCourseService.getObjectList(tc);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainApplyCourse> trainApplyCourses(int trainApplyID) {
        TrainApplyDetailModel tadl = new TrainApplyDetailModel();
        tadl.setIntTrainApplyID(trainApplyID);
        return trainApplyDetailService.getObjectList("getTrainApplyCourseList",
                tadl);
    }

    // 办班申请：课程 end

    // 考核修改：课程start
    @SuppressWarnings("unchecked")
    public static List<TrainCheckDetailModel> trainCheckCourses(int trainCheckID) {
        TrainCheckDetailModel tc = new TrainCheckDetailModel();
        tc.setIntTrainCheckID(trainCheckID);
        return trainCheckDetailService.getObjectList("getTrainCheckCourseList",
                tc);
    }

    // 考核修改：课程end

    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> trainApplyTeacherList(int trainApplyID) {
        TrainApplyTeacher tat = new TrainApplyTeacher();
        tat.setIntTrainApplyID(trainApplyID);
        return trainApplyTeacherService.getObjectList(
                "getTrainApplyTeacherList", tat);
    }

    // 办班申请：教师-字典 start
    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> trainTeachers2(int intCreateUserId,
                                                    int intCreateGroupId, String strDictionary) {
        TrainDictionaryTeacher tt = new TrainDictionaryTeacher();
        tt.setIntCreateUser(intCreateUserId);
        tt.setIntCreateGroup(intCreateGroupId);
        tt.setIntDictionary(Integer.parseInt(strDictionary));
        return trainApplyTeacherService.getObjectList("getObjectList2", tt);
    }

    // 办班申请：教师-字典 end

    @SuppressWarnings("unchecked")
    public static List<TrainApplyItem> getTrainItemListByApplyID(int applyID) {
        TrainApplyItem ti = new TrainApplyItem();
        ti.setIntTrainApplyID(applyID);
        List<TrainApplyItem> returnLs = trainApplyService.getObjectList(
                "getTrainItemListByApplyID", ti);
        TrainApplyItem tt = new TrainApplyItem();
        tt.setStrItemName("合计");
        returnLs.add(tt);
        return returnLs;
    }

    @SuppressWarnings("unchecked")
    public static List<TrainApplyMaterial> getTrainMaterialListByApplyID(
            int applyID) {
        TrainApplyMaterial tm = new TrainApplyMaterial();
        tm.setIntTrainApplyID(applyID);
        return trainApplyService.getObjectList("getTrainMaterialListByApplyID",
                tm);

    }

    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> getTrainTeacherListByApplyID(int applyID) {
        TrainApplyTeacher tm = new TrainApplyTeacher();
        tm.setIntTrainApplyID(applyID);
        return trainApplyService.getObjectList("getTrainTeacherListByApplyID",
                tm);

    }

    @SuppressWarnings("unchecked")
    public static List<TrainApplyCourse> getTrainCourseListByApplyID(int applyID) {
        TrainApplyCourse tac = new TrainApplyCourse();
        tac.setIntTrainApplyID(applyID);
        return trainApplyService.getObjectList("getTrainCourseListByApplyID",
                tac);

    }

    // /////////////////////////////////////学员信息上报审查 start
    @SuppressWarnings("rawtypes")
    public static List getStudentDetailListByMainID(int intMainID,
                                                    String strTrainCount) {
        TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
        trainStudentDetailModel.setIntMainID(intMainID);// 主表
        trainStudentDetailModel.setStrTrainCount(strTrainCount);// 期次
        return trainStudentService.getObjectList(
                "getStudentDetailListByMainID", trainStudentDetailModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getStudentDetailListByMainIDView(int intMainID,
                                                        String strTrainCount, int intIsLeader) {
        TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
        trainStudentDetailModel.setIntMainID(intMainID);// 主表
        // trainStudentDetailModel.setStrTrainCount(strTrainCount);// 期次
        if (intIsLeader == 1) {
            trainStudentDetailModel.setIntIsLeader(intIsLeader);
        }
        return trainStudentService.getObjectList(
                "getStudentDetailListByMainIDView", trainStudentDetailModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getStudentListByGroupID(int intGroupID) {
        TrainStudentModel trainStudentModel = new TrainStudentModel();
        trainStudentModel.setIntCreateGroupID(intGroupID);
        return trainStudentService.getObjectList("getStudentListByGroupID",
                trainStudentModel);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List searchStaticReport3(String strTrainStartDate,
                                           String strTrainEndDate, String strTel) {
        List rtnList = new ArrayList();
        List tmpList = RbacUtil.getChildGroupListByFatherId(11, 0);

        if (tmpList != null && tmpList.size() > 0) {
            TrainStudentModel trainStudentModel = new TrainStudentModel();
            trainStudentModel.setStrTrainStartDate(strTrainStartDate);
            trainStudentModel.setStrTrainEndDate(strTrainEndDate);
            List tmpList2 = trainStudentService.getObjectList(
                    "searchStaticReport3", trainStudentModel);
            for (int m = 0; m < tmpList.size(); m++) {
                GroupModel groupModel = (GroupModel) tmpList.get(m);

                // 指定级别查询
                if (!MyStringUtil.isNullStr(strTel)
                        && !strTel.trim().equals("all")) {
                    if (!groupModel.getStrTel().equals(strTel)) {
                        continue;
                    }
                }

                int intTotalA1 = 0;// 培训---批次
                int intTotalA2 = 0;// 培训---培训人数
                int intTotalA3 = 0;// 培训---发证人数
                String strTrainCountA = "";

                int intTotalB1 = 0;// 再培训---批次
                int intTotalB2 = 0;// 再培训---培训人数
                int intTotalB3 = 0;// 再培训---合格人数
                String strTrainCountB = "";

                if (tmpList2 != null && tmpList2.size() > 0) {
                    for (int j = 0; j < tmpList2.size(); j++) {
                        trainStudentModel = (TrainStudentModel) tmpList2.get(j);
                        if (groupModel.getIntID() == trainStudentModel
                                .getIntCreateGroupID()) {
                            if (trainStudentModel.getStrTrainObjectDesc()
                                    .equals("安全生产管理人员")) {
                                intTotalA2 = intTotalA2
                                        + trainStudentModel.getIntID();
                                if (trainStudentModel.getIntCertAllow_search() == 1) {
                                    intTotalA3 = intTotalA3
                                            + trainStudentModel.getIntID();
                                }
                                if (!strTrainCountA.equals(trainStudentModel
                                        .getStrTrainCount())) {
                                    intTotalA1 = intTotalA1 + 1;
                                    strTrainCountA = trainStudentModel
                                            .getStrTrainCount();
                                }
                            } else if (trainStudentModel
                                    .getStrTrainObjectDesc().indexOf("班组长") > -1) {
                                intTotalB2 = intTotalB2
                                        + trainStudentModel.getIntID();
                                if (trainStudentModel.getIntCertAllow_search() == 1) {
                                    intTotalB3 = intTotalB3
                                            + trainStudentModel.getIntID();
                                }
                                if (!strTrainCountB.equals(trainStudentModel
                                        .getStrTrainCount())) {
                                    intTotalB1 = intTotalB1 + 1;
                                    strTrainCountB = trainStudentModel
                                            .getStrTrainCount();
                                }
                            }
                        }
                    }
                }
                Map tmpMap = new HashMap();
                tmpMap.put("1", groupModel.getStrTel());// 级别
                tmpMap.put("2", groupModel.getStrName());
                tmpMap.put("11", intTotalA1 + intTotalB1);
                tmpMap.put("12", intTotalA2 + intTotalB2);

                tmpMap.put("21", intTotalA1);
                tmpMap.put("22", intTotalA2);
                tmpMap.put("23", intTotalA3);

                tmpMap.put("24", intTotalB1);
                tmpMap.put("25", intTotalB2);
                tmpMap.put("26", intTotalB3);

                rtnList.add(tmpMap);
            }
        }
        return rtnList;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List searchStaticReport4(String strTrainStartDate,
                                           String strTrainEndDate, String strTel) {
        List rtnList = new ArrayList();
        List tmpList = RbacUtil.getChildGroupListByFatherId(11, 0);
        if (tmpList != null && tmpList.size() > 0) {
            TrainStudentModel trainStudentModel = new TrainStudentModel();
            trainStudentModel.setStrTrainStartDate(strTrainStartDate);
            trainStudentModel.setStrTrainEndDate(strTrainEndDate);
            List tmpList2 = trainStudentService.getObjectList(
                    "searchStaticReport4", trainStudentModel);
            for (int m = 0; m < tmpList.size(); m++) {

                GroupModel groupModel = (GroupModel) tmpList.get(m);

                // 指定级别查询
                if (!MyStringUtil.isNullStr(strTel)
                        && !strTel.trim().equals("all")) {
                    if (!groupModel.getStrTel().equals(strTel)) {
                        continue;
                    }
                }

                int intTotalA1 = 0;// 培训---考试人数
                int intTotalA2 = 0;// 培训---当期考试人数
                int intTotalA3 = 0;// 培训---补考人数
                int intTotalA4 = 0;// 培训---合格人数
                int intTotalA5 = 0;// 培训---违纪人数
                int intTotalA6 = 0;// 培训---督导人数

                int intTotalB1 = 0;// 再培训---考试人数
                int intTotalB2 = 0;// 再培训---当期考试人数
                int intTotalB3 = 0;// 再培训---补考人数
                int intTotalB4 = 0;// 再培训---合格人数
                int intTotalB5 = 0;// 再培训---违纪人数
                int intTotalB6 = 0;// 再培训---督导人数

                if (tmpList2 != null && tmpList2.size() > 0) {
                    for (int j = 0; j < tmpList2.size(); j++) {
                        trainStudentModel = (TrainStudentModel) tmpList2.get(j);
                        if (groupModel.getIntID() == trainStudentModel
                                .getIntCreateGroupID()) {
                            if (trainStudentModel.getStrTrainType()
                                    .equals("培训")) {
                                intTotalA1 = intTotalA1
                                        + trainStudentModel.getIntID();// 考试人数
                                if (trainStudentModel.getIntCertAllow_search() == 1) {
                                    intTotalA4 = intTotalA4
                                            + trainStudentModel.getIntID();// 合格人数
                                }
                                intTotalA2 = intTotalA2
                                        + trainStudentModel
                                        .getIntCertDQ_search();// 当期考试人数
                                intTotalA3 = intTotalA3
                                        + trainStudentModel
                                        .getIntCertBK_search();// 补考人数
                                intTotalA6 = intTotalA6
                                        + trainStudentModel.getIntIsLeader();// 督导人数
                                intTotalA5 = intTotalA5
                                        + trainStudentModel
                                        .getIntCertWJ1_search()
                                        + trainStudentModel
                                        .getIntCertWJ2_search();// 违纪人数
                            } else {
                                intTotalB1 = intTotalB1
                                        + trainStudentModel.getIntID();// 考试人数
                                if (trainStudentModel.getIntCertAllow_search() == 1) {
                                    intTotalB4 = intTotalB4
                                            + trainStudentModel.getIntID();// 合格人数
                                }
                                intTotalB2 = intTotalB2
                                        + trainStudentModel
                                        .getIntCertDQ_search();// 当期考试人数
                                intTotalB3 = intTotalB3
                                        + trainStudentModel
                                        .getIntCertBK_search();// 补考人数
                                intTotalB6 = intTotalB6
                                        + trainStudentModel.getIntIsLeader();// 督导人数
                                intTotalB5 = intTotalB5
                                        + trainStudentModel
                                        .getIntCertWJ1_search()
                                        + trainStudentModel
                                        .getIntCertWJ2_search();// 违纪人数
                            }
                        }
                    }
                }
                Map tmpMap = new HashMap();
                tmpMap.put("1", groupModel.getStrTel());// 级别
                tmpMap.put("2", groupModel.getStrName());
                tmpMap.put("3", intTotalA1);
                tmpMap.put("4", intTotalB1);

                tmpMap.put("11", intTotalA1 + intTotalB1);// 培训人数
                tmpMap.put("12", intTotalA1);// 其中培训人数
                tmpMap.put("13", intTotalB1);// 其中再培训人数
                tmpMap.put("14", intTotalA6 + intTotalB6);// 督导处理人数
                tmpMap.put("15", intTotalA1 + intTotalB1);// 考试人数

                tmpMap.put("21", intTotalA2);// 当期考试人数
                tmpMap.put("22", intTotalA3);// 补考人数
                tmpMap.put("23", intTotalA4);// 合格人数
                tmpMap.put("24", intTotalA5);// 违纪人数
                tmpMap.put("25", StringUtil.getDoubleDivByString(intTotalA4
                        + "", intTotalA1 + "", true, "", "%", "0.00"));

                tmpMap.put("31", intTotalB2);// 当期考试人数
                tmpMap.put("32", intTotalB3);// 补考人数
                tmpMap.put("33", intTotalB4);// 合格人数
                tmpMap.put("34", intTotalB5);// 违纪人数
                tmpMap.put("35", StringUtil.getDoubleDivByString(intTotalB4
                        + "", intTotalB1 + "", true, "", "%", "0.00"));

                rtnList.add(tmpMap);
            }
        }
        return rtnList;
    }

    // /////////////////////////////////////学员信息上报审查 end

    // /////////////////////////////////////考核申请信息 start
    public static TrainCheckModel getTrainCheckByID(int intID) {
        TrainCheckModel trainCheckModel = new TrainCheckModel();
        trainCheckModel.setIntID(intID);
        return (TrainCheckModel) trainCheckService
                .getObjectInfo(trainCheckModel);
    }

    public static TrainCheckModel getTrainCheckByCode(String strTrainCount) {
        TrainCheckModel trainCheckModel = new TrainCheckModel();
        trainCheckModel.setStrTrainCount(strTrainCount);
        return (TrainCheckModel) trainCheckService.getObjectInfo(
                "getTrainCheckByCode", trainCheckModel);
    }

    // 成绩导入
    @SuppressWarnings("rawtypes")
    public static List getTrainCheckListByGroupID(int intGroupID, int userID) {
        TrainCheckModel trainCheckModel = new TrainCheckModel();
        if (userID == 28 || userID == 1) {
            // 如果是系统管理员或者成绩管理员，得到所有的可导入成绩的已审批考核
            return trainCheckService.getObjectList("getTrainCheckList",
                    trainCheckModel);
        } else {
            trainCheckModel.setIntCreateGroupID(intGroupID);
            return trainCheckService.getObjectList(
                    "getTrainCheckListByGroupID", trainCheckModel);
        }
    }

    // 补考成绩导入
    @SuppressWarnings("rawtypes")
    public static List getTrainCheckListByGroupID2(int intGroupID, int userID) {
        TrainCheckModel trainCheckModel = new TrainCheckModel();
        if (userID == 28 || userID == 1) {
            // 如果是系统管理员或者成绩管理员，得到所有的可导入成绩的已审批考核
            return trainCheckService.getObjectList("getTrainCheckList2",
                    trainCheckModel);
        } else {
            trainCheckModel.setIntCreateGroupID(intGroupID);
            return trainCheckService.getObjectList(
                    "getTrainCheckListByGroupID2", trainCheckModel);
        }
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainDetailCheckListByCheckID(int intTrainCheckID) {
        return trainCheckService.getObjectList("getDetailList", intTrainCheckID
                + "");
    }

    // /////////////////////////////////////考核申请信息 end

    public static boolean checkScore(float fltScore1, float fltScore2) {
        boolean rtnFlag = false;
        if (fltScore1 >= fltPassScore) {
            rtnFlag = true;
        }
        if (fltScore2 >= fltPassScore) {
            rtnFlag = true;
        }
        return rtnFlag;
    }

    //
    // /////////////////////////////////////考试 start
    @SuppressWarnings("rawtypes")
    public static List getScoreDetailListByMainID(int intMainID) {
        TrainScoreDetailModel trainScoreDetailModel = new TrainScoreDetailModel();
        trainScoreDetailModel.setIntMainID(intMainID);
        return trainScoreService.getObjectList("getScoreDetailListByMainID",
                trainScoreDetailModel);
    }

    // /////////////////////////////////////考试 end

    // ///////////////////////////////////////黑名单 start
    // public static int getIntIsBlack1(String strStudentIDCode)
    // {
    // TrainUserBlackModel trainUserBlackModel = new TrainUserBlackModel();
    // trainUserBlackModel.setStrStudentIDCode(strStudentIDCode);
    // trainUserBlackModel.setStrEndDate(DateUtil.getNowDateByFormat("yyyy-MM-dd"));
    //
    // return
    // trainUserBlackService.getRecordCount("getIntIsBlack",trainUserBlackModel);
    // }
    // ///////////////////////////////////////黑名单 end

    // /////////////////////////////////////证书 start
    public static void updateCertCode(int intID, int intCertCode,
                                      String strStudentCertCode, String strStudentIDCode) {
        int intYear = StringUtil.ObjectToInt(DateUtil
                .getNowDateByFormat("yyyy"));
        String strToDay = DateUtil.getNowDateByFormat("yyyy-MM-dd");
        if (intSessionMaxCertCode == 0) {
            intSessionMaxCertCode = trainCertService.getRecordCount(
                    "getMaxCertCode", intYear);
        }

        // 如果没有生成过证书号码或者导入参培人员时没有证书号码，按照规则生成证书号
        if (intCertCode == 0) {
            intSessionMaxCertCode = intSessionMaxCertCode + 1;
            TrainCertModel trainCertModel = new TrainCertModel();
            trainCertModel.setIntID(intID);
            trainCertModel.setIntCertCode_1(intSessionMaxCertCode);
            trainCertModel.setIntCertYear_1(intYear);
            String strCertCode = "";

            // 如果在导入参培人员时填写了证书号码，则使用填写的号码
            if (!MyStringUtil.isNullStr(strStudentCertCode)) {
                strCertCode = strStudentCertCode;
            } else {
                strCertCode += DateUtil.getNowDateByFormat("yy");// 第1、2位为发证年份后两位数字
                strCertCode += trainCertService.getRecordCount(
                        "getMaxCertCount", strStudentIDCode);// 第3位代表证书延期情况（简称延期码），0代表初次领证，1代表第1次换证，依此类推。
                strCertCode += "41";// 第4、5位为地区码。河南省为41。
                strCertCode += "00";// 第6、7位为类型码，安全生产管理人员为02。煤矿主要负责人为01，其他几个暂定为03、04、05、06依次排列。
                strCertCode += StringUtil.getZeroFill(intSessionMaxCertCode
                        + "", 7);// 第8-14位为流水号，一年一更新。
            }

            trainCertModel.setStrCertCode_1(strCertCode);
            trainCertModel.setStrCertDate_1(strToDay);
            trainCertService.updateObject("updateCertCode", trainCertModel);
        }

    }

    // /////////////////////////////////////黑名单 end

    // //////////////////////////////////////////////////////////////////////////////////人员变更及补正
    @SuppressWarnings("rawtypes")
    public static List getUserApplyDetailListByMainID(int intMainID) {
        TrainUserApplyDetailModel trainUserApplyDetailModel = new TrainUserApplyDetailModel();
        trainUserApplyDetailModel.setIntMainID(intMainID);
        return trainUserApplyService.getObjectList("getDetailList",
                trainUserApplyDetailModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getUserChangeDetailListByMainID(int intMainID) {
        TrainUserChangeDetailModel trainUserChangeDetailModel = new TrainUserChangeDetailModel();
        trainUserChangeDetailModel.setIntMainID(intMainID);
        return trainUserChangeService.getObjectList("getDetailList",
                trainUserChangeDetailModel);
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static List getUserApplyAndChangeList(String strStartDate,
                                                 String strEndDate) {

        List rtnList = new ArrayList();
        TrainUserApplyModel trainUserApplyModel = new TrainUserApplyModel();
        trainUserApplyModel.setStrSearchStartDate(strStartDate);
        trainUserApplyModel.setStrSearchEndDate(strEndDate);
        List applyList = trainUserApplyService.getObjectList("getAllList",
                trainUserApplyModel);
        int i = 0;
        if (applyList != null && applyList.size() > 0) {
            i = applyList.size();
        }

        TrainUserChangeModel trainUserChangeModel = new TrainUserChangeModel();
        trainUserChangeModel.setStrSearchStartDate(strStartDate);
        trainUserChangeModel.setStrSearchEndDate(strEndDate);
        List changeList = trainUserChangeService.getObjectList("getAllList",
                trainUserChangeModel);
        int j = 0;
        if (changeList != null && changeList.size() > 0) {
            j = changeList.size();
        }
        System.out.println(i + "-------------------------------" + j);
        if (i >= j) {
            for (int k = 0; k < j; k++) {
                Map tmpMap1 = new HashMap();
                tmpMap1.put("1",
                        ((TrainUserApplyModel) applyList.get(k)).getStrCode());
                tmpMap1.put(
                        "2",
                        ((TrainUserApplyModel) applyList.get(k))
                                .getIntApplyNum() + "");

                tmpMap1.put("11",
                        ((TrainUserChangeModel) changeList.get(k)).getStrCode());
                tmpMap1.put(
                        "12",
                        ((TrainUserChangeModel) changeList.get(k))
                                .getIntApplyNum() + "");
                rtnList.add(tmpMap1);
            }
            for (int k = j; k < i; k++) {
                Map tmpMap2 = new HashMap();
                tmpMap2.put("1",
                        ((TrainUserApplyModel) applyList.get(k)).getStrCode());
                tmpMap2.put(
                        "2",
                        ((TrainUserApplyModel) applyList.get(k))
                                .getIntApplyNum() + "");

                tmpMap2.put("11", "&nbsp;");
                tmpMap2.put("12", "&nbsp;");
                rtnList.add(tmpMap2);
            }
        } else if (j >= i) {
            for (int k = 0; k < i; k++) {
                Map tmpMap3 = new HashMap();
                tmpMap3.put("1",
                        ((TrainUserApplyModel) applyList.get(k)).getStrCode());
                tmpMap3.put(
                        "2",
                        ((TrainUserApplyModel) applyList.get(k))
                                .getIntApplyNum() + "");

                tmpMap3.put("11",
                        ((TrainUserChangeModel) changeList.get(k)).getStrCode());
                tmpMap3.put(
                        "12",
                        ((TrainUserChangeModel) changeList.get(k))
                                .getIntApplyNum() + "");
                rtnList.add(tmpMap3);
            }
            for (int k = i; k < j; k++) {
                Map tmpMap4 = new HashMap();
                tmpMap4.put("1", "&nbsp;");
                tmpMap4.put("2", "&nbsp;");

                tmpMap4.put("11",
                        ((TrainUserChangeModel) changeList.get(k)).getStrCode());
                tmpMap4.put(
                        "12",
                        ((TrainUserChangeModel) changeList.get(k))
                                .getIntApplyNum() + "");
                rtnList.add(tmpMap4);
            }
        }
        return rtnList;
    }

    // /////////////////////////////////////日志 start
    public static void insertLog(int intCheckFlag, int intInfoID,
                                 String strTableName, String strOptContent,
                                 UserSessionModel userSessionModel) {
        TrainLogModel trainLogModel = new TrainLogModel();
        trainLogModel.setIntInfoID(intInfoID);
        trainLogModel.setIntCheckFlag(intCheckFlag);
        trainLogModel.setStrTableName(strTableName);
        trainLogModel.setStrOptDate(DateUtil.getNowDateByFormat(""));
        trainLogModel.setStrOptContent(strOptContent);

        trainLogModel.setIntOptUserID(userSessionModel.getIntUserID());
        trainLogModel.setStrOptUserName(userSessionModel.getStrName());
        trainLogModel.setIntOptGroupID(userSessionModel.getIntGroupID());
        trainLogModel.setStrOptGroupName(userSessionModel.getStrGroupName());
        trainLogModel.setIntOptUnitID(userSessionModel.getIntUnitID());
        trainLogModel.setStrOptUnitName(userSessionModel.getStrUnitName());
        trainLogService.insertObject(trainLogModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getObjectLogList(int intInfoID, String strTableName) {
        TrainLogModel trainLogModel = new TrainLogModel();
        trainLogModel.setIntInfoID(intInfoID);
        trainLogModel.setStrTableName(strTableName);
        return trainLogService.getObjectList(trainLogModel);

    }

    // /////////////////////////////////////日志 end

    @SuppressWarnings("rawtypes")
    public static List getTrainUnitBaseListByMainID(int intMainID) {
        return trainUnitBaseService.getObjectList("getDetailList", intMainID
                + "");
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainPapers(TrainPapersModel model) {
        return trainPapersService.getObjectList(model);
    }

    @SuppressWarnings("rawtypes")
    public static List checkIdentity(String identity) {
        TrainExamineeModel model = new TrainExamineeModel();
        model.setStrIdentity(identity);
        List list = new ArrayList();
        list = trainExamineeService.getObjectList(model);
        return list;
    }

    public static void insertApplyExaminee(ApplyExamineeModel model) {
        applyExamineeService.insertObjectAndGetID(model);
    }

    @SuppressWarnings("rawtypes")
    public static List getObjectListByTrainApplyID(ApplyExamineeModel model) {
        List list = new ArrayList();
        list = applyExamineeService.getObjectList(
                "getObjectListByTrainApplyID", model);
        return list;
    }

    public static void insertExaminee(TrainExamineeModel trainExamineeModel) {
        trainExamineeService.insertExaminee(trainExamineeModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getAllPapers() {
        TrainPapersModel model = new TrainPapersModel();
        return trainPapersService.getObjectList(model);
    }

    public static void updateExamineePart(TrainExamineeModel model) {
        trainPapersService.updateObject("updateExamineePart", model);
    }

    @SuppressWarnings("rawtypes")
    public static List getExamineePapersList(ExamineePapersModel model) {
        return examineePapersService.getObjectList("getExamineePapersList",
                model);
    }

    public static void insertPapers(TrainPapersModel trainPapersModel) {
        trainPapersService.insertObjectAndGetID(trainPapersModel);
    }

    public static TrainStudentDetailModel getStudentDetailByIntID(int intID) {
        return (TrainStudentDetailModel) trainStudentService.getObjectInfo(
                "getStudentDetailByIntID", intID);
    }

    public static TrainApplyModel getApplyByTrainCount(String strTrainCount) {
        TrainApplyModel trainApplyModel = (TrainApplyModel) trainApplyService
                .getObjectInfo("getTrainApplyByTrainCount", strTrainCount);
        return trainApplyModel;
    }

    @SuppressWarnings("rawtypes")
    public static List getApplyDetailByTrainCount(String strTrainCount) {
        // 根据期次得到培训的intID
        int intID = (Integer) trainApplyService.getObjectInfo(
                "getIntIDByTrainCount", strTrainCount);

        // 根据培训的intID得到培训的详细信息
        TrainApplyDetailModel trainApplyDetailModel = new TrainApplyDetailModel();
        trainApplyDetailModel.setIntTrainApplyID(intID);
        List list = trainApplyDetailService.getObjectList(
                "getTrainApplyCourseList", trainApplyDetailModel);
        return list;
    }

    @SuppressWarnings("rawtypes")
    public static List getCheckDetailByTrainApplyID(int intTrainApplyID) {
        // 根据期次得到培训的intID
        int intID = (Integer) trainCheckService.getObjectInfo(
                "getCheckDetailByTrainApplyID", intTrainApplyID);

        // 根据培训的intID得到培训的详细信息
        TrainCheckDetailModel trainCheckDetailModel = new TrainCheckDetailModel();
        trainCheckDetailModel.setIntTrainCheckID(intID);
        List list = trainCheckDetailService.getObjectList(
                "getTrainCheckCourseList", trainCheckDetailModel);
        return list;
    }

    // 取得所有的培训机构
    @SuppressWarnings("rawtypes")
    public static List getAllTrainUnit() {
        TrainUnitModel trainUnitModel = new TrainUnitModel();
        trainUnitModel.setIntFatherID(11);
        trainUnitModel.setIntIsDelete(0);

        List list = trainStudentService.getObjectList("getAllTrainUnit",
                trainUnitModel);
        return list;
    }

    // 取得所有的培训机构
    @SuppressWarnings("rawtypes")
    public static List getTrainUnitByGroupID(int intGroupID) {
        TrainUnitModel trainUnitModel = new TrainUnitModel();
        trainUnitModel.setIntFatherID(11);
        trainUnitModel.setIntIsDelete(0);
        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainUnitModel.setIntID(intGroupID);
        }
        List list = trainStudentService.getObjectList("getTrainUnitByGroupID",
                trainUnitModel);
        return list;
    }

    public static TrainPersonModel checkPersonRecord(
            TrainStudentDetailModel trainStudentDetailModel) {
        return (TrainPersonModel) trainStudentService.getObjectInfo(
                "checkPersonRecord", trainStudentDetailModel);
    }

    // 网上报名，向数据库中插入学员信息
    public static String signUp(Map<String, String> map) {
        String strStudentIDCode = map.get("studentIDCode");
        // 判断 数据库中有没有此身份证号的未处理的网上报名信息
        int studentNum = trainStudentService.getRecordCount(
                "getSignUpCountByIDCode", strStudentIDCode);
        if (studentNum > 0) {
            return "exists";
        } else {
            return processSignUpInfo(map);
        }

    }

    public static String processSignUpInfo(Map<String, String> map) {
        TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();

        trainStudentDetailModel.setIntIsSignUp(1);
        trainStudentDetailModel.setStrTrainUnitName(map.get("trainUnit"));
        trainStudentDetailModel.setStrTrainType(map.get("trainType"));
        trainStudentDetailModel.setStrStudentPost(map.get("studentPost"));
        trainStudentDetailModel.setStrStudentTitles(map.get("studentTitle"));
        trainStudentDetailModel.setStrStudentName(map.get("studentName"));
        trainStudentDetailModel.setStrStudentSex(map.get("studentSex"));
        trainStudentDetailModel.setStrStudentPic1(map.get("studentPic1"));
        trainStudentDetailModel.setStrStudentPic2(map.get("studentPic2"));
        trainStudentDetailModel.setStrStudentPic3(map.get("studentPic3"));
        trainStudentDetailModel.setStrStudentPic4(map.get("studentPic4"));

        String strStudentAge = map.get("strStudentAge");
        int intStudentAge = 0;
        if (!MyStringUtil.isNullStr(strStudentAge)) {
            try {
                intStudentAge = Integer.parseInt(strStudentAge);
                if (intStudentAge < 0) {
                    intStudentAge = 0;
                }
            } catch (NumberFormatException e) {
                System.out.println("年龄格式错误，必须为正整数！！");
            }
        }

        trainStudentDetailModel.setIntStudentAge(intStudentAge);
        trainStudentDetailModel.setStrStudentIDAddr(map.get("studentIDAddr"));
        trainStudentDetailModel.setStrStudentDegree(map.get("studentDegree"));
        trainStudentDetailModel.setStrStudentUnitType(map
                .get("studentUnitType"));
        trainStudentDetailModel.setStrStudentRelation(map
                .get("studentRelation"));
        trainStudentDetailModel.setStrStudentCertCode(map
                .get("studentCertCode"));
        trainStudentDetailModel.setStrStudentFirstDate(map.get("firstDate"));
        trainStudentDetailModel.setStrStudentViolate(map.get("studentViolate"));

        // 根据身份证号得到出生日期
        String strBirthDay = "1900-00-00";
        String strIDCard = map.get("studentIDCode");
        if (strIDCard.length() == 18) {
            strBirthDay = strIDCard.substring(6, 10) + "-"
                    + strIDCard.substring(10, 12) + "-"
                    + strIDCard.substring(12, 14);
        } else if (strIDCard.length() == 15) {
            strBirthDay = "19" + strIDCard.substring(6, 8) + "-"
                    + strIDCard.substring(8, 10) + "-"
                    + strIDCard.substring(10, 12);
        }

        trainStudentDetailModel.setStrBirthDay(strBirthDay);
        trainStudentDetailModel.setStrStudentIDCode(strIDCard);

        try {
            trainStudentService.insertObject("signUp", trainStudentDetailModel);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }
    }

    public static List<TrainStudentDetailModel> signUpBatch(
            List<Map<String, String>> list) {
        // 档案库中已经存在的人员信息
        List<TrainStudentDetailModel> existStudents = new ArrayList<TrainStudentDetailModel>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                Map<String, String> map = list.get(i);
                String studentIDCode = map.get("studentIDCode");
                TrainStudentDetailModel student = (TrainStudentDetailModel) trainStudentService
                        .getObjectInfo("getSignUpDetailByIDCode", studentIDCode);

                if (student == null) {

                    TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();

                    trainStudentDetailModel.setIntIsSignUp(1);
                    trainStudentDetailModel.setStrTrainUnitName(map
                            .get("trainUnit"));
                    trainStudentDetailModel.setStrStudentPost(map
                            .get("studentPost"));
                    trainStudentDetailModel.setStrStudentTitles(map
                            .get("studentTitle"));
                    trainStudentDetailModel.setStrStudentName(map
                            .get("studentName"));
                    trainStudentDetailModel.setStrStudentSex(map
                            .get("studentSex"));
                    trainStudentDetailModel.setStrStudentPic1(map
                            .get("studentPic1"));
                    trainStudentDetailModel.setStrStudentPic2(map
                            .get("studentPic2"));
                    trainStudentDetailModel.setStrStudentPic3(map
                            .get("studentPic3"));
                    trainStudentDetailModel.setStrStudentPic4(map
                            .get("studentPic4"));

                    String strStudentAge = map.get("strStudentAge");
                    int intStudentAge = 0;
                    if (!MyStringUtil.isNullStr(strStudentAge)) {
                        try {
                            intStudentAge = Integer.parseInt(strStudentAge);
                            if (intStudentAge < 0) {
                                intStudentAge = 0;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("年龄格式错误，必须为正整数！！");
                        }
                    }

                    trainStudentDetailModel.setIntStudentAge(intStudentAge);
                    trainStudentDetailModel.setStrStudentIDCode(map
                            .get("studentIDCode"));
                    trainStudentDetailModel.setStrStudentIDAddr(map
                            .get("studentIDAddr"));
                    trainStudentDetailModel.setStrStudentDegree(map
                            .get("studentDegree"));
                    trainStudentDetailModel.setStrStudentUnitType(map
                            .get("studentUnitType"));
                    trainStudentDetailModel.setStrStudentRelation(map
                            .get("studentRelation"));
                    trainStudentDetailModel.setStrStudentCertCode(map
                            .get("studentCertCode"));
                    trainStudentDetailModel.setStrStudentFirstDate(map
                            .get("firstDate"));
                    trainStudentDetailModel.setStrStudentViolate(map
                            .get("studentViolate"));

                    // 根据身份证号得到出生日期
                    String strBirthDay = "1900-00-00";
                    String strIDCard = map.get("studentIDCode");
                    if (strIDCard.length() == 18) {
                        strBirthDay = strIDCard.substring(6, 10) + "-"
                                + strIDCard.substring(10, 12) + "-"
                                + strIDCard.substring(12, 14);
                    } else if (strIDCard.length() == 15) {
                        strBirthDay = "19" + strIDCard.substring(6, 8) + "-"
                                + strIDCard.substring(8, 10) + "-"
                                + strIDCard.substring(10, 12);
                    }

                    trainStudentDetailModel.setStrBirthDay(strBirthDay);

                    trainStudentDetailModel.setStrTrainType(map
                            .get("trainType2"));

                    // 检查人员档案库中是否存在该人的信息
                    TrainPersonModel person = (TrainPersonModel) trainStudentService
                            .getObjectInfo("checkPersonRecord",
                                    trainStudentDetailModel);
                    if (person != null) {
                        // 档案库中存在此人的信息
                        // 把此人的信息存放在existStudents中，不插入数据库
                        existStudents.add(trainStudentDetailModel);
                        continue;
                    } else {
                        try {
                            trainStudentService.insertObject("signUp",
                                    trainStudentDetailModel);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }

        return existStudents;
    }

    public static String getGroupNameById(int intID) {
        return trainStudentService.getObjectInfo("getGroupNameById", intID)
                .toString();
    }

    // 得到所有审核通过的培训期次
    @SuppressWarnings("rawtypes")
    public static List getAllTrainCount(int intGroupID) {
        TrainApplyModel trainApplyModel = new TrainApplyModel();

        // 如果不是省中心或系统管理员
        if (intGroupID != 12 && intGroupID != 1) {
            trainApplyModel.setIntCreateGroupID(intGroupID);
        }

        return trainApplyService.getObjectList("getAllTrainCount",
                trainApplyModel);
    }

    // 处理网上报名信息
    public static void processSignUp(int intID, int trainApplyID,
                                     int culUserId, String filePath) throws Exception {
        // 得到培训班信息
        TrainApplyModel trainApplyModel = new TrainApplyModel();
        trainApplyModel.setIntID(trainApplyID);

        trainApplyModel = (TrainApplyModel) trainApplyService.getObjectInfo(
                "getObjectInfo", trainApplyModel);

        int intTrainYear = Integer.parseInt(trainApplyModel
                .getStrTrainStartDate().substring(0,
                        trainApplyModel.getStrTrainStartDate().indexOf("-")));

        // 判断train_student是否存在相应的期次信息
        TrainStudentModel trainStudentModel = (TrainStudentModel) trainStudentService
                .getObjectInfo("getStudentModelByTrainApply", trainApplyModel);

        // 如果已经存在相应的期次信息，更新实际的学员人数。不存在则创建相应的期次信息
        if (trainStudentModel != null) {
            // 得到当前的实际人数
            int totalUserNum = trainStudentModel.getIntTotalUserNum();
            totalUserNum += 1;
            trainStudentModel.setIntTotalUserNum(totalUserNum);

            // 更新实际人数
            trainStudentService.updateObject("updateTotalUserNum",
                    trainStudentModel);
        } else {
            // 在train_student表中创建相应的培训班信息
            trainStudentModel = new TrainStudentModel();

            trainStudentModel.setIntTrainApplyID(trainApplyID);
            trainStudentModel.setStrTrainUnitName(trainApplyModel
                    .getStrTrainUnitName());
            trainStudentModel.setStrTrainStartDate(trainApplyModel
                    .getStrTrainStartDate());
            trainStudentModel.setStrTrainEndDate(trainApplyModel
                    .getStrTrainEndDate());
            trainStudentModel.setStrTrainCount(trainApplyModel
                    .getStrTrainCount());
            trainStudentModel
                    .setStrTrainType(trainApplyModel.getStrTrainType());
            trainStudentModel.setStrTrainObject(trainApplyModel
                    .getStrTrainObject());
            trainStudentModel.setStrTrainRange(trainApplyModel
                    .getStrTrainRange());
            trainStudentModel
                    .setStrTrainCode(trainApplyModel.getStrTrainCode());
            trainStudentModel.setStrTrainUserNum(trainApplyModel
                    .getStrTrainUserNum());
            trainStudentModel.setIntTotalUserNum(1);
            trainStudentModel.setIntCreateUserID(trainApplyModel
                    .getIntCreateUserID());
            trainStudentModel.setStrCreateUserName(trainApplyModel
                    .getStrCreateUserName());
            trainStudentModel.setIntCreateGroupID(trainApplyModel
                    .getIntCreateGroupID());
            trainStudentModel.setStrCreateGroupName(trainApplyModel
                    .getStrCreateGroupName());
            trainStudentModel.setIntCreateUnitID(trainApplyModel
                    .getIntCreateUnitID());
            trainStudentModel.setStrCreateUnitName(trainApplyModel
                    .getStrCreateUnitName());
            trainStudentModel.setStrCreateDate(MyDateUtil.DateToString(
                    new Date(), "yyyy-MM-dd HH:mm:ss"));
            int intTrainUnitId = Integer.parseInt(trainStudentService
                    .getObjectInfo("getRbacGroupByDesc",
                            trainApplyModel.getStrTrainUnitName()).toString());

            trainStudentModel.setIntTrainUnitID(intTrainUnitId);
            trainStudentModel.setStrTrainObjectDesc(trainApplyModel
                    .getStrTrainObjectDesc());

            trainStudentModel.setStrTrainCountClass(trainApplyModel
                    .getStrTrainCountClass());

            trainStudentService.insertObject("insertSignUp", trainStudentModel);
        }

        // 更新train_student_detail中的期次信息和处理状态以及intMainID和intTrainYear
        TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
        trainStudentDetailModel.setIntID(intID);
        trainStudentDetailModel.setStrTrainCount(trainApplyModel
                .getStrTrainCount());
        trainStudentDetailModel.setIntTrainYear(intTrainYear);

        // 根据trainApplyID得到intMainID
        int intMainID = Integer.parseInt(trainStudentService.getObjectInfo(
                "getIntIDByTrainApplyID", trainApplyID).toString());
        trainStudentDetailModel.setIntMainID(intMainID);
        trainStudentModel.setIntID(intMainID);

        trainStudentService.updateObject("updateSignUpInfo",
                trainStudentDetailModel);

        // 把相关照片复制到对应的目录下
        trainStudentDetailModel = (TrainStudentDetailModel) trainStudentService
                .getObjectInfo("getStudentDetailByIntID", intID);
        String idCard = trainStudentDetailModel.getStrStudentIDCode(); // 身份证号
        String fromPath1 = filePath + "0/pic1/" + idCard + ".JPG";
        String fromPath2 = filePath + "0/pic2/" + idCard + ".JPG";
        String fromPath3 = filePath + "0/pic3/" + idCard + ".JPG";
        String fromPath4 = filePath + "0/pic4/" + idCard + ".JPG";
        File fromFile1 = new File(fromPath1);
        File fromFile2 = new File(fromPath2);
        File fromFile3 = new File(fromPath3);
        File fromFile4 = new File(fromPath4);
        String toPath1 = filePath + intMainID + "/pic1/" + idCard + ".JPG";
        String toPath2 = filePath + intMainID + "/pic2/" + idCard + ".JPG";
        String toPath3 = filePath + intMainID + "/pic3/" + idCard + ".JPG";
        String toPath4 = filePath + intMainID + "/pic4/" + idCard + ".JPG";
        if (fromFile1.exists()) {
            MyFileUtil.copyFile(fromPath1, toPath1, true);
            MyFileUtil.deleteFile(fromFile1);
        }
        if (fromFile2.exists()) {
            MyFileUtil.copyFile(fromPath2, toPath2, true);
            MyFileUtil.deleteFile(fromFile2);
        }
        if (fromFile3.exists()) {
            MyFileUtil.copyFile(fromPath3, toPath3, true);
            MyFileUtil.deleteFile(fromFile3);
        }
        if (fromFile4.exists()) {
            MyFileUtil.copyFile(fromPath4, toPath4, true);
            MyFileUtil.deleteFile(fromFile4);
        }
    }

    @SuppressWarnings("rawtypes")
    public static List getAllTrainObject() {
        TrainObject trainObject = new TrainObject();
        List trainObjectList = trainApplyService.getObjectList(
                "getAllTrainObject", trainObject);
        return trainObjectList;
    }

    @SuppressWarnings("rawtypes")
    public static List getCourseByTrainObject(String strTrainObject,
                                              int intGroupID) {
        Traincourse trainCourse = new Traincourse();
        trainCourse.setIntCreateGroup(intGroupID);
        trainCourse.setIntDictionary(Integer.parseInt(strTrainObject));
        List trainCourseList = trainApplyService.getObjectList(
                "getCourseByTrainObject", trainCourse);
        return trainCourseList;
    }

    @SuppressWarnings("rawtypes")
    public static List getAllTrainCourse(int intGroupID) {
        Traincourse trainCourse = new Traincourse();
        trainCourse.setIntCreateGroup(intGroupID);
        List trainCourseList = trainApplyService.getObjectList(
                "getAllTrainCourse", trainCourse);
        return trainCourseList;
    }

    public static TrainTeacher getTeacherByCourseID(int intCourseID) {
        Traincourse traincourse = new Traincourse();
        traincourse.setIntID(intCourseID);
        TrainTeacher trainTeacher = (TrainTeacher) trainApplyService
                .getObjectInfo("getTeacherByCourseID", traincourse);
        if (trainTeacher == null) {
            trainTeacher = new TrainTeacher();
            trainTeacher.setStrName("");
            trainTeacher.setStrEducation("");
            trainTeacher.setStrMajor("");
            trainTeacher.setStrTitle("");
            trainTeacher.setStrCertCode("");
        }
        return trainTeacher;
    }

    public static TrainStudentModel getStudentModelByTrainApplyId(
            int intTrainApplyID) {
        return (TrainStudentModel) trainStudentService.getObjectInfo(
                "getStudentModelByTrainApplyId", intTrainApplyID);
    }

    @SuppressWarnings("rawtypes")
    public static List getStudentDetailByIntMainID(int intMainID) {
        return trainStudentService.getObjectList("getStudentDetailByIntMainID",
                intMainID);
    }

    public static void updateStudentPic(
            TrainStudentDetailModel trainStudentDetailModel) {
        trainStudentService.updateObject("updateStudentPic",
                trainStudentDetailModel);
    }

    /**
     * 网上报名批量导入时，更新数据库中人员信息的图片信息
     *
     * @throws Exception
     */
    public static String updataImageInfo(String path) throws Exception {
        StringBuffer returnStr = new StringBuffer();

        File directory = new File(path);
        if (!directory.exists()) {
            return "null";
        }
        List<File> fileList = new ArrayList<File>();
        fileList = MyFileUtil.getAllJPGFiles(directory, fileList);

        if (fileList != null && fileList.size() > 0) {

            String signUpPicRootPath = ConstantUtil.TRAIN_PIC_ROOT_PATH + "0/";

            for (int i = 0; i < fileList.size(); i++) {
                File file = fileList.get(i);

                // 把扩展名为小写的jpg改为大写JPG
                String fileName = file.getName().toUpperCase(); // 全名

                String parentPath = file.getParent();

                // 文件名（不含扩展名）
                String fname = fileName.substring(0, fileName.lastIndexOf("."));

                if (!MyStringUtil.isNullStr(fname)
                        && MyFileUtil.checkFileName(fileName, false)) {
                    TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
                    trainStudentDetailModel.setStrStudentIDCode(fname);
                    trainStudentDetailModel = (TrainStudentDetailModel) trainStudentService
                            .getObjectInfo("getSignUpStudentDetailByUserCode",
                                    trainStudentDetailModel);

                    if (trainStudentDetailModel != null) {

                        if (parentPath.indexOf("pic1") > 0) {
                            trainStudentDetailModel.setStrStudentPic1("pic1/"
                                    + fileName);
                            MyFileUtil.copyFile(parentPath + "/" + fileName,
                                    signUpPicRootPath + "pic1/" + fileName,
                                    true);
                        }
                        if (parentPath.indexOf("pic2") > 0) {
                            trainStudentDetailModel.setStrStudentPic2("pic2/"
                                    + fileName);
                            MyFileUtil.copyFile(parentPath + "/" + fileName,
                                    signUpPicRootPath + "pic2/" + fileName,
                                    true);
                        }
                        if (parentPath.indexOf("pic3") > 0) {
                            trainStudentDetailModel.setStrStudentPic3("pic3/"
                                    + fileName);
                            MyFileUtil.copyFile(parentPath + "/" + fileName,
                                    signUpPicRootPath + "pic3/" + fileName,
                                    true);
                        }
                        if (parentPath.indexOf("pic4") > 0) {
                            trainStudentDetailModel.setStrStudentPic4("pic4/"
                                    + fileName);
                            MyFileUtil.copyFile(parentPath + "/" + fileName,
                                    signUpPicRootPath + "pic4/" + fileName,
                                    true);
                        }

                        trainStudentService.updateObject(
                                "updateStudentPicByIdCard",
                                trainStudentDetailModel);

                    } else {
                        returnStr.append(fname + ",");
                    }
                }
            }

            if (!MyStringUtil.isNullStr(returnStr.toString())) {
                returnStr.deleteCharAt(returnStr.length() - 1);
                return returnStr.toString();
            } else {
                return "success";
            }
        } else {
            return "null";
        }
    }

    public static List<Map<String, String>> getPrintTicketInfo(
            TrainStudentModel trainStudentModel, int num) {
        @SuppressWarnings("rawtypes")
        List list = trainStudentService.getObjectList(
                "getStudentDetailByTrainCount", trainStudentModel);

        trainStudentModel = (TrainStudentModel) trainStudentService
                .getObjectInfo("getStudentByTrainCount", trainStudentModel);
        String trainObjectDesc = trainStudentModel.getStrTrainObjectDesc();
        int intID = trainStudentModel.getIntID();
        // 图片路径前缀
        String picPre = ConstantUtil.TRAIN_PIC_HTTP_ROOT_PATH + intID + "/";
        List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();

        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                TrainStudentDetailModel trainStudentDetailModel = (TrainStudentDetailModel) list
                        .get(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("studentName",
                        trainStudentDetailModel.getStrStudentName());
                map.put("studentPost",
                        trainStudentDetailModel.getStrStudentPost());
                map.put("studentIDCard",
                        trainStudentDetailModel.getStrStudentIDCode());
                if (!MyStringUtil.isNullStr(trainStudentDetailModel
                        .getStrStudentPic1())) {
                    map.put("ifPic", "1");
                    map.put("studentPic1",
                            picPre
                                    + trainStudentDetailModel
                                    .getStrStudentPic1());
                }
                map.put("room", "机房" + ((i / num) + 1));
                map.put("seat", (i % num) + 1 + "");
                map.put("trainObjectDesc", trainObjectDesc);
                returnList.add(map);
            }
        }

        return returnList;
    }

    public static TrainApplyModel getApplyByStudentDetail(
            TrainStudentDetailModel trainStudentDetailModel) {
        return ((TrainApplyModel) trainCertService.getObjectInfo(
                "getApplyByStudentDetail", trainStudentDetailModel));
    }

    public static void insertTrainCertLog(TrainCertLogModel trainCertLogModel) {
        trainCertService.insertObject("insertTrainCertLog", trainCertLogModel);
    }

    public static List<TrainApplyModel> getAllTrainApply() {
        return null;
    }

    public static TrainScoreModel getTrainScoreByTrainCount(
            TrainScoreModel trainScoreModel) {
        return (TrainScoreModel) trainScoreService.getObjectInfo(
                "getTrainScoreByTrainCount", trainScoreModel);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainStudentDetailScoreVO> getStudentDetailScoreByTrainApplyID(
            int TrainApplyID) {
        return (List<TrainStudentDetailScoreVO>) trainCertService
                .getObjectList("getStudentDetailScoreByTrainApplyID",
                        TrainApplyID);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainStudentModel> getAllTrainStudent() {
        return (List<TrainStudentModel>) trainStudentService.getObjectList(
                "getAllTrainStudent", new TrainStudentModel());
    }

    public static List<TrainPersonModel> usePersonRecord(
            List<TrainStudentDetailModel> existStudents) {
        return trainPersonService.usePersonRecord(existStudents);
    }

    public static List<TrainPersonModel> useNewAndUpdate(
            List<TrainStudentDetailModel> existStudents) {
        return trainPersonService.useNewAndUpdate(existStudents);
    }

    public static void importRecordPic(List<TrainPersonModel> existPicPersons) {
        trainPersonService.importRecordPic(existPicPersons);
    }

    public static void savePerson(TrainStudentModel trainStudentModel) {
        trainPersonService.savePerson(trainStudentModel);
    }
}
