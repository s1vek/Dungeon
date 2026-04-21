package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Direction;
import com.example.soulspire.Entity.Player.Player;

/**
 * Hunter ability 3: Quick leap backwards to create distance from enemies.
 */
public class LeapAbility extends Ability {

    private static final double LEAP_DISTANCE = 120;

    public LeapAbility() {
        super("Leap", "Dodge backwards to create distance", 5.0, AbilityType.MOBILITY);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {

    }
}