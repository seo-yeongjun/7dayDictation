package application;

import javax.swing.JFrame;

import application.panel.Start;

public class Main extends JFrame {

	Main() {
		setTitle("7일 완성 성인 받아쓰기");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		setSize(865, 684);
		setContentPane(new Start());
		revalidate();
	}
}
