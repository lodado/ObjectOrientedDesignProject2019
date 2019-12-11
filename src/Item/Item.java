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

/**아이템 클래스
 * @author 박다원
 *
 */
public class Item {
	private int itemId; // 아이템 id
	private int effect; // 아이템 효과
	private String name;// 아이템 이름
	private ImageIcon manual;// 아이템 설명
	private ImageIcon image;// 아이템 이미지
	private int prob = 0; // 아이템 명중률

	
	/**아이템 생성자
	 * @param result
	 */
	public Item(int result) {
		
	 if(result ==-3) {
			itemId = -3;
			effect = 5;
			prob = 5;
		} // 기본 공격 장비
	 else if(result ==-4) {
		 itemId =-4;
		 effect =6;
	 }
		else if (result == 1) {
			itemId = 1;
			name = "M16";
			effect = 7; // 아이템 무기 1번
			prob = 6;
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des1.png"));
			image = new ImageIcon(getClass().getClassLoader().getResource("image/w1.png"));
		} else if (result == 2) {
			itemId = 2;
			name = "AK";
			prob = 6;
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des2.png"));
			image = new ImageIcon(getClass().getClassLoader().getResource("image/w2.png"));
			effect = 10;// 아이템 무기 2번
		} else if (result == 3) {
			itemId = 3;
			name = "M4";
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des3.png"));
			image = new ImageIcon(getClass().getClassLoader().getResource("image/w3.png"));
			prob = 7;
			effect = 13;// 아이템 무기 3번
		} else if (result == 4) {
			itemId = 4;
			name = "Kar-98";
			image = new ImageIcon(getClass().getClassLoader().getResource("image/w4.png"));
			prob = 4;
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des4.png"));
			effect = 22;// 아이템 무기 4번
		} else if (result == 5) {
			itemId = 5;
			name = "AWP";
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/w5.png"));
			image = new ImageIcon(getClass().getClassLoader().getResource("image/w5.png"));
			prob = 6;
			effect = 30;// 아이템 무기 5번
		} else if (result == 6) {
			itemId = 6;
			name = "구급상자";
			image = new ImageIcon(getClass().getClassLoader().getResource("image/p.png"));
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des6.png"));
			effect = 30;// 아이템 물약
		} else if (result == 7) {
			itemId = 7;
			name = "수류탄";
			image = new ImageIcon(getClass().getClassLoader().getResource("image/s.png"));
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des7.png"));
			prob = 5;
			effect = 35; // 아이템 수류탄
		} else if (result == 8) {
			itemId = 8;
			image = new ImageIcon(getClass().getClassLoader().getResource("image/m.png"));
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des8.png"));
			prob = 5;
			effect = -999; // 아이템 연막탄
		} else if (result == 9) {
			itemId = 9;
			name = "1렙 방어구";
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des9.png"));
			image = new ImageIcon(getClass().getClassLoader().getResource("image/a1.png"));
			effect = 20; // 아이템 방어구
		} else if (result == 10) {
			itemId = 10;
			image = new ImageIcon(getClass().getClassLoader().getResource("image/a2.png"));
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des10.png"));
			name = "2렙 방어구";
			effect = 30;// 아이템 방어구
		} else if (result == 11) {
			itemId = 11;
			image = new ImageIcon(getClass().getClassLoader().getResource("image/a3.png"));
			manual = new ImageIcon(getClass().getClassLoader().getResource("image/des11.png"));
			name = "3렙 방어구";
			effect = 40;// 아이템 방어구
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
	/** 아이템명중률 getter */
	public int getProb() {
		return prob;
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