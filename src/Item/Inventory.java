package Item;

import java.util.ArrayList;

public class Inventory {
	private ArrayList<Item> itemlist = new ArrayList<>(); // inventory를 만들기위한 ArrayList
	Item temp; // 아이템 임시저장
	/** 인벤토리 아이템 추가 메소드 */
	public void pushItem(Item I) {
		if (itemlist.size() <= 9) {
			itemlist.add(I);
		}
	}

	/** 인벤토리 아이템 사용 메소드 */
	public void itemUse(Item I) {
		int i;
		int id=I.getItemId();
		for (i = 0; i < itemlist.size(); i++) {
			if ((id==6&&itemlist.get(i)== I)||(id==7&&itemlist.get(i)== I)&&(id==8&&itemlist.get(i)== I))
				itemlist.remove(i);
		}
		
	}
	public ArrayList<Item> getItemlist(){
		return itemlist;
	}
}