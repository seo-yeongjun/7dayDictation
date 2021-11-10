package application.listenerForPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import application.Application;
import application.nDaySet.NdaySet;

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