var line = ' │';
var empty = '&nbsp;&nbsp;';
var join = ' ├';
var joinBottom =' └';
var minus = ' ├';
var minusBottom	= ' └';
var aIndent = [];
function showTreeMainInfo(unitListArrForTree,strShowType,useLines,strPara1,strPara2)
{
	var csInfo;
	for (var n=0; n<unitListArrForTree.length; n++)
	{
		if(n  == 0)
			unitListArrForTree[0][1] = 0;
		csInfo=checkNodeInfo(unitListArrForTree,unitListArrForTree[n]).split(";");
		unitListArrForTree[n][10] = csInfo[0];
		unitListArrForTree[n][11] = csInfo[1];
	}
	for (var n=0; n<unitListArrForTree.length; n++)
	{
		if(unitListArrForTree[n][1]  == 0)
			document.write(showTreeNode(unitListArrForTree,unitListArrForTree[n],strShowType,useLines,strPara1,strPara2));
	}
}



function showMainInfo(unitListArrForTree)
{
	var csInfo;
	for (var n=0; n<unitListArrForTree.length; n++)
	{
		if(n  == 0)
			unitListArrForTree[0][1] = 0;
		csInfo=checkNodeInfo(unitListArrForTree,unitListArrForTree[n]).split(";");
		unitListArrForTree[n][10] = csInfo[0];
		unitListArrForTree[n][11] = csInfo[1];
	}
}


function addTreeNode(unitListArrForTree,node,strShowType,useLines,strPara1,strPara2)
{
	var str = '';
	for (var n = 0; n<unitListArrForTree.length; n++)
	{
		if (unitListArrForTree[n][1] == node[0])
		{
			str += showTreeNode(unitListArrForTree,unitListArrForTree[n],strShowType,useLines,strPara1,strPara2);
		}
	}
	return str;
}

/**
扩展方法名 showTreeNodeLocal(unitListArrForTree,node,strShowType,useLines,strPara1,strPara2) 写在各个页面中
*/
function showTreeNode(unitListArrForTree,node,strShowType,useLines,strPara1,strPara2)
{

	try
	{
		return showTreeNodeLocal(unitListArrForTree,node,strShowType,useLines,strPara1,strPara2);
	}
	catch(e)
	{
	}

	var str = "";
	if(strShowType == "select")
	{
		str = indentTree(node,useLines);
		str = "<option value='"+node[0]+"'"+((node[0] == strPara1)?" selected":"")+">"+str+node[2]+"</option>";
	}
	else if(strShowType == "text")
	{
		str = indentTree(node,useLines);
		str += node[2]+"<br>";
	}
	else if(strShowType == "tr")
	{
		if(node[3] && node[3].length>0)
		{
			str += node[3][1];
			for(i =3 ;i<node[3][0];i++)
			{
				str += node[3][i];
			}
			str += "<td>"+indentTree(node,useLines)+node[2]+"</td>";
			str += node[3][2];
		}
	}
	else
	{
			str = indentTree(node,useLines);
			str += node[2]+"<br>";
	}

	if (node[10])
	{
		str += addTreeNode(unitListArrForTree,node,strShowType,useLines,strPara1,strPara2);
	}

	this.aIndent.pop();
	return str;
}

// Adds the empty and line icons
function indentTree (node,useLines)
{
	var str = '';
	if ( node[1]  != 0 )
	{
		for (var n=0; n<aIndent.length; n++)
			str +=  ( (aIndent[n] == 1 && useLines) ? line : empty );

		(node[11] == 1) ? aIndent.push(0) : aIndent.push(1);

		str +=  ( (useLines) ? ((node[11] == 1) ? joinBottom : join ) : empty);
	}
	return str;
};

