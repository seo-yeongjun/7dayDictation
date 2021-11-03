package application.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.img.ImageSet;
import application.listener.btn.StartListener;

public class Start extends JPanel {

	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 학습 하기 버튼
	JButton btnStart = new JButton(imgs.startStudy());
	// 한글 능력치 검사 버튼
	JButton btnTest = new JButton(imgs.startTest());
	// 설정 버튼
	JButton btnSetting = new JButton(imgs.setting());

	public Start() {

		setLayout(null);

		// 시작하기 버튼 설정
		btnSet(btnStart);
		Dimension size1 = btnStart.getPreferredSize();
		btnStart.setBounds(550, 630, size1.width, size1.height);
		btnStart.setRolloverIcon(imgs.startStudyRollover());
		btnStart.addActionListener(new StartListener());
		// 한글 테스트 버튼 설정
		btnSet(btnTest);
		Dimension size2 = btnTest.getPreferredSize();
		btnTest.setBounds(400, 720, size2.width, size2.height);
		btnTest.setRolloverIcon(imgs.startTestRollover());
		// 설정 버튼 설정
		btnSet(btnSetting);
		Dimension size3 = btnSetting.getPreferredSize();
		btnSetting.setBounds(1300, 900, size3.width, size3.height);
		btnSetting.setRolloverIcon(imgs.settingRollover());

		// 버튼 추가
		add(btnStart);
		add(btnTest);
		add(btnSetting);
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.startBG(), 0, 0, this);
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


