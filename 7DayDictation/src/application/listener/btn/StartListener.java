package application.listener.btn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import application.Application;
import application.panel.FirstStartFrame;

public class StartListener implements ActionListener {
	//텍스트 주소 모음 클래스
	TxtPathSet txtPathSet= new TxtPathSet();
	
	
	public void actionPerformed(ActionEvent e) {
	
		if (txtPathSet.userName().exists()) {
			Application.getMain().setContentPane(Application.getMainPanel());
		} else {
			FirstStartFrame firstStartFrame = new FirstStartFrame();
			firstStartFrame.setLocation(420, 300);
		}
	}
}