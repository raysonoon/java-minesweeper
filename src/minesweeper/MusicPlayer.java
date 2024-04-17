package minesweeper;

import java.io.File;

// java sound packages
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
//import javax.sound.sampled.FloatControl;

public class MusicPlayer {

    static Clip clip;

    // playBackgroundMusic method
    public static void playBackgroundMusic(String location) {
        try {
            File musicPath = new File(location);

            if (musicPath.exists()) {
                // CD player
                AudioInputStream audioInput = AudioSystem.getAudioInputStream(musicPath);
                clip = AudioSystem.getClip();
                // Open CD player
                clip.open(audioInput);
                // Play CD player
                clip.start();
                // Keep looping
                clip.loop(Clip.LOOP_CONTINUOUSLY);
                
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
                clip = AudioSystem.getClip();
                // Open CD player
                clip.open(audioInput);
                // Play CD player
                clip.start();
            } else {
                System.out.println("Can't find file");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // play method
    public static void play() {
        clip.start();
    }

    // stop method
    public static void stop() {
        clip.stop();
    }
}
