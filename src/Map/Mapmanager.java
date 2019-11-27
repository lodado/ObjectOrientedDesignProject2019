package Map;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedList;

import minigame.*;




/**
 * 맵 관리 매니저 클래스. Thread는 launcher와 함께 게임 플레이동안 계속 돌아간다.
 * @author Chungheon Yi
 */

public class Mapmanager extends JFrame implements Runnable{
	
	//private statusManager Man; -> 나중 stat 받으면 생성
	String statue;
	
	//prvaite AImanager AI -> 나중 AI매니저 생성하면 생성
	
	/** The hp */
	private JProgressBar HP = new JProgressBar(0,100);
	
	/** The my location. */
	private int myLocation = 0; //자신의 위치숫자로 구별  
	
	/** 맵 이름들. 맵 생성용으로 사용. */
	private String mapsName[] = {"농대","공대","자연대","사회대","인문대","봉지","경영대","용지","예대"};
	
	/** 타이머  */
	private int timer = 0; // 
	
	/** 맵 9개  */
	private map m[] = new map[9];
	
	/** 버튼 9개*/
	private JButton[] b= new JButton[9]; 
	
	/**타이머 관리용 쓰레드*/
	private Thread myThread; 
	
	/** 이 클래스의 Jframe. */
	private JFrame frame = new JFrame();
	
	/** 좌측 상단 패널 */
	private JPanel top = new JPanel();
	
	/** hp 출력 */
	private JLabel forHp = new JLabel(" HP :");
	
	/** 방어력 출력 */
	private JLabel forDef = new JLabel(" 방어력 :");
	
	/** 현재위치 출력  */
	private JLabel forLoc = new JLabel(" 현재 위치 :   "+mapsName[myLocation]);	
	
	private JLabel Mytime =new JLabel("                   "+timer);
	/**
	 * 맵 장소 이동할때 확인 혹은 취소를 누르는 팝업창을 위한 inner class, JDialog로 교체 가능
	 * @author Chungheon Yi
	 */
	private class MapLocationPopup extends JFrame
	{
		
		/** 이동 버튼 */
		private JButton Bt1 = new JButton("이동");
		
		/** 취소 버튼 */
		private JButton Bt2 = new JButton("취소");
		
		/** inner class의 JFrame */
		private JFrame thisframe = new JFrame();
		
		/** 하단 패널 */
		private JPanel bottom = new JPanel();
		
		/** 상단 패널  */
		private JPanel top = new JPanel();
		
		/**
		 * 이동  둘중 택 fight or startMinigame
		 * @param mv 클릭한 버튼의 map을 인자로 받음 
		 */
		public void moving(map mv) 
		{
			myLocation = mv.getLoc();  //자신의 위치 이곳으로 이동
			forLoc.setText(" 현재 위치 :   "+mapsName[myLocation]);
			if(mv.getUserNumber()<=1) // start minigame 
			{
				new MinigameManager(mv,Mapmanager.this);
			}		
			else
			{
				//fight manager();
			}
			
	
		}
		/**
		 	확인 누르면 맵 이동, 취소 누르면 취소
		 * @param m 자기 위치를 알아낼때 class map 이용
		 */
		public MapLocationPopup(map M,int num)
		{
			    Toolkit tk = Toolkit.getDefaultToolkit();
			    Dimension screenSize = tk.getScreenSize();
			    int screenHeight = screenSize.height;
			    int screenWidth = screenSize.width;
			   
			thisframe.setTitle(M.getMapName()+"로 이동?");
			thisframe.setContentPane(new JLabel(M.getMapImage()));
			
			Bt1.addActionListener(
			
			new ActionListener()
			{
				public void actionPerformed(ActionEvent arg0) //확인 버튼을 누르면 맵 화면에서 전투 혹은 미니게임 화면으로 이동
				{ 
					frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
					thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					thisframe.setVisible(false);
					frame.setVisible(false);
					
					moving(m[num]);
				}
			});
			
			Bt2.addActionListener(new ActionListener() //취소 버튼을 눌렀을때 맵으로 돌아감
			{
				public void actionPerformed(ActionEvent arg0)
				{ 
					
					thisframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					thisframe.setVisible(false);
				}
			});
		
			thisframe.add(Bt1);
			thisframe.add(Bt2);
			
			Bt1.setBounds(100,500,200,100);
			Bt2.setBounds(400,500,200,100);
			
			add(top);
			thisframe.add(Bt1);
			thisframe.add(Bt2);
			thisframe.add(bottom);
		
			setResizable(false);
			 thisframe.setLocation(screenWidth / 3, screenHeight / 7);
			 thisframe.setSize(700,700);
			 thisframe.setVisible(true);	 
			//int result = JOptionPane.showConfirmDialog(null, "계속할 것입니까?")
		}
	}
	/**
	 * @return 자기 자신 위치 받음
	 */
	public map getMyLoc(){  
		return m[myLocation];
	}
	
