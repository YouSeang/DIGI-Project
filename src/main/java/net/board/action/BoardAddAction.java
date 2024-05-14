package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoardDAO boarddao=new BoardDAO(); //DB�� �����ϱ� ���� DAO��ü ����
	   	BoardBean boarddata=new BoardBean(); //request�� ��ü�� ���޹ޱ� ���� Bean ��ü ����
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";
   		String saveFolder="boardupload"; //��ο� boardupload ���� ����
   		
   		int fileSize=5*1024*1024;
   		
   		realFolder=request.getRealPath(saveFolder);
   		
   		boolean result=false;
   		
   		try{
   			
   			MultipartRequest multi=null;
   			
   			multi=new MultipartRequest(request,
   					realFolder,
   					fileSize,
   					"euc-kr",
   					new DefaultFileRenamePolicy());
   			
   			boarddata.setBOARD_NAME(multi.getParameter("BOARD_NAME"));
   			boarddata.setBOARD_PASS(multi.getParameter("BOARD_PASS"));
	   		boarddata.setBOARD_SUBJECT(multi.getParameter("BOARD_SUBJECT"));
	   		boarddata.setBOARD_CONTENT(multi.getParameter("BOARD_CONTENT"));
	   		boarddata.setBOARD_FILE(
	   				multi.getFilesystemName((String)multi.getFileNames().nextElement()));
	   		
	   		result=boarddao.boardInsert(boarddata); 
	   		//boardInsert�� BoardDAO���� boolean Ÿ������ ���� �ְ�, true�� ��ȯ�Ѵ�.
	   		
	   		if(result==false){
	   			System.out.println("�Խ��� ��� ����");
	   			return null;
	   		}
	   		System.out.println("�Խ��� ��� �Ϸ�"); //result�� true������ ��ȯ�Ǹ� �Խ��� ��� �Ϸ�
	   		
	   		forward.setRedirect(true); 
	   		//�Խñ� ����ϸ� url�� BoardList�� ���;� �ϴϱ� �������� �ƴ϶� �����̷�Ʈ�� ó����
	   		//���⼭ forward ������� �ٲٸ� url�� �ٲ��� �ʱ⶧���� null�� �߻�
	   		//���� BoardList.bo�� ����Ǿ�� ����
	   		forward.setPath("./BoardList.bo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}