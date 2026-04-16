
package com.example.soulspire.Entity.Player;

import com.example.soulspire.Ability.BladewhirlAbility;
import com.example.soulspire.Ability.ChargeAbility;
import com.example.soulspire.Ability.EnrageAbility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Warrior extends Player {

    public Warrior(String name, double x, double y) {
        super(name, PlayerType.WARRIOR, x, y, 32, 32);
    }

    @Override
    protected void initAbilities() {
        abilities[0] = new ChargeAbility();
        abilities[1] = new BladewhirlAbility();
        abilities[2] = new EnrageAbility();
    }

    @Override
    public void attack(double targetX, double targetY) {
        if (!canAttack()) return;
        resetAttackCooldown();
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        // Blink when invulnerable (iframes)
        if (invulnerable && ((int)(invulnerabilityTimer * 10) % 2 == 0)) return;
        gc.setFill(Color.STEELBLUE);
        gc.fillRect(screenX, screenY, width, height);
    }
}