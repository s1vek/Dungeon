package com.example.soulspire.Entity.Enemy;

import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Entity.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Enemy that maintains distance and fires projectiles at the player.
 * Retreats when the player gets too close.
 */
public class RangedEnemy extends Enemy {

    private double projectileSpeed;
    private double preferredDistance;
    /** Stored reference to the last fired projectile for floor entity addition. */
    private Projectile lastFiredProjectile;

    public RangedEnemy(double x, double y, int floorNumber) {
        super(x, y, 26, 26,
                25,    // baseHealth (fragile)
                15,    // baseAttack
                1,     // defense
                2.0,   // moveSpeed
                250,   // aggroRange
                200,   // attackRange
                1.5,   // attackCooldown
                floorNumber);
        this.projectileSpeed = 300;
        this.preferredDistance = 150;
        this.lastFiredProjectile = null;
    }

    @Override
    public void updateAI(Player target, double deltaTime) {

    }

    /**
     * Fires a projectile toward the target coordinates.
     * The projectile must be added to the floor's entity list by GameEngine.
     */
    private void shoot(double targetX, double targetY) {

    }

    /**
     * Returns and clears the last fired projectile.
     * Called by GameEngine to add the projectile to the floor.
     *
     * @return the projectile to spawn, or null if none was fired this frame
     */

    /*
    public Projectile consumeProjectile() {

    }

     */

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {

    }
}