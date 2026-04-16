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
        remainingDuration -= deltaTime;
        if (remainingDuration <= 0) {
            setActive(false);
            return;
        }

        healTimer += deltaTime;
        if (healTimer >= healInterval) {
            healTimer -= healInterval;
            // Heal owner if within radius
            if (distanceTo(owner) <= radius) {
                owner.heal(healAmount);
            }
        }
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        double centerX = screenX + width / 2;
        double centerY = screenY + height / 2;

        // Draw radius indicator
        gc.setStroke(Color.rgb(100, 255, 100, 0.2));
        gc.strokeOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw totem body
        gc.setFill(Color.LIMEGREEN);
        gc.fillRect(screenX, screenY, width, height);
    }

    public double getRemainingDuration() { return remainingDuration; }
}