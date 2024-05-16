<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:url&gt;</h3>

<c:url value="/JSTL.choose.jsp" var="target">
	<c:param name="sel">a</c:param>
</c:url>
<hr>
단순출력 : ${target} <Br> 
링크연동 : <a href="${target}">choose.jsp-a선택</a>

</body>
</html>


