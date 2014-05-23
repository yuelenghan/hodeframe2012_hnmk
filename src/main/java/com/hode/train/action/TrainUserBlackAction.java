/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.action;

import static com.hode.framework.commons.util.DateUtil.getNowDateByFormat;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.train.model.TrainUserBlackModel;

public class TrainUserBlackAction extends AbstractBaseAction
{
    private static final long serialVersionUID = -5557079038745914885L;

    protected void createObjInfo()
    {
        objInfo = new TrainUserBlackModel();
    }

    protected void createObjSearch()
    {
        objSearch = new TrainUserBlackModel();
    }

    public String add()
    {
//    	String strStudentIDCode = ((TrainUserBlackModel)objInfo).getStrStudentIDCode();
//
//    	int count = ibatisService.getRecordCount("getStudentCountByIDCode", strStudentIDCode);
//    	if(count > 0 && count == 1) {
//    		TrainStudentDetailModel trainStudentDetailModel = new TrainStudentDetailModel();
//    		trainStudentDetailModel.setStrStudentIDCode(strStudentIDCode);
//    		trainStudentDetailModel.setIntIsBlackUser(1);
//    		ibatisService.updateObject("updateStudentDetail", trainStudentDetailModel);
//    	} else if(count == 0) {
//
//    	}


        ((TrainUserBlackModel)objInfo).setIntCreateUserID(getUserSessionModel().getIntUserID());
        ((TrainUserBlackModel)objInfo).setStrCreateUserName(getUserSessionModel().getStrName());
        ((TrainUserBlackModel)objInfo).setIntCreateGroupID(getUserSessionModel().getIntGroupID());
        ((TrainUserBlackModel)objInfo).setStrCreateGroupName(getUserSessionModel().getStrGroupName());
        ((TrainUserBlackModel)objInfo).setIntCreateUnitID(getUserSessionModel().getIntUnitID());
        ((TrainUserBlackModel)objInfo).setStrCreateUnitName(getUserSessionModel().getStrUnitName());
        ((TrainUserBlackModel)objInfo).setStrCreateDate(getNowDateByFormat(""));
        super.add();
        return SUCCESS;
    }

}