package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Application;
import application.EffectWavListener;
import application.ImageSet;
import application.NdaySet;
import application.TxtPathSet;

public class DictationPanel extends JPanel {
	
	
	
	
	
	
	
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();
	TxtPathSet tp = new TxtPathSet();
	BtnCheckAnswerListener btnCheckAnswerListener = new BtnCheckAnswerListener();
	EffectWavListener effectWavListener = new EffectWavListener("choose");
	AudioListener audioListener = new AudioListener();
	MyAnswerListener myAnswerListener = new MyAnswerListener();
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
	Clip clip;
	int re = 0;

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
	public DictationPanel(NdaySet ndaySet, int re) {
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
		this.re=re;
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
		btnGrade.addActionListener(new GradeListener());
		btnGrade.addActionListener(effectWavListener);
		btnSet(btnGrade);
		add(btnGrade);
	}

	public void setExitButton() {
		btnExit.setRolloverIcon(imgs.exitRollover());
		Dimension size1 = btnExit.getPreferredSize();
		btnExit.setBounds(680, 590, size1.width, size1.height);
		btnExit.addActionListener(new ExitListener());
		btnSet(btnExit);
		btnExit.setVisible(false);
		add(btnExit);
	}

	public void setListenButton() {
		for (int i = 0; i < 10; i++) {
			btnListen[i] = new JButton(imgs.listen());
			btnListen[i].setRolloverIcon(imgs.listenRollover());
			Dimension size1 = btnListen[i].getPreferredSize();
			btnListen[i].setBounds(230, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnListen[i]);
			btnListen[i].addActionListener(audioListener);
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
			btnCheckAnswer[i].addActionListener(effectWavListener);
			btnCheckAnswer[i].addActionListener(btnCheckAnswerListener);
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
			btnMyAnswer[i].addActionListener(effectWavListener);
			btnMyAnswer[i].addActionListener(myAnswerListener);
			btnMyAnswer[i].setVisible(false);
			add(btnMyAnswer[i]);
		}
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.dictationPanel(), 0, 0, this);
		repaint();
		Application.getMain().revalidate();
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

	public Clip audioPlay(int i) {
		String volume = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new TxtPathSet().volume()));
			try {
				volume = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			Clip clip = AudioSystem.getClip();
			File audio = ndaySet.getAudios()[i];
			AudioInputStream audioStream = AudioSystem.getAudioInputStream(audio);
			clip.open(audioStream);
			FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
			float range = gainControl.getMaximum() - gainControl.getMinimum();
			float gain = (float) ((range * (Float.parseFloat(volume) / 100.0)) + gainControl.getMinimum());
			gainControl.setValue(gain);
			return (clip);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (UnsupportedAudioFileException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	class AudioListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			int i = 0;
			for (int j = 0; j < 10; j++) {
				if (btnListen[j] == (JButton) e.getSource()) {
					i = j;
				}
			}
			if (clip != null) {
				clip.stop();
			}
			clip = audioPlay(i);
			clip.start();
		}

	}

	class BtnCheckAnswerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String answer = "";
			JButton btn = (JButton) e.getSource();
			for (int i = 0; i < 10; i++) {
				if (btnCheckAnswer[i] == btn) {
					try (BufferedReader br = new BufferedReader(new FileReader(tp.dictation(ndaySet.getDay())))) {
						for (int j = 0; j < i; j++)
							br.readLine();
						answer = br.readLine();
						br.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					textField[i].setText(answer);
					btn.setVisible(false);
					btnMyAnswer[i].setVisible(true);
				}
			}

		}
	}

	class MyAnswerListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String answer = "";
			JButton btn = (JButton) e.getSource();
			for (int i = 0; i < 10; i++) {
				if (btnMyAnswer[i] == btn) {
					try (BufferedReader br = new BufferedReader(new FileReader(tp.myDictation(ndaySet.getDay())))) {
						for (int j = 0; j < i; j++)
							br.readLine();
						answer = br.readLine();
						br.close();
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					textField[i].setText(answer);
					btnCheckAnswer[i].setVisible(true);
					btnMyAnswer[i].setVisible(false);
				}
			}

		}
	}

	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}

	}

	class GradeListener implements ActionListener {
		String myAnswers = "";
		int score = 0;

		@Override
		public void actionPerformed(ActionEvent e) {
			if (clip != null) {
				clip.stop();
			}
			String[] answers = ndaySet.getDictations();
			for (int i = 0; i < 10; i++) {
				textField[i].setEditable(false);
				myAnswers += textField[i].getText() + "\n";
				if (textField[i].getText().equals(answers[i])) {
					answerLabel[i].setVisible(true);
					score += 10;
				} else {
					starLabel[i].setVisible(true);
					btnCheckAnswer[i].setVisible(true);
				}
			}
			try {
				BufferedWriter myDictation = new BufferedWriter(new FileWriter(tp.myDictation(ndaySet.getDay())));
				myDictation.write(myAnswers);
				myDictation.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			scoreLabel.setText(Integer.toString(score));
			scoreLabel.setVisible(true);
			btnGrade.setVisible(false);
			btnExit.setVisible(true);
			if(re==0) {
			try {
				BufferedWriter dayWriter = new BufferedWriter(new FileWriter(tp.nDay()));
				dayWriter.write(Integer.toString(ndaySet.getDay()));
				dayWriter.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}}
		}
	}

}