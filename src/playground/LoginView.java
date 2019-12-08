package playground;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
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

//로그인
class LoginView extends JFrame implements ActionListener {
	private JTextField id;
	private JTextField pw;
	private JButton login;
	private JButton register;
	private Container c;
	private CharacterSelectView cv;
	private UserInfo user;
	private StatusManager player;
	private JLabel loginFail;
	
	JFrame frame;

	LoginView(UserInfo user, StatusManager player) {
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
		
		login = new JButton("로그인");
		register = new JButton("회원가입");
		login.addActionListener(this);
		register.addActionListener(this);
		login.setBounds(213, 695, 129, 29);
		register.setBounds(517, 695, 129, 29);
		panel.add(login);
		panel.add(register);

		c = frame.getContentPane();

		c.add(panel);

		frame.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(login)) {
			System.out.println("로그인 버튼 눌림");
			System.out.println(id.getText());
			String loginResult = user.login(id.getText(), pw.getText());
			if (loginResult.equals("로그인 성공!")) {
				frame.setVisible(false);
				cv = new CharacterSelectView(user, player);
			}
			else {
				loginFail.setText(loginResult);
			}
		}

		if (e.getSource().equals(register)) {
			System.out.println("가입 버튼 눌림");
			RegisterView rv = new RegisterView(user);
		
		}

	}
}