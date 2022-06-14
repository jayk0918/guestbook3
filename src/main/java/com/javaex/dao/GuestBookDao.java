package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.javaex.vo.GuestBookVo;

public class GuestBookDao {

	// 필드
	private String id = "admin";
	private String pw = "Jayk09180918";
	private String url = "jdbc:oracle:thin:@webdb_high?TNS_ADMIN=/Users/jaykim0918/Dropbox/Wallet_webdb";
	private String driver = "oracle.jdbc.OracleDriver";
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// connection
	public void getConnection() {
		try {
			Class.forName(driver);
			conn = DriverManager.getConnection(url, id, pw);

		} catch (ClassNotFoundException e) {
			System.out.println("error : 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		}
	}

	// connection end - close
	public void close() {
		try {
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// select
	public List<GuestBookVo> getList() {
		List<GuestBookVo> gList = new ArrayList<GuestBookVo>();
		getConnection();

		try {
			String query = "";
			query += " select no ";
			query += "     	 ,name ";
			query += "       ,content ";
			query += "       ,to_char(reg_date, 'YYYY-MM-DD HH24:MM:SS') reg_date";
			query += " from guestbook ";
			query += " order by no asc ";

			pstmt = conn.prepareStatement(query);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");

				GuestBookVo guestBookVo = new GuestBookVo(no, name, content, date);

				gList.add(guestBookVo);
			}

		} catch (SQLException e) {
			System.out.println("error" + e);
		}

		close();
		return gList;
	}

	// insert
	public int insert(GuestBookVo guestBookVo) {
		int count = -1;

		getConnection();

		try {
			String query = "";
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate ) ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, guestBookVo.getName());
			pstmt.setString(2, guestBookVo.getPassword());
			pstmt.setString(3, guestBookVo.getContent());

			count = pstmt.executeUpdate();

			System.out.println("[" + count + "건 등록되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// delete
	public int delete(String password, int no) {
		int count = -1;
		getConnection();

		try {
			String query = "";
			query += " delete from guestbook ";
			query += " where password = ? ";
			query += " and no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, password);
			pstmt.setInt(2, no);

			count = pstmt.executeUpdate();
			System.out.println("[" + count + "건 삭제되었습니다.]");
			System.out.println();

		} catch (SQLException e) {
			System.out.println("error:" + e);
		}

		close();
		return count;
	}

	// search individual
	public GuestBookVo getList(int num) {
		GuestBookVo guestBookVo = null;

		getConnection();

		try {
			String query = "";
			query += " select no ";
			query += "     	 ,name ";
			query += "       ,content ";
			query += "       ,reg_date ";
			query += " from guestbook ";
			query += " where no = ? ";

			pstmt = conn.prepareStatement(query);
			pstmt.setInt(1, num);

			rs = pstmt.executeQuery();

			while (rs.next()) {

				int no = rs.getInt("no");
				String name = rs.getString("name");
				String content = rs.getString("content");
				String date = rs.getString("reg_date");
				
				guestBookVo = new GuestBookVo(no, name, content, date);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return guestBookVo;
	}

}
