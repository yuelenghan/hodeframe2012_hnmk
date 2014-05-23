/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainUnitBaseDetailModel;

public class TrainUnitBaseService extends IbatisService
{
    public void addInsertDetail(TrainUnitBaseDetailModel trainUnitBaseDetailModel_1)
    {

        if(trainUnitBaseDetailModel_1.getIntMainID() > 0)
        {
            String strArr[] = trainUnitBaseDetailModel_1.getStrNameArr();
            if(strArr != null && strArr.length > 0)
            {
                TrainUnitBaseDetailModel trainUnitBaseDetailModel = new TrainUnitBaseDetailModel();
                trainUnitBaseDetailModel.setIntMainID(trainUnitBaseDetailModel_1.getIntMainID());
                for(int i=0;i<strArr.length;i++)
                {
                    trainUnitBaseDetailModel.setStrName(trainUnitBaseDetailModel_1.getStrNameArr()[i]);//
                    trainUnitBaseDetailModel.setStrSex(trainUnitBaseDetailModel_1.getStrSexArr()[i]);//
                    trainUnitBaseDetailModel.setStrIDCode(trainUnitBaseDetailModel_1.getStrIDCodeArr()[i]);//
                    trainUnitBaseDetailModel.setStrCertType(trainUnitBaseDetailModel_1.getStrCertTypeArr()[i]);//
                    trainUnitBaseDetailModel.setStrCertCode(trainUnitBaseDetailModel_1.getStrCertCodeArr()[i]);//
                    trainUnitBaseDetailModel.setStrCreateDate(trainUnitBaseDetailModel_1.getStrCreateDateArr()[i]);//
                    trainUnitBaseDetailModel.setStrEndDate(trainUnitBaseDetailModel_1.getStrEndDateArr()[i]);//
                    trainUnitBaseDetailModel.setStrStatus(trainUnitBaseDetailModel_1.getStrStatusArr()[i]);//
                    trainUnitBaseDetailModel.setIntOrderID(i);//排序号
                    this.insertObject("insertDetailObject", trainUnitBaseDetailModel);

                }// for(int i=0;i<dataArr.length;i++)
            }
        }
    }
}