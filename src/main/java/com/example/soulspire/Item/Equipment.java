package com.example.soulspire.Item;

/**
 * Represents an equippable item (weapon or armor) that modifies player stats.
 * Crafted at the Blacksmith NPC using materials, or found as loot.
 */
public class Equipment extends Item {

    private int attackBonus;
    private int defenseBonus;
    private EquipmentSlot slot;

    /**
     * Creates a new piece of equipment.
     *
     * @param name         display name
     * @param description  tooltip text
     * @param attackBonus  bonus to player's attack damage when equipped
     * @param defenseBonus bonus to player's defense when equipped
     * @param slot         which slot this item occupies (WEAPON or ARMOR)
     */
    public Equipment(String name, String description, int attackBonus, int defenseBonus, EquipmentSlot slot) {
        super(name, description);
        this.attackBonus = attackBonus;
        this.defenseBonus = defenseBonus;
        this.slot = slot;
    }

    @Override
    public Item copy() {
        return new Equipment(name, description, attackBonus, defenseBonus, slot);
    }

    public int getAttackBonus() { return attackBonus; }
    public int getDefenseBonus() { return defenseBonus; }
    public EquipmentSlot getSlot() { return slot; }
}