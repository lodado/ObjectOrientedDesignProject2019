package playground;

import javax.swing.JFrame;

/** 로그인 및 캐릭터 선택을 관리하는 클래스 */
public class LaunchManager {
	/** 사용자의 정보 */
	UserInfo user;
	StatusManager player;
	LoginView lv;
	
	LaunchManager(UserInfo user, StatusManager player) {
		this.user = user;
		this.player = player;
		lv = new LoginView(user, player);
	}

}