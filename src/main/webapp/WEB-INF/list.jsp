<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.PersonVo" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>

<% 
	List<PersonVo> personList = (List<PersonVo>)request.getAttribute("pList");
	System.out.println("여기는 jsp");
	System.out.println(personList);
%>




<!DOCTYPE html>
<html lang="ko">

<head>
<meta charset="UTF-8">
<title>배아파</title>
</head>


<body>

	<h1>전화번호부</h1>

	<h2>전화번호-리스트</h2>

	<p>등록된 전화번호 리스트 입니다</p>


	<% for ( int i = 0 ; i < personList.size() ; i++ ) {%>
	<table border="1">

		<tbody name="no" value="<%=personList.get(i).getPersonId()%>">

			<tr>
				<td>이름(name)</td>
				<td><%=personList.get(i).getName()%></td>
			</tr>
			<tr>
				<td>핸드폰(hp)</td>
				<td><%=personList.get(i).getHp()%></td>
			</tr>
			<tr>
				<td>회사(company)</td>
				<td><%=personList.get(i).getCompany()%></td>
			</tr>
			
			<tr>
				<!-- 
				<td>
					<a href="http://localhost:8080/pb2/pbc?action=editform&no=<%=personList.get(i).getPersonId()%>">
					[수정폼으로 이동]</a>
				</td>
				<td>[삭제]</td>
				-->
				<td><a href="http://localhost:8080/pb2/pbc?action=editform&no=<%=personList.get(i).getPersonId()%>">[수정폼으로 이동]<a></td>
				<td><a href="http://localhost:8080/pb2/pbc?action=delete&no=<%=personList.get(i).getPersonId()%>">[삭제]</a></td>
			</tr>

		</tbody>

	</table>
	<br>
	<%} %>
	
	<br>
	<a href="#">등록폼으로 이동</a>
	<br>
	<br>



</body>

</html>