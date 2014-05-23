/*
 * Created on 2005-2-21
 */
/*
 * Created on 2005-7-14
 * write by xdju
 */
package com.hode.train.model;

/**
 * @author xdju
 *  培训机构提交办班申请
 */
public class TrainUnitBaseModel extends TrainBaseModel
{
    private static final long serialVersionUID = -9043037296865048627L;
    private String strUnitName;//企业名称
    private String strSafeCode;//安全生产许可证
    private String strUnitType;//经济类型
    private String strMakeInfo;//生产能力
    private String strMainName;//主要负责人
    private String strMainLeader;//矿长
    private int intMainUser;//三项岗位人员
    private int intMainUserLess;//三项岗位人员最低配备数


    public String getStrUnitName() {
        return (strUnitName == null) ? "" : strUnitName;
    }
    public void setStrUnitName(String strUnitName) {
        this.strUnitName = strUnitName;
    }
    public String getStrSafeCode() {
        return (strSafeCode == null) ? "" : strSafeCode;
    }
    public void setStrSafeCode(String strSafeCode) {
        this.strSafeCode = strSafeCode;
    }
    public String getStrUnitType() {
        return (strUnitType == null) ? "" : strUnitType;
    }
    public void setStrUnitType(String strUnitType) {
        this.strUnitType = strUnitType;
    }
    public String getStrMakeInfo() {
        return (strMakeInfo == null) ? "" : strMakeInfo;
    }
    public void setStrMakeInfo(String strMakeInfo) {
        this.strMakeInfo = strMakeInfo;
    }
    public String getStrMainName() {
        return (strMainName == null) ? "" : strMainName;
    }
    public void setStrMainName(String strMainName) {
        this.strMainName = strMainName;
    }
    public String getStrMainLeader() {
        return (strMainLeader == null) ? "" : strMainLeader;
    }
    public void setStrMainLeader(String strMainLeader) {
        this.strMainLeader = strMainLeader;
    }
    public int getIntMainUser() {
        return intMainUser;
    }
    public void setIntMainUser(int intMainUser) {
        this.intMainUser = intMainUser;
    }
    public int getIntMainUserLess() {
        return intMainUserLess;
    }
    public void setIntMainUserLess(int intMainUserLess) {
        this.intMainUserLess = intMainUserLess;
    }




}