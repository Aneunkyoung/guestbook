package controller;

import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Frame_Panel.GuestBookPanel;
import Frame_Panel.JoinPanel;
import Frame_Panel.MainPanel;
import GuestBook.GuestBookDAO;
import user.UserDTO;
import user.UserDAO;


public class Controller {
	private static Controller controller = new Controller();
	public String username = null;
	public String useremail = null;

	UserDAO userDAO;
	GuestBookDAO cashBookDAO;

	private Controller() {
		userDAO = new UserDAO();
	}

	public static Controller getInstance() {
	    return controller;
	  }

	public void joinUser(UserDTO user) {
		boolean insert = userDAO.joinUser(user);
	    if (insert) {
	      MainPanel mainPanel = new MainPanel(MainPanel.frame);
	      MainPanel.frame.changePanel(mainPanel);
	      JOptionPane.showMessageDialog(mainPanel, "회원가입이 완료되었습니다.");
	    } else {
	    	JoinPanel joinPanel = new JoinPanel();
	    	JOptionPane.showMessageDialog(joinPanel, "이미 존재하는 이메일 입니다.");
	    }
	}
	
	  public void findUser(ArrayList<JTextField> userInfos){

		   username = userDAO.findUser(userInfos);
		   useremail = userDAO.useremail;

		    if (username != null) {
		      GuestBookPanel chatPanel = new GuestBookPanel();
		      MainPanel.frame.changePanel(chatPanel);
		    } else if (username == null) {
		    	JOptionPane.showMessageDialog(null, "아이디 혹은 비밀번호를 확인해주세요.");
		    }
	  }
	
}
