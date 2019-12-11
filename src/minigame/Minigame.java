/*
 * 
 */
package minigame;

import javax.swing.ImageIcon;

// TODO: Auto-generated Javadoc
/** 미니게임을 담아두는 class. 클릭해서 푸는 문제와 이미지를 보고 답을 제출하는 문제 2가지
 * @author Chungheon Yi
 *
 */
public class Minigame {
		
	/**  게임 이미지. */
	private ImageIcon image;

	/**  게임 답. */
	private String str;

	/**  미니게임 pause 체크 하는데 쓰는 boolean 값. */
	private boolean isStop = false;
	
	/**  게임에 대한 설명을 담아놓는 문자열 배열, String[0]는 이 미니게임의 이름. */
	private String[] list = new String[4];
	
	/**  게임 제한 초. */
	private int timer =30;
	
	
		/** The x. */
		private int x;
		
		/** The y. */
		private int y;
		
		/** 좌표 4가지 */
		private int Point[];
	
	/**
	 * set the image, answer, and description of game.
	 *
	 * @param ima image
	 * @param strings answer 
	 * @param howtoplay description of game
	 * 	 */
	public Minigame(ImageIcon ima, String strings,String[] howtoplay) // 게임 생성
	{
		this.image = ima;

		str = new String(strings);
		setList(howtoplay);
		
	}
	
	/**
	 * 이 게임의 퀴즈와 정답을 설정한다.클릭용 (좌표O)
	 *
	 * @param ima image of quiz
	 * @param XY thexy
	 * @param howtoplay 게임에 대한 설명을 담아놓는 문자열 배열, String[0] name of game 
	 * @Param XY 4 (x,y) for answer
	 */
	public Minigame(ImageIcon ima,int XY[], String[] howtoplay) // 게임 생성
	{
		this.image = ima;
		setList(howtoplay);
		
		Point = new int[4];
		setPoint(XY);
		
	}
	
	/**
	 * Sets the x.
	 *
	 * @param num the new x
	 */
	void setX(int num) {
		x = num;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param num the new y
	 */
	void setY(int num) {
		y = num;
	}
	
	/**
	 * Sets X and Y.
	 * @param num1 get x
	 * @param num2 get y
	 */
	void setXY(int num1,int num2) {
		x = num1;
		y=  num2;
		
		if(Point !=null) // 미니게임이 클릭이라면 이거 실행
		{  //(x최소,x최대,y최소,y최대)
			if(Point[0]<=x && Point[1]>=x && Point[2]<=y && Point[3]>=y)
			{
				str = "클릭후 제출 누르기"; //누른 좌표가 답 사각형 안이라면 제출시 true
			}
			else
				str = "you are wrong"; //그외 
		}
	}
	
	/**
	 * Gets the x.
	 * @return the x
	 */
	int getX() {
		return x;
	}
	
	/**
	 * Gets the y.
	 * @return the y
	 */
	int getY() {
		return y;
	}
	
	/**
	 * Sets the point.
	 * @param arr the new point
	 */
	void setPoint(int[] arr)
	{
		try {
			for(int i=0; i<4; i++)
			{
				Point[i]=arr[i];
			}
		}
		catch(ArrayIndexOutOfBoundsException e) //실수 방지용
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Gets the point.
	 * @param index the index
	 * @return the point of index
	 */
	int getPoint(int index)
	{
		return Point[index];
	}
	
	/**
	 * return game image.
	 * @return image gameimage
	 */
	public ImageIcon getImage() {
		return image;
	}
	
	/**
	 * Sets the image.
	 * @param ima the new image
	 */
	public void setImage(ImageIcon ima) {
		 image = ima;
	}
	
	
	/**
	 * Gets the timer.
	 * @return timer
	 */
	public int getTimer() {
		return timer;
	}
	
	/**
	 * Sets the timer.
	 * @param timer the new timer
	 */
	public void setTimer(int timer) {
		this.timer = timer;
	}
	
	/**
	 * return answer.
	 *
	 * @return str return answer
	 */
	public String getAnswer() {
		return str;
	}

	/**
	 * Sets the answer.
	 *
	 * @param strings the new answer
	 */
	public void setAnswer(String strings) {
		this.str = new String(strings);
	}
	
	/**
	 * return pause check.
	 *
	 * @return isstop pause check
	 */
	public boolean getisStop() {
		return isStop;
	}

	
	/**
	 * 	to check it is paused or not .
	 *
	 * @param bool the new checks if is stop
	 */
	public void setisStop(boolean bool) {
		isStop = bool;
	}
	
	/**
	 * Sets the list.
	 * @param getstring the new list list[0] is the name of minigame
	 */
	public void setList(String[] getstring) // 게임 설명을 적어놓는다.
	{
		try{
			for(int i=0; i<4; i++) list[i] = getstring[i]; 
		}
		catch(ArrayIndexOutOfBoundsException e)
		{
			e.printStackTrace();  //실수방지
		}
	}
	
	/**
	 * Gets the list. list[0] is the name of minigame
	 * @param i number of index
	 * @return the list
	 */
	public String getList(int i)
	{
		return list[i];
	}
	
}
