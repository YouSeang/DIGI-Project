<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:redirect&gt;</h3>

<c:redirect url="/JSTL.choose.jsp">
	<c:param name="sel">a</c:param>
</c:redirect>


</body>
</html>