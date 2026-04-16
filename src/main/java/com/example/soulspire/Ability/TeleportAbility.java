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

    }
}