/*
package com.example.soulspire.Util;

import javafx.scene.media.AudioClip;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;


 * Manages sound effects and background music playback.
 * Sound effects use {@link AudioClip} (short, can overlap).
 * Background music uses {@link MediaPlayer} (long, looped).

public class SoundManager {

    private static final GameLogger logger = GameLogger.getLogger(SoundManager.class);

    private final Map<String, AudioClip> sounds;
    private MediaPlayer bgMusic;
    private boolean muted;
    private double volume;

    public SoundManager() {
        this.sounds = new HashMap<>();
        this.muted = false;
        this.volume = 0.7;
    }


    public void loadSound(String name, String path) {
        try {
            URL resource = getClass().getResource(path);
            if (resource == null) {
                logger.warn("Sound not found: " + path);
                return;
            }
            AudioClip clip = new AudioClip(resource.toExternalForm());
            sounds.put(name, clip);
            logger.info("Loaded sound: " + name);
        } catch (Exception e) {
            logger.error("Failed to load sound: " + path, e);
        }
    }


    public void playSound(String name) {
        if (muted) return;
        AudioClip clip = sounds.get(name);
        if (clip != null) {
            clip.play(volume);
        }
    }


    public void playMusic(String path) {
        stopMusic();
        try {
            URL resource = getClass().getResource(path);
            if (resource == null) {
                logger.warn("Music not found: " + path);
                return;
            }
            Media media = new Media(resource.toExternalForm());
            bgMusic = new MediaPlayer(media);
            bgMusic.setCycleCount(MediaPlayer.INDEFINITE);
            bgMusic.setVolume(volume);
            if (!muted) bgMusic.play();
            logger.info("Playing music: " + path);
        } catch (Exception e) {
            logger.error("Failed to play music: " + path, e);
        }
    }


    public void stopMusic() {
        if (bgMusic != null) {
            bgMusic.stop();
            bgMusic.dispose();
            bgMusic = null;
        }
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
        if (bgMusic != null) {
            if (muted) bgMusic.pause(); else bgMusic.play();
        }
    }

    public void setVolume(double volume) {
        this.volume = Math.max(0, Math.min(1.0, volume));
        if (bgMusic != null) bgMusic.setVolume(this.volume);
    }

    public boolean isMuted() { return muted; }
    public double getVolume() { return volume; }
}

 */