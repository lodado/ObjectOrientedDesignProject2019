package minigame;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Collection;
import java.util.LinkedList;

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
import playground.GameCharacter;
/**
 * 미니게임을 관리하는 클래스.
 *
 * @author Chungheon Yi
 */

public class MinigameManager extends JFrame implements Runnable {

	private GameCharacter myMan;
	
	/** 타이머에 쓰이는 정수. */
	private int timer = 999;
	
	/**  이 매니저 관련 쓰레드. */
	private Thread myThread;
	
	/** 이 클래스의 frame. */
	private JFrame frame = new JFrame();
	
	/** 상단. */
	private JPanel top = new JPanel();
	
	/** 중간. */
	private JLabel middle = new JLabel();
	
	/** 하단. */
	private JPanel bot = new JPanel();
	
	/** 우측 상단1. */
	private JPanel topright = new JPanel();
	
	/** 우측 상단 2. */
	private JPanel topright1 = new JPanel();
	
	/** 좌측 상단 */
	private JPanel topleft = new JPanel();
	
	/** 타이머 출력용 라벨. */
	JLabel times = new JLabel(timer+"초");
	
	/** 제출 버튼 */
	private JButton bt = new JButton(new ImageIcon("./src/image/button/button1.png"));
	
	/** 텍스트 필드 */
	private JTextField jp = new JTextField(20);
	
	/** The hp */
	private JProgressBar HP = new JProgressBar(0,100);
	
	private JLabel xy = new JLabel();
	/** 클릭시 마우스 좌표 x축 */
	private int x;
	
	/** 클릭시 마우스 좌표 y축 */
	private int y;
	
	/**  게임 정답. */
	private String answer = "답";

	/**  지금 미니게임이 실행되는 맵의 장소. */
	private map currentMap;
	
	/**  게임들. 객체 생성 오버헤드를 줄이기위하여 1회만 생성하면 쭉 쓰도록 구성.*/
	private static LinkedList<Minigame> miniGames;

	/**  선택된 게임. */
	private Minigame currentMinigame;
	
