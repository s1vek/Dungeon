package com.example.soulspire.Entity.Enemy;

import com.example.soulspire.Entity.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Basic enemy that chases the player and attacks at close range.
 * The most common enemy type in the tower.
 */
public class MeleeEnemy extends Enemy {

    /**
     * Creates a melee enemy with default stats scaled by floor number.
     *
     * @param x           spawn x position
     * @param y           spawn y position
     * @param floorNumber current floor (for difficulty scaling)
     */
    public MeleeEnemy(double x, double y, int floorNumber) {
        super(x, y, 28, 28,
                40,    // baseHealth
                12,    // baseAttack
                3,     // defense
                2.5,   // moveSpeed
                200,   // aggroRange
                40,    // attackRange
                1.0,   // attackCooldown
                floorNumber);
    }

    @Override
    public void updateAI(Player target, double deltaTime) {
        checkAggro(target);
        if (!aggroed) return;

        double dist = distanceTo(target);
        if (dist <= attackRange && currentAttackCooldown <= 0) {
            // Attack the player
            target.takeDamage(attackDamage);
            currentAttackCooldown = attackCooldown;
        } else if (dist > attackRange) {
            // Chase the player
            moveToward(target.getCenterX(), target.getCenterY(), deltaTime);
        }
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        gc.setFill(Color.CRIMSON);
        gc.fillRect(screenX, screenY, width, height);
    }
}