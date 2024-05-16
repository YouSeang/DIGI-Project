<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:for&gt;</h3>

<c:forTokens items="123-456-789" delims="-" var="sel">
	${sel} <br>
</c:forTokens>

</body>
</html>