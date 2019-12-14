package playground;

public class Main {
	public static int timer = 0;

	public static void main(String args[]) {
		//without login
		UserInfo user = new UserInfo("default");
		StatusManager player = new StatusManager();
		
		CharacterSelectView cv = new CharacterSelectView(user, player);
		
		
		
		
	}
}
