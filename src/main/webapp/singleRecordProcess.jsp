<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>人员档案处理</title>
<script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
<script type="text/javascript">
	function singleUseRecord() {
		$.ajax({
            url : "/hodeframe2012_hnmk/servlet/singleUseRecord",
            dataType : "text",
            type : "POST",
            success : function(data, textStatus, jqXHR) {

            },
            error : function() {
            	alert("服务器出错！");
            }
      	});
	}

	function singleUseNewAndUpdate(){
		$.ajax({
            url : "/hodeframe2012_hnmk/servlet/singleUseNewAndUpdate",
            dataType : "text",
            type : "POST",
            success : function(data, textStatus, jqXHR) {

            },
            error : function() {
            	alert("服务器出错！");
            }
      	});
	}
</script>
</head>
<body>
	该身份证号的学员信息在档案库中已存在，请选择操作：<br>
	<input type="button" value="使用档案库中的信息" onclick="singleUseRecord()">
	&nbsp&nbsp&nbsp&nbsp
	<input type="button" value="使用新信息并更新档案库" onclick="singleUseNewAndUpdate()">
	&nbsp&nbsp&nbsp&nbsp
	<input type='button' value='返回' onclick='history.go(-1)'>
</body>
</html>