package Item;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import minigame.*;
import Map.*;
import playground.*;
public class Item {
	private int itemId; // 아이템 id
	private int effect; // 아이템 효과
	private String name;// 아이템 이름
	private ImageIcon manual;// 아이템 설명
	private ImageIcon image;// 아이템 이미지
	private int prob = 0; // 아이템 명중률

	/**
	 * @param int 아이템 생성자
	 */
	public Item(int result) {
		if (result == 1) {
			itemId = 1;
			name = "M16";
			effect = 7; // 아이템 무기 1번
			prob = 60;
			manual = new ImageIcon("./src/image/아이템설명1.PNG");
			image = new ImageIcon("./src/image/무기1.PNG");
		} else if (result == 2) {
			itemId = 2;
			name = "AK";
			prob = 65;
			manual = new ImageIcon("./src/image/아이템설명2.PNG");
			image = new ImageIcon("./src/image/무기2.PNG");
			effect = 10;// 아이템 무기 2번
		} else if (result == 3) {
			itemId = 3;
			name = "M4";
			manual = new ImageIcon("./src/image/아이템설명3.PNG");
			image = new ImageIcon("./src/image/무기3.PNG");
			prob = 70;
			effect = 13;// 아이템 무기 3번
		} else if (result == 4) {
			itemId = 4;
			name = "Kar-98";
			image = new ImageIcon("./src/image/무기4.PNG");
			prob = 40;
			manual = new ImageIcon("./src/image/아이템설명4.PNG");
			effect = 16;// 아이템 무기 4번
		} else if (result == 5) {
			itemId = 5;
			name = "AWP";
			manual = new ImageIcon("./src/image/아이템설명5.PNG");
			image = new ImageIcon("./src/image/무기5.PNG");
			prob = 60;
			effect = 19;// 아이템 무기 5번
		} else if (result == 6) {
			itemId = 6;
			name = "구급상자";
			image = new ImageIcon("./src/image/물약.PNG");
			manual = new ImageIcon("./src/image/아이템설명6.PNG");
			effect = 30;// 아이템 물약
		} else if (result == 7) {
			itemId = 7;
			name = "수류탄";
			image = new ImageIcon("./src/image/수류탄.PNG");
			manual = new ImageIcon("./src/image/아이템설명7.PNG");
			prob = 30;
			effect = 35; // 아이템 수류탄
		} else if (result == 8) {
			itemId = 8;
			image = new ImageIcon("./src/image/연막탄.PNG");
			manual = new ImageIcon("./src/image/아이템설명8.PNG");
			effect = -999; // 아이템 연막탄
		} else if (result == 9) {
			itemId = 9;
			name = "1렙 방어구";
			manual = new ImageIcon("./src/image/아이템설명9.PNG");
			image = new ImageIcon("./src/image/방어구1.PNG");
			effect = 25; // 아이템 방어구
		} else if (result == 10) {
			itemId = 10;
			image = new ImageIcon("./src/image/방어구2.PNG");
			manual = new ImageIcon("./src/image/아이템설명10.PNG");
			name = "2렙 방어구";
			effect = 30;// 아이템 방어구
		} else if (result == 11) {
			itemId = 11;
			image = new ImageIcon("./src/image/방어구3.PNG");
			manual = new ImageIcon("./src/image/아이템설명11.PNG");
			name = "3렙 방어구";
			effect = 35;// 아이템 방어구
		}
	}
	
	/** 이미지 getter */
	public ImageIcon getManual() {
		return manual;
	}
	/** 이미지 getter */
	public ImageIcon getImage() {
		return image;
	}
	
	/** 아이템 아이디 getter */
	public int getItemId() {
		return itemId; // 아이템 id getter
	}

	/** 아이템효과 getter */
	public int getEffect() {
		return effect; // 아이템 효과 getter
	}

	/** 아이템효과 setter */
	public void setEffect(int effect) {
		this.effect = effect;
	}

	/** 아이템이름 getter */
	public String getName() {
		return name;
	}
}