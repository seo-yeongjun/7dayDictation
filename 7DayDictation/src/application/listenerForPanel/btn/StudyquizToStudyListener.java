package application.listenerForPanel.btn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import application.Application;
import application.nDaySet.NdaySet;
import application.panel.FirstStartFrame;

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