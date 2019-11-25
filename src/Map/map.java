package Map;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * 맵에 관한 클래스
 * @author Chungheon Lee(이충헌)
 *
 */

public class map
{
	/**맵 로케이션 위치(번호) 범위는 정수 {x ∈  Z , 0<=x<=8} */
	private int location; 
	
	/**  맵 이름 */
	private String mapName; 
	
	/** 그 대상 맵 안에 있는 유저들 수  */
	private int UserNumber;
	
	/** 그 맵의 이미지 */
	private ImageIcon image;
	
	/** flag for thread */
	private boolean flag = false;
	
			
	/** map을 setting 하는 map의 생성자
	 * @param mylocation  map에서 내 위치를 알려주는 변수값
	 * @param name        map의 위치의 이름
	 */
	public map(int mylocation,String name)
	{
		this.mapName = name;
		this.location = mylocation;
		UserNumber = 0;
	}
	
	
	/** @return 자기 위치 반환 */
	public int getLoc()
	{
		
		return location;
	}
	
	/** @return 자신 이름 반환  */
	public String getMapName()
	{
		return mapName;
	}
	
	/** 자기 장소를 setting 하는 메소드 
	 * @param setlocation 자기 장소(위치) 샛팅
	 * @param name 자기 이름 샛팅
	 */
	public void setLoc(int setlocation,String name)
	{
		this.location = setlocation;
		this.mapName = name;
	}
	
	/** 그 맵 안의 유저수를 반환하는 메소드 
	 * @return 지금 자기 자신 안의 총 유저수 반환
	 */
	public int getUserNumber()
	{
		return UserNumber;
	}
	
	/** 맵 안의 유저수를 설정하는 메소드 
	 * @param number  
	 */
	public void setUserNumber(int number)
	{
		this.UserNumber = number;
	}
	
	/** 맵의 이미지를 설정하는 메소드 
	 * @param ima 자기 이미지 지정
	 */
	public void setImage(ImageIcon ima)
	{
		image = (ima);
	}
	
	/** 자신의 이미지를 반환하는 메소드
	 * @return 자기 이미지 반환
	 */
	public ImageIcon getMapImage()
	{
		return image;
	}
	
	/**
	 * 화면 넘길때 쓰는 쓰레드 전용 진리값
	 */
	public void setFlag()
	{
		flag=!flag;
	}
	
	/**
	 * @return 진리값 반환
	 */
	public boolean getFlag()
	{
		return flag;
	}
}
