package com.hode.train.action;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.train.model.TrainingProgramsModel;
import com.hode.train.util.TraUtil;

public class TrainingProgramsAction extends AbstractBaseAction{

	
	private static final long serialVersionUID = 6392218646959982770L;


	@Override
	protected void createObjInfo() {
      objInfo=new TrainingProgramsModel();
	}
	
	private TrainingProgramsModel trainingProgramsModel=new TrainingProgramsModel();

	
	public TrainingProgramsModel getTrainingProgramsModel() {
		return trainingProgramsModel;
	}

	public void setTrainingProgramsModel(TrainingProgramsModel trainingProgramsModel) {
		this.trainingProgramsModel = trainingProgramsModel;
	}

	@Override
	protected void createObjSearch() {
		objSearch=new TrainingProgramsModel();
		
	}
	
	public String listTrp(){
		//((TrainingProgramsModel)objInfo).setIntCreateUser(getUserSessionModel().getIntUserID());
		return SUCCESS;
	}
	
	public String addTrp(){
		String name=((TrainingProgramsModel)objInfo).getStrItemName();
		String prict=((TrainingProgramsModel)objInfo).getStrPrice();
		((TrainingProgramsModel)objInfo).setStrItemName(name);
		((TrainingProgramsModel)objInfo).setStrPrice(prict);
		((TrainingProgramsModel)objInfo).setIntCreateUser(getUserSessionModel().getIntUserID());
		((TrainingProgramsModel)objInfo).setIntCreateGroup(getUserSessionModel().getIntGroupID());
		ibatisService.insertObject(objInfo);
		return SUCCESS;
	}
	
	public String deleteTrpId(){
		ibatisService.deleteObject(objInfo);
		return SUCCESS;
	}
	
	public String selectUpdate(){
		//if(((TrainingProgramsModel)objInfo).getIntID()>0){
			TrainingProgramsModel tra=TraUtil.trainPro(((TrainingProgramsModel)objInfo).getIntID());
			trainingProgramsModel=tra;
		//}
		return SUCCESS;
	}
	
	public String updatePro(){
		if(((TrainingProgramsModel)objInfo).getIntID()>0){
		TraUtil.updatePro(((TrainingProgramsModel)objInfo));
		}
		return SUCCESS;
	}
	
	//public String


}
