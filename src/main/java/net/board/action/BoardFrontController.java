package net.board.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

 public class BoardFrontController 
 	extends javax.servlet.http.HttpServlet 
 	implements javax.servlet.Servlet {
	 protected void doProcess(HttpServletRequest request, HttpServletResponse response) 
	 	throws ServletException, IOException {
		 String RequestURI=request.getRequestURI(); 
		 //http://localhost:8080/MVCproject/BoardList.bo�� ������
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
		   
		   if(command.equals("/BoardWrite.bo")){ //DB�� ������ ���� ���� ���;
			   forward=new ActionForward(); //forward ��ü ����
			   forward.setRedirect(false); //Redirect 'false'������ ������������� ������ �̵�
			   forward.setPath("./board/qna_board_write.jsp");
			   /* �۾��� �������� �̵� -> DB�� ���پ��� ó����
				* "BoardWrite.bo"�� ��û�ϸ� 'ActionForward' ��ü�� �����ǰ�, 
				* 'setRedirect(false)'�� �����̷�Ʈ ����� �ƴ� ������ ������� ������ �̵�
				*/
		   } else if(command.equals("/BoardAddAction.bo")){ //DB�� �����ؼ� �Խñ��� ���� �ҷ���
			   action  = new BoardAddAction();
			   try {
				   forward=action.execute(request, response );
			   } catch (Exception e) {
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardList.bo")){ //DB�� �����ؼ� �Խñ��� ���� �ҷ���
			   action = new BoardListAction(); //BoardListAction�� �����͸� �޾ƿ��� ���� action ��ü ����?
			   try{
				   forward=action.execute(request, response); 
				   /* �������̵� �Ǵ� �κ�?? BoardListAction�� �����͸� �������̵��ؼ� forward��ü�� ����?
				    * BoardListAction�� 'execute'�޼��带 ȣ�� �� �� �� ����� forward ������ ����
				    */
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardReplyAction.bo")){
			   action = new BoardReplyView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardDelete.bo")){
			   forward=new ActionForward();
			   forward.setRedirect(false);
			   forward.setPath("./board/qna_board_delete.jsp");
		   } else if(command.equals("/BoardModify.bo")){
			   action = new BoardModifyView();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardReplyView.bo")){
			   action = new BoardReplyAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardModifyAction.bo")){
			   action = new BoardModifyAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardDeleteAction.bo")){
			   action = new BoardDeleteAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
				   e.printStackTrace();
			   }
		   } else if(command.equals("/BoardDetailAction.bo")){
			   action = new BoardDetailAction();
			   try{
				   forward=action.execute(request, response);
			   }catch(Exception e){
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