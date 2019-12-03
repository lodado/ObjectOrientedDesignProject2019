package playground;

/** Character 클래스를 상속받은 AI 클래스 */
class AI extends Character{
	/** AI의 위치 */
	int place;
	/**
   	 * AI의 정보를 설정한다.
   	 * @param name - AI 이름
   	 * @param hp - AI 체력
   	 * @param off - AI 공격력
   	 * @param def - AI 방어력
   	 * @param agi - AI 민첩
   	 * @param image - AI 이미지
   	 * @param place - AI 위치
   	 */
	AI(String name, int hp, int off, int def, int agi, int place, String image) {
		super(name, hp, off, def, agi, image);
		this.place = place;
	}
}