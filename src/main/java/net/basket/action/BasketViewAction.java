package net.basket.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.basket.action.Action;
import net.basket.action.ActionForward;
import net.basket.db.BasketBean;
import net.basket.db.BasketDAO;

public class BasketViewAction implements Action{
	public ActionForward execute(HttpServletRequest request,HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		String userId = (String) request.getSession().getAttribute("User_id");
        
        if (userId == null) {
            // 로그인되지 않은 경우 처리
            // 예를 들어 로그인 페이지로 리다이렉트하거나 메시지를 표시하는 등의 처리를 할 수 있습니다.
            // 여기에서는 임시로 로그인 페이지로 리다이렉트하는 코드를 작성하겠습니다.
            ActionForward forward = new ActionForward();
            forward.setRedirect(true);
            forward.setPath("./login/login.jsp");
            return forward;
        } else {
            // 로그인된 경우, 해당 사용자의 장바구니 정보를 가져오기

            BasketBean basketData = new BasketBean();
            BasketDAO basketdao = new BasketDAO();
            ActionForward forward = new ActionForward(); // actionforward의 객체를 생성하면 안의 변수를 사용가능함.
            basketData.setUser_id(userId); // 사용자 아이디 설정
            
            request.setCharacterEncoding("UTF-8");
            
            basketData = basketdao.getBasketData(basketData); // 올바른 매개변수 전달
            
            // 로그로 출력하여 확인
            System.out.println("받은 장바구니 데이터: " + basketData);
            
            // 가져온 장바구니 정보를 request에 저장
            request.setAttribute("basketData", basketData);
            
            
            // 각 제품의 수량 정보를 가져와서 request에 저장
            if (basketData != null) {
                request.setAttribute("product1Quantity", basketData.getQuantity_product1());
                request.setAttribute("product2Quantity", basketData.getQuantity_product2());
                request.setAttribute("product3Quantity", basketData.getQuantity_product3());
            }
            
      
            
            forward.setRedirect(false);
            forward.setPath("./basket/cookieCart.jsp");
            return forward;
        }
	}	
}
