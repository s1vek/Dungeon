package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Entity.Projectile;
import javafx.scene.paint.Color;

/**
 * Hunter ability 1: Fires multiple arrows in a fan pattern toward the cursor.
 */
public class SpreadShotAbility extends Ability {

    private static final int ARROW_COUNT = 5;
    private static final double SPREAD_ANGLE = Math.toRadians(40);
    private static final double ARROW_SPEED = 450;
    private static final double ARROW_RANGE = 500;

    public SpreadShotAbility() {
        super("Spread Shot", "Fire 5 arrows in a fan", 8.0, AbilityType.OFFENSIVE);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {
        double cx = caster.getCenterX();
        double cy = caster.getCenterY();
        double baseAngle = Math.atan2(targetY - cy, targetX - cx);

        for (int i = 0; i < ARROW_COUNT; i++) {
            double angleOffset = SPREAD_ANGLE * ((double)i / (ARROW_COUNT - 1) - 0.5);
            double angle = baseAngle + angleOffset;
            double tx = cx + Math.cos(angle) * ARROW_RANGE;
            double ty = cy + Math.sin(angle) * ARROW_RANGE;

            Projectile arrow = new Projectile(cx, cy, tx, ty,
                    ARROW_SPEED, caster.getAttackDamage(), ARROW_RANGE, caster);
            arrow.setColor(Color.LIGHTGREEN);
            // TODO: add arrow to floor entities
        }
        resetCooldown();
    }
}