package com.hode.train.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainPersonModel;
import com.hode.train.model.TrainScoreDetailModel;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.model.TrainStudentModel;
import com.hode.train.model.TrainUserApplyDetailModel;
import com.hode.train.model.TrainUserChangeDetailModel;
import com.hode.train.util.ConstantUtil;
import com.hode.train.util.MyFileUtil;
import com.hode.train.util.MyStringUtil;

public class TrainPersonService extends IbatisService {

    /**
     * 生成人员档案基础信息和图片信息
     *
     * @param recordMap
     *            档案信息
     * @param trainPersonModel
     *            查询条件
     */
    public void genPersonBasicRecord(Map<String, Object> recordMap,
                                     TrainPersonModel trainPersonModel) {
        trainPersonModel = (TrainPersonModel) this.getObjectInfo(
                "getPersonByIDCode", trainPersonModel);

        // 基础信息
        recordMap.put("personName", trainPersonModel.getStrPersonName());// 姓名
        recordMap.put("personID", trainPersonModel.getStrPersonID());// 身份证号
        recordMap.put("personSex", trainPersonModel.getStrSex()); // 性别
        recordMap.put("personAge", trainPersonModel.getIntAge()); // 年龄
        recordMap.put("personSchool", trainPersonModel.getStrSchool()); // 毕业院校
        recordMap.put("personSpecialty", trainPersonModel.getStrSpecialty()); // 所学专业
        recordMap.put("personPolitical", trainPersonModel.getStrPolitical()); // 政治面貌
        recordMap.put("personJobDate", trainPersonModel.getStrJobDate()); // 参加工作时间

        // 图片信息
        String picPath = ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                + trainPersonModel.getIntGroupID() + "/";
        if (trainPersonModel.getIntPic1() == 1) {
            recordMap.put("pic1",
                    picPath + "pic1/" + trainPersonModel.getStrPersonID()
                            + ".JPG");
        }
        if (trainPersonModel.getIntPic2() == 1) {
            recordMap.put("pic2",
                    picPath + "pic2/" + trainPersonModel.getStrPersonID()
                            + ".JPG");
        }
        if (trainPersonModel.getIntPic3() == 1) {
            recordMap.put("pic3",
                    picPath + "pic3/" + trainPersonModel.getStrPersonID()
                            + ".JPG");
        }
        if (trainPersonModel.getIntPic4() == 1) {
            recordMap.put("pic4",
                    picPath + "pic4/" + trainPersonModel.getStrPersonID()
                            + ".JPG");
        }
    }

    /**
     * 生成人员培训记录
     *
     * @param recordMap
     *            档案信息
     * @param trainPersonModel
     *            查询条件
     */
    @SuppressWarnings("unchecked")
    public void genPersonTrainRecord(Map<String, Object> recordMap,
                                     TrainPersonModel trainPersonModel) {
        List<TrainStudentDetailModel> list = this.getObjectList(
                "getStudentDetailByIDCode", trainPersonModel);

        if (list != null && list.size() > 0) {
            List<Map<String, String>> trainRecord = new ArrayList<Map<String, String>>();

            for (TrainStudentDetailModel trainStudentDetailModel : list) {
                Map<String, String> trainRecordItem = new HashMap<String, String>();

                // 培训期次
                trainRecordItem.put("trainCount",
                        trainStudentDetailModel.getStrTrainCount());
                // 班级
                trainRecordItem.put("trainCountClass",
                        trainStudentDetailModel.getStrTrainCountClass());
                // 单位及部门
                trainRecordItem.put("unitAndPost",
                        trainStudentDetailModel.getStrStudentPost());
                // 职务
                trainRecordItem.put("title",
                        trainStudentDetailModel.getStrStudentTitles());
                // 学历
                trainRecordItem.put("eduLevel",
                        trainStudentDetailModel.getStrStudentDegree());
                // 健康状况
                trainRecordItem.put("health",
                        trainStudentDetailModel.getStrStudentHealth());
                // 违纪情况
                trainRecordItem.put("violate",
                        trainStudentDetailModel.getStrStudentViolate());
                // 年份
                trainRecordItem.put("trainYear",
                        trainStudentDetailModel.getIntTrainYear() + "");

                trainRecord.add(trainRecordItem);
            }

            recordMap.put("trainRecord", trainRecord);
        }
    }

