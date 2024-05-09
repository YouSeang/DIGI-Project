package net.board.action;

/* 페이지 이동 및 리다이렉트를 처리하기 위한 class
 * Servlet이나 Controller에서 처리된 결과를 클라이언트에게 알려주는 데 사용됨
 */
public class ActionForward {
	private boolean isRedirect=false; //Redirect를 사용하지 않겠다는것
	//페이지 이동 방식을 결정 'true'면 Redirect를 사용 'false'면 포워딩을 사용
	private String path=null;
	//이동할 페이지의 경로를 저장
	
	// Redirect 여부를 반환하는 메소드
	public boolean isRedirect() {
		return isRedirect;
	}
	
	// Redirect 여부를 설정하는 메소드 true, false에 따라 Redirect일지 포워딩일지 결정
	public void setRedirect(boolean isRedirect) {
		this.isRedirect = isRedirect;
	}
	
	// 이동할 페이지의 경로를 반환하는 메서드
	public String getPath() {
		return path;
	}
	
	// 이동할 페이지의 경로를 설정하는 메서드
	public void setPath(String path) {
		this.path = path;
	}
	
	
}