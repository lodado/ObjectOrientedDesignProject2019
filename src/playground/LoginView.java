package playground;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**�α��� ��*/
class LoginView implements ActionListener {
	/**���̵� �Է� �ʵ�*/
	private JTextField id;
	/**��й�ȣ �Է� �ʵ�*/
	private JTextField pw;
	/**�α��� ��ư*/
	private JButton login;
	/**ȸ������ ��ư*/
	private JButton register;
	private Container c;
	/**�α����� �������� �� ����� ��*/
	private CharacterSelectView cv;
	/**�Է��� ������ �����ϱ� ���� ����� ���� ��ü*/
	private UserInfo user;
	/**ĳ���� ���� �信 �ѱ�� ���� ����*/
	private StatusManager player;
	/**�α����� �������� �� ���� �޽����� ���� ��*/
	private JLabel loginFail;

	JFrame frame;
	/**�α��� �� ����
	 * @param user - ����� ����
	 * @param player - �÷��̾� ����*/
	LoginView(UserInfo user, StatusManager player) {
		frame = new JFrame();
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		
		this.user = user;
		this.player = player;
		frame = new JFrame("Login");
		frame.setSize(920, 920);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setResizable(false);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 898, 864);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setFont(new Font("Gulim", Font.PLAIN, 18));
		lblId.setBounds(196, 222, 82, 21);
		panel.add(lblId);
		
		JLabel lblPassword = new JLabel("Password");
		lblId.setFont(new Font("Gulim", Font.PLAIN, 18));
		lblPassword.setBounds(196, 355, 82, 21);
		panel.add(lblPassword);
		
		loginFail = new JLabel("");
		loginFail.setBounds(196, 411, 750, 196);
		panel.add(loginFail);
		
		id = new JTextField();
		pw = new JTextField();
		id.setBounds(493, 219, 166, 27);
		pw.setBounds(493, 352, 166, 27);

		panel.add(id);
		panel.add(pw);

		id.setColumns(10);
		pw.setColumns(10);
		
		login = new JButton("�α���");
		register = new JButton("ȸ������");
		login.addActionListener(this);
		register.addActionListener(this);
		login.setBounds(213, 695, 129, 29);
		register.setBounds(517, 695, 129, 29);
		panel.add(login);
		panel.add(register);

		c = frame.getContentPane();

		c.add(panel);
		
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		
		frame.setVisible(true);
	}

	/**�α��� ��ư�� ������ DB�� ������ �α��� ����/���и� �Ǵ��Ѵ�.
	 * ������ ĳ���� ���� ��� �Ѿ�� ���н� ���� �޽����� �󺧿� ǥ���Ѵ�.
	 * ȸ������ ��ư�� ������ ȸ������ ��� �Ѿ��.*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(login)) {
			String loginResult = user.login(id.getText(), pw.getText());
			if (loginResult.equals("�α��� ����!")) {
				frame.setVisible(false);
				cv = new CharacterSelectView(user, player);
			}
			else {
				loginFail.setText(loginResult);
			}
		}

		if (e.getSource().equals(register)) {
			RegisterView rv = new RegisterView(user);
		}

	}
}