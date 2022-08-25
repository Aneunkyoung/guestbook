package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;

public class MainPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private final Color backgroundColor = new Color(46,46,46);
	
	private JTextField userEmail;
	private JPasswordField password;
	private JButton btnLogin, btnJoin;
	private JLabel email, pw;
	private ArrayList<JTextField> userInfo = new ArrayList<JTextField>();
	public JLabel userImageLabel;
	
	private ImageIcon userImage = getUserImage();
	public static MainFrame frame;
	
	public MainPanel() {}
	public MainPanel(MainFrame frame) {
		MainPanel.frame = frame;
		
		setBackground(backgroundColor);
		setLayout(null);
		
		email = new JLabel("email");
		email.setBounds(148, 185, 190, 50);
		email.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		email.setForeground(Color.white);
		add(email);
		
		userEmail = new JTextField("");
		userEmail.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		userEmail.setHorizontalAlignment(SwingConstants.CENTER);
		userEmail.setBounds(143, 217, 190, 50);
		add(userEmail);
		
		pw = new JLabel("password");
		pw.setBounds(148, 258, 190, 50);
		pw.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		pw.setForeground(Color.white);
		add(pw);
		
		password = new JPasswordField();
		password.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		password.setHorizontalAlignment(SwingConstants.CENTER);
		password.setBounds(143, 290, 190, 50);
		add(password);
		
		btnLogin = new JButton("로그인");
		btnLogin.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		btnLogin.setBounds(45, 370, 300, 50);
		btnLogin.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		    	  loginUser();
		      }
		});
		add(btnLogin);
		
		btnJoin = new JButton("회원가입");
		btnJoin.setFont(new Font("나눔고딕코딩", Font.PLAIN, 14));
		btnJoin.setBackground(backgroundColor);
		btnJoin.setBorderPainted (false);
		btnJoin.setForeground(Color.WHITE);
		btnJoin.setBounds(45, 420, 300, 50);
		btnJoin.addActionListener(new ActionListener() {
		      @Override
		      public void actionPerformed(ActionEvent e) {
		        JoinPanel joinPanel = new JoinPanel();
		        MainPanel.frame.changePanel(joinPanel);
		      }
		});
		add(btnJoin);
		
		UserImage();
		userImageLabel.setBounds(47, 240, 70, 70);
		add(userImageLabel);
		
		userInfo.add(userEmail);
		userInfo.add(password);
	}
	

	private ImageIcon getUserImage() {
		return new ImageIcon(new ImageIcon("img" + "/profile.png").getImage().getScaledInstance(70, 70, java.awt.Image.SCALE_SMOOTH));
	}
	
	private void loginUser(){
	    Controller controller = Controller.getInstance();
	    controller.findUser(userInfo);
	}
	
	public JLabel UserImage() {
		return userImageLabel = new JLabel(userImage);
	}
	
}
