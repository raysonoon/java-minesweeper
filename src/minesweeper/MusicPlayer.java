package minesweeper;

import java.io.File;

// java sound packages
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer {

    static Clip backgroundClip;
    private Clip soundClip;

    // playBackgroundMusic method
    public static void playBackgroundMusic(String location) {
        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                // CD player
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                backgroundClip = AudioSystem.getClip();
                // Open CD player
                backgroundClip.open(audioInput);
                // Play CD player
                backgroundClip.start();
                // Keep looping
                backgroundClip.loop(Clip.LOOP_CONTINUOUSLY);
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // playEndMusic method
    public void playEndMusic(String location) {
        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                // CD player
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                soundClip = AudioSystem.getClip();
                // Open CD player
                soundClip.open(audioInput);
                // Play CD player
                soundClip.start();
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // play background music method
    public static void playBackgroundMusic() {
        backgroundClip.start();
    }

    // stop background music method
    public static void stopBackgroundMusic() {
        if (backgroundClip != null)
        backgroundClip.stop();
    }
}
