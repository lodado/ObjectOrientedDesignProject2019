/*
 * 
 */
package Map;

import java.awt.BorderLayout;
import Fight.*;
import AI.*;
import Item.Itemview;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import minigame.*;
import playground.*;

/**
  * The Class Mapmanager.
  *  @author Chungheon Yi
  */
public class Mapmanager extends JFrame implements Runnable {
	
	/**  my Character stat. */
	private StatusManager player;
	
	/**  The userInfo. */
	private UserInfo user;
	
	/** The pointer. */
	private GameCharacter pointer;
	
	/** AI manager. */
	private AIManager AI;
	
	/**  The hp. */
	private JProgressBar HP = new JProgressBar(0, 100);

	/** The my location. */
	private int myLocation = 0; // 자신의 위치숫자로 구별

	/** nameofMaps */
	private String mapsName[] = { "농대", "공대", "자연대", "사회대", "봉지","인문대", "경영대", "용지", "예대" };

	/**  timer */
	private int timer = 0; //

	/**  map total 9  */
	private map m[] = new map[9];
	
	/**  ping where i am*/
	JLabel ping = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/ping.png"))); // 자기위치 가리킴

	/**  maps name text. */
	JLabel text[] = new JLabel[9];

	/**  button total 9 for map */
	private JButton[] b = new JButton[9];

	/**  Thread to control map. */
	private Thread myThread;
	
	/**  image of Character. */
	private JLabel characterImage;
	
	/** this class's Frame. */
	private JFrame frame = new JFrame();

	/**  Panel top */
	private JPanel top = new JPanel();

	/**  print hp */
	private JLabel forHp = new JLabel(" HP :");

	/**  print def */
	private JLabel forDef;

	/**  print my location */
	private JLabel forLoc = new JLabel(" 현재 위치 :   " + mapsName[myLocation]);

	/**  print total playtime */
	private JLabel Mytime = new JLabel("                   " + timer);

	/** LinkedList to know this place is critical field or not */
	LinkedList<Integer> list;

	/**
	 * inner class to move place to another place 
	 * @author Chungheon Yi
	 */
	private class MapLocationPopup extends JDialog {

		/**  move button . */
		private JButton Bt1 = new JButton(new ImageIcon(getClass().getClassLoader().getResource(("image/button/buttonYES.png"))));

		/**  cancel button. */
		private JButton Bt2 = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/button/buttonNO.png")));

		/**  bottom panel. */
		private JPanel bottom = new JPanel();

		/**  top panel. */
		private JPanel top = new JPanel();

		/**
		 * moving method, fight or start minigame.
		 * @param mv get map instance
		 */
		public void moving(map mv) {
			myLocation = mv.getLoc(); // 자신의 위치 이곳으로 이동
			forLoc.setText(" 현재 위치 :   " + mapsName[myLocation]);

			ping.setBounds(50 + 280 * (myLocation % 3), 200 + 250 * (myLocation / 3), 230, 920 - 770);
			if (mv.getAINumber() <= 0) // start minigame
			{
				new MinigameManager(mv, Mapmanager.this,player.getStatus());
			} else {
					
					pointer = m[myLocation].getAI().get((int)Math.random()*m[myLocation].getAINumber());
				new FightManager(player.getStatus(), pointer,Mapmanager.this,m[myLocation],AI.getList(), user);// fight manager();
			}

		}

