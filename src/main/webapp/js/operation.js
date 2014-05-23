function submitDoc(frm,url)
{
	frm.action=url;
	frm.submit();
}

function addDoc(frm,url,intId,ids,vals,target)
{
	if((ids)&&(ids.length>0)&&(ids.length==vals.length))
	{
		var obj = null;
		for(var i = 0 ;i < ids.length ; i++)
		{
			obj = frm.elements[ids[i]];
			if(obj)
				obj.value = vals[i];
		}
	}
	if((intId)&&(intId>=0))
		frm.elements['objInfo.intID'].value = intId;
	if((target)&&(target!=""))
		frm.target = target;
	else
		frm.target = "_self";
	frm.action=url;
	frm.submit();
}

function editDoc(frm,url,intId,msg,ids,vals,target)
{

	if((ids)&&(ids.length>0)&&(ids.length==vals.length))
	{
		var obj = null;
		for(var i = 0 ;i < ids.length ; i++)
		{
			obj = frm.elements[ids[i]];
			if(obj)
				obj.value = vals[i];
		}
	}
	if((intId)&&(intId>=0))
		frm.elements['objInfo.intID'].value = intId;
	if((msg)&&(msg!=""))
	{
		if(confirm(msg))
		{
			if((target)&&(target!=""))
				frm.target = target;
			else
				frm.target = "_self";
        	frm.action=url;
        	frm.submit();
		}
	}
	else
	{
    	frm.action=url;
    	frm.submit();
	}
}

function checkCreateElement(frm,ids,vals)
{
	var tmp="<input type=hidden  id='"+strName+"'  name='"+strName+"' value='attaTagName_"+count+"' >";
	var fileObj=document.getElementById(strName);
	if(fileObj==null)
	{
		fileObj=document.createElement(attaFileTag);
		strFormName.appendChild(fileObj);
	};
}

function showDoc(frm,url,intId,ids,vals,target)
{
	if((ids)&&(ids.length>0)&&(ids.length==vals.length))
	{
		var obj = null;
		for(var i = 0 ;i < ids.length ; i++)
		{
			obj = frm.elements[ids[i]];
			if(obj)
			{
				obj.value = vals[i];
			}
			else
			{
				var formControlTag="<input type=hidden  id='"+ids[i]+"'  name='"+ids[i]+"' value='"+vals[i]+"' >";
				var thisObj=frm.elements(ids[i]);
				if(thisObj==null)
				{
					thisObj=document.createElement(formControlTag);
					frm.appendChild(thisObj);
				};
			}
		}
	}
	if((intId)&&(intId>=0))
		frm.elements['objInfo.intID'].value = intId;
	if((target)&&(target!=""))
		frm.target = target;
	else
		frm.target = "_self";
	frm.action=url;
	frm.submit();
}

function saveDoc(frm,url,msg,isNeedSubmit)
{
	if((msg)&&(msg!=""))
	{
		if(confirm(msg))
		{
        	frm.action=url;
			if(isNeedSubmit == 1)
			{
				frm.submit();
			}
		}
		else
		{
			return false;
		}
	}
	else
	{
    	frm.action=url;
		if(isNeedSubmit == 1)
		{
			frm.submit();
		}
	}
}
function deleteOptDoc(frm,url,intId,msg,ids,vals,target,alertInfo)
{
	if((ids)&&(ids.length>0)&&(ids.length==vals.length))
	{
		var obj = null;
		for(var i = 0 ;i < ids.length ; i++)
		{
			obj = frm.elements[ids[i]];
			if(obj)
				obj.value = vals[i];
		}
	}
	if((intId)&&(intId>=0))
	{
		frm.elements['objInfo.intID'].value = intId;
		ischeck = true;
	}
	else
	{
    	var ischeck = false;

    	if(frm.elements['objInfo.intIDList'])
    	{
    		if(frm.elements['objInfo.intIDList'].length)
    		{
    			for(var i=0;i<frm.elements['objInfo.intIDList'].length;i++)
    			{
    				if(frm.elements['objInfo.intIDList'][i].checked)
    				{
    					ischeck = true;
    					break;
    				}
    			}
    		}
    		else
    		{
    			if(frm.elements['objInfo.intIDList'].type == "checkbox")
    			{
    				if(frm.elements['objInfo.intIDList'].checked)
    				{
    					ischeck = true;
    				}
    			}
    			else
    				ischeck = true;
    		}
    	}
    	if(!ischeck)
    	{
    		alert(alertInfo);
    		return false;
    	}
	}
	if((target)&&(target!=""))
		frm.target = target;
	else
		frm.target = "_self";
	if((msg)&&(msg!=""))
	{
		if(confirm(msg)&&ischeck)
		{
        	frm.action=url;
        	frm.submit();
		}
	}
	else
	{
		if(ischeck)
		{
        	frm.action=url;
        	frm.submit();
		}
	}
}


