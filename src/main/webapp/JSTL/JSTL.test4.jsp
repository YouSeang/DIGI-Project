<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:test4&gt;</h3>
<%-- <jsp:useBean id="eltest" class="test.Eltest" scope="session" /> --%>
1. 선택한 상품 : ${param.sel} <br>
2. num1 값 : ${eltest.num1} <br>
3. num2 값 : ${eltest.num2} <br>
4. num1 + num2 : ${eltest.num1 + eltest.num2}

</body>
</html>