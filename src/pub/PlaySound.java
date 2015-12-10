package pub;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import sun.audio.AudioPlayer;

public class PlaySound {

	private static String file = "music/shot.wav";
	private static String file1 = "Resources/snd_explosion2.wav";
	private static String file2 = "music/warning.wav";
        private static String file3 = "Resources/background.wav";
	private static InputStream inputStream = null;
        
	public static void playShot() {
		try {
			inputStream = new FileInputStream(new File(file));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(inputStream);
	}

	public static void playBang() {
		try {
			inputStream = new FileInputStream(new File(file1));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(inputStream);
	}

	public static void playBoss() {
		try {
			inputStream = new FileInputStream(new File(file2));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(inputStream);
	}
        public static void playBackground() {
		try {
			inputStream = new FileInputStream(new File(file3));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		AudioPlayer.player.start(inputStream);
	}
}
