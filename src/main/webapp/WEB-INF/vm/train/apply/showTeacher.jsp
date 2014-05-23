<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.hode.train.model.TrainTeacher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="/hodeframe2012_hnmk/js/jquery-1.8.2.js"></script>
<title>选择教师信息</title>
</head>
<body>
<table id="t">
<tr>
<th></th><th>教师姓名</th><th>学历</th><th>专业</th><th>是否持教师证</th><th>职称</th>
</tr>
<%
List<TrainTeacher> ts = (List<TrainTeacher>)request.getAttribute("allTeachers");
for(TrainTeacher t : ts){
	%>
	<tr>
	<td><input type="checkbox" value="<%=t.getIntID()%>" onclick="selectTeacher(this)"></td><td><%=t.getStrName() %></td><td><%=t.getStrEducation()%></td><td><%=t.getStrMajor()%></td><td><%=t.getStrCertificate()%></td><td><%=t.getStrTitle() %></td>
	</tr>
	<%
}
%>
<tr><td colspan="6"><input type="button" value="选择" onclick="transData()"></td></tr>
</table>
</body>
<script type="text/javascript">
var tbl = new Array();
function selectTeacher(obj){
	var id = $(obj).val();
	if(!obj.checked){
		for(var i=0;i<tbl.length;i++){
			var r = tbl[i];
			if(r[0] == id){
				tbl.splice(i,1);
			}
		}
		return ;
	}

	var name = $(obj).parent().next().text();
	var education = $(obj).parent().next().next().text();
	var major = $(obj).parent().next().next().next().text();
	var certification = $(obj).parent().next().next().next().next().text();
	var title = $(obj).parent().next().next().next().next().next().text();

	var tr = new Array(6);
	tr[0] = id;
	tr[1] = name;
	tr[2] = education;
	tr[3] = major;
	tr[4] = certification;
	tr[5] = title;

	var len = tbl.length;
	tbl[len] = tr;
}

function transData(){
	window.opener.addRow_teacher2(tbl);
	window.close();
}
</script>
</html>