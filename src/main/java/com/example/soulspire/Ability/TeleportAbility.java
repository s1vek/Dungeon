package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;

/**
 * Mage ability 3: Instantly teleport a short distance in facing direction.
 */
public class TeleportAbility extends Ability {

    private static final double TELEPORT_DISTANCE = 150;

    public TeleportAbility() {
        super("Teleport", "Blink forward a short distance", 6.0, AbilityType.MOBILITY);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {
        double dx = caster.getFacing().getDx() * TELEPORT_DISTANCE;
        double dy = caster.getFacing().getDy() * TELEPORT_DISTANCE;
        caster.setX(caster.getX() + dx);
        caster.setY(caster.getY() + dy);
        // TODO: validate destination is not inside a wall
        resetCooldown();
    }
}