    /**
     * 生成人员成绩信息
     *
     * @param recordMap
     *            档案信息
     * @param trainPersonModel
     *            查询条件
     */
    @SuppressWarnings("unchecked")
    public void genPersonScoreRecord(Map<String, Object> recordMap,
                                     TrainPersonModel trainPersonModel) {
        List<TrainScoreDetailModel> list = this.getObjectList(
                "getScoreDetailByIDCode", trainPersonModel);

        if (list != null && list.size() > 0) {
            List<Map<String, String>> scoreRecord = new ArrayList<Map<String, String>>();

            for (TrainScoreDetailModel trainScoreDetailModel : list) {
                Map<String, String> scoreRecordItem = new HashMap<String, String>();

                // 培训期次
                String trainCount = trainScoreDetailModel.getStrTrainCount();
                String trainCount1 = trainScoreDetailModel.getStrTrainCount1();
                if (MyStringUtil.isNullStr(trainCount)) {
                    scoreRecordItem.put("trainCount", trainCount1);
                } else {
                    scoreRecordItem.put("trainCount", trainCount);
                }

                // 班级
                scoreRecordItem.put("trainCountClass",
                        trainScoreDetailModel.getStrTrainCountClass());
                // 考试期次
                String scoreCount = trainScoreDetailModel.getStrScoreCount();
                String scoreCount1 = trainScoreDetailModel.getStrScoreCount1();
                if (MyStringUtil.isNullStr(scoreCount)) {
                    scoreRecordItem.put("scoreCount", scoreCount1);
                } else {
                    scoreRecordItem.put("scoreCount", scoreCount);
                }

                // 考试场次
                scoreRecordItem.put("scoreExcam",
                        trainScoreDetailModel.getStrScoreExam());
                // 理论成绩
                scoreRecordItem.put("fltExamScore1",
                        trainScoreDetailModel.getFltExamScore1() + "");
                // 实操成绩
                scoreRecordItem.put("fltExamScore2",
                        trainScoreDetailModel.getFltExamScore2() + "");
                // 理论成绩（补考）
                scoreRecordItem.put("fltExamScore3",
                        trainScoreDetailModel.getFltExamScore3() + "");
                // 实操成绩（补考）
                scoreRecordItem.put("fltExamScore4",
                        trainScoreDetailModel.getFltExamScore4() + "");

                scoreRecord.add(scoreRecordItem);
            }

            recordMap.put("scoreRecord", scoreRecord);
        }
    }

    /**
     * 生成人员补证信息
     *
     * @param recordMap
     *            档案信息
     * @param trainPersonModel
     *            查询条件
     */
    @SuppressWarnings("unchecked")
    public void genPersonApplyRecord(Map<String, Object> recordMap,
                                     TrainPersonModel trainPersonModel) {
        List<TrainUserApplyDetailModel> list = this.getObjectList(
                "getUserApplyDetailByIDCode", trainPersonModel);

        if (list != null && list.size() > 0) {
            List<Map<String, String>> userApplyRecord = new ArrayList<Map<String, String>>();

            for (TrainUserApplyDetailModel trainUserApplyDetailModel : list) {
                Map<String, String> userApplyRecordItem = new HashMap<String, String>();

                // 补证日期
                userApplyRecordItem.put("userApplyDate",
                        trainUserApplyDetailModel.getStrUserDate());
                // 证书号码
                userApplyRecordItem.put("strCertCode",
                        trainUserApplyDetailModel.getStrCertCode());
                // 单位
                userApplyRecordItem.put("strUserUnitName",
                        trainUserApplyDetailModel.getStrUserUnitName());
                // 职务
                userApplyRecordItem.put("strUserPost",
                        trainUserApplyDetailModel.getStrUserPost());
                // 补证理由
                userApplyRecordItem.put("strApplyReason",
                        trainUserApplyDetailModel.getStrApplyReason());

                userApplyRecord.add(userApplyRecordItem);
            }

            recordMap.put("userApplyRecord", userApplyRecord);
        }
    }

