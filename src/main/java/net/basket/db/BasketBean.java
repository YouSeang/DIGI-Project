package net.basket.db;

import java.sql.Date;

// ������ ������ ���� DTO Ŭ����; �Ϲ������� ���̺�� �ϳ��� �����ϸ�, ���̺��� Į���� ������ ��� ������ ���´�.
public class BasketBean {
	// ��� ���� ����
	private int basket_id;
	private String user_id;
	private String quantity_product1;
	private String quantity_product2;
	private String quantity_product3;
	//private String cookie_name;
	//private String cookie_img;
	//private int cookie_qnt;
	//private int cookie_price;
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQuantity_product1() {
		return quantity_product1;
	}
	public void setQuantity_product1(String quantity_product1) {
		this.quantity_product1 = quantity_product1;
	}
	public String getQuantity_product2() {
		return quantity_product2;
	}
	public void setQuantity_product2(String quantity_product2) {
		this.quantity_product2 = quantity_product2;
	}
	public String getQuantity_product3() {
		return quantity_product3;
	}
	public void setQuantity_product3(String quantity_product3) {
		this.quantity_product3 = quantity_product3;
	}
	
	
	
	
}