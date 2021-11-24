package application.panel;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// 게임 프레임
public class GameFrame extends JFrame {

	public GameFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = getContentPane();
		setSize(800, 600);
		setTitle("이스터에그");
		setResizable(true); // 화면크기 바꾸기 불가
		setVisible(true);
		ProgressPanel progressPanel = new ProgressPanel();
		GamePanel gamePanel = new GamePanel(progressPanel);
		TxtPanel txtPanel = new TxtPanel(GamePanel.vector, gamePanel, progressPanel);
		c.setLayout(new BorderLayout());
		c.add(gamePanel, BorderLayout.CENTER); // GamePanel을 프레임 CENTER에 배치한다.
		c.add(txtPanel, BorderLayout.SOUTH);
		c.add(progressPanel, BorderLayout.NORTH);
		revalidate();
	}

}

class ProgressPanel extends JPanel {
	int life = 5;
	int score = 0;
	JLabel lifeLabel = new JLabel("남은 목숨 : " + life);
	JLabel scoreLabel = new JLabel("점수 : " + score);

	public ProgressPanel() {
		this.setBackground(Color.gray);
		setLayout(new FlowLayout());
		add(lifeLabel);
		add(scoreLabel);
	}
}

//게임 진행 패널
class GamePanel extends JPanel {
	// 떨어지는 라벨 저장할 벡터
	int end = 0;
	static Vector<JLabel> vector = new Vector<JLabel>();
	JTextField txtField;
	ProgressPanel progressPanel;

	public GamePanel(ProgressPanel progressPanel) {
		this.setBackground(Color.white);
		this.progressPanel = progressPanel;
		setLayout(null);
		// 쓰레드 실행
		Thread createVirusThread = new CreateVirusThread(this, vector);
		Thread fallingThread = new FallingThread(vector, this, progressPanel);
		createVirusThread.start();
		fallingThread.start();
	}

}

//입력 패널
class TxtPanel extends JPanel {
	JTextField textField = new JTextField(30);
	Vector<JLabel> vector;
	GamePanel gamePanel;
	ProgressPanel progressPanel;

	public TxtPanel(Vector<JLabel> vector, GamePanel gamePanel, ProgressPanel progressPanel) {
		this.vector = vector;
		this.setBackground(Color.gray);
		this.gamePanel = gamePanel;
		this.progressPanel = progressPanel;
		setLayout(new FlowLayout());
		add(textField);

		textField.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String text;
				text = textField.getText();
				textField.setText("");
				for (int i = 0; i < vector.size(); i++) {
					if (vector.get(i).getText().equals(text)) {
						gamePanel.remove(vector.get(i));
						gamePanel.repaint();
						vector.remove(i);
						progressPanel.score += 100;
						progressPanel.scoreLabel.setText("점수 : " + progressPanel.score);
					}
				}
			}
		});
	}
}

// 라벨 생성 쓰레드
class CreateVirusThread extends Thread {

	// 게임 진행 패널에 추가해야 되니깐 쓰레드 생성자로 게임진행 패널, 백터를 받아서 추가해준거임
	GamePanel gamePanel;
	int randomX;
	Vector<JLabel> vector;
	JLabel virus;
	private int delay = 4000;

	// 게임 진행 패널, 백터를 생성자로 전달
	CreateVirusThread(GamePanel gamePanel, Vector<JLabel> vector) {
		this.gamePanel = gamePanel;
		this.vector = vector;
	}

	@Override
	public void run() {
		Words words = new Words();
		while (gamePanel.end != 1) {
			String str = words.getRandomWord(); // 라벨 스트링
			virus = new JLabel(str);
			virus.setOpaque(false);
			randomX = (int) (Math.random() * gamePanel.getWidth());
			Dimension size1 = virus.getPreferredSize();
			virus.setBounds(randomX, 20, 100, 20);
			if (randomX + size1.getWidth() >= gamePanel.getWidth()) {
				virus.setBounds((int) (randomX - size1.getWidth()), 20, 100, 20);
			}
			vector.add(virus); // 생성된 단어레이블(바이러스) 벡터에 저장
			gamePanel.add(virus);
			gamePanel.repaint(); // 화면갱신
			delay -= 100;

			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

// 떨어지기 쓰레드
class FallingThread extends Thread {
	private long delay = 200; // 지연 시간의 초깃값 = 200
	// 라벨 빼올 백터
	Vector<JLabel> vector;
	GamePanel gamePanel;
	ProgressPanel progressPanel;

	// 벡터에서 라벨 빼내와야 되니깐 벡터를 생성자로 받아온거임
	public FallingThread(Vector<JLabel> vector, GamePanel gamePanel, ProgressPanel progressPanel) {
		this.vector = vector;
		this.gamePanel = gamePanel;
		this.progressPanel = progressPanel;
	}

	// 떨어지기 메소드
	void move(JLabel label, int i) {
		int y = label.getY() + 5;
		if (y > gamePanel.getHeight()) {
			progressPanel.life -= 1;
			vector.remove(i);
		}
		progressPanel.lifeLabel.setText("남은 목숨 : " + progressPanel.life);
		label.setBounds(label.getX(), y, label.getWidth(), label.getHeight());
	}

	@Override
	public void run() {
		while (gamePanel.end != 1) {
			// 벡터에서 바이러스 하나씩 꺼내서 아래로 이동 시키기
			for (int i = 0; i < vector.size(); i++) {
				move(vector.get(i), i); // 레이블값과 레이블의 인덱스값인 인수로 전송
			}
			if (progressPanel.life == 0) {
				gamePanel.end = 1;
			}
			try {
				sleep(delay);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (gamePanel.end == 1) {
			int end = JOptionPane.showConfirmDialog(gamePanel.getTopLevelAncestor(), "점수 :" + progressPanel.score, "확인",
					JOptionPane.CLOSED_OPTION);
			if (end == JOptionPane.CLOSED_OPTION) {
			}
		}
	}
}

class Words {
	private Vector<String> wordVector = new Vector<String>();

	public Words() {
		try {
			Scanner scanner = new Scanner(new FileReader("imEgg.txt"));
			while (scanner.hasNext()) { // 파일 끝까지 읽음
				String word = scanner.nextLine(); // 한 라인을 읽고 '\n'을 버린 나머지 문자열만 리턴
				wordVector.add(word); // 문자열을 벡터에 저장
			}
			scanner.close();
		} catch (FileNotFoundException e) {
			System.out.println("file not found error");
			System.exit(0);
		}
	}

	public String getRandomWord() { // 벡터에 저장된 단어 중 랜덤하게 하나를 반환하는 함수
		final int WORDMAX = wordVector.size(); // 총 단어의 개수
		int index = (int) (Math.random() * WORDMAX);
		return wordVector.get(index);
	}
}