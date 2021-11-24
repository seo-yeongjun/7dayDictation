package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.nDaySet.NdaySet;

//MainFame의 패널을 받아쓰기로 변경하는 리스너
public class DictationListener implements ActionListener {
	NdaySet ndaySet;
	public DictationListener(NdaySet ndaySet) {
		this.ndaySet=ndaySet;
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		Application.getMain().setContentPane(Application.getDictationPanel(ndaySet));
		Application.getMain().revalidate();
	}
}