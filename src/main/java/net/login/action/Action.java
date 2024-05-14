package net.login.action;

import javax.servlet.http.*;

/* Action 인터페이스를 구현한 클래스는 컨트롤러의 역할을 수행하며, 클라이언트의 요청을 적절히 처리하여 뷰에 전달
 * 해당 클래스를 구현하는 여러 가지 Action 클래스를 만들 수 있음. 이렇게 하면 각 요청에 따라 다른 작업을 수행하는 여러 동작을 만들 수 있는 유연성과 확장성의 증가
 * 예를 들어, 게시글 추가, 수정, 삭제 등 다양한 동작을 처리하는 Action 클래스를 따로 만들어 관리할 수 있음
 */
public interface Action {
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response)
			throws Exception;
}
