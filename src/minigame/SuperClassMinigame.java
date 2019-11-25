package minigame;

import java.util.List;

import javax.swing.ImageIcon;

/**
 * 미니게임을 담아두는 클래스의 super class (클릭이 필요없는 미니게임에만 이용)
 * 
 * @author Chungheon Yi
 *
 */

public class SuperClassMinigame {

	/** 게임 이미지 */
	private ImageIcon image;

	/** 게임 답 */
	private String str;

	/** 미니게임 pause 체크 하는데 쓰는 boolean 값 */
	private boolean isStop = false;
	
	/** 게임에 대한 설명을 담아놓는 문자열 배열, String[0]는 이 미니게임의 이름 */
	private String[] list = new String[4];
	
	/**
	 * 이 게임의 퀴즈와 정답을 설정한다.
	 * 
	 * @param ima 퀴즈의 이미지
	 * @param strings 퀴즈의 정답
	 * @param howtoplay 게임에 대한 설명을 담아놓는 문자열 배열, String[0]는 이 미니게임의 이름
	 */
	SuperClassMinigame(ImageIcon ima, String strings, String[] howtoplay) // 게임 생성
	{
		this.image = ima;

		str = new String(strings);
		setList(howtoplay);
	}

	/**
	 * 게임 이미지 반환
	 * 
	 * @return image 이미지 반환
	 */
	public ImageIcon getImage() {
		return image;
	}

	public void setImage(ImageIcon ima) {
		 image = ima;
	}
	/**
	 * 게임 답 반환
	 * 
	 * @return str 정답 반환
	 */
	public String getAnswer() {
		return str;
	}

	public void setAnswer(String strings) {
		this.str = new String(strings);
	}
	/**
	 * 미니게임 pause 체크 하는데 쓰는 boolean 값 반환
	 * @return isstop 반환
	 */
	public boolean getisStop() {
		return isStop;
	}

	
	/**	미니게임 pause 체크 하는데 쓰는 boolean 값 setting
	 * @param bool = setting할 boolean값
	 */
	public void setisStop(boolean bool) {
		isStop = bool;
	}
	
	/**
	 * Sets the list.
	 *
	 * @param getstring the new list, list[0]은 이 게임 이름
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
	 * Gets the list. list[0]는 이 미니게임 이름
	 *
	 * @param 인덱스 번호
	 * @return the list
	 */
	public String getList(int i)
	{
		return list[i];
	}
}