package com.example.soulspire.Util;

import javafx.scene.image.Image;

/**
 * Handles sprite animation frame cycling with configurable speed.
 * Each {@link com.example.soulspire.Entity.LivingEntity} can hold
 * an AnimationHandler to manage walk, attack, and death animations.
 */
public class AnimationHandler {

    private Image[] frames;
    private int currentFrame;
    private double frameTimer;
    private double frameDuration;
    private boolean looping;
    private boolean finished;

    /**
     * Creates an animation handler with no initial frames.
     * Call {@link #setAnimation(Image[], double, boolean)} to configure.
     */
    public AnimationHandler() {
        this.frames = new Image[0];
        this.currentFrame = 0;
        this.frameTimer = 0;
        this.frameDuration = 0.15;
        this.looping = true;
        this.finished = false;
    }

    /**
     * Sets a new animation sequence.
     *
     * @param frames        array of frame images
     * @param frameDuration seconds per frame
     * @param looping       whether the animation loops or plays once
     */
    public void setAnimation(Image[] frames, double frameDuration, boolean looping) {
        this.frames = frames;
        this.frameDuration = frameDuration;
        this.looping = looping;
        this.currentFrame = 0;
        this.frameTimer = 0;
        this.finished = false;
    }

    /**
     * Advances the animation timer. Call once per frame.
     *
     * @param deltaTime time elapsed since last frame
     */
    public void update(double deltaTime) {
        if (frames.length == 0 || finished) return;

        frameTimer += deltaTime;
        if (frameTimer >= frameDuration) {
            frameTimer -= frameDuration;
            currentFrame++;

            if (currentFrame >= frames.length) {
                if (looping) {
                    currentFrame = 0;
                } else {
                    currentFrame = frames.length - 1;
                    finished = true;
                }
            }
        }
    }

    /**
     * @return the current frame image to render
     */
    public Image getCurrentFrame() {
        if (frames.length == 0) return null;
        return frames[currentFrame];
    }

    /**
     * @return true if a non-looping animation has reached its last frame
     */
    public boolean isFinished() { return finished; }

    /**
     * Resets the animation to the first frame.
     */
    public void reset() {
        currentFrame = 0;
        frameTimer = 0;
        finished = false;
    }
}