package playground;

/** 플레이어의 상태를 관리하는 매니저 클래스 */
public class StatusManager {
	/** 플레이어의 정보*/
	Character player;
	
	public void setStatus(Character player) {
		this.player = player;
	}
	
	public Character getStatus() {
		return player;
	}
}
