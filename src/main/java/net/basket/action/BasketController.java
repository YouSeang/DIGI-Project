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
		 //http://localhost:8080/MVCproject/BoardList.bo�� ������
		 //�����(./BoardList.bo)�� �������� ����
		 //�����δ� ������ �ٸ� ����� ���������� �����ġ�� ���� �ʱ� ������ ������ �߻�; �ǹ������� ����θ� �� ���� �����.
		 String contextPath=request.getContextPath(); 
		 //http://localhost:8080/MVCproject ������
		 String command=RequestURI.substring(contextPath.length());
		 //��ü ��ο��� /BoardList.bo�� ����
		 ActionForward forward=null;
		 //Ŭ���̾�Ʈ ��û�� ó�� �Ŀ� �̵��� ���; �ʱ⿡�� �̵� ��θ� 'null'�� ����
		 //forward�� ����, sendRedirect ������ �������ִ� class
		 Action action=null;
		 //Ŭ���̾�Ʈ ��û�� ó���ϴ� �� ����
		 //Interface�� �߻�޼ҵ带 ����� ����, ���� �޼ҵ带 �������̵�(�������ε�)                 
		   
		   if(command.equals("/CookieList.ba")){ //DB�� ������ ���� ���� ���;
			   forward=new ActionForward(); //forward ��ü ����
			   forward.setRedirect(false); //Redirect 'false'������ ������������� ������ �̵�
			   forward.setPath("./basket/cookieList.jsp");
			   /* �۾��� �������� �̵� -> DB�� ���پ��� ó����
				* "BoardWrite.bo"�� ��û�ϸ� 'ActionForward' ��ü�� �����ǰ�, 
				* 'setRedirect(false)'�� �����̷�Ʈ ����� �ƴ� ������ ������� ������ �̵�
				*/
		   
			   //��Ű ��ٱ��� ���
		   } else if (command.equals("/BasketAddAction.ba")) {
			   action = new BasketAddAction();
			   try {
				   forward = action.execute(request, response);
			   } catch (Exception e) {
				   e.printStackTrace();
			   } 
			   //cookieCart.jsp�� �̵�
		   } else if (command.equals("/BasketViewAction.ba")) {
			   action = new BasketViewAction();
			   try {
				   forward = action.execute(request, response);
			   } catch (Exception e) {
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