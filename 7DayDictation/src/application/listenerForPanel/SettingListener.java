package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.panel.SettingFrame;

public class SettingListener implements ActionListener {
	public void actionPerformed(ActionEvent e) {
		SettingFrame firstStartFrame = new SettingFrame();
		firstStartFrame.setLocation(420, 300);
	}
}
