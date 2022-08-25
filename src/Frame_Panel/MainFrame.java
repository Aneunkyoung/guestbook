package Frame_Panel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	private MainPanel mainPanel;
	
	public MainFrame() {
		mainPanel = new MainPanel(this);
		getContentPane().add(mainPanel);
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		setSize(390, 660);
		setLocationRelativeTo(null);
	}
	
	public void changePanel(JPanel panelName) {
	    getContentPane().removeAll();
	    getContentPane().add(panelName);
	    revalidate();
	    repaint();
	  }

}
