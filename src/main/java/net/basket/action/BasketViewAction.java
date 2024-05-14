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
            // �α��ε��� ���� ��� ó��
            // ���� ��� �α��� �������� �����̷�Ʈ�ϰų� �޽����� ǥ���ϴ� ���� ó���� �� �� �ֽ��ϴ�.
            // ���⿡���� �ӽ÷� �α��� �������� �����̷�Ʈ�ϴ� �ڵ带 �ۼ��ϰڽ��ϴ�.
            ActionForward forward = new ActionForward();
            forward.setRedirect(true);
            forward.setPath("./login/login.jsp");
            return forward;
        } else {
            // �α��ε� ���, �ش� ������� ��ٱ��� ������ ��������

            BasketBean basketData = new BasketBean();
            BasketDAO basketdao = new BasketDAO();
            ActionForward forward = new ActionForward(); // actionforward�� ��ü�� �����ϸ� ���� ������ ��밡����.
            basketData.setUser_id(userId); // ����� ���̵� ����
            
            request.setCharacterEncoding("UTF-8");
            
            basketData = basketdao.getBasketData(basketData); // �ùٸ� �Ű����� ����
            
            // �α׷� ����Ͽ� Ȯ��
            System.out.println("���� ��ٱ��� ������: " + basketData);
            
            // ������ ��ٱ��� ������ request�� ����
            request.setAttribute("basketData", basketData);
            
            
            // �� ��ǰ�� ���� ������ �����ͼ� request�� ����
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
