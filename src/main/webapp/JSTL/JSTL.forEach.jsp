<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<center>
<h3>상품목록</h3>
<hr>
<form name=form1 method="post" action="../EL2/Sel.jsp">
	<jsp:useBean id="eltest" class="test.Eltest" scope="session" />
	
	<select name="sel">
	<c:forEach items="${eltest.productList}" var="item">
		<option> ${item}</option>
	</c:forEach>

	</select>
	<input type="submit" value="선택"/>

</form>
</center>
</body>
</html>