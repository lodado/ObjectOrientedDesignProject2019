package minigame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import Map.Mapmanager;
import Map.map;
/**
 * 미니게임을 관리하는 클래스.
 *
 * @author Chungheon Yi
 */

public class MinigameManager extends JFrame implements Runnable {

	
	/** 타이머에 쓰이는 정수. */
	private int timer = 150;
	
	/**  이 매니저 관련 쓰레드. */
	private Thread myThread;
	
	/** 이 클래스의 frame. */
	private JFrame frame = new JFrame();
	
	/** 상단. */
	private JPanel top = new JPanel();
	
	/** 중간. */
	private JPanel middle = new JPanel();
	
	/** 하단. */
	private JPanel bot = new JPanel();
	
	/** 우측 상단1. */
	private JPanel topright = new JPanel();
	
	/** 우측 상단 2. */
	private JPanel topright1 = new JPanel();
	
	/** 좌측 상단 */
	private JPanel topleft = new JPanel();
	
	/** 타이머 출력용 라벨. */
	JLabel times = new JLabel(timer +" 초");

	/** 제출 버튼 */
	private JButton bt = new JButton("제출");
	
	/** 텍스트 필드 */
	private JTextField jp = new JTextField(20);
	
	/** The hp */
	private JProgressBar HP = new JProgressBar(0,100);

	/** 클릭시 마우스 좌표 x축 */
	private int x;
	
	/** 클릭시 마우스 좌표 y축 */
	private int y;
	
	/**  게임 정답. */
	private String answer = "답";

	/**  장소 출력. */
	private map currentMap;
	
	/**  게임들. */
	public static Minigame mini[];

	/**  선택된 게임. */
	private Minigame currentMinigame;
	
	private Mapmanager manager;
	/**
	 * 게임의 정답을 맞추면 true, 틀리면 false 반환, 그리고 시간초가 끝나면 false 반환.
	 * @return 게임의 정답을 맞추면 true, 틀리면 false 반환
	 */
	public boolean gameresult() {
		
		if(currentMinigame instanceof Minigame) { //클릭 유무에 따라 쓰는 클래스가 달라짐
		answer.replaceAll(" ", "");  //제출된 문자열 빈칸제거
		currentMinigame.setAnswer(currentMinigame.getAnswer().replaceAll(" ", ""));  //정답 빈칸제거. 안전장치

		if (timer <= 0) //시간제한 
			return false;

			try {
				if (answer.equals(currentMinigame.getAnswer())) // 똑같으면 정답 아니면 오답
					return true;
				else
					return false;
			} catch (NullPointerException e) // 보통 상황엔 거의 안뜸
			{
				e.printStackTrace(); //에러 호출
				return false;
			}
		}
		else
		{
			return false; //다른거
		}
	}

	/**
	 *  이 클래스의 쓰레드를 세팅하는 메소드.
	 * @param th the new thread
	 */
	public void setThread(Thread th) {
		this.myThread = th;
	}

	/**
	 *  이 클래스의 Thread를 반환하는 메소드.
	 * @return the thread
	 */
	public Thread getThread() {
		return this.myThread;
	}

	/**
	 *  게임들 생성함, mini[] 사용.
	 */
	private void gamesGenerator() {

		currentMinigame = new Minigame(new ImageIcon("image1.PNG"), "답",
				new String[]{"더미 파일! ","이렇게 ","플레이","하는것 "}
				);// 더미임!

		
		// Minigame(img,answer);
	}
	
	@Override
	public void run() {

		try {
			
			int count = 0; //count
			int currentHP = 89;  //Character의 HP를 여기다 대입
			
			int arr[] = new int[] {count,currentHP};  //앞은 count=0, 뒤는 chracter HP를 넣을것

			while (timer >= 0 && !myThread.isInterrupted()) {

				System.out.println("minigame manager thread");
				
	
				if (!currentMinigame.getisStop()) { // 멈췄다 다시 플레이하면 재실행
					times.setText(timer--+" 초"); // 1초마다 타이머 1초씩 감소
					manager.IsClosedMap(arr,HP,1);	
				}
				
				
				
				Thread.sleep(1000); // 1초씩 sleep. 딜레이때문에 정확하진 않지만 게임 진행엔 큰 무리가 없음
			}
		} catch (InterruptedException e) {
			
			// Interrupted catch - while문 종료

		} catch (Exception e) {
			e.printStackTrace(); // 그외 오류면 출력
		}
		
		if(timer<=0)
		{
			bt.doClick(); //타이머가 끝나면 강제로 누름
		}
		
	}
	
	  
	
