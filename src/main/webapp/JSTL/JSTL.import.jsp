<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:import&gt;</h3>

다음은 import 를 이용해 포함한 것입니다. <hr>

<c:import url="JSTL.set.jsp" var="myurl" />
<c:out value="${myurl}" escapeXml="false" />


</body>
</html>