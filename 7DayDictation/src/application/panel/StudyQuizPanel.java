package application.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.img.ImageSet;
import application.nDaySet.NdaySet;

public class StudyQuizPanel extends JPanel {

	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 다음 문제 버튼
	JButton btnNext = new JButton(imgs.nextQuiz());
	// 학습 하기 버튼
	JButton btnNextStudy = new JButton(imgs.nextStudy());
	// 한글 능력치 검사 버튼
	JButton btnLeft = new JButton();
	// 설정 버튼
	JButton btnRight = new JButton();
	// 문제 라벨
	JLabel questionLabel = new JLabel();
	// day Set;
	String QuizSet[] = new String[3];
	String QuizLeft[] = new String[3];
	String QuizRight[] = new String[3];
	String QuizAnswer[] = new String[3];
	ImageIcon StudyImg[] = new ImageIcon[3];
	String dictations[] = new String[10];

	public StudyQuizPanel(NdaySet ndaySet) {
		this.QuizAnswer = ndaySet.getQuizAnswer();
		this.QuizSet = ndaySet.getQuizSet();
		this.QuizLeft = ndaySet.getQuizLeft();
		this.QuizRight = ndaySet.getQuizRight();
		this.StudyImg = ndaySet.getStudyImg();
		this.dictations = ndaySet.getDictations();
		setLayout(null);

		// 다음 문제 버튼 설정
		btnSet(btnNext);
		Dimension size1 = btnNext.getPreferredSize();
		btnNext.setBounds(1000, 920, size1.width, size1.height);
		btnNext.setRolloverIcon(imgs.nextQuizRollover());
		btnNext.addActionListener(null);
		// 학습 하기 버튼 설정
		btnSet(btnNextStudy);
		Dimension size2 = btnNextStudy.getPreferredSize();
		btnNextStudy.setBounds(1000, 920, size2.width, size2.height);
		btnNextStudy.setRolloverIcon(imgs.nextStudyRollover());
		// 왼쪽 버튼 설정
		btnSet(btnLeft);
		btnLeft.setFont(new Font("바탕체", Font.PLAIN, 77));
		btnLeft.setText(QuizLeft[0]);
		Dimension size3 = btnLeft.getPreferredSize();
		btnLeft.setBounds(250, 600, size3.width, size3.height);
		// 오른쪽 버튼 설정
		btnSet(btnRight);
		btnRight.setFont(new Font("바탕체", Font.PLAIN, 77));
		btnRight.setText(QuizRight[0]);
		Dimension size4 = btnRight.getPreferredSize();
		btnRight.setBounds(880, 600, size4.width, size4.height);

		// 버튼 추가
		add(btnNext);
		add(btnNextStudy);
		add(btnLeft);
		add(btnRight);
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.studyQuizPanel(), 0, 0, this);
		setOpaque(false);

	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}
}