		/**
		 * select move or cancel 
		 * @param thisFrameList to control JFrame
		 * @param M   Map m
		 * @param num location
		 */
		public MapLocationPopup(final LinkedList<JFrame> thisFrameList, map M, int num) {
			JFrame thisframe = new JFrame();

			while (!thisFrameList.isEmpty()) {
				thisFrameList.peekFirst().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				thisFrameList.pollFirst().setVisible(false);
			} 
			
			thisFrameList.add(thisframe); // 이 frame도 스텍에 담아둠
			
			
			Bt1.setBorderPainted(false);
			Bt1.setFocusPainted(false);
			Bt1.setContentAreaFilled(false); // 버튼 테두리, 색칠 등 지움

			Bt2.setBorderPainted(false);
			Bt2.setFocusPainted(false);
			Bt2.setContentAreaFilled(false); // 버튼 테두리, 색칠 등 지움

			Toolkit tk = Toolkit.getDefaultToolkit();
			Dimension screenSize = tk.getScreenSize();
			int screenHeight = screenSize.height;
			int screenWidth = screenSize.width;
			
			thisframe.setTitle(M.getMapName() + "로 이동?");
			
			thisframe.setIconImage(Toolkit.getDefaultToolkit().getImage(("image/chonnam.png")));// 전대 로고
			Bt1.addActionListener(

					new ActionListener() {
						public void actionPerformed(ActionEvent arg0) // 확인 버튼을 누르면 맵 화면에서 전투 혹은 미니게임 화면으로 이동
						{
							frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
							frame.setVisible(false);

							while (!thisFrameList.isEmpty()) {
								thisFrameList.peekFirst().setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
								thisFrameList.pollFirst().setVisible(false);
							} // 이게 없으면 팝업이 중복되는 문제가 생겨서 만든 코드입니다..확인을 누를시
								// 모든 팝업의 frame을 없애줍니다

							moving(m[num]);
						}
					});

			Bt2.addActionListener(new ActionListener() // 취소 버튼을 눌렀을때 맵으로 돌아감
			{
				public void actionPerformed(ActionEvent arg0) {

					thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					thisframe.setVisible(false);

				}
			});

			thisframe.add(Bt1);
			thisframe.add(Bt2);

			Bt1.setBounds(100, 300, 200, 100);
			Bt2.setBounds(400, 300, 200, 100);
			thisframe.setContentPane(new JLabel(M.getMapImage()));
			add(top);
			thisframe.add(Bt1);
			thisframe.add(Bt2);
			thisframe.add(bottom);

			setResizable(false);
			thisframe.setLocation(screenWidth / 3, screenHeight / 7);
			thisframe.setSize(720, 480);
			thisframe.setVisible(true);

			thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}
		// int result = JOptionPane.showConfirmDialog(null, "계속할 것입니까?")
	}

	/**
	 * Gets the my location.
	 * @return m[myLocation]
	 */
	public map getMyLoc() {
		return m[myLocation];
	}

	/**
	 * Sets the my location.
	 *
	 * @param num newLocation
	 */
	public void setMyLoc(int num) {
		this.myLocation = num;
	}

	/**
	 * Sets the thread.
	 * @param T1 the new thread
	 */
	void setThread(Thread T1) {
		T1 = new Thread(this);
	}

	/**
	 * Gets the thread.
	 * @return myThread
	 */
	public Thread getThread() {
		return this.myThread;
	}

	/**
	 * Sets the timer.
	 * @param time the new timer
	 */
	public void setTimer(final int time) {
		this.timer = time;
	}

	/**
	 * Gets the timer.
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * Gets the map frame.
	 * @return the map frame
	 */
	public JFrame getMapFrame() {
		return frame;
	}

	/**
	 * Sets the map frame.

	 * @param frame the new map frame
	 */
	public void setMapFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Gets the pointer.
	 * point AI who fights with me
	 * @return pointer
	 */
	public GameCharacter getPointer()
	{
		return pointer;
	}
	
	/**
	 * Sets the pointer
	 * Point AI who fights with me
	 * @param pt the new pointer
	 */
	public void setPointer(GameCharacter pt)
	{
	  pointer = pt;
	}
	
	/**
	 * Sets the hp bar. use in map, fight, minigame
	 *
	 * @param CharHP the char HP
	 * @param m map
	 * @param HPBar the JPrgressBar
	 */
	public void setHP(int CharHP, map m, JProgressBar HPBar) {
		if (!m.getFlag())
			HPBar.setForeground(Color.magenta); // 자기장 안에 있다고 표시해줌
		else
			HPBar.setForeground(Color.RED); // 보통 상태

		HPBar.setValue(CharHP); // 체력 set
	}

	/**
	 * (private) player gets damaged when he enters harmful field
	 *
	 * @param count       the count. i used this to use reference in Cplusplus               
	 * @param m the map
	 * @param Threadspeed 1000 = 1 250 = 4 500 = 2 100 = 10
	 * @param gamer AI who fights against me
	 */
	private void IsClosedMap(int count[], map m, double Threadspeed, GameCharacter gamer) //
	{
		// count = a[0];
		if(gamer == null) return; //상대가 없으면 그냥 메소드를 끝냄 
		
		if(gamer.getHp()<5) return; // 체력 5 이하로는 안떨어짐
		
		if (!m.getFlag()) // flag가 0이면 맵이 닫겨있는것이고 1이면 체력 안깎임
		{
			if (count[0]++ > 4 * Threadspeed) { // 만약 닫힌곳에 들어가 있다면 4*Threadspeed초 이후에 체력이 1 깎임

				count[0] = 0;
				gamer.setHp(gamer.getHp()-1);
				
			}
		}
	}
	
