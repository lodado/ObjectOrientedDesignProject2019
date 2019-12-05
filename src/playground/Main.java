package playground;

public class Main {
	public static int timer = 0;
	
	public static void main(String args[]) {
		//CharacterSelectFrame csf = new CharacterSelectFrame();
		//csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//csf.setVisible(true);
		/** The timer. */
		

		/** Map의 쓰레드를 작동시키는 T1 Thread. */
		Thread T1;

		/** 시간초를 세는 T2 Thread. */
		Thread T2 = null;
		T1 = null;
		WaitingRoomView wv = new WaitingRoomView(T2);
		CharacterSelectView cv = new CharacterSelectView(wv);
		StatusManager player = new StatusManager(cv);
		LaunchManager lm = new LaunchManager(cv);
		
		Mapmanager MapController = new Mapmanager(T1, player);
		
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
		
		//WaitPlay w = new WaitPlay();
		//w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w.setVisible(true);
	}
}
