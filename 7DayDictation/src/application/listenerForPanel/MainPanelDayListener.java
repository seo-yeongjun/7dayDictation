package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;

import application.Application;
import application.NdaySet;

//메인 패널에서 며칠인지 고르고 해당 날짜 학습 패널 전환을 위한 리스너
public class MainPanelDayListener implements ActionListener {

	int day;
	JButton left;
	JButton right;
	JLabel dayLabel;
	JButton btnStudyStart;
	JLabel nDayLabel;
	JButton btnReview;
	int nDay;

	public MainPanelDayListener(int day, JButton left, JButton right, JLabel dayLabel, JButton btnStudyStart,
			int nDay, JLabel nDayLabel, JButton btnReview) {
		this.day = day;
		this.left = left;
		this.right = right;
		this.dayLabel = dayLabel;
		this.btnStudyStart = btnStudyStart;
		this.nDay = nDay;
		this.nDayLabel = nDayLabel;
		this.btnReview = btnReview;
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
			if (day <= nDay+1) {
				NdaySet ndaySet = new NdaySet(day);
				Application.getMain().setContentPane(Application.getStudyQuizPanel(ndaySet));
				Application.getMain().revalidate();
			}else {
				nDayLabel.setText(nDay+1+"일차 학습을 완료해야 "+day+"일차로 넘어갈 수 있습니다.");
				nDayLabel.setVisible(true);
			}
		}else if (btn.equals(btnReview)) {
			if (day <= nDay) {
			Application.getMain().setContentPane(Application.getReDictationPanel(new NdaySet(day)));
			Application.getMain().revalidate();}
			else {
				nDayLabel.setText(day+"일차 학습을 완료해야 "+day+"일차 복습을 할 수 있습니다.");
				nDayLabel.setVisible(true);
			}
		}

	}

}
