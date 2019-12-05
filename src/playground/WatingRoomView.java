package playground;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class WaitingRoomView extends JFrame{
	Mapmanager mm = null;
	public WaitingRoomView(Mapmanager mm) {
		this.mm = mm;
		setVisible(false);
		setTitle("Wait Playing");
		setSize(500, 500);
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		
		JLabel winlose = new JLabel("Win : " +"Lose : 0");
		JLabel gameDes = new JLabel("게임 설명~~");
		JButton startButton = new JButton("Start!");
		
		startButton.addActionListener(

				new ActionListener() {
					public void actionPerformed(ActionEvent arg0) // 확인 버튼을 누르면 맵 화면에서 전투 혹은 미니게임 화면으로 이동
					{
						
						/** * Instantiates a new launcher.*/
						gamestart();
					}
				});
		
		
		Container contentPane = getContentPane();
		
		winlose.setBounds(20, 10, 200, 70);
		gameDes.setBounds(40, 150, 250, 250);
		startButton.setSize(150, 70);
		startButton.setLocation(300, 10);
		panel.add(winlose);
		panel.add(gameDes);
		panel.add(startButton);
		contentPane.add(panel);
		
	}
	public void gamestart() {
		Thread T1 = new Thread() { // 메인 쓰레드
			@Override
			public void run() {
				int timer = 0;
				// MinigameManager mini= new MinigameManager(new map(1,"농대"));
				while (true) {
					try {
						mm.setTimer(timer++); // 딜레이로 인하여 오차가 발생하지만 게임플레이엔 지장없음
						System.out.println(timer);
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
	
	public void setPlayer(StatusManager player) {
		System.out.println(player.getStatus().name);
		mm.setPlayer(player);
	}
	
}