    /**
     * 生成人员变更记录
     *
     * @param recordMap
     *            档案信息
     * @param trainPersonModel
     *            查询条件
     */
    @SuppressWarnings("unchecked")
    public void genPersonChangeRecord(Map<String, Object> recordMap,
                                      TrainPersonModel trainPersonModel) {
        List<TrainUserChangeDetailModel> list = this.getObjectList(
                "getUserChangeDetailByIDCode", trainPersonModel);

        if (list != null && list.size() > 0) {
            List<Map<String, String>> userChangeRecord = new ArrayList<Map<String, String>>();

            for (TrainUserChangeDetailModel trainUserChangeDetailModel : list) {
                Map<String, String> userChangeRecordItem = new HashMap<String, String>();

                // 变更日期
                userChangeRecordItem.put("userChangeDate",
                        trainUserChangeDetailModel.getStrUserDate());
                // 原单位
                userChangeRecordItem.put("fromUnit",
                        trainUserChangeDetailModel.getStrFromUnit());
                // 现单位
                userChangeRecordItem.put("toUnit",
                        trainUserChangeDetailModel.getStrToUnit());
                // 原职务
                userChangeRecordItem.put("fromTitle",
                        trainUserChangeDetailModel.getStrFromTitles());
                // 现职务
                userChangeRecordItem.put("toTitle",
                        trainUserChangeDetailModel.getStrToTitles());

                userChangeRecord.add(userChangeRecordItem);
            }

            recordMap.put("userChangeRecord", userChangeRecord);
        }
    }

