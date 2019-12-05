package playground;

/** 플레이어의 상태를 관리하는 매니저 클래스 */
public class StatusManager {
	/** 플레이어의 정보*/
	/**
	* 캐릭터의 정보를 설정한다.
    * @param name - 캐릭터 이름
    * @param hp - 캐릭터 체력
    * @param off - 캐릭터 공격력
    * @param def - 캐릭터 방어력
    * @param agi - 캐릭터 민첩
    * @param image - 캐릭터 이미지
    */
	GameCharacter player;
	
	public void setStatus(GameCharacter cha1) {
		this.player = cha1;
		
	}
	
	public GameCharacter getStatus() {
		return player;
	}
}
