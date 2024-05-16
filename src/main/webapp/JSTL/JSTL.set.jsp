<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:set&gt;</h3>
<!-- page scope에 Hello World를 저장하는데 이때 var의 "msg"라는 곳에 저장  -->
<c:set value="Hello World" var="msg" />

msg : ${msg} <br> <!-- EL을 활용해서 처리 -->
msg : <%= pageContext.getAttribute("msg") %> <br>


</body>
</html>