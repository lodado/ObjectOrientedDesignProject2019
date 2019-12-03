package playground;

import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//캐릭터 선택 프레임
class CharacterSelectView extends JFrame {
	GameCharacter player;
	WaitingRoomView wv;
	
	CharacterSelectView(WaitingRoomView wv) {
		this.wv = wv;
		setTitle("Text Battle");
		setSize(500, 500);
		setResizable(false);
		ImageIcon ic1 = new ImageIcon("cha1.jpg");
		ImageIcon ic2 = new ImageIcon("cha2.jpg");
		ImageIcon ic3 = new ImageIcon("cha3.jpg");
		Image im1 = ic1.getImage();
		Image im2 = ic2.getImage();
		Image im3 = ic3.getImage();
		Image nim1 = im1.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim2 = im2.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim3 = im3.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		ImageIcon icon1 = new ImageIcon(nim1);
		ImageIcon icon2 = new ImageIcon(nim2);
		ImageIcon icon3 = new ImageIcon(nim3);

		JLabel lbImage1 = new JLabel(icon1);
		JLabel lbImage2 = new JLabel(icon2);
		JLabel lbImage3 = new JLabel(icon3);
		lbImage1.setBounds(0, 0, 150, 300);
		lbImage2.setBounds(175, 0, 150, 300);
		lbImage3.setBounds(325, 0, 150, 300);

		JLabel dCha1 = new JLabel("기본형");
		JLabel dCha2 = new JLabel("공격형");
		JLabel dCha3 = new JLabel("방어형");
		dCha1.setSize(150, 40);
		dCha2.setSize(150, 40);
		dCha3.setSize(150, 40);
		dCha1.setLocation(0, 330);
		dCha2.setLocation(175, 330);
		dCha3.setLocation(325, 330);

		// add panel to frame
		ButtonPanel buttonPanel = new ButtonPanel(player, this.wv);
		buttonPanel.setLayout(null);
		// ImagePanel imagePanel = new ImagePanel();
		Container contentPane = getContentPane();

		contentPane.add(dCha1);
		contentPane.add(dCha2);
		contentPane.add(dCha3);
		contentPane.add(lbImage1);
		contentPane.add(lbImage2);
		contentPane.add(lbImage3);
		contentPane.add(buttonPanel);
		//보이기
		setVisible(false);

		// contentPane.add(panel3);
	}
}

/**
 * A panel with three buttons.
 */

class ButtonPanel extends JPanel implements ActionListener {
	private JButton selectButton1;
	private JButton selectButton2;
	private JButton selectButton3;
	private GameCharacter player;
	WaitingRoomView wv;
	
	private GameCharacter cha1;
	private GameCharacter cha2;
	private GameCharacter cha3;
	public ButtonPanel(GameCharacter player, WaitingRoomView wv) {
		this.player = player;
		this.wv = wv;
		cha1 = new GameCharacter("디폴트", 100, 10, 10, 10, "default.jpg");
		cha2 = new GameCharacter("공격형", 80, 15, 5, 10, "attacker.jpg");
		cha3 = new GameCharacter("방어형", 120, 5, 15, 10, "tanker.jpg");

		// create buttons
		selectButton1 = new JButton("Select");
		selectButton2 = new JButton("Select");
		selectButton3 = new JButton("Select");

		selectButton1.setSize(150, 80);
		selectButton2.setSize(150, 80);
		selectButton3.setSize(150, 80);
		selectButton1.setLocation(0, 370);
		selectButton2.setLocation(175, 370);
		selectButton3.setLocation(325, 370);
		// add buttons to panel
		selectButton1.addActionListener(this);
		selectButton2.addActionListener(this);
		selectButton3.addActionListener(this);

		add(selectButton1);
		add(selectButton2);
		add(selectButton3);
		// create button actions
	}

	/**
	 * An action listener that sets the panel's background color.
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(selectButton1)) {
			player = cha1;
		}
		if (e.getSource().equals(selectButton2)) {
			player = cha2;
		}
		if (e.getSource().equals(selectButton3)) {
			player = cha3;
		}
		wv.setVisible(true);
	}

}