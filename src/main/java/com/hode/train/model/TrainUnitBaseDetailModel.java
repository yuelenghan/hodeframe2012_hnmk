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
 * @author xdju
 *  培训机构提交办班申请
 */
public class TrainUnitBaseDetailModel extends IbatisModel
{
    private static final long serialVersionUID = -5363511659097377909L;
    private int intMainID;//
    private String strName;//姓名
    private String strSex;//性别
    private String strIDCode;//身份证号码
    private String strCertType;//证书类别
    private String strCertCode;//证书号码
    private String strCreateDate;//领证时间
    private String strEndDate;//证书到期时间
    private String strStatus;//状态


    private String strNameArr[];//姓名
    private String strSexArr[];//性别
    private String strIDCodeArr[];//身份证号码
    private String strCertTypeArr[];//证书类别
    private String strCertCodeArr[];//证书号码
    private String strCreateDateArr[];//领证时间
    private String strEndDateArr[];//证书到期时间
    private String strStatusArr[];//状态


    public int getIntMainID() {
        return intMainID;
    }
    public void setIntMainID(int intMainID) {
        this.intMainID = intMainID;
    }
    public String getStrName() {
        return (strName == null) ? "" : strName;
    }
    public void setStrName(String strName) {
        this.strName = strName;
    }
    public String getStrSex() {
        return (strSex == null) ? "" : strSex;
    }
    public void setStrSex(String strSex) {
        this.strSex = strSex;
    }
    public String getStrIDCode() {
        return (strIDCode == null) ? "" : strIDCode;
    }
    public void setStrIDCode(String strIDCode) {
        this.strIDCode = strIDCode;
    }
    public String getStrCertType() {
        return (strCertType == null) ? "" : strCertType;
    }
    public void setStrCertType(String strCertType) {
        this.strCertType = strCertType;
    }
    public String getStrCertCode() {
        return (strCertCode == null) ? "" : strCertCode;
    }
    public void setStrCertCode(String strCertCode) {
        this.strCertCode = strCertCode;
    }
    public String getStrCreateDate() {
        return (strCreateDate == null) ? "" : strCreateDate;
    }
    public void setStrCreateDate(String strCreateDate) {
        this.strCreateDate = strCreateDate;
    }
    public String getStrEndDate() {
        return (strEndDate == null) ? "" : strEndDate;
    }
    public void setStrEndDate(String strEndDate) {
        this.strEndDate = strEndDate;
    }
    public String getStrStatus() {
        return (strStatus == null) ? "" : strStatus;
    }
    public void setStrStatus(String strStatus) {
        this.strStatus = strStatus;
    }
    public String[] getStrNameArr() {
        return strNameArr;
    }
    public void setStrNameArr(String[] strNameArr) {
        this.strNameArr = strNameArr;
    }
    public String[] getStrSexArr() {
        return strSexArr;
    }
    public void setStrSexArr(String[] strSexArr) {
        this.strSexArr = strSexArr;
    }
    public String[] getStrIDCodeArr() {
        return strIDCodeArr;
    }
    public void setStrIDCodeArr(String[] strIDCodeArr) {
        this.strIDCodeArr = strIDCodeArr;
    }
    public String[] getStrCertTypeArr() {
        return  strCertTypeArr;
    }
    public void setStrCertTypeArr(String[] strCertTypeArr) {
        this.strCertTypeArr = strCertTypeArr;
    }
    public String[] getStrCertCodeArr() {
        return strCertCodeArr;
    }
    public void setStrCertCodeArr(String[] strCertCodeArr) {
        this.strCertCodeArr = strCertCodeArr;
    }
    public String[] getStrCreateDateArr() {
        return  strCreateDateArr;
    }
    public void setStrCreateDateArr(String[] strCreateDateArr) {
        this.strCreateDateArr = strCreateDateArr;
    }
    public String[] getStrEndDateArr() {
        return  strEndDateArr;
    }
    public void setStrEndDateArr(String[] strEndDateArr) {
        this.strEndDateArr = strEndDateArr;
    }
    public String[] getStrStatusArr() {
        return strStatusArr;
    }
    public void setStrStatusArr(String[] strStatusArr) {
        this.strStatusArr = strStatusArr;
    }



}