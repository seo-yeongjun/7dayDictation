package application.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.EffectWavListener;
import application.ImageSet;

public class TestResultFrame extends JFrame {

	public TestResultFrame(int score, JFrame frame) {
		setTitle("결과");
		setVisible(true);
		setResizable(false);
		setSize(342, 270);
		setContentPane(new ResultPanel(score, frame));
	}

}

//첫 시작 프레임 속 패널
class ResultPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 학습 하기 버튼
	JButton exitBtn = new JButton(imgs.exitTest());
	// 세종 라벨
	JLabel kingLabel = new JLabel();
	// 점수 라벨
	JLabel scoreLabel = new JLabel();
	int score;
	JFrame frame;

	// 리스너
	EffectWavListener effectWavListener = new EffectWavListener("choose");

	public ResultPanel(int score, JFrame frame) {
		setLayout(null);
		this.score = score;
		this.frame= frame;
		// 끝내기 버튼 세팅
		btnSet(exitBtn);
		Dimension size1 = exitBtn.getPreferredSize();
		exitBtn.setBounds(170, 180, size1.width, size1.height);
		exitBtn.addActionListener(effectWavListener);
		exitBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
			JButton btn = (JButton)e.getSource();
			frame.dispose();
			((JFrame)btn.getTopLevelAncestor()).dispose();
			}
		});

		// 점수 라벨 세팅
		scoreLabel.setText(Integer.toString(score));
		scoreLabel.setFont(new Font("바탕체", Font.PLAIN, 20));
		Dimension size3 = scoreLabel.getPreferredSize();
		scoreLabel.setBounds(64, 23, size3.width, size3.height);

		if (score == 100) {
			kingLabel.setIcon(imgs.to100());
		} else if (score >= 70) {
			kingLabel.setIcon(imgs.to70_90());
		} else if (score >= 40) {
			kingLabel.setIcon(imgs.to40_60());
		} else {
			kingLabel.setIcon(imgs.to0_30());
		}

		Dimension size2 = kingLabel.getPreferredSize();
		kingLabel.setBounds(10, 42, size2.width, size2.height);

		// 버튼 추가
		add(exitBtn);
		add(scoreLabel);
		add(kingLabel);
	}

	// 배경 그리기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.resultBG(), 0, 0, this);
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