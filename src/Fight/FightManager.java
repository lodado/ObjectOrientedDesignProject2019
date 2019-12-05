package Fight;

import java.awt.*;
import AI.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import Item.*;
import playground.*;
import Map.*;

// TODO: Auto-generated Javadoc
/**
 * The Class FightManager.
 *
 * @author 박다원 파이트매니저 클래스
 */
public class FightManager extends JFrame{
	
	/** The A iturn. */
	private int AIturn = 5; // AI turn
	
	/** The playerturn. */
	private int playerturn = 5; // Player turn
	
	/** The equip. */
	private Item[] equip = new Item[2];// 장비
	
	/** The effect. */
	private int effect = 0; // 아이템, 공격효과
	
	/** The run result. */
	private int run_result = 0; // 도망의 성공 실패
	
	/** The manager. */
	private Mapmanager manager;
	
	/** The Player. */
	private GameCharacter Player;
	
	/** The t1. */
	Thread T1;
	
	
	/** The des. */
	JLabel des[] = new JLabel[11];
	
	/** The frame. */
	JFrame frame = new JFrame("Fight Screen");
	
	/**
	 * Player attack.
	 *
	 * @param Player the player
	 * @return the int
	 */
	public int playerAttack(GameCharacter Player) {
		equip = Player.getEquip();
		effect = equip[0].getEffect();
		int prob = equip[0].getProb();
		int random = (int) (Math.random() * 10);
		if (prob < random)
			effect = 0;
		return effect;
	}

	/**
	 * A iattack.
	 *
	 * @return the int
	 */
	public int AIattack() {
		int AIattack = (int) (Math.random() * 10) + 10;
		return AIattack;
	}

	/**
	 * A irun.
	 *
	 * @return the int
	 */
	public int AIrun() {
		return -999;
	}

	/**
	 * Defence.
	 *
	 * @param Player the player
	 * @return the int
	 */
	public int defence(GameCharacter Player) {

		int prob = (int) Math.random() * 10;
		equip = Player.getEquip();
		effect = equip[1].getEffect();
		if (prob <= 3) {
			effect = effect / 10 + 3;
		}
		if (3 < prob && prob <= 6) {
			effect = effect / 10 + 4;
		}
		if (6 < prob && prob <= 9) {
			effect = effect / 10 + 5;
		}
		return effect;
	}

	/**
	 * Run.
	 *
	 * @param player the player
	 * @return the int
	 */
	public int run(GameCharacter player) {
		int prob = (int) (Math.random() * 10);
		int agi = player.getAgi();
		prob = prob + agi / 10;
		if (prob > 12) {
			run_result = -999;
		} else
			run_result = 0;
		return run_result;
	}

	/**
	 * A ioperate.
	 *
	 * @param player the player
	 * @param textarea the textarea
	 */
	public void AIoperate(GameCharacter player, JTextArea textarea) {
		int random = (int) (Math.random() * 10);
		if (random < 1) {
			int run = AIrun();
			if (run == -999) {
				textarea.append("AI가 도망갔습니다. 싸움을 종료합니다\n"); // 도망성공 창 띄우고 나가기 버튼으로 맵매니저 호출

			}
		} else {
			int AIattack = AIattack();
			int player_hp = player.getHp();
			player_hp = player_hp - AIattack;
			int random1 = (int) (Math.random() * 10);
			if (random1 > 3) {
				player.setHp(player_hp);
				textarea.append("AI가" + AIattack + "의 공격\n");
			} else
				textarea.append("AI 공격실패\n");
		}
	}

	/**
	 * A ioperate.
	 *
	 * @param player the player
	 * @param defence the defence
	 * @param textarea the textarea
	 */
	public void AIoperate(GameCharacter player, int defence, JTextArea textarea) {
		int random = (int) (Math.random() * 10);
		if (random < 1) {
			int run = AIrun();
			if (run == -999) {
				textarea.append("AI가 도망갔습니다. 싸움을 종료합니다\n"); // 도망성공 창 띄우고 나가기 버튼으로 맵매니저 호출
			}
		} else {
			int AIattack = AIattack();
			int player_hp = player.getHp();
			int random1 = (int) (Math.random() * 10);
			if (random1 > 3) {
				AIattack = AIattack - defence;
				player_hp = player_hp - AIattack;
				player.setHp(player_hp);
				textarea.append("AI가" + AIattack + "의 공격\n");
			} else
				textarea.append("AI 공격실패\n");
		}
	}

