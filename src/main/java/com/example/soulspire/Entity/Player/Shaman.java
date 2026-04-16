
package com.example.soulspire.Entity.Player;

import com.example.soulspire.Ability.AstralWolfAbility;
import com.example.soulspire.Ability.FirestrikeAbility;
import com.example.soulspire.Ability.HealingTotemAbility;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;


public class Shaman extends Player {

    public Shaman(String name, double x, double y) {
        super(name, PlayerType.SHAMAN, x, y, 32, 32);
    }

    @Override
    protected void initAbilities() {
        abilities[0] = new HealingTotemAbility();
        abilities[1] = new FirestrikeAbility();
        abilities[2] = new AstralWolfAbility();
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
        if (invulnerable && ((int)(invulnerabilityTimer * 10) % 2 == 0)) return;
        gc.setFill(Color.DARKORANGE);
        gc.fillRect(screenX, screenY, width, height);
    }
}