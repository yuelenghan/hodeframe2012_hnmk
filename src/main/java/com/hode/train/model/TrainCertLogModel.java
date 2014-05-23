/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

import com.hode.framework.model.IbatisModel;

/**
 * 证书日志
 *
 * @author yuelenghan
 *
 */
public class TrainCertLogModel extends IbatisModel {

    private static final long serialVersionUID = 6693402659351231138L;

    private int intID;
    private String strUserName;
    private String strIp;
    private String strDate;
    private String strUri;
    private int intUserID;

    public int getIntID() {
        return intID;
    }

    public void setIntID(int intID) {
        this.intID = intID;
    }

    public String getStrUserName() {
        return strUserName;
    }

    public void setStrUserName(String strUserName) {
        this.strUserName = strUserName;
    }

    public String getStrIp() {
        return strIp;
    }

    public void setStrIp(String strIp) {
        this.strIp = strIp;
    }

    public String getStrDate() {
        return strDate;
    }

    public void setStrDate(String strDate) {
        this.strDate = strDate;
    }

    public String getStrUri() {
        return strUri;
    }

    public void setStrUri(String strUri) {
        this.strUri = strUri;
    }

    public int getIntUserID() {
        return intUserID;
    }

    public void setIntUserID(int intUserID) {
        this.intUserID = intUserID;
    }
}