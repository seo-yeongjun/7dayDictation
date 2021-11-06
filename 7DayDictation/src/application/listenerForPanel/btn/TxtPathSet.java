package application.listenerForPanel.btn;

import java.io.File;

public class TxtPathSet {
	public File userName() {
		return new File("src/application/txt/userName.txt");
	}
	public File startDate() {
		return new File("src/application/txt/startDate.txt");
	}
	public File quizAnswer(int i) {
		return new File("src/application/txt/quizAnswer"+i+".txt");
	}
	public File quizDay(int i) {
		return new File("src/application/txt/quizDay"+i+".txt");
	}
	public File quizLeft(int i) {
		return new File("src/application/txt/quizLeftDay"+i+".txt");
	}
	public File quizRight(int i) {
		return new File("src/application/txt/quizRightDay"+i+".txt");
	}
	public File dictation(int i) {
		return new File("src/application/txt/dictation"+i+".txt");
	}
	public File study(int i, int progress) {
		return new File("src/application/txt/study"+i+"-"+progress+".txt");
	}
}

