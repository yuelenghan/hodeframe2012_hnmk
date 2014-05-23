/*
	popupMessage 1.1
	example:
	var pop = new popupMessage(20,128,128,"pop");
	pop.popmsg("hello!","Welcome !");
*/
function popupMessage(name)
{
	var oPopup = window.createPopup();
	var popTop=20;//初始高度；
	var popWidth=150;		//宽度
	var popHeight=150;		//高度
	var mytime,mytime3;
	var bottomHeight = 25;	//底边高度；
	var step = 4;			//速度单位；
	var waitTime = 1;		//弹出后的停留时间；
	var isDone = true;
	var i=0;
	var j=0;
	var array = new Array();

	this.setBottomHeight = function (prmBottomHeight)
	{
		bottomHeight = prmBottomHeight;
	}
	this.setStep = function (prmStep)
	{
		step = prmStep;
	}
	this.setWaitTime = function (prmWaitTime)
	{
		waitTime = prmWaitTime;
	}
	this.setPopWidth = function (prmPopWidth)
	{
		popWidth = prmPopWidth;
	}
	this.setPopHeight = function (prmPopHeight)
	{
		popHeight = prmPopHeight;
	}

	this.popmsgstr = function (html){
		if(isDone){
			popTop=20;
			oPopup.document.body.innerHTML = html;
			this.popshow();
		}
	}
	this.popmsg = function (title,msgstr,imgpath){
	var winstr="<table  border='0' cellpadding='0' cellspacing='0'>";
		winstr+="<tr>";
		winstr+="	<td height='16' colspan='3' valign='top'>";
		winstr+="		<table width='100%' height='16'  border='0' cellpadding='0' cellspacing='0'>";
		winstr+="		<tr>";
		winstr+="			<td valign='top' background='"+imgpath+"pop_02.gif'><img src='"+imgpath+"pop_01.gif' width='6' height='16'></td>";
		winstr+="			<td width='50' valign='top' background='"+imgpath+"pop_02.gif'></td>";
		winstr+="			<td valign='top' background='"+imgpath+"pop_02.gif'><img src='"+imgpath+"pop_03.gif' width='57' height='16'></td>";
		winstr+="			<td width='110' valign='top' background='"+imgpath+"pop_02.gif'></td>";
		winstr+="			<td valign='top' background='"+imgpath+"pop_02.gif'><div align='right'><a href=\"###\" onclick=\"parent."+name+".popshow();\"><img src='"+imgpath+"pop_05.gif' width='12' height='16' border=0></a></div></td>";
		winstr+="			<td valign='top' background='"+imgpath+"pop_02.gif'><div align='right'><img src='"+imgpath+"pop_06.gif' width='6' height='16'></div></td>";
		winstr+="		</tr>";
		winstr+="		</table>";
		winstr+="	</td>";
		winstr+="</tr>";
		winstr+="<tr valign='top'>";
		winstr+="	<td height='163' colspan='3'>";
		winstr+="		<table width='100%' height='100%'  border='0' cellpadding='0' cellspacing='0'>";
		winstr+="		<tr>";
		winstr+="			<td width='6' valign='top' background='"+imgpath+"pop_07.gif'></td>";
		winstr+="			<td valign='middle' bgcolor='#FDFDFF'>";
		winstr+="				<table width='90%' height='90%'  border='0' cellpadding='0' align='center' valign='middle' cellspacing='0'  style='TABLE-LAYOUT: fixed'><tr><td style='font-size: 9pt;LEFT: 0px; WIDTH: 30%; WORD-WRAP: break-word' valign='top'>"+msgstr+"</td></tr></table>";
		winstr+="			</td>";
		winstr+="			<td width='6' valign='top' background='"+imgpath+"pop_09.gif'></td>";
		winstr+="		</tr>";
		winstr+="		</table>";
		winstr+="	</td>";
		winstr+="</tr>";
		winstr+="<tr>";
		winstr+="	<td height='11' valign='top' background='"+imgpath+"pop_11.gif'><img src='"+imgpath+"pop_10.gif' width='6' height='11'></td>";
		winstr+="	<td height='11' valign='top' background='"+imgpath+"pop_11.gif'></td>";
		winstr+="	<td height='11' valign='top' background='"+imgpath+"pop_11.gif'><div align='right'><img src='"+imgpath+"pop_12.gif' width='6' height='11'></div></td>";
		winstr+="</tr>";
		winstr+="<tr>";
		winstr+="	<td height='4' valign='top' background='"+imgpath+"pop_14.gif'><img src='"+imgpath+"pop_13.gif' width='6' height='5'></td>";
		winstr+="	<td height='4' valign='top' background='"+imgpath+"pop_14.gif'></td>";
		winstr+="	<td height='4' valign='top' background='"+imgpath+"pop_14.gif'><div align='right'><img src='"+imgpath+"pop_15.gif' width='6' height='5'></div></td>";
		winstr+="</tr>";
		winstr+="</table>";
		if(isDone)
		{
			popTop = 20;
			oPopup.document.body.innerHTML = winstr;
			this.popshow();
		}
		else
		{
			array[i]=winstr;
			i++;
		}
	}

	this.popshow = function ()
	{
		//window.status=popTop;
		if(popTop == 20)
		{
			isDone = false;
			clearTimeout(mytime3);
		}
		if(popTop>(popHeight*2))
		{
			//弹完
			clearTimeout(mytime);
			clearTimeout(mytime3);
			isDone = true;
			//alert(j + "" + i);
			if(j==i)
				oPopup.hide();
			else
				this.popmsgstr(array[j++]);
			return;
		}
		else if(popTop>popHeight&&popTop<(popHeight*2))
		{	//弹下
			clearTimeout(mytime3);
			oPopup.show(screen.width-popWidth-5,screen.height-(2*popHeight-popTop)-bottomHeight,popWidth,popHeight+(popHeight-popTop));
		}
		else if(popTop<popHeight)
		{	//弹上
			oPopup.show(screen.width-popWidth-5,screen.height-popTop-bottomHeight,popWidth,popHeight-(popHeight-popTop));
		}
		popTop+=step;
		if(popTop>=popHeight && popTop<(popHeight+step))
		{
			clearTimeout(mytime);
			clearTimeout(mytime3);
			this.showDiv(screen.width-popWidth-5,screen.height-popTop-bottomHeight,popWidth,popHeight-(popHeight-popTop));
			if(waitTime>0)
			{
				mytime=setTimeout(name+".popshow();",waitTime*1000);
			}
		}
		else
		{
			clearTimeout(mytime);
			mytime=setTimeout(name+".popshow();",20);
		}
	}
	this.showDiv = function(left,top,divWidth,divHeight)
	{
		oPopup.show(left,top,divWidth,divHeight);
		mytime3=setTimeout(name+".showDiv("+left+","+top+","+divWidth+","+divHeight+")",50);
	}
	this.popclear = function ()
	{
		oPopup.document.body.innerHTML = "";
		clearTimeout(mytime);
		isDone=false;
	}
}
