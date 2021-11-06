package application.panel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseWheelEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.plaf.basic.BasicScrollBarUI;

import application.ScrollBarUI;
import application.img.ImageSet;
import application.nDaySet.NdaySet;

public class StudyPanel extends JPanel {
	int progress = 0;
	JTextArea studyArea = new JTextArea(5, 24);
	
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();
	// day Set;
	NdaySet ndaySet;

	public StudyPanel(NdaySet ndaySet) {
		setLayout(null);
		this.ndaySet = ndaySet;
		studyArea.setFont(new Font("바탕체",Font.PLAIN,27));
		studyArea.setForeground(Color.WHITE);
		studyArea.setText(ndaySet.getStudy()[0]);
		studyArea.setEditable(false);
	
		studyArea.setOpaque(false);
		Dimension size2 = studyArea.getPreferredSize();
		studyArea.setBounds(0, 0, size2.width, size2.height);
		studyArea.setSelectionStart(0);
		studyArea.setSelectionEnd(0);
		JScrollPane scroll = new JScrollPane(studyArea);
		scroll.getViewport().setOpaque(false);
		scroll.setBackground(new Color(0x33FFFFFF,true));
		scroll.setBorder(null);
		scroll.getVerticalScrollBar().setUI(new ScrollBarUI(Color.white));
		scroll.setBounds(72,210, 720, 282);
		
		add(scroll);
	}
	// 배경 이미지 설정
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.studyPanel(), 0, 0, this);
		repaint();
	}
	
}