	/**
	 * @param 자기 자신 위치 설정
	 */
	public void setMyLoc(int num)  
	{
		this.myLocation = num;
	}
	
	/**
	 * Sets the thread.
	 * @param the thread를 인자로 받음.
	 */
	void setThread(Thread T1)  
	{   
		 myThread = T1 = new Thread(this);
		 
		 // myThread == launcher의 Thread T1
	}
	
	/**
	 * Gets the thread.
	 *
	 * @return the thread
	 */
	public Thread getThread()
	{
		return this.myThread;
	}
	
	/**
	 * Sets the timer. 변경은 허용불가
	 * @param time the new timer
	 */
	public void setTimer(final int time)
	{
		this.timer = time;
	}
	
	/**
	 * Gets the timer.
	 * @return the timer
	 */
	public int getTimer()
	{
		return timer;
	}
	
	/**
	 * Gets the map frame.
	 * @return the map frame
	 */
	public JFrame getMapFrame()
	{
		return frame;
	}
	
	/**
	 * Sets the map frame.
	 * @param frame the new map frame
	 */
	public void setMapFrame(JFrame frame)
	{
		this.frame = frame;
	}
	
	/**
	 * Sets the hp bar. map, fight, minigame 매니저에서 사용
	 * @param CharHP the new hp
	 */
	public void setHP(int CharHP,JProgressBar HPBar)
	{
		HPBar.setValue(CharHP);
	}
	
	
	/**
	 * 맵이 닫겼으면 쓰레드와 연동하여서 지속적으로 체력을 깎는다.
	 *
	 * @param count the count. C++의 인자 참조(&)를 구현하기 위하여 배열을 사용하였다. 칸은 총 1칸(count)
	 * @param HPBar 이 객체에서 쓰는 JProgressBar
	 * @param Threadspeed 1000 = 1, 250 = 4 , 500 = 2 , 100 = 10
	 */
	public void IsClosedMap(int count[],JProgressBar HPBar,double Threadspeed)  //
	{
		// count = a[0];
		
		if(!this.m[myLocation].getFlag())  //flag가 0이면 맵이 닫겨있는것이고 1이면 체력 안깎임
		{
			HPBar.setForeground(Color.magenta);  //자기장 안에 있다고 표시해줌
			
			if(count[0]++>4*Threadspeed) {   // 만약 닫힌곳에 들어가 있다면 4*Threadspeed초 이후에 체력이 1 깎임
				
					count[0] = 0;
					int hp =100; // character.setHp(character.getHp()-1);
					setHP(--hp,HPBar);  //현재 HP에 characterHP 대입. 이부분은 나중 character와 연동할것
			}
		}
		else
		{
			HPBar.setForeground(Color.RED); // 보통 상태
		}
	}

	@Override
	public void run()
	{
		try
		{
			int count = 0; //count
			int whattime = 0; //시간 launcher timer와 상관 없이 따로재는 타이머 
			boolean notify = true; // notify = 미리 알람용 boolean
			
			LinkedList<Integer> list = new LinkedList<Integer>(); //자기장 제어용 LinkedList
			
			for(int i=0; i<9;i++) list.add(i);      //[0,9]
			Collections.shuffle(list);     //랜덤하게 섞음
			
			int[] arr = new int[]{count};  // C++의 참조(&) 구현, 수정하다보니 굳이 참조를 쓸 필요가 없게 되었지만 그냥 놔둠
			
			while(true)
			{

				//System.out.println(timer+"변경 - mapmanager"); //확인용 print
				Mytime.setText("                   "+timer);  //시간초 계속 갱신
				
				IsClosedMap(arr,HP,(double)10); // arr = 참조를 통한 인자 변경(C++의 &)을 위한 배열,
												//HP는 HP바, 쓰레드 speed(500millsec*10)
				
				if(!list.isEmpty()) //모두 닫겼다면 실행 하지 않음
					{
						if( notify && whattime>750) //75초후에, 15초후 이곳이 영향을 받는다고 알려줌
						{
							JOptionPane.showConfirmDialog(null, m[list.peekLast()].getMapName()
									+"에서 곧 자기장이 생성됩니다.\n","!!", JOptionPane.CLOSED_OPTION);
						
						notify = false;
					}
					
					whattime++;
					
					if(whattime>900) //90초마다 닫김
						{
									m[list.peekLast()].setFlag();   //이곳을 닫음
									JOptionPane.showConfirmDialog(null, m[list.pollLast()].getMapName()+"(이)가 곧 자기장의 영향에 듭니다!\n"
									 		+ "어서 도망치세요 !!",
											 "!!", JOptionPane.CLOSED_OPTION);	
							whattime = 0; //타이머 재기 초기화		
							notify = true;
						}	
				}	
				
				Thread.sleep(100); //쓰레드 sleep 0.1초	
				}
			}
			catch(InterruptedException e)
			{
				 //인터럽트 일어나면 while문 종료
			}
			catch(Exception e)
			{
				e.printStackTrace(); //그외 에러 체크
			}
		
	}

