package playground;

/**스코어 목록에 스코어를 저장하기 위한 클래스*/
public class Score{
	/**닉네임*/
	String nickname;
	/**이긴 횟수*/
	int win;
	/**진 횟수*/
	int lose;
	
	/**스코어 설정
	 * @param nickname - 닉네임
	 * @param win - 이긴 횟수
	 * @param lose - 진 횟수*/
	Score(String nickname, int win, int lose){
		this.nickname = nickname;
		this.win = win;
		this.lose = lose;
	}
}
