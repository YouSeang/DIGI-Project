<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>�α���</title>

</head>
<center>
<body>

<h2>LOGIN</h2>
<hr>

<%
if (session.getAttribute("User_id") == null) { // �α��� ���� Ȯ��
	// �α׾ƿ� ����
	
%>

<script>
function validateForm(form) {
	if(!form.user_id.value) {
		alert("���̵� �Է��ϼ���.");
		return false;
	}
	if(form.user_pw.value == "") {
		alert("�н����带 �Է��ϼ���.");
		return false;
	}
}


</script>


<form action="MemberLoginAction.lo" method="post" name="loginForm"
onsubmit="return validateForm(this);">
���̵� : <input type="text" name="user_id" class="form-control" style="width: 150px; margin: auto;"><br>
�н����� : <input type="password" name="user_pw" class="form-control" style="width: 150px; margin: auto;"><br>
<input type="submit" value="Login" class="btn btn-success">
</form>

<div><a href="./MemberJoinView.lo">ȸ������</a></div>
<% } else { %>
	<%= session.getAttribute("User_id") %>ȸ����, �α����ϼ̽��ϴ�.<br> ������������ ���ư��ϴ�.<br>
	<script>
    	// ������ �̵� �Լ�
    	function redirect() {
    		window.location.href = "main.lo"; // ���� �������� �̵�
    	}

    	// 5�� �Ŀ� ���� �������� �̵�
   			setTimeout(redirect, 3000); // 5000�и��� = 5��
    </script>
<% }
%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</center>
</html>