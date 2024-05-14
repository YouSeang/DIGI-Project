<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
session.invalidate();
// 응답이 이미 시작되었는지 확인
if (!response.isCommitted()) {
    // 리다이렉트
    response.sendRedirect(request.getContextPath() + "/Login.lo");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>로그아웃</title>
</head>
<body>

</body>
</html>