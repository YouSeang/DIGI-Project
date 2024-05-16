<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:test3&gt;</h3>

<form name=form1 method="post" action="JSTL.test5.jsp">
	<jsp:useBean id="eltest1" class="test.Eltest" scope="session" />
	<jsp:setProperty name="eltest1" property="num1" value="3000"/>
		${eltest1.num1}
	<select name="sel">
	<c:forEach items="${eltest1.productList}" var="item">
		<option> ${item}</option>
	</c:forEach>

	</select>
	<input type="submit" value="선택"/>

</form>

</body>
</html>