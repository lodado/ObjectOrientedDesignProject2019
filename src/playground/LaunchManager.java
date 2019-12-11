package playground;

import javax.swing.JFrame;

/** 로그인 및 캐릭터 선택을 관리하는 클래스 */
public class LaunchManager {
	/** 사용자의 정보 */
	UserInfo user;
	
	/** 플레이하고 있는 캐릭터의 정보 */
	StatusManager player;
	LoginView lv;
	
	/** 필요한 정보를 저장한다.
	 * @param user - 사용자 정보
	 * @param player - 캐릭터 정보
	 * */
	LaunchManager(UserInfo user, StatusManager player) {
		this.user = user;
		this.player = player;
		lv = new LoginView(user, player);
	}

}