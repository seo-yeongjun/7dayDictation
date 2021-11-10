package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.ImageSet;
import application.TxtPathSet;
import application.nDaySet.NdaySet;

public class DictationPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	int progress = 0;
	JLabel dateLabel = new JLabel();
	// 작성 필드
	JTextField[] textField = new JTextField[10];
	// 정답 라벨
	JLabel[] answerLabel = new JLabel[10];
	// 틀림 라벨
	JLabel[] starLabel = new JLabel[10];
	// 채점 버튼
	JButton btnGrade = new JButton(imgs.grade());
	// 종료 버튼
	JButton btnExit = new JButton(imgs.exit());
	// 정답 보기 버튼
	JButton[] btnCheckAnswer = new JButton[10];
	// 내답 보기 버튼
	JButton[] btnMyAnswer = new JButton[10];
	// 듣기 버튼
	JButton[] btnListen = new JButton[10];
	// 점수 라벨
	JLabel scoreLabel = new JLabel();
	// 이름 라벨
	JLabel name = new JLabel();
	// day Set;
	NdaySet ndaySet;

	public DictationPanel(NdaySet ndaySet) {
		setLayout(null);
		this.ndaySet = ndaySet;
		// 이름 세팅
		nameLabelSet();
		// 정답표시 세팅
		setAnswerLabel();
		// 듣기 버튼 세팅
		setListenButton();
		// 틀림 라벨 세팅
		setStarLabel();
		// 작성 필드 세팅
		setTextField();
		// 날짜 라벨 세팅
		setDate();
		// 채점 하기 버튼 세팅
		setGradeButton();
		// 종료 하기 버튼 세팅
		setExitButton();
		// 정답 채크 버튼 세팅
		setBtnCheckAnswer();
		// 내답 보기 버튼 세팅
		setBtnMyAnswer();
		// 점수 라벨 세팅
		setScoreLabel();
	}

	public void setDate() {
		dateLabel.setFont(StudyPanel.openFontTTF("a내손글씨L", 18f));
		dateLabel.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Dimension size1 = dateLabel.getPreferredSize();
		dateLabel.setBounds(240, 80, size1.width, size1.height);
		add(dateLabel);
	}

	public void setAnswerLabel() {
		for (int i = 0; i < 10; i++) {
			answerLabel[i] = new JLabel(imgs.answerLabel());
			Dimension size1 = answerLabel[i].getPreferredSize();
			answerLabel[i].setBounds(250, 110 + (i * 40), size1.width, size1.height);
			answerLabel[i].setVisible(false);
			add(answerLabel[i]);
		}
	}

	public void setStarLabel() {
		for (int i = 0; i < 10; i++) {
			starLabel[i] = new JLabel(imgs.star());
			Dimension size1 = starLabel[i].getPreferredSize();
			starLabel[i].setBounds(260, 110 + (i * 40), size1.width, size1.height);
			starLabel[i].setVisible(false);
			add(starLabel[i]);
		}
	}

	public void setScoreLabel() {
		scoreLabel.setFont(StudyPanel.openFontTTF("a내손글씨B", 35f));
		scoreLabel.setForeground(new Color(225, 116, 116));
		scoreLabel.setText("100");
		Dimension size1 = scoreLabel.getPreferredSize();
		scoreLabel.setBounds(600, 77, size1.width, size1.height);
		scoreLabel.setVisible(false);
		add(scoreLabel);
	}

	public void setGradeButton() {
		btnGrade.setRolloverIcon(imgs.gradeRollover());
		Dimension size1 = btnGrade.getPreferredSize();
		btnGrade.setBounds(540, 590, size1.width, size1.height);
		// todo:
		btnGrade.addActionListener(null);
		btnSet(btnGrade);
		add(btnGrade);
	}

	public void setExitButton() {
		btnExit.setRolloverIcon(imgs.exitRollover());
		Dimension size1 = btnExit.getPreferredSize();
		btnExit.setBounds(680, 590, size1.width, size1.height);
		// todo:
		btnExit.addActionListener(null);
		btnSet(btnExit);
		btnExit.setVisible(false);
		add(btnExit);
	}

	public void setListenButton() {
		for (int i = 0; i < 10; i++) {
			btnListen[i] = new JButton(imgs.listen());
			Dimension size1 = btnListen[i].getPreferredSize();
			btnListen[i].setBounds(230, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnListen[i]);
			// todo:
			btnListen[i].addActionListener(null);
			add(btnListen[i]);
		}
	}

	public void setBtnCheckAnswer() {
		for (int i = 0; i < 10; i++) {
			btnCheckAnswer[i] = new JButton(imgs.checkAnswer());
			btnCheckAnswer[i].setRolloverIcon(imgs.checkAnswerRollover());
			Dimension size1 = btnCheckAnswer[i].getPreferredSize();
			btnCheckAnswer[i].setBounds(620, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnCheckAnswer[i]);
			// todo:
			btnCheckAnswer[i].addActionListener(null);
			btnCheckAnswer[i].setVisible(false);
			add(btnCheckAnswer[i]);
		}
	}

	public void setBtnMyAnswer() {
		for (int i = 0; i < 10; i++) {
			btnMyAnswer[i] = new JButton(imgs.myAnswer());
			btnMyAnswer[i].setRolloverIcon(imgs.myAnswerRollover());
			Dimension size1 = btnMyAnswer[i].getPreferredSize();
			btnMyAnswer[i].setBounds(620, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnMyAnswer[i]);
			// todo:
			btnMyAnswer[i].addActionListener(null);
			btnMyAnswer[i].setVisible(false);
			add(btnMyAnswer[i]);
		}
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.dictationPanel(), 0, 0, this);
		repaint();
	}

	public void setTextField() {
		for (int i = 0; i < 10; i++) {
			textField[i] = new JTextField(28);
			textField[i].setFont(StudyPanel.openFontTTF("a내손글씨L", 22f));
			Dimension size1 = textField[i].getPreferredSize();
			textField[i].setBounds(325, 115 + (i * 40), size1.width, size1.height);
			textField[i].setBackground(new Color(0x20E2E2E2, true));
			textField[i].setBorder(null);
			add(textField[i]);
		}
	}

	public void nameLabelSet() {
		TxtPathSet txtPathSet = new TxtPathSet();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(txtPathSet.userName()));
			String name = reader.readLine();
			this.name.setText(name);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		name.setForeground(Color.decode("#707070"));
		name.setFont(StudyPanel.openFontTTF("a내손글씨L", 23f));
		Dimension size = name.getPreferredSize();
		name.setBounds(370, 80, size.width, size.height);
		add(name);
	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}

	// to do
	class GradeListener implements ActionListener {
		String myAnswers = "";
		int score=0;
		public void actionPerformed(ActionEvent e) {
			String[] answers = ndaySet.getDictations();
			for (int i = 0; i < 10; i++) {
				textField[i].setEditable(false);
				myAnswers += textField[i].getText()+"\n";
				if (textField[i].getText().equals(answers[i])) {
					answerLabel[i].setVisible(true);
					score+=10;
				} else {
					starLabel[i].setVisible(true);
					btnCheckAnswer[i].setVisible(true);
				}
			}
			scoreLabel.setText(Integer.toString(score));
			scoreLabel.setVisible(true);
			btnGrade.setVisible(false);
			btnExit.setVisible(true);
		}
	}

}