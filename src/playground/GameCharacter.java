package playground;
import java.util.ArrayList;
import Item.*;
/**ĳ������ ������ ��� Ŭ���� */
public class GameCharacter {
   /** ĳ������ �̸� */
   String name;
   /** ĳ������ ü�� */
   int hp;
   /** ĳ������ ���ݷ� */
   int off; 
   /** ĳ������ ���� */
   int def; 
   /** ĳ������ ��ø */
   int agi; 
   /** ĳ������ �̹��� */
   String image;
   Inventory inventory= new Inventory();
   Item[] equip = new Item[2];
   /**
       * ĳ������ ������ �����Ѵ�.
       * @param name - ĳ���� �̸�
       * @param hp - ĳ���� ü��
       * @param off - ĳ���� ���ݷ�
       * @param def - ĳ���� ����
       * @param agi - ĳ���� ��ø
       * @param image - ĳ���� �̹���
       */
   public GameCharacter(String name, int hp, int off, int def, int agi, String image) {
      this.name = name;
      this.hp = hp;
      this.off = off;
      this.def = def;
      this.agi = agi;
      this.image = image;
      this.equip[0] = new Item(-3);
      this.equip[1] = new Item(-4);
      this.inventory.pushItem(new Item(6));
      this.inventory.pushItem(new Item(7));
      this.inventory.pushItem(new Item(8));
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public int getHp() {

	   synchronized(this)
	   {
		   return hp;
	   }
   }

   public void setHp(int hp) {
	   synchronized (this){
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
   
   public void setEquip(Item I)
   {
      if(I.getItemId()<=5) {
         equip[0] = I;
      }
      else if(9<=I.getItemId()&&I.getItemId()<=11) {
         equip[1] = I;
      }
   }
   public Item[] getEquip() {
	   return equip;
   }
   /** �κ��丮 setter
 * @return */
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	public Inventory getInventory() {
		return inventory;
	}
}