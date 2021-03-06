package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;import application.EffectWavListener;
import application.ImageSet;
import application.listenerForPanel.FirstStartListener;

public class FirstStartFrame extends JFrame {
	
	public FirstStartFrame() {
		setTitle("첫 시작");
		setVisible(true);
		setResizable(false);
		setSize(342, 270);
		setContentPane(new FirstPanel());
	}

}

//첫 시작 프레임 속 패널
class FirstPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 학습 하기 버튼
	JButton firstStart = new JButton(imgs.firstStart());
	// 이름 textfield
	JTextField nameArea = new JTextField(8);
	// 이름 빈값 입력시 에러메세지
	JLabel errorLabel = new JLabel("이름을 입력해 주세요.");
	
	//리스너
	FirstStartListener firstStartListener = new FirstStartListener(firstStart,nameArea,errorLabel);
	EffectWavListener effectWavListener = new EffectWavListener("choose");
	public FirstPanel() {
		setLayout(null);
		
		//버튼 세팅
		btnSet(firstStart);
		Dimension size1 = firstStart.getPreferredSize();
		firstStart.setBounds(78, 150, size1.width, size1.height);
		firstStart.setRolloverIcon(imgs.firstStartRollover());
		firstStart.addActionListener(firstStartListener);
		firstStart.addActionListener(effectWavListener);
		
		// 이름필드 세팅
		nameArea.setBorder(null);
		nameArea.setFont(new Font("바탕체",Font.PLAIN,21));
		Dimension size2 = nameArea.getPreferredSize();
		nameArea.setBounds(153,54,size2.width,size2.height);
		nameArea.addActionListener(firstStartListener);
		
		//에러 메세지 세팅
		
		errorLabel.setForeground(Color.red);
		errorLabel.setFont(new Font("바탕체",Font.PLAIN,12));
		Dimension size3 = errorLabel.getPreferredSize();
		errorLabel.setBounds(102,132,size3.width,size3.height);
		errorLabel.setVisible(false);
		//버튼 추가
		add(firstStart);
		add(nameArea);
		add(errorLabel);
	}

	// 배경 그리기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.firstStartBG(), 0, 0, this);
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