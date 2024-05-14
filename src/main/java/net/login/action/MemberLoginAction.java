package net.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.login.action.Action;
import net.login.action.ActionForward;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class MemberLoginAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		 HttpSession session = request.getSession();
		 
		 LoginDAO logindao=new LoginDAO(); //DB에 접근하기 위한 DAO객체 생성
		 LoginBean logindata=new LoginBean();
		 ActionForward forward=new ActionForward();
		 
		 request.setCharacterEncoding("UTF-8");
		 
		 boolean result = false;
		 try {
			 //main 로그인 폼에서 가져올 id와 pw 파라미터를 빈즈 클래스에 넣는다.
			 logindata.setUser_id(request.getParameter("user_id"));
			 logindata.setUser_pw(request.getParameter("user_pw"));
			 
			 result = logindao.memberLogin(logindata);
			 
			 if(result == false) {
				 System.out.println("Action : 로그인 실패");
				 forward.setRedirect(false);
				 forward.setPath("./login/index.jsp");
				 return forward;
			 }
			 System.out.println("Action : 로그인 성공");
			 session.setAttribute("User_id", logindata.getUser_id());
			 String user = (String)session.getAttribute("User_id");
			 
			// admin인지 일반 사용자인지 확인 게시판으로 이동...
	 		forward.setRedirect(true);
			forward.setPath("./main.lo");
			return forward;
			
		 } catch(Exception e) {
				System.out.println("데이터 등록 실패");
				e.printStackTrace();
		 }
		 return null;
	 }
}
