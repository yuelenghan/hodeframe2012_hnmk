/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainApplyCourse;
import com.hode.train.model.TrainApplyDetailModel;
import com.hode.train.model.TrainApplyItem;
import com.hode.train.model.TrainApplyMaterial;
import com.hode.train.model.TrainApplyTeacher;
import com.hode.train.model.TrainTeacher;
import com.hode.train.model.Traincourse;

public class TrainApplyService extends IbatisService {

    public void addInsertDetail(TrainApplyItem trainApplyItem,
                                TrainApplyMaterial trainApplyMaterial,
                                TrainApplyTeacher trainApplyTeacher,
                                TrainApplyCourse trainApplyCourse) {
        if (trainApplyItem.getIntTrainApplyID() > 0) {
            if (trainApplyItem.getStrItemName() != null
                    && trainApplyItem.getStrItemName().length() > 0) {
                String strNameArr[] = trainApplyItem.getStrItemName()
                        .split(",");
                if (trainApplyItem.getStrItemPrice() == null) {
                    trainApplyItem.setStrItemPrice("");
                }
                String strPriceArr[] = trainApplyItem.getStrItemPrice().split(
                        ",");

                if (strNameArr != null && strNameArr.length > 0
                        && strPriceArr != null && strPriceArr.length > 0) {
                    for (int i = 0; i < strNameArr.length; i++) {
                        TrainApplyItem tApplyItem = new TrainApplyItem();
                        tApplyItem.setIntTrainApplyID(trainApplyItem
                                .getIntTrainApplyID());
                        tApplyItem.setStrItemName(strNameArr[i].trim());
                        tApplyItem.setStrItemPrice(strPriceArr[i].trim());
                        this.insertObject("insertDetailObject2", tApplyItem);
                    }
                }
            }
        }

        if (trainApplyMaterial.getIntTrainApplyID() > 0) {
            if (trainApplyMaterial.getStrMaterialName() != null
                    && trainApplyMaterial.getStrMaterialName().length() > 0
                    && trainApplyMaterial.getStrMaterialVersion() != null
                    && trainApplyMaterial.getStrMaterialVersion().length() > 0
                    && trainApplyMaterial.getStrMaterialPublisher() != null
                    && trainApplyMaterial.getStrMaterialPublisher().length() > 0
                    && trainApplyMaterial.getStrMaterialPrice() != null
                    && trainApplyMaterial.getStrMaterialPrice().length() > 0) {
                String strNameArr[] = trainApplyMaterial.getStrMaterialName()
                        .split(",");
                String strVersionArr[] = trainApplyMaterial
                        .getStrMaterialVersion().split(",");
                String strPublisherArr[] = trainApplyMaterial
                        .getStrMaterialPublisher().split(",");
                String strPriceArr[] = trainApplyMaterial.getStrMaterialPrice()
                        .split(",");

                if (strNameArr != null && strNameArr.length > 0) {
                    for (int i = 0; i < strNameArr.length; i++) {
                        TrainApplyMaterial tApplyMaterial = new TrainApplyMaterial();
                        tApplyMaterial.setIntTrainApplyID(trainApplyMaterial
                                .getIntTrainApplyID());
                        tApplyMaterial.setStrMaterialName(strNameArr[i].trim());
                        tApplyMaterial.setStrMaterialVersion(strVersionArr[i]
                                .trim());
                        tApplyMaterial
                                .setStrMaterialPublisher(strPublisherArr[i]);
                        tApplyMaterial.setStrMaterialPrice(strPriceArr[i]);
                        this.insertObject("insertDetailObject3", tApplyMaterial);
                    }
                }
            }
        }

        if (trainApplyTeacher.getIntTrainApplyID() > 0) {
            if (trainApplyTeacher.getStrTeacherID() != null
                    && trainApplyTeacher.getStrTeacherID().length() > 0) {
                String strTeacherIDArr[] = trainApplyTeacher.getStrTeacherID()
                        .split(",");
                if (strTeacherIDArr != null && strTeacherIDArr.length > 0) {
                    for (int i = 0; i < strTeacherIDArr.length; i++) {
                        TrainApplyTeacher tApplyTeacher = new TrainApplyTeacher();
                        tApplyTeacher.setIntTrainApplyID(trainApplyTeacher
                                .getIntTrainApplyID());
                        tApplyTeacher.setStrTeacherID(strTeacherIDArr[i]);
                        this.insertObject("insertDetailObject4", tApplyTeacher);
                    }
                }
            }
        }

        if (trainApplyCourse.getIntTrainApplyID() > 0) {
            if (trainApplyCourse.getStrCourseName() != null
                    && trainApplyCourse.getStrCourseName().length() > 0
                    && trainApplyCourse.getStrCourseTime() != null
                    && trainApplyCourse.getStrCourseTime().length() > 0) {
                String strNameArr[] = trainApplyCourse.getStrCourseName()
                        .split(",");
                String strTimeArr[] = trainApplyCourse.getStrCourseTime()
                        .split(",");

                if (strNameArr != null && strNameArr.length > 0) {
                    for (int i = 0; i < strNameArr.length; i++) {
                        TrainApplyCourse tApplyCourse = new TrainApplyCourse();
                        tApplyCourse.setIntTrainApplyID(trainApplyCourse
                                .getIntTrainApplyID());
                        tApplyCourse.setStrCourseName(strNameArr[i].trim());
                        tApplyCourse.setStrCourseTime(strTimeArr[i].trim());
                        this.insertObject("insertDetailObject5", tApplyCourse);
                    }
                }
            }
        }
    }

