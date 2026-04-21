package com.example.soulspire.UI;

import com.example.soulspire.Crafting.CraftingRecipe;
import com.example.soulspire.Crafting.CraftingSystem;
import com.example.soulspire.Item.Inventory;
import com.example.soulspire.Item.MaterialType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.Map;

/**
 * Crafting UI displayed when interacting with the Blacksmith.
 * Shows available recipes, required materials, and allows crafting.
 * Built programmatically without Scene Builder (assignment requirement).
 */
public class CraftingUI extends VBox {

    private final CraftingSystem craftingSystem;
    private final Inventory inventory;
    private VBox recipeList;
    private Label materialsLabel;

    public CraftingUI(CraftingSystem craftingSystem, Inventory inventory) {
        this.craftingSystem = craftingSystem;
        this.inventory = inventory;

        setAlignment(Pos.CENTER);
        setSpacing(15);
        setPadding(new Insets(30));
        setMaxWidth(500);
        setStyle("-fx-background-color: rgba(30,30,30,0.95); -fx-background-radius: 10;");

        Label title = new Label("Blacksmith - Crafting");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 22; -fx-font-weight: bold;");

        materialsLabel = new Label();
        materialsLabel.setStyle("-fx-text-fill: #aaa; -fx-font-size: 13;");

        recipeList = new VBox(8);
        recipeList.setAlignment(Pos.CENTER);

        Button closeBtn = new Button("Close");
        closeBtn.setOnAction(e -> setVisible(false));

        getChildren().addAll(title, materialsLabel, recipeList, closeBtn);
        refresh();
    }

    /**
     * Refreshes the recipe list and material counts.
     */
    public void refresh() {
        materialsLabel.setText(
                "Iron Ore: " + inventory.getMaterialCount(MaterialType.IRON_ORE) +
                        "    Ethereal Dust: " + inventory.getMaterialCount(MaterialType.ETHEREAL_DUST)
        );

        recipeList.getChildren().clear();
        for (CraftingRecipe recipe : craftingSystem.getAllRecipes()) {
            HBox row = new HBox(10);
            row.setAlignment(Pos.CENTER_LEFT);
            row.setPadding(new Insets(5, 10, 5, 10));
            row.setStyle("-fx-background-color: #444; -fx-background-radius: 5;");

            StringBuilder cost = new StringBuilder();
            for (Map.Entry<MaterialType, Integer> entry : recipe.getRequiredMaterials().entrySet()) {
                cost.append(entry.getValue()).append("x ").append(entry.getKey().getDisplayName()).append("  ");
            }

            Label nameLabel = new Label(recipe.getResultName());
            nameLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14; -fx-font-weight: bold;");
            nameLabel.setMinWidth(150);

            Label costLabel = new Label(cost.toString().trim());
            costLabel.setStyle("-fx-text-fill: #bbb; -fx-font-size: 12;");

            /*
            Button craftBtn = new Button("Craft");
            craftBtn.setDisable(!recipe.canCraft(inventory));
            craftBtn.setOnAction(e -> {
                craftingSystem.craft(recipe, inventory);
                refresh();
            });

             */

            /*
            row.getChildren().addAll(nameLabel, costLabel, craftBtn);
            recipeList.getChildren().add(row);

             */
        }
    }
}