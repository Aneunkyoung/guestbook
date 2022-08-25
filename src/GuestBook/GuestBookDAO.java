package GuestBook;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import user.JdbcDAO;
import user.UserDTO;

public class GuestBookDAO extends JdbcDAO{
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "";
	private String user = ""; 
	private String password = ""; 
	private Connection con=null;
	private PreparedStatement pstmt=null;

	private static GuestBookDAO guestBookDAO = new GuestBookDAO();
	UserDTO userr;
	Controller controller;

	public GuestBookDAO() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static GuestBookDAO getInstance() {
		if(guestBookDAO == null) {
			guestBookDAO = new GuestBookDAO();
		}
		return guestBookDAO;
	}
	
	public void Connection() {
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("[UserDAO 오류] = " + e.getMessage());
		}
	}
	
	public void close() {
		try {
			pstmt.close();
		    con.close();
		} catch (SQLException e) {
		      e.printStackTrace();
		  }
	}
	
	public int insert(GuestBookDTO guestBook) {
		Connection();
	    String sql = "INSERT INTO GUESTBOOK (no, email, name, content, dates) VALUES (GUESTBOOK_SEQ.NEXTVAL,?, ?, ?, ?)";
	    
	    int rows = 0;
	    
	    try {
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, guestBook.getEmail());
	      pstmt.setString(2, guestBook.getName());
	      pstmt.setString(3, guestBook.getContent());
	      pstmt.setString(4, guestBook.getDate());
	      rows=pstmt.executeUpdate();
	    } catch (SQLException e) {
	    	System.out.println("[에러]insert() 메소드의 SQL 오류 = "+e.getMessage());
	    } finally {
	    	close();
		}
	    return rows;
	}
	
	public List<GuestBookDTO> selectAllList() throws SQLException {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GuestBookDTO> guestBookList=new ArrayList<GuestBookDTO>();
		try {
			con=getConnection();
			String sql="select * from GUESTBOOK order by no";
			pstmt=con.prepareStatement(sql);
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GuestBookDTO guestBook=new GuestBookDTO();
				guestBook.setEmail(rs.getString("email"));
				guestBook.setName(rs.getString("name"));
				guestBook.setNo(rs.getInt("no"));
				guestBook.setDate(rs.getString("dates"));
				guestBook.setContent(rs.getString("content"));
				guestBookList.add(guestBook);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectAllList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			con.close();
			pstmt.close();
			rs.close();
		}
		return guestBookList;
	}
	
	public int delete(int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="delete from GUESTBOOK where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, no);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]delete() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public int update(GuestBookDTO guestBook, int no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		int rows=0;
		try {
			con=getConnection();
			
			String sql="update GUESTBOOK set dates=?,content=? where no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, guestBook.getDate());
			pstmt.setString(2, guestBook.getContent());
			pstmt.setInt(3, no);
			
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[에러]update() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt);
		}
		return rows;
	}
	
	public List<GuestBookDTO> selectList(String content) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		List<GuestBookDTO> guestBookList=new ArrayList<GuestBookDTO>();
		try {
			con=getConnection();
			
			String sql="select * from GUESTBOOK where content like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+content+"%");
			
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				GuestBookDTO guestBook=new GuestBookDTO();
				guestBook.setEmail(rs.getString("email"));
				guestBook.setNo(rs.getInt("no"));
				guestBook.setDate(rs.getString("dates").substring(0, 10));
				guestBook.setContent(rs.getString("content"));
				guestBookList.add(guestBook);
			}
		} catch (SQLException e) {
			System.out.println("[에러]selectList() 메소드의 SQL 오류 = "+e.getMessage());
		} finally {
			close(con, pstmt, rs);
		}
		return guestBookList;
	}


}
