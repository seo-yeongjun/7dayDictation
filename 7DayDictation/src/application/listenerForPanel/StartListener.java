package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import application.Application;
import application.TxtPathSet;
import application.panel.FirstStartFrame;

//시작 패널에서 메인 패널로 전환하기 위한 리스너 첫 시작이면 첫시작 프레임을 염
public class StartListener implements ActionListener {
	//텍스트 주소 모음 클래스
	TxtPathSet txtPathSet= new TxtPathSet();
	
	
	public void actionPerformed(ActionEvent e) {
		String name = null;
		int nDay = 0;
		try {
			BufferedReader reader1 = new BufferedReader(new FileReader(txtPathSet.userName()));
			BufferedReader reader2 = new BufferedReader(new FileReader(txtPathSet.nDay()));
			name = reader1.readLine();
			nDay = Integer.parseInt(reader2.readLine());
			reader1.close();
			reader2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		if (name!=null) {
			Application.getMain().setContentPane(Application.getMainPanel(nDay));
		} else {
			FirstStartFrame firstStartFrame = new FirstStartFrame();
			firstStartFrame.setLocation(420, 300);
		}
	}
}