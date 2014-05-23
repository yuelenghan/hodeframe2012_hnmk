package com.hode.ajax;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hode.train.model.ApplyExamineeModel;
import com.hode.train.model.TrainExamineeModel;
import com.hode.train.util.TrainUtil;

public class verifyIdentity_ajax extends HttpServlet{

	private static final long serialVersionUID = -7409356395590374711L;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void doGet (HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
    {
      String identity = request.getParameter("identitycard");
      StringBuffer sb = new StringBuffer();
      List<TrainExamineeModel> list = new ArrayList();
      List<ApplyExamineeModel> list1 = new ArrayList();
      list = TrainUtil.checkIdentity(identity);
      int intApply = 0;
      if(list.size()==1){
//    	  list.get(0).
          ApplyExamineeModel model = new ApplyExamineeModel();
          model.setIntExamineeID(list.get(0).getIntID());
          list1 = TrainUtil.getObjectListByTrainApplyID(model);
          intApply = list1.get(0).getIntTrainApplyID();
          sb.append("1").append(",").append(identity).append(",").append(intApply);
      }
      else if(list.size()==0)
    	  sb.append("0").append(",").append(identity).append(",").append(intApply);
      else
    	  sb.append("2").append(",").append(identity).append(",").append(intApply);
      PrintWriter out = response.getWriter();
      out.println(sb.toString());
      out.flush();
    }

    public void doPost(HttpServletRequest request,HttpServletResponse response)
    throws ServletException,IOException
    {
       doGet(request,response);
    }

}
