package application.listener.btn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

public class MainPanelDayListener implements ActionListener {

	int day;
	JButton left;
	JButton right;
	JLabel dayLabel;

	public MainPanelDayListener(int day, JButton left, JButton right, JLabel dayLabel) {
		this.day = day;
		this.left = left;
		this.right = right;
		this.dayLabel = dayLabel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.equals(left)) {
			if (day == 0) {
				day = 7;
			} else {
				day--;
			}
		} else {
			if (day == 7) {
				day = 1;
			} else {
				day++;
			}
		}
		dayLabel.setText(Integer.toString(day));
	}

}
