package com.hode.train.util;

import java.util.ArrayList;
import java.util.List;

import com.hode.dictionary.model.DictionaryModel;
import com.hode.framework.service.GlobeSpringContext;
import com.hode.train.model.TrainDictionaryTeacherModel;
import com.hode.train.model.TrainExamineeModel;
import com.hode.train.model.TrainMaterialModel;
import com.hode.train.model.TrainPapersModel;
import com.hode.train.model.TrainTeacher;
import com.hode.train.model.Traincourse;
import com.hode.train.model.TrainingProgramsModel;
import com.hode.train.service.TrainCourseService;
import com.hode.train.service.TrainMaterialService;
import com.hode.train.service.TrainTeacherService;
import com.hode.train.service.TrainingProgramsService;

public class TraUtil {
    private static TrainingProgramsService trainingProgramsService = (TrainingProgramsService) (GlobeSpringContext
            .getApplicationContext().getBean("trainingProgramsServiceProxy"));
    private static TrainCourseService trainCourseService = (TrainCourseService) (GlobeSpringContext
            .getApplicationContext().getBean("trainCourseServiceProxy"));
    private static TrainMaterialService trainMaterialService = (TrainMaterialService) (GlobeSpringContext
            .getApplicationContext().getBean("trainMaterialServiceProxy"));
    private static TrainTeacherService trainTeacherService = (TrainTeacherService) (GlobeSpringContext
            .getApplicationContext().getBean("trainTeacherServiceProxy"));

