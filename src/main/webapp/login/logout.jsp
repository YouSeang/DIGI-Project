<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%
session.invalidate();
// ������ �̹� ���۵Ǿ����� Ȯ��
if (!response.isCommitted()) {
    // �����̷�Ʈ
    response.sendRedirect(request.getContextPath() + "/Login.lo");
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>�α׾ƿ�</title>
</head>
<body>

</body>
</html>