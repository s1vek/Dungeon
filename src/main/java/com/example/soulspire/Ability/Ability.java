package com.example.soulspire.Ability;

import com.example.soulspire.Entity.Player.Player;
import javafx.scene.image.Image;

/**
 * Abstract base class for all character abilities.
 * Each of the four character classes has 3 abilities (mapped to keys 1, 2, 3),
 * making 12 concrete implementations total.
 *
 * <p>Every ability has a cooldown timer that prevents immediate reuse.
 * The {@link #execute} method contains the ability's unique logic — spawning
 * projectiles, applying buffs, dealing area damage, etc.</p>
 *
 * <p>Concrete abilities by character:</p>
 * <pre>
 * Warrior:  ChargeAbility, BladewhirlAbility, EnrageAbility
 * Mage:     ArcaneOrbAbility, IceBlockAbility, TeleportAbility
 * Hunter:   SpreadShotAbility, FrostTrapAbility, LeapAbility
 * Shaman:   HealingTotemAbility, FirestrikeAbility, AstralWolfAbility
 * </pre>
 */
public abstract class Ability {

    /** Human-readable ability name (e.g. "Charge", "Ice Block"). */
    protected String name;

    /** Short description of what the ability does, shown in tooltips. */
    protected String description;

    /** Total cooldown duration in seconds. */
    protected double cooldown;

    /** Remaining cooldown time. Ability is ready when this reaches 0. */
    protected double currentCooldown;

    /** Categorization of the ability's purpose (offensive, defensive, etc.). */
    protected AbilityType abilityType;

    /** Icon displayed in the HUD ability bar. May be null during development. */
    protected Image icon;

    /**
     * Creates a new ability with the given properties.
     *
     * @param name        display name
     * @param description tooltip description
     * @param cooldown    cooldown duration in seconds
     * @param abilityType category of this ability
     */
    protected Ability(String name, String description, double cooldown, AbilityType abilityType) {
        this.name = name;
        this.description = description;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
        this.abilityType = abilityType;
        this.icon = null;
    }

    /**
     * Executes the ability's effect. Called when the player presses the ability key
     * and the ability is off cooldown.
     *
     * <p>The targetX/targetY parameters represent the mouse cursor position,
     * used by ranged and directional abilities for aiming. Melee and self-buff
     * abilities may ignore these parameters.</p>
     *
     * <p>Implementations must call {@link #resetCooldown()} at the end.</p>
     *
     * @param caster  the player using this ability
     * @param targetX mouse X position in world coordinates
     * @param targetY mouse Y position in world coordinates
     */
    public abstract void execute(Player caster, double targetX, double targetY);

    /**
     * Updates the cooldown timer. Called every frame by the Player's update loop.
     *
     * @param deltaTime time elapsed since last frame in seconds
     */
    public void update(double deltaTime) {
        if (currentCooldown > 0) {
            currentCooldown = Math.max(0, currentCooldown - deltaTime);
        }
    }

    /**
     * @return true if the ability can be used (cooldown has elapsed)
     */
    public boolean isReady() {
        return currentCooldown <= 0;
    }

    /**
     * Returns how far through the cooldown we are, as a fraction from 0.0 (ready)
     * to 1.0 (just used). Used by the HUD to render a cooldown overlay on the icon.
     *
     * @return cooldown progress (0.0 = ready, 1.0 = full cooldown remaining)
     */
    public double getCooldownPercent() {
        if (cooldown <= 0) return 0;
        return currentCooldown / cooldown;
    }

    /**
     * Starts the cooldown timer. Should be called at the end of {@link #execute}.
     */
    protected void resetCooldown() {
        currentCooldown = cooldown;
    }

    // --- Getters ---

    public String getName() { return name; }
    public String getDescription() { return description; }
    public double getCooldown() { return cooldown; }
    public double getCurrentCooldown() { return currentCooldown; }
    public AbilityType getAbilityType() { return abilityType; }
    public Image getIcon() { return icon; }
    public void setIcon(Image icon) { this.icon = icon; }
}