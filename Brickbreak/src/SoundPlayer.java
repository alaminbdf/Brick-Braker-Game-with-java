import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

public class SoundPlayer {
    private static Clip backgroundClip;

    public static void playSound(String soundFile) {
        try {
            URL soundPath = SoundPlayer.class.getResource(soundFile);
            if (soundPath == null) {
                System.out.println("Sound file not found: " + soundFile);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(soundPath);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void playBackgroundMusic(String musicFile) {
        try {
            URL musicPath = SoundPlayer.class.getResource(musicFile);
            if (musicPath == null) {
                System.out.println("Music file not found: " + musicFile);
                return;
            }

            AudioInputStream audioStream = AudioSystem.getAudioInputStream(musicPath);
            backgroundClip = AudioSystem.getClip();
            backgroundClip.open(audioStream);
            backgroundClip.loop(Clip.LOOP_CONTINUOUSLY); // 🔄 Loop music
            backgroundClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void stopBackgroundMusic() {
        if (backgroundClip != null && backgroundClip.isRunning()) {
            backgroundClip.stop();
        }
    }
}
