package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.Controller;
import user.UserDAO;
import user.UserDTO;

public class UserEditFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private final Color backgroundColor = new Color(46,46,46);

	private JPanel contentPane;
	private JLabel uemail, name;
	private JTextField userEmail;
	public JTextField uname;
	private JButton update, delete;
	UserDAO _udo;
	Controller controller;
	
	public UserEditFrame(String email) {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(300, 300);
		setLocationRelativeTo(null);
		setBackground(backgroundColor);
		
		contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(backgroundColor);
		
		uemail = new JLabel("email");
		uemail.setBounds(60, 20, 190, 50);
		uemail.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		uemail.setForeground(Color.white);
		contentPane.add(uemail);
		
		controller = Controller.getInstance();
		userEmail = new JTextField(controller.useremail);
		userEmail.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
		userEmail.setHorizontalAlignment(SwingConstants.CENTER);
		userEmail.setBounds(55, 50, 190, 40);
		userEmail.setEditable(false);
		contentPane.add(userEmail);
		
		name = new JLabel("name");
		name.setBounds(60, 80, 190, 50);
		name.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		name.setForeground(Color.white);
		contentPane.add(name);
		
		uname = new JTextField(controller.username);
		uname.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		uname.setHorizontalAlignment(SwingConstants.CENTER);
		uname.setBounds(55, 110, 190, 40);
		contentPane.add(uname);
		
		update = new JButton("수정하기");
		update.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		update.setBackground(backgroundColor);
		update.setBorderPainted (false);
		update.setForeground(Color.WHITE);
		update.setBounds(55, 160, 190, 40);
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				UserDTO user=new UserDTO();
				String name = uname.getText();
				user.setName(name);
				int rows=UserDAO.getInstance().updateUser(user);
				if(rows>0) {
					JOptionPane.showMessageDialog(null, "수정 되었습니다.");
					dispose();
					validate();
					repaint();
				} else {
					JOptionPane.showMessageDialog(null, "수정할 수 없습니다.");
				}
			}
		});
		contentPane.add(update);
		
		delete = new JButton("탈퇴하기");
		delete.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		delete.setBackground(backgroundColor);
		delete.setBorderPainted (false);
		delete.setForeground(Color.WHITE);
		delete.setBounds(55, 190, 190, 40);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rows=UserDAO.getInstance().deleteUser(email);
				if(rows>0) {
					JOptionPane.showMessageDialog(null, "탈퇴가 완료 되었습니다.");
					System.exit(0);
				} else {
					JOptionPane.showMessageDialog(null, "탈퇴할 수 없습니다.");
				}
			}
		});
		contentPane.add(delete);
	}
}
