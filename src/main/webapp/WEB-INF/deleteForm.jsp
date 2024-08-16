<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>    
 
<% PersonVo personVo = (PersonVo)request.getAttribute("personVo"); %>
    
    
<!DOCTYPE html>

<html>


	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>


	<body>
	
		<form>
			<table>
				<tr>
					<td>비밀번호</td>
					<td><input type="password" name="password"></td>
					
					<input type="hidden" name="no" value="<%=personVo.getNo()%>">
					<input type="hidden" name="action" value="delete">
					
					<td><button type="submit">삭제</button></td>
				</tr>
			</table>
		</form>
	
		<br><br>
	
		<a href="http://localhost:8080/guestbook/gbc?action=list">메인으로 돌아가기</a>

	</body>
</html>