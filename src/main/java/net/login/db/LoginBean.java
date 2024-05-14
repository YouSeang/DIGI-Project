package net.login.db;

import java.sql.Date;

// 데이터 전달을 위한 DTO 클래스; 일반적으로 테이블당 하나씩 생성하며, 테이블의 칼럼과 동일한 멤버 변수를 갖는다.
public class LoginBean {
	// 멤버 변수 선언
	private String user_id;
	private String user_name;
	private String user_pw;
	private String user_email;
	//private String birth;
	//private String favorite;
	private String info;
	//private boolean isadmin;
	
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_pw() {
		return user_pw;
	}
	public void setUser_pw(String user_pw) {
		this.user_pw = user_pw;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
//	public String getBirth() {
//		return birth;
//	}
//	public void setBirth(String birth) {
//		this.birth = birth;
//	}
//	public String getFavorite() {
//		return favorite;
//	}
//	public void setFavorite(String favorite) {
//		this.favorite = favorite;
//	}
	public String getInfo() {
		return info;
	}
	public void setInfo(String info) {
		this.info = info;
	}
//	public boolean isIsadmin() {
//		return isadmin;
//	}
//	public void setIsadmin(boolean isadmin) {
//		this.isadmin = isadmin;
//	}
}