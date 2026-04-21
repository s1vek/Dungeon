package com.example.soulspire.World;

public enum TileType {

    FLOOR(true, false),

    /** Solid wall — blocks movement and projectiles. */
    WALL(false, false),

    /** Door between rooms — walkable, may require a lever to open. */
    DOOR(true, false),

    /** Inactive trap tile — looks like floor, triggers damage when stepped on. */
    TRAP(true, true),

    /** Location where a Chest entity is placed. */
    CHEST_SPOT(true, false),

    /** Safe zone tile — marks rest areas with NPC access, no enemy spawns. */
    SAFE_ZONE(true, false),

    /** Lever or button — activates connected doors or disables traps. */
    LEVER(true, true),

    /** Cracked wall — Warrior can break through with Charge ability. */
    DESTRUCTIBLE_WALL(false, false),

    /** Spawn point — where the player appears on this floor. */
    SPAWN(true, false),

    /** Exit point — stairs leading to the next floor. */
    EXIT(true, true);

    private final boolean walkable;
    private final boolean triggersOnStep;

    TileType(boolean walkable, boolean triggersOnStep) {
        this.walkable = walkable;
        this.triggersOnStep = triggersOnStep;
    }

    /**
     * @return true if entities can move onto this tile
     */
    public boolean isWalkable() { return walkable; }

    /**
     * @return true if stepping on this tile triggers a game event
     */
    public boolean triggersOnStep() { return triggersOnStep; }
}
