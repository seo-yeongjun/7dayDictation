package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import application.ImageSet;
import application.listenerForPanel.StudyquizToStudyListener;
import application.nDaySet.NdaySet;

public class StudyQuizPanel extends JPanel {
	int check = 0;
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
	NdaySet ndaySet;
	
	public StudyQuizPanel(NdaySet ndaySet) {
		this.ndaySet=ndaySet;

		MouseAdapter leftOrRight = new LeftOrRight();
		ActionListener nextQuiz = new NextQuiz();
		ActionListener checkAnswer = new CheckAnswer();
		setLayout(null);

		// 다음 문제 버튼 설정
		btnSet(btnNext);
		btnNext.setVisible(false);
		Dimension size1 = btnNext.getPreferredSize();
		btnNext.setBounds(600, 572, size1.width, size1.height);
		btnNext.setRolloverIcon(imgs.nextQuizRollover());
		btnNext.addActionListener(nextQuiz);
		// 학습 하기 버튼 설정
		btnSet(btnNextStudy);
		btnNextStudy.setVisible(false);
		btnNextStudy.addActionListener(new StudyquizToStudyListener(ndaySet));
		Dimension size2 = btnNextStudy.getPreferredSize();
		btnNextStudy.setBounds(600, 552, size2.width, size2.height);
		btnNextStudy.setRolloverIcon(imgs.nextStudyRollover());
		// 왼쪽 버튼 설정
		btnSet(btnLeft);
		btnLeft.addMouseListener(leftOrRight);
		btnLeft.addActionListener(checkAnswer);
		btnLeft.setFont(new Font("바탕체", Font.PLAIN, 46));

		// 오른쪽 버튼 설정
		btnSet(btnRight);
		btnRight.addMouseListener(leftOrRight);
		btnRight.addActionListener(checkAnswer);
		btnRight.setFont(new Font("바탕체", Font.PLAIN, 46));

		// 정답 라벨 설정
		Dimension size5 = answerLabel.getPreferredSize();
		answerLabel.setBounds(108, 312, size5.width, size5.height);
		answerLabel.setVisible(false);
		// 문제 라벨 폰트
		quizJLabel.setFont(new Font("바탕체", Font.PLAIN, 43));

		setLabel();

		add(btnNext);
		add(btnNextStudy);
		add(answerLabel);
		add(btnLeft);
		add(btnRight);
		add(quizJLabel);
	}

	// 라벨 재설정
	public void setLabel() {
		quizJLabel.setText(ndaySet.getQuizSet()[progress]);
		Dimension size6 = quizJLabel.getPreferredSize();
		quizJLabel.setBounds(420 - (size6.width / 2), 204, size6.width, size6.height);
		btnRight.setText(ndaySet.getQuizRight()[progress]);
		Dimension size4 = btnRight.getPreferredSize();
		btnRight.setBounds(612 - (size4.width / 2), 360, size4.width, size4.height);
		btnLeft.setText(ndaySet.getQuizLeft()[progress]);
		Dimension size3 = btnLeft.getPreferredSize();
		btnLeft.setBounds(228 - (size3.width / 2), 360, size3.width, size3.height);
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

	// 다음 문제 리스너
	class NextQuiz implements ActionListener {
		public void actionPerformed(ActionEvent e) {
				check = 0;
				setLabel();
				btnNext.setVisible(false);
				answerLabel.setVisible(false);
		}
	}

	// 정답 체크 리스너
	class CheckAnswer implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (check == 0) {
				JButton btn = (JButton) e.getSource();

				// 라벨 위치 설정
				if (ndaySet.getQuizAnswer()[progress].equals("1")) {
					answerLabel.setLocation(108, answerLabel.getY());
				} else {
					answerLabel.setLocation(492, answerLabel.getY());
				}
				// 정답일시
				if (ndaySet.getQuizAnswer()[progress].equals("1") && btn == btnLeft
						|| ndaySet.getQuizAnswer()[progress].equals("0") && btn == btnRight) {
					// to do: 정답 소리
					answerLabel.setVisible(true);
					progress++;
				} // 오답일시
				else if (ndaySet.getQuizAnswer()[progress].equals("0") && btn == btnLeft
						|| ndaySet.getQuizAnswer()[progress].equals("1") && btn == btnRight) {
					// to do: 틀림 소리
					Timer wrongAnswer = new Timer(75, new ActionListener() {
						int x = btn.getX();
						int y = btn.getY();
						int key = 0;

						@Override
						public void actionPerformed(ActionEvent e) {
							if (key == 0) {
								btn.setLocation(x - 6, y);
								key++;
							} else if (key == 1) {
								btn.setLocation(x + 6, y);
								key++;
							} else if (key == 2) {
								btn.setLocation(x - 6, y);
								key++;
							} else {
								btn.setLocation(x, y);
								((Timer) e.getSource()).stop();
							}
						}
					});
					wrongAnswer.start();
					answerLabel.setVisible(true);
					progress++;
				}
			}
			check++;
			if (progress == 3) {
				btnNextStudy.setVisible(true);
			} else {
				btnNext.setVisible(true);
			}
		}
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
	}

}
