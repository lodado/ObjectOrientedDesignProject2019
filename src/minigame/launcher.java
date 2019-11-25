package minigame;

import Map.Mapmanager;

// TODO: Auto-generated Javadoc
/**
 * 두개의 쓰레드를 작동시키는 launcher class. 하나는 총 플레이 시간 재기 및 객체. 하나는 여분
 * @author Chungheon Yi
 */
public class launcher implements Runnable{

	/** The timer. */
	public int timer = 0 ;	
	public boolean flag1 = true ,flag2 = true;
	
	@Override
	public void run()
	{
		Mapmanager J = new Mapmanager();
		
		Thread T1 = new Thread(){	 //메인 쓰레드
			@Override
			public void run(){
				
					//MinigameManager mini= new MinigameManager(new map(1,"농대"));
				while(flag1)
				{
				
				try {
						J.setTimer(timer++); //딜레이로 인하여 오차가 발생하지만 게임플레이엔 지장없음
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
		
		Thread T2 = new Thread()  //여분 쓰레드 
				{		
					@Override 
					public void run() {
						
						
							while(flag2) {
						try
						{
							Thread.sleep(1000);
						}
							catch(Exception e)
						{
								break; //오류 캐치
						}
							}
						}
					};
		
		T1.start();
		//T2.start();
		
	}
	
	/**
	 * Instantiates a new launcher.
	 */
	public launcher()
	{
		Thread T = new Thread(this);
		
		T.run();
		
	}
}
