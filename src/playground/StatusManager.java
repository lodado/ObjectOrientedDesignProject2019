package playground;

/** 플레이어의 상태를 관리하는 매니저 클래스 */
public class StatusManager {
	/** 플레이어의 정보*/
	GameCharacter player;
	
	/** 플레이어 상태를 설정한다. */
	public void setStatus(GameCharacter cha1) {
		this.player = cha1;
	}
	
	/** 플레이어 상태를 얻는다. */
	public GameCharacter getStatus() {
		return player;
	}
}
