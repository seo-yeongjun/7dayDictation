package application;

import application.panel.MainPanel;

public class Application {
	static Main main = new Main();
	
	public static Main getMain() {
		return main;
	}
	public static MainPanel getMainPanel() {
		return new MainPanel();
	}
	public static void main(String[] args) {
		getMain();
	}
}
