package com.hode.train.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.train.model.TrainDictionaryTeacherModel;
import com.hode.train.model.TrainTeacher;
import com.hode.train.model.Traincourse;
import com.hode.train.util.MyStringUtil;
import com.hode.train.util.TeacUtil;
import com.hode.train.util.TraUtil;
import com.opensymphony.webwork.ServletActionContext;

public class TeacherAction extends AbstractBaseAction {

    private static final long serialVersionUID = -1333624969185409821L;

    @Override
    protected void createObjInfo() {
        objInfo = new TrainTeacher();
    }

    @Override
    protected void createObjSearch() {
        objSearch = new TrainTeacher();
    }

    private List<TrainTeacher> listTeac = new ArrayList<TrainTeacher>();
    private List<TrainTeacher> listTeacSeTeachers = new ArrayList<TrainTeacher>();
    private List<TrainDictionaryTeacherModel> listCout = new ArrayList<TrainDictionaryTeacherModel>();

    public List<TrainDictionaryTeacherModel> getListCout() {
        return listCout;
    }

    public void setListCout(List<TrainDictionaryTeacherModel> listCout) {
        this.listCout = listCout;
    }

    public List<TrainTeacher> getListTeacSeTeachers() {
        return listTeacSeTeachers;
    }

    public void setListTeacSeTeachers(List<TrainTeacher> listTeacSeTeachers) {
        this.listTeacSeTeachers = listTeacSeTeachers;
    }

    public List<TrainTeacher> getListTeac() {
        return listTeac;
    }

    private TrainTeacher trainTeacher = new TrainTeacher();
    private String istru = "";

    public String getIstru() {
        return istru;
    }

    public void setIstru(String istru) {
        this.istru = istru;
    }

    public TrainTeacher getTrainTeacher() {
        return trainTeacher;
    }

    public void setTrainTeacher(TrainTeacher trainTeacher) {
        this.trainTeacher = trainTeacher;
    }

    public void setListTeac(List<TrainTeacher> listTeac) {
        this.listTeac = listTeac;
    }

    private int resu;

    public int getResu() {
        return resu;
    }

    public void setResu(int resu) {
        this.resu = resu;
    }

    public String showList() {
        String strTrainObject = ((TrainTeacher) objSearch).getStrTrainObject();
        int intGroupID = getUserSessionModel().getIntGroupID();

        Traincourse trainCourse = new Traincourse();
        trainCourse.setIntCreateGroup(intGroupID);
        if (!MyStringUtil.isNullStr(strTrainObject)) {
            trainCourse.setIntDictionary(Integer.parseInt(strTrainObject));
            resu = Integer.parseInt(strTrainObject);
        } else {
            trainCourse.setIntDictionary(0);
        }
        List<Traincourse> courseList = TraUtil.getObjectList(trainCourse);

        List<Integer> courseIDList = new ArrayList<Integer>();
        courseIDList.add(0);
        if (courseList != null && courseList.size() > 0) {
            for (int i = 0; i < courseList.size(); i++) {
                courseIDList.add(courseList.get(i).getIntID());
            }
        }

        if (courseIDList != null && courseIDList.size() > 0) {
            ((TrainTeacher) objSearch).setCourseIDList(courseIDList);
        }
        ((TrainTeacher) objSearch).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        List<TrainTeacher> list = TraUtil.getListTeac((TrainTeacher) objSearch);
        TraUtil.setTeacherCourseName(list);
        listTeac = list;
        return SUCCESS;
    }

    public String selectupdate() {
        trainTeacher = TraUtil.selectTeacherId(((TrainTeacher) objSearch)
                .getIntID());
        istru = trainTeacher.getStrCertificate();
        return SUCCESS;
    }

    public String updateTeac() {
        TraUtil.updateTeac(((TrainTeacher) objInfo));
        ((TrainTeacher) objSearch).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        List<TrainTeacher> list = TraUtil.getListTeac((TrainTeacher) objSearch);
        listTeac = list;
        return SUCCESS;
    }

