package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import application.ImageSet;
import application.TxtPathSet;
import application.listenerForPanel.MainPanelDayListener;

public class MainPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();

	// 왼쪽 버튼
	JButton btnLeft = new JButton(imgs.left());
	// 오른쪽 버튼
	JButton btnRight = new JButton(imgs.right());
	// 학습하기 버튼
	JButton btnStudyStart = new JButton(imgs.studyStart());
	// 복습하기 버튼
	JButton btnReview = new JButton(imgs.review());
	
	// 이름표 라벨
	JLabel nameTag = new JLabel();
	// n일 int
	private static int day = 1;
	// n일 라벨
	JLabel dayTag = new JLabel(Integer.toString(day));
	// 시작 날짜 라벨
	JLabel dateTag = new JLabel();

	// 왼쪽 오른쪽 버튼 리스너
		MainPanelDayListener dayListener = new MainPanelDayListener(day, btnLeft, btnRight, dayTag, btnStudyStart);

	
	public MainPanel() {
		setSize(1424, 1040);
		setLayout(null);

		// n일 라벨 세팅
		dayTag.setBorder(null);
		dayTag.setFont(new Font("바탕체", Font.PLAIN, 31));
		dayTag.setForeground(Color.decode("#707070"));
		Dimension size6 = dayTag.getPreferredSize();
		dayTag.setBounds(288, 289, size6.width, size6.height);
		// 이름표 라벨 세팅
		nameTag.setBorder(null);
		nameTagSet();
		nameTag.setForeground(Color.decode("#707070"));
		nameTag.setFont(new Font("바탕체", Font.PLAIN, 21));
		Dimension size = nameTag.getPreferredSize();
		nameTag.setBounds(33, 18, size.width, size.height);
		// 시작 날짜 라벨 세팅
		dateTag.setBorder(null);
		dateTag.setFont(new Font("바탕체", Font.PLAIN, 11));
		dateTag.setForeground(Color.decode("#707070"));
		dateTagSet();
		Dimension size4 = dateTag.getPreferredSize();
		dateTag.setBounds(630, 244, size4.width, size4.height);

		// 버튼 세팅
		btnSet(btnLeft);
		btnSet(btnRight);
		btnSet(btnStudyStart);
		btnSet(btnReview);
		btnLeft.addActionListener(dayListener);
		btnRight.addActionListener(dayListener);
		btnStudyStart.addActionListener(dayListener);
		btnLeft.setRolloverIcon(imgs.leftRollover());
		btnReview.setRolloverIcon(imgs.reviewRollover());
		btnRight.setRolloverIcon(imgs.rightRollover());
		btnStudyStart.setRolloverIcon(imgs.studyStartRollover());
		Dimension size1 = btnLeft.getPreferredSize();
		Dimension size2 = btnRight.getPreferredSize();
		Dimension size3 = btnStudyStart.getPreferredSize();
		Dimension size5 = btnReview.getPreferredSize();
		btnLeft.setBounds(216, 286, size1.width, size1.height);
		btnRight.setBounds(410, 286, size2.width, size2.height);
		btnStudyStart.setBounds(216, 346, size3.width, size3.height);
		btnReview.setBounds(464, 346, size5.width, size5.height);

		add(nameTag);
		add(dateTag);
		add(btnLeft);
		add(btnRight);
		add(btnStudyStart);
		add(btnReview);
		add(dayTag);
	}

	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.mainPanel(), 0, 0, this);
		setOpaque(false);

	}

	// 이름 설정 메소드
	public void nameTagSet() {
		TxtPathSet txtPathSet = new TxtPathSet();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(txtPathSet.userName()));
			String name = reader.readLine();
			nameTag.setText(name);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 날짜 설정 메소드
	public void dateTagSet() {
		TxtPathSet txtPathSet = new TxtPathSet();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(txtPathSet.startDate()));
			String date = reader.readLine();
			dateTag.setText(date);

		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}
}
