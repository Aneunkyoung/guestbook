package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;
import user.UserDTO;

public class JoinPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private final Color backgroundColor = new Color(46,46,46);
	
	private UserDTO user;
	
	private ArrayList<JTextField> userInfo = new ArrayList<JTextField>();
	private JTextField userEmail, userName;
	private JPasswordField password;
	private JButton btnJoin, backBtn;
	private JLabel email, name, pw;
	
	
	public JoinPanel() {
		setBackground(backgroundColor);
		setLayout(null);
		
		name = new JLabel("name");
		name.setBounds(75, 152, 190, 50);
		name.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		name.setForeground(Color.white);
		add(name);
		userName = new JTextField("");
		userName.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		userName.setHorizontalAlignment(SwingConstants.CENTER);
		userName.setBounds(70, 187, 250, 50);
		add(userName);
		
		email = new JLabel("email");
		email.setBounds(75, 225, 190, 50);
		email.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		email.setForeground(Color.white);
		add(email);
		userEmail = new JTextField("");
		userEmail.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		userEmail.setHorizontalAlignment(SwingConstants.CENTER);
		userEmail.setBounds(70, 257, 250, 50);
		add(userEmail);
		
		pw = new JLabel("password");
		pw.setBounds(75, 295, 190, 50);
		pw.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		pw.setForeground(Color.white);
		add(pw);
		password = new JPasswordField();
		password.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(70, 327, 250, 50);
		add(password);
		
		btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		btnJoin.setBounds(45, 490, 300, 50);
		btnJoin.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  String userNameCheck = userName.getText();
		    	  if(userNameCheck.equals("") || userNameCheck == null) {
		    		  JOptionPane.showMessageDialog(null, "이름을 입력해 주세요.");
		    		  userName.requestFocus();
		    		  return;
		    	  }
		    	  String userNameReg="[가-힣]{2,7}";
		    	  if(!Pattern.matches(userNameReg, userNameCheck)) {
		    		  JOptionPane.showMessageDialog(null, "이름은 2~5 범위의 한글만 입력해 주세요.");
		    		  userName.requestFocus();
		    		  return;
		    	  }
		    	  String userEmailCheck = userEmail.getText();
		    	  if(userEmailCheck.equals("") || userEmailCheck == null) {
		    		  JOptionPane.showMessageDialog( null, "이메일을 입력해 주세요.");
		    		  userEmail.requestFocus();
		    		  return;
		    	  }
		    	  String userEmailReg="^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$";
		    	  if(!Pattern.matches(userEmailReg, userEmailCheck)) {
		    		  JOptionPane.showMessageDialog(null, "이메일 형식에 맞게 입력해주세요.    \n ex)  example@google.com");
		    		  userEmail.requestFocus();
		    		  return;
		    	  }
		    	  @SuppressWarnings("deprecation")
		    	  String passwordCheck = password.getText();
		    	  if(passwordCheck.equals("") || passwordCheck == null) {
		    		  JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주세요.");
		    		  password.requestFocus();
		    		  return;
		    	  }
		    	  String passwordReg="^(?=.*[A-Za-z])(?=.*\\d)(?=.*[$@$!%*#?&])[A-Za-z\\d$@$!%*#?&]{8,15}$";
		    	  if(!Pattern.matches(passwordReg, passwordCheck)) {
		    		  JOptionPane.showMessageDialog(null, "비밀번호는 숫자, 문자, 특수문자 포함 8~15자리 이내로 입력해 주세요.");
		    		  password.requestFocus();
		    		  return;
		    	  }
		    	  
		    	  createUser();
		      }
		});
		userInfo.add(userEmail);
		userInfo.add(userName);
		userInfo.add(password);
		add(btnJoin);
		
		backBtn = new JButton("뒤로가기");
		backBtn.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		backBtn.setBackground(backgroundColor);
		backBtn.setBorderPainted (false);
		backBtn.setForeground(Color.WHITE);
		backBtn.setBounds(45, 540, 300, 50);
		backBtn.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  MainPanel mainPanel = new MainPanel(MainPanel.frame);
			      MainPanel.frame.changePanel(mainPanel);
		      }
		});
		add(backBtn);
	}

	private void createUser() {
		user = new UserDTO(userInfo.get(0).getText(), userInfo.get(1).getText(), userInfo.get(2).getText());
		Controller controller = Controller.getInstance();
	    controller.joinUser(user);
	}
}
