<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>网上报名</title>
	<base target="_self"/>
	<script type="text/javascript" src="./js/validator.js"></script>
	<script type="text/javascript" src="./js/jquery-1.8.3.js"></script>
	<script type="text/javascript" src="./js/jquery.js"></script>
	<script type="text/javascript" src="./js/ajaxfileupload.js"></script>
	<style type="text/css">
		/*tr {
			height: 30px;
		}*/

		font {
			font-size: 14px;
			font-weight: bold;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function(){
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/getAllTrainUnit",
                dataType : "json",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	//alert("success");
                    if(data != null && data.returnList.length > 0) {
                    	showData(data);
                    } else {
                    	alert("数据初始化出错！");
                    }
                },
                error : function() {
                	alert("数据初始化出错！");
                }
          	});
		});

		function showData(data) {
			var trainUnitSelect = $("#trainUnitSelect");
			var selectStr = "<option vlaue=''></option>";

			for(var i = 0; i < data.returnList.length; i ++) {
				selectStr += "<option value='"+ data.returnList[i].unitName + "'>" + data.returnList[i].unitName + "</option>";
			}

			trainUnitSelect.empty();
			trainUnitSelect.html(selectStr);


			var trainUnitSelect2 = $("#trainUnitSelect2");
			var selectStr2 = "<option vlaue=''></option>";

			for(var i = 0; i < data.returnList.length; i ++) {
				selectStr2 += "<option value='"+ data.returnList[i].unitName + "'>" + data.returnList[i].unitName + "</option>";
			}

			trainUnitSelect2.empty();
			trainUnitSelect2.html(selectStr);
		}

		function fileChange(img) {
			if(checkFormat(img)) {
				//alert(img);
				document.getElementById("idImg1").width=200;
				 document.getElementById("idImg1").height=200;
				document.getElementById("idImg1").src=img;
			}
		}

		function checkFormat(filePath) {
			 var i = filePath.lastIndexOf('.');
			 var len = filePath.length;
			 var str = filePath.substring(len,i+1);
			 var extName = "JPG,GIF,PNG,JPEG,BMP";
			 if(extName.indexOf(str.toUpperCase()) < 0)  {
			  alert("请选择正确的图片文件!");
			  return false;
			 }
			 return true;
		}

		function uploadImage(img) {
			//alert(img.value);
			//alert(img.id);
			//var imgValue = img.value;
			var imgId = img.id;
			var loading = $("#" + imgId + "_loading");

			if(checkFormat(img.value)) {
				var idCard = $("#studentIDCode").val().replace(/[ ]/g,"").toUpperCase();
				//alert(idCard);
				var idCodeReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;

				if(!idCodeReg.test(idCard)) {
					alert("请输入正确的身份证号！");
					return;
				}

				//显示loading的gif图片
				loading.attr("style", "display:span");
				loading.attr("src", "images/hnmk/loading.gif");

				$.ajaxFileUpload({
	                url:"/hodeframe2012_hnmk/servlet/imageUpload?idCard="+idCard+"&imgId="+imgId,       //需要链接到服务器地址
	                secureuri:false,
	                fileElementId:imgId,                            //文件选择框的id属性
	                dataType: "json",                                   //服务器返回的格式，可以是json
	                type : "POST",
	                success: function () {                              //相当于java中try语句块的用法
	   					var ms = new Date().getTime();
	   				 	if(imgId == "image1") {
	   				 		document.getElementById("idImg1").width=200;
	   				 		document.getElementById("idImg1").height=200;
	   				 		document.getElementById("idImg1").src="/train/student/0/pic1/"+idCard+".JPG?temp="+ms;
	   				 		document.getElementById("image1_hd").value="pic1/"+idCard+".JPG";
	   				 	}
	   					 if(imgId == "image2") {
	   						document.getElementById("idImg2").width=200;
		   				 	document.getElementById("idImg2").height=200;
	   				 		document.getElementById("idImg2").src="/train/student/0/pic2/"+idCard+".JPG?temp="+ms;
	   				 		document.getElementById("image2_hd").value="pic2/"+idCard+".JPG";
	   				 	}
	   					if(imgId == "image3") {
	   						document.getElementById("idImg3").width=200;
		   				 	document.getElementById("idImg3").height=200;
				 			document.getElementById("idImg3").src="/train/student/0/pic3/"+idCard+".JPG?temp="+ms;
				 			document.getElementById("image3_hd").value="pic3/"+idCard+".JPG";
				 		}
	   					if(imgId == "image4") {
	   						document.getElementById("idImg4").width=200;
		   				 	document.getElementById("idImg4").height=200;
				 			document.getElementById("idImg4").src="/train/student/0/pic4/"+idCard+".JPG?temp="+ms;
				 			document.getElementById("image4_hd").value="pic4/"+idCard+".JPG";
				 		}

	   					//显示上传成功的图片
	   					loading.attr("src", "images/hnmk/loading_success.jpg");
	                },
	                error: function () {
	               		var ms = new Date().getTime();
	   				 	if(imgId == "image1") {
	   				 		document.getElementById("idImg1").width=200;
	   				 		document.getElementById("idImg1").height=200;
	   				 		document.getElementById("idImg1").src="/train/student/0/pic1/"+idCard+".JPG?temp="+ms;
	   				 		document.getElementById("image1_hd").value="pic1/"+idCard+".JPG";
	   				 	}
	   					 if(imgId == "image2") {
	   						document.getElementById("idImg2").width=200;
		   				 	document.getElementById("idImg2").height=200;
	   				 		document.getElementById("idImg2").src="/train/student/0/pic2/"+idCard+".JPG?temp="+ms;
	   				 		document.getElementById("image2_hd").value="pic2/"+idCard+".JPG";
	   				 	}
	   					if(imgId == "image3") {
	   						document.getElementById("idImg3").width=200;
		   				 	document.getElementById("idImg3").height=200;
   				 			document.getElementById("idImg3").src="/train/student/0/pic3/"+idCard+".JPG?temp="+ms;
   				 			document.getElementById("image3_hd").value="pic3/"+idCard+".JPG";
   				 		}
	   					if(imgId == "image4") {
	   						document.getElementById("idImg4").width=200;
		   				 	document.getElementById("idImg4").height=200;
   				 			document.getElementById("idImg4").src="/train/student/0/pic4/"+idCard+".JPG?temp="+ms;
   				 			document.getElementById("image4_hd").value="pic4/"+idCard+".JPG";
   				 		}

	   					//显示上传成功的图片
	   					loading.attr("src", "images/hnmk/loading_success.jpg");
	                }
	            });
			}
		}

		function batchImport() {
			//隐藏单个录入
			var div1 = $("#div1");
			div1.attr("style","display: none");

			//显示批量导入
			var div2 = $("#div2");
			div2.attr("style","display:block");

			//传递选中的培训机构
			var select1 = $("#trainUnitSelect");
			var select2 = $("#trainUnitSelect2");
			select2.val(select1.val());
		}

		function singleInput() {
			//隐藏批量导入
			var div2 = $("#div2");
			div2.attr("style","display: none");

			//显示单个录入
			var div1 = $("#div1");
			div1.attr("style","display:block");

			//传递选中的培训机构
			var select1 = $("#trainUnitSelect");
			var select2 = $("#trainUnitSelect2");
			select1.val(select2.val());
		}

		function uploadBaseData(dataFile) {
			$("#importBtn").attr("style", "display:none");
			$("#baseData_loading").attr("style", "display:none");
			$("#status").empty();

			var dataTable = $("#dataTable");
			var dataFileValue = dataFile.value;
			var str = dataFileValue.substr(dataFileValue.length-3).toUpperCase();
			if(str != "XLS") {
				alert("请输入正确的xls文件!");
				$("#importBtn").attr("style", "display:none");
				dataTable.empty();
				dataTable.html("<tr><td>文件格式不正确，请输入正确的xls文件！</td></tr>");
				return;
			}
			var dataFileId = dataFile.id;

			// 显示loading的gif图片
			$("#baseData_loading").attr("style", "display:span");
			$("#baseData_loading").attr("src", "images/hnmk/loading.gif");

			$.ajaxFileUpload({
                url:"/hodeframe2012_hnmk/servlet/dataFileUpload",       //需要链接到服务器地址
                secureuri:false,
                fileElementId:dataFileId,                            //文件选择框的id属性
                dataType: "text",                                   //服务器返回的格式，可以是json
                type : "POST",
                success: function (data) {                              //相当于java中try语句块的用法
   				 	//alert("success");
   				 	//alert(data);
            		dataTable.empty();
					dataTable.html("<tr><td>" + data);   //这里获得的data有问题，需要补全
					$("#importBtn").attr("style", "display:block");

					//显示上传成功的图片
   					$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
                },
                error: function (data) {
               		alert("error");

               		//显示上传失败的图片
   					$("#baseData_loading").attr("src", "images/hnmk/loading_error.jpg");
                }
            });
		}

		function startImport() {
			var trainUnit = $("#trainUnitSelect2").val();
			if(trainUnit == "") {
				alert("请选择培训机构！");
				return;
			}
			var trainType2 = $("#trainType2").val();
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/dataFileUpload",
                data : "flag=1&trainUnit="+trainUnit+"&trainType2="+trainType2,
                dataType : "text",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	//alert(data);
                	if(data == "error") {
                		$("#baseData_loading").attr("src", "images/hnmk/loading_error.jpg");
                		$("#status").html("<font color='red'>导入失败！</font>");
                	} else {
                		$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
                		var status = "<font color='green'>导入成功！</font>";
                		var dataTable = $("#dataTable");
                		dataTable.empty();

                		if(data != null && data.length > 0) {
                			status += "<font color='red'>以下人员暂时没有导入，原因：档案库中已经存在。请选择操作</font>";
                			status += "<input type='button' value='使用档案库信息' onclick='usePersonRecord()'>      ";
                			status += "<input type='button' value='使用以上信息并更新档案库' onclick='useNewAndUpdate()'>";
                			var tableStr = "";
                    		tableStr += "<tr><td>序号</td>";
                    		tableStr += "<td>单位及部门</td>";
                    		tableStr += "<td>身份证地址</td>";
                    		tableStr += "<td>姓名</td>";
                    		tableStr += "<td>性别</td>";
                    		tableStr += "<td>年龄</td>";
                    		tableStr += "<td>职务</td>";
                    		tableStr += "<td>文化程度</td>";
                    		tableStr += "<td>身份证号</td>";
                    		tableStr += "<td>证书编号（仅再培训填写）</td>";
                    		tableStr += "<td>职业健康状况</td>";
                    		tableStr += "<td>初次领证时间（仅再培训填写）</td>";
                    		tableStr += "<td>违章作业情况（仅再培训填写）</td>";
                    		tableStr += "<td>联系电话</td>";
                    		tableStr += "<td>单位类型</td></tr>";

                    		tableStr += data;
                    		dataTable.html(tableStr);
                		}
                		$("#status").html(status);
                	}
                	$("#importBtn").attr("style", "display:none");
                },
                error : function() {
                	$("#baseData_loading").attr("src", "images/hnmk/loading_error.jpg");
            		$("#status").html("<font color='red'>导入失败！</font>");
            		$("#importBtn").attr("style", "display:none");
                }
          	});
		}

		function usePersonRecord() {
			//window.location = "/hodeframe2012_hnmk/servlet/usePersonRecord";
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/usePersonRecord",
                dataType : "text",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
                	var status = "<font color='green'>数据处理成功！</font>";

                	var dataTable = $("#dataTable");
            		dataTable.empty();

                	if(data != null && data.length > 0) {
                		status += "<font color='red'>以下用户的图片在档案库中已经存在</font>";
                		dataTable.attr("border", "0");
                		dataTable.html(data);
                	}

                	$("#status").html(status);
                },
                error : function() {
                	alert("服务器出错！");
                }
          	});
		}

		function useNewAndUpdate() {
			//window.location = "/hodeframe2012_hnmk/servlet/useNewAndUpdate";
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/useNewAndUpdate",
                dataType : "text",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
                	var status = "<font color='green'>数据处理成功！</font>";

                	var dataTable = $("#dataTable");
            		dataTable.empty();

                	if(data != null && data.length > 0) {
                		status += "<font color='red'>以下用户的图片在档案库中已经存在</font>";
                		dataTable.attr("border", "0");
                		dataTable.html(data);
                	}

                	$("#status").html(status);
                },
                error : function() {
                	alert("服务器出错！");
                }
          	});
		}

		function importRecordPic() {
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/importRecordPic",
                dataType : "text",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	var status = "";
                	if(data == "success") {
                		$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
                		status = "<font color='green'>图片处理成功！</font>";
                	} else {
                		$("#baseData_loading").attr("src", "images/hnmk/loading_error.jpg");
                		status = "<font color='red'>图片处理失败！</font>";
                	}

                	var dataTable = $("#dataTable");
            		dataTable.empty();

                	$("#status").html(status);
                },
                error : function() {
                	$("#baseData_loading").attr("src", "images/hnmk/loading_error.jpg");
            		var status = "<font color='red'>图片处理失败！</font>";

            		var dataTable = $("#dataTable");
            		dataTable.empty();

                	$("#status").html(status);
                }
          	});
		}

		function ignoreRecordPic() {
			$("#baseData_loading").attr("src", "images/hnmk/loading_success.jpg");
			var status = "<font color='green'>导入成功！</font>";

			var dataTable = $("#dataTable");
    		dataTable.empty();

    		$("#status").html(status);
		}

		function uploadImageData(dataFile) {
			$("#importBtn2").attr("style", "display:none");
			$("#imageData_loading").attr("style", "display:none");
			$("#status2").empty();

			var dataFileValue = dataFile.value;
			var str = dataFileValue.substr(dataFileValue.length-3).toUpperCase();
			 if(str != "ZIP") {
				alert("请输入正确的zip文件!");
				return;
			}
			var dataFileId = dataFile.id;

			//显示loading的gif图片
			$("#imageData_loading").attr("style", "display:span");
			$("#imageData_loading").attr("src", "images/hnmk/loading.gif");

			$.ajaxFileUpload({
                url:"/hodeframe2012_hnmk/servlet/imageFileUpload",       //需要链接到服务器地址
                secureuri:false,
                fileElementId:dataFileId,                            //文件选择框的id属性
                dataType: "text",                                   //服务器返回的格式，可以是json
                type : "POST",
                success: function (data) {                              //相当于java中try语句块的用法
   				 	//alert("success");
   				 	//alert(data);
   				 	if(data == "success") {
   				 		$("#importBtn2").attr("style", "display:block");

   				 		//显示上传成功的图片
   	   					$("#imageData_loading").attr("src", "images/hnmk/loading_success.jpg");
   				 	} else {
   				 		$("#importBtn2").attr("style", "display:block");

   				 		//显示上传失败的图片
   	   					$("#imageData_loading").attr("src", "images/hnmk/loading_error.jpg");
   				 	}
                },
                error: function (data) {
                	$("#importBtn2").attr("style", "display:block");

                	//显示上传失败的图片
	   				$("#imageData_loading").attr("src", "images/hnmk/loading_error.jpg");
                }
            });
		}

		function startImport2() {
			var trainUnit = $("#trainUnitSelect2").val();
			if(trainUnit == "") {
				alert("请选择培训机构！");
				return;
			}
			$.ajax({
                url : "/hodeframe2012_hnmk/servlet/imageFileUpload",
                data : "flag=1&trainUnit="+trainUnit,
                dataType : "text",
                type : "POST",
                success : function(data, textStatus, jqXHR) {
                	//alert(data);
                	if (data == "error") {
                		$("#imageData_loading").attr("src", "images/hnmk/loading_error.jpg");
                		$("#status2").html("<font color='red'>导入失败！</font>");
                	} else if (data == "success"){
                		$("#imageData_loading").attr("src", "images/hnmk/loading_success.jpg");
                		$("#status2").html("<font color='green'>导入成功！</font>");
                	} else if(data == "error2") {
                		$("#imageData_loading").attr("src", "images/hnmk/loading_success.jpg");
                		$("#status2").html("<font color='red'>模板格式错误！双击模板打开以后应该直接看到pic1、pic2、pic3、pic4,请检查模板格式！</font>");
                	} else {
                		$("#imageData_loading").attr("src", "images/hnmk/loading_error.jpg");
                		$("#status2").html("<font color='red'>人员"+data+"没有基础信息</font>");
                	}
                	$("#importBtn2").attr("style", "display:none");
                },
                error : function() {
                	$("#imageData_loading").attr("src", "images/hnmk/loading_error.jpg");
            		$("#status2").html("<font color='red'>导入失败！</font>");
            		$("#importBtn2").attr("style", "display:none");
                }
          	});
		}
	</script>
