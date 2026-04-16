package com.example.soulspire.Combat;

import com.example.soulspire.Entity.Entity;
import com.example.soulspire.Entity.LivingEntity;
import com.example.soulspire.Entity.Projectile;
import com.example.soulspire.Util.GameLogger;

import java.util.List;

/**
 * Handles all combat damage calculations — melee hits, projectile impacts,
 * and area-of-effect abilities.
 */
public class CombatSystem {

    private static final GameLogger logger = GameLogger.getLogger(CombatSystem.class);

    /**
     * Processes a direct melee attack from one entity to another.
     */
    public void processAttack(LivingEntity attacker, LivingEntity target) {
        if (target.isInvulnerable() || target.isDead()) return;
        target.takeDamage(attacker.getAttackDamage());
        logger.info(attacker.getClass().getSimpleName() + " hit " +
                target.getClass().getSimpleName() + " for " + attacker.getAttackDamage());
    }

    /**
     * Processes a projectile hitting a living entity.
     * Deactivates the projectile after impact.
     */
    public void processProjectileHit(Projectile projectile, LivingEntity target) {
        if (target.isInvulnerable() || target.isDead()) return;
        if (projectile.getOwner() == target) return; // No self-hits

        target.takeDamage(projectile.getDamage());
        projectile.setActive(false);
        logger.info("Projectile hit " + target.getClass().getSimpleName() +
                " for " + projectile.getDamage());
    }

    /**
     * Deals damage to all living entities within a radius.
     * Used by AoE abilities like Bladewhirl, Arcane Orb explosion, etc.
     *
     * @param centerX  center of the area
     * @param centerY  center of the area
     * @param radius   effect radius in pixels
     * @param damage   damage to deal to each target
     * @param entities all entities on the floor
     * @param owner    the entity causing the damage (excluded from hits)
     */
    public void processAreaDamage(double centerX, double centerY, double radius,
                                  int damage, List<Entity> entities, Entity owner) {
        List<Entity> targets = CollisionDetector.getEntitiesInArea(
                centerX, centerY, radius, entities
        );
        for (Entity entity : targets) {
            if (entity instanceof LivingEntity target && entity != owner) {
                if (!target.isInvulnerable() && !target.isDead()) {
                    target.takeDamage(damage);
                }
            }
        }
    }
}