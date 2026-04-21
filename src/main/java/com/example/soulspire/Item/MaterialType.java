package com.example.soulspire.Item;

public enum MaterialType {

    /** Dropped by enemies and found in destructible objects. Used for weapons and armor. */
    IRON_ORE("Iron Ore"),

    /** Rare material dropped by stronger enemies. Required for advanced crafting recipes. */
    ETHEREAL_DUST("Ethereal Dust");

    private final String displayName;

    MaterialType(String displayName) {
        this.displayName = displayName;
    }

    /**
     * @return human-readable material name for UI and inventory display
     */
    public String getDisplayName() { return displayName; }
}
