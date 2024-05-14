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
		
		LoginBean logindata = new LoginBean(); // DAO �������� ���� ��ü ���� 
		LoginDAO logindao = new LoginDAO(); // ���� Ŭ������ �����͸� ������ ��ü ����
		ActionForward forward = new ActionForward(); // actionforward�� ��ü�� �����ϸ� ���� ������ ��밡����.
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");
	
	// ���� Ŭ������ ������ ����
		try {
			logindata.setUser_id(request.getParameter("user_id"));
			logindata.setUser_name(request.getParameter("user_name"));
			logindata.setUser_pw(request.getParameter("user_pw"));
			logindata.setUser_email(request.getParameter("user_email"));
			logindata.setInfo(request.getParameter("info"));
		
				
			// ��� DAO�� Insert �Լ��� memberdata�� ���� �־�� (beans Ŭ����)
			result = logindao.memberInsert(logindata);
				
			if(result == false) {
				System.out.println("ȸ����� ����");
				return null;
			}
			System.out.println("ȸ�� ��� �Ϸ� (�׼� Ŭ������)");
				
			forward.setRedirect(true);
			forward.setPath("./main.lo");
			return forward;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return forward;
		
	}
}