function checkNodeInfo(unitListArrForTree,thisNode)
{
	var lastId = -1;
	var intHasLast=0;
	var intHasChild=0;
	for (var n=0; n<unitListArrForTree.length; n++)
	{
		if (unitListArrForTree[n][1] == thisNode[0])
			intHasChild = 1;
		if (unitListArrForTree[n][1] == thisNode[1])
			lastId = unitListArrForTree[n][0];
	}
	if (lastId == thisNode[0])
		intHasLast = 1;
	return intHasChild+";"+intHasLast;
}
function getNodeInfoById(intID ,unitListArrForTree)
{
	for (var n=0; n<unitListArrForTree.length; n++)
	{
		if(unitListArrForTree[n][0] == intID)
		{
			return unitListArrForTree[n];
		}
	}
}
/*
var unitListArrForTree = new Array();
//id,fatherid,name ,intHasChild ,intHasLast
unitListArrForTree[0] = Array("1","0","系统组");
unitListArrForTree[1] = Array("9","1","希迪麦德硬件");
unitListArrForTree[2] = Array("7","1","南京新百");
unitListArrForTree[10] = Array("8","1","吴越之国");
unitListArrForTree[11] = Array("16","9","硬件部门2");
unitListArrForTree[5] = Array("15","9","硬件部门1");
unitListArrForTree[6] = Array("14","8","吴越之国子单位一");
unitListArrForTree[7] = Array("11","8","吴越之国业务部");
unitListArrForTree[8] = Array("13","11","吴越之国业务部业务一部");
unitListArrForTree[9] = Array("10","8","吴越之国开发部");
unitListArrForTree[10] = Array("21","10","吴越之国开发部");
unitListArrForTree[11] = Array("22","10","吴越之国开发部");
unitListArrForTree[12] = Array("23","7","吴越之国开发部a");
unitListArrForTree[13] = Array("24","7","吴越之国开发部a");
showMainInfo(unitListArrForTree,"text",true);

//数组名称,类型,是否需要line,其他1,其他2
*/

// fbox - 待选项目列表
// tbox - 选择了的项目列表
function move(unitListArr,fbox,tbox)
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
	BumpUp(fbox);
	SortD(unitListArr,tbox);
	clearSelect(fbox);
}
function clearSelect(fbox)
{
	fbox.selectedIndex = -1;
}
function moveAll(unitListArr,fbox,tbox)
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
	BumpUp(fbox);
	SortD(unitListArr,tbox);
}
// 清除空的项目列表
function BumpUp(box)
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
		BumpUp(box);
	}
}

function SortD(unitListArr,box)
{

	var temp_opts = new Array()
	var temp = new Object()
	// 调用临时数组
	for(var i=0; i<box.options.length; i++)
	{
		temp_opts[i] = box.options[i].value;
	}
	var m = 0;
	for(var i=0;i<unitListArr.length;i++)
	{
		if(checkInArray(unitListArr[i][0],temp_opts))
		{
			box.options[m].value = unitListArr[i][0];
			box.options[m].text = unitListArr[i][2];
			m = m + 1;
		}
	}
/*	for(var x=0; x<temp_opts.length-1; x++)
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
	*/
}
function moveUp(obj)
{
	with (obj)
	{
		if(selectedIndex==0)
		{
		}
		else if(selectedIndex>=1)
		{
			moveG(obj,-1)
		}
	}
}
function moveDown(obj)
{
	with (obj)
	{
		if(selectedIndex==length-1)
		{
		}
		else if(selectedIndex>=0 && selectedIndex<length-1)
			moveG(obj,+1)
	}
}
function moveG(obj,offset)
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
var aIndentForSelect = [];
function addNodeForSelect(unitListArrbb,node,useLines,leftSource,rightTarget)
{
	var str = '';
	for (var n = 0; n<unitListArrbb.length; n++)
	{
		if (unitListArrbb[n][1] == node[0])
		{
			showNodeForSelect(unitListArrbb,unitListArrbb[n],useLines,leftSource,rightTarget);
		}
	}
}

