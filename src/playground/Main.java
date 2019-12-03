package playground;

public class Main {
	public static void main(String args[]) {
		//CharacterSelectFrame csf = new CharacterSelectFrame();
		//csf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//csf.setVisible(true);
		
		WaitingRoomView wv = new WaitingRoomView();
		CharacterSelectView cv = new CharacterSelectView(wv);
		StatusManager player = new StatusManager(cv);
		LaunchManager lm = new LaunchManager(cv);
		
		
		//WaitPlay w = new WaitPlay();
		//w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//w.setVisible(true);
	}
}
