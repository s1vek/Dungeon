package com.example.soulspire.Ability;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Ability.AbilityType;
import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Entity.Projectile;
import javafx.scene.paint.Color;

/**
 * Mage ability 1: Fires a magic orb that explodes on impact dealing AoE damage.
 */
public class ArcaneOrbAbility extends Ability {

    private static final double ORB_SPEED = 250;
    private static final double ORB_RANGE = 400;
    private static final double EXPLOSION_RADIUS = 60;

    public ArcaneOrbAbility() {
        super("Arcane Orb", "Launch an orb that explodes on impact", 10.0, AbilityType.OFFENSIVE);
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {

    }
}