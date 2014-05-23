<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>测试图片上传</title>
<script type="text/javascript">
	function fileChange(img) {
		if(checkFormat(img)) {
			document.getElementById("idImg").width=200;
			 document.getElementById("idImg").height=200;
			document.getElementById("idImg").src=img;
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
</script>
</head>
<body>
	<form action="/hodeframe2012_hnmk/servlet/imageUpload" method="post" enctype="multipart/form-data">
		<table border="0" class="perview" align="center">
        	<tr>
            	<th>选择文件</th>
           		<th width="50%">预览图</th>
       		</tr>
        	<tr>
            	<td height="200"><input id="idFile" name="upload" type="file" onchange="fileChange(this.value)"/></td>
            	<td align="center"><img id="idImg" /></td>
       		</tr>
       		<tr>
       			<td colspan="2"><input type="submit" value="提交"></td>
       		</tr>
       		<tr>
				<th>
					联系电话：
				</th>
				<td>
					<input type="text" name="studentRelation" id="studentRelation">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>