	@Override
	public void run() {
		try {
			int whattime = 0; // 시간 launcher timer와 상관 없이 따로재는 타이머
			boolean notify = true; // notify = 미리 알람용 boolean
			
			int[] count1 = new int[] { 0 }; // C++의 참조(&) 구현, 수정하다보니 굳이 참조를 쓸 필요가 없게 되었지만 그냥 놔둠
			int[] count2 = new int[] { 0 };
			while (true) {
				
				// System.out.println(timer+"변경 - mapmanager"); //확인용 print
				Mytime.setText("                   " + timer); // 시간초 계속 갱신

				IsClosedMap(count1, m[myLocation], (double) 10,	player.getStatus()); // count = 참조를 통한 인자 변경(C++의 &)을 위한 배열,
				
				IsClosedMap(count2, m[myLocation], (double)10, pointer);
				// HP는 HP바, 쓰레드 speed(500millsec*10)
				setHP(player.getStatus().getHp(), m[myLocation], HP); // 현재 HP에 characterHP 대입. 이부분은 나중 character와 연동할것
				if (!list.isEmpty()) // 모두 닫겼다면 실행 하지 않음
				{
					
					if (notify && timer - whattime > 60) // 대략 60초후에, 대략 30초후 이곳이 영향을 받는다고 알려줌
					{
						
					JOptionPane.showMessageDialog(null, m[list.peekLast()].getMapName() + "에서 곧 자기장이 생성됩니다.\n",
							"!!", JOptionPane.CLOSED_OPTION);
					
						text[m[list.peekLast()].getLoc()].setForeground(Color.MAGENTA); // 보라색으로 맵이름변경
						
							
							
							
							
						AI.MoveAlgorithm(m, list); //AI 이동 알고리즘 
						notify = false;
					}
					else if (timer - whattime > 90) // 약 90초마다 닫김
					{
						
						text[m[list.peekLast()].getLoc()].setForeground(Color.RED); // 레드로 맵이름변경
						m[list.peekLast()].setFlag(); // 이곳을 닫음
						
					
							JOptionPane.showMessageDialog(null,m[list.pollLast()].getMapName() + "(이)가 곧 자기장의 영향에 듭니다!\n" + "어서 도망치세요 !!", "!!",
									JOptionPane.WARNING_MESSAGE); //마지막꺼 반환하면서 pop
					
						AI.MoveAlgorithm(m, list); //AI 이동 알고리즘 
						AI.AIgetStronger(); //AI가 점점 쎄짐
						whattime = timer; // 타이머 재기 초기화
						notify = true;
					}
					else; //그외
					
				}

				Thread.sleep(100); // 쓰레드 sleep 0.1초
			}
		} catch (InterruptedException e) {
			// 인터럽트 일어나면 while문 종료
		} catch (Exception e) {
			e.printStackTrace(); // 그외 에러 체크
		}

	}

