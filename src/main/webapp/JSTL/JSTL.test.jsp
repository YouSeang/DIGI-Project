<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:test&gt;</h3>

<form name=form1 method="post" action="JSTL.test2.jsp">
	<jsp:useBean id="eltest" class="test.Eltest" scope="session"/>
	
	이름 <input type="text" name="name"> <br>
	<input type=submit value="입력"/>
</form>


</body>
</html>