package playground;

/** 플레이어의 상태를 관리하는 매니저 클래스 */
public class StatusManager {
	/** 플레이어의 정보*/
	private GameCharacter player;
	private CharacterSelectView cv;
	
	StatusManager(CharacterSelectView cv){
		this.cv = cv;
		cv.player = player;
	}
	public void setStatus(GameCharacter player) {
		this.player = player;
	}
	
	public GameCharacter getStatus() {
		return player;
	}
}
