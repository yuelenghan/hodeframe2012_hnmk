/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainUserApplyDetailModel;

public class TrainUserApplyService extends IbatisService
{
    public void addInsertDetail(TrainUserApplyDetailModel trainUserApplyDetailInfo)
    {

        String strArr[] = trainUserApplyDetailInfo.getStrUserCodeArr();
        if(strArr != null && strArr.length > 0)
        {
            TrainUserApplyDetailModel trainUserApplyDetailModel = new TrainUserApplyDetailModel();
            trainUserApplyDetailModel.setIntMainID(trainUserApplyDetailInfo.getIntMainID());
            for(int i=0;i<strArr.length;i++)
            {
                trainUserApplyDetailModel.setStrCertCode(trainUserApplyDetailInfo.getStrCertCodeArr()[i]);//证书号码1
                trainUserApplyDetailModel.setStrUserCode(trainUserApplyDetailInfo.getStrUserCodeArr()[i]);//身份证
                trainUserApplyDetailModel.setStrUserName(trainUserApplyDetailInfo.getStrUserNameArr()[i]);//姓名
                trainUserApplyDetailModel.setStrUserDate(trainUserApplyDetailInfo.getStrUserDateArr()[i]);//补正日期
                trainUserApplyDetailModel.setStrUserUnitName(trainUserApplyDetailInfo.getStrUserUnitNameArr()[i]);//单位名称
                trainUserApplyDetailModel.setStrUserPost(trainUserApplyDetailInfo.getStrUserPostArr()[i]);//职务
                trainUserApplyDetailModel.setStrApplyReason(trainUserApplyDetailInfo.getStrApplyReasonArr()[i]);//补证理由
                trainUserApplyDetailModel.setIntOrderID(i);//排序号
                this.insertObject("insertDetailObject", trainUserApplyDetailModel);

            }// for(int i=0;i<dataArr.length;i++)
        }
    }
}