function deleteDoc(frm,url,intId,msg,ids,vals,target)
{
	if((ids)&&(ids.length>0)&&(ids.length==vals.length))
	{
		var obj = null;
		for(var i = 0 ;i < ids.length ; i++)
		{
			obj = frm.elements[ids[i]];
			if(obj)
				obj.value = vals[i];
		}
	}
	if((intId)&&(intId>=0))
	{
		frm.elements['objInfo.intID'].value = intId;
		ischeck = true;
	}
	else
	{
    	var ischeck = false;

    	if(frm.elements['objInfo.intIDList'])
    	{
    		if(frm.elements['objInfo.intIDList'].length)
    		{
    			for(var i=0;i<frm.elements['objInfo.intIDList'].length;i++)
    			{
    				if(frm.elements['objInfo.intIDList'][i].checked)
    				{
    					ischeck = true;
    					break;
    				}
    			}
    		}
    		else
    		{
    			if(frm.elements['objInfo.intIDList'].type == "checkbox")
    			{
    				if(frm.elements['objInfo.intIDList'].checked)
    				{
    					ischeck = true;
    				}
    			}
    			else
    				ischeck = true;
    		}
    	}
    	if(!ischeck)
    	{
    		alert("请选择你要删除的数据项!");
    		return false;
    	}
	}
	if((target)&&(target!=""))
		frm.target = target;
	else
		frm.target = "_self";
	if((msg)&&(msg!=""))
	{
		if(confirm(msg)&&ischeck)
		{
        	frm.action=url;
        	frm.submit();
		}
	}
	else
	{
		if(ischeck)
		{
        	frm.action=url;
        	frm.submit();
		}
	}
}

function selectAll(chks,obj)
{
	if((chks)&&(obj))
	{
		if(chks.length)
		{
    		for(var i=0;i<chks.length;i++)
    		{
    			chks[i].checked=obj.checked;
    		}
		}
		else
		{
			chks.checked=obj.checked;
		}
	}
}

var isChecked=false;
function selectAll2(chks)
{
	if(isChecked==false)
	{
		isChecked=true;
	}
	else
	{
		isChecked=false;
	}

	if(chks)
	{
		if(chks.length)
		{
    		for(var i=0;i<chks.length;i++)
    		{
    			chks[i].checked=isChecked;
    		}
		}
		else
		{
			chks.checked=isChecked;
		}
	}
}
function fPopUpSelectGroupUnitDlg(actionNameSpace,strIDList,strName)
{
	showx = event.screenX - event.offsetX -120 - 4 - 310 ; // + deltaX;
	showy = event.screenY - event.offsetY -220 + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;

	var strIDList=document.getElementById(strIDList);
	var strName=document.getElementById(strName);

	retval = window.showModalDialog(actionNameSpace+"&jsSelectModel.strID="+strIDList.value, "", "dialogWidth:490px; dialogHeight:490px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );


	if( retval != null )
	{
		var ss=retval.split("#");
		if(ss[0]!=null && ss[0].indexOf(',')>-1)
		{
			ss[0]=ss[0].substring(1);
			if(ss[0]!=null && ss[0].lastIndexOf(',')>-1)
			{
				ss[0]=ss[0].substring(0,ss[0].length-1);
			}
		}
		if(ss[1]!=null && ss[1].indexOf(',')>-1)
		{
			ss[1]=ss[1].substring(1);
			if(ss[1]!=null && ss[1].lastIndexOf(',')>-1)
			{
				ss[1]=ss[1].substring(0,ss[1].length-1);
			}
		}
		strIDList.value=ss[0];
		strName.value=ss[1];
	}
}

function fPopUpCommonDlg(actionNameSpace,intWidth,intHeight)
{
	showx = event.screenX - event.offsetX -120 - 4 - 310 ; // + deltaX;
	showy = event.screenY - event.offsetY -220 + 18; // + deltaY;
	newWINwidth = 210 + 4 + 18;
	window.showModalDialog(actionNameSpace, "", "dialogWidth:"+intWidth+"px; dialogHeight:"+intHeight+"px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; "  );

}

function deleteSingleDocSubmit(thisForm,thisurl,msg)
{
	thisForm.action=thisurl;
	if((msg)&&(msg!=""))
	{
		if(confirm(msg))
		{
        	thisForm.submit();
		}
	}
	else
	{
		thisForm.submit();
	}
}


function deleteSingleDoc(url,msg)
{

	if((msg)&&(msg!=""))
	{
		if(confirm(msg))
		{
        	document.location.href=url;
		}
	}
	else
	{
		document.location.href=url;
	}
}
