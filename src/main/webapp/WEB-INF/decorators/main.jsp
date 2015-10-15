<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.opensymphony.com/sitemesh/decorator" prefix="decorator" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- "src/main/webapp/WEB-INF/lib/sitemesh-decorator.tld" -->
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	
	<title>
		<%-- 目标页面的title --%>
		<decorator:title />
	</title>
	<script type="text/javascript" src="<c:url value='/js/jquery/jquery-1.11.0.js'/> "></script>
	
	<%-- 目标页面的head --%>
	<decorator:head />
</head>
<body onload="<decorator:getProperty property='body.onload'/>" >
    <%-- 目标页面的body --%>
     <decorator:body />
    
</body>
</html>