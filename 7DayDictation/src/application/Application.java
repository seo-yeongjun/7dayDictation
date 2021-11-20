package application;

import java.lang.reflect.Field;
import java.nio.charset.Charset;

import application.nDaySet.NdaySet;
import application.panel.DictationPanel;
import application.panel.MainPanel;
import application.panel.ReDictationPanel;
import application.panel.StudyPanel;
import application.panel.StudyQuizPanel;

public class Application {
	static Main main = new Main();

	public static Main getMain() {
		return main;
	}

	public static MainPanel getMainPanel(int nDay) {
		return new MainPanel(nDay);
	}

	public static StudyQuizPanel getStudyQuizPanel(NdaySet ndaySet) {
		return new StudyQuizPanel(ndaySet);
	}

	public static StudyPanel getStudyPanel(NdaySet ndaySet) {
		return new StudyPanel(ndaySet);
	}

	public static DictationPanel getDictationPanel(NdaySet ndaySet) {
		return new DictationPanel(ndaySet);
	}
	
	public static ReDictationPanel getReDictationPanel(NdaySet ndaySet) {
		return new ReDictationPanel(ndaySet);
	}

	public static void main(String[] args) {
		  System.setProperty("file.encoding","UTF-8");
          try{
              Field charset = Charset.class.getDeclaredField("defaultCharset");
            charset.setAccessible(true);
            charset.set(null,null);
        }
        catch(Exception e){
        }
		getMain();
	}

}
