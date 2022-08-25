package Frame_Panel;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import GuestBook.GuestBookDAO;
import GuestBook.GuestBookDTO;
import controller.Controller;

public class GuestBookPanel extends JPanel{

	private static final long serialVersionUID = 1L;
	private final Color topPanelColor = new Color(46,46,46);
	private final Color midPanelColor = new Color(25,25,25);
	private final Color backgroundColor = new Color(38,38,38);
	private ImageIcon userImage = getUserImage();

	JTextField dateText, contentText;
	JPanel topPanel, midPane;
	JLabel userLabel, name, date, content, money;
	Controller controller;
	JButton submit, update, delete, search, cancle, exit;
	JTable table;
	
	public GuestBookPanel() {
		setBackground(backgroundColor);
		setLayout(null);
		
		topPanel = new JPanel();
		topPanel.setLayout(null);
		topPanel.setBounds(0, 0, 390, 660); 
		topPanel.setBackground(topPanelColor);
		add(topPanel);
		
		userLabel = new JLabel(userImage);
		userLabel.setBounds(12, 13, 40, 40);
		userLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				UserEditFrame userEditFrame = new UserEditFrame(null);
				userEditFrame.setVisible(true);
			}
			
		});
		topPanel.add(userLabel);
		
		controller = Controller.getInstance();
		name = new JLabel(controller.username);
		name.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
		name.setForeground(Color.WHITE);
		name.setBounds(65, 13, 40, 40);
		topPanel.add(name);
		
		midPane = new JPanel();
		midPane.setBounds(0, 65, 390, 660);
		midPane.setBackground(midPanelColor);
		topPanel.add(midPane);
		
		midPane.setLayout(null);
		
		date = new JLabel("날짜");
		date.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
		date.setBounds(20, 10, 200, 30);
		date.setForeground(Color.WHITE);
		midPane.add(date);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date time = new Date();
		String time1 = sdf.format(time);
		
		dateText = new JTextField(time1.toString());
		dateText.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
		dateText.setBackground(topPanelColor);
		dateText.setForeground(Color.WHITE);
		dateText.setHorizontalAlignment(SwingConstants.CENTER);
		dateText.setBounds(13, 35, 120, 40);
		midPane.add(dateText);
		
		content = new JLabel("내용");
		content.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		content.setBounds(142, 10, 150, 30);
		content.setForeground(Color.WHITE);
		midPane.add(content);
		
		contentText = new JTextField();
		contentText.setFont(new Font("나눔고딕코딩", Font.PLAIN, 12));
		contentText.setBackground(topPanelColor);
		contentText.setForeground(Color.WHITE);
		contentText.setHorizontalAlignment(SwingConstants.CENTER);
		contentText.setBounds(137, 35, 240, 40);
		midPane.add(contentText);
		
		submit = new JButton("입력");
		submit.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		submit.setBounds(15, 90, 60, 30);
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				insertDB();
				
			}
		});
		midPane.add(submit);
		
		update = new JButton("수정");
		update.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		update.setBounds(75, 90, 60, 30);
		update.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				update();
			}
		});
		midPane.add(update);
		
		delete = new JButton("삭제");
		delete.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		delete.setBounds(135, 90, 60, 30);
		delete.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				remove();
			}
		});
		midPane.add(delete);
		
		search = new JButton("검색");
		search.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		search.setBounds(195, 90, 60, 30);
		search.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});
		midPane.add(search);
		
		cancle = new JButton("취소");
		cancle.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		cancle.setBounds(255, 90, 60, 30);
		cancle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					displayAll();
					dateText.setText(null);
					contentText.setText(null);
				} catch (SQLException e1) {
					
				}
			}
		});
		midPane.add(cancle);
		
		exit = new JButton("닫기");
		exit.setFont(new Font("나눔고딕코딩", Font.PLAIN, 11));
		exit.setBounds(315, 90, 60, 30);
		exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		midPane.add(exit);
		
		Object[] title={"no","name","content","date"};
		table=new JTable(new DefaultTableModel(title, 0));
		table.setEnabled(false);
		table.setFont(new Font("나눔고딕코딩", Font.PLAIN, 10));
		table.getTableHeader().setReorderingAllowed(false);
		table.getTableHeader().setResizingAllowed(false);
		
		JScrollPane sp=new JScrollPane(table);
		sp.setBounds(15, 150, 360, 400);
		midPane.add(sp);

		try {
			displayAll();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
	
	private ImageIcon getUserImage() {
		return new ImageIcon(new ImageIcon("img" + "/profile.png").getImage().getScaledInstance(40, 40, java.awt.Image.SCALE_SMOOTH));
	}
	
	private void insertDB(){
		String noDate=dateText.getText();
		if(noDate.equals("")) {
			JOptionPane.showMessageDialog(this, "날짜를 입력해주세요.");
			dateText.requestFocus();
			return;
		}
		String dateReg="^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
		if(!Pattern.matches(dateReg, noDate)) {
			JOptionPane.showMessageDialog(this, "날짜 형식은 YYYY-MM-DD로 입력해주세요.");
			dateText.requestFocus();
			return;
		}
		String noContent=contentText.getText();
		if(noContent.equals("")) {
			JOptionPane.showMessageDialog(this, "내용을 입력해주세요.");
			contentText.requestFocus();
			return;
		}

		GuestBookDTO guestBook=new GuestBookDTO();
		guestBook.setNo(0);
		guestBook.setEmail(controller.useremail);
		guestBook.setName(controller.username);
		guestBook.setContent(contentText.getText());
		guestBook.setDate(dateText.getText());
		GuestBookDAO.getInstance().insert(guestBook);
		
		contentText.setText(null);
		try {
			displayAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void displayAll() throws SQLException {
		List<GuestBookDTO> guestBookList=GuestBookDAO.getInstance().selectAllList();
		
		if(guestBookList.isEmpty()) {
			return;
		}

		DefaultTableModel model=(DefaultTableModel)table.getModel();

		for(int i=model.getRowCount();i>0;i--) {
			model.removeRow(0);
		}
		
		for(GuestBookDTO guestBook:guestBookList) {
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(guestBook.getNo());
			rowData.add(guestBook.getName());
			rowData.add(guestBook.getContent());
			rowData.add(guestBook.getDate().substring(0, 10));
			model.addRow(rowData);
		}
	}
	
	public void remove() {	
		String noTemp =JOptionPane.showInputDialog(this, "삭제할 번호(No)를 입력해주세요." ,"NO 입력", JOptionPane.QUESTION_MESSAGE);
		int no = Integer.parseInt(noTemp);
		int rows = GuestBookDAO.getInstance().delete(no);
		
		if(rows>0) {
			try {
				JOptionPane.showMessageDialog(this, no + "번의 방명록을 삭제 완료하였습니다.");
				displayAll();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			JOptionPane.showMessageDialog(this, "삭제하고자 하는 정보가 없습니다.");
		}
	}
	
	public void update() {
		String noDate=dateText.getText();
		if(noDate.equals("")) {
			JOptionPane.showMessageDialog(this, "날짜를 입력해주세요.");
			dateText.requestFocus();
			return;
		}
		String dateReg="^\\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$";
		if(!Pattern.matches(dateReg, noDate)) {
			JOptionPane.showMessageDialog(this, "날짜 형식은 YYYY-MM-DD로 입력해주세요.");
			dateText.requestFocus();
			return;
		}
		String noContent=contentText.getText();
		if(noContent.equals("")) {
			JOptionPane.showMessageDialog(this, "내용을 입력해주세요.");
			contentText.requestFocus();
			return;
		}
		
		String noTemp =JOptionPane.showInputDialog(this, "수정할 번호(No)를 입력해주세요." ,"NO 입력", JOptionPane.QUESTION_MESSAGE);
		int no = Integer.parseInt(noTemp);
		
		String date = dateText.getText();
		String content = contentText.getText();
		
		GuestBookDTO guestBook=new GuestBookDTO();
		guestBook.setDate(date);
		guestBook.setContent(content);
		
		GuestBookDAO.getInstance().update(guestBook, no);
		
		contentText.setText(null);
		try {
			displayAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void search() {  
		String noTemp =JOptionPane.showInputDialog(this, "검색하고자 하는 내용을 입력해주세요." ,"CONTENT 입력", JOptionPane.QUESTION_MESSAGE);
		
		String content= noTemp;
		List<GuestBookDTO> guestBookList=GuestBookDAO.getInstance().selectList(content);
		
		if(guestBookList.isEmpty()) {
			JOptionPane.showMessageDialog(this, "검색된 정보가 없습니다.");
			return;
		}
		
		DefaultTableModel model=(DefaultTableModel)table.getModel();
		
		for(int i=model.getRowCount();i>0;i--) {
			model.removeRow(0);
		}
		
		for(GuestBookDTO guestBook:guestBookList) {
			Vector<Object> rowData=new Vector<Object>();
			rowData.add(guestBook.getNo());
			rowData.add(guestBook.getName());
			rowData.add(guestBook.getContent());
			rowData.add(guestBook.getDate());
			model.addRow(rowData);
		}
	}	
}
