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


	<INPUT TYPE="button" VALUE="�˳�" onclick="if (confirm('��ȷ��Ҫ�˳�ô��')==true) {top.setCookie('mainContentValue','');top.location.href='/hodeframe2010_common/index/loginout.action'}" >
	<INPUT TYPE="button" VALUE="��ҳ" onclick="changeURL('','main.action',20)">
 	<INPUT TYPE="button" VALUE="�޸�����" onclick="changeURL('','../rbac/user/editPasswordForm.action',20)">

	<INPUT TYPE="button" VALUE="���Ź���" onclick="changeURL('../rbac/group/showLeftTree.action','../rbac/group/readme.action',6)" >
	<INPUT TYPE="button" VALUE="��ɫ����" onclick="changeURL('../rbac/role/readme.action','../rbac/role/showPageList.action',6)"  >

	<INPUT TYPE="button" VALUE="�û�����" onclick="changeURL('../rbac/group/showUserTree.action','../rbac/user/readme.action',6)" >
	<INPUT TYPE="button" VALUE="Ȩ�޹���" onclick="changeURL('','../rbac/privilege/showList.action',6)" >


	
	<INPUT TYPE="button" VALUE="������Ա1" onclick="changeURL('','../rbac/user/statisticsOnLineList.action',6)" >

	<INPUT TYPE="button" VALUE="������Ա2" onclick="changeURL('','../rbac/user/statisticsOnLineUnit.action',6)" >
	<INPUT TYPE="button" VALUE="�����޸�" onclick="changeURL('','../rbac/user/updatePersonnelUserForm.action',6)" >
	<INPUT TYPE="button" VALUE="ƽ̨����" onclick="changeURL('','../rbac/user/updatePersonnelUserOtherForm.action',6)" >
	<INPUT TYPE="button" VALUE="�л��û�" onclick="changeURL('','../rbac/user/changeUserForm.action',6)" >
	<INPUT TYPE="button" VALUE="��Ȩ����" onclick="changeURL('','../rbac/user/updateAllowUserForm.action',6)" >
