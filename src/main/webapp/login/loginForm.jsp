<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
<meta charset="EUC-KR">
<title>로그인</title>

</head>
<center>
<body>

<h2>LOGIN</h2>
<hr>

<%
if (session.getAttribute("User_id") == null) { // 로그인 상태 확인
	// 로그아웃 상태
	
%>

<script>
function validateForm(form) {
	if(!form.user_id.value) {
		alert("아이디를 입력하세요.");
		return false;
	}
	if(form.user_pw.value == "") {
		alert("패스워드를 입력하세요.");
		return false;
	}
}


</script>


<form action="MemberLoginAction.lo" method="post" name="loginForm"
onsubmit="return validateForm(this);">
아이디 : <input type="text" name="user_id" class="form-control" style="width: 150px; margin: auto;"><br>
패스워드 : <input type="password" name="user_pw" class="form-control" style="width: 150px; margin: auto;"><br>
<input type="submit" value="Login" class="btn btn-success">
</form>

<div><a href="./MemberJoinView.lo">회원가입</a></div>
<% } else { %>
	<%= session.getAttribute("User_id") %>회원님, 로그인하셨습니다.<br> 메인페이지로 돌아갑니다.<br>
	<script>
    	// 페이지 이동 함수
    	function redirect() {
    		window.location.href = "main.lo"; // 메인 페이지로 이동
    	}

    	// 5초 후에 메인 페이지로 이동
   			setTimeout(redirect, 3000); // 5000밀리초 = 5초
    </script>
<% }
%>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</center>
</html>