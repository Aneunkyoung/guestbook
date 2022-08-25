package Launch;

import Frame_Panel.MainFrame;

public class Launch {
	public static void main(String[] args) {
		try {
			MainFrame mainFrame = new MainFrame();
		    mainFrame.setVisible(true);
		} catch (Exception e) {
			System.out.println("[ClientLaunch] 오류 = " + e.getMessage());
		}
	}
}
