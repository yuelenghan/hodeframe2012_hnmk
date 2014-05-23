//通用的js语句和函数

var TANGER_OCX_bDocOpen = false;
var TANGER_OCX_strOp; //标识当前操作。1:新建；2:打开编辑；3:打开阅读
var TANGER_OCX_attachName; //标识已经存在的在线编辑文档附件的名称
var TANGER_OCX_attachURL; //在线编辑文档附件的URL
var TANGER_OCX_actionURL; //表单提交到的URL
var TANGER_OCX_OBJ; //控件对象
var TANGER_OCX_key=""; //加密签章
var TANGER_OCX_Username="匿名用户";


//此函数在网页装载时被调用。用来获取控件对象并保存到TANGER_OCX_OBJ
//同时，可以设置初始的菜单状况，打开初始文档等等。
function TANGER_OCX_Init(initdocurl)
{
	TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	var useUTF8 = (document.charset == "utf-8");
	TANGER_OCX_OBJ.IsUseUTF8Data = useUTF8;
	TANGER_OCX_OBJ.FileNew = false;
	TANGER_OCX_OBJ.FileClose = false;
	try
	{
		//保存该表单的提交url，将来传递给控件的SaveToURL函数
		TANGER_OCX_actionURL = document.forms[0].action;
		//获取当前操作代码
		TANGER_OCX_strOp = document.all.item("TANGER_OCX_op").innerHTML;
		//获取已经存在的附件名称
		TANGER_OCX_attachName = document.all.item("TANGER_OCX_attachName").innerHTML;
		//获取已经存在的附件URL
		TANGER_OCX_attachURL = document.all.item("TANGER_OCX_attachURL").innerHTML;
		try{
		   TANGER_OCX_key = document.all.item("TANGER_OCX_key").innerHTML;
		}catch(err){}finally{};
		TANGER_OCX_OBJ.SetAutoCheckSignKey(TANGER_OCX_key);

		switch(TANGER_OCX_strOp)
		{
			case "1":
				if(initdocurl!="")
				{
					TANGER_OCX_OBJ.BeginOpenFromURL(initdocurl,false,false);//参数：URL,是否显示进程,是否只读
				}
				break;
			case "2":
				if(TANGER_OCX_attachURL)
				{
					TANGER_OCX_OBJ.BeginOpenFromURL(TANGER_OCX_attachURL,true,false);
				}
				else
				{
					if(initdocurl!="")
					TANGER_OCX_OBJ.BeginOpenFromURL(initdocurl,true,false);
				}
				break;
			case "3":
				if(TANGER_OCX_attachURL)
				{
					TANGER_OCX_OBJ.BeginOpenFromURL(TANGER_OCX_attachURL,true,true);
				}
				break;
			default: //去要打开指定的模板文件，此时，TANGER_OCX_strOp指定的是url
				//以下使用当前文档的URL来获得模板的URL,也就是跟在?openform后面的部分
				var keystr = "?openform&".toUpperCase();
				var parastring = window.location.search;
				//alert(parastring);
				var urlbegin = parastring.toUpperCase().indexOf(keystr);
				if(-1 != urlbegin)
				{
					TANGER_OCX_strOp = parastring.substr(urlbegin+keystr.length);
					//alert(TANGER_OCX_strOp);
					//判断是否是WPS模板
					var wpsKey = "vwWpsTurl".toUpperCase();
					var isWpsTemplateURL = (-1 != parastring.toUpperCase().indexOf(wpsKey));
					if(!isWpsTemplateURL)
					{
						TANGER_OCX_OBJ.BeginOpenFromURL(TANGER_OCX_strOp,true,false);
					}
					else
					{
						TANGER_OCX_OBJ.BeginOpenFromURL(TANGER_OCX_strOp,true,false,"WPS.Document");
					}
				}
				break;
		}
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}
//增加演示自定义菜单项目
function addMyMenuItems()
{
	try{
		TANGER_OCX_OBJ = document.all.item("TANGER_OCX");
	    //增加自定义文件菜单项目
	    TANGER_OCX_OBJ.AddFileMenuItem('保存到服务器-用户自定义菜单',false,true,1);
		TANGER_OCX_OBJ.AddFileMenuItem('');
		//增加自定义主菜单项目
		TANGER_OCX_OBJ.AddCustomMenuItem('我的菜单1:切换禁止拷贝',false,false,1);
		TANGER_OCX_OBJ.AddCustomMenuItem('');
		TANGER_OCX_OBJ.AddCustomMenuItem('我的菜单2',false,false,2);
		TANGER_OCX_OBJ.AddCustomMenuItem('');
		TANGER_OCX_OBJ.AddCustomMenuItem('我的菜单3',false,false,3);
		TANGER_OCX_OBJ.AddCustomMenuItem('');
		TANGER_OCX_OBJ.AddCustomMenuItem('此菜单需要打开的文档才能使用',false,true,4);
	}
	catch(err)
	{
	}
}

function ShowTitleBar(bShow)
{
	TANGER_OCX_OBJ.Titlebar = bShow;
}
function ShowMenubar(bShow)
{
	TANGER_OCX_OBJ.Menubar = bShow;
}
function ShowToolMenu(bShow)
{
	TANGER_OCX_OBJ.IsShowToolMenu = bShow;
}
//从本地增加图片到文档指定位置
function AddPictureFromLocal()
{
	if(TANGER_OCX_bDocOpen)
	{
	    TANGER_OCX_OBJ.SetReadOnly(false);
        TANGER_OCX_OBJ.AddPicFromLocal(
        	"", //路径
        	true,//是否提示选择文件
        	true,//是否浮动图片
        	0,//如果是浮动图片，相对于左边的Left 单位磅
        	0, //如果是浮动图片，相对于当前段落Top
        	1 //光标位置
    	);
	};
}

//从URL增加图片到文档指定位置
function AddPictureFromURL(URL)
{
	if(TANGER_OCX_bDocOpen)
	{
	    TANGER_OCX_OBJ.SetReadOnly(false);
        TANGER_OCX_OBJ.AddPicFromURL(
        	URL,//URL 注意；URL必须返回Word支持的图片类型。
        	true,//是否浮动图片
        	0,//如果是浮动图片，相对于左边的Left 单位磅
        	0,//如果是浮动图片，相对于当前段落Top
        	1 //光标位置
	    );
	};
}

//从本地增加印章文档指定位置
function AddSignFromLocal()
{
//   alert(TANGER_OCX_key);
   if(TANGER_OCX_bDocOpen)
   {
      TANGER_OCX_OBJ.SetReadOnly(false);
      TANGER_OCX_OBJ.AddSignFromLocal
      (
    	TANGER_OCX_Username,//当前登陆用户
    	"",//缺省文件
    	true,//提示选择
    	0,//left
    	0,
    	TANGER_OCX_key,
    	1,
    	100,
    	0
	  ) ;
   }
}

//从URL增加印章文档指定位置
function AddSignFromURL(URL)
{
//   alert(TANGER_OCX_key);
   if(TANGER_OCX_bDocOpen)
   {
        TANGER_OCX_OBJ.SetReadOnly(false);
        TANGER_OCX_OBJ.AddSignFromURL
        (
        	TANGER_OCX_Username,//当前登陆用户
        	URL,//URL
        	0,//left
        	0,
        	TANGER_OCX_key,
        	1,
        	100,
        	0
        );  //top
   }
}

//开始手写签名
function DoHandSign()
{
//   alert(TANGER_OCX_key);
    if(TANGER_OCX_bDocOpen)
    {
        TANGER_OCX_OBJ.SetReadOnly(false);
    	TANGER_OCX_OBJ.DoHandSign2(TANGER_OCX_Username,TANGER_OCX_key);
    }
}
//开始手工绘图，可用于手工批示
function DoHandDraw()
{
	if(TANGER_OCX_bDocOpen)
	{
	    TANGER_OCX_OBJ.SetReadOnly(false);
    	TANGER_OCX_OBJ.DoHandDraw2();
	}
}
//检查签名结果
function DoCheckSign()
{
//   alert(TANGER_OCX_key);
   if(TANGER_OCX_bDocOpen)
   {
	//var ret = TANGER_OCX_OBJ.DoCheckSign
	(
	false,/*可选参数 IsSilent 缺省为FAlSE，表示弹出验证对话框,否则，只是返回验证结果到返回值*/
	TANGER_OCX_key
	);//返回值，验证结果字符串
	//alert(ret);
   }
}
//从服务器加盖安全印章
function addServerSecSign()
{
	var signUrl=document.all("secSignFileUrl").options[document.all("secSignFileUrl").selectedIndex].value;
		if(TANGER_OCX_OBJ.doctype==1||TANGER_OCX_OBJ.doctype==2)
		{
			try
			{TANGER_OCX_OBJ.AddSecSignFromURL("ntko",signUrl);}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
}
//从本机加盖安全印章
function addLocalSecSign()
{
		if(TANGER_OCX_OBJ.doctype==1||TANGER_OCX_OBJ.doctype==2)
		{
			try
			{TANGER_OCX_OBJ.AddSecSignFromLocal("ntko","");}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
}
//从EKEY加盖安全印章
function addEkeySecSign()
{
		if(TANGER_OCX_OBJ.doctype==1||TANGER_OCX_OBJ.doctype==2)
		{
			try
			{TANGER_OCX_OBJ.AddSecSignFromEkey("ntko");}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
}
//增加手写安全签名
function addHandSecSign()
{
		if(TANGER_OCX_OBJ.doctype==1||TANGER_OCX_OBJ.doctype==2)
		{
			try
			{TANGER_OCX_OBJ.AddSecHandSign("ntko");}
			catch(error){}
		}
		else
		{alert("不能在该类型文档中使用安全签名印章.");}
}


//如果原先的表单定义了OnSubmit事件，保存文档时首先会调用原先的事件。
function TANGER_OCX_doFormOnSubmit()
{
	var form = document.forms[0];
  	if (form.onsubmit)
	{
    	var retVal = form.onsubmit();
     	if (typeof retVal == "boolean" && retVal == false)
       	return false;
	}
	return true;
}


//允许或禁止用户从控件拷贝数据
function TANGER_OCX_SetNoCopy(boolvalue)
{
	TANGER_OCX_OBJ.IsNoCopy = boolvalue;
}

//设置用户名
function TANGER_OCX_SetDocUser(cuser)
{
    TANGER_OCX_Username = cuser;
	with(TANGER_OCX_OBJ.ActiveDocument.Application)
	{
		UserName = cuser;
	}
}

//设置页面布局
function TANGER_OCX_ChgLayout()
{
 	try
	{
		TANGER_OCX_OBJ.showdialog(5); //设置页面布局
	}
	catch(err){
		alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}

//打印文档
function TANGER_OCX_PrintDoc(isBackground)
{
	var oldOption;
	try
	{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		oldOption = objOptions.PrintBackground;
		objOptions.PrintBackground = isBackground;
	}
	catch(err){};

	TANGER_OCX_OBJ.printout(!isBackground);

	try
	{
		var objOptions =  TANGER_OCX_OBJ.ActiveDocument.Application.Options;
		objOptions.PrintBackground = oldOption;
	}
	catch(err){};
}

//此函数在文档关闭时被调用。
function TANGER_OCX_OnDocumentClosed()
{
	TANGER_OCX_bDocOpen = false;
}
//此函数用来保存当前文档。主要使用了控件的SaveToURL函数。
//有关此函数的详细用法，请参阅编程手册。
function TANGER_OCX_SaveDoc(fileName)
{
	var retStr=new String;
	var newwin,newdoc;
	if(fileName=="")
	{
		alert("请指定附件名称!");
		return;
	}
	try
	{
	 	if(!TANGER_OCX_doFormOnSubmit())return;
		if(!TANGER_OCX_bDocOpen)
		{
			alert("没有打开的文档。");
			return;
		}
		//在编辑状态下需要删除的附件名称
		var deleteFile = "";
		//设置要保存的附件文件名
		document.all.item("TANGER_OCX_filename").value = fileName;
		switch(TANGER_OCX_strOp)
		{
			case "3":
				alert("文档处于阅读状态，您不能保存到服务器。");
				break;
			case "2": //需要首先删除以前的文档附件
				deleteFile = (TANGER_OCX_attachName=="")?"":"%%Detach="+escape(TANGER_OCX_attachName);
			case "1":
				//新建文档
			default:
				retStr = TANGER_OCX_OBJ.SaveToURL(TANGER_OCX_actionURL,
				document.all.item("NTKO_UPLOADFIELD").name, //子表单的文件上载控件的名称
				deleteFile,
				fileName,
				0 //同时提交forms[0]的信息
				);
				newwin = window.open("","_blank","left=200,top=200,width=400,height=200,status=0,toolbar=0,menubar=0,location=0,scrollbars=0,resizable=0",false);
				newdoc = newwin.document;
				newdoc.open();
				newdoc.write("<center><hr>"+retStr+"<hr><input type=button VALUE='关闭窗口' onclick='window.close()'></center>");
				newdoc.close();
				//window.alert(retStr);
				window.opener.location.reload();
			   window.close();
				break;
		}
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}

//此函数在文档打开时被调用。
function TANGER_OCX_OnDocumentOpened(str, obj)
{
	try
	{
		TANGER_OCX_bDocOpen = true;
		//设置用户名
		TANGER_OCX_SetDocUser(TANGER_OCX_Username);
		if(obj)
		{
			switch(TANGER_OCX_strOp)
			{
				case "1":
				case "2":
				    TANGER_OCX_OBJ.SetReadOnly(false);
					break;
				case "3":
					TANGER_OCX_OBJ.SetReadOnly(true);
					break;
				default:
					break;
			}
		}
	}
	catch(err){

	}
	finally{
	}
}
function SaveAsHTML(URL,uploadfield,fileName)
{
	try
	{
		var retStr = TANGER_OCX_OBJ.PublishAsHTMLToURL(
						URL,uploadfield,
						"__Click=0&subject="+escape(document.forms(0).Subject.value)+
						"&filename="+fileName,
						fileName
					);
		var newwin = window.open("","_blank","left=200,top=200,width=400,height=200,status=0,toolbar=0,menubar=0,location=0,scrollbars=0,resizable=0",false);
			var newdoc = newwin.document;
			newdoc.open();
			newdoc.write("<center><hr>"+retStr+"<hr><input type=button VALUE='关闭窗口' onclick='window.close()'></center>");
			newdoc.close();
	}
	catch(err){
		alert("不能保存到URL：" + err.number + ":" + err.description);
	}
	finally{
	}
}

//用于对控件中的Word文档进行操作的实用函数，根据需要，调用了tangerocx.js中的函数

//允许或禁止显示修订工具栏和工具菜单（保护修订）
function WPSEnterRevisionMode(BoolValue)
{
    var doc = TANGER_OCX_OBJ.ActiveDocument;
    var app = doc.Application;
    var doctype = TANGER_OCX_OBJ.DocType;
    if( 6 != doctype)
    {
	    alert("此功能需要使用WPS！");
	    return;
    }
    var cmdbars = app.CommandBars;
    TANGER_OCX_OBJ.IsShowToolMenu = !BoolValue;	//关闭或打开工具菜单
    doc.TrackRevisions = BoolValue;
	cmdbars("Reviewing").Enabled = !BoolValue;
	cmdbars("Reviewing").Visible = !BoolValue;
	//RevisionTextPopupMenuOntbShortcutMenus 禁止右键菜单。很奇怪使用字符串不行。
	cmdbars(40).Enabled = !BoolValue;
	cmdbars(40).Visible = !BoolValue;
}

function TANGER_OCX_EnableReviewBar(boolvalue)
{
    try{
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Reviewing").Enabled = boolvalue;
	TANGER_OCX_OBJ.ActiveDocument.CommandBars("Track Changes").Enabled = boolvalue;
	TANGER_OCX_OBJ.IsShowToolMenu = boolvalue;	//关闭或打开工具菜单
	}catch(E){}finally{};
}

//打开或者关闭修订模式
function TANGER_OCX_SetReviewMode(boolvalue)
{
    try{
    TANGER_OCX_OBJ.SetReadOnly(false);
	TANGER_OCX_OBJ.ActiveDocument.TrackRevisions = boolvalue;
	}catch(E){}finally{};
}

//进入或退出痕迹保留状态，调用上面的两个函数
function TANGER_OCX_SetMarkModify(boolvalue)
{
    try{TANGER_OCX_OBJ.SetReadOnly(false);}catch(E){}finally{};
    if( 6 == TANGER_OCX_OBJ.doctype)
    {
    	WPSEnterRevisionMode(boolvalue);
    }
	else
	{
		TANGER_OCX_SetReviewMode(boolvalue);
		TANGER_OCX_EnableReviewBar(!boolvalue);
	}
}

//显示/不显示修订文字
function TANGER_OCX_ShowRevisions(boolvalue)
{
    try{TANGER_OCX_OBJ.SetReadOnly(false);}catch(E){}finally{};
	TANGER_OCX_OBJ.ActiveDocument.ShowRevisions = boolvalue;
}

//打印/不打印修订文字
function TANGER_OCX_PrintRevisions(boolvalue)
{
    try{TANGER_OCX_OBJ.SetReadOnly(false);}catch(E){}finally{};
	TANGER_OCX_OBJ.ActiveDocument.PrintRevisions = boolvalue;
}
//接受所有修订
function TANGER_OCX_AcceptAllRevisions()
{
    try{TANGER_OCX_OBJ.SetReadOnly(false);}catch(E){}finally{};
	TANGER_OCX_OBJ.ActiveDocument.AcceptAllRevisions();
}


//此函数用来加入一个自定义的文件头部
function TANGER_OCX_AddDocHeader( strHeader )
{
	var i,cNum = 30;
	var lineStr = "";
	try
	{
		for(i=0;i<cNum;i++) lineStr += "_";  //生成下划线
		with(TANGER_OCX_OBJ.ActiveDocument.Application)
		{
			Selection.HomeKey(6,0); // go home
			Selection.TypeText(strHeader);
			Selection.TypeParagraph(); 	//换行
			Selection.TypeText(lineStr);  //插入下划线
			// Selection.InsertSymbol(95,"",true); //插入下划线
			Selection.TypeText("★");
			Selection.TypeText(lineStr);  //插入下划线
			Selection.TypeParagraph();
			//Selection.MoveUp(5, 2, 1); //上移两行，且按住Shift键，相当于选择两行
			Selection.HomeKey(6,1);  //选择到文件头部所有文本
			Selection.ParagraphFormat.Alignment = 1; //居中对齐
			with(Selection.Font)
			{
				NameFarEast = "宋体";
				Name = "宋体";
				Size = 12;
				Bold = false;
				Italic = false;
				Underline = 0;
				UnderlineColor = 0;
				StrikeThrough = false;
				DoubleStrikeThrough = false;
				Outline = false;
				Emboss = false;
				Shadow = false;
				Hidden = false;
				SmallCaps = false;
				AllCaps = false;
				Color = 255;
				Engrave = false;
				Superscript = false;
				Subscript = false;
				Spacing = 0;
				Scaling = 100;
				Position = 0;
				Kerning = 0;
				Animation = 0;
				DisableCharacterSpaceGrid = false;
				EmphasisMark = 0;
			}
			Selection.MoveDown(5, 3, 0); //下移3行
		}
	}
	catch(err){
		//alert("错误：" + err.number + ":" + err.description);
	}
	finally{
	}
}
//将html form的域值拷贝到Word文档的标签中
function CopyTextToBookMark(inputname,BookMarkName)
{
	try
	{
		var inputValue="";
		var j,elObj,optionItem;
		var elObj = document.forms[0].elements(inputname);
		if (!elObj)
		{
			alert("HTML的FORM中没有此输入域："+ inputname);
			return;
		}
		switch(elObj.type)
		{
				case "select-one":
					inputValue = elObj.options[elObj.selectedIndex].text;
					break;
				case "select-multiple":
					var isFirst = true;
					for(j=0;j<elObj.options.length;j++)
					{
						optionItem = elObj.options[j];
						if (optionItem.selected)
						{
							if(isFirst)
							{
								inputValue = optionItem.text;
								isFirst = false;
							}
							else
							{
								inputValue += "  " + optionItem.text;
							}
						}
					}

					break;
				default: // text,Areatext,selecte-one,password,submit,etc.
					inputValue = elObj.value;
					break;
		}
		//do copy
		//DEBUG
		//alert(inputname+"="+inputValue+" Bookmarkname="+BookMarkName);
		var bkmkObj = TANGER_OCX_OBJ.ActiveDocument.BookMarks(BookMarkName);
		if(!bkmkObj)
		{
			alert("Word 模板中不存在名称为：\""+BookMarkName+"\"的书签！");
		}
		var saverange = bkmkObj.Range;
		saverange.Text = inputValue;
		TANGER_OCX_OBJ.ActiveDocument.Bookmarks.Add(BookMarkName,saverange);
	}
	catch(err){

	}
	finally{
	}
}
