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
		
		BasketBean Basketdata = new BasketBean(); // DAO �������� ���� ��ü ���� 
		BasketDAO Basketdao = new BasketDAO(); // ���� Ŭ������ �����͸� ������ ��ü ����
		ActionForward forward = new ActionForward(); // actionforward�� ��ü�� �����ϸ� ���� ������ ��밡����.
		boolean result = false;
		
		request.setCharacterEncoding("UTF-8");

		//���� �� ���޵ǰ� �ִ��� Ȯ���ϴ� ���
		System.out.println((String)session.getAttribute("User_id")); //session�� �� ����ִ��� Ȯ���ϴ¹��
		System.out.println(request.getParameter("quantity_product1"));
		System.out.println(request.getParameter("quantity_product2"));
		System.out.println(request.getParameter("quantity_product3")); 
		// ���� Ŭ������ ������ ����
		try {
			Basketdata.setUser_id((String)session.getAttribute("User_id")); //������ ���� ����ȯ�� �ؾ��Ѵ�.
			Basketdata.setQuantity_product1(request.getParameter("quantity_product1"));
			Basketdata.setQuantity_product2(request.getParameter("quantity_product2"));
			Basketdata.setQuantity_product3(request.getParameter("quantity_product3"));
				
						
			// ��� DAO�� Insert �Լ��� memberdata�� ���� �־�� (beans Ŭ����)
			result = Basketdao.cookieInsert(Basketdata);
						
			if(result == false) {
				System.out.println("������� ����");
				forward.setRedirect(false);
				forward.setPath("/BasketViewAction.ba"); // ���� �������� �̵��ϵ��� ��� ����
				return forward;
			}
			System.out.println("���� ��� �Ϸ� (�׼� Ŭ������)");
						
			forward.setRedirect(false);
			forward.setPath("/BasketViewAction.ba");
			return forward;
			}catch(Exception e) {
				e.printStackTrace();
			}
			return forward;
	}

}
