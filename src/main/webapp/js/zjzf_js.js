function getNum(str)
{
	return str.replace(/[^\x00-\xff]/g,"**").length-str.length;
}
function getCheckAssicNum(str)
{
	return (str.replace(/[\x00-\xff]/g,"**").length-str.length) % 2;
}

function getCountInfo(name,breaknum,linenum,intIsNewLine,countnum,isNeedNbsp)
{
	var id=document.getElementById(name);
	if(id)
	{
		var strContent = id.innerHTML;

		var intStart = strContent.indexOf("<!--");
		var intEnd = strContent.indexOf("-->");
		if((intStart != -1) && (intEnd != -1))
		{
			strContent = strContent.substr(intEnd+3);
		}

		var strHTML = "";
		var t = 0,intTmp=0;


		var intHanzi=getNum(strContent)
		for(i=(strContent.length-intHanzi + getCheckAssicNum(strContent))/2+intHanzi;i<countnum;i++)
		{
			strContent+="　";
		}
		if(isNeedNbsp == 1)
		{
			strContent+="&nbsp;";
		}
		if(breaknum > 0)
		{
			strHTML += strContent.substr(0,breaknum);
		}
		if(linenum > 0)
		{
			var intPos=breaknum;
			var intFlag=strContent.substr(intPos,1);
			while(intFlag.length > 0 && strContent.length > intPos)
			{
				t++;
				if(t>100)
					break;
				strHTML+="&nbsp;<BR>";
				strHTML += strContent.substr(intPos,linenum);
				intTmp = strContent.substr(intPos,linenum).length;
				intPos += linenum;
				intFlag = strContent.substr(intPos,1);

			}
		}
		if(intIsNewLine)
		{
			for(i=intTmp;i<linenum;i++)
			{
				strHTML+="　";
			}
			strHTML+="<BR>";
		}
		id.innerHTML = strHTML;
	}
}

/**
getCountInfo(para1,para2,para3,para4,para5);
一共５个参数
第一个参数para1 : span 的ｉｄ名称
第一个参数para2 : 第一行存放的字数
第一个参数para3 : 整行存放的字数
第一个参数para4 : 是否需要新的一行
第一个参数para5 : 总字数。如果输入的总字数比para5小，那么用空格补充。否则自动填充
*/

//getCountInfo("name1",7,31,1,23);
