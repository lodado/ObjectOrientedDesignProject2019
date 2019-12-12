package playground;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**ĳ���� ���� ��*/
public class CharacterSelectView{
	JFrame frame;
	/**����� ����*/
	UserInfo user;
	/**�÷��̾� ����*/
	StatusManager player;
	/**ĳ���͸� ���� �� ������ ��*/
	WaitingRoomView wv;
	
	/**ĳ���� ���� â �ʱ�ȭ
	 * @param user - ����� ����
	 * @param player - �÷��̾� ����
	 * */
	CharacterSelectView(UserInfo user, StatusManager player) {
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		this.user = user;
		this.player = player;
		frame.setTitle("Text Battle");
		frame.setSize(500, 500);
		frame.setResizable(false);
		/*
		ImageIcon ic1 = new ImageIcon("./cha1.jpg");
		ImageIcon ic2 = new ImageIcon("./cha2.jpg");
		ImageIcon ic3 = new ImageIcon("./cha3.jpg");
		Image im1 = ic1.getImage();
		Image im2 = ic2.getImage();
		Image im3 = ic3.getImage();
		Image nim1 = im1.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim2 = im2.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim3 = im3.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		ImageIcon icon1 = new ImageIcon(nim1);
		ImageIcon icon2 = new ImageIcon(nim2);
		ImageIcon icon3 = new ImageIcon(nim3);
		*/

		ImageIcon ic1 = (new ImageIcon(getClass().getClassLoader().getResource("image/cha1.png")));
		ImageIcon ic2  = (new ImageIcon(getClass().getClassLoader().getResource("image/cha2.png")));
		ImageIcon ic3 = (new ImageIcon(getClass().getClassLoader().getResource("image/cha3.png")));
		
		
		
		Image im1 = ic1.getImage();
		Image im2 = ic2.getImage();
		Image im3 = ic3.getImage();
		Image nim1 = im1.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim2 = im2.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		Image nim3 = im3.getScaledInstance(150, 300, Image.SCALE_DEFAULT);
		
		
		JLabel lbImage1 = new JLabel(new ImageIcon(nim1));
		JLabel lbImage2 = new JLabel(new ImageIcon(nim2));
		JLabel lbImage3 = new JLabel(new ImageIcon(nim3));
		
		
		
		lbImage1.setBounds(0, 0, 150, 300);
		lbImage2.setBounds(175, 0, 150, 300);
		lbImage3.setBounds(325, 0, 150, 300);

		JLabel dCha1 = new JLabel("�⺻��");
		JLabel dCha2 = new JLabel("������");
		JLabel dCha3 = new JLabel("�����");
		dCha1.setSize(150, 40);
		dCha2.setSize(150, 40);
		dCha3.setSize(150, 40);
		dCha1.setLocation(0, 330);
		dCha2.setLocation(175, 330);
		dCha3.setLocation(325, 330);

		// add panel to frame
		ButtonPanel buttonPanel = new ButtonPanel(frame, user, player);
		buttonPanel.setLayout(null);
		// ImagePanel imagePanel = new ImagePanel();
		Container contentPane = frame.getContentPane();

		contentPane.add(dCha1);
		contentPane.add(dCha2);
		contentPane.add(dCha3);
		contentPane.add(lbImage1);
		contentPane.add(lbImage2);
		contentPane.add(lbImage3);
		contentPane.add(buttonPanel);
		//���̱�
		frame.setVisible(true);
		
		// contentPane.add(panel3);
	}
}

/**��ư�� ��Ƶ� �г�*/
class ButtonPanel extends JPanel implements ActionListener {
	private JButton selectButton1;
	private JButton selectButton2;
	private JButton selectButton3;
	private JFrame frame;
	/**����� ����*/
	private UserInfo user;
	/**�÷��̾� ����*/
	private StatusManager player;
	/**ĳ���� ���� �� ������ ��*/
	WaitingRoomView wv;
	
	private GameCharacter cha1;
	private GameCharacter cha2;
	private GameCharacter cha3;
	
	/**�ʿ��� ���ڸ� �޾� �г��� �����
	 * @param user - ����� ����
	 * @param player - �÷��̾� ����*/
	public ButtonPanel(JFrame frame, UserInfo user, StatusManager player) {
		this.frame = frame;
		this.user = user;
		this.player = player;
		cha1 = new GameCharacter("����Ʈ", 100, 10, 10, 10, "image/cha1.png");
		cha2 = new GameCharacter("������", 80, 15, 5, 10, "image/cha2.png");
		cha3 = new GameCharacter("�����", 120, 5, 15, 10, "image/cha3.png");

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

	/**���� ��ư�� �´� ĳ���� ������ �÷��̾� ĳ���Ϳ� �����Ѵ�. ���� ���� �並 �����.*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(selectButton1)) {
			player.setStatus(cha1);
		}
		if (e.getSource().equals(selectButton2)) {
			player.setStatus(cha2);
		}
		if (e.getSource().equals(selectButton3)) {
			player.setStatus(cha3);
		}
		wv = new WaitingRoomView(user, player);
		frame.dispose();
	}

}