package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;
import net.login.db.LoginBean;
import net.login.db.LoginDAO;

public class BasketAddAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		
		BasketBean Basketdata = new BasketBean(); // DAO 쿼리문을 날릴 객체 생성 
		BasketDAO Basketdao = new BasketDAO(); // 빈즈 클래스에 데이터를 삽입할 객체 생성
		ActionForward forward = new ActionForward(); // actionforward의 객체를 생성하면 안의 변수를 사용가능함.
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");

		//값이 잘 전달되고 있는지 확인하는 방법
		System.out.println((String)session.getAttribute("User_id")); //session에 잘 담겨있는지 확인하는방법
		System.out.println(request.getParameter("quantity_product1"));
		System.out.println(request.getParameter("quantity_product2"));
		System.out.println(request.getParameter("quantity_product3")); 
		// 빈즈 클래스에 데이터 삽입
		try {
			Basketdata.setUser_id((String)session.getAttribute("User_id")); //세션은 강제 형변환을 해야한다.
			Basketdata.setQuantity_product1(request.getParameter("quantity_product1"));
			Basketdata.setQuantity_product2(request.getParameter("quantity_product2"));
			Basketdata.setQuantity_product3(request.getParameter("quantity_product3"));
				
						
			// 멤버 DAO의 Insert 함수에 memberdata의 값을 넣어라 (beans 클래스)
			result = Basketdao.cookieInsert(Basketdata);
						
			if(result == false) {
				System.out.println("수량등록 실패");
				forward.setRedirect(false);
				forward.setPath("/BasketViewAction.ba"); // 실패 페이지로 이동하도록 경로 설정
				return forward;
			}
			System.out.println("수량 등록 완료 (액션 클래스임)");
						
			forward.setRedirect(false);
			forward.setPath("/BasketViewAction.ba");
			return forward;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return forward;
	}

}
