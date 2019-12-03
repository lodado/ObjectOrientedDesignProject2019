package playground;

import java.util.ArrayList;

/**캐릭터의 정보를 담는 클래스 */
public class GameCharacter {
	/** 캐릭터의 이름 */
	String name;
	/** 캐릭터의 체력 */
	int hp;
	/** 캐릭터의 공격력 */
	int off; 
	/** 캐릭터의 방어력 */
	int def; 
	/** 캐릭터의 민첩 */
	int agi; 
	/** 캐릭터의 이미지 */
	String image;
	//ArrayList<Item> inventory;
	//Item[] equip = new Item[2];
	/**
   	 * 캐릭터의 정보를 설정한다.
   	 * @param name - 캐릭터 이름
   	 * @param hp - 캐릭터 체력
   	 * @param off - 캐릭터 공격력
   	 * @param def - 캐릭터 방어력
   	 * @param agi - 캐릭터 민첩
   	 * @param image - 캐릭터 이미지
   	 */
	public GameCharacter(String name, int hp, int off, int def, int agi, String image) {
		this.name = name;
		this.hp = hp;
		this.off = off;
		this.def = def;
		this.agi = agi;
		this.image = image;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public synchronized int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		synchronized(this)
		{
			this.hp = hp;
		}
	}

	public int getOff() {
		return off;
	}

	public void setOff(int off) {
		this.off = off;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getAgi() {
		return agi;
	}

	public void setAgi(int agi) {
		this.agi = agi;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	/*
	public void setEquip(Item I)
	{
	   if(I.getItemId()<=5) {
	      equip[0] = I;
	   }
	   else if(9<=I.getItemId()&&I.getItemId()<=11) {
	      equip[1] = I;
	   }
	}
	*/
}