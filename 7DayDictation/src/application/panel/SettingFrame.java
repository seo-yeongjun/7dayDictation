package application.panel;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import application.EffectWavListener;
import application.ImageSet;
import application.TxtPathSet;
import application.WavPathSet;

public class SettingFrame extends JFrame {

	public SettingFrame() {
		setTitle("설정");
		setVisible(true);
		setResizable(false);
		setSize(342, 270);
		setContentPane(new SettingPanel());
	}

}

//첫 시작 프레임 속 패널
class SettingPanel extends JPanel {
	// 이미지 모음 클래스
	ImageSet imgs = new ImageSet();
	Clip clip;
	// 저장 버튼
	JButton save = new JButton(imgs.save());
	// 이름 재설정 버튼
	JButton nameReset = new JButton(imgs.nameReset());
	// 초기화 버튼
	JButton reset = new JButton(imgs.reset());
	// 미리듣기 버튼
	JButton setlisten = new JButton(imgs.setlisten());
	// 볼륨 라벨
	JTextField volume = new JTextField("100");

	public SettingPanel() {
		setLayout(null);
		EffectWavListener effectWavListener = new EffectWavListener("choose");
		// 저장 버튼 세팅
		btnSet(save);
		Dimension size1 = save.getPreferredSize();
		save.setBounds(93, 183, size1.width, size1.height);
		save.setRolloverIcon(imgs.saveRollover());
		save.addActionListener(effectWavListener);
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				TxtPathSet txtPathSet = new TxtPathSet();
				BufferedWriter volumeWriter;
				try {
					volumeWriter = new BufferedWriter(new FileWriter(txtPathSet.volume()));
					if (Integer.parseInt(volume.getText()) < 0)
						volumeWriter.write("0");
					else if (Integer.parseInt(volume.getText()) > 100)
						volumeWriter.write("100");
					else
						volumeWriter.write(volume.getText());
					volumeWriter.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				((JFrame) btn.getTopLevelAncestor()).dispose();
			}
		});

		// 이름 재설정 버튼
		btnSet(nameReset);
		Dimension size2 = nameReset.getPreferredSize();
		nameReset.setBounds(160, 38, size2.width, size2.height);
		nameReset.setRolloverIcon(imgs.nameResetRollover());
		nameReset.addActionListener(effectWavListener);
		nameReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton btn = (JButton) e.getSource();
				FirstStartFrame firstStartFrame = new FirstStartFrame();
				firstStartFrame.setTitle("이름 재설정");
				firstStartFrame.setLocation(420, 300);
				((JFrame) btn.getTopLevelAncestor()).dispose();
			}
		});

		// 리셋 버튼
		btnSet(reset);
		Dimension size5 = reset.getPreferredSize();
		reset.setBounds(20, 38, size5.width, size5.height);
		reset.setRolloverIcon(imgs.resetRollover());
		reset.addActionListener(effectWavListener);
		reset.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				TxtPathSet txtPathSet = new TxtPathSet();
				JButton btn = (JButton) e.getSource();
				int result = JOptionPane.showConfirmDialog(btn.getTopLevelAncestor(), "지금 까지의 학습내용을 모두 초기화 합니다.", "확인",
						JOptionPane.YES_NO_OPTION);
				if (result == JOptionPane.YES_OPTION) {
					BufferedWriter nameWriter;
					BufferedWriter nDayWriter;
					try {
						nameWriter = new BufferedWriter(new FileWriter(txtPathSet.userName()));
						nDayWriter = new BufferedWriter(new FileWriter(txtPathSet.nDay()));
						nDayWriter.write("0");
						nameWriter.write("");
						nameWriter.close();
						nDayWriter.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					for (int i = 1; i < 7; i++) {
						try {
							BufferedWriter myDictationWriter = new BufferedWriter(
									new FileWriter(txtPathSet.myDictation(i)));
							myDictationWriter.write("");
							myDictationWriter.close();
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				} else if (result == JOptionPane.NO_OPTION) {
				} else {
				}
			}
		});

		// 볼륨 라벨 세팅
		volume.setFont(new Font("바탕체", Font.PLAIN, 20));
		Dimension size3 = volume.getPreferredSize();
		volume.setBounds(150, 121, size3.width, size3.height);
		String volume1 = null;
		try {
			BufferedReader br = new BufferedReader(new FileReader(new TxtPathSet().volume()));
			try {
				volume1 = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		volume.setText(volume1);

		// 미리 듣기 버튼 세팅
		btnSet(setlisten);
		Dimension size4 = setlisten.getPreferredSize();
		setlisten.setBounds(180, 117, size4.width, size4.height);
		setlisten.setRolloverIcon(imgs.setlistenRollover());
		setlisten.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				WavPathSet wavPathSet = new WavPathSet();
				try {
					if (clip != null) {
						clip.stop();
					}
					clip = AudioSystem.getClip();
					File audio = wavPathSet.dictationWav(3, 3);
					AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(audio);
					clip.open(audioInputStream);
					FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
					float range = gainControl.getMaximum() - gainControl.getMinimum();
					int volume1 = Integer.parseInt(volume.getText());
					if (volume1 < 0) {
						volume1 = 0;
						volume.setText("0");
					} else if (volume1 > 100) {
						volume1 = 100;
						volume.setText("100");
					}
					float gain = (float) ((range * (Float.parseFloat(volume.getText()) / 100.0))
							+ gainControl.getMinimum());
					gainControl.setValue(gain);
					clip.start();

				} catch (LineUnavailableException e1) {
					e1.printStackTrace();
				} catch (UnsupportedAudioFileException e1) {
					e1.printStackTrace();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});

		// 버튼 추가
		add(reset);
		add(volume);
		add(nameReset);
		add(setlisten);
		add(save);
	}

	// 배경 그리기
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(imgs.settingBG(), 0, 0, this);
		setOpaque(false);
	}

	// 버튼 효과 제거 메소드
	public void btnSet(JButton btn) {
		btn.setBorderPainted(false);
		btn.setOpaque(false);
		btn.setContentAreaFilled(false);
		btn.setFocusPainted(false);
	}
}