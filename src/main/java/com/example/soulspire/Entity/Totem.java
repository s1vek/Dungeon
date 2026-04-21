package com.example.soulspire.Entity;

import com.example.soulspire.Entity.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A healing totem placed by the Shaman's HealingTotemAbility.
 * Periodically heals the player when they are within its radius.
 * Despawns after its duration expires.
 */
public class Totem extends Entity {

    private int healAmount;
    private double healInterval;
    private double healTimer;
    private double radius;
    private double duration;
    private double remainingDuration;
    private Player owner;

    /**
     * Creates a new healing totem.
     *
     * @param x            x position
     * @param y            y position
     * @param healAmount   HP restored per tick
     * @param healInterval seconds between heal ticks
     * @param radius       range in pixels the player must be within
     * @param duration     total lifetime in seconds
     * @param owner        the player who placed this totem
     */
    public Totem(double x, double y, int healAmount, double healInterval,
                 double radius, double duration, Player owner) {
        super(x, y, 24, 24);
        this.healAmount = healAmount;
        this.healInterval = healInterval;
        this.healTimer = 0;
        this.radius = radius;
        this.duration = duration;
        this.remainingDuration = duration;
        this.owner = owner;
    }

    @Override
    public void update(double deltaTime) {

    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {

    }

    public double getRemainingDuration() { return remainingDuration; }
}