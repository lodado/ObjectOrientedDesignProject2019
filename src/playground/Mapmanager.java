package playground;
import minigame.*;

import java.awt.BorderLayout;
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

/**
 * 맵 관리 매니저 클래스. Thread는 launcher와 함께 게임 플레이동안 계속 돌아간다.
 * 
 * @author Chungheon Yi
 */

public class Mapmanager extends JFrame implements Runnable {

	//private statusManager Man; -> 나중 stat 받으면 생성
	
	// prvaite AImanager AI -> 나중 AI매니저 생성하면 생성

	/** The hp */
	private JProgressBar HP = new JProgressBar(0, 100);

	/** The my location. */
	private int myLocation = 0; // 자신의 위치숫자로 구별

	/** 맵 이름들. 맵 생성용으로 사용. */
	private String mapsName[] = { "농대", "공대", "자연대", "사회대", "인문대", "봉지", "경영대", "용지", "예대" };

	/** 타이머 */
	private int timer = 0; //

	/** 맵 9개 */
	private M m[] = new M[9];

	JLabel ping = new JLabel(new ImageIcon("./src/image/mapImage/ping.png")); // 자기위치 가리킴

	JLabel text[] = new JLabel[9];

	/** 버튼 9개 */
	private JButton[] b = new JButton[9];

	/** 타이머 관리용 쓰레드 */
	private Thread myThread;

	/** 이 클래스의 Frame. */
	private JFrame frame = new JFrame();

	/** 좌측 상단 패널 */
	private JPanel top = new JPanel();

	/** hp 출력 */
	private JLabel forHp = new JLabel(" HP :");

	// nameofMap.setForeground(Color.BLUE);

	/** 방어력 출력 */
	private JLabel forDef = new JLabel(" 방어력 :");

	/** 현재위치 출력 */
	private JLabel forLoc = new JLabel(" 현재 위치 :   " + mapsName[myLocation]);

	/** 총 게임 플레이 시간 출력 */
	private JLabel Mytime = new JLabel("                   " + timer);

	/** 자기장이 여닫겼는지 확인할때 쓰이는 linkedlist */
	LinkedList<Integer> list;

	/**
	 * 맵 장소 이동할때 확인 혹은 취소를 누르는 팝업창을 위한 inner class, JDialog로 교체 가능
	 * 
	 * @author Chungheon Yi
	 */
	private class MapLocationPopup extends JDialog {

		/** 이동 버튼 */
		private JButton Bt1 = new JButton(new ImageIcon("./src/image/button/buttonYES.png"));

		/** 취소 버튼 */
		private JButton Bt2 = new JButton(new ImageIcon("./src/image/button/buttonNO.png"));

		/** 하단 패널 */
		private JPanel bottom = new JPanel();

		/** 상단 패널 */
		private JPanel top = new JPanel();

		/**
		 * 이동 둘중 택 fight or startMinigame
		 * 
		 * @param mv 클릭한 버튼의 map을 인자로 받음
		 */
		public void moving(M mv) {
			myLocation = mv.getLoc(); // 자신의 위치 이곳으로 이동
			forLoc.setText(" 현재 위치 :   " + mapsName[myLocation]);

			ping.setBounds(50 + 280 * (myLocation % 3), 200 + 250 * (myLocation / 3), 230, 920 - 770);
			if (mv.getUserNumber() <= 0) // start minigame
			{
				new MinigameManager(mv, Mapmanager.this);
			} else {
				// fight manager();
			}

		}

		/**
		 * 확인 누르면 맵 이동, 취소 누르면 취소.
		 *
		 * @param thisFrameList 이게 없으면 팝업이 중복되는 문제가 생겨서 만든 코드입니다..확인을 누를시 모든 JFrame을 닫습니다.
		 * @param M   맵 m
		 * @param num 이곳 번지수(location)
		 */
		public MapLocationPopup(final LinkedList<JFrame> thisFrameList, M M, int num) {
			JFrame thisframe = new JFrame();

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

			thisframe.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));// 전대 로고
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
	 * @return 자기 자신 위치 받음
	 */
	public M getMyLoc() {
		return m[myLocation];
	}

	/**
	 * @param 자기 자신 위치 설정
	 */
	public void setMyLoc(int num) {
		this.myLocation = num;
	}

