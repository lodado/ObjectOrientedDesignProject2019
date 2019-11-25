package minigame;

import javax.swing.ImageIcon;

/** 미니게임을 담아두는 class. 만약 클릭이 필요한 경우와 안 필요한 경우를 구분하기 위하여(두 클래스를 합치면 너무 정신없으니) 사용하였다.
 * @author Chungheon Yi
 *
 */
public class Minigame extends SuperClassMinigame {

		/** The x. */
		private int x;
		
		/** The y. */
		private int y;
		
		/** 좌표를 4개 담아놓는 박스(클릭해서 그 좌표들이 만든 사각형 안에 포함되있다면 정답) */
		private int Point[];
		
		//private boolean reqClick;

	/**
		 * Instantiates a new minigame.
		 * @param ima 이미지
		 * @param strings 게임 정답
		 * @param howtoplay 미니게임 설명 
		 */
		Minigame(ImageIcon ima, String strings,String[] howtoplay) // 게임 생성
	{
		super(ima,strings,howtoplay);
		Point = new int[4];
	}
	
	/**
	 * Sets the x.
	 *
	 * @param num x에 대입
	 */
	void setX(int num) {
		x = num;
	}
	
	/**
	 * Sets the y.
	 *
	 * @param num y에 대입
	 */
	void setY(int num) {
		y = num;
	}
	
	/**
	 * Sets X and Y.
	 *
	 * @param num1 x값 인자로 받음
	 * @param num2 y값 인자로 받음
	 */
	void setXY(int num1,int num2) {
		x = num1;
		y=  num2;
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
	 * @param arr 4개의 좌표
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
	 *
	 * @param index 받고싶은 Point 인덱스 값
	 * @return the point[index]
	 */
	int getPoint(int index)
	{
		return Point[index];
	}
	
	/*
	
	 * 클릭이 필요한지 안 필요한지에 대하여 반환 
	 * 
	 * @return reqClick 반환
	 
	public boolean getreqclick() {
		return reqClick;
	}

	
	/**	reqClick이 클릭이 필요한지 안필요한지 setting
	 * @param bool = setting할 boolean값
	 
	public void setreqClick(boolean bool) {
		reqClick = bool;
	}
	*/
}
