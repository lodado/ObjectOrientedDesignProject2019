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
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import minigame.*;




/**
 * 맵 관리 매니저 클래스
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
	private String str[] = {"농대","공대","자연대","사회대","인문대","봉지","경영대","용지","예대"};
	
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
	private JLabel forLoc = new JLabel(" 현재 위치 :   "+str[myLocation]);	
	
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
		@SuppressWarnings("static-access")
		public void moving(map mv) 
		{
			
			if(mv.getUserNumber()<=1) // start minigame 
			{
				new MinigameManager(mv,Mapmanager.this);
			}		
			else
			{
				//fight manager();
			}
			myThread.yield(); //맵의 쓰레드를 yield 시키고 다음으로 넘어감.
	
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
	void setThread(Thread th)  
	{
		
		this.myThread = th;
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
	
	public JFrame getMapFrame()
	{
		return frame;
	}
	
	public void setMapFrame(JFrame frame)
	{
		this.frame = frame;
	}
	
	@Override
	public void run()
	{
		while(true)
		{
			try
			{	
				System.out.println(timer+"변경");
				Mytime.setText("                   "+timer);  //시간초 계속 갱신
				Thread.sleep(1000);							//쓰레드 sleep
			}
			catch(InterruptedException e)
			{
				break;             //인터럽트 일어나면 while문 종료
			}
			catch(Exception e)
			{
				e.printStackTrace(); //그외 에러 체크
			}
		}
	}
	
	
	
	/**
	 * map을 관리해주는 매니저 생성자.
	 */
	public Mapmanager()
	{
		
		final  int ROW = 1000; //크기 나중에 삭제
		final  int COL = 1000;
		
		for(int i=0; i<9; i++)
		{
			m[i] = new map(i,str[i]);
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
		
		setThread(new Thread(this));
		
		frame.setLayout(null);
		
		JPanel topright = new JPanel();
		topright.setBounds(0,90,202,30);
		topright.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		
		topright.add(new JLabel(" 캐릭터 이름"),BorderLayout.CENTER);
		
		top.setBounds(200,0,(ROW)/20+ROW-770,120);
		top.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		
		top.setLayout(new BoxLayout(top,BoxLayout.Y_AXIS));
		forHp.setBounds(200,20,40,30);
		forDef.setBounds(200,50,100,30);
		forLoc.setBounds(200,80,100,30);
		
		HP.setStringPainted(true);
		HP.setForeground(Color.RED);
		HP.setValue(74); // 스텟의 hp를 여기에 넣음
		HP.setStringPainted(true);
		HP.setBounds(240,20,238,30);
		
		JLabel turns=Mytime;
		JLabel skadmstlrks= new JLabel("                Time");
		skadmstlrks.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		skadmstlrks.setBounds(479,0,140,61);
		
		turns.setBorder(new TitledBorder(new LineBorder(Color.black,2)));
		turns.setBounds(479,60,140,60);
		
		
		frame.add(HP);
		frame.add(forHp);
		frame.add(forDef);
		frame.add(forLoc);
		frame.add(turns);
		frame.add(skadmstlrks);
		frame.add(topright);
		frame.add(top);
		
		
		for(int i=0; i<9; i++)
		{
			b[i] = new JButton(str[i]);
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