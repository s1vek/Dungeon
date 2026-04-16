package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;

/**
 * Warrior ability 2: Spins and damages all enemies in a radius.
 */
public class BladewhirlAbility extends Ability {

    private static final double RADIUS = 80;
    private static final double DAMAGE_MULTIPLIER = 1.5;

    public BladewhirlAbility() {
        super("Bladewhirl", "Spin attack hitting all nearby enemies", 12.0, AbilityType.OFFENSIVE);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {
        int damage = (int)(caster.getAttackDamage() * DAMAGE_MULTIPLIER);
        // TODO: use CombatSystem.processAreaDamage(caster.getCenterX(), caster.getCenterY(), RADIUS, damage, entities, caster)
        resetCooldown();
    }
}