</head>

<body>
	<form action="/hodeframe2012_hnmk/servlet/signUp" id="form1" method="post">
	<div id="div1">
	<table>
		<tr>
			<td align="right">
				<font>培训机构：</font>
			</td>
			<td>
				<select name="trainUnit" id="trainUnitSelect">
				</select>
			</td>
			<td><input type="button" value="批量导入" onclick="batchImport()"></td>
		</tr>
		<tr>
			<td align="right">
				<font>培训类别：</font>
			</td>
			<td>
				<select name="trainType" id="trainType">
					<option value="0">培训</option>
					<option value="1">再培训</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>单位及部门：</font>
			</td>
			<td>
				<input type="text" name="studentPost" id="studentPost">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>职务：</font>
			</td>
			<td>
				<input type="text" name="studentTitle" id="studentTitle">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>姓名：</font>
			</td>
			<td>
				<input type="text" name="studentName" id="studentName">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>性别：</font>
			</td>
			<td>
				<input type="radio" value="男" name="studentSex" checked="checked">
				男
				<input type="radio" value="女" name="studentSex">
				女
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>年龄：</font>
			</td>
			<td>
				<input type="text" name="studentAge" id="studentAge">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>身份证号：</font>
			</td>
			<td>
				<input type="text" name="studentIDCode" id="studentIDCode">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>身份证地址：</font>
			</td>
			<td>
				<input type="text" name="studentIDAddr" id="studentIDAddr">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>文化程度：</font>
			</td>
			<td>
				<select name="studentDegree">
					<option value="初中">
						初中
					</option>
					<option value="中专">
						中专
					</option>
					<option value="高中">
						高中
					</option>
					<option value="大专">
						大专
					</option>
					<option value="本科">
						本科
					</option>
					<option value="硕士">
						硕士
					</option>
					<option value="博士">
						博士
					</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>单位类型：</font>
			</td>
			<td>
				<input type="text" name="studentUnitType" id="studentUnitType">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>联系电话：</font>
			</td>
			<td>
				<input type="text" name="studentRelation" id="studentRelation">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>证书编号（仅再培训填写）：</font>
			</td>
			<td>
				<input type="text" name="studentCertCode">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>初次领证时间（仅再培训填写）：</font>
			</td>
			<td>
				<INPUT TYPE="text" NAME="firstDate" id="firstDate"
						onclick="fPopUpCalendarDlg(this);" CLASS="inputText" readonly="readonly" size=7 >
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>违章作业情况（仅再培训填写）：</font>
			</td>
			<td>
				<input type="text" name="studentViolate">
			</td>
		</tr>

		<tr>
			<td align="right">
				<font>相片：</font>
			</td>
			<td>
				<nobr>
				<input type="file" name="image1" id="image1" onchange="uploadImage(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="image1_loading">
				</nobr>
				<input type="hidden" id="image1_hd" name="image1_hd">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font></font>
			</td>
			<td>
				<img id="idImg1">
			</td>
		</tr>

		<tr>
			<td align="right">
				<font>学历：</font>
			</td>
			<td>
				<nobr>
				<input type="file" name="image2" id="image2" onchange="uploadImage(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="image2_loading">
				</nobr>
				<input type="hidden" id="image2_hd" name="image2_hd">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font></font>
			</td>
			<td>
				<img id="idImg2" />
			</td>
		</tr>

		<tr>
			<td align="right">
				<font>体检表：</font>
			</td>
			<td>
				<nobr>
				<input type="file" name="image3" id="image3" onchange="uploadImage(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="image3_loading">
				</nobr>
				<input type="hidden" id="image3_hd" name="image3_hd">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font></font>
			</td>
			<td>
				<img id="idImg3" />
			</td>
		</tr>

		<tr>
			<td align="right">
				<font>相关工作经历证明：</font>
			</td>
			<td>
				<nobr>
				<input type="file" name="image4" id="image4" onchange="uploadImage(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="image4_loading">
				</nobr>
				<input type="hidden" id="image4_hd" name="image4_hd">
			</td>
		</tr>
		<tr>
			<td align="right">
				<font></font>
			</td>
			<td>
				<img id="idImg4" />
			</td>
		</tr>

		<tr>
          <td height="34" align="right"><font>验证码：</font></td>
          <td width="90"><input type="text" name="strRand" id="strRand" class="login_srk" value="" style="width:78px;"/></td>
          <td align="left">
          	<img style="cursor:hand" id="valid" onclick="changPic(this)" src="./images.jsp"  border="0" scrolling="no"  />
          </td>
        </tr>
		<tr>
			<td align="right">
				<img src="/hodeframe2012_hnmk/images/operator/opt_confirm.gif"
						style="cursor: hand" onclick="signUp()" />
					&nbsp;&nbsp;
			</td>
			<td>
				<img src="/hodeframe2012_hnmk/images/operator/opt_return.gif"
						style="cursor: hand" onclick="history.back()" />
					&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	</div>
	</form>

	<form action="/hodeframe2012_hnmk/servlet/signUpBatch" id="form2" method="post">
	<div id="div2" style="display: none;">
	<table border="0">
		<tr>
			<td align="right">
				<font>培训机构：</font>
			</td>
			<td>
				<select name="trainUnit2" id="trainUnitSelect2">
				</select>
			</td>
			<td><input type="button" value="单个录入" onclick="singleInput()"></td>
		</tr>
		<tr>
			<td align="right">
				<font>培训类别：</font>
			</td>
			<td>
				<select name="trainType2" id="trainType2">
					<option value="0">培训</option>
					<option value="1">再培训</option>
				</select>
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>基础信息格式：</font>
			</td>
			<td>
				<a href='javascript:downloadFile3("information/example/student/index.xls","基础信息导入模板.xls")'>基础信息导入模板</a>
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>基础信息导入：</font>
			</td>
			<td>
				<input type="file" name="baseData" id="baseData" onchange="uploadBaseData(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="baseData_loading">
				<span id="status"></span>
			</td>
			<td>
				<input type="button" value="导入" id="importBtn" style="display: none;" onclick="startImport()">
			</td>
		</tr>
	</table>
	<table id="dataTable" borderColorDark=#000000 borderColorLight=#000000 style='border-collapse: collapse; line-height:20px' border='1' cellspacing='1' cellpadding='3' width=1400>
	</table>

	<table>
		<tr>
			<td align="right">
				<font>图片信息格式：</font>
			</td>
			<td>
				<a href='javascript:downloadFile3("information/example/student/images.zip","图片信息导入模板.zip")'>图片信息导入模板</a>
			</td>
		</tr>
		<tr>
			<td align="right">
				<font>图片信息导入：</font>
			</td>
			<td>
				<input type="file" name="imageData" id="imageData" onchange="uploadImageData(this)">
				<img src="images/hnmk/loading.gif" style="display: none;" id="imageData_loading">
				<span id="status2"></span>
			</td>
			<td>
				<input type="button" value="导入" id="importBtn2" style="display: none;" onclick="startImport2()">
			</td>
		</tr>
		<tr>
			<td></td>
			<td>
				<img src="/hodeframe2012_hnmk/images/operator/opt_return.gif"
						style="cursor: hand" onclick="history.back()" />
					&nbsp;&nbsp;
			</td>
		</tr>
	</table>
	</div>
	</form>

	<iframe id="downLoadFrame3A" name="downLoadFrame3A" src="" width="0" height="0"></iframe>
	<SCRIPT LANGUAGE="JavaScript">
		function downloadFile3(strFileName,strFileTitle)
		{
		document.getElementById('downLoadFrame3A').src="servlet/fileDownload?strFileName="+strFileName+"&strFileTitle="+strFileTitle;
		}
	</SCRIPT>
