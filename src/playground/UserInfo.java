package playground;

/** 로그인을 하고 사용자의 승패정보를 관리하기 위한 클래스 */
class UserInfo {
	/** 사용자 아이디 */
	String id;
	/** 사용자 비밀번호 */
	String pw;
	/** 사용자의 승리 횟수 */
	int win;
	/** 사용자의 패배 횟수 */
	int lose;
	
	/**
   	 * 사용자의 아이디와 비밀번호를 저장한다.
   	 * @param id - 사용자 아이디
   	 * @param pw - 사용자 비밀번호
   	 */
	UserInfo(String id, String pw) {
		this.id = id;
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}
}