package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;

/**
 * Shaman ability 2: Powerful fire-enhanced melee strike with extended range.
 */
public class FirestrikeAbility extends Ability {

    private static final double DAMAGE_MULTIPLIER = 2.5;
    private static final double RANGE = 60;

    public FirestrikeAbility() {
        super("Firestrike", "Devastating fire-enhanced weapon strike", 10.0, AbilityType.OFFENSIVE);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {

    }
}