package application;

import java.io.File;

public class WavPathSet {
	public File dictationWav(int day, int i) {
		return new File(Main.class.getResource("/wav/day" + day + "_" + i + ".wav").getFile());
	}

	public File effect(String str) {
		return new File(Main.class.getResource("/wav/"+str+".wav").getFile());
	}

}
