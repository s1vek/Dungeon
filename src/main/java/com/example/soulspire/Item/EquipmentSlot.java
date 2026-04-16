package com.example.soulspire.Item;

public enum EquipmentSlot {

    /** Weapon slot — swords, staves, bows. Affects attack damage. */
    WEAPON("Weapon"),

    /** Armor slot — chestplates, robes. Affects defense. */
    ARMOR("Armor");

    private final String displayName;

    EquipmentSlot(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return human-readable slot name for UI display
     */
    public String getDisplayName() { return displayName; }
}
