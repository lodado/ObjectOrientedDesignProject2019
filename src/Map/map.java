/*
 * 
 */
package Map;

import java.awt.Image;
import java.util.LinkedList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import playground.GameCharacter;

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
	
	/** 그 맵의 이미지 */
	private ImageIcon image;
	
	/** 그 맵의 아이콘 이미지 */
	private ImageIcon iconImage;
	
	LinkedList<GameCharacter> AI = new LinkedList<>();
	
	/** flag for thread true 면 안닫긴거고 false면 닫김*/
	private boolean flag = true;
	
			
	/** map을 setting 하는 map의 생성자
	 * @param mylocation  map에서 내 위치를 알려주는 변수값
	 * @param name        map의 위치의 이름
	 */
	public map(int mylocation,String name)
	{
		this.mapName = name;
		this.location = mylocation;
	}
	
	
	/** @return 맵에서 자기 위치 반환 */
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
	 */
	public void setLoc(int setlocation)
	{
		this.location = setlocation;	
	}
	
	/** 그 맵 안의 유저수를 반환하는 메소드 
	 * @return 지금 자기 맵 안의 총 유저수 반환
	 */
	public int getAINumber()
	{
		return AI.size();
	}
	
	public LinkedList<GameCharacter> getAI(){
		return AI;
	}
	
	/** 맵 안의 AI를 더하는 메소드 
	 * @param number  
	 */
	public void addAI(GameCharacter AImove)
	{
		this.AI.add(AImove);
	}
	
	/**
	 * Pop 맵안의 AI를 빼는 메소드.
	 * @param name AI의 이름
	 */
	public void popAI(String name)
	{
		for(int i=0; i<AI.size(); i++)
			{	
				if(AI.get(i).getName() == name)
					{	
						AI.remove(AI.get(i));			
					}
			}
	}
	
	/** 맵의 이미지를 설정하는 메소드 
	 * @param ima 자기 이미지 지정
	 */
	public void setImage(ImageIcon ima)
	{
		image = (ima);
	}
	
	/** 맵의 아이콘 이미지를 설정하는 메소드 
	 * @param ima 자기 아이콘 이미지 지정
	 */
	public void setIconImage(ImageIcon ima)
	{
		iconImage = (ima);
	}
	
	/** 자신의 이미지를 반환하는 메소드
	 * @return 자기 이미지 반환
	 */
	public ImageIcon getMapImage()
	{
		return image;
	}
	
	/** 자신의 아이콘 이미지를 반환하는 메소드
	 * @return 자기 아이콘 이미지 반환
	 */
	public ImageIcon getIconImage()
	{
		return iconImage;
	}
	
	/**
	 * 화면 넘길때 쓰는 쓰레드 전용 진리값
	 */
	public void setFlag()
	{
		flag=!flag;
	}
	
	/** false면 위험 장소고 true면 안전 장소
	 * @return 진리값 반환
	 */
	public boolean getFlag()
	{
		return flag;
	}
}
