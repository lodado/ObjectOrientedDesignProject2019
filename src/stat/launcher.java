package stat;


import Map.Mapmanager;
/**
 * 두개의 쓰레드를 작동시키는 launcher class. 하나는 총 플레이 시간 재기 및 객체. 하나는 여분
 * @author Chungheon Yi
 */
public class launcher{

	/** The timer. */
	public int timer = 0 ;	
	
	/** Map의 쓰레드를 작동시키는 T1 Thread. */
	Thread T1;
	
	/** launcher가 소유하는 MapController. */
	Mapmanager MapController;
	
	
	public void gameStart()
	{
		
		T1 = null; // Mapmanager가 쓸 Thread	
		MapController = new Mapmanager(T1);
		
		Thread T2 = new Thread(){	 //메인 쓰레드
			@Override
			public void run(){
				
					//MinigameManager mini= new MinigameManager(new map(1,"농대"));
				while(true)
				{
				
				try {
						MapController.setTimer(timer++); //딜레이로 인하여 오차가 발생하지만 게임플레이엔 지장없음
						System.out.println(timer);
						Thread.sleep(1000);
					}
				catch(InterruptedException e)
				{
					break; //인터럽트 캐치
				}
				catch(Exception e)
				{
					e.printStackTrace(); //오류 캐치
				}
			}
			}		
		};
		T2.start();

	}
	
	/**
	 * Instantiates a new launcher.
	 */
	public launcher()
	{
		gameStart();

		
	}
}
