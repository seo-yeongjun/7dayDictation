package application.img;

import java.awt.Image;

import javax.swing.ImageIcon;

import application.Main;

public class ImageSet {

	// 버튼 이미지 메소드
	public ImageIcon startStudy() {
		return new ImageIcon(Main.class.getResource("./img/startStudy.png"));
	}

	public ImageIcon startStudyRollover() {
		return new ImageIcon(Main.class.getResource("./img/startStudyRollover.png"));
	}

	public ImageIcon startTest() {
		return new ImageIcon(Main.class.getResource("./img/startTest.png"));
	}

	public ImageIcon startTestRollover() {
		return new ImageIcon(Main.class.getResource("./img/startTestRollover.png"));
	}

	public ImageIcon setting() {
		return new ImageIcon(Main.class.getResource("./img/setting.png"));
	}

	public ImageIcon settingRollover() {
		return new ImageIcon(Main.class.getResource("./img/settingRollover.png"));
	}

	public ImageIcon firstStart() {
		return new ImageIcon(Main.class.getResource("./img/firstStart.png"));
	}

	public ImageIcon firstStartRollover() {
		return new ImageIcon(Main.class.getResource("./img/firstStartRollover.png"));
	}

	public ImageIcon left() {
		return new ImageIcon(Main.class.getResource("./img/left.png"));
	}

	public ImageIcon leftRollover() {
		return new ImageIcon(Main.class.getResource("./img/leftRollover.png"));
	}

	public ImageIcon right() {
		return new ImageIcon(Main.class.getResource("./img/right.png"));
	}

	public ImageIcon rightRollover() {
		return new ImageIcon(Main.class.getResource("./img/rightRollover.png"));
	}

	public ImageIcon studyStart() {
		return new ImageIcon(Main.class.getResource("./img/studySrart.png"));
	}

	public ImageIcon studyStartRollover() {
		return new ImageIcon(Main.class.getResource("./img/studyStartRollover.png"));
	}

	public ImageIcon review() {
		return new ImageIcon(Main.class.getResource("./img/review.png"));
	}

	public ImageIcon reviewRollover() {
		return new ImageIcon(Main.class.getResource("./img/reviewRollover.png"));
	}

	public ImageIcon nextQuiz() {
		return new ImageIcon(Main.class.getResource("./img/nextQuiz.png"));
	}

	public ImageIcon nextQuizRollover() {
		return new ImageIcon(Main.class.getResource("./img/nextQuizRollover.png"));
	}

	public ImageIcon nextStudy() {
		return new ImageIcon(Main.class.getResource("./img/nextStudy.png"));
	}

	public ImageIcon nextStudyRollover() {
		return new ImageIcon(Main.class.getResource("./img/nextStudyRollover.png"));
	}

	public ImageIcon answer() {
		return new ImageIcon(Main.class.getResource("./img/answer.png"));
	}

	// 배경 이미지 메소드
	public Image startBG() {
		return new ImageIcon(Main.class.getResource("./img/startBG.png")).getImage();
	}

	public Image firstStartBG() {
		return new ImageIcon(Main.class.getResource("./img/firstStartBG.png")).getImage();
	}

	public Image mainPanel() {
		return new ImageIcon(Main.class.getResource("./img/mainPanel.png")).getImage();
	}

	public Image studyQuizPanel() {
		return new ImageIcon(Main.class.getResource("./img/studyQuizPanel.png")).getImage();
	}
}
