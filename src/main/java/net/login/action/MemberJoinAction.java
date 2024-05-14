package net.login.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.login.action.Action;
import net.login.action.ActionForward;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class MemberJoinAction implements Action {
	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		LoginBean logindata = new LoginBean(); // DAO 쿼리문을 날릴 객체 생성 
		LoginDAO logindao = new LoginDAO(); // 빈즈 클래스에 데이터를 삽입할 객체 생성
		ActionForward forward = new ActionForward(); // actionforward의 객체를 생성하면 안의 변수를 사용가능함.
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");
	
	// 빈즈 클래스에 데이터 삽입
		try {
			logindata.setUser_id(request.getParameter("user_id"));
			logindata.setUser_name(request.getParameter("user_name"));
			logindata.setUser_pw(request.getParameter("user_pw"));
			logindata.setUser_email(request.getParameter("user_email"));
			logindata.setInfo(request.getParameter("info"));
		
				
			// 멤버 DAO의 Insert 함수에 memberdata의 값을 넣어라 (beans 클래스)
			result = logindao.memberInsert(logindata);
				
			if(result == false) {
				System.out.println("회원등록 실패");
				return null;
			}
			System.out.println("회원 등록 완료 (액션 클래스임)");
				
			forward.setRedirect(true);
			forward.setPath("./main.lo");
			return forward;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
		
	}
}
