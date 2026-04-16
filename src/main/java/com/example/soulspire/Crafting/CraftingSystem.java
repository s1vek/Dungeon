package com.example.soulspire.Crafting;

import com.example.soulspire.Item.*;
import com.example.soulspire.Util.GameLogger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Manages all crafting recipes and executes crafting operations.
 * Used by the Blacksmith NPC in safe zones.
 */
public class CraftingSystem {

    private static final GameLogger logger = GameLogger.getLogger(CraftingSystem.class);

    private List<CraftingRecipe> recipes;

    public CraftingSystem() {
        this.recipes = new ArrayList<>();
        loadRecipes();
    }

    /**
     * Initializes the available crafting recipes.
     */
    private void loadRecipes() {
        // Iron Sword: 5 Iron Ore
        recipes.add(new CraftingRecipe(
                new Equipment("Iron Sword", "A sturdy blade", 10, 0, EquipmentSlot.WEAPON),
                Map.of(MaterialType.IRON_ORE, 5)
        ));

        // Iron Armor: 8 Iron Ore
        recipes.add(new CraftingRecipe(
                new Equipment("Iron Armor", "Solid protection", 0, 8, EquipmentSlot.ARMOR),
                Map.of(MaterialType.IRON_ORE, 8)
        ));

        // Ethereal Blade: 3 Iron Ore + 5 Ethereal Dust
        recipes.add(new CraftingRecipe(
                new Equipment("Ethereal Blade", "Infused with ethereal energy", 20, 0, EquipmentSlot.WEAPON),
                Map.of(MaterialType.IRON_ORE, 3, MaterialType.ETHEREAL_DUST, 5)
        ));

        // Ethereal Armor: 4 Iron Ore + 6 Ethereal Dust
        recipes.add(new CraftingRecipe(
                new Equipment("Ethereal Armor", "Shimmers with energy", 0, 15, EquipmentSlot.ARMOR),
                Map.of(MaterialType.IRON_ORE, 4, MaterialType.ETHEREAL_DUST, 6)
        ));
    }

    /**
     * Attempts to craft a recipe, consuming materials from the inventory.
     *
     * @param recipe    the recipe to craft
     * @param inventory the player's inventory
     * @return true if crafting was successful
     */
    public boolean craft(CraftingRecipe recipe, Inventory inventory) {
        if (!recipe.canCraft(inventory)) return false;

        // Consume materials
        for (Map.Entry<MaterialType, Integer> entry : recipe.getRequiredMaterials().entrySet()) {
            inventory.removeMaterial(entry.getKey(), entry.getValue());
        }

        // Add result to inventory
        Equipment result = (Equipment) recipe.getResult().copy();
        inventory.addItem(result);
        logger.info("Crafted: " + result.getName());
        return true;
    }

    /**
     * Returns only the recipes the player currently has materials for.
     */
    public List<CraftingRecipe> getAvailableRecipes(Inventory inventory) {
        return recipes.stream()
                .filter(r -> r.canCraft(inventory))
                .toList();
    }

    public List<CraftingRecipe> getAllRecipes() { return recipes; }
}