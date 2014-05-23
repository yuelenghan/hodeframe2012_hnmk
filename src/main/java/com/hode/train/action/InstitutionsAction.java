package com.hode.train.action;

import javax.servlet.http.HttpServletRequest;

import com.hode.framework.action.AbstractBaseAction;
import com.hode.rbac.model.GroupModel;
import com.opensymphony.webwork.interceptor.ServletRequestAware;

@SuppressWarnings("serial")
public class InstitutionsAction extends AbstractBaseAction implements ServletRequestAware{

    HttpServletRequest request;
    //private IbatisService ibatisService;
    @Override
    protected void createObjInfo() {
        objInfo=new GroupModel();
    }

/*	public void setIbatisService(IbatisService ibatisService) {
		this.ibatisService = ibatisService;
	}*/

    @Override
    protected void createObjSearch() {
        objSearch=new GroupModel();
    }

    public String showInstitutions(){

        return SUCCESS;
    }

    public String updateInstitutions(){
        //System.out.println(request.getParameter("objInfo.strTrainRange"));
        //GroupModel groupModel=new GroupModel();

        String strAddress=((GroupModel)objInfo).getStrAddress();
        String strMachineCode=((GroupModel)objInfo).getStrMachineCode();
        int id=((GroupModel)objInfo).getIntID();
        System.out.println("ID："+id);
        System.out.println("黄："+strMachineCode);
        ((GroupModel)objInfo).setStrAddress(strAddress);
        ((GroupModel)objInfo).setStrMachineCode(strMachineCode);
        if(null==ibatisService){
            System.out.println("为空！");
        }
        ibatisService.updateObject(objInfo);
		/*if(null==objInfo){
			System.out.println("为空");
		}
		super.update();*/
        return SUCCESS;
    }

    public void setServletRequest(HttpServletRequest arg0) {
        request=arg0;
    }

}
