package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import application.img.ImageSet;
import application.nDaySet.NdaySet;

public class StudyQuizPanel extends JPanel {

	int progress = 0;
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 다음 문제 버튼
	JButton btnNext = new JButton(imgs.nextQuiz());
	// 학습 하기 버튼
	JButton btnNextStudy = new JButton(imgs.nextStudy());
	// 왼쪽 버튼
	JButton btnLeft = new JButton();
	// 오른쪽 버튼
	JButton btnRight = new JButton();
	// 정답 라벨
	JLabel answerLabel = new JLabel(imgs.answer());
	// 문제 라벨
	JLabel quizJLabel = new JLabel();
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

		MouseAdapter leftOrRight = new LeftOrRight();
		setLayout(null);

		// 다음 문제 버튼 설정
		btnSet(btnNext);
		Dimension size1 = btnNext.getPreferredSize();
		btnNext.setBounds(1000, 920, size1.width, size1.height);
		btnNext.setRolloverIcon(imgs.nextQuizRollover());
		btnNext.addActionListener(null);
		// 학습 하기 버튼 설정
		btnSet(btnNextStudy);
		btnNextStudy.setVisible(false);
		Dimension size2 = btnNextStudy.getPreferredSize();
		btnNextStudy.setBounds(1000, 920, size2.width, size2.height);
		btnNextStudy.setRolloverIcon(imgs.nextStudyRollover());
		// 왼쪽 버튼 설정
		btnSet(btnLeft);
		btnLeft.addMouseListener(leftOrRight);
		btnLeft.setFont(new Font("바탕체", Font.PLAIN, 77));
		btnLeft.setText(QuizLeft[0]);
		Dimension size3 = btnLeft.getPreferredSize();
		btnLeft.setBounds(380 - (size3.width / 2), 600, size3.width, size3.height);
		// 오른쪽 버튼 설정
		btnSet(btnRight);
		btnRight.addMouseListener(leftOrRight);
		btnRight.setFont(new Font("바탕체", Font.PLAIN, 77));
		btnRight.setText(QuizRight[0]);
		Dimension size4 = btnRight.getPreferredSize();
		btnRight.setBounds(1020 - (size4.width / 2), 600, size4.width, size4.height);
		// 정답 라벨 설정
		Dimension size5 = answerLabel.getPreferredSize();
		answerLabel.setBounds(180, 520, size5.width, size5.height);
		answerLabel.setVisible(false);
		// 문제 라벨 설정
		quizJLabel.setFont(new Font("바탕체", Font.PLAIN, 72));
		quizJLabel.setText(QuizSet[0]);
		Dimension size6 = quizJLabel.getPreferredSize();
		quizJLabel.setBounds(700 - (size6.width / 2), 340, size6.width, size6.height);

		add(btnNext);
		add(btnNextStudy);
		add(answerLabel);
		add(btnLeft);
		add(btnRight);
		add(quizJLabel);
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

	// 왼쪽 오른쪽 버튼 리스너
	class LeftOrRight extends MouseAdapter {
		public void mouseEntered(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			btn.setForeground(Color.decode("#810202"));
		}

		public void mouseExited(MouseEvent e) {
			JButton btn = (JButton) e.getSource();
			btn.setForeground(Color.black);
		}

		int check = 0;

		public void mouseClicked(MouseEvent e) {
			if (check == 0) {
				JButton btn = (JButton) e.getSource();

				// 라벨 위치 설정
				if (QuizAnswer[progress].equals("1")) {
					answerLabel.setLocation(180, answerLabel.getY());
				} else {
					answerLabel.setLocation(820, answerLabel.getY());
				}
				// 정답일시
				if (QuizAnswer[progress].equals("1") && btn == btnLeft
						|| QuizAnswer[progress].equals("0") && btn == btnRight) {
					// to do: 정답 소리
					answerLabel.setVisible(true);
				} // 오답일시
				else if (QuizAnswer[progress].equals("0") && btn == btnLeft
						|| QuizAnswer[progress].equals("1") && btn == btnRight) {
					// to do: 틀림 소리
					Timer wrongAnswer = new Timer(100, new ActionListener() {
						int x = btn.getX();
						int y = btn.getY();
						int key = 0;

						@Override
						public void actionPerformed(ActionEvent e) {
							if (key == 0) {
								btn.setLocation(x - 10, y);
								key++;
							} else if (key == 1) {
								btn.setLocation(x + 10, y);
								key++;
							} else if (key == 2) {
								btn.setLocation(x - 10, y);
								key++;
							} else if (key == 3) {
								btn.setLocation(x + 10, y);
								key++;
							} else {
								btn.setLocation(x, y);
								((Timer) e.getSource()).stop();
							}
						}
					});
					wrongAnswer.start();
					answerLabel.setVisible(true);
				}
			}
			check++;
		}
	}

}
