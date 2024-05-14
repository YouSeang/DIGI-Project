package net.basket.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BasketDAO {
	Connection con; //데이터베이스와 연결을 담당
	PreparedStatement pstmt; //인파라미터가 있는 동적 쿼리문을 실행할 때 사용
	ResultSet rs; //SELECT 쿼리문의 결과를 저장할 떄 사용

	DataSource ds;
	public BasketDAO() {
		try {
			// 커넥션 풀(DataSource) 얻기
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			// 커넥션 풀을 통해 연결 얻기
			//con = ds.getConnection();
			
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	public boolean cookieInsert(BasketBean member) {
	    String sql = "";
	    int result = 0;

	    try {
	        con = ds.getConnection();

	        // 해당 사용자가 이미 주문한 상품이 있는지 확인
	        String checkSql = "SELECT * FROM basket WHERE user_id = ?";
	        pstmt = con.prepareStatement(checkSql);
	        pstmt.setString(1, member.getUser_id());
	        ResultSet checkRs = pstmt.executeQuery();

	        if (checkRs.next()) {
	            // 이미 주문한 상품이 있는 경우, 기존 주문 내역을 삭제하고 새로운 데이터 입력
	            String deleteSql = "DELETE FROM basket WHERE user_id = ?";
	            pstmt = con.prepareStatement(deleteSql);
	            pstmt.setString(1, member.getUser_id());
	            pstmt.executeUpdate();
	        }

	        // 새로운 데이터 입력
	        sql = "INSERT INTO basket (user_id, quantity_product1, quantity_product2, quantity_product3) VALUES(?,?,?,?)";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, member.getUser_id());
	        pstmt.setString(2, member.getQuantity_product1());
	        pstmt.setString(3, member.getQuantity_product2());
	        pstmt.setString(4, member.getQuantity_product3());

	        result = pstmt.executeUpdate();
	        if(result > 0) { 
	            return true;
	        }
	    } catch(Exception e) {
	        System.out.println("DAO : cookieInsert 에러 : " + e);
	        e.printStackTrace();

	    } finally {
	        try {
	            if (rs != null) rs.close();
	            if (pstmt != null) pstmt.close();
	            if (con != null) con.close();
	        } catch (SQLException ex) {
	            ex.printStackTrace();
	        }
	    }

	    return false;
	}
	
    // 사용자의 아이디를 기반으로 장바구니 정보를 가져오는 메소드
	public BasketBean getBasketData(BasketBean basketData2) {
	    Connection con = null;
	    PreparedStatement pstmt = null;
	    ResultSet rs = null;
	    BasketBean basketData = null;

	    try {
	        con = ds.getConnection();

	        String sql = "SELECT * FROM basket WHERE user_id = ?";
	        pstmt = con.prepareStatement(sql);
	        pstmt.setString(1, basketData2.getUser_id()); // 바인딩할 값으로 사용자 아이디를 가져옴
	        rs = pstmt.executeQuery();

	        if (rs.next()) {
	            // 장바구니 정보가 있는 경우, 해당 정보를 BasketBean 객체에 저장
	            basketData = new BasketBean();
	            basketData.setUser_id(rs.getString("user_id"));
	            basketData.setQuantity_product1(rs.getString("quantity_product1"));
	            basketData.setQuantity_product2(rs.getString("quantity_product2"));
	            basketData.setQuantity_product3(rs.getString("quantity_product3"));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        // 리소스 해제
	        if (rs != null) try { rs.close(); } catch (SQLException e) {}
	        if (pstmt != null) try { pstmt.close(); } catch (SQLException e) {}
	        if (con != null) try { con.close(); } catch (SQLException e) {}
	    }

	    return basketData;
	}
}