/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.util.DateUtil;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainUserApplyModel;
import com.hode.train.model.TrainUserApplyDetailModel;
import com.hode.train.service.TrainUserApplyService;

public class TrainUserApplyAction extends AbstractBaseAction
{
    private static final long serialVersionUID = 4056983254160864601L;
    TrainUserApplyDetailModel trainUserApplyDetailInfo = new TrainUserApplyDetailModel();


    public TrainUserApplyDetailModel getTrainUserApplyDetailInfo() {
        return  trainUserApplyDetailInfo;
    }

    public void setTrainUserApplyDetailInfo(
            TrainUserApplyDetailModel trainUserApplyDetailInfo) {
        this.trainUserApplyDetailInfo = trainUserApplyDetailInfo;
    }

    public void initOptDB()
    {
        setInsertAndGetID(true);
    }

    protected void createObjInfo()
    {
        objInfo = new TrainUserApplyModel();
    }

    protected void createObjSearch()
    {
        objSearch = new TrainUserApplyModel();
    }

    public String add()
    {
        ((TrainUserApplyModel)objInfo).setIntCreateUserID(getUserSessionModel().getIntUserID());
        ((TrainUserApplyModel)objInfo).setStrCreateUserName(getUserSessionModel().getStrName());
        ((TrainUserApplyModel)objInfo).setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        ((TrainUserApplyModel)objInfo).setStrCreateGroupName(getUserSessionModel().getStrGroupName());
        ((TrainUserApplyModel)objInfo).setIntCreateUnitID(getUserSessionModel().getIntUnitID());
        ((TrainUserApplyModel)objInfo).setStrCreateUnitName(getUserSessionModel().getStrUnitName());
        ((TrainUserApplyModel)objInfo).setStrCreateDate(getNowDateByFormat(""));
        ((TrainUserApplyModel)objInfo).setIntYear(StringUtil.ObjectToInt(getNowDateByFormat("yyyy")));
        super.add();

        trainUserApplyDetailInfo.setIntMainID(intInsertID);
        ((TrainUserApplyService)ibatisService).addInsertDetail(trainUserApplyDetailInfo);
        return SUCCESS;
    }
    public String update()
    {
        super.update();
        ibatisService.deleteObject("deleteDetailObject",objInfo.getIntID()+"");
        trainUserApplyDetailInfo.setIntMainID(objInfo.getIntID());
        ((TrainUserApplyService)ibatisService).addInsertDetail(trainUserApplyDetailInfo);
        return SUCCESS;
    }
    public String delete()
    {
        super.delete();
        ibatisService.deleteObject("deleteDetailObject",objInfo.getIntID()+"");
        return SUCCESS;
    }

    public String showSearchDetailList()
    {
        pagination.setUserSessionDM(getUserSessionModel());
        int intTotal = ibatisService.getRecordCount("getDetailRecordCount",trainUserApplyDetailInfo);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("getDetailPageObjectList",trainUserApplyDetailInfo, pagination.getIntStartNum(), pagination.getIntLineNum());
        return SUCCESS;
    }

    public String searchStaticReport1()
    {
        String strSearchStartDate =  ((TrainUserApplyModel)objSearch).getStrSearchStartDate();
        if(strSearchStartDate == null || strSearchStartDate.length() < 4)
        {
            ((TrainUserApplyModel)objSearch).setStrSearchStartDate(DateUtil.getNowDateByFormat("yyyy")+"-01-01");
            ((TrainUserApplyModel)objSearch).setStrSearchEndDate(DateUtil.getNowDateByFormat("yyyy-MM-dd"));
        }
        else
        {
            System.out.println("not init");
        }
        return SUCCESS;
    }

}