package net.login.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.login.action.ActionForward;
import net.login.action.MemberJoinAction;

 public class LoginController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 String RequestURI=request.getRequestURI(); 
		 //http://localhost:8080/Model2-Board/Login.lo를 가져옴
		 //상대경로(./Login.lo)와 절대경로의 차이
		 //절대경로는 서버가 다른 사람이 실행했을때 경로위치가 맞지 않기 때문에 에러가 발생; 실무적으로 상대경로를 더 많이 사용함.
		 String contextPath=request.getContextPath(); 
		 //http://localhost:8080/Model2-Board 가져옴
		 String command=RequestURI.substring(contextPath.length());
		 //전체 경로에서 /Login.lo만 남김
		 ActionForward forward=null;
		 //클라이언트 요청의 처리 후에 이동할 경로; 초기에는 이동 경로를 'null'로 저장
		 //forward로 갈지, sendRedirect 보낼지 결정해주는 class
		 Action action=null;
		 //클라이언트 요청을 처리하는 데 사용됨
		 //Interface에 추상메소드를 만들어 놓고, 같은 메소드를 오버라이딩(동적바인딩)                 
		   
		   if(command.equals("/Login.lo")){ //DB를 가지고 있지 않은 경우;
			   forward=new ActionForward(); //forward 객체 생성
			   forward.setRedirect(false); //Redirect 'false'값으로 포워딩방식으로 페이지 이동
			   forward.setPath("./login/loginForm.jsp");
			   /* 글쓰기 페이지로 이동 -> DB에 접근없이 처리됨
				* "Login.lo"를 요청하면 'ActionForward' 객체가 생성되고, 
				* 'setRedirect(false)'로 리다이렉트 방식이 아닌 포워딩 방식으로 페이지 이동
				*/
		   	} else if (command.equals("/main.lo")){ //DB에 접근해서 게시글을 전부 불러옴
//		   		HttpSession session = request.getSession();
//		   		if (session.getAttribute("User_id") == null) {
//		   	        // 로그인되지 않은 경우, 로그인 페이지로 리다이렉트
//		   	        response.sendRedirect("Login.lo");
//		   	        return; // 리다이렉트 후 추가 코드 실행을 막기 위해 return 사용
//		   	    }
		   		// 로그인된 경우, main.jsp로 포워딩
		   		forward = new ActionForward();
		   		forward.setRedirect(false);
		   		forward.setPath("./login/main.jsp");
		   	} 
		   // 회원가입 폼
			else if(command.equals("/MemberJoinView.lo")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./login/joinForm.jsp");
			} 
			
			else if(command.equals("/MemberLoginAction.lo")){ //DB에 접근해서 게시글을 전부 불러옴
				action  = new MemberLoginAction();
				try {
					forward=action.execute(request, response );
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		   
		   	// 회원가입 버튼
			else if(command.equals("/MemberJoinAction.lo")) {
				action = new MemberJoinAction();
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		   
		   // Redirect로 보낼지 forward로 보낼지 결정
		   if(forward.isRedirect()){ 
			   /* Redirect는 url 바뀌고, 데이터 전달은 못함.
			    * 리다이렉트 된 페이지로는 새로운 요청에 대한 응답으로 해당 페이지의 내용이 반환함.
			    * 따라서 이전 요청에서의 데이터나 상태 정보는 보존되지 않고, 전달하려면 쿼리 매개변수나 세션을 활용하여 데이터를 전달해야함
			    */
			   response.sendRedirect(forward.getPath());
		   }else{
			   RequestDispatcher dispatcher=
				   request.getRequestDispatcher(forward.getPath()); 
			   	//request.getRequestDispatcher(포워드할 파일 경로); request를 다른 페이지로 넘겨주는 기능 수행
			   dispatcher.forward(request, response);
		   }
	 }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}  	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
		throws ServletException, IOException {
		doProcess(request,response);
	}   	  	    
}