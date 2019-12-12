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

/**로그인 뷰*/
class LoginView implements ActionListener {
	/**아이디 입력 필드*/
	private JTextField id;
	/**비밀번호 입력 필드*/
	private JTextField pw;
	/**로그인 버튼*/
	private JButton login;
	/**회원가입 버튼*/
	private JButton register;
	private Container c;
	/**로그인이 성공했을 시 실행될 뷰*/
	private CharacterSelectView cv;
	/**입력한 정보를 저장하기 위한 사용자 정보 객체*/
	private UserInfo user;
	/**캐릭터 선택 뷰에 넘기기 위한 인자*/
	private StatusManager player;
	/**로그인이 실패했을 시 실패 메시지를 띄우는 라벨*/
	private JLabel loginFail;

	JFrame frame;
	/**로그인 뷰 설정
	 * @param user - 사용자 정보
	 * @param player - 플레이어 정보*/
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
		
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		
		frame.setVisible(true);
	}

	/**로그인 버튼을 누르면 DB와 연동해 로그인 성공/실패를 판단한다.
	 * 성공시 캐릭터 선택 뷰로 넘어가고 실패시 실패 메시지를 라벨에 표시한다.
	 * 회원가입 버튼을 누르면 회원가입 뷰로 넘어간다.*/
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(login)) {
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
			RegisterView rv = new RegisterView(user);
		}

	}
}