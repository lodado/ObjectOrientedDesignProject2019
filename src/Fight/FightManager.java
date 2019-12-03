package Fight;
import java.awt.*;
import AI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import Item.*;
import playground.*;
/**
 * @author 박다원 파이트매니저 클래스
 */
public class FightManager extends JFrame {
	private int AIturn = 5; // AI turn
	private int AIattack= (int) Math.random()*10 +10;
	private int playerturn = 5; // Player turn
	private int run = 2; // run할 수 있는 횟수
	private Item[] equip;// 장비
	private Inventory inventory; // inventory
	private int effect; // 아이템, 공격효과
	private int prob = (int) Math.random() * 10;// 도망, 방어 확률조정
	private boolean run_end = false; // 도망의 성공 실패

	public int attack(GameCharacter Player) {
		equip = Player.getEquip();
		effect = equip[0].getEffect();
		playerturn--;
		JFrame frame= new JFrame("attack");
		frame.setSize(200,200);
		frame.setLayout(null);
		JLabel fight = new JLabel(effect + "의 공격을 했음");
		fight.setLayout(null);
		fight.setBounds(0,50,200,100);
		frame.add(fight);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);
		
		return effect;
	}
	public int AIattack() {
		return AIattack;
	}
	public int AIrun() {
		
	}

	public int defense(GameCharacter Player) {
		equip = Player.getEquip();
		effect = equip[1].getEffect();
		if (prob <= 3) {
			effect = effect / 2 + 3;
		}
		if (3 < prob && prob <= 6) {
			effect = effect / 2 + 4;
		}
		if (6 < prob && prob <= 9) {
			effect = effect / 2 + 5;
		}
		playerturn--;
		return effect;
	}

	public int run() {
		if (prob < 3 && run>0) {
			run_end = true;// map
		}
		run--;
		playerturn--;
		return effect;
	}

	public int useItem(GameCharacter Player) {
		inventory = Player.getInventory();
		
		Player.setHp(effect);
		return effect;
	}

	FightManager(GameCharacter player, GameCharacter AI) {
		
		ImageIcon bk = new ImageIcon("배경화면.PNG");
		JLabel Image3 = new JLabel(bk);
		Image3.setLayout(null);
		Image3.setBounds(0, -70, 700, 700);
		int p_hp = player.getHp();
		int a_hp = AI.getStatus();
		JFrame frame = new JFrame("Fight Screen"); // 밑바탕
		frame.setSize(920, 920);
		frame.setLayout(null);
		LineBorder lb = new LineBorder(Color.black, 1);

		ImageIcon ic = new ImageIcon("캐릭터 예씨.PNG");
		JLabel Image = new JLabel(ic);
		Image.setLayout(null);
		Image.setBounds(0, 100, 200, 400);
		JLabel Image1 = new JLabel(ic);
		Image1.setLayout(null);
		Image1.setBounds(500, 100, 200, 400);

		Font f1 = new Font("바탕", Font.PLAIN, 25);
		JLabel label1 = new JLabel("         체력 :" + p_hp);
		JLabel label2 = new JLabel("       " + playerturn + "라운드");
		JLabel label3 = new JLabel("         체력 :" + a_hp);
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBounds(0, 0, 700, 700);
		
		label1.setFont(f1);
		label2.setFont(f1);
		label3.setFont(f1);
		label1.setBounds(0, 0, 250, 50);
		label2.setBounds(250, 0, 200, 50);
		label3.setBounds(450, 0, 250, 50);
		label1.setBorder(lb);
		label2.setBorder(lb);
		label3.setBorder(lb);
		JButton button1 = new JButton("공격하기");
		JButton button2 = new JButton("방어하기");
		JButton button3 = new JButton("아이템 사용");
		JButton button4 = new JButton("도망가기");

		button1.setLayout(null);
		button2.setLayout(null);
		button3.setLayout(null);
		button4.setLayout(null);

		button1.setBounds(0, 500, 300, 75);
		button2.setBounds(400, 500, 300, 75);
		button3.setBounds(0, 600, 300, 75);
		button4.setBounds(400, 600, 300, 75);

		button1.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("공격하기"))
					attack(player);
			}
		});
		button2.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("방어하기"))
					defense(player);
			}
		});
		button3.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("아이템 사용"))
					useItem(player);
			}
		});
		button4.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("도망가기"))
					run();
			}
		});


		frame.add(label1);
		frame.add(label2);
		frame.add(label3);
		frame.add(button1);
		frame.add(button2);
		frame.add(button3);
		frame.add(button4);
		frame.add(Image);
		frame.add(Image1);
		frame.add(Image3);
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}


