<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form action = "./delete" method = "get">
	<label for = "password">비밀번호</label>
	<input type = "password" name = "password" value = ""></input>
	<input type = "hidden" name = "no" value = "${param.no}"></input>
	<button type = "submit">확인</button>
</form>

<a href = "./addList">메인으로 돌아가기</a>

</body>
</html>