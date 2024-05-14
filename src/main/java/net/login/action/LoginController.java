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
		 //http://localhost:8080/Model2-Board/Login.lo�� ������
		 //�����(./Login.lo)�� �������� ����
		 //�����δ� ������ �ٸ� ����� ���������� �����ġ�� ���� �ʱ� ������ ������ �߻�; �ǹ������� ����θ� �� ���� �����.
		 String contextPath=request.getContextPath(); 
		 //http://localhost:8080/Model2-Board ������
		 String command=RequestURI.substring(contextPath.length());
		 //��ü ��ο��� /Login.lo�� ����
		 ActionForward forward=null;
		 //Ŭ���̾�Ʈ ��û�� ó�� �Ŀ� �̵��� ���; �ʱ⿡�� �̵� ��θ� 'null'�� ����
		 //forward�� ����, sendRedirect ������ �������ִ� class
		 Action action=null;
		 //Ŭ���̾�Ʈ ��û�� ó���ϴ� �� ����
		 //Interface�� �߻�޼ҵ带 ����� ����, ���� �޼ҵ带 �������̵�(�������ε�)                 
		   
		   if(command.equals("/Login.lo")){ //DB�� ������ ���� ���� ���;
			   forward=new ActionForward(); //forward ��ü ����
			   forward.setRedirect(false); //Redirect 'false'������ ������������� ������ �̵�
			   forward.setPath("./login/loginForm.jsp");
			   /* �۾��� �������� �̵� -> DB�� ���پ��� ó����
				* "Login.lo"�� ��û�ϸ� 'ActionForward' ��ü�� �����ǰ�, 
				* 'setRedirect(false)'�� �����̷�Ʈ ����� �ƴ� ������ ������� ������ �̵�
				*/
		   	} else if (command.equals("/main.lo")){ //DB�� �����ؼ� �Խñ��� ���� �ҷ���
//		   		HttpSession session = request.getSession();
//		   		if (session.getAttribute("User_id") == null) {
//		   	        // �α��ε��� ���� ���, �α��� �������� �����̷�Ʈ
//		   	        response.sendRedirect("Login.lo");
//		   	        return; // �����̷�Ʈ �� �߰� �ڵ� ������ ���� ���� return ���
//		   	    }
		   		// �α��ε� ���, main.jsp�� ������
		   		forward = new ActionForward();
		   		forward.setRedirect(false);
		   		forward.setPath("./login/main.jsp");
		   	} 
		   // ȸ������ ��
			else if(command.equals("/MemberJoinView.lo")) {
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("./login/joinForm.jsp");
			} 
			
			else if(command.equals("/MemberLoginAction.lo")){ //DB�� �����ؼ� �Խñ��� ���� �ҷ���
				action  = new MemberLoginAction();
				try {
					forward=action.execute(request, response );
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		   
		   	// ȸ������ ��ư
			else if(command.equals("/MemberJoinAction.lo")) {
				action = new MemberJoinAction();
				try {
					forward = action.execute(request, response);
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		   
		   // Redirect�� ������ forward�� ������ ����
		   if(forward.isRedirect()){ 
			   /* Redirect�� url �ٲ��, ������ ������ ����.
			    * �����̷�Ʈ �� �������δ� ���ο� ��û�� ���� �������� �ش� �������� ������ ��ȯ��.
			    * ���� ���� ��û������ �����ͳ� ���� ������ �������� �ʰ�, �����Ϸ��� ���� �Ű������� ������ Ȱ���Ͽ� �����͸� �����ؾ���
			    */
			   response.sendRedirect(forward.getPath());
		   }else{
			   RequestDispatcher dispatcher=
				   request.getRequestDispatcher(forward.getPath()); 
			   	//request.getRequestDispatcher(�������� ���� ���); request�� �ٸ� �������� �Ѱ��ִ� ��� ����
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