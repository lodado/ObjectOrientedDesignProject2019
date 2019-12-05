package AI;

import java.util.LinkedList;

import Map.*;
import playground.map;

/**
 * AI를 컨트롤 하는 클래스
 * @author Chungheon Yi
 *
 */
public class AIManager {

	/**
	 * AI를 설정하는 AIManager 생성자 .
	 */
	public AIManager(map[] puthere)
	{
		String adj[]= {"똑똑한","유능한","부지런한","멍청한","귀여운","재빠른","얼빠진","키 큰"};
		String name[]= {"조지","존","데이빗","홍길동","김범수","쯔위","주이","연우"};
		
		for(int i=0; i<5; i++) //AIgenerator i의 한계 = ai의 갯수
			{
			String Ainame = adj[(int)(Math.random()*10)%8]+
					name[(int)(Math.random()*10)%8]; //이름 설정 
			
				int popAILocation = (int)(Math.random()*10)%9; //이곳에 넣음
				puthere[popAILocation].setUserNumber
				(puthere[popAILocation].getUserNumber()+1);//해당 맵 위치에 유저수+1
			}
		
		
		
		
		
	}
	
	/**
	 * AI를 움직이는 Move algorithm.
	 *
	 * @param currentMap ai가 현재 있는 위치
	 * @param worldMap the world map
	 * @param list 맵이 움직여도 되는지 안되는지 확인
	 */
	public void MoveAlgorithm(map currentMap, map[] worldMap,final LinkedList<Integer> list) 
	{
		int current = currentMap.getLoc(); //나중에 AI 위치도 바꾸기 설정할것
		
		
		
		int move; //이동할 위치 
		
		if(list.isEmpty()) //맵이 모두 닫겼으면 랜덤으로 이동
			{	 
				move= (int)(Math.random()*10)%9;
			}
		else //아니면 닫기지 않은곳으로 이동
			{
				move = list.get((int)(Math.random()*10)%list.size());
			}
		
			currentMap.setUserNumber(currentMap.getUserNumber()-1);
			worldMap[move].setUserNumber(worldMap[move].getUserNumber()+1);  //이동
		
	}
	
	public void AttackAlgorithm()
	{
		
	}
}
