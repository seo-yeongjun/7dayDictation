package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.InputStream;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import application.EffectWavListener;
import application.ImageSet;
import application.ScrollBarUI;
import application.nDaySet.NdaySet;
import application.listenerForPanel.DictationListener;

public class StudyPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	int progress = 0;
	// 학습 TextArea
	JTextArea studyArea = new JTextArea(5, 24);
	// 진행률 라벨
	JLabel progressLabel = new JLabel();
	// 다음 버튼
	JButton btnNext = new JButton(imgs.next());
	// 이전 버튼
	JButton btnPrev = new JButton(imgs.prev());
	// 받아쓰기 버튼
	JButton btnDictation = new JButton(imgs.dictationStart());
	// day Set;
	NdaySet ndaySet; 

	public StudyPanel(NdaySet ndaySet) {
		setLayout(null);
		this.ndaySet = ndaySet;
		
		ActionListener nextStudyActionListener = new NextStudyListener();
		DictationListener dictationListener = new DictationListener(ndaySet);
		EffectWavListener effectWavListener = new EffectWavListener("choose");
		// 학습공간 세팅
		studyArea.setFont(new Font("바탕체", Font.PLAIN, 27));
		progressLabel.setFont(openFontTTF("a남북통일",49f));
		studyArea.setForeground(Color.WHITE);
		studyArea.setEditable(false);
		studyArea.setOpaque(false);

		// 진행률 라벨 세팅
		progressLabel.setForeground(Color.white);
		setLabelText();
		// 스크롤패널 세팅
		JScrollPane scroll = new JScrollPane(studyArea);
		scroll.getViewport().setOpaque(false);
		scroll.setBackground(new Color(0x33FFFFFF, true));
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUI(new ScrollBarUI(Color.white));
		scroll.setBounds(72, 210, 720, 282);
		// 다음, 받아쓰기 버튼 세팅
		btnSet(btnNext);
		btnSet(btnDictation);
		btnSet(btnPrev);
		btnNext.addActionListener(nextStudyActionListener);
		btnNext.addActionListener(effectWavListener);
		btnDictation.addActionListener(effectWavListener);
		btnDictation.addActionListener(dictationListener);
		Dimension size3 = btnNext.getPreferredSize();
		btnNext.setBounds(700, 590, size3.width, size3.height);
		Dimension size4 = btnDictation.getPreferredSize();
		btnDictation.setBounds(640, 590, size4.width, size4.height);
		btnPrev.addActionListener(nextStudyActionListener);
		btnPrev.addActionListener(effectWavListener);
		Dimension size5 = btnPrev.getPreferredSize();
		btnPrev.setBounds(30, 590, size5.width, size5.height);
		btnPrev.setVisible(false);
		btnNext.setRolloverIcon(imgs.nextRollover());
		btnDictation.setRolloverIcon(imgs.dictationStartRollover());
		btnPrev.setRolloverIcon(imgs.prevtRollover());
		btnDictation.setVisible(false);

		add(btnPrev);
		add(scroll);
		add(progressLabel);
		add(btnNext);
		add(btnDictation);
	}

	public void setLabelText() {
		studyArea.setText(ndaySet.getStudy()[progress]);
		Dimension size1 = studyArea.getPreferredSize();
		studyArea.setBounds(0, 0, size1.width, size1.height);
		studyArea.setSelectionStart(0);
		studyArea.setSelectionEnd(0);
		progressLabel.setText(Integer.toString(progress + 1));
		Dimension size2 = progressLabel.getPreferredSize();
		progressLabel.setBounds(735, 150, size2.width, size2.height);
	}

	// 다음버튼 리스너
	class NextStudyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton) e.getSource();
			if (btn == btnNext) {
				progress++;
				if (progress == 1) {
					btnPrev.setVisible(true);
				} else if (progress == 2) {
					btnDictation.setVisible(true);
					btnNext.setVisible(false);
				}

				setLabelText();
			} else {
				progress--;
				btnDictation.setVisible(false);
				btnNext.setVisible(true);
				if (progress == 0) {
					btnPrev.setVisible(false);
				}
				setLabelText();
			}
		}
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.studyPanel(), 0, 0, this);
		repaint();
	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}

	// 폰트 생성 메소드
	public static Font openFontTTF(String name,float size) {
		String fontPath = "/font/" + name + ".ttf";
		try (InputStream is = StudyPanel.class.getResourceAsStream(fontPath)) {
			Font font = Font.createFont(Font.TRUETYPE_FONT, is);
			return font.deriveFont(size);
		} catch (Exception e) {
			return null;
		}
	}

}