package application;

import javax.swing.JFrame;

import application.panel.Start;

public class Main extends JFrame {

	Main() {
		setTitle("7일 완성 성인 받아쓰기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(true);
		setSize(865, 644);
		setContentPane(new Start());
		revalidate();
	}
}
