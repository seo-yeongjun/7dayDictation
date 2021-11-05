package application.listenerForPanel.btn;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import application.Application;
import application.nDaySet.NdaySet;

public class MainPanelDayListener implements ActionListener {

	int day;
	JButton left;
	JButton right;
	JLabel dayLabel;
	JButton btnStudyStart;

	public MainPanelDayListener(int day, JButton left, JButton right, JLabel dayLabel, JButton btnStudyStart) {
		this.day = day;
		this.left = left;
		this.right = right;
		this.dayLabel = dayLabel;
		this.btnStudyStart = btnStudyStart;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JButton btn = (JButton) e.getSource();
		if (btn.equals(left)) {
			if (day == 1) {
				day = 7;
			} else {
				day--;
			}
			dayLabel.setText(Integer.toString(day));
		} else if (btn.equals(right)) {
			if (day == 7) {
				day = 1;
			} else {
				day++;
			}
			dayLabel.setText(Integer.toString(day));
		} else if (btn.equals(btnStudyStart)){
			NdaySet ndaySet = new NdaySet(day);
			Application.getMain().setContentPane(Application.getStudyQuizPanel(ndaySet));
			Application.getMain().revalidate();
		}

	}

}
