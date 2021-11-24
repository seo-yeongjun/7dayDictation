package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import application.Application;
import application.TxtPathSet;

//첫 시작 프레임의 리스너 (이름, 시작 날짜 저장)
public class FirstStartListener implements ActionListener {
	JButton firstStart;
	JTextField nameArea;
	JLabel errorLabel;
	public FirstStartListener(JButton firstStart, JTextField nameArea, JLabel errorLabel) {
		this.firstStart = firstStart;
		this.nameArea = nameArea;
		this.errorLabel= errorLabel;
	}

	public void actionPerformed(ActionEvent e) {
		
		//텍스트 주소 모음 클래스
		TxtPathSet txtPathSet= new TxtPathSet();
		
		int nDay = 0;
		
		try {
			BufferedReader reader2 = new BufferedReader(new FileReader(txtPathSet.nDay()));
			nDay = Integer.parseInt(reader2.readLine());
			reader2.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		//이름 필드가 비어있으면 에러 메시지 출력
		if (nameArea.getText().equals("")) {
			errorLabel.setVisible(true);
		} else if (firstStart == e.getSource()) {
			JButton btn = (JButton) e.getSource();
			setUserNameAndDate(txtPathSet.userName(), txtPathSet.startDate());
			((JFrame) btn.getTopLevelAncestor()).dispose();
			Application.getMain().setContentPane(Application.getMainPanel(nDay));
		} else {
			JTextField tf = (JTextField) e.getSource();
			setUserNameAndDate(txtPathSet.userName(), txtPathSet.startDate());
			((JFrame) tf.getTopLevelAncestor()).dispose();
			Application.getMain().setContentPane(Application.getMainPanel(nDay));
		}

	
	}

	//userName,startDate 입력 메소드
	public void setUserNameAndDate(File fileName, File fileDate) {
		try {
			BufferedWriter nameWriter = new BufferedWriter(new FileWriter(fileName));
			BufferedWriter dateWriter = new BufferedWriter(new FileWriter(fileDate));
			nameWriter.write(nameArea.getText());
			dateWriter.write("시작 "+new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			dateWriter.close();
			nameWriter.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}