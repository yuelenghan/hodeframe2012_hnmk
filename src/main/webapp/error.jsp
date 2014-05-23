<%@ page language="java" isErrorPage="true" %>
<%@ taglib uri="/WEB-INF/tld/webwork.tld" prefix="ww" %>
<HTML>
<HEAD>
<TITLE>error</TITLE>
<META HTTP-EQUIV="Content-Type" CONTENT="text/html; charset=UTF-8">
</HEAD>
<BODY bgcolor="#FFFFFF" text="#000000" leftmargin="0" topmargin="0">

<div align="center"><h1>this is a mistake in system</h1></div>

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

<% if (exception != null) { %>
    <pre><% exception.printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } else if ((Exception)request.getAttribute("javax.servlet.error.exception") != null) { %>
    <pre><% ((Exception)request.getAttribute("javax.servlet.error.exception"))
                           .printStackTrace(new java.io.PrintWriter(out)); %></pre>
 <% } %>
</BODY>
</HTML>