	/**
	 * map을 관리해주는 매니저 생성자.
	 */
	public Mapmanager(Thread T1)
	{
		
		final  int ROW = 1000; //크기 나중에 삭제
		final  int COL = 1000;
		
		for(int i=0; i<9; i++)
		{
			m[i] = new map(i,mapsName[i]);
		}
		
		
		m[0].setImage(new ImageIcon("image.PNG"));
		m[1].setImage(new ImageIcon("image.PNG"));
		m[2].setImage(new ImageIcon("image.PNG"));
		m[3].setImage(new ImageIcon("image.PNG"));
		m[4].setImage(new ImageIcon("image.PNG"));
		m[5].setImage(new ImageIcon("image.PNG"));
		m[6].setImage(new ImageIcon("image.PNG"));
		m[7].setImage(new ImageIcon("image.PNG"));
		m[8].setImage(new ImageIcon("image.PNG"));
		
		setThread(T1);
		
		frame.setLayout(null);
		
		JPanel topright = new JPanel();
		topright.setBounds(0,90,202,30);
		topright.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		
		topright.add(new JLabel(" 캐릭터 이름"),BorderLayout.CENTER);
		
		top.setBounds(200,0,(ROW)/20+ROW-770,120);
		top.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		
		top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
		forHp.setBounds(200,20,40,30);
		forDef.setBounds(200,50,200,30);
		forLoc.setBounds(200,80,200,30);
		
		HP.setStringPainted(true);
		HP.setForeground(Color.RED);
		HP.setValue(74); // 스텟의 hp를 여기에 넣음
		HP.setStringPainted(true);
		HP.setBounds(240,20,238,30);
		
		
		JLabel timeTitle= new JLabel("                Time");
		timeTitle.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		timeTitle.setBounds(479,0,140,61);
		
		Mytime.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		Mytime.setBounds(479,60,140,60);
		
		
		frame.add(HP);
		frame.add(forHp);
		frame.add(forDef);
		frame.add(forLoc);
		frame.add(Mytime);
		frame.add(timeTitle);
		frame.add(topright);
		frame.add(top);
		
		
		for(int i=0; i<9; i++)
		{
			b[i] = new JButton(mapsName[i]);
		}
		
		JButton Item = new JButton("가방");
		
		Item.setBounds((13*ROW)/20,20,ROW-770,100);
		b[0].setBounds(ROW/20,150,ROW-770,ROW-770);
		b[1].setBounds((7*ROW)/20,150,ROW-770,ROW-770);
		b[2].setBounds((13*ROW)/20,150,ROW-770,ROW-770);
		b[3].setBounds(ROW/20,400,ROW-770,ROW-770);
		b[4].setBounds(ROW/20,650,ROW-770,ROW-770);
		b[5].setBounds((7*ROW)/20,400,ROW-770,ROW-770);
		b[6].setBounds((7*ROW)/20,650,ROW-770,ROW-770);
		b[7].setBounds((13*ROW)/20,400,ROW-770,ROW-770);
		b[8].setBounds((13*ROW)/20,650,ROW-770,ROW-770);
		frame.add(Item);
		for(int i=0; i<9; i++)  
		{
			b[i].addActionListener(new ActionListener() //장소를 누르면 시작되는 이벤트
			{
				public void actionPerformed(ActionEvent e)
				{ 
					int i=0;
		            for(i=0; i<9; i++)
		            {
		            	if(e.getSource()==b[i])
		            	{
		            		new MapLocationPopup(m[i],i);
		            		break;
		            	}
		            }
		        }
			}
		);
	
		frame.add(b[i]);
		}
		
		Item.addActionListener(new ActionListener()  //가방을 누르면 시작되는 이벤트
				{
					public void actionPerformed(ActionEvent e)
					{ 
						// new Item();
					}
				});	
		
		 Toolkit tk = Toolkit.getDefaultToolkit();
		    Dimension screenSize = tk.getScreenSize();
		    int screenHeight = screenSize.height;
		    int screenWidth = screenSize.width;
		   
		    frame.setTitle("전대 그라운드 - Map");
			frame.setSize(ROW,COL);
	   
		    frame.setLocation(screenWidth / 4, screenHeight / 10);
		    frame.setVisible(true);
		    frame.setResizable(false);
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			
			
			myThread.setDaemon(true);
			myThread.start();
	}
	
}