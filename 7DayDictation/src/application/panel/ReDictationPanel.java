package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.Application;
import application.EffectWavListener;
import application.ImageSet;
import application.TxtPathSet;
import application.listenerForPanel.DictationListener;
import application.nDaySet.NdaySet;
import application.panel.DictationPanel.GradeListener;

public class ReDictationPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();
	TxtPathSet tp = new TxtPathSet();
	EffectWavListener effectWavListener = new EffectWavListener("choose");
	BtnCheckAnswerListener btnCheckAnswerListener = new BtnCheckAnswerListener();
	MyAnswerListener myAnswerListener = new MyAnswerListener();
	int progress = 0;
	JLabel dateLabel = new JLabel();
	// 작성 필드
	JTextField[] textField = new JTextField[10];
	// 정답 라벨
	JLabel[] answerLabel = new JLabel[10];
	// 틀림 라벨
	JLabel[] starLabel = new JLabel[10];
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
	// 다시 풀기 버튼
	JButton btnReDictation = new JButton(imgs.reDictation());
	// day Set;
	NdaySet ndaySet;
	int score = 0;

	public ReDictationPanel(NdaySet ndaySet) {
		setLayout(null);
		this.ndaySet = ndaySet;
		// 이름 세팅
		nameLabelSet();
		// 작성 필드 세팅
		setTextField();
		// 날짜 라벨 세팅
		setDate();
		// 정답표시 세팅
		setAnswerLabel();
		// 틀림 라벨 세팅
		setStarLabel();
		// 정답 채크 버튼 세팅
		setBtnCheckAnswer();
		// 내답 보기 버튼 세팅
		setBtnMyAnswer();
		// 듣기 버튼 세팅
		setListenButton();
		// 점수 라벨 세팅
		setScoreLabel();
		// 다시 풀기 세팅
		setBtnReDictation();

	}

	private void setBtnReDictation() {
		btnReDictation.setRolloverIcon(imgs.reDictationRollover());
		Dimension size1 = btnReDictation.getPreferredSize();
		btnReDictation.setBounds(540, 590, size1.width, size1.height);
		btnReDictation.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				Application.getMain().setContentPane(Application.getDictationPanel(ndaySet,1));
				Application.getMain().revalidate();
			}
		});
		btnReDictation.addActionListener(effectWavListener);
		btnSet(btnReDictation);
		add(btnReDictation);
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.dictationPanel(), 0, 0, this);
		repaint();
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

	public void setTextField() {
		for (int i = 0; i < 10; i++) {
			textField[i] = new JTextField(28);
			textField[i].setFont(StudyPanel.openFontTTF("a내손글씨L", 22f));
			Dimension size1 = textField[i].getPreferredSize();
			textField[i].setBounds(325, 115 + (i * 40), size1.width, size1.height);
			textField[i].setBackground(new Color(0x20E2E2E2, true));
			textField[i].setBorder(null);
			textField[i].setEditable(false);
			add(textField[i]);
		}
		try (BufferedReader br = new BufferedReader(new FileReader(tp.myDictation(ndaySet.getDay())))) {
			for (int i = 0; i < 10; i++)
				textField[i].setText(br.readLine());
			br.close();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public void setDate() {
		dateLabel.setFont(StudyPanel.openFontTTF("a내손글씨L", 18f));
		dateLabel.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
		Dimension size1 = dateLabel.getPreferredSize();
		dateLabel.setBounds(240, 80, size1.width, size1.height);
		add(dateLabel);
	}

	public void setBtnCheckAnswer() {
		for (int i = 0; i < 10; i++) {
			btnCheckAnswer[i] = new JButton(imgs.checkAnswer());
			btnCheckAnswer[i].setRolloverIcon(imgs.checkAnswerRollover());
			Dimension size1 = btnCheckAnswer[i].getPreferredSize();
			btnCheckAnswer[i].setBounds(620, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnCheckAnswer[i]);
			btnCheckAnswer[i].addActionListener(btnCheckAnswerListener);
			btnCheckAnswer[i].addActionListener(effectWavListener);

			add(btnCheckAnswer[i]);
			String[] answers = ndaySet.getDictations();
			if (textField[i].getText().equals(answers[i])) {
				score += 10;
				answerLabel[i].setVisible(true);
			} else {
				starLabel[i].setVisible(true);
				btnCheckAnswer[i].setVisible(true);
			}
		}
	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
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

	public void setBtnMyAnswer() {
		for (int i = 0; i < 10; i++) {
			btnMyAnswer[i] = new JButton(imgs.myAnswer());
			btnMyAnswer[i].setRolloverIcon(imgs.myAnswerRollover());
			Dimension size1 = btnMyAnswer[i].getPreferredSize();
			btnMyAnswer[i].setBounds(620, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnMyAnswer[i]);
			btnMyAnswer[i].addActionListener(myAnswerListener);
			btnMyAnswer[i].addActionListener(effectWavListener);
			btnMyAnswer[i].setVisible(false);
			add(btnMyAnswer[i]);
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

	public void setListenButton() {
		for (int i = 0; i < 10; i++) {
			btnListen[i] = new JButton(imgs.listen());
			Dimension size1 = btnListen[i].getPreferredSize();
			btnListen[i].setBounds(230, 110 + (i * 40), size1.width / 2, size1.height);
			btnSet(btnListen[i]);
			add(btnListen[i]);
		}
	}

	public void setScoreLabel() {
		scoreLabel.setFont(StudyPanel.openFontTTF("a내손글씨B", 35f));
		scoreLabel.setForeground(new Color(225, 116, 116));
		scoreLabel.setText(Integer.toString(score));
		Dimension size1 = scoreLabel.getPreferredSize();
		scoreLabel.setBounds(600, 77, size1.width, size1.height);
		scoreLabel.setVisible(true);
		add(scoreLabel);
	}
}