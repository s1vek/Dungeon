package com.example.soulspire.Ability;


import com.example.soulspire.Entity.Player.Player;

/**
 * Warrior ability 3: Buff increasing damage and max HP for 10 seconds.
 */
public class EnrageAbility extends Ability {

    private static final double DURATION = 10.0;
    private static final double DAMAGE_BOOST = 1.5;
    private static final int HEALTH_BOOST = 50;

    private boolean active;
    private double remainingDuration;
    private int originalDamage;
    private int originalMaxHealth;

    public EnrageAbility() {
        super("Enrage", "Increase DMG and HP for 10 seconds", 25.0, AbilityType.UTILITY);
        this.active = false;
    }

    @Override
    public void execute(Player caster, double targetX, double targetY) {
        if (active) return;
        active = true;
        remainingDuration = DURATION;
        originalDamage = caster.getAttackDamage();
        originalMaxHealth = caster.getMaxHealth();

        caster.setAttackDamage((int)(originalDamage * DAMAGE_BOOST));
        caster.setMaxHealth(originalMaxHealth + HEALTH_BOOST);
        caster.heal(HEALTH_BOOST);
        resetCooldown();
    }

    @Override
    public void update(double deltaTime) {
        super.update(deltaTime);
        if (active) {
            remainingDuration -= deltaTime;
            if (remainingDuration <= 0) {
                active = false;
                // Revert is handled externally or via stored player reference
                // TODO: store caster reference to revert stats
            }
        }
    }
}