    public void addInsertDetail(TrainApplyDetailModel trainApplyDetailInfo) {
        if (trainApplyDetailInfo.getIntTrainApplyID() > 0) {
            String strArr[] = trainApplyDetailInfo.getStrSubjectNameArr();
            if (strArr != null && strArr.length > 0) {
                TrainApplyDetailModel trainApplyDetailModel = new TrainApplyDetailModel();
                trainApplyDetailModel.setIntTrainApplyID(trainApplyDetailInfo
                        .getIntTrainApplyID());
                for (int i = 0; i < strArr.length; i++) {
                    trainApplyDetailModel
                            .setStrSubjectName(trainApplyDetailInfo
                                    .getStrSubjectNameArr()[i]);// 课程名称
                    trainApplyDetailModel
                            .setStrSubjectTime(trainApplyDetailInfo
                                    .getStrSubjectTimeArr()[i]);// 时数

                    try {
                        Map<String, String> map = this.getTeacherInfo(Integer
                                .parseInt(trainApplyDetailInfo
                                        .getStrTeacherNameArr()[i]));

                        trainApplyDetailModel.setStrTeacherName(map
                                .get("teacherName"));// 任课教师--姓名
                    } catch (Exception e) {
                        trainApplyDetailModel
                                .setStrTeacherName(trainApplyDetailInfo
                                        .getStrTeacherNameArr()[i]);// 任课教师--姓名
                    }
                    trainApplyDetailModel
                            .setStrTeacherDegree(trainApplyDetailInfo
                                    .getStrTeacherDegreeArr()[i]);// 任课教师--学历
                    trainApplyDetailModel
                            .setStrTeacherProfessional(trainApplyDetailInfo
                                    .getStrTeacherProfessionalArr()[i]);// 任课教师--所学专业
                    trainApplyDetailModel
                            .setStrTeacherTitles(trainApplyDetailInfo
                                    .getStrTeacherTitlesArr()[i]);// 任课教师--职称
                    trainApplyDetailModel
                            .setStrTeacherCode(trainApplyDetailInfo
                                    .getStrTeacherCodeArr()[i]);// 任课教师--证书号码
                    trainApplyDetailModel.setIntOrderID(i);// 排序号
                    this.insertObject("insertDetailObject",
                            trainApplyDetailModel);

                }// for(int i=0;i<dataArr.length;i++)
            }
        }
    }

