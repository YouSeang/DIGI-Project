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
		 
		 LoginDAO logindao=new LoginDAO(); //DB�� �����ϱ� ���� DAO��ü ����
		 LoginBean logindata=new LoginBean();
		 ActionForward forward=new ActionForward();
		 
		 request.setCharacterEncoding("UTF-8");
		 
		 boolean result = false;
		 try {
			 //main �α��� ������ ������ id�� pw �Ķ���͸� ���� Ŭ������ �ִ´�.
			 logindata.setUser_id(request.getParameter("user_id"));
			 logindata.setUser_pw(request.getParameter("user_pw"));
			 
			 result = logindao.memberLogin(logindata);
			 
			 if(result == false) {
				 System.out.println("Action : �α��� ����");
				 forward.setRedirect(false);
				 forward.setPath("./login/index.jsp");
				 return forward;
			 }
			 System.out.println("Action : �α��� ����");
			 session.setAttribute("User_id", logindata.getUser_id());
			 String user = (String)session.getAttribute("User_id");
			 
			// admin���� �Ϲ� ��������� Ȯ�� �Խ������� �̵�...
	 		forward.setRedirect(true);
			forward.setPath("./main.lo");
			return forward;
			
		 } catch(Exception e) {
				System.out.println("������ ��� ����");
				e.printStackTrace();
		 }
		 return null;
	 }
}