	/**
	 * Sets the thread.
	 * 
	 * @param the thread를 인자로 받음.
	 */
	void setThread(Thread T1) {
		myThread = T1 = new Thread(this);

		// myThread == launcher의 Thread T1
	}

	/**
	 * Gets the thread.
	 *
	 * @return the thread
	 */
	public Thread getThread() {
		return this.myThread;
	}

	/**
	 * Sets the timer. 변경은 허용불가
	 * 
	 * @param time the new timer
	 */
	public void setTimer(final int time) {
		this.timer = time;
	}

	/**
	 * Gets the timer.
	 * 
	 * @return the timer
	 */
	public int getTimer() {
		return timer;
	}

	/**
	 * Gets the map frame.
	 * 
	 * @return the map frame
	 */
	public JFrame getMapFrame() {
		return frame;
	}

	/**
	 * Sets the map frame.
	 * 
	 * @param frame the new map frame
	 */
	public void setMapFrame(JFrame frame) {
		this.frame = frame;
	}

	/**
	 * Sets the hp bar. map, fight, minigame 매니저에서 사용
	 * 
	 * @param CharHP the new hp
	 */
	public void setHP(int CharHP, M m, JProgressBar HPBar) {
		if (!m.getFlag())
			HPBar.setForeground(Color.magenta); // 자기장 안에 있다고 표시해줌
		else
			HPBar.setForeground(Color.RED); // 보통 상태

		HPBar.setValue(CharHP); // 체력 set
	}

	/**
	 * 맵이 닫겼으면 쓰레드와 연동하여서 지속적으로 체력을 깎는다.
	 *
	 * @param count       the count. C++의 인자 참조(&)를 구현하기 위하여 배열을 사용하였다. 칸은 총
	 *                    1칸(count)
	 * @param HPBar       이 객체에서 쓰는 JProgressBar
	 * @param Threadspeed 1000 = 1, 250 = 4 , 500 = 2 , 100 = 10
	 */
	private void IsClosedMap(int count[], M m, JProgressBar HPBar, double Threadspeed) //
	{
		// count = a[0];

		if (!m.getFlag()) // flag가 0이면 맵이 닫겨있는것이고 1이면 체력 안깎임
		{
			if (count[0]++ > 4 * Threadspeed) { // 만약 닫힌곳에 들어가 있다면 4*Threadspeed초 이후에 체력이 1 깎임

				count[0] = 0;
				int hp = 100; // character.setHp(character.getHp()-1);
				setHP(--hp, m, HPBar); // 현재 HP에 characterHP 대입. 이부분은 나중 character와 연동할것
			}
		}
	}

