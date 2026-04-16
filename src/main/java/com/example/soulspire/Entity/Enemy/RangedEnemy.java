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
        checkAggro(target);
        if (!aggroed) return;

        double dist = distanceTo(target);

        if (dist < preferredDistance) {
            // Too close — retreat away from player
            double dx = getCenterX() - target.getCenterX();
            double dy = getCenterY() - target.getCenterY();
            double len = Math.sqrt(dx * dx + dy * dy);
            if (len > 0) {
                x += (dx / len) * moveSpeed * deltaTime;
                y += (dy / len) * moveSpeed * deltaTime;
            }
        } else if (dist > attackRange) {
            // Too far — approach
            moveToward(target.getCenterX(), target.getCenterY(), deltaTime);
        }

        // Shoot when in range and cooldown ready
        if (dist <= attackRange && currentAttackCooldown <= 0) {
            shoot(target.getCenterX(), target.getCenterY());
            currentAttackCooldown = attackCooldown;
        }
    }

    /**
     * Fires a projectile toward the target coordinates.
     * The projectile must be added to the floor's entity list by GameEngine.
     */
    private void shoot(double targetX, double targetY) {
        lastFiredProjectile = new Projectile(
                getCenterX(), getCenterY(), targetX, targetY,
                projectileSpeed, attackDamage, attackRange * 1.5, this
        );
        lastFiredProjectile.setColor(Color.ORANGERED);
    }

    /**
     * Returns and clears the last fired projectile.
     * Called by GameEngine to add the projectile to the floor.
     *
     * @return the projectile to spawn, or null if none was fired this frame
     */
    public Projectile consumeProjectile() {
        Projectile p = lastFiredProjectile;
        lastFiredProjectile = null;
        return p;
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        gc.setFill(Color.DARKORANGE);
        gc.fillOval(screenX, screenY, width, height);
    }
}