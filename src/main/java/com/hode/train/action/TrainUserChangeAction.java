/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainUserChangeDetailModel;
import com.hode.train.model.TrainUserChangeModel;
import com.hode.train.service.TrainUserChangeService;

public class TrainUserChangeAction extends AbstractBaseAction
{
    private static final long serialVersionUID = 6015657404332681724L;
    TrainUserChangeDetailModel trainUserChangeDetailInfo = new TrainUserChangeDetailModel();


    public TrainUserChangeDetailModel getTrainUserChangeDetailInfo() {
        return trainUserChangeDetailInfo;
    }

    public void setTrainUserChangeDetailInfo(
            TrainUserChangeDetailModel trainUserChangeDetailInfo) {
        this.trainUserChangeDetailInfo = trainUserChangeDetailInfo;
    }

    public void initOptDB()
    {
        setInsertAndGetID(true);
    }

    protected void createObjInfo()
    {
        objInfo = new TrainUserChangeModel();
    }

    protected void createObjSearch()
    {
        objSearch = new TrainUserChangeModel();
    }

    public String add()
    {
        ((TrainUserChangeModel)objInfo).setIntCreateUserID(getUserSessionModel().getIntUserID());
        ((TrainUserChangeModel)objInfo).setStrCreateUserName(getUserSessionModel().getStrName());
        ((TrainUserChangeModel)objInfo).setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        ((TrainUserChangeModel)objInfo).setStrCreateGroupName(getUserSessionModel().getStrGroupName());
        ((TrainUserChangeModel)objInfo).setIntCreateUnitID(getUserSessionModel().getIntUnitID());
        ((TrainUserChangeModel)objInfo).setStrCreateUnitName(getUserSessionModel().getStrUnitName());
        ((TrainUserChangeModel)objInfo).setStrCreateDate(getNowDateByFormat(""));
        ((TrainUserChangeModel)objInfo).setIntYear(StringUtil.ObjectToInt(getNowDateByFormat("yyyy")));
        super.add();

        trainUserChangeDetailInfo.setIntMainID(intInsertID);
        ((TrainUserChangeService)ibatisService).addInsertDetail(trainUserChangeDetailInfo);
        return SUCCESS;
    }
    public String update()
    {
        super.update();
        ibatisService.deleteObject("deleteDetailObject",objInfo.getIntID()+"");
        trainUserChangeDetailInfo.setIntMainID(objInfo.getIntID());
        ((TrainUserChangeService)ibatisService).addInsertDetail(trainUserChangeDetailInfo);
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
        int intTotal = ibatisService.getRecordCount("getDetailRecordCount",trainUserChangeDetailInfo);
        pagination.setIntTotalNum(intTotal);
        objInfoList = ibatisService.getPageObjectList("getDetailPageObjectList",trainUserChangeDetailInfo, pagination.getIntStartNum(), pagination.getIntLineNum());
        return SUCCESS;
    }

}