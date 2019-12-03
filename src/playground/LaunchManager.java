package playground;

import javax.swing.JFrame;
import Map.*;

/** 로그인 및 캐릭터 선택을 관리하는 클래스 */
public class LaunchManager {
	/** 사용자의 정보 */
	UserInfo userInfo = new UserInfo(null, null);
	LoginView lv;

	LaunchManager(CharacterSelectView cv) {
		lv = new LoginView(userInfo, cv);
		
		
		
	}



	
	 
}