function showNodeForSelect(unitListArrbb,node,useLines,leftSource,rightTarget)
{
	//要判断是不是单选,如果是单选，则source中的值重复不需要去掉
	if(!checkInListArray(node[0],rightTarget) && node[2]!="")
	{
		myEle = document.createElement("option") ;
		myEle.value = node[0];
		myEle.text = node[2];
		leftSource.add(myEle) ;
	}
	if (node[10])
	{
		addNodeForSelect(unitListArrbb,node,useLines,leftSource,rightTarget);
	}
	this.aIndentForSelect.pop();
}
function checkNodeInfoForSelect(unitListArrbb,thisNode)
{
	var lastId = -1;
	var intHasLast=0;
	var intHasChild=0;
	for (var n=0; n<unitListArrbb.length; n++)
	{
		if (unitListArrbb[n][1] == thisNode[0])
			intHasChild = 1;
		if (unitListArrbb[n][1] == thisNode[1])
			lastId = unitListArrbb[n][0];
	}
	if (lastId == thisNode[0])
		intHasLast = 1;
	return intHasChild+";"+intHasLast;
}
function initSource1(unitListArrbb,arr,leftSource,rightTarget)
{
	leftSource.length=0;
	var csInfo;
	for(var i=0;i<unitListArrbb.length;i++)
	{
		if(!checkInListArray(unitListArrbb[i][0],rightTarget) && checkInArray(unitListArrbb[i][1],arr) && unitListArrbb[i][2]!="")
		{
			myEle = document.createElement("option") ;
			myEle.value = unitListArrbb[i][0];
			myEle.text = unitListArrbb[i][2];
			leftSource.add(myEle) ;
		}

	}
}

function initSource(unitListArrbb,leftSource,rightTarget)
{
	leftSource.length=0;
	var csInfo;
	for(var i=0;i<unitListArrbb.length;i++)
	{
		if(i  == 0)
			unitListArrbb[0][1] = 0;

	}
	showNodeForSelect(unitListArrbb,unitListArrbb[0],false,leftSource,rightTarget)
}

function initTarget(unitListArrbb,idList,rightTarget)
{
	var idArray=idList.split(",");

	//左边必须是右边的子集
	for(var i=0;i<idArray.length;i++)
	{
		for(var j=0;j<unitListArrbb.length;j++)
		{
			if(unitListArrbb[j][0] == idArray[i] && unitListArrbb[j][2]!="" && idArray[i] > 0)
			{
				//不要判断是不是单选
				myEle = document.createElement("option") ;
				myEle.value = unitListArrbb[j][0];
				myEle.text = unitListArrbb[j][2];
				rightTarget.add(myEle) ;
			}
		}
	}
/*
	//左边必须是右边的子集
	for(var i=0;i<unitListArrbb.length;i++)
	{
		if(checkInArray(unitListArrbb[i][0],idArray)  && unitListArrbb[i][2]!="")
		{
			//不要判断是不是单选
			myEle = document.createElement("option") ;
			myEle.value = unitListArrbb[i][0];
			myEle.text = unitListArrbb[i][2];
			rightTarget.add(myEle) ;
		}
	}
*/
}

function checkInListArray(id,rightTarget)
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

function checkInArray(id,strArray)
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

function selectSubmit(selectName,isSelectOne)
{
	if(isSelectOne == 1 && selectName.options.length > 1)
	{
		alert("只能选择单一的单位、部门或人员");
		return;
	}
	var returnHiddenID=',';
	var hiddenName=',';

	for(i=0;i<selectName.length;i++)
	{
		returnHiddenID+=selectName.options[i].value+',';
		hiddenName+=selectName.options[i].text+',';
	}
	parent.window.returnValue= returnHiddenID+"#"+hiddenName;
	window.close();
}

function initTarget_1(unitListArrbb,idList,rightTarget)
{
	var idArray=idList.split(",");
	//左边必须是右边的子集
	for(var i=0;i<idArray.length;i++)
	{
		for(var j=0;j<unitListArrbb.length;j++)
		{
			if(unitListArrbb[j][0] == idArray[i] && unitListArrbb[j][2]!="" && idArray[i] > 0)
			{
				//不要判断是不是单选
				myEle = document.createElement("option") ;
				myEle.value = unitListArrbb[j][0];
				myEle.text = unitListArrbb[j][2];
				rightTarget.add(myEle) ;
			}
		}
	}
}

function initSource_1(unitListArrbb,leftSource,rightTarget)
{
	leftSource.length=0;
	var csInfo;
	for(var i=0;i<unitListArrbb.length;i++)
	{
		if(!checkInListArray(unitListArrbb[i][0],rightTarget) && unitListArrbb[i][2]!="")
		{
			myEle = document.createElement("option") ;
			myEle.value = unitListArrbb[i][0];
			myEle.text = unitListArrbb[i][2];
			leftSource.add(myEle) ;
		}

	}
}

