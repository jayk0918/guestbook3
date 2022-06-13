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

<form action = "./add" method = "get">
	<table border = '1'>
	
	<tr>
		<td>이름</td>
		<td><input type = "text" name = "name" value = ""></input></td>
		<td>비밀번호</td>
		<td><input type = "password" name = "password" value = ""></input></td>
	</tr>
	
	<tr>
		<td><input type = "text" name = "content" value = ""></input></td>
	</tr>
	
	<tr>
		<td><button type = "submit">확인</button></td>
	</tr>
	
	</table>
	
</form>

<c:forEach items = "${gList}" var = "GuestBookVo">
<table border = '1'>
	<tr>
		<td>${GuestBookVo.no}</td>
		<td>${GuestBookVo.name}</td>
		<td>${GuestBookVo.date}</td>
		<td><a href = "./deleteForm?no=${GuestBookVo.no}">삭제</a></td>
	</tr>
	
	<tr>
		<td>${GuestBookVo.content}</td>
	</tr>

</table>

</c:forEach>
</body>
</html>