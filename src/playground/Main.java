package playground;

public class Main {
	public static int timer = 0;

	public static void main(String args[]) {
		// CharacterSelectFrame csf = new CharacterSelectFrame();
		// csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// csf.setVisible(true);
		UserInfo user = new UserInfo();
		StatusManager player = new StatusManager();

		 //DB없이 시작하고싶으면 이것 삽입 
		/*
		player.setStatus(new GameCharacter("PlayerName", 100, 10, 20, 45, "image/cha3.png"));
		WaitingRoomView wv = new WaitingRoomView(user, player);
		wv.gamestart();	
		LaunchManager lm = new LaunchManager(user, player);
		*/
		// WaitPlay w = new WaitPlay();
		// w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// w.setVisible(true);
	}
}
