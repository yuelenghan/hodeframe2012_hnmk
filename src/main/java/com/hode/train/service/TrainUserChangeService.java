/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainUserChangeDetailModel;

public class TrainUserChangeService extends IbatisService
{
    public void addInsertDetail(TrainUserChangeDetailModel trainUserChangeDetailInfo)
    {

        String strArr[] = trainUserChangeDetailInfo.getStrUserCodeArr();
        if(strArr != null && strArr.length > 0)
        {
            TrainUserChangeDetailModel trainUserChangeDetailModel = new TrainUserChangeDetailModel();
            trainUserChangeDetailModel.setIntMainID(trainUserChangeDetailInfo.getIntMainID());
            for(int i=0;i<strArr.length;i++)
            {
                trainUserChangeDetailModel.setStrCertCode(trainUserChangeDetailInfo.getStrCertCodeArr()[i]);//证书号码
                trainUserChangeDetailModel.setStrUserCode(trainUserChangeDetailInfo.getStrUserCodeArr()[i]);//身份证
                trainUserChangeDetailModel.setStrUserName(trainUserChangeDetailInfo.getStrUserNameArr()[i]);//姓名
                trainUserChangeDetailModel.setStrUserDate(trainUserChangeDetailInfo.getStrUserDateArr()[i]);//补正日期

                trainUserChangeDetailModel.setStrFromTitles(trainUserChangeDetailInfo.getStrFromTitlesArr()[i]);//原工作单位的职务
                trainUserChangeDetailModel.setStrToTitles(trainUserChangeDetailInfo.getStrToTitlesArr()[i]);//现工作单位的职务
                trainUserChangeDetailModel.setStrFromUnit(trainUserChangeDetailInfo.getStrFromUnitArr()[i]);//原工作单位的名称
                trainUserChangeDetailModel.setStrToUnit(trainUserChangeDetailInfo.getStrToUnitArr()[i]);//现工作单位的名称

                trainUserChangeDetailModel.setIntOrderID(i);//排序号
                this.insertObject("insertDetailObject", trainUserChangeDetailModel);

            }// for(int i=0;i<dataArr.length;i++)
        }
    }
}