	/**
	 * Checks if is end.
	 *
	 * @param player the player
	 * @param AI the ai
	 */
	public void isEnd(GameCharacter player, GameCharacter AI) {
		if (playerturn == 0) {
			System.out.print("턴 끝");// mapManger 호출
			
			/*돌릴때 실행할 부분 */
			manager.getMapFrame().setVisible(true);
			manager.getMapFrame().setDefaultCloseOperation(EXIT_ON_CLOSE);
			T1.interrupt();
			/**/
			
			frame.dispose();
		}
		if (player.getHp() <= 0) {
			System.out.print("플레이어 사망 끝");// 사망
			JFrame frame = new JFrame("END");
			frame.setSize(200, 200);
			frame.setLayout(null);
			JLabel end = new JLabel("                   패배");
			end.setLayout(null);
			end.setBounds(0, 50, 200, 50);
			JButton out = new JButton("나가기");
			out = new JButton("나가기");
			out.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
					
			out.setLayout(null);
			out.setBounds(50, 100, 100, 50);
			frame.add(out);
			frame.add(end);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);

		}
		if (AI.getHp() <= 50) {
			System.out.print("에이아이 사망 끝");


			JFrame frame = new JFrame("END");
			frame.setSize(200, 200);
			frame.setLayout(null);
			JLabel end = new JLabel("                   승리");
			end.setLayout(null);
			end.setBounds(0, 50, 200, 50);
			JButton out = new JButton("나가기");
			out = new JButton("나가기");
			out.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
			out.setLayout(null);
			out.setBounds(50, 100, 100, 50);
			frame.add(out);
			frame.add(end);
			frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			frame.setVisible(true);
			// 승리 팝업 후 돌아가기 호출
		}
	}

	/**
	 * Instantiates a new fight manager.
	 *
	 * @param player the player
	 * @param AI the ai
	 * @param mana the mana
	 * @param m the m
	 */
	public FightManager(GameCharacter player, GameCharacter AI, Mapmanager mana, map m) {

		
		
		Player = player;
		
		manager = mana;
		
		ImageIcon bk = new ImageIcon("배경화면.PNG");
		JLabel Image3 = new JLabel(bk);
		Image3.setLayout(null);
		Image3.setBounds(0, -70, 700, 700);

		 // 밑바탕
		frame.setSize(940, 940);
		frame.setLayout(null);
		LineBorder lb = new LineBorder(Color.black, 1);
		JTextArea textarea = new JTextArea();
		Font f1 = new Font("바탕", Font.PLAIN, 22);
		textarea.setFont(f1);
		String playerimage = player.getImage();
		String aiimage = AI.getImage();
		ImageIcon ic = new ImageIcon("캐릭터1.PNG");
		JLabel Image = new JLabel(ic);
		Image.setBounds(0, 153, 230, 459);
		JLabel Image1 = new JLabel(ic);
		Image1.setLayout(null);
		Image1.setBounds(690, 153, 230, 459);

		
		JLabel label1 = new JLabel("         체력 :" + player.getHp());
		JLabel label2 = new JLabel("                        " + playerturn + "라운드");
		JLabel label3 = new JLabel("         체력 :" + AI.getHp());

		JProgressBar ChaHp = new JProgressBar(0,100);
		
		ChaHp.setStringPainted(true);
		if(m.getFlag()) ChaHp.setForeground(Color.RED);
		else  ChaHp.setForeground(Color.MAGENTA);
		
		ChaHp.setValue(Player.getHp()); // 여기에 Character HP 대입
		ChaHp.setStringPainted(true);
		
		JProgressBar AIHp = new JProgressBar(0,100);
		AIHp.setStringPainted(true);
		if(m.getFlag()) AIHp.setForeground(Color.RED);
		else  AIHp.setForeground(Color.MAGENTA);
		
		AIHp.setValue(Player.getHp()); // 여기에 Character HP 대입
		AIHp.setStringPainted(true);
		
		T1 = new Thread()  //@author ChungHeon Yi
				{
			@Override
			public void run() {
					int i= 0;
				try {
					
					while (true) {
						
						int PLAYERHP = Player.getHp();
						int AIHP = AI.getHp(); // sync 오버헤드 방지 
						
						System.out.println("dd");
						manager.setHP(PLAYERHP,m,ChaHp);   //currentHP에 캐릭터 HP를 넣을것
						manager.setHP(AIHP, m, AIHp);
						label1.setText("         체력 :" + PLAYERHP);
						label3.setText("         체력 :" + AIHP);
						
						Thread.sleep(10); // 1초씩 sleep. 딜레이때문에 정확하진 않지만 게임 진행엔 큰 무리가 없음
					}
				} catch (InterruptedException e) {
					
					// Interrupted catch - while문 종료

				} catch (Exception e) {
					e.printStackTrace(); // 그외 오류면 출력
				}	
			}
		};		
		
		T1.start();
		
		ChaHp.setBounds(0,0,230,80);
		AIHp.setBounds(690,0,230,80);
		
		frame.add(ChaHp);
		frame.add(AIHp);
		
		label1.setFont(f1);
		label2.setFont(f1);
		label3.setFont(f1);
		label1.setBounds(0, 101, 230, 53);
		label2.setBounds(230, 0, 460, 153);
		label3.setBounds(690, 101, 230, 53);
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

		button1.setBounds(0, 620, 230, 140);
		button2.setBounds(690, 620, 230, 140);
		button3.setBounds(0, 774, 230, 140);
		button4.setBounds(690, 774, 230, 140);

		button1.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("공격하기")) {
					int effect = playerAttack(player);

					if (effect == 0) {
						textarea.append(playerturn + "라운드: player 공격 실패\n");
						playerturn--;
						AIoperate(player, textarea);// 텍스트로 공격 실패
						isEnd(player, AI);
						label1.setText("         체력 :" + player.getHp());
						
					} else {

						int AIhp = AI.getHp(); // AI hp 가져오기
						AIhp = AIhp - effect;
						AI.setHp(AIhp); // 공격 효과 세팅
						isEnd(player, AI);
						label3.setText("         체력 :" + AI.getHp());
						textarea.append(playerturn + "라운드: player" + effect + "의 공격 성공\n");
						playerturn--;
						AIoperate(player, textarea);// 텍스트로 공격 성공 얼마나 공격을 입혓는지 텍스트 입력
						isEnd(player, AI);
						label1.setText("         체력 :" + player.getHp());
					}
				}
			}
		});
		button2.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("방어하기")) {
					int player_defence = defence(player);
					textarea.append(playerturn + "라운드: player 방어선택\n");
					playerturn--;
				
					
					AIoperate(player, player_defence, textarea);
					label1.setText("         체력 :" + player.getHp());
					if (playerturn == 0) {
						System.out.print("턴 끝");// mapManger 호출
					}
					if (player.getHp() < 0) {
						System.out.print("플레이어 사망 끝");// 사망
					}
					if (AI.getHp() < 0) {
						System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
					}
				}
			}
		});
		button3.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("아이템 사용")) {
					Inventory inventory = new Inventory();
					inventory = player.getInventory();
					JFrame frame = new JFrame("Inventory screen");
					frame.setSize(520, 540);
					frame.setLayout(null);
					JButton[] button = new JButton[15];
					JButton out = new JButton();
					ImageIcon[] image = new ImageIcon[11];
					ImageIcon[] manual = new ImageIcon[11];
					final int[] id = new int[10];
					ArrayList<Item> playerinventory = new ArrayList<Item>();
					playerinventory = inventory.getItemlist();
					for (int i = 0; i < playerinventory.size(); i++) {
						
						Item temp = playerinventory.get(i);
						image[i] = temp.getImage();
						manual[i] = temp.getManual();
						des[i] = new JLabel(manual[i]);
						String name = temp.getName();
						id[i] = temp.getItemId();

						button[i] = new JButton(name, image[i]);
						button[i].setLayout(null);
						if (i <= 2)
							button[i].setBounds(100 * (i), 0, 100, 100);
						if (2 < i && i < 6)
							button[i].setBounds(100 * (i - 3), 100, 100, 100);
						if (6 <= i && i < 9)
							button[i].setBounds(100 * (i - 6), 200, 100, 100);
						if (9 <= i && i < 12)
							button[i].setBounds(100 * (i - 9), 200, 100, 100);
						frame.add(button[i]);

						final int mynum = i;

						if (id[mynum] == 1 || id[mynum] == 2 || id[mynum] == 3 || id[mynum] == 4 || id[mynum] == 5
								|| id[mynum] == 9 || id[mynum] == 10 || id[mynum] == 11) {
							button[i].addMouseListener(new MouseListener() {
								public void mouseClicked(MouseEvent e) {
									if (e.getClickCount() == 2) {
										System.out.println("click");
										player.setEquip(temp);
										JFrame frame = new JFrame("장착완료");
										frame.setSize(200, 200);
										frame.setLayout(null);
										JLabel set = new JLabel("             장착 완료했습니다");
										set.setLayout(null);
										set.setBounds(0, 10, 200, 100);
										frame.add(set);
										JButton ok = new JButton("완료");
										ok.setLayout(null);
										ok.setBounds(50, 100, 100, 50);
										frame.add(ok);
										ok.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
											public void actionPerformed(ActionEvent e) {
												frame.dispose();
											}
										});

										frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										frame.setVisible(true);
										System.out.print(player.getEquip()[0].getName());
									}
								}

								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub

								}

							});
						} else if (id[mynum] == 6 || id[mynum] == 7 || id[mynum] == 8) {
							button[i].addMouseListener(new MouseListener() {
								public void mouseClicked(MouseEvent e) {
									if (e.getClickCount() == 2) {
										System.out.println("click");
										if (id[mynum] == 6) {
											int hp = player.getHp();
											effect = temp.getEffect();
											Inventory inventory = player.getInventory();
											inventory.itemUse(temp);
											player.setInventory(inventory);
											frame.remove(button[mynum]);
											hp = hp + effect;
											player.setHp(hp);
											textarea.append(playerturn + "라운드: player가" + effect + "의 회복 물약사용\n");
											playerturn--;
											label1.setText("         체력 :" + player.getHp());
											AIoperate(player, textarea);
											System.out.println("아이템사용" + playerturn);
											if (playerturn == 0) {
												System.out.print("턴 끝");// mapManger 호출
											}
											if (player.getHp() < 0) {
												System.out.print("플레이어 사망 끝");// 사망
											}
											if (AI.getHp() < 0) {
												System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
											}
										} else if (id[mynum] == 7) {
											int random = (int) (Math.random() * 10);
											if (temp.getProb() > random) {
												int hp = AI.getHp();
												effect = temp.getEffect();
												hp = hp - effect;
												Inventory inventory = player.getInventory();
												inventory.itemUse(temp);
												player.setInventory(inventory);
												frame.remove(button[mynum]);
												AI.setHp(hp);
												textarea.append(playerturn + "라운드: player 수류탄으로 " + effect + "의 공격\n");
												playerturn--;
												label3.setText("         체력 :" + AI.getHp());
												AIoperate(player, textarea);
												label1.setText("         체력 :" + player.getHp());
												System.out.println("방어" + playerturn);
												if (playerturn == 0) {
													System.out.print("턴 끝");// mapManger 호출
												}
												if (player.getHp() < 0) {
													System.out.print("플레이어 사망 끝");// 사망
												}
												if (AI.getHp() < 0) {
													System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
												}
											} else {
												textarea.append(playerturn + "라운드: player 수류탄 공격실패\n");
												playerturn--;
												AIoperate(player, textarea);
												label1.setText("         체력 :" + player.getHp());
												if (playerturn == 0) {
													System.out.print("턴 끝");// mapManger 호출
												}
												if (player.getHp() < 0) {
													System.out.print("플레이어 사망 끝");// 사망
												}
												if (AI.getHp() < 0) {
													System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
												}
												// text로 사용실패띄워주기
											}
										} else if (id[mynum] == 8) {
											int random = (int) (Math.random() * 10);
											if (temp.getProb() > random) {
												textarea.append(playerturn + "라운드: player 연막탄사용 AI 1턴 삭제 n");
												playerturn--;

												if (playerturn == 0) {
													System.out.print("턴 끝");// mapManger 호출
												}
												if (player.getHp() < 0) {
													System.out.print("플레이어 사망 끝");// 사망
												}
												if (AI.getHp() < 0) {
													System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
												}
												// text로 연막탄 사용성공,한턴 무효화
											} else {
												textarea.append(playerturn + "라운드: player 연막탄실패 \n");
												playerturn--;
												AIoperate(player, textarea);
												if (playerturn == 0) {
													System.out.print("턴 끝");// mapManger 호출
												}
												if (player.getHp() < 0) {
													System.out.print("플레이어 사망 끝");// 사망
												}
												if (AI.getHp() < 0) {
													System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
												}
											}

										}
										JFrame frame = new JFrame("사용완료");
										frame.setSize(200, 200);
										frame.setLayout(null);
										JLabel set = new JLabel("             사용했습니다");
										set.setLayout(null);
										set.setBounds(0, 10, 200, 100);
										frame.add(set);
										JButton ok = new JButton("완료");
										ok.setLayout(null);
										ok.setBounds(50, 100, 100, 50);
										frame.add(ok);
										ok.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
											public void actionPerformed(ActionEvent e) {
												frame.dispose();
											}
										});

										frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
										frame.setVisible(true);
									}
								}

								@Override
								public void mousePressed(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseReleased(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseEntered(MouseEvent e) {
									// TODO Auto-generated method stub

								}

								@Override
								public void mouseExited(MouseEvent e) {
									// TODO Auto-generated method stub

								}

							});
						}
						button[i].addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
							public void actionPerformed(ActionEvent e) {
								System.out.println("mynum:" + mynum);
								des[mynum].setLayout(null);
								des[mynum].setBounds(300, 0, 200, 400);
								des[mynum].setVisible(true);
								frame.add(des[mynum]);
							}
						});
					}

					out = new JButton("나가기");
					out.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
						public void actionPerformed(ActionEvent e) {
							frame.dispose();
						}
					});
					out.setLayout(null);
					out.setBounds(300, 400, 200, 100);
					frame.add(out);
					frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
					frame.setVisible(true);
				}
			}
		});
		button4.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
			public void actionPerformed(ActionEvent e) {
				JButton button1 = (JButton) e.getSource();
				if (button1.getText().equals("도망가기")) {
					int run = run(player);
					if (run == -999) {
						System.out.println("도망성공");
						JFrame frame = new JFrame("run");
						frame.setSize(200, 200);
						frame.setLayout(null);
						JLabel Run = new JLabel("                   도망성공");
						Run.setLayout(null);
						Run.setBounds(0, 50, 200, 50);
						JButton out = new JButton("나가기");
						out = new JButton("나가기");
						out.addActionListener(new ActionListener() { // 익명클래스로 리스너 작성
							public void actionPerformed(ActionEvent e) {
								frame.dispose();
							}
						});
						out.setLayout(null);
						out.setBounds(50, 100, 100, 50);
						frame.add(out);
						frame.add(Run);
						frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
						frame.setVisible(true);
						// 맵매니저 호출

					} else {
						textarea.append(playerturn + "라운드: player 도망 실패\n");
						playerturn--;
						AIoperate(player, textarea);
						label1.setText("         체력 :" + player.getHp());
						if (AIturn <= 0) {
							System.out.print("턴 끝");// mapManger 호출
						}
						if (player.getHp() < 0) {
							System.out.print("플레이어 사망 끝");// 사망
						}
						if (AI.getHp() < 0) {
							System.out.print("에이아이 사망 끝");// 승리 팝업 후 돌아가기 호출
						}
						// 도망실패 팝업
					}
				}
			}
		});
		JScrollPane scroll = new JScrollPane(textarea);
		scroll.setSize(440, 750);

		JPanel panel = new JPanel();// 텍스트 붙일 패널
		panel.setLayout(null);
		panel.setBounds(250, 153, 420, 910);
		panel.add(scroll);
		frame.add(panel);
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
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true);

	}
}
