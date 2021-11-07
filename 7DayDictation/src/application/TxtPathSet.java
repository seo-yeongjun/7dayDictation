package application;

import java.io.File;

public class TxtPathSet {
	public File userName() {
			return new File(Main.class.getResource("/txt/userName.txt").getFile());
	}
	public File startDate() {
		return new File(Main.class.getResource("/txt/startDate.txt").getFile());
	}
	public File quizAnswer(int i) {
		return new File(Main.class.getResource("/txt/quizAnswer"+i+".txt").getFile());
	}
	public File quizDay(int i) {
		return new File(Main.class.getResource("/txt/quizDay"+i+".txt").getFile());

	}
	public File quizLeft(int i) {
		return new File(Main.class.getResource("/txt/quizLeftDay"+i+".txt").getFile());
		
	}
	public File quizRight(int i) {
		return new File(Main.class.getResource("/txt/quizRightDay"+i+".txt").getFile());
	
	}
	public File dictation(int i) {
		return new File(Main.class.getResource("/txt/dictation"+i+".txt").getFile());
	}
	public File study(int i, int progress) {
		return new File(Main.class.getResource("/txt/study"+i+"-"+progress+".txt").getFile());
	}
}

