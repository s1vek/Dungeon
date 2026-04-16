package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;

/**
 * Warrior ability 1: Dashes forward and stuns the first enemy hit.
 * Can break destructible walls.
 */
public class ChargeAbility extends Ability {

    private static final double CHARGE_DISTANCE = 200;
    private static final double STUN_DURATION = 2.0;

    public ChargeAbility() {
        super("Charge", "Dash forward and stun the first enemy", 8.0, AbilityType.MOBILITY);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {
        double dx = caster.getFacing().getDx() * CHARGE_DISTANCE;
        double dy = caster.getFacing().getDy() * CHARGE_DISTANCE;
        caster.setX(caster.getX() + dx);
        caster.setY(caster.getY() + dy);
        // TODO: check for enemies along path → stun first hit
        // TODO: check for DESTRUCTIBLE_WALL → break it
        resetCooldown();
    }
}