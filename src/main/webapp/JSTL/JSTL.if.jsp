<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:if&gt;</h3>
<c:set value="superman" var="msg" />
msg : ${msg} <br>

<c:if test="${msg == 'superman'}" var="result">
	test result : ${result}
</c:if>


</body>
</html>