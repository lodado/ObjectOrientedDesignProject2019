package playground;

public class Main {
	public static int timer = 0;

	public static void main(String args[]) {
		// CharacterSelectFrame csf = new CharacterSelectFrame();
		// csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// csf.setVisible(true);
		StatusManager player = new StatusManager();

		WaitingRoomView wv = new WaitingRoomView(player);

		CharacterSelectView cv = new CharacterSelectView(wv, player);

		LaunchManager lm = new LaunchManager(cv);

		// WaitPlay w = new WaitPlay();
		// w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// w.setVisible(true);
	}
}
