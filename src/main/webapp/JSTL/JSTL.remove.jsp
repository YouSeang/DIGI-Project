<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:remove&gt;</h3>
<c:set value="batman" var="msg" />
msg : <c:out value="${msg}" /> <br>

<c:remove var="msg" />

after remove : <c:out value="${msg}"/>
</body>
</html>