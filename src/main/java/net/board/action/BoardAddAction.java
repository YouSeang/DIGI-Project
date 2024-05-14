package net.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import net.board.db.BoardDAO;
import net.board.db.BoardBean;

public class BoardAddAction implements Action {
	 public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		BoardDAO boarddao=new BoardDAO(); //DB에 접근하기 위한 DAO객체 생성
	   	BoardBean boarddata=new BoardBean(); //request로 객체를 전달받기 위해 Bean 객체 생성
	   	ActionForward forward=new ActionForward();
	   	
		String realFolder="";
   		String saveFolder="boardupload"; //경로에 boardupload 폴더 있음
   		
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
	   		//boardInsert가 BoardDAO에서 boolean 타입으로 값을 넣고, true를 반환한다.
	   		
	   		if(result==false){
	   			System.out.println("게시판 등록 실패");
	   			return null;
	   		}
	   		System.out.println("게시판 등록 완료"); //result가 true값으로 반환되면 게시판 등록 완료
	   		
	   		forward.setRedirect(true); 
	   		//게시글 등록하면 url이 BoardList로 나와야 하니까 포워딩이 아니라 리다이렉트로 처리함
	   		//여기서 forward 방식으로 바꾸면 url이 바뀌지 않기때문에 null값 발생
	   		//값은 BoardList.bo로 연결되어야 나옴
	   		forward.setPath("./BoardList.bo");
	   		return forward;
	   		
  		}catch(Exception ex){
   			ex.printStackTrace();
   		}
  		return null;
	}  	
}