	<!-- rbac -->
	<sqlMap resource="config/rbac/mysql/GroupSQL.xml"/>	
	<sqlMap resource="config/rbac/mysql/PrivilegeSQL.xml"/>
	<sqlMap resource="config/rbac/mysql/RoleSQL.xml"/>
	<sqlMap resource="config/rbac/mysql/UserSQL.xml"/>	




			/WEB-INF/classes/config/rbac/bean/applicationContext-Service.xml,
			/WEB-INF/classes/config/rbac/bean/applicationContext-Action.xml,


	<!-- rbac -->
	<include file = "config/rbac/action/GroupAction.xml" />
	<include file = "config/rbac/action/PrivilegeAction.xml" />
	<include file = "config/rbac/action/RoleAction.xml" />
	<include file = "config/rbac/action/UserAction.xml" />
	<include file = "config/rbac/action/PersonAction.xml" />


	<INPUT TYPE="button" VALUE="退出" onclick="if (confirm('您确定要退出么？')==true) {top.setCookie('mainContentValue','');top.location.href='/hodeframe2010_common/index/loginout.action'}" >
	<INPUT TYPE="button" VALUE="首页" onclick="changeURL('','main.action',20)">
 	<INPUT TYPE="button" VALUE="修改密码" onclick="changeURL('','../rbac/user/editPasswordForm.action',20)">

	<INPUT TYPE="button" VALUE="部门管理" onclick="changeURL('../rbac/group/showLeftTree.action','../rbac/group/readme.action',6)" >
	<INPUT TYPE="button" VALUE="角色管理" onclick="changeURL('../rbac/role/readme.action','../rbac/role/showPageList.action',6)"  >

	<INPUT TYPE="button" VALUE="用户管理" onclick="changeURL('../rbac/group/showUserTree.action','../rbac/user/readme.action',6)" >
	<INPUT TYPE="button" VALUE="权限管理" onclick="changeURL('','../rbac/privilege/showList.action',6)" >


	
	<INPUT TYPE="button" VALUE="在线人员1" onclick="changeURL('','../rbac/user/statisticsOnLineList.action',6)" >

	<INPUT TYPE="button" VALUE="在线人员2" onclick="changeURL('','../rbac/user/statisticsOnLineUnit.action',6)" >
	<INPUT TYPE="button" VALUE="资料修改" onclick="changeURL('','../rbac/user/updatePersonnelUserForm.action',6)" >
	<INPUT TYPE="button" VALUE="平台资料" onclick="changeURL('','../rbac/user/updatePersonnelUserOtherForm.action',6)" >
	<INPUT TYPE="button" VALUE="切换用户" onclick="changeURL('','../rbac/user/changeUserForm.action',6)" >
	<INPUT TYPE="button" VALUE="授权访问" onclick="changeURL('','../rbac/user/updateAllowUserForm.action',6)" >
