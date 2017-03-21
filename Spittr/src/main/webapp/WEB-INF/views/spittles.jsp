<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>Spittr</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="<c:url value="/resources/style.css" />" >
  </head>
  
  <body>
    <c:forEach items="${spittleList}" var="spittle">
    	<li id="spittle_<c:out value="${spittle.id}"/>">
    		<div class="spittleMessage">
    			<c:out value="${spittle.message}" />
    		</div>
    		<div>
    			<span class="spittleTime"><c:out value="${spittle.time}"/></span>
    			<span class="SpittleLocation">(<c:out value="${spittle.latitude}"/>,
    				<c:out value="${spittle.longitude}"/>)</span>
    		</div>
    	</li>
    </c:forEach>
  </body>
</html>
