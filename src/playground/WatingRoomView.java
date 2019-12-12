package playground;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Map.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**대기실 뷰*/
class WaitingRoomView extends JFrame implements ActionListener{
	/**사용자 정보*/
	UserInfo user;
	/**플레이어 정보*/
	StatusManager player;
	/**이긴 횟수, 진 횟수 표시*/
	JLabel winlose = new JLabel("");
	/**게임 설명*/
	JLabel gameDes = new JLabel("게임 설명~~");
	JButton startButton = new JButton("Start!");
	JButton scoreButton = new JButton("Score");
	
	/**대기실 뷰 설정
	 * @param user - 사용자 정보
	 * @param player - 플레이어 정보*/
	public WaitingRoomView(UserInfo user, StatusManager player) {
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		setLocation(screenWidth / 4, screenHeight / 10);
		this.user = user;
		this.player = player;
		winlose.setText("Win :  " + user.getWin() +"  Lose :  " + user.getLose());
		setVisible(false);
		setTitle("Wait Playing");
		setSize(500, 500);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		startButton.addActionListener(this);
		scoreButton.addActionListener(this);
		
		Container contentPane = getContentPane();
		
		winlose.setBounds(20, 10, 200, 70);
		gameDes.setBounds(40, 150, 250, 250);
		startButton.setSize(70, 70);
		startButton.setLocation(300, 10);
		scoreButton.setSize(70, 70);
		scoreButton.setLocation(200, 10);
		panel.add(winlose);
		panel.add(gameDes);
		panel.add(startButton);
		panel.add(scoreButton);
		contentPane.add(panel);
		setVisible(true);
	}
	
	/**시작 버튼이 눌리면 게임을 시작하고 스코어 버튼이 눌리면 점수 목록을 표시해준다.*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(startButton)) {
			System.out.println("시작 버튼 눌림");
			gamestart();
			
		}
		else if (e.getSource().equals(scoreButton)) {
			ScoreView sv = new ScoreView(user);
		}
		
	}
	
	/**Mapmanager를 생성하고 게임을 시작한다*/
	public void gamestart() {
	
		final Mapmanager Mapcontroller = new Mapmanager(user, player);
		Thread T1 = new Thread() { // 메인 쓰레드
			@Override
			public void run() {
				int timer = 0;
				// MinigameManager mini= new MinigameManager(new map(1,"농대"));
				while (true) {
					try {
						Mapcontroller.setTimer(timer++); // 딜레이로 인하여 오차가 발생하지만 게임플레이엔 지장없음
						
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						break; // 인터럽트 캐치
					} catch (Exception e) {
						e.printStackTrace(); // 오류 캐치
					}
				}
			}
		};
		T1.start();
		
	}
	
}
