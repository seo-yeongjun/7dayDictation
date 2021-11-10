package application.listenerForPanel;

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
	JLabel nDayLabel;
	int nDay;

	public MainPanelDayListener(int day, JButton left, JButton right, JLabel dayLabel, JButton btnStudyStart,
			int nDay, JLabel nDayLabel) {
		this.day = day;
		this.left = left;
		this.right = right;
		this.dayLabel = dayLabel;
		this.btnStudyStart = btnStudyStart;
		this.nDay = nDay;
		this.nDayLabel = nDayLabel;
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
		} else if (btn.equals(btnStudyStart)) {
			if (day <= nDay + 1) {
				NdaySet ndaySet = new NdaySet(day);
				Application.getMain().setContentPane(Application.getStudyQuizPanel(ndaySet));
				Application.getMain().revalidate();
			}else {
				nDayLabel.setText(nDay+1+"일차 학습을 완료해야 "+day+"일차로 넘어갈 수 있습니다.");
				nDayLabel.setVisible(true);
			}
		}

	}

}
