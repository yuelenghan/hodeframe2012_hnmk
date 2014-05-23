package com.hode.train.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.train.model.Traincourse;
import com.hode.train.util.TraUtil;
import com.opensymphony.webwork.interceptor.ServletRequestAware;

public class CourseAction extends AbstractBaseAction implements
        ServletRequestAware {

    private static final long serialVersionUID = 2511576512085124015L;
    HttpServletRequest request;

    @Override
    protected void createObjInfo() {
        objInfo = new Traincourse();
    }

    @Override
    protected void createObjSearch() {
        objSearch = new Traincourse();

    }

    private List<Traincourse> traList = new ArrayList<Traincourse>();
    private Traincourse traincourses = new Traincourse();

    public Traincourse getTraincourses() {
        return traincourses;
    }

    public void setTraincourses(Traincourse traincourses) {
        this.traincourses = traincourses;
    }

    private int resu = 0;
    private int resus = 0;

    public int getResus() {
        return resus;
    }

    public void setResus(int resus) {
        this.resus = resus;
    }

    public int getResu() {
        return resu;
    }

    public void setResu(int resu) {
        this.resu = resu;
    }

    public List<Traincourse> getTraList() {
        return traList;
    }

    public void setTraList(List<Traincourse> traList) {
        this.traList = traList;
    }

	/*
	 * public void showExa(){ HttpServletRequest
	 * request=ServletActionContext.getRequest(); String
	 * carid=request.getParameter("cardid"); System.out.println("看看："+carid); }
	 */

    public String showList() {
        // System.out.println("部门："+((Traincourse)objSearch).getIntDictionary());
        int dic = ((Traincourse) objSearch).getIntDictionary();
        resu = dic;
        ((Traincourse) objSearch).setIntDictionary(dic);
        ((Traincourse) objSearch).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        List<Traincourse> list = TraUtil.getObjectList((Traincourse) objSearch);
        traList = list;
        return SUCCESS;
    }

    public String deleteCourse() {
        int res = ((Traincourse) objInfo).getIntID();
        if (res > 0) {
            TraUtil.deleteCourse((Traincourse) objInfo);
        }
        int dic = ((Traincourse) objSearch).getIntDictionary();
        resu = dic;
        ((Traincourse) objSearch).setIntDictionary(dic);
        ((Traincourse) objSearch).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        List<Traincourse> list = TraUtil.getObjectList((Traincourse) objSearch);
        traList = list;
        return SUCCESS;
    }

    public String addCourse() {
        ((Traincourse) objInfo).setIntCreateUser(getUserSessionModel()
                .getIntUserID());
        ((Traincourse) objInfo).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        TraUtil.insertCoures((Traincourse) objInfo);
        int dic = ((Traincourse) objSearch).getIntDictionary();
        resu = dic;
        ((Traincourse) objSearch).setIntDictionary(dic);
        ((Traincourse) objSearch).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        List<Traincourse> list = TraUtil.getObjectList((Traincourse) objSearch);
        traList = list;
        return SUCCESS;
    }

    public String copyCourse() {
        int dicrionaryFrom = ((Traincourse) objInfo).getIntDictionaryFrom();
        int dicrionaryTo = ((Traincourse) objInfo).getIntDictionaryTo();

        TraUtil.copyCourse(dicrionaryFrom, dicrionaryTo, getUserSessionModel()
                .getIntUserID(), getUserSessionModel().getIntGroupID());

        int dic = ((Traincourse) objSearch).getIntDictionary();
        resu = dic;
        ((Traincourse) objSearch).setIntDictionary(dic);
        ((Traincourse) objSearch).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        List<Traincourse> list = TraUtil.getObjectList((Traincourse) objSearch);
        traList = list;
        return SUCCESS;
    }

    public String selectUpdate() {
        ((Traincourse) objSearch).getIntID();
        resu = ((Traincourse) objSearch).getIntDictionary();
        Traincourse traincourse = TraUtil
                .selectCourse(((Traincourse) objSearch).getIntID());
        traincourses = traincourse;
        resus = traincourse.getIntDictionary();
        // System.out.println(traincourse.getStrCourseName());
        return SUCCESS;
    }

    public String update() {
        if (((Traincourse) objInfo).getIntID() > 0) {
            TraUtil.updateCourse((Traincourse) objInfo);
        }
        int dic = ((Traincourse) objSearch).getIntDictionary();
        resu = dic;
        ((Traincourse) objSearch).setIntDictionary(dic);
        ((Traincourse) objSearch).setIntCreateGroup(getUserSessionModel()
                .getIntGroupID());
        List<Traincourse> list = TraUtil.getObjectList((Traincourse) objSearch);
        traList = list;
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        request = arg0;
    }

}
