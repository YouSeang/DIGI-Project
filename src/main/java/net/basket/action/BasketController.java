package net.basket.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class BasketController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 String RequestURI=request.getRequestURI(); 
		 //http://localhost:8080/MVCproject/BoardList.bo를 가져옴
		 //상대경로(./BoardList.bo)와 절대경로의 차이
		 //절대경로는 서버가 다른 사람이 실행했을때 경로위치가 맞지 않기 때문에 에러가 발생; 실무적으로 상대경로를 더 많이 사용함.
		 String contextPath=request.getContextPath(); 
		 //http://localhost:8080/MVCproject 가져옴
		 String command=RequestURI.substring(contextPath.length());
		 //전체 경로에서 /BoardList.bo만 남김
		 ActionForward forward=null;
		 //클라이언트 요청의 처리 후에 이동할 경로; 초기에는 이동 경로를 'null'로 저장
		 //forward로 갈지, sendRedirect 보낼지 결정해주는 class
		 Action action=null;
		 //클라이언트 요청을 처리하는 데 사용됨
		 //Interface에 추상메소드를 만들어 놓고, 같은 메소드를 오버라이딩(동적바인딩)                 
		   
		   if(command.equals("/CookieList.ba")){ //DB를 가지고 있지 않은 경우;
			   forward=new ActionForward(); //forward 객체 생성
			   forward.setRedirect(false); //Redirect 'false'값으로 포워딩방식으로 페이지 이동
			   forward.setPath("./basket/cookieList.jsp");
			   /* 글쓰기 페이지로 이동 -> DB에 접근없이 처리됨
				* "BoardWrite.bo"를 요청하면 'ActionForward' 객체가 생성되고, 
				* 'setRedirect(false)'로 리다이렉트 방식이 아닌 포워딩 방식으로 페이지 이동
				*/
		   
			   //쿠키 장바구니 담기
		   } else if (command.equals("/BasketAddAction.ba")) {
			   action = new BasketAddAction();
			   try {
				   forward = action.execute(request, response);
			   } catch (Exception e) {
				   e.printStackTrace();
			   } 
			   //cookieCart.jsp로 이동
		   } else if (command.equals("/BasketViewAction.ba")) {
			   action = new BasketViewAction();
			   try {
				   forward = action.execute(request, response);
			   } catch (Exception e) {
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