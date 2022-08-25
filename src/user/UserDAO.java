package user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTextField;

import controller.Controller;

public class UserDAO {
	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "";
	private String user = ""; 
	private String password = ""; 
	private Connection con=null;
	private PreparedStatement pstmt=null;
	public String username = null;
	public String useremail = null;
	ArrayList<UserDTO> userList;
	private static UserDAO userDAO = new UserDAO();
	UserDTO userr;
	Controller controller;
	
	public UserDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static UserDAO getInstance() {
		if(userDAO == null) {
			userDAO = new UserDAO();
		}
		return userDAO;
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
	
	public boolean joinUser(UserDTO userr) {
		Connection();
	    String sql = "insert into GEUSTBOOK_USER values(?,?,?)";
	    
	    boolean success = false;
	    
	    try {
	      pstmt = con.prepareStatement(sql);
	      pstmt.setString(1, userr.getEmail());
	      pstmt.setString(2, userr.getName());
	      pstmt.setString(3, userr.getPassword());
	      pstmt.executeUpdate();
	      
	      success = true;
	 
	    } catch (SQLException e) {
	    	success = false;
	    } finally {
	    	close();
		}
	    return success;
	}
	
	public String findUser(ArrayList<JTextField> userInfos) {
		Connection();
		String sql = "select name from GEUSTBOOK_USER where email=? and password=?";
		    String email = userInfos.get(0).getText();
		    String password = userInfos.get(1).getText();
		    
		    String name = null;
		    try {
		      pstmt = con.prepareStatement(sql);
		      pstmt.setString(1, email);
		      pstmt.setString(2, password);
		      ResultSet rs = pstmt.executeQuery();
		      while (rs.next()) {
		    	  name = rs.getString("name");
		      }
		      username = name;
		      useremail = email;
		    } catch (SQLException e) {
		    	System.out.println("[UserDAO 오류] = " + e.getMessage());
		    } finally {
		    	close();
			}
		    return username; 
	}
	
	public int deleteUser(String email) {
		Connection();
		int rows=0;
		try {
			controller = Controller.getInstance();
			String email1 = controller.useremail;
			String sql="delete from GEUSTBOOK_USER where email='"+email1+"'";
			pstmt=con.prepareStatement(sql);
			rows=pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println("[deleteUser] 오류 = "+e.getMessage());
		} finally {
			close();
		}
		return rows;
	}
	
	public int updateUser(UserDTO user) {
		Connection();
		int rows = 0;
		try {
			controller = Controller.getInstance();
			String email1 = controller.useremail;
			String newName = user.getName();
			String sql="update GEUSTBOOK_USER set name='"+newName+"' where email='"+email1+"'";
			pstmt=con.prepareStatement(sql);
			rows=pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("[updateUser] 오류 = "+e.getMessage());
		} finally {
			close();
		}
		return rows;
	}
}
