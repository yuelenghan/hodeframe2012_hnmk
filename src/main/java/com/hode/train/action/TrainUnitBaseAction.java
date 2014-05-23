/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.util.StringUtil;
import com.hode.train.model.TrainUnitBaseModel;
import com.hode.train.model.TrainUnitBaseDetailModel;
import com.hode.train.service.TrainUnitBaseService;
import com.opensymphony.webwork.ServletActionContext;

public class TrainUnitBaseAction extends AbstractBaseAction
{
    private static final long serialVersionUID = -323337772317018626L;
    TrainUnitBaseDetailModel trainUnitBaseDetailModel = new TrainUnitBaseDetailModel();



    public TrainUnitBaseDetailModel getTrainUnitBaseDetailModel() {
        return  trainUnitBaseDetailModel;
    }

    public void setTrainUnitBaseDetailModel(
            TrainUnitBaseDetailModel trainUnitBaseDetailModel) {
        this.trainUnitBaseDetailModel = trainUnitBaseDetailModel;
    }

    public void initOptDB()
    {
        setInsertAndGetID(true);
    }
    public int intSelectType; //选择类型

    protected void createObjInfo()
    {
        objInfo = new TrainUnitBaseModel();
    }

    protected void createObjSearch()
    {
        objSearch = new TrainUnitBaseModel();
    }
    public String showAddFrm()
    {
        HttpServletRequest request = ServletActionContext.getRequest();
        int intOldSelectType = StringUtil.ObjectToInt(request.getSession().getAttribute(("searchTrainApplyType_session")));
        intSelectType = (intSelectType > 0)?intSelectType:intOldSelectType;
        request.getSession().setAttribute("searchTrainApplyType_session",intSelectType+"");
        return super.showAddFrm();
    }

    public String add()
    {
        super.add();
        trainUnitBaseDetailModel.setIntMainID(intInsertID);
        ((TrainUnitBaseService)ibatisService).addInsertDetail(trainUnitBaseDetailModel);
        return SUCCESS;
    }
    public String update()
    {
        super.update();
        ibatisService.deleteObject("deleteDetailObject",objInfo.getIntID()+"");
        trainUnitBaseDetailModel.setIntMainID(objInfo.getIntID());
        ((TrainUnitBaseService)ibatisService).addInsertDetail(trainUnitBaseDetailModel);
        return SUCCESS;
    }
    public String delete()
    {
        super.delete();
        ibatisService.deleteObject("deleteDetailObject",objInfo.getIntID()+"");
        return SUCCESS;
    }
    //////////////////////////////////////////////////三级机构 start ////////////////////////////


}