</body>

<script>
	function changPic(_id) {
		_id.src = "images.jsp" + "?dd="
				+ Math.random();
	}

	function signUp() {
		//数据校验
		var trainUnitSelect = $("#trainUnitSelect").val().replace(/[ ]/g,""); 
		var studentPost = $("#studentPost").val().replace(/[ ]/g,"");
		var studentTitle = $("#studentTitle").val().replace(/[ ]/g,"");
		var studentName = $("#studentName").val().replace(/[ ]/g,"");
		var studentAge = $("#studentAge").val().replace(/[ ]/g,"");
		var studentIDCode = $("#studentIDCode").val().replace(/[ ]/g,"");
		var studentIDAddr = $("#studentIDAddr").val().replace(/[ ]/g,"");
		var studentUnitType = $("#studentUnitType").val().replace(/[ ]/g,"");
		var studentRelation = $("#studentRelation").val().replace(/[ ]/g,"");
		var strRand = $("#strRand").val().replace(/[ ]/g,"");

		if(trainUnitSelect == "") {
			alert("请选择培训机构！");
			return;
		}
		if(studentPost == "") {
			alert("请输入单位及部门！");
			return;
		}
		if(studentTitle == "") {
			alert("请输入职务！");
			return;
		}
		if(studentName == "") {
			alert("请输入姓名！");
			return;
		}
		var ageReg = /[1-9]\d{1}/;
		if(studentAge == "" || !ageReg.test(studentAge)) {
			alert("请输入正确的年龄！");
			return;
		}

		var idCodeReg = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
		if(!idCodeReg.test(studentIDCode)) {
			alert("请输入正确的身份证号！");
			return;
		}

		if(studentIDAddr == "") {
			alert("请输入身份证地址！");
			return;
		}
		if(studentUnitType == "") {
			alert("请输入单位类型！");
			return;
		}
		if(studentRelation == "") {
			alert("请输入联系电话！");
			return;
		}
		if(strRand == "") {
			alert("请输入验证码！");
			return;
		}

		//提交form
		$("#form1").submit();
	}

</script>
</html>