	@Override
	public void run() {
		try {
			int whattime = 0; // 시간 launcher timer와 상관 없이 따로재는 타이머
			boolean notify = true; // notify = 미리 알람용 boolean

			int[] count = new int[] { 0 }; // C++의 참조(&) 구현, 수정하다보니 굳이 참조를 쓸 필요가 없게 되었지만 그냥 놔둠

			while (true) {

				// System.out.println(timer+"변경 - mapmanager"); //확인용 print
				Mytime.setText("                   " + timer); // 시간초 계속 갱신

				IsClosedMap(count, m[myLocation], HP, (double) 10); // count = 참조를 통한 인자 변경(C++의 &)을 위한 배열,
				// HP는 HP바, 쓰레드 speed(500millsec*10)

				if (!list.isEmpty()) // 모두 닫겼다면 실행 하지 않음
				{
					if (notify && whattime > 750) // 75초후에, 15초후 이곳이 영향을 받는다고 알려줌
					{
						text[m[list.peekLast()].getLoc()].setForeground(Color.MAGENTA); // 보라색으로 맵이름변경
						JOptionPane.showConfirmDialog(null, m[list.peekLast()].getMapName() + "에서 곧 자기장이 생성됩니다.\n",
								"!!", JOptionPane.CLOSED_OPTION);

						notify = false;
					}

					whattime++;

					if (whattime > 900) // 90초마다 닫김
					{
						text[m[list.peekLast()].getLoc()].setForeground(Color.RED); // 레드로 맵이름변경
						m[list.peekLast()].setFlag(); // 이곳을 닫음
						JOptionPane.showConfirmDialog(null,
								m[list.pollLast()].getMapName() + "(이)가 곧 자기장의 영향에 듭니다!\n" + "어서 도망치세요 !!", "!!",
								JOptionPane.CLOSED_OPTION);

						whattime = 0; // 타이머 재기 초기화
						notify = true;
					}
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
	 * map을 관리해주는 매니저 생성자.
	 */
	StatusManager player;
	public Mapmanager(Thread T1, StatusManager player) {
		this.player = player;
		final int ROW = 920; // 크기 나중에 삭제
		final int COL = 920;

		for (int i = 0; i < 9; i++) {
			m[i] = new M(i, mapsName[i]);
		}

		list = new LinkedList<Integer>(); // 자기장 제어용 LinkedList 설정

		for (int i = 0; i < 9; i++)
			list.add(i); // [0,9]
		Collections.shuffle(list); // 랜덤하게 섞음

		m[0].setImage(new ImageIcon("./src/image/mapImage/map1.png"));
		m[1].setImage(new ImageIcon("./src/image/mapImage/map2.png"));
		m[2].setImage(new ImageIcon("./src/image/mapImage/map3.png"));
		m[3].setImage(new ImageIcon("./src/image/mapImage/map4.png"));
		m[4].setImage(new ImageIcon("./src/image/mapImage/map5.png"));
		m[5].setImage(new ImageIcon("./src/image/mapImage/map6.png"));
		m[6].setImage(new ImageIcon("./src/image/mapImage/map7.png"));
		m[7].setImage(new ImageIcon("./src/image/mapImage/map8.png"));
		m[8].setImage(new ImageIcon("./src/image/mapImage/map9.jpg")); // 이미지 삽입

		m[0].setIconImage(new ImageIcon("./src/image/mapImage/icon1.PNG"));
		m[1].setIconImage(new ImageIcon("./src/image/mapImage/icon2.PNG"));
		m[2].setIconImage(new ImageIcon("./src/image/mapImage/icon3.PNG"));
		m[3].setIconImage(new ImageIcon("./src/image/mapImage/icon4.PNG"));
		m[4].setIconImage(new ImageIcon("./src/image/mapImage/icon5.PNG"));
		m[5].setIconImage(new ImageIcon("./src/image/mapImage/icon6.PNG"));
		m[6].setIconImage(new ImageIcon("./src/image/mapImage/icon7.PNG"));
		m[7].setIconImage(new ImageIcon("./src/image/mapImage/icon8.PNG"));
		m[8].setIconImage(new ImageIcon("./src/image/mapImage/icon9.PNG"));
		setThread(T1);

		frame.setLayout(null);

		JPanel topright = new JPanel();
		topright.setBounds(0, 90, 202, 30);
		topright.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		topright.add(new JLabel(" 캐릭터 이름"), BorderLayout.CENTER);

		top.setBounds(200, 0, 350, 120);
		top.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));

		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		forHp.setBounds(200, 20, 40, 30);
		forDef.setBounds(200, 50, 200, 30);
		forLoc.setBounds(200, 80, 200, 30);

		HP.setStringPainted(true);
		HP.setForeground(Color.RED);
		HP.setValue(74); // 스텟의 hp를 여기에 넣음
		HP.setStringPainted(true);
		HP.setBounds(240, 20, 260, 30);

		JLabel timeTitle = new JLabel("                Time");
		timeTitle.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		timeTitle.setBounds(550, 0, 140, 61);

		Mytime.setBorder(new TitledBorder(new LineBorder(Color.black, 2)));
		Mytime.setBounds(550, 60, 140, 60);

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

		JButton Item = new JButton(new ImageIcon("./src/image/button/gabang.png"));// 가방사진
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

					if (e.getSource() == b[mynum]) {
						new MapLocationPopup(thisFrame, m[mynum], mynum);

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
			public void actionPerformed(ActionEvent e) {
				// new Item();
			}
		});

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;

		frame.setTitle("전대 그라운드 - Map");
		frame.setSize(ROW, COL);

		frame.setLocation(screenWidth / 4, screenHeight / 10);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(("./src/image/chonnam.png")));// 전대 로고

		myThread.setDaemon(true);
		myThread.start();
	}

}