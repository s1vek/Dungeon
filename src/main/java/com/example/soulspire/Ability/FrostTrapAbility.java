package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;

/**
 * Hunter ability 2: Places an invisible trap at the player's position.
 * When an enemy steps on it, they are frozen and immobilized.
 */
public class FrostTrapAbility extends Ability {

    private static final double FREEZE_DURATION = 3.0;
    private static final double TRAP_RADIUS = 30;

    public FrostTrapAbility() {
        super("Frost Trap", "Place a trap that freezes enemies", 15.0, AbilityType.UTILITY);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {

    }
}