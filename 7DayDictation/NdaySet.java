package application.nDaySet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import application.TxtPathSet;
import application.WavPathSet;

//N일차에 대한 필요 정보를 txt파일에서 읽어 구조체로 만듬
public class NdaySet {
	int day;
	String QuizSet[] = new String[3];
	String QuizLeft[] = new String[3];
	String QuizRight[] = new String[3];
	String QuizAnswer[] = new String[3];
	String Study[] = new String[3];
	String dictations[] = new String[10];
	File audios[] = new File[10];
	// txtPath
	TxtPathSet txtPath = new TxtPathSet();
	WavPathSet wavPathSet = new WavPathSet();

	public String[] getQuizSet() {
		return QuizSet;
	}

	public String[] getQuizLeft() {
		return QuizLeft;
	}

	public String[] getQuizRight() {
		return QuizRight;
	}

	public String[] getQuizAnswer() {
		return QuizAnswer;
	}

	public String[] getStudy() {
		return Study;
	}

	public String[] getDictations() {
		return dictations;
	}

	public File[] getAudios() {
		return audios;
	}
	
	public int getDay() {
		return day;
	}

	public NdaySet(int i) {
		this.day = i;
		File quizSet = txtPath.quizDay(i);
		File quizLeft = txtPath.quizLeft(i);
		File quizRight = txtPath.quizRight(i);
		File quizAnswer = txtPath.quizAnswer(i);
		File dictation = txtPath.dictation(i);

		for (int j = 1; j <= 10; j++) {
			audios[j-1] = wavPathSet.dictationWav(i, j);
		}

		for (int progress = 1; progress <= 3; progress++) {
			try {
				BufferedReader reader = new BufferedReader(new FileReader(txtPath.study(i, progress)));
				String line = "";
				Study[progress - 1] = "";
				while ((line = reader.readLine()) != null) {
					Study[progress - 1] += line + "\n";
				}
				reader.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quizSet));
			String line = "";
			for (int z = 0; (line = reader.readLine()) != null; z++) {
				QuizSet[z] = line;
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quizLeft));
			String line = "";
			for (int z = 0; (line = reader.readLine()) != null; z++) {
				QuizLeft[z] = line;
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quizRight));
			String line = "";
			for (int z = 0; (line = reader.readLine()) != null; z++) {
				QuizRight[z] = line;
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(quizAnswer));
			String line = "";
			for (int z = 0; (line = reader.readLine()) != null; z++) {
				QuizAnswer[z] = line;
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		try {
			BufferedReader reader = new BufferedReader(new FileReader(dictation));
			String line = "";
			for (int z = 0; (line = reader.readLine()) != null; z++) {
				dictations[z] = line;
			}
			reader.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}
}
