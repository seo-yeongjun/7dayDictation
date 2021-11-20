package application;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class EffectWavListener implements ActionListener {

	WavPathSet wavPathSet = new WavPathSet();
	Clip clip;

	public EffectWavListener(String str) {

		String volume = "100";
		try {
			BufferedReader br = new BufferedReader(new FileReader(new TxtPathSet().volume()));
			try {
				volume = br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			clip = AudioSystem.getClip();
			File audio = wavPathSet.effect(str);
			AudioInputStream audioStream = null;
			try {
				audioStream = AudioSystem.getAudioInputStream(audio);
			} catch (UnsupportedAudioFileException e) {
				e.printStackTrace();
			}
			clip.open(audioStream);
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		float range = gainControl.getMaximum() - gainControl.getMinimum();
		float gain = (float) ((range * (Float.parseFloat(volume) / 100.0)) + gainControl.getMinimum());
		gainControl.setValue(gain);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}

	public void run() {
		clip.stop();
		clip.setFramePosition(0);
		clip.start();
	}

}
