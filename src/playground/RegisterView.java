package playground;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Toolkit;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**회원가입 뷰*/
public class RegisterView {
	
	private JFrame frame;
	/**아이디 입력 필드*/
	private JTextField textField;
	/**닉네임 입력 필드*/
	private JTextField textField_1;
	/**비밀번호 입력 필드*/
	private JTextField textField_2;
	/**비밀번호 확인 필드*/
	private JTextField textField_3;
	/**DB와 연동하기 위한 객체*/
	private UserInfo user;
	
	/**회원가입이 성공하면 버튼의 상태를 바꾸기 위한 변수*/
	boolean registerCheck = false;

	/**회원가입 뷰 설정
	 * @param user - 사용자 정보*/
	public RegisterView(UserInfo user) {
		this.user = user;
		initialize();
		frame.setVisible(true);
	}

	/**프레임 설정*/
	private void initialize() {
		frame = new JFrame();
		frame.setSize(600, 600);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension screenSize = tk.getScreenSize();
		int screenHeight = screenSize.height;
		int screenWidth = screenSize.width;
		frame.setLocation(screenWidth / 4, screenHeight / 10);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 578, 544);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		JLabel lblId = new JLabel("ID");
		lblId.setBounds(99, 94, 82, 21);
		panel.add(lblId);

		JLabel lblNickname = new JLabel("Nickname");
		lblNickname.setBounds(96, 173, 82, 21);
		panel.add(lblNickname);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(99, 240, 82, 21);
		panel.add(lblPassword);

		JLabel lblPasswordCheck = new JLabel("Password Check");
		lblPasswordCheck.setBounds(91, 316, 152, 21);
		panel.add(lblPasswordCheck);

		textField = new JTextField();
		textField.setBounds(296, 91, 166, 27);
		panel.add(textField);
		textField.setColumns(10);

		textField_1 = new JTextField();
		textField_1.setBounds(296, 150, 166, 27);
		panel.add(textField_1);
		textField_1.setColumns(10);

		textField_2 = new JTextField();
		textField_2.setBounds(296, 237, 166, 27);
		panel.add(textField_2);
		textField_2.setColumns(10);

		textField_3 = new JTextField();
		textField_3.setBounds(296, 313, 166, 27);
		panel.add(textField_3);
		textField_3.setColumns(10);
		JLabel label = new JLabel("");
		label.setBounds(17, 411, 544, 21);
		panel.add(label);

		
		JButton btnRegister = new JButton("Register");
		btnRegister.setBounds(228, 459, 129, 29);
		panel.add(btnRegister);
		/**회원가입이 성공하면 registerCheck를 true로 바꿔 프레임이 꺼질 수 있도록 한다.*/
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (arg0.getSource().equals(btnRegister)) {
					if (registerCheck == false) {
						if (textField.getText().length() < 4 || textField.getText().length() > 15)
							label.setText("아이디는 4자 이상, 15자 이하여야 합니다.");
						else if (textField_1.getText().length() < 1 || textField_1.getText().length() > 15)
							label.setText("닉네임은 1자 이상, 15자 이하여야 합니다.");
						else if (!(textField_2.getText().equals(textField_3.getText())))
							label.setText("비밀번호를 다시 확인해주세요.");
						else {
							String result = user.register(textField.getText(), textField_2.getText(), textField_3.getText());
							if (result.equals("완료")) {
								result = "회원가입이 완료되었습니다. OK 버튼을 눌러주세요";
								btnRegister.setText("OK");
								registerCheck = true;
							}
							label.setText(result);
						}
					}
					else {
						frame.dispose();
					}
				}

			}
		});

	}
}