    /**
     * 导入基础信息时，使用档案库中的信息
     *
     * @param existStudents
     *            档案库中已经存在的人员列表
     *
     * @return 档案库中存在图片信息的人员列表
     */
    public List<TrainPersonModel> usePersonRecord(
            List<TrainStudentDetailModel> existStudents) {
        List<TrainPersonModel> existPicPersons = new ArrayList<TrainPersonModel>();
        for (TrainStudentDetailModel trainStudentDetailModel : existStudents) {
            // 由学员信息得到对应人员的档案库信息
            TrainPersonModel person = this
                    .getPersonRecordByStudent(trainStudentDetailModel);

            // 如果存在图片信息，将person加入到existPicPersons中
            if (person.getIntPic1() == 1 || person.getIntPic2() == 1
                    || person.getIntPic3() == 1 || person.getIntPic4() == 1) {
                // 导入档案库图片时用来确认文件路径
                person.setIntMainID(trainStudentDetailModel.getIntMainID());

                if (person.getIntPic1() == 1) {
                    person.setStrPic1(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic1/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic2() == 1) {
                    person.setStrPic2(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic2/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic3() == 1) {
                    person.setStrPic3(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic3/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic4() == 1) {
                    person.setStrPic4(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic4/"
                            + person.getStrPersonID() + ".JPG");
                }
                existPicPersons.add(person);
            }

            // 用档案库中的人员信息替换学员信息
            trainStudentDetailModel = this.changeStudentDetailToPersonRecord(
                    trainStudentDetailModel, person);

            // 将学员信息插入数据库
            this.insertObject("insertStudentDetail", trainStudentDetailModel);
        }
        return existPicPersons;
    }

    /**
     * 根据学员信息得到对应人员的档案库信息
     *
     * @param trainStudentDetailModel
     *            学员信息
     * @return 对应人员的档案库信息
     */
    public TrainPersonModel getPersonRecordByStudent(
            TrainStudentDetailModel trainStudentDetailModel) {
        return (TrainPersonModel) this.getObjectInfo(
                "getPersonRecordByStudent", trainStudentDetailModel);
    }

    /**
     * 根据档案库信息得到学员信息
     *
     * @param trainPersonModel
     *            档案库信息
     * @return 学员信息
     */
    public TrainStudentDetailModel getStudentByPersonRecord(
            TrainPersonModel trainPersonModel) {
        return (TrainStudentDetailModel) this.getObjectInfo(
                "getStudentByPersonRecord", trainPersonModel);
    }

    /**
     * 用档案库中的人员信息替换学员信息
     *
     * @param trainStudentDetailModel
     *            学员信息
     * @param trainPersonModel
     *            档案库中的人员信息
     * @return
     */
    public TrainStudentDetailModel changeStudentDetailToPersonRecord(
            TrainStudentDetailModel trainStudentDetailModel,
            TrainPersonModel trainPersonModel) {

        trainStudentDetailModel.setStrStudentName(trainPersonModel
                .getStrPersonName()); // 姓名
        trainStudentDetailModel.setStrStudentSex(trainPersonModel.getStrSex()); // 性别
        trainStudentDetailModel.setStrStudentDegree(trainPersonModel
                .getStrEdu()); // 学历
        trainStudentDetailModel.setStrStudentPost(trainPersonModel
                .getStrWorkUnit()); // 单位及部门
        trainStudentDetailModel.setStrStudentTitles(trainPersonModel
                .getStrPost()); // 职务
        trainStudentDetailModel.setIntStudentAge(trainPersonModel.getIntAge()); // 年龄

        return trainStudentDetailModel;
    }

    /**
     * 使用新信息并更新档案库
     *
     * @param existStudents
     *            档案库中已经存在的人员列表
     *
     * @return 档案库中存在图片信息的人员列表
     */
    public List<TrainPersonModel> useNewAndUpdate(
            List<TrainStudentDetailModel> existStudents) {
        List<TrainPersonModel> existPicPersons = new ArrayList<TrainPersonModel>();
        for (TrainStudentDetailModel trainStudentDetailModel : existStudents) {
            // 根据学员信息得到对应人员的档案库信息
            TrainPersonModel person = this
                    .getPersonRecordByStudent(trainStudentDetailModel);

            // 用学员信息替换档案库中的人员信息
            person = this.changePersonRecordToStudentDetail(
                    trainStudentDetailModel, person);

            // 如果存在图片信息，将person加入到existPicPersons中
            if (person.getIntPic1() == 1 || person.getIntPic2() == 1
                    || person.getIntPic3() == 1 || person.getIntPic4() == 1) {
                // 导入档案库图片时用来确认文件路径
                person.setIntMainID(trainStudentDetailModel.getIntMainID());

                if (person.getIntPic1() == 1) {
                    person.setStrPic1(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic1/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic2() == 1) {
                    person.setStrPic2(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic2/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic3() == 1) {
                    person.setStrPic3(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic3/"
                            + person.getStrPersonID() + ".JPG");
                }
                if (person.getIntPic4() == 1) {
                    person.setStrPic4(ConstantUtil.RECORD_PIC_HTTP_ROOT_PATH
                            + person.getIntGroupID() + "/pic4/"
                            + person.getStrPersonID() + ".JPG");
                }
                existPicPersons.add(person);
            }

            // 更新档案库信息
            this.updateObject("updatePersonRecord", person);

            // 将学员信息插入数据库
            this.insertObject("insertStudentDetail", trainStudentDetailModel);
        }
        return existPicPersons;
    }

    /**
     * 用学员信息替换档案库中的人员信息
     *
     * @param trainStudentDetailModel
     *            学员信息
     * @param trainPersonModel
     *            档案库中的人员信息
     * @return
     */
    public TrainPersonModel changePersonRecordToStudentDetail(
            TrainStudentDetailModel trainStudentDetailModel,
            TrainPersonModel trainPersonModel) {

        trainPersonModel.setStrPersonName(trainStudentDetailModel
                .getStrStudentName()); // 姓名
        trainPersonModel.setStrSex(trainStudentDetailModel.getStrStudentSex()); // 性别
        trainPersonModel.setStrEdu(trainStudentDetailModel
                .getStrStudentDegree()); // 学历
        trainPersonModel.setStrWorkUnit(trainStudentDetailModel
                .getStrStudentPost()); // 单位及部门
        trainPersonModel.setStrPost(trainStudentDetailModel
                .getStrStudentTitles()); // 职务
        trainPersonModel.setIntAge(trainStudentDetailModel.getIntStudentAge()); // 年龄

        return trainPersonModel;
    }

    /**
     * 导入档案库中的图片信息
     *
     * @param existPicPersons
     *            档案库中存在图片信息的人员列表
     */
    public void importRecordPic(List<TrainPersonModel> existPicPersons) {
        for (TrainPersonModel trainPersonModel : existPicPersons) {
            TrainStudentDetailModel trainStudentDetailModel = this
                    .getStudentByPersonRecord(trainPersonModel);
            String pic1 = trainPersonModel.getStrPic1();
            if (!MyStringUtil.isNullStr(pic1)) {
                // 复制照片
                copyPicToTrainStudent(trainPersonModel,
                        trainStudentDetailModel, 1);

                trainStudentDetailModel.setStrStudentPic1("pic1/"
                        + trainPersonModel.getStrPersonID() + ".JPG");
            }

            String pic2 = trainPersonModel.getStrPic2();
            if (!MyStringUtil.isNullStr(pic2)) {
                // 复制学历
                copyPicToTrainStudent(trainPersonModel,
                        trainStudentDetailModel, 2);

                trainStudentDetailModel.setStrStudentPic2("pic2/"
                        + trainPersonModel.getStrPersonID() + ".JPG");
            }

            String pic3 = trainPersonModel.getStrPic3();
            if (!MyStringUtil.isNullStr(pic3)) {
                // 复制体检表
                copyPicToTrainStudent(trainPersonModel,
                        trainStudentDetailModel, 3);

                trainStudentDetailModel.setStrStudentPic3("pic3/"
                        + trainPersonModel.getStrPersonID() + ".JPG");
            }

            String pic4 = trainPersonModel.getStrPic4();
            if (!MyStringUtil.isNullStr(pic4)) {
                // 复制工作证明
                copyPicToTrainStudent(trainPersonModel,
                        trainStudentDetailModel, 4);

                trainStudentDetailModel.setStrStudentPic4("pic4/"
                        + trainPersonModel.getStrPersonID() + ".JPG");
            }

            // 更新数据库
            this.updateObject("updateStudentDetailPicStatus",
                    trainStudentDetailModel);
        }
    }

    /**
     * 复制人员图片信息到档案库
     *
     * @param person
     * @param student
     * @param type
     *            图片类型 1.照片 2.学历 3.体检表 4.工作证明
     */
    public void copyPicToRecord(TrainPersonModel person,
                                TrainStudentDetailModel student, int type) {
        String fromPath = ConstantUtil.TRAIN_PIC_ROOT_PATH
                + student.getIntMainID() + "/";
        String toPath = ConstantUtil.RECORD_PIC_ROOT_PATH
                + person.getIntGroupID() + "/";
        if (type == 1) {
            fromPath += "pic1/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic1/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 2) {
            fromPath += "pic2/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic2/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 3) {
            fromPath += "pic3/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic3/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 4) {
            fromPath += "pic4/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic4/" + student.getStrStudentIDCode() + ".JPG";
        }

        MyFileUtil.copyFile(fromPath, toPath, true);
    }

    /**
     * 复制档案库中的人员图片信息到对应的期次下
     *
     * @param person
     * @param student
     * @param type
     *            图片类型 1.照片 2.学历 3.体检表 4.工作证明
     */
    public void copyPicToTrainStudent(TrainPersonModel person,
                                      TrainStudentDetailModel student, int type) {
        String fromPath = ConstantUtil.RECORD_PIC_ROOT_PATH
                + person.getIntGroupID() + "/";
        String toPath = ConstantUtil.TRAIN_PIC_ROOT_PATH
                + student.getIntMainID() + "/";

        if (type == 1) {
            fromPath += "pic1/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic1/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 2) {
            fromPath += "pic2/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic2/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 3) {
            fromPath += "pic3/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic3/" + student.getStrStudentIDCode() + ".JPG";
        }
        if (type == 4) {
            fromPath += "pic4/" + student.getStrStudentIDCode() + ".JPG";
            toPath += "pic4/" + student.getStrStudentIDCode() + ".JPG";
        }

        MyFileUtil.copyFile(fromPath, toPath, true);
    }

    /**
     * 人员信息归档--全部
     *
     * @param trainStudentModel
     */
    @SuppressWarnings({ "unchecked" })
    public void savePerson(TrainStudentModel trainStudentModel) {
        trainStudentModel = (TrainStudentModel) this.getObjectInfo(
                "getStudentModel", trainStudentModel);

        List<TrainStudentDetailModel> studentDetailList = this.getObjectList(
                "getStudentDetail", trainStudentModel);
        if (studentDetailList != null && studentDetailList.size() > 0) {
            for (TrainStudentDetailModel trainStudentDetailModel : studentDetailList) {
                this.savePerson(trainStudentModel, trainStudentDetailModel);
            }
        }
    }

    /**
     * 人员信息归档--单个
     *
     * @param trainStudentModel
     * @param trainStudentDetailModel
     */
    public void savePerson(TrainStudentModel trainStudentModel,
                           TrainStudentDetailModel trainStudentDetailModel) {
        TrainPersonModel person = this
                .getPersonRecordByStudent(trainStudentDetailModel);
        if (person == null) {
            // 将人员基础信息插入档案库
            person = new TrainPersonModel();
            person.setStrPersonName(trainStudentDetailModel.getStrStudentName());
            person.setStrPersonID(trainStudentDetailModel.getStrStudentIDCode());
            person.setStrSex(trainStudentDetailModel.getStrStudentSex());
            person.setIntGroupID(trainStudentModel.getIntCreateGroupID());
            person.setStrEdu(trainStudentDetailModel.getStrStudentDegree());
            person.setStrWorkUnit(trainStudentDetailModel.getStrStudentPost());
            person.setStrPost(trainStudentDetailModel.getStrStudentTitles());
            person.setIntAge(trainStudentDetailModel.getIntStudentAge());

            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic1())) {
                person.setIntPic1(1);

                // 将照片复制到图片档案文件夹
                // MyFileUtil.copyFile(srcFileName1, destFileName1, true);
                this.copyPicToRecord(person, trainStudentDetailModel, 1);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic2())) {
                person.setIntPic2(1);

                // 将学历复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 2);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic3())) {
                person.setIntPic3(1);

                // 将体检表复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 3);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic4())) {
                person.setIntPic4(1);

                // 将工作证明复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 4);
            }

            this.insertObject("insertPersonRecord", person);

        } else {
            // 只更新档案库图片信息
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic1())) {
                person.setIntPic1(1);

                // 将照片复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 1);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic2())) {
                person.setIntPic2(1);

                // 将学历复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 2);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic3())) {
                person.setIntPic3(1);

                // 将体检表复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 3);
            }
            if (!MyStringUtil.isNullStr(trainStudentDetailModel
                    .getStrStudentPic4())) {
                person.setIntPic4(1);

                // 将工作证明复制到图片档案文件夹
                this.copyPicToRecord(person, trainStudentDetailModel, 4);
            }

            this.updateObject("updatePersonPicRecord", person);
        }
    }
}
