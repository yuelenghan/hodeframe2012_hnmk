package com.hode.train.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.framework.commons.file.FileUtil;
import com.hode.train.model.TrainPersonModel;
import com.hode.train.model.TrainScoreDetailModel;
import com.hode.train.model.TrainStudentDetailModel;
import com.hode.train.model.TrainStudentModel;
import com.hode.train.model.TrainUserApplyDetailModel;
import com.hode.train.model.TrainUserChangeDetailModel;
import com.hode.train.service.TrainPersonService;
import com.hode.train.util.ConstantUtil;
import com.hode.train.util.TrainUtil;

/**
 * 
 * @author lh
 *
 */
public class TrainPersonAction extends AbstractBaseAction {

    private static final long serialVersionUID = -2493248430675063104L;
    private List<TrainPersonModel> personList;
    private Map<String, Object> detailRecord;
    private List<TrainStudentDetailModel> studentDetailList;
    private List<TrainScoreDetailModel> scoreDetailList;
    private List<TrainUserApplyDetailModel> userApplyDetailList;
    private List<TrainUserChangeDetailModel> userChangeDetailList;
    private TrainPersonModel person;
    private List<TrainPersonModel> existPicPersons;

    /**
     * 图片信息归档 初始化档案库图片信息
     */
    public void savePersonPic() {
        List<TrainStudentModel> list = TrainUtil.getAllTrainStudent();
        if (list != null && list.size() > 0) {
            for (TrainStudentModel trainStudentModel : list) {
                int intID = trainStudentModel.getIntID();
                int intGroupID = trainStudentModel.getIntCreateGroupID();

                String path = ConstantUtil.TRAIN_PIC_ROOT_PATH + intID;
                if (new File(path).exists()) {
                    if (!new File(ConstantUtil.RECORD_PIC_ROOT_PATH + intGroupID).exists()) {
                        new File(ConstantUtil.RECORD_PIC_ROOT_PATH + intGroupID).mkdirs();
                    }
                    FileUtil.copyAllDir(path, ConstantUtil.RECORD_PIC_ROOT_PATH + intGroupID);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public String showPersonRecord() {
        int intGroupID = getUserSessionModel().getIntGroupID();
        if (intGroupID != 12 && intGroupID != 1) {
            // 不是省中心用户，并且不是系统管理员
            // 只查询当前培训机构的人员档案信息
            ((TrainPersonModel) objSearch).setIntGroupID(intGroupID);
        }

        // 处理年龄
        int intStartAge = ((TrainPersonModel) objSearch).getIntStartAge();
        int intEndAge = ((TrainPersonModel) objSearch).getIntEndAge();
        if (intStartAge > intEndAge) {
            ((TrainPersonModel) objSearch).setIntStartAge(intEndAge);
            ((TrainPersonModel) objSearch).setIntEndAge(intStartAge);
        }

        pagination.setUserSessionDM(getUserSessionModel());
        int intTotal = ((TrainPersonService) ibatisService).getRecordCount(
                "personCount", objSearch);
        pagination.setIntTotalNum(intTotal);

        personList = ((TrainPersonService) ibatisService).getPageObjectList(
                "getPerson", objSearch, pagination.getIntStartNum(),
                pagination.getIntLineNum());
        return SUCCESS;
    }

    /**
     * 显示人员档案详细信息
     *
     * @return
     */
    public String showDetailRecord() {
        detailRecord = new HashMap<String, Object>();

        try {
            // 基础信息和图片信息
            ((TrainPersonService) ibatisService).genPersonBasicRecord(
                    detailRecord, (TrainPersonModel) objInfo);

            // 培训记录
            ((TrainPersonService) ibatisService).genPersonTrainRecord(
                    detailRecord, (TrainPersonModel) objInfo);

            // 成绩信息
            ((TrainPersonService) ibatisService).genPersonScoreRecord(
                    detailRecord, (TrainPersonModel) objInfo);

            // 补证信息
            ((TrainPersonService) ibatisService).genPersonApplyRecord(
                    detailRecord, (TrainPersonModel) objInfo);

            // 变更记录
            ((TrainPersonService) ibatisService).genPersonChangeRecord(
                    detailRecord, (TrainPersonModel) objInfo);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 导入基础信息时，使用档案库中的信息
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public String usePersonRecord() {
        // 档案库中已经存在的人员列表
        List<TrainStudentDetailModel> existStudents = (List<TrainStudentDetailModel>) this
                .getSessionModel().get("existStudents");

        try {
            existPicPersons = ((TrainPersonService) ibatisService)
                    .usePersonRecord(existStudents);
            this.getSessionModel().put("existPicPersons", existPicPersons);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    /**
     * 使用新信息并更新档案库
     *
     * @return
     */
    @SuppressWarnings("unchecked")
    public String useNewAndUpdate() {
        // 档案库中已经存在的人员列表
        List<TrainStudentDetailModel> existStudents = (List<TrainStudentDetailModel>) this
                .getSessionModel().get("existStudents");

        try {
            existPicPersons = ((TrainPersonService) ibatisService)
                    .useNewAndUpdate(existStudents);
            this.getSessionModel().put("existPicPersons", existPicPersons);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    @SuppressWarnings("unchecked")
    public String importRecordPic() {
        List<TrainPersonModel> existPicPersons = (List<TrainPersonModel>) this
                .getSessionModel().get("existPicPersons");

        try {
            ((TrainPersonService) ibatisService)
                    .importRecordPic(existPicPersons);
        } catch (Exception e) {
            e.printStackTrace();
            return ERROR;
        }

        return SUCCESS;
    }

    public String ignoreRecordPic() {
        return SUCCESS;
    }

    @Override
    protected void createObjInfo() {
        objInfo = new TrainPersonModel();
    }

    @Override
    protected void createObjSearch() {
        objSearch = new TrainPersonModel();
    }

    public List<TrainPersonModel> getPersonList() {
        return personList;
    }

    public void setPersonList(List<TrainPersonModel> personList) {
        this.personList = personList;
    }

    public Map<String, Object> getDetailRecord() {
        return detailRecord;
    }

    public void setDetailRecord(Map<String, Object> detailRecord) {
        this.detailRecord = detailRecord;
    }

    public List<TrainStudentDetailModel> getStudentDetailList() {
        return studentDetailList;
    }

    public void setStudentDetailList(
            List<TrainStudentDetailModel> studentDetailList) {
        this.studentDetailList = studentDetailList;
    }

    public List<TrainUserApplyDetailModel> getUserApplyDetailList() {
        return userApplyDetailList;
    }

    public void setUserApplyDetailList(
            List<TrainUserApplyDetailModel> userApplyDetailList) {
        this.userApplyDetailList = userApplyDetailList;
    }

    public List<TrainUserChangeDetailModel> getUserChangeDetailList() {
        return userChangeDetailList;
    }

    public void setUserChangeDetailList(
            List<TrainUserChangeDetailModel> userChangeDetailList) {
        this.userChangeDetailList = userChangeDetailList;
    }

    public TrainPersonModel getPerson() {
        return person;
    }

    public void setPerson(TrainPersonModel person) {
        this.person = person;
    }

    public List<TrainScoreDetailModel> getScoreDetailList() {
        return scoreDetailList;
    }

    public void setScoreDetailList(List<TrainScoreDetailModel> scoreDetailList) {
        this.scoreDetailList = scoreDetailList;
    }

    public List<TrainPersonModel> getExistPicPersons() {
        return existPicPersons;
    }

    public void setExistPicPersons(List<TrainPersonModel> existPicPersons) {
        this.existPicPersons = existPicPersons;
    }
}