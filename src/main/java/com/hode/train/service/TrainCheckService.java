/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.service;
import com.hode.framework.service.IbatisService;
import com.hode.train.model.TrainCheckDetailModel;

public class TrainCheckService extends IbatisService
{
    public void addInsertDetail(TrainCheckDetailModel trainCheckDetailInfo)
    {

        if(trainCheckDetailInfo.getIntTrainCheckID() > 0 )
        {
            String strArr[] = trainCheckDetailInfo.getStrSubjectNameArr();
            if(strArr != null && strArr.length > 0)
            {
                TrainCheckDetailModel trainCheckDetailModel = new TrainCheckDetailModel();
                trainCheckDetailModel.setIntTrainCheckID(trainCheckDetailInfo.getIntTrainCheckID());
                for(int i=0;i<strArr.length;i++)
                {
                    trainCheckDetailModel.setStrSubjectName(trainCheckDetailInfo.getStrSubjectNameArr()[i]);//课程名称
                    trainCheckDetailModel.setStrSubjectTime(trainCheckDetailInfo.getStrSubjectTimeArr()[i]);//时数
                    trainCheckDetailModel.setIntOrderID(i);//排序号
                    this.insertObject("insertDetailObject", trainCheckDetailModel);

                }// for(int i=0;i<dataArr.length;i++)
            }
        }
    }
}