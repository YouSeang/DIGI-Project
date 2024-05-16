<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="test.Eltest" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JSTL: Core Library</title>
</head>
<body bgcolor="#ffffff">

<h3>&lt;c:test5&gt;</h3>

<jsp:useBean id="eltest2" class="test.Eltest" scope="session" />
<%-- <% Eltest eltest = (Eltest)session.getAttribute("eltest"); %> --%>

1. 선택한 상품 : <%= request.getParameter("sel") %> <br>
2. num1 값 : <%= eltest2.getNum1() %><br> 
3. num2 값 : <%= eltest2.getNum2() %> <br>
4. num1 + num2 : <%= eltest2.getNum1() + eltest2.getNum2() %>
</html>