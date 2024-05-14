<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("UTF-8"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<title>회원가입 유효성 검사 정규식</title>
<script type="text/javascript" src="./js/script.js" charset="utf-8"></script>
<script
	src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<link rel="stylesheet" href="./bootst/css/bootstrap.css">
</head>

<body>

	<form id="sm" name="pej" method="post" onsubmit="return allCheck()" action="MemberJoinAction.lo">
	
		<div class="row">
			<div class="col-sm-6">
				<table class="table table-bordered table-dark">

					<tr bgcolor="blanchedalmond">
						<!-- 첫줄 -->
						<td align="center" colspan="2" style="color: black">회원 기본 정보</td>
					</tr>

					<tr>
						<!-- 2 -->
						<td>아이디:</td>
						<td><input type="text" id="_id" name="user_id" size=12
							maxlength=12> 4~12자의 영문 대소문자와 숫자로만 입력</td>

					</tr>

					<tr>
						<!-- 3 -->
						<td>비밀번호:</td>
						<td><input type="password" id="_pw" name="user_pw" size=12
							maxlength=12> 4~12자의 영문 대소문자와 숫자로만 입력</td>
					</tr>

					<tr>
						<!-- 4 행-->
						<td>비밀번호확인:</td>
						<td><input type="password" id="_pw2" size=12 maxlength=12></td>
					</tr>

					<tr>
						<!-- 5 -->
						<td>메일주소:</td>
						<td><input type="text" id="_mail" name="user_email" size=15>
							예) id@domain.com</td>
					</tr>

					<tr>
						<!-- 6 -->
						<td>이름:</td>
						<td><input type="text" id="_name" name="user_name" size=15
							maxlength=4></td>
					</tr>

					<tr bgcolor="blanchedalmond">
						<!-- 첫줄 -->
						<td align="center" colspan="2" style="color: black">개인 신상 정보</td>
					</tr>

					<tr>
						<!-- 9 -->
						<td>자기소개:</td>
						<td><textarea id="my_intro" name="info" cols="55" rows="5"
								maxlength="700"></textarea></td>
					</tr>

					<tr>
						<td colspan="2" align="center"><input type="submit"
							value="회원 가입"> <input type="reset" value="다시입력">
						</td>
					</tr>
				</table>
			</div>
		</div>
	</form>
	
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" src="bootst/js/bootstrap.js"></script>
	
</body>
</html>