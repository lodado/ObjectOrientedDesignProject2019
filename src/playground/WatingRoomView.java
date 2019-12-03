package playground;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Map.Mapmanager;

class WaitingRoomView extends JFrame{
	
	/** Map의 쓰레드를 작동시키는 T1 Thread. */
	Thread T1;

	/** 시간초를 세는 T2 Thread. */
	Thread T2;
	
	/** The timer. */
	public int timer = 0;

	/** launcher가 소유하는 MapController. */
	Mapmanager MapController;
	
	public WaitingRoomView() {
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
						gameStart();
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
	

	/**
	 * Game start.
	 * 
	 * @author ChungHeon Yi
	 */
	public void gameStart() {

		
		
		T1 = null; // Mapmanager가 쓸 Thread
		MapController = new Mapmanager(T1);

		T2 = new Thread() { // 메인 쓰레드
			@Override
			public void run() {

				// MinigameManager mini= new MinigameManager(new map(1,"농대"));
				while (true) {

					try {
						MapController.setTimer(timer++); // 딜레이로 인하여 오차가 발생하지만 게임플레이엔 지장없음
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
		T2.start();

	}
	
	
}