    public void deleteTrainApplyDetail(int trainApplyID) {
        TrainApplyItem tai = new TrainApplyItem();
        tai.setIntTrainApplyID(trainApplyID);
        this.deleteObject("deleteTrainApplyItem", tai);

        TrainApplyMaterial tam = new TrainApplyMaterial();
        tam.setIntTrainApplyID(trainApplyID);
        this.deleteObject("deleteTrainApplyMaterial", tam);

        TrainApplyTeacher tat = new TrainApplyTeacher();
        tat.setIntTrainApplyID(trainApplyID);
        this.deleteObject("deleteTrainApplyTeacher", tat);

        TrainApplyCourse tac = new TrainApplyCourse();
        tac.setIntTrainApplyID(trainApplyID);
        this.deleteObject("deleteTrainApplyCourse", tac);
    }

    public List<Map<String, Object>> getCourseAndTeacher(Traincourse traincourse) {
        List<Traincourse> courseList = this.getCourseList(traincourse);
        List<Map<String, Object>> resultList = new ArrayList<Map<String, Object>>();
        for (Traincourse course : courseList) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("courseID", course.getIntID());
            map.put("courseName", course.getStrCourseName());
            map.put("courseNum", course.getIntCourseNum());
            List<TrainTeacher> teacherList = this.getTeacherList(course);
            List<Map<String, String>> list = new ArrayList<Map<String, String>>();
            for (TrainTeacher teacher : teacherList) {
                Map<String, String> teacherMap = new HashMap<String, String>();
                teacherMap.put("teacherID", teacher.getIntID() + "");
                teacherMap.put("teacherName", teacher.getStrName());
                teacherMap.put("teacherEdu", teacher.getStrEducation());
                teacherMap.put("teacherMajor", teacher.getStrMajor());
                teacherMap.put("teacherTitle", teacher.getStrTitle());
                teacherMap.put("teacherCertCode", teacher.getStrCertCode());

                list.add(teacherMap);
            }

            map.put("teacherInfo", list);
            resultList.add(map);
        }
        return resultList;
    }

    public Map<String, String> getTeacherInfo(TrainTeacher trainTeacher) {
        trainTeacher = (TrainTeacher) this.getObjectInfo("getTeacherInfo",
                trainTeacher);
        Map<String, String> map = new HashMap<String, String>();
        if (trainTeacher != null) {
            map.put("teacherID", trainTeacher.getIntID() + "");
            map.put("teacherName", trainTeacher.getStrName());
            map.put("teacherEdu", trainTeacher.getStrEducation());
            map.put("teacherMajor", trainTeacher.getStrMajor());
            map.put("teacherTitle", trainTeacher.getStrTitle());
            map.put("teacherCertCode", trainTeacher.getStrCertCode());
            map.put("courseID", trainTeacher.getIntCourseID() + "");
        }

        return map;
    }

    public Map<String, String> getTeacherInfo(int intID) {
        TrainTeacher trainTeacher = (TrainTeacher) this.getObjectInfo(
                "getTeacherInfoByID", intID);

        if (trainTeacher != null) {
            Map<String, String> map = new HashMap<String, String>();
            map.put("teacherID", trainTeacher.getIntID() + "");
            map.put("teacherName", trainTeacher.getStrName());
            map.put("teacherEdu", trainTeacher.getStrEducation());
            map.put("teacherMajor", trainTeacher.getStrMajor());
            map.put("teacherTitle", trainTeacher.getStrTitle());
            map.put("teacherCertCode", trainTeacher.getStrCertCode());
            map.put("courseID", trainTeacher.getIntCourseID() + "");
            return map;
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public List<Traincourse> getCourseList(Traincourse traincourse) {
        return this.getObjectList("getCourseByTrainObject", traincourse);
    }

    @SuppressWarnings("unchecked")
    public List<TrainTeacher> getTeacherList(Traincourse traincourse) {
        return this.getObjectList("getTeacherByCourseID", traincourse);
    }

}