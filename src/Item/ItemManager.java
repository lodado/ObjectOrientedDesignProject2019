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

/**
 * 아이템매니저
 * 
 * @author 박다원
 * 
 */
public class ItemManager {
	private Inventory inventory;
	private Item temp;
	private static GameCharacter cha1;

	public static void main(String[] args) {

	}

	public ItemManager(GameCharacter player, int result) {
		inventory = player.getInventory();
		temp = new Item(result);
		inventory.pushItem(temp);
		player.setInventory(inventory);
	}

}