	/**
	 * control Mapmanager.
	 *
	 * @param user get UserInfo
	 * @param player get player
	 */
	public Mapmanager(UserInfo user, StatusManager player) {
		this.user = user;
		this.player = player;
		forDef = new JLabel(" 방어력 :  " +player.getStatus().getDef());
		
		String imags =player.getStatus().getImage();
		
		if(imags == "image/cha1.png")	imags = "image/1cha1.png";
		if(imags == "image/cha2.png")	imags = "image/1cha2.png";
		if(imags == "image/cha3.png")	imags = "image/1cha3.png";
		
			characterImage = new JLabel(new ImageIcon(getClass().getClassLoader().getResource(imags)));
			characterImage.setBounds(0,0,201,90);
			
		
		
		final int ROW = 920; // 크기 나중에 삭제
		final int COL = 920;

		for (int i = 0; i < 9; i++) {
			m[i] = new map(i, mapsName[i]);
		}

		list = new LinkedList<Integer>(); // 자기장 제어용 LinkedList 설정

		for (int i = 0; i < 9; i++)
			list.add(i); // [0,9]
		Collections.shuffle(list); // 랜덤하게 섞음

		m[0].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map1.png")));
		m[1].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map2.png")));
		m[2].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map3.png")));
		m[3].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map4.png")));
		m[4].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map5.png")));
		m[5].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map6.png")));
		m[6].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map7.png")));
		m[7].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map8.png")));
		m[8].setImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/map9.jpg"))); // 이미지 삽입

		m[0].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon1.png")));
		m[1].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon2.png")));
		m[2].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon3.png")));
		m[3].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon4.png")));
		m[4].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon5.png")));
		m[5].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon6.png")));
		m[6].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon7.png")));
		m[7].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon8.png")));
		m[8].setIconImage(new ImageIcon(getClass().getClassLoader().getResource("image/mapImage/icon9.png")));
		
		AI = new AIManager(m); //AImanager 셋팅
		myThread = new Thread(this);
		
		frame.setLayout(null);

		JPanel topright = new JPanel();
		topright.setBounds(0, 90, 202, 30);
		topright.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		
		topright.add(new JLabel(player.getStatus().getName()), BorderLayout.CENTER);

		top.setBounds(200, 0, 350, 120);
		top.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		forHp.setBounds(200, 20, 40, 30);
		forDef.setBounds(200, 50, 200, 30);
		forLoc.setBounds(200, 80, 200, 30);

		HP.setStringPainted(true);
		HP.setForeground(Color.RED);
		HP.setValue(player.getStatus().getHp()); // 스텟의 hp를 여기에 넣음
		HP.setStringPainted(true);
		HP.setBounds(240, 20, 260, 30);

		JLabel timeTitle = new JLabel("                Time");
		timeTitle.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		timeTitle.setBounds(550, 0, 140, 61);

		Mytime.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		Mytime.setBounds(550, 60, 140, 60);

		frame.add(characterImage);
		frame.add(HP);
		frame.add(forHp);
		frame.add(forDef);
		frame.add(forLoc);
		frame.add(Mytime);
		frame.add(timeTitle);
		frame.add(topright);
		frame.add(top);

		for (int i = 0; i < 9; i++) {
			b[i] = new JButton(m[i].getIconImage());
			b[i].setBorderPainted(false);
			b[i].setFocusPainted(false);
			b[i].setContentAreaFilled(false); // 버튼 테두리, 색칠 등 지움

			b[i].setFont(new Font("Serif", Font.ITALIC, 24));
			b[i].setForeground(Color.BLUE);
			
			
			
		}

		JButton Item = new JButton(new ImageIcon(getClass().getClassLoader().getResource("image/button/gabang.png")));// 가방사진
		Item.setBorderPainted(false);
		Item.setFocusPainted(false);
		Item.setContentAreaFilled(false); // 버튼 테두리, 색칠 등 지움

		Item.setBounds(730, 10, 140, 120);

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (i * 3 + j == myLocation)
					ping.setBounds(50 + 280 * j, 200 + 250 * i, 230, ROW - 770);

				text[i * 3 + j] = new JLabel(mapsName[i * 3 + j]); // 맵 이름 텍스트
				b[i * 3 + j].setBounds(50 + 280 * j, 200 + 250 * i, 230, ROW - 770);

				text[i * 3 + j].setBounds(70 + 280 * j, 230 + 250 * i, 200, 30); // 이름 텍스트 고정!
				// text[i*3+j].setFont(new Font("Serif",Font.BOLD,20)); 폰트안건드는게 나음
				frame.add(text[i * 3 + j]); // 프레임에 추가
			}
		}

		frame.add(Item);
		frame.add(ping);

		LinkedList<JFrame> thisFrame = new LinkedList<>();
		//이게 없으면 팝업이 중복되는 문제가 생겨서 만든 코드입니다.. 팝업의 모든 frame을 없앨때 씁니다.
		
		for (int i = 0; i < 9; i++) {
			final int mynum = i;
			
			 b[i].addActionListener(new ActionListener() // 장소를 누르면 시작되는 이벤트
			{

				public void actionPerformed(ActionEvent e) {
					
					AI.MoveAlgorithm(m, list); //AI 이동 알고리즘 
					if (b[myLocation]!=e.getSource() && e.getSource() == b[mynum]) {
					
						new MapLocationPopup(thisFrame, m[mynum], mynum);	//이동 확인 팝업 띄움   
						
					} else {
						
					}
				}
			});
			
			b[i].addMouseListener(new MouseAdapter(){
			      
			      @Override
			      public void mouseEntered(MouseEvent e){
			         
			         b[mynum].setCursor(new Cursor(Cursor.HAND_CURSOR)); //버튼에 손 올리면 핸드커서

			      }
			    
			      public void mouseExited(MouseEvent e){
			       
			         b[mynum].setCursor(new Cursor(Cursor.DEFAULT_CURSOR)); //기본 커서

			      }
			});
			frame.add(b[i]);

		}

		Item.addActionListener(new ActionListener() // 가방을 누르면 시작되는 이벤트
		{
			
			/**
			 * Action performed.
			 *
			 * @param e the e
			 */
			public void actionPerformed(ActionEvent e) {
				new Itemview(thisFrame,player.getStatus(),Mapmanager.this);
			}
		});

		/** The tk. */
		Toolkit tk = Toolkit.getDefaultToolkit();
		
		/** The screen size. */
		Dimension screenSize = tk.getScreenSize();
		
		/** The screen height. */
		int screenHeight = screenSize.height;
		
		/** The screen width. */
		int screenWidth = screenSize.width;
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setTitle("전대 그라운드 - Map");
		frame.setSize(ROW, COL);

		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));// 전대 로고

		//myThread.setDaemon(true);
		myThread.start();
	}
	
	/**
	 * Sets the player.
	 * @param player the new player
	 */
	public void setPlayer(StatusManager player) {
		this.player = player;
	}

}