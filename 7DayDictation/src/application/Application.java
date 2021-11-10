package application;

import application.nDaySet.NdaySet;
import application.panel.DictationPanel;
import application.panel.MainPanel;
import application.panel.StudyPanel;
import application.panel.StudyQuizPanel;

public class Application {
	static Main main = new Main();

	public static Main getMain() {
		return main;
	}

	public static MainPanel getMainPanel() {
		return new MainPanel();
	}

	public static StudyQuizPanel getStudyQuizPanel(NdaySet ndaySet) {
		return new StudyQuizPanel(ndaySet);
	}

	public static StudyPanel getStudyPanel(NdaySet ndaySet) {
		return new StudyPanel(ndaySet);
	}
	public static DictationPanel getDictationPanel(NdaySet ndaySet) {
		return new DictationPanel(ndaySet);
	}

	public static void main(String[] args) {
		getMain();
	}

}
