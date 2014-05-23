// fbox - 待选项目列表
// tbox - 选择了的项目列表
function move_select(fbox,tbox)
{
	for(var i=0; i<fbox.options.length; i++)
	{
		if(fbox.options[i].selected && fbox.options[i].value != "")
		{
			// 增加项目列表到tbox
			var no = new Option();
			no.value = fbox.options[i].value
			no.text = fbox.options[i].text

			tbox.options[tbox.options.length] = no;

			// 清空fbox的项目列表,但是如果是单选时不清掉现在的项目
    		fbox.options[i].value = ""
    		fbox.options[i].text = ""
		}
	}
	BumpUp_select(fbox);
	//SortD_select(tbox);
}

function moveAll_select(fbox,tbox)
{
	for(var i=0; i<fbox.options.length; i++)
	{
		if(fbox.options[i].value != "")
		{
			// 增加项目列表到右侧
			var no = new Option();
			no.value = fbox.options[i].value
			no.text = fbox.options[i].text
			tbox.options[tbox.options.length] = no;

			// 清空左侧的项目列表
			fbox.options[i].value = ""
			fbox.options[i].text = ""
		}
	}
	BumpUp_select(fbox);
	//SortD_select(tbox);
}
// 清除空的项目列表
function BumpUp_select(box)
{
	for(var i=0; i<box.options.length; i++)
	{
		if(box.options[i].value == "")
		{
			for(var j=i; j<box.options.length-1; j++)
			{
				box.options[j].value = box.options[j+1].value
				box.options[j].text = box.options[j+1].text
			}
			var ln = i
			break
		}
	}
	if(ln < box.options.length)
	{
		box.options.length -= 1;
		BumpUp_select(box);
	}
}

function SortD_select(box)
{
	var temp_opts = new Array()
	var temp = new Object()
	// 调用临时数组
	for(var i=0; i<box.options.length; i++)
	{
		temp_opts[i] = box.options[i]
	}
	for(var x=0; x<temp_opts.length-1; x++)
	{
		for(var y=(x+1); y<temp_opts.length; y++)
		{
			if(temp_opts[x].text< temp_opts[y].text)
			{
				temp.text = temp_opts[x].text
				temp.value = temp_opts[x].value
				temp_opts[x].text = temp_opts[y].text
				temp_opts[x].value = temp_opts[y].value
				temp_opts[y].text = temp.text
				temp_opts[y].value = temp.value
			}
		}
	}
	for(var i=0; i<box.options.length; i++)
	{
		box.options[i].value = temp_opts[i].value
		box.options[i].text = temp_opts[i].text
	}
}
function moveUp_select(obj)
{
	with (obj)
	{
		if(selectedIndex==0)
		{
		}
		else if(selectedIndex>=1)
		{
			moveG_select(obj,-1)
		}
	}
}
function moveDown_select(obj)
{
	with (obj)
	{
		if(selectedIndex==length-1)
		{
		}
		else if(selectedIndex>=0 && selectedIndex<length-1)
			moveG_select(obj,+1)
	}
}
function moveG_select(obj,offset)
{
	with (obj)
	{
		desIndex=selectedIndex+offset
		var otext=options[desIndex].text
		var ovalue=options[desIndex].value
		options[desIndex].text=options[selectedIndex].text
		options[desIndex].value=options[selectedIndex].value
		options[selectedIndex].text=otext
		options[selectedIndex].value=ovalue
		selectedIndex=desIndex
	}
}

function initSource_select(unitListArr,leftSource,rightTarget)
{
	leftSource.length=0;
	for(var i=0;i<unitListArr.length;i++)
	{
		//要判断是不是单选,如果是单选，则source中的值重复不需要去掉
		if(!checkInListArray(unitListArr[i][0],rightTarget))
		{
			myEle = document.createElement("option") ;
			myEle.value = unitListArr[i][0];
			myEle.text = unitListArr[i][1];
			leftSource.add(myEle) ;
		}
	}
}
function initTarget_select(unitListArr,idList,rightTarget)
{
	var idArray=idList.split(",");
	//左边必须是右边的子集
	for(var i=0;i<unitListArr.length;i++)
	{
		if(checkInArray(unitListArr[i][0],idArray))
		{
			//不要判断是不是单选
			myEle = document.createElement("option") ;
			myEle.value = unitListArr[i][0];
			myEle.text = unitListArr[i][1];
			rightTarget.add(myEle) ;
		}
	}

}

function checkInListArray_select(id,rightTarget)
{
	var returnVal=false;
	id=id*1;

	if(id>0)
	{
		for(i=0;i<rightTarget.length;i++)
		{
			if(id==rightTarget.options[i].value)
			{
				returnVal=true;
				break;
			}
		}
	}
	else
	{
		returnVal=true;
	}
	return returnVal;
}

function checkInArray_select(id,strArray)
{
	var returnVal=false;
	id=id*1;
	if(id>0)
	{
		for(i=0;i<strArray.length;i++)
		{
			if(id==strArray[i])
			{
				returnVal=true;
				break;
			}
		}
	}
	else
	{
		returnVal=false;
	}
	return returnVal;
}


function selectSubmit_select(selectName,intTotalNumber)
{
	var returnHiddenID=',';
	var hiddenName=',';
	intTotalNumber = parseInt(intTotalNumber);
	var iCount = 0;
	intTotalNumber = isNaN(intTotalNumber)?0:intTotalNumber;
	for(i=0;i<selectName.length;i++)
	{
		returnHiddenID+=selectName.options[i].value+',';
		hiddenName+=selectName.options[i].text+',';
		iCount++;
	}
	if(intTotalNumber > 0)
	{
		if(intTotalNumber == iCount)
		{

		}
		else
		{
			alert("您只能选择"+intTotalNumber+"个数据");
			return false;
		}
	}
	parent.window.returnValue= returnHiddenID+"#"+hiddenName;
	window.close();
}

function getStrIDList_select(tbox)
{
	var rtn = "";
	if(tbox.length>0)
	{
		rtn = tbox.options[0].value;
		for(var i=1;i<tbox.length;i++)
		{
			if(tbox.options[i].value>0)
			{
				rtn += ","+tbox.options[i].value;
			}
		}
	}
	return rtn;
}

function onSearch_select(obj,tbox)
{
	obj.value = getStrIDList(tbox);
	return true;
}