package application.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import application.EffectWavListener;
import application.ImageSet;
import application.listenerForPanel.SettingListener;
import application.listenerForPanel.StartListener;

public class Start extends JPanel {

	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();
	EffectWavListener effectWavListener = new EffectWavListener("choose");

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
		btnStart.setBounds(325, 378, size1.width, size1.height);
		btnStart.setRolloverIcon(imgs.startStudyRollover());
		btnStart.addActionListener(effectWavListener); 
		btnStart.addActionListener(new StartListener());
		// 한글 테스트 버튼 설정
		btnSet(btnTest);
		Dimension size2 = btnTest.getPreferredSize();
		btnTest.setBounds(238, 427, size2.width, size2.height);
		btnTest.addActionListener(effectWavListener);
		btnTest.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				TestDictationFrame testDictationFrame = new TestDictationFrame();
			}
		});
		btnTest.setRolloverIcon(imgs.startTestRollover());
		// 설정 버튼 설정
		btnSet(btnSetting);
		btnSetting.addActionListener(effectWavListener);
		btnSetting.addActionListener(new SettingListener());
		Dimension size3 = btnSetting.getPreferredSize();
		btnSetting.setBounds(770, 580, size3.width, size3.height);
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


