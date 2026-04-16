package com.example.soulspire.Entity.Player;

import com.example.soulspire.Ability.FrostTrapAbility;
import com.example.soulspire.Ability.LeapAbility;
import com.example.soulspire.Ability.SpreadShotAbility;
import com.example.soulspire.Entity.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * Ranged archer class with high speed and kiting abilities.
 * Abilities: Spread Shot (multi-arrow), Frost Trap (freeze), Leap (dodge back).
 * Basic attack: fires an arrow toward the mouse cursor.
 */
public class Hunter extends Player {

    private static final double ARROW_SPEED = 500;
    private static final double ARROW_RANGE = 600;

    public Hunter(String name, double x, double y) {
        super(name, PlayerType.HUNTER, x, y, 32, 32);
    }

    @Override
    protected void initAbilities() {
        abilities[0] = new SpreadShotAbility();
        abilities[1] = new FrostTrapAbility();
        abilities[2] = new LeapAbility();
    }

    @Override
    public void attack(double targetX, double targetY) {
        if (!canAttack()) return;
        // Fire a single arrow toward the mouse
        Projectile arrow = new Projectile(
                getCenterX(), getCenterY(), targetX, targetY,
                ARROW_SPEED, attackDamage, ARROW_RANGE, this
        );
        arrow.setColor(Color.LIGHTGREEN);
        resetAttackCooldown();
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        if (invulnerable && ((int)(invulnerabilityTimer * 10) % 2 == 0)) return;
        gc.setFill(Color.FORESTGREEN);
        gc.fillRect(screenX, screenY, width, height);
    }
}