    public String deleteTeac() {
        int res = ((TrainTeacher) objInfo).getIntID();
        if (res > 0) {
            TraUtil.deleteTeac((TrainTeacher) objInfo);
        }
        ((TrainTeacher) objSearch).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        List<TrainTeacher> list = TraUtil.getListTeac((TrainTeacher) objSearch);
        listTeac = list;
        return SUCCESS;
    }

    public String addTeac() {
        ((TrainTeacher) objInfo).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        ((TrainTeacher) objInfo).setIntCreateUser(getUserSessionModel()
                .getIntUserID());
        TraUtil.addTeac((TrainTeacher) objInfo);

        ((TrainTeacher) objSearch).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        List<TrainTeacher> list = TraUtil.getListTeac((TrainTeacher) objSearch);
        listTeac = list;
        return SUCCESS;
    }

    public String copyTeacher() {
        String strTrainObjectFrom = ((TrainTeacher) objInfo)
                .getStrTrainObjectFrom();
        String strTrainObjectTo = ((TrainTeacher) objInfo)
                .getStrTrainObjectTo();
        TraUtil.copyTeacher(strTrainObjectFrom, strTrainObjectTo,
                getUserSessionModel().getIntUserID(), getUserSessionModel()
                .getIntGroupID());

        ((TrainTeacher) objSearch).setIntGroup(getUserSessionModel()
                .getIntGroupID());
        List<TrainTeacher> list = TraUtil.getListTeac((TrainTeacher) objSearch);
        listTeac = list;
        return SUCCESS;
    }

    public String selectListDic() {
        HttpServletRequest request = ServletActionContext.getRequest();
        TeacUtil teacUtil = new TeacUtil();
        if (null != request.getParameter("intDictionary")
                && "null".equals(request.getParameter("intDictionary")) == false) {
            teacUtil.setIntDictionary(Integer.valueOf(request
                    .getParameter("intDictionary")));
            resu = Integer.valueOf(request.getParameter("intDictionary"));
        } else {
            teacUtil.setIntDictionary(0);
        }
        teacUtil.setIntGroup(getUserSessionModel().getIntGroupID());
        List<TrainTeacher> list = TraUtil.getTeacListByDic(teacUtil);
        List<TrainDictionaryTeacherModel> listDictea = TraUtil
                .getObjectDicTea(teacUtil);
        listCout = listDictea;
        // System.out.println("看看长度："+listDictea.size());
        // System.out.println(list.size());
        listTeacSeTeachers = list;
        return SUCCESS;
    }

    public String savListDic() {
        HttpServletRequest request = ServletActionContext.getRequest();
        if (null != request.getParameter("intDictionary")) {
            resu = Integer.valueOf(request.getParameter("intDictionary"));
        }
        String intID[] = request.getParameterValues("checkAddDic");
        // System.out.println(intDictionary);
        TeacUtil teacUtil = new TeacUtil();
        teacUtil.setIntGroup(getUserSessionModel().getIntGroupID());
        teacUtil.setIntDictionary(Integer.valueOf(request
                .getParameter("intDictionary")));
        int results = 0;
        if (null != request.getParameter("intDictionary")) {
            results = Integer.valueOf(request.getParameter("intDictionary"));
        }
        TraUtil.deleteTeacDic(teacUtil);
        if (intID != null) {
            for (int i = 0; i < intID.length; i++) {
                TrainDictionaryTeacherModel trainDictionaryTeacherModel = new TrainDictionaryTeacherModel();
                trainDictionaryTeacherModel
                        .setIntCreateGroup(getUserSessionModel()
                                .getIntGroupID());
                trainDictionaryTeacherModel.setIntDictionary(results);
                trainDictionaryTeacherModel.setIntTeacher(Integer
                        .valueOf(intID[i]));
                trainDictionaryTeacherModel
                        .setIntCreateUser(getUserSessionModel().getIntUserID());
                TraUtil.addSavTec(trainDictionaryTeacherModel);
            }
        }
        return selectListDic();
    }

}
