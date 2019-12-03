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
	public Item itemUse(Item I) {
		int i;
		for (i = 0; i < itemlist.size(); i++) {
			if (itemlist.get(i) == I)
				temp = itemlist.get(i);
			if (I.getItemId() == 6 || I.getItemId() == 7 || I.getItemId() == 8)
				itemlist.remove(i);

		}
		return temp;
	}
	public ArrayList<Item> getItemlist(){
		return itemlist;
	}
}