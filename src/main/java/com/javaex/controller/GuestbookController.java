package com.javaex.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.javaex.dao.GuestbookDao;
import com.javaex.vo.PersonVo;

//http://localhost:8080/guestbook/gbc
@WebServlet("/gbc") //("/GuestbookController")
public class GuestbookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	// Controller 접수받는일 "업무구분"해주는 애
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//action 뭔지 알아야됨
		
		String action = request.getParameter("action");
		System.out.println("action : " + action);
		
		
		if ( "list".equals(action) ) { //null을 피하기 위해 반대로 쓰기 문자열에 리스트를 쓴것임
			
			//접수
			System.out.println("접수 성공 : list 요청");
			
			
			//db데이터 가져오기
			GuestbookDao guestbookDao = new GuestbookDao();
			List<PersonVo> personList = guestbookDao.getPersonList();
			//System.out.println(personList);
			
			System.out.println("여기");
			
			//화면그리기 --> 포워드   html이있네?포워드 진행시켜
			//request 에 리스트 주소 넣기
			request.setAttribute("pList", personList);
			
			System.out.println("여기2");
			
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/addList.jsp");
			rd.forward(request, response);
			

		} else if ( "writeform".equals(action) ) {
			
			System.out.println("등록 시작 : writeForm 요청"); // 업무보고   
			
			//포워드
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
			rd.forward(request, response);
			
			
		} else if ( "insert".equals(action) ) {
			
			System.out.println("등록 요청 데이터 4개 저장해줘"); // 업무보고
			
			PersonVo personVo = new PersonVo( request.getParameter("name"),
											  request.getParameter("password"),
											  request.getParameter("content"),
											  request.getParameter("regDate")	);
			
			/*
			PersonVo personVo = new PersonVo();

			personVo.setName(request.getParameter("name"));
			personVo.setPassword(request.getParameter("password"));
			personVo.setContent(request.getParameter("content"));
			personVo.setRegDate(request.getParameter("regDate"));
			*/
			System.out.println("controller" + personVo);
			
			
			
			//dao를 메모리에 올린다
			//insertPerson(personVo) 사용해서 db에 저장한다
			GuestbookDao guestbookDao = new GuestbookDao();
			guestbookDao.insertPerson(personVo);
			
			//리다이렉트
			response.sendRedirect("http://localhost:8080/guestbook/gbc?action=list");
			
		
			
		} else if ( "deleteform".equals(action) ) {
			
			System.out.println("삭제 시작 : deleteForm 요청"); // 업무보고   
			
			int no = Integer.parseInt(request.getParameter("no"));
			System.out.println("삭제할 no : " + no);
			
			//getPersonOne(no) 로 1명의 데이터 주소를 가져온다
			//PersonVo personVo = guestbookDao.getPersonOne(no);
			
			//System.out.println("Con - getPersonOne : " + personVo);
			GuestbookDao guestbookDao = new GuestbookDao();
			PersonVo personVo = guestbookDao.getPersonOne(no);
			//화면+데이터 수정폼
			//리퀘스트 어트리부트 영역에 personVo 주소를 담는다
			//request.setAttribute("personVo", personVo);
			request.setAttribute("personVo", personVo);
			
			//포워드
			//컨트롤러가 포워드할떄 파라미터 값을 넣어주지 않아도 됨
			RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/deleteForm.jsp");
			rd.forward(request, response);
			
			
		} else if ( "delete".equals(action) ) {
			
			System.out.println("삭제");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String password = request.getParameter("password");
			
			//Dao를 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			//phonebookDao를 통해서 삭제delete를 시킨다
			guestbookDao.deletePerson(no, password);
			
			//리다이렉트 시킨다
			response.sendRedirect("/guestbook/gbc?action=list");
			
			
		} else {
			System.out.println("action 없음");
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
		
	}

}


/*	
} else if ( "update".equals(action) ) {
			
			System.out.println("수정");
			
			//파라미터 꺼내기
			int no = Integer.parseInt(request.getParameter("no"));
			String name = request.getParameter("name");
			String password = request.getParameter("passord");
			String content = request.getParameter("content");
			String regDate = request.getParameter("regDate");
			
			PersonVo personVo = new PersonVo(no, name, password, content, regDate);
			System.out.println(personVo);
			
			//Dao를 메모리에 올린다
			GuestbookDao guestbookDao = new GuestbookDao();
			
			//phonebookDao를 통해서 수정update를 시킨다
			guestbookDao.updatePerson(personVo);
			
			//리다이렉트 시킨다
			//response.sendRedirect("/pb2/pbc?action=editForm");
			response.sendRedirect("/guestbook/gbc?action=addList");
			
			

} else if ( "writeform".equals(action) ) {
	
	System.out.println("등록 시작 : writeForm 요청"); // 업무보고   
	
	//포워드
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/writeForm.jsp");
	rd.forward(request, response);
	
	
} else if ( "insert".equals(action) ) {
	
	System.out.println("등록 요청 데이터 4개 저장해줘"); // 업무보고
	
	PersonVo personVo = new PersonVo( request.getParameter("name"),
									  request.getParameter("password"),
									  request.getParameter("content"),
									  request.getParameter("regDate")	);
	
	/*
	PersonVo personVo = new PersonVo();

	personVo.setName(request.getParameter("name"));
	personVo.setPassword(request.getParameter("password"));
	personVo.setContent(request.getParameter("content"));
	personVo.setRegDate(request.getParameter("regDate"));
	
	System.out.println("controller" + personVo);
	
	
	
	//dao를 메모리에 올린다
	//insertPerson(personVo) 사용해서 db에 저장한다
	GuestbookDao guestbookDao = new GuestbookDao();
	guestbookDao.insertPerson(personVo);
	
	//리다이렉트
	response.sendRedirect("http://localhost:8080/guestbook/gbc?action=list");
	
	
	
} else if ( "editform".equals(action) ) {
	
	System.out.println("정보 수정 !!! 폼 !!! 시작");
	int no = Integer.parseInt(request.getParameter("no"));
	System.out.println("no : " + no);
	
	//Dao를 메모리에 올린다
	GuestbookDao guestbookDao = new GuestbookDao();
	
	//getPersonOne(no) 로 1명의 데이터 주소를 가져온다
	PersonVo personVo = guestbookDao.getPersonOne(no);
	
	System.out.println("Con - getPersonOne : " + personVo);
	
	//화면+데이터 수정폼
	//리퀘스트 어트리부트 영역에 personVo 주소를 담는다
	request.setAttribute("personVo", personVo);
	
	//포워드 editform.jsp
	RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/editForm.jsp");
	rd.forward(request, response);
	
*/	