	/**
	 * 미니게임을 생성하고, 관리하는 매니저.
	 *
	 * @param map
	 */
	public MinigameManager(map m,Mapmanager Manager) {
		
		
		manager = Manager;
		
		final int ROW = 1000; // The row. 	 	
		final int COL = 900; // The col.
		
		
		setThread(new Thread(this));
		gamesGenerator();
			
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		currentMap = m;

		frame.setTitle("전대 그라운드 - Minigame"); //타이틀 설정 
		frame.setLayout(null); //레이아웃 설정

		JLabel img = new JLabel(m.getMapImage());
		img.setBounds(2,40,196,178);
		frame.add(img); //좌측 상단 맵 이미지 삽입
		
		JLabel img1 = new JLabel(currentMinigame.getImage());
		middle.add(img1); // 미니퀴즈 삽입
		
		
		JLabel timelimit = new JLabel("남은 시간");
		
		timelimit.setBounds(870,10,70,70);
		frame.add(timelimit);
		times.setBounds(870,120,70,70);
		frame.add(times);	
		
	
		HP.setStringPainted(true);
		if(m.getFlag()) HP.setForeground(Color.RED);
		else  HP.setForeground(Color.MAGENTA);
		HP.setBounds(597,194,201,24);
		HP.setValue(74); // 여기에 Character HP 대입
		HP.setStringPainted(true);
		frame.add(HP); //show progressbar
		
		JLabel nameofMap = new JLabel(m.getMapName());
		nameofMap.setBounds(85,0,60,60);
		frame.add(nameofMap);
		
		JLabel[] howtoplay = new JLabel[5]; //지금 미니게임에 대한 설명,
		// howtoplay[0]은 미니게임 이름임, howtoplay[4]는 "space바를 누르면 중단" 출력
		JLabel how = new JLabel("게임 설명 "); // the title
		
		how.setBounds(220,20,100,25);
		frame.add(how); // 게임설명 상단에 삽입
		
		JLabel showhp = new JLabel("HP :");
		showhp.setBounds(560,195,30,20); 
		frame.add(showhp); //HP : 상단에 add
		
		howtoplay[0] = new JLabel(currentMinigame.getList(0));  
		howtoplay[0].setBounds(220,50,100,25);
		
		howtoplay[4]  = new JLabel("게임 잠시 중단은 맵 그림을 누르세요.");		
		frame.add(howtoplay[0]);
		for(int i=1; i<5; i++)
		{
			if(i!=4)	howtoplay[i] = new JLabel(currentMinigame.getList(i));  
			howtoplay[i].setBounds(220,70+i*20,250,25);
			frame.add(howtoplay[i]);
		}
		
		bot.add(new JLabel("정답"));
		bot.add(jp);
		bot.add(bt, BorderLayout.LINE_END);
		
		top.setBounds(0, 0, 200, 220);
		topright.setBounds(800, 0, 195, 100);
		topright1.setBounds(800,100,195,120);
		topleft.setBounds(200,0,600,220);
		middle.setBounds(0, 300, 900, 500);
		bot.setBounds(0, 800, 1000, 300);
		
		top.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topleft.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright1.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		
		
		frame.add(top);
		frame.add(topright);
		frame.add(topright1);
		frame.add(topleft);
		frame.add(bot);
		
		frame.setSize(ROW, COL);

		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		int result = JOptionPane.showConfirmDialog(null, "미니게임 시작 !\n 게임 방법은 상단을 확인하세요.\n"
				+ "확인을 누르면 시작합니다.","확인", JOptionPane.CLOSED_OPTION);
			
			if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {	
				frame.add(middle);	//확인을 눌러야 미니게임이 보임 
				myThread.start();
			
			}
		
		//마우스 클릭을 인식하는 addMouseListener
          frame.addMouseListener(new MouseAdapter(){
          
        	  @Override
        	  public void mousePressed(MouseEvent e)
        	  {
        		   x = e.getX();
        		   y = e.getY();
        		   
        		   currentMinigame.setXY(x, y);
        		  
        		   System.out.println(x+" "+y);
        		  
        		  if(x>=2 && x<=198 && y>=70 && y<=246) // 좌측 상단 그림을 누르면 pause되게 설정 , 맵 name이 그림을 약간 미는걸로 보임
        			 {
        		 currentMinigame.setisStop(!currentMinigame.getisStop());
        		 middle.setVisible(!currentMinigame.getisStop());  //그림 누르면 pause 되고 미니게임이 안보임
        		 int result = JOptionPane.showConfirmDialog(null, "PAUSE", "재실행", JOptionPane.CLOSED_OPTION);

 				if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
 					currentMinigame.setisStop(!currentMinigame.getisStop());
 	        		middle.setVisible(!currentMinigame.getisStop());
 					}
        		} //팝업 확인 혹은 취소 누르면 재실행
        		  
        						 
        	  }
          }
		);
         
			
		bt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String ans;

				answer = jp.getText(); // 입력받은 텍스트 저장
				
				myThread.interrupt();
				
				if (gameresult())
					ans = "미니게임 성공 ! 아이템을 확득합니다.";
				else
					ans = "미니게임 실패 ! \n아이템을 확득하지 못하였습니다.";

				int result = JOptionPane.showConfirmDialog(null, ans, "확인", JOptionPane.CLOSED_OPTION);

				if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {

					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame.setVisible(false);
					
					
					/**/
					manager.getMapFrame().setVisible(true);
					manager.getMapFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
					/**/
					
				
					
					// 나중 아이 매니저 실행
				}

			}
		});

	}

}
