
package com.example.soulspire.Entity.Player;

import com.example.soulspire.Ability.ArcaneOrbAbility;
import com.example.soulspire.Ability.IceBlockAbility;
import com.example.soulspire.Ability.TeleportAbility;
import com.example.soulspire.Entity.Projectile;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Mage extends Player {

    private static final double PROJECTILE_SPEED = 400;
    private static final double PROJECTILE_RANGE = 500;

    public Mage(String name, double x, double y) {
        super(name, PlayerType.MAGE, x, y, 32, 32);
    }

    @Override
    protected void initAbilities() {
        abilities[0] = new ArcaneOrbAbility();
        abilities[1] = new IceBlockAbility();
        abilities[2] = new TeleportAbility();
    }

    @Override
    public void attack(double targetX, double targetY) {
        if (!canAttack()) return;

        Projectile bolt = new Projectile(
                getCenterX(), getCenterY(), targetX, targetY,
                PROJECTILE_SPEED, attackDamage, PROJECTILE_RANGE, this
        );
        bolt.setColor(Color.MEDIUMPURPLE);
        resetAttackCooldown();
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        if (invulnerable && ((int)(invulnerabilityTimer * 10) % 2 == 0)) return;
        gc.setFill(Color.MEDIUMPURPLE);
        gc.fillRect(screenX, screenY, width, height);
    }
}