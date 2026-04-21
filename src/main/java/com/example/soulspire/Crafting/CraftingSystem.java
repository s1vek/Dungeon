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

    }

    /**
     * Attempts to craft a recipe, consuming materials from the inventory.
     *
     * @param recipe    the recipe to craft
     * @param inventory the player's inventory
     * @return true if crafting was successful
     */

    /*
    public boolean craft(CraftingRecipe recipe, Inventory inventory) {

    }
     */

    /**
     * Returns only the recipes the player currently has materials for.
     */

    /*
    public List<CraftingRecipe> getAvailableRecipes(Inventory inventory) {

    }

     */
    public List<CraftingRecipe> getAllRecipes() { return recipes; }
}