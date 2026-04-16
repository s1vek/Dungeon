package com.example.soulspire.Item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Defines what items an enemy drops when killed, with probability weights.
 * Each enemy type has its own LootTable instance configured at spawn time.
 *
 * <p>Example usage:</p>
 * <pre>
 * LootTable table = new LootTable();
 * table.addDrop(new Material("Iron Ore", MaterialType.IRON_ORE, 1), 0.6);
 * table.addDrop(new Material("Ethereal Dust", MaterialType.ETHEREAL_DUST, 1), 0.2);
 * List&lt;Item&gt; dropped = table.roll();
 * </pre>
 */
public class LootTable {

    /** Map of possible drops to their probability (0.0 to 1.0). */
    private final Map<Item, Double> drops;

    /** Random number generator for loot rolls. */
    private final Random random;

    /**
     * Creates an empty loot table.
     */
    public LootTable() {
        this.drops = new HashMap<>();
        this.random = new Random();
    }

    /**
     * Adds a possible drop with a given probability.
     *
     * @param item   the item that can drop (a copy is made on each drop)
     * @param chance probability of dropping (0.0 = never, 1.0 = always)
     */
    public void addDrop(Item item, double chance) {
        drops.put(item, Math.max(0, Math.min(1.0, chance)));
    }

    /**
     * Rolls all entries in the loot table and returns the items that dropped.
     * Each entry is rolled independently — multiple items can drop at once.
     *
     * @return list of dropped item copies (may be empty)
     */
    public List<Item> roll() {
        List<Item> result = new ArrayList<>();
        for (Map.Entry<Item, Double> entry : drops.entrySet()) {
            if (random.nextDouble() < entry.getValue()) {
                result.add(entry.getKey().copy());
            }
        }
        return result;
    }

    /**
     * @return true if this loot table has no entries
     */
    public boolean isEmpty() {
        return drops.isEmpty();
    }
}