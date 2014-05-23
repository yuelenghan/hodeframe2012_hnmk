<%@ page language="java" isErrorPage="true" %>
<HTML>
<HEAD>
<TITLE>403</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
</HEAD>
<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">
<%-- ActionError Messages - usually set in Actions --%>
<ww:if test="hasActionErrors()">
    <div class="error fade-ffff00" id="errorMessages">
      <ww:iterator value="actionErrors">
			    **
          <ww:property/><br />
      </ww:iterator>
   </div>
</ww:if>

<%-- FieldError Messages - usually set by validation rules --%>
<ww:if test="hasFieldErrors()">
    <div class="error fade-ffff00" id="errorMessages">
      <ww:iterator value="fieldErrors">
          <ww:iterator value="value">
			@@
             <ww:property/><br />
          </ww:iterator>
      </ww:iterator>
   </div>
</ww:if>
<div align="center"><h1>403</h1></div>
</BODY>
</HTML>