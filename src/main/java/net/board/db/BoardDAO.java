package net.board.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {
	Connection con; //데이터베이스와 연결을 담당
	PreparedStatement pstmt; //인파라미터가 있는 동적 쿼리문을 실행할 때 사용
	ResultSet rs; //SELECT 쿼리문의 결과를 저장할 떄 사용

	DataSource ds;
	public BoardDAO() {
		try {
			// 커넥션 풀(DataSource) 얻기
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/MysqlDB");
			// 커넥션 풀을 통해 연결 얻기
			//con = ds.getConnection();
			
		} catch (Exception ex) {
			System.out.println("DB �뿰寃� �떎�뙣 : " + ex);
			return;
		}
	}
	

	// 글의 개수 구하기 (전체 글의 개수를 반환)
	public int getListCount() {
		int x = 0;

		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement("select count(*) from board");
			rs = pstmt.executeQuery();

			if (rs.next()) {
				x = rs.getInt(1); //count(*)한 값을 x에 저장
			}
		} catch (Exception ex) {
			System.out.println("getListCount �뿉�윭: " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return x;
	}

	// 게시글 목록확인
	public List getBoardList(int page, int limit) {
		String board_list_sql = "select * from "
				+ "(select row_number() over() rnum,BOARD_NUM,BOARD_NAME,BOARD_SUBJECT,"
				+ "BOARD_CONTENT,BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,"
				+ "BOARD_RE_SEQ,BOARD_READCOUNT,BOARD_DATE from "
				+ "(select * from board order by BOARD_RE_REF desc,BOARD_RE_SEQ asc) AS s)AS u "
				+ "where rnum>=? and rnum<=?"; 
		
		//List 객체로 반환
		List list = new ArrayList();

		int startrow = (page - 1) * 10 + 1; // 시작 row 번호
		int endrow = startrow + limit - 1; // 끝 row 번호
		try {
			
			con = ds.getConnection();
			
			pstmt = con.prepareStatement(board_list_sql);
			pstmt.setInt(1, startrow);
			pstmt.setInt(2, endrow);
			rs = pstmt.executeQuery();
			//executeQuery() : SELECT 쿼리문을 실행할 때 사용. SELECT는 기존 레코드를 조회하는 쿼리문. 조회한 레코드들의 집합인 ResultSet 객체를 반환

			while (rs.next()) { //ResultSet 객체에 레코드가 들어 있는지를 next() 메서드를 통해 확인. 일치하는 멤버가 존재했다면 레코드가 담겨 있을 것이고, 따라서 true 반환.
				// 쿼리 결과로 얻은 정보를 board 객체에 저장
				BoardBean board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
				list.add(board);
			}

		} catch (Exception ex) {
			System.out.println("getBoardList �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return list; // 객체 반환
	}

	// 게시글 등록
	public boolean boardInsert(BoardBean board) {
		int num = 0;
		String sql = "";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select max(board_num) from board");
			rs = pstmt.executeQuery();

			if (rs.next())
				num = rs.getInt(1) + 1; // 글이 등록되어있으면 글 번호 +1
			else
				num = 1; // 글 등록되어 있지 않으면 1번 게시글

			//쿼리문 생성
			sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql += "BOARD_CONTENT, BOARD_FILE, BOARD_RE_REF," + "BOARD_RE_LEV,BOARD_RE_SEQ,BOARD_READCOUNT,"
					+ "BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,now())"; // ?, ?, ? 나중에 대입

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, board.getBOARD_FILE());
			pstmt.setInt(7, num);
			pstmt.setInt(8, 0);
			pstmt.setInt(9, 0);
			pstmt.setInt(10, 0);

			// 쿼리 수행
			result = pstmt.executeUpdate();

		} catch (Exception ex) {
			System.out.println("boardInsert �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		if (result == 0)
			return false;

		return true;
	}

	// 게시글 수정
	public boolean boardModify(BoardBean modifyboard) throws Exception {
		String sql = "update board set BOARD_SUBJECT=?,BOARD_CONTENT=? where BOARD_NUM=?";

		int result = 0;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, modifyboard.getBOARD_SUBJECT());
			pstmt.setString(2, modifyboard.getBOARD_CONTENT());
			pstmt.setInt(3, modifyboard.getBOARD_NUM());
			result = pstmt.executeUpdate(); //executeUpdate() : INSERT, UPDATE, DELETE 쿼리문 실행 때 사용. 실행 후 영향을 받은 행의 개수가 int형으로 반환됨.
		} catch (Exception ex) {
			System.out.println("boardModify �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		if (result != 0) {
			return true;
		} else {
			return false;
		}
	}

	// 본인 글 확인(글쓴이 확인)
	public boolean isBoardWriter(int num, String pass) {
		String board_sql = "select * from board where BOARD_NUM=?";
		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			rs.next();

			if (pass.equals(rs.getString("BOARD_PASS"))) {
				result = 1;
			}
		} catch (SQLException ex) {
			System.out.println("isBoardWriter �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		if (result != 0) {
			return true;
		} else {
			return false;
		}

	}

	// 게시글 삭제
	public boolean boardDelete(int num) {

		String board_delete_sql = "delete from board where BOARD_num=?";

		int result = 0;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_delete_sql);
			pstmt.setInt(1, num);
			result = pstmt.executeUpdate();
			if (result == 0)
				return false;

			return true;
		} catch (Exception ex) {
			System.out.println("boardDelete �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}

		return false;
	}

	// 조회 수 업데이트 (게시글 내용 확인할 떄 호출)
	public void setReadCountUpdate(int num) throws Exception {
		String sql = "update board set BOARD_READCOUNT = " + "BOARD_READCOUNT+1 where BOARD_NUM = " + num;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("setReadCountUpdate �뿉�윭 : " + ex);
		}
		finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
	}

	public BoardBean getDetail(int num) throws Exception {
		BoardBean board = null;
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement("select * from board where BOARD_NUM = ?");
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			if (rs.next()) {
				board = new BoardBean();
				board.setBOARD_NUM(rs.getInt("BOARD_NUM"));
				board.setBOARD_NAME(rs.getString("BOARD_NAME"));
				board.setBOARD_SUBJECT(rs.getString("BOARD_SUBJECT"));
				board.setBOARD_CONTENT(rs.getString("BOARD_CONTENT"));
				board.setBOARD_FILE(rs.getString("BOARD_FILE"));
				board.setBOARD_RE_REF(rs.getInt("BOARD_RE_REF"));
				board.setBOARD_RE_LEV(rs.getInt("BOARD_RE_LEV"));
				board.setBOARD_RE_SEQ(rs.getInt("BOARD_RE_SEQ"));
				board.setBOARD_READCOUNT(rs.getInt("BOARD_READCOUNT"));
				board.setBOARD_DATE(rs.getDate("BOARD_DATE"));
			}
			return board;
		} catch (Exception ex) {
			System.out.println("getDetail �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return null;
	}

	// 게시글 답글
	public int boardReply(BoardBean board) {
		String board_max_sql = "select max(board_num) from board";
		String sql = "";
		int num = 0;
		int result = 0;

		int re_ref = board.getBOARD_RE_REF(); // 원본 글 번호
		int re_lev = board.getBOARD_RE_LEV(); // 답변글의 깊이
		int re_seq = board.getBOARD_RE_SEQ(); // 답변글의 순서

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(board_max_sql);
			rs = pstmt.executeQuery();
			if (rs.next())
				num = rs.getInt(1) + 1; // 답변글이 있을경우 max에 +1 글번호
			else
				num = 1; // 답변글 없으면 최초등록

			sql = "update board set BOARD_RE_SEQ=BOARD_RE_SEQ+1 where BOARD_RE_REF=? ";
			sql += "and BOARD_RE_SEQ>?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, re_ref);
			pstmt.setInt(2, re_seq);
			result = pstmt.executeUpdate();

			re_seq = re_seq + 1; // 답변글이 원본 글보다 아래에 출력되어야 함
			re_lev = re_lev + 1; // 답변하면 레벨 1 증가

			sql = "insert into board (BOARD_NUM,BOARD_NAME,BOARD_PASS,BOARD_SUBJECT,";
			sql += "BOARD_CONTENT, BOARD_FILE,BOARD_RE_REF,BOARD_RE_LEV,BOARD_RE_SEQ,";
			sql += "BOARD_READCOUNT,BOARD_DATE) values(?,?,?,?,?,?,?,?,?,?,sysdate())";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			pstmt.setString(2, board.getBOARD_NAME());
			pstmt.setString(3, board.getBOARD_PASS());
			pstmt.setString(4, board.getBOARD_SUBJECT());
			pstmt.setString(5, board.getBOARD_CONTENT());
			pstmt.setString(6, ""); // 답변에는 파일을 저장하지않음
			pstmt.setInt(7, re_ref);
			pstmt.setInt(8, re_lev);
			pstmt.setInt(9, re_seq);
			pstmt.setInt(10, 0);
			pstmt.executeUpdate();
			return num;
		} catch (SQLException ex) {
			System.out.println("boardReply �뿉�윭 : " + ex);
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
			}
			if(con != null)
				try {
					con.close();
				} catch(SQLException ex) {
					
			}
		}
		return 0;
	}

}