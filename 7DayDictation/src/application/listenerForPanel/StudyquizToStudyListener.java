package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import application.Application;
import application.nDaySet.NdaySet;
import application.panel.FirstStartFrame;

//퀴즈 패널을 학습하기 패널로 전환하기 위한 리스너
public class StudyquizToStudyListener implements ActionListener {
	NdaySet ndaySet;
	public StudyquizToStudyListener(NdaySet ndaySet) {
		this.ndaySet=ndaySet;
	}
	public void actionPerformed(ActionEvent e) {
			Application.getMain().setContentPane(Application.getStudyPanel(ndaySet));
			Application.getMain().revalidate();
	}
}