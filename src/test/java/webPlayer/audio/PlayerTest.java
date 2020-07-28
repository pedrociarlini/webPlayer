package webPlayer.audio;

import org.junit.Test;

public class PlayerTest {
	@Test
	public void testPlayMusic() {
		Player player = Player.getInstance();
		player.playMusic("/Users/pedrociarlini/Music/Princesas teste A.mp3");
		// player.playMusic("/Users/pedrociarlini/Music/Parabens Tac Tacs 22.03.2018.mp3");
		System.out.println("Acabou.");
	}
}
