package playground;

public class Main {
	public static int timer = 0;

	public static void main(String args[]) {
		UserInfo user = new UserInfo();
		StatusManager player = new StatusManager();

		 //DB���� �����ϰ������ �̰� ���� 
		/*
		player.setStatus(new GameCharacter("PlayerName", 100, 10, 20, 45, "image/cha3.png"));
		WaitingRoomView wv = new WaitingRoomView(user, player);
		wv.gamestart();	
		*/
		LaunchManager lm = new LaunchManager(user, player);
		
	}
}
