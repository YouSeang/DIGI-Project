package net.login.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.login.db.LoginBean;

public class LoginDAO {
	Connection con; //데이터베이스와 연결을 담당
	PreparedStatement pstmt; //인파라미터가 있는 동적 쿼리문을 실행할 때 사용
	ResultSet rs; //SELECT 쿼리문의 결과를 저장할 떄 사용

	DataSource ds;
	public LoginDAO() {
		try {
			// 커넥션 풀(DataSource) 얻기
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			// 커넥션 풀을 통해 연결 얻기
			con = ds.getConnection();
			
		} catch (Exception ex) {
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
	}
	
	// 로그인
	public boolean memberLogin(LoginBean member) {
		String sql = "SELECT user_id, user_pw FROM user where user_id ='" + member.getUser_id() + "'";
//		member.getId();
//		member.getPw();
		
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString(2).equals(member.getUser_pw())) {
					return true;
				}
			}
			return false;
		}catch(Exception e) {
			System.out.println("DAO : memberLogin 에러 : " + e);
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con != null) try {con.close();}catch(SQLException ex) {}
		}
		return false;
	}
	
	// 회원가입 회원 등록
	public boolean memberInsert(LoginBean member) {
		String sql = ""; 
		int result=0;

		try {
		
			con = ds.getConnection(); // sql문을 조회할때 쓰는 함수

			// 컬럼은 12개
			sql = "INSERT INTO user VALUES(?,?,?,?,?)";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, member.getUser_id());
			pstmt.setString(2, member.getUser_name());
			pstmt.setString(3, member.getUser_pw());
			pstmt.setString(4, member.getUser_email());
			pstmt.setString(5, member.getInfo());

			result = pstmt.executeUpdate();
			if(result==0) { 
				return false;
			}
			return true;
			
		}catch(Exception e) {
			System.out.println("DAO : memberInsert 에러 : " + e);
			e.printStackTrace();
			
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException ex) {}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con != null) try {con.close();}catch(SQLException ex) {}
		}
		
		return false;
	}
	
	

}