	/** 맵매니저 manager. */
	private Mapmanager manager;
	/**
	 * 게임의 정답을 맞추면 true, 틀리면 false 반환, 그리고 시간초가 끝나면 false 반환.
	 * @return 게임의 정답을 맞추면 true, 틀리면 false 반환
	 */
	public boolean gameresult() {
		
		try {
			answer = answer.replaceAll(" ", "");  //제출된 문자열 빈칸제거
			currentMinigame.setAnswer(currentMinigame.getAnswer().replaceAll(" ", ""));  //정답 빈칸제거. 안전장치
		}
			catch(NullPointerException e) //그냥 제출하기 바로 누르면 에러뜨는데 이때 false 반납
		{
				return false;
		}
		
		while(!answer.isEmpty() && answer.charAt(0) == '0') answer = answer.substring(1); //000012 == 12, 0은 답사용불가
		
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
	 *  게임들 생성함, miniGames(LinkedList) 사용. 미니게임이 처음 생성되었을때 한번 생성하고 그후에는 생성하지 않고 다시씀 
	 */
	private void gamesGenerator() {
		
		bt.setBorderPainted(false);
		bt.setFocusPainted(false);
		bt.setContentAreaFilled(false); //버튼 테두리, 색칠 등 지움
		
		xy.setVisible(false); //클릭 좌표 보여주는 유무 
		
		if(miniGames == null)  // 객체 생성 시간을 아끼기 위해서 이미 만들어져 있으면 게임 생성 시간을 건너뜀
		{
		miniGames = new LinkedList<Minigame>(); //Minigame 객체 넣기 가능
		
		/*이미지 문제들*/
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game1.PNG"), "96",
				new String[]{"논리 퀴즈! ","다음을 보고 ","답을 추론해 보세요!",""}
				));	// 게임0
		miniGames.getLast().setTimer(45); //시간초 - 직관적으로 보기 힘들어서 생성자에 넣지 않고 따로 설정함
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game2.PNG"), "12",
				new String[]{"가운데에 올 수는? ","어떤 규칙으로 색칠한 세수를 이용하여 ","가운데 수를 구해 보세요!",""}
				));	// 게임1
		miniGames.getLast().setTimer(45); //시간초 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game3.PNG"), "검문소",
				new String[]{"넌센스 퀴즈 ! ","이 소의 이름은?","(3글자)",""}
				));	// 게임2
		miniGames.getLast().setTimer(55); //시간초 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game4.PNG"), "샴푸",
				new String[]{"넌센스 퀴즈 ! ","이 곰의 이름은?","(2글자)",""}
				));	// 게임3
		miniGames.getLast().setTimer(55); //시간초 
				
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game5.PNG"), "양반",
				new String[]{"넌센스 퀴즈 ! ","이 불쌍한 양의 이름은?","(2글자)",""}
				));	// 게임4
		miniGames.getLast().setTimer(55); //시간초 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/game6.PNG"), "이유식",
				new String[]{"아재 개그 ","People live in EU like eating this","(한글로 3글자)",""}
				));	// 게임5
		miniGames.getLast().setTimer(40); //시간초 
		/* 이미지 문제 끝 */
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy1.PNG"),
				new int[] {640,686,430,515}, //이건 답 사각형 좌표 (x최소,x최대,y최소,y최대)
				new String[]{"윌리를 찾아라! ","윌리(빨간 줄무늬)를 찾은후에 클릭하세요!","클릭후 제출 누르기",""}
				));	// 게임6
		miniGames.getLast().setTimer(85); //시간초 
		
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy2.jpg"),
				new int[] {0,9000,0,9000},         //이건 답 사각형 좌표 (x최소,x최대,y최소,y최대)
				new String[]{"윌리를 찾아라! ","윌리(빨간 줄무늬)를 찾은후에 클릭하세요!","클릭후 제출 누르기",""}
				));	// 게임7
		miniGames.getLast().setTimer(85); //시간초 
		
		miniGames.add(new Minigame(new ImageIcon("./src/image/minigameImage/willy3.PNG"),
				new int[] {740,775,480,533},         //이건 답 사각형 좌표 (x최소,x최대,y최소,y최대)
				new String[]{"윌리를 찾아라! ","윌리(빨간 줄무늬)를 찾은후에 클릭하세요!","클릭후 제출 누르기",""}
				));	// 게임8
		miniGames.getLast().setTimer(85); //시간초 
		
		}
		
		
		
		
		int currentgame = (int)(Math.random()*10)%(miniGames.size());
		
		currentMinigame = (miniGames.get(currentgame));
		if(currentgame<=5)
		{
		
		}	
		else
		{
			jp.setText("클릭후 제출 누르기");
			jp.setEditable(false);
			
			xy.setVisible(true); //클릭 좌표 보여줌 유무 출력
		}
		
		timer = miniGames.get(currentgame).getTimer();
		
		//currentMinigame = new Minigame(new ImageIcon("./image/minigameImage/image1.PNG"), "답",
				//new String[]{"더미 파일! ","이렇게 ","플레이","하는것 "}
				//);// 더미임!
		
		
		
		
		// Minigame(img,answer);
	}
	
	@Override
	public void run() {

		try {
			
			while (timer >= 0 && !myThread.isInterrupted()) {

				//System.out.println("minigame manager thread");
				
				if(timer<10) times.setForeground(Color.RED);
				
				
				if (!currentMinigame.getisStop()) { // 멈췄다 다시 플레이하면 재실행
					
					times.setText(timer--+" 초"); // 1초마다 타이머 1초씩 감소
					manager.setHP(myMan.getHp(),currentMap,HP);   //currentHP에 캐릭터 HP를 넣을것
					
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
	 * @param m 지금 현재 장소
	 * @param Mapmanager를 넘겨받음. association
	 */
	public MinigameManager(map m,Mapmanager Manager, GameCharacter cha) {
		
		myMan = cha; // 캐릭터 넘겨받음 
		
		JLabel characterImage;
		
		characterImage = new JLabel(new ImageIcon(myMan.getImage()));
		characterImage.setBounds(0,0,201,90);
		
		myMan.setHp(100); //나중에 지움
		
		
		
		
		frame.setContentPane(new JLabel(new ImageIcon("./src/image/mapImage/back11.jpg")));
		manager = Manager;
		currentMap = m;
		
		final int ROW = 920; // The row. 	 	
		final int COL = 920; // The col.
		
		
		setThread(new Thread(this));
		gamesGenerator();
			
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;


		frame.setTitle("전대 그라운드 - Minigame"); //타이틀 설정 
		frame.setLayout(null); //레이아웃 설정

		try {
		
		JLabel img = new JLabel(currentMap.getIconImage());
		img.setBounds(2,40,196,178);
		frame.add(img); //좌측 상단 맵 이미지 삽입
		middle = new JLabel(currentMinigame.getImage());
		
		}
		catch(NullPointerException e)
		{
			e.printStackTrace();
		}
		
		JLabel timelimit = new JLabel("남은 시간");
		
		timelimit.setBounds(815,0,70,70);
		frame.add(timelimit);
		times.setBounds(815,120,70,70);
		frame.add(times);	
		times.setVisible(false);        //times가 확인을 누르면 보이게함
	
		HP.setStringPainted(true);
		if(currentMap.getFlag()) HP.setForeground(Color.RED);
		else  HP.setForeground(Color.MAGENTA);
		
		HP.setBounds(569,194,201,24);
		HP.setValue(myMan.getHp()); // 여기에 Character HP 대입
		HP.setStringPainted(true);
		frame.add(HP); //show progressbar
		
		JLabel showhp = new JLabel("HP :");
		showhp.setBounds(535,195,30,20); 
		frame.add(showhp); //HP : 상단에 add
		
		JLabel nameofMap = new JLabel(currentMap.getMapName());
		nameofMap.setBounds(55,0,120,60);
		frame.add(nameofMap);
		
		JLabel[] howtoplay = new JLabel[5]; //지금 미니게임에 대한 설명,
		// howtoplay[0]은 미니게임 이름임, howtoplay[4]는 "space바를 누르면 중단" 출력
		
		/*JLabel how = new JLabel("게임 설명 "); // the title
		
		how.setBounds(220,20,100,25);
		frame.add(how); // 게임설명 상단에 삽입
		*/
		
		
		
		howtoplay[0] = new JLabel(currentMinigame.getList(0));  
		howtoplay[0].setBounds(220,50,250,25);
		
		howtoplay[4]  = new JLabel("게임 잠시 중단은 맵 그림을 누르세요.");		
		frame.add(howtoplay[0]);
		for(int i=1; i<5; i++)
		{
			if(i!=4)	howtoplay[i] = new JLabel(currentMinigame.getList(i));  
			howtoplay[i].setBounds(220,70+i*20,250,25);
			howtoplay[i].setFont(new Font("Serif",Font.ITALIC,13));
			frame.add(howtoplay[i]);
		}
		
		bot.add(new JLabel("정답"));
		bot.add(jp);
		bot.add(bt, BorderLayout.LINE_END);
	
		/*폰트*/
		times.setFont(new Font("Serif",Font.ITALIC,20));
		jp.setFont(new Font("Serif",Font.ITALIC,18));
		nameofMap.setFont(new Font("Serif",Font.PLAIN,24));
		
		
		
		top.setBounds(0,48, 200, 172);
		topright.setBounds(770, 0, 200, 100);
		topright1.setBounds(770,100,200,120);
		topleft.setBounds(200,0,600,220);
		middle.setBounds(0, 220, 920, 500);
		bot.setBounds(240, 800, 450, 300);
		
		bot.setBackground(new Color(255,182,193));
		top.setBackground(Color.WHITE);
		topright.setBackground(new Color(255,182,193));
		topleft.setBackground(new Color(255,182,193));
		topright1.setBackground(Color.WHITE);
		frame.setBounds(280,700,300,300);
		
		
		top.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topleft.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		topright1.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		//middle.setBorder(new TitledBorder(new LineBorder(Color.black,1)));
		
		
		frame.add(top);
		frame.add(topright);
		frame.add(topright1);
		frame.add(topleft);
		frame.add(bot,0);
		frame.add(middle);	
		
		xy.setBounds(800,850,200,30);
		frame.add(xy); //클릭 미니게임에서 좌표를 보여줌
		
		middle.setVisible(false);//확인을 눌러야 미니게임이 보임 
		frame.setSize(ROW, COL);
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));//전대 로고
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		
		
		int result =  JOptionPane.showConfirmDialog(null,
				 currentMinigame.getList(1)+"\n"+
				 currentMinigame.getList(2)+"\n"+
				 currentMinigame.getList(3)+"\n",
				 "미니게임 - "+currentMinigame.getList(0)
				 ,JOptionPane.CLOSED_OPTION);
			
			if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {	
				middle.setVisible(true);
				times.setVisible(true); //확인을 눌러야 timer가 보임 
				myThread.start();
			
			}
		
		//마우스 클릭을 인식하는 addMouseListener
          frame.addMouseListener(new MouseAdapter(){
          
        	  @Override
        	  public void mousePressed(MouseEvent e)
        	  {
        		   x = e.getX();
        		   y = e.getY();
        		   
        		   currentMinigame.setXY(x, y); //클릭 쓸경우 답 유무 판정
        		   xy.setText("좌표 : ("+currentMinigame.getX()+","+currentMinigame.getY()+")");//좌표출력
        		 
        		  
        		  if(x>=2 && x<=198 && y>=70 && y<=246) // 좌측 상단 그림을 누르면 pause되게 설정 , 맵 name이 그림을 약간 미는걸로 보임
        			 {
        			  
        			  if(!currentMinigame.getisStop())
        			  {
        				  if(currentMap.getFlag())
        				  {
        					  currentMinigame.setisStop(!currentMinigame.getisStop());
        		        		 middle.setVisible(!currentMinigame.getisStop());  //그림 누르면 pause 되고 미니게임이 안보임
        		        		 int result = JOptionPane.showConfirmDialog(null,
        		        				"팝업을 닫으면 재시작 합니다.",
        		        				 "PAUSE - "+ currentMinigame.getList(0)
        		        				 ,JOptionPane.CLOSED_OPTION);

        		 				if (result == JOptionPane.OK_OPTION || result == JOptionPane.CLOSED_OPTION) {
        		 					currentMinigame.setisStop(!currentMinigame.getisStop());
        		 	        		middle.setVisible(!currentMinigame.getisStop());
        		 						} //팝업 제거시 재시작
        				  }
        				  else
            			  {
            				  JOptionPane.showConfirmDialog(null, "자기장에 튀겨지고 있는동안에는\nPause할수 없습니다.", "XD", JOptionPane.CLOSED_OPTION);
            			  }
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
