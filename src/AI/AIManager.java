/*
 * 
 */
package AI;

import java.util.LinkedList;

import javax.swing.ImageIcon;

import Map.*;
import playground.*;

/**
 * AI를 컨트롤 하는 클래스
 * @author Chungheon Yi
 *
 */
public class AIManager {

	/** 전체 AI를 담아두는 LinkedList */
	LinkedList<GameCharacter> AI = new LinkedList<>();
	
	/**
	 * AI를 설정하는 AIManager 생성자 .
	 *
	 * @param puthere 전체 맵을 인자로 받음
	 */
	public AIManager(map[] puthere)
	{
		String adj[]= {"똑똑한","유능한","부지런한","멍청한","귀여운","재빠른","얼빠진","키 큰"};
		String name[]= {"나연","정연","모모","사나","지효","미나","다현","채영","쯔위"};
		
		LinkedList<String> names = new LinkedList<>();
		for(int i=0; i<9; i++) names.add(name[i]);                           

		
		for(int i=0; i<5; i++) //AIgenerator i의 한계 = ai의 갯수
			{
			int num =(int)(Math.random()*10)%names.size();
			String Ainame = adj[(int)(Math.random()*10)%8]+" "+
					names.get(num); //이름 설정  
			
			String path="image/AI/";
			for(int j=0; j<9; j++)
			{
				if(name[j] == names.get(num))
				{
					path=path+j+".png";
				}
			}
			
			names.remove(num); //쓴 이름은 제거
			
			/**
		   	 * 캐릭터의 정보를 설정한다.
		   	 * @param name - 캐릭터 이름
		   	 * @param hp - 캐릭터 체력
		   	 * @param off - 캐릭터 공격력
		   	 * @param def - 캐릭터 방어력
		   	 * @param agi - 캐릭터 민첩
		   	 * @param image - 캐릭터 이미지
		   	 */
				AI.add(new GameCharacter(Ainame,100,4000,3,4,path));
				
				int popAILocation = (int)(Math.random()*10)%9; //이곳에 넣음
				
				puthere[popAILocation].addAI(AI.getLast());
				

				
			}
		}
	
	/**
	 * AI를 움직이는 Move algorithm.
	 *
	 * @param worldMap 맵 전체를 이곳에 넣음
	 * @param list 맵이 움직여도 되는지 안되는지 확인
	 */
	
	public void MoveAlgorithm(map[] worldMap,final LinkedList<Integer> list) 
	{
		LinkedList<GameCharacter> targetAI = new LinkedList<>(); //AI 수집 리스트 
		
		for(int a=0; a<9; a++)
		{
			for(int b=0; b<worldMap[a].getAINumber(); b++)
			{
				if(worldMap[a].getAI().get(b).getHp()>0) //체력 0이면 수집되지 않음 
						targetAI.add(worldMap[a].getAI().get(b)); //모든 AI 수집
			}
			
			worldMap[a].getAI().clear(); //수집후 제거
		}
		
		for(int a=0; a<targetAI.size(); a++)
		{
			int move; //이동할 위치 
			
			if(list.isEmpty()) //맵이 모두 닫겼으면 랜덤으로 이동
			{	 
				move= (int)(Math.random()*10)%9;
			}
		else //아니면 닫기지 않은곳으로 이동
			{
				move = list.get((int)(Math.random()*10)%list.size());
			}
			
			worldMap[move].addAI(targetAI.get(a));  //그곳에 AI를 넣어줌
			
			
		}
		
		for(int j=0; j<9; j++) {
			for(int x=0; x<worldMap[j].getAINumber(); x++)
		{
				System.out.print(worldMap[j].getMapName()+" :");
				System.out.print(worldMap[j].getAI().get(x).getName()+" " + worldMap[j].getAI().get(x).getHp()+"\n");
			
			} 
		}
			System.out.println();
	}
	
	/*
	 * 
	public void attackAlgorithm()
	{
		fightManager로 이동
	}
	
	*/	
	/**
	 * AI가 턴이 지날때마다 점점 쎄집니다.
	 */
	public void AIgetStronger()
	{
		for(int i=0; i<AI.size(); i++)
		{
			int HPbonus = (AI.get(i).getHp()+12);
			if(HPbonus>=100) AI.get(i).setHp(100);
			else AI.get(i).setHp(HPbonus); //100을 넘진 않음 
			
			AI.get(i).setDef(AI.get(i).getDef()+1);
			AI.get(i).setAgi(AI.get(i).getAgi()+1);
			AI.get(i).setOff(AI.get(i).getOff()+2);
			
			//방어력 1,민첩1,공격력 2 증가
		
		}
	}
	
	public LinkedList<GameCharacter> getList()
	{
		return AI;
	}
	
}
