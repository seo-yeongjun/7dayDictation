package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
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
		
		
		
		if (nameArea.getText().equals("")) {
			errorLabel.setVisible(true);
		} else if (firstStart == e.getSource()) {
			JButton btn = (JButton) e.getSource();
			setUserNameAndDate(txtPathSet.userName(), txtPathSet.startDate());
			((JFrame) btn.getTopLevelAncestor()).dispose();
			Application.getMain().setContentPane(Application.getMainPanel(0));
		} else {
			JTextField tf = (JTextField) e.getSource();
			setUserNameAndDate(txtPathSet.userName(), txtPathSet.startDate());
			((JFrame) tf.getTopLevelAncestor()).dispose();
			Application.getMain().setContentPane(Application.getMainPanel(0));
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