    public static void updateTrainExa(TrainExamineeModel trainExamineeModel) {
        trainCourseService.updateObject("updateTrainExa", trainExamineeModel);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainPapersModel> trainPapList(TeacUtil teacUtil) {
        return trainCourseService.getObjectList("trainPapList", teacUtil);
    }

    @SuppressWarnings("unchecked")
    public static List<DictionaryModel> getListTest() {
        return trainCourseService.getObjectList("getListTest");
    }

    public static TrainPapersModel trainPap(String Ids) {
        return (TrainPapersModel) trainCourseService.getObjectInfo("trainPap",
                Ids);
    }

    public static TrainExamineeModel trainExa(String cardId) {
        return (TrainExamineeModel) trainCourseService.getObjectInfo(
                "trainExa", cardId);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainDictionaryTeacherModel> getObjectDicTea(
            TeacUtil teacUtil) {
        return trainTeacherService.getObjectList("getObjectDicTea", teacUtil);
    }

    public static void deleteTeacDic(TeacUtil teacUtil) {
        trainTeacherService.deleteObject("deleteTeacDic", teacUtil);
    }

    public static void addSavTec(
            TrainDictionaryTeacherModel trainDictionaryTeacherModel) {
        trainTeacherService.insertObject("addSavTec",
                trainDictionaryTeacherModel);
    }

    public static void updateTeac(TrainTeacher trainTeacher) {
        trainTeacherService.updateObject("updateTeac", trainTeacher);
    }

    public static TrainTeacher selectTeacherId(int intID) {
        return (TrainTeacher) trainTeacherService.getObjectInfo(
                "selectTeacherId", intID);
    }

    public static void updateMat(TrainMaterialModel trainMaterialModel) {
        trainMaterialService.updateObject("updateMat", trainMaterialModel);
    }

    public static TrainMaterialModel selectMatId(int intID) {
        return (TrainMaterialModel) trainMaterialService.getObjectInfo(
                "selectMatId", intID);
    }

    public static void updatePro(TrainingProgramsModel trainingProgramsModel) {
        trainingProgramsService
                .updateObject("updatePro", trainingProgramsModel);
    }

    public static void updateCourse(Traincourse traincourse) {
        trainCourseService.updateObject("updateCourse", traincourse);
    }

    public static TrainingProgramsModel trainPro(int intID) {
        return (TrainingProgramsModel) trainingProgramsService.getObjectInfo(
                "trainPro", intID);
    }

    public static Traincourse selectCourse(int intID) {
        return (Traincourse) trainCourseService.getObjectInfo("traincourse",
                intID);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> getListTeac(TrainTeacher trainTeacher) {
        return trainTeacherService.getObjectList("getObjectInfo", trainTeacher);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainTeacher> getTeacListByDic(TeacUtil teacUtil) {
        return trainTeacherService.getObjectList("getObjectTeacInfo", teacUtil);
    }

    public static void addTeac(TrainTeacher trainTeacher) {
        trainTeacherService.insertObject("addTeac", trainTeacher);
    }

    public static void deleteTeac(TrainTeacher trainTeacher) {
        trainTeacherService.deleteObject("deleteTeac", trainTeacher);
    }

    @SuppressWarnings("unchecked")
    public static List<TrainMaterialModel> getTrMaList(
            TrainMaterialModel trainMaterialModel) {
        return trainMaterialService.getObjectList("getObjectList",
                trainMaterialModel);
    }

    public static void deleteMarter(TrainMaterialModel trainMaterialModel) {
        trainMaterialService.deleteObject("deleteMater", trainMaterialModel);
    }

    public static void insertMatr(TrainMaterialModel trainMaterialModel) {
        trainMaterialService.insertObject("insertObject", trainMaterialModel);
    }

    @SuppressWarnings("rawtypes")
    public static List getTrainingProgramsListById(int id) {
        return trainingProgramsService.getObjectList("getObjectList", id + "");
    }

    @SuppressWarnings("rawtypes")
    public static List getBM(int id) {
        return trainCourseService.getObjectList("getObjectAllList", id + "");
    }

    @SuppressWarnings("unchecked")
    public static List<Traincourse> getObjectList(Traincourse traincourse) {
        return trainCourseService.getObjectList("getObjectList", traincourse);
    }

    public static void deleteCourse(Traincourse traincourse) {
        trainCourseService.deleteObject("deleteCourse", traincourse);
    }

    public static void insertCoures(Traincourse traincourse) {
        trainCourseService.insertObject("insertObject", traincourse);
    }

    public static void setTeacherCourseName(List<TrainTeacher> list) {
        for (int i = 0; i < list.size(); i++) {
            TrainTeacher trainTeacher = list.get(i);
            String courseName = String.valueOf(trainCourseService
                    .getObjectInfo("getCourseNameByID",
                            trainTeacher.getIntCourseID()));
            trainTeacher.setStrCourseName(courseName);
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyCourse(int dicrionaryFrom, int dicrionaryTo,
                                  int createUser, int createGroup) {
        Traincourse traincourse = new Traincourse();
        traincourse.setIntCreateGroup(createGroup);
        traincourse.setIntDictionary(dicrionaryFrom);

        List<Traincourse> courseFrom = trainCourseService.getObjectList(
                "getCourseFrom", traincourse);
        if (courseFrom != null && courseFrom.size() > 0) {
            for (int i = 0; i < courseFrom.size(); i++) {
                Traincourse trainCourse = courseFrom.get(i);
                trainCourse.setIntCreateUser(createUser);
                trainCourse.setIntDictionary(dicrionaryTo);
                trainCourseService.insertObject("insertObject", trainCourse);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static void copyTeacher(String strTrainObjectFrom,
                                   String strTrainObjectTo, int createUser, int createGroup) {

        Traincourse traincourse = new Traincourse();
        traincourse.setIntCreateGroup(createGroup);
        traincourse.setIntDictionary(Integer.parseInt(strTrainObjectFrom));
        List<Traincourse> courseFrom = trainCourseService.getObjectList(
                "getCourseFrom", traincourse);

        List<Integer> courseIDList = new ArrayList<Integer>();
        for (int i = 0; i < courseFrom.size(); i++) {
            courseIDList.add(courseFrom.get(i).getIntID());
        }

        TrainTeacher trainTeacher = new TrainTeacher();
        trainTeacher.setIntGroup(createGroup);
        trainTeacher.setCourseIDList(courseIDList);

        List<TrainTeacher> teacherFrom = trainTeacherService.getObjectList(
                "getObjectInfo", trainTeacher);

        for (int i = 0; i < teacherFrom.size(); i++) {
            trainTeacher = teacherFrom.get(i);
            trainTeacher.setIntCreateUser(createUser);
            int courseID = trainTeacher.getIntCourseID();
            String courseName = trainCourseService.getObjectInfo(
                    "getCourseNameByID", courseID).toString();

            if (!MyStringUtil.isNullStr(courseName)) {
                traincourse.setStrCourseName(courseName);
                traincourse
                        .setIntDictionary(Integer.parseInt(strTrainObjectTo));
                Traincourse traincourse2 = (Traincourse) trainCourseService
                        .getObjectInfo("getCourseByNameAndTrainObject",
                                traincourse);

                if (traincourse2 != null) {
                    trainTeacher.setIntCourseID(traincourse2.getIntID());
                    trainTeacherService.insertObject("addTeac", trainTeacher);
                }
            }
        }
    }
}
