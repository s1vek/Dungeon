package com.example.soulspire.UI;

import com.example.soulspire.Ability.Ability;
import com.example.soulspire.Core.GameEngine;
import com.example.soulspire.Entity.Player.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Heads-up display overlay showing player health, lives, floor number,
 * and ability cooldowns during gameplay.
 */
public class HUDOverlay extends BorderPane {

    private final GameEngine engine;
    private ProgressBar healthBar;
    private Label livesLabel;
    private Label floorLabel;
    private Label[] abilityLabels;

    public HUDOverlay(GameEngine engine) {
        this.engine = engine;
        setPickOnBounds(false); // Allow clicks to pass through to canvas

        // Top bar — health + info
        VBox topBar = new VBox(4);
        topBar.setPadding(new Insets(10));
        topBar.setMaxWidth(300);

        healthBar = new ProgressBar(1.0);
        healthBar.setPrefWidth(250);
        healthBar.setStyle("-fx-accent: red;");

        livesLabel = new Label("Lives: 3");
        livesLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14;");

        floorLabel = new Label("Floor: 1");
        floorLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14;");

        topBar.getChildren().addAll(healthBar, livesLabel, floorLabel);
        setTop(topBar);

        // Bottom bar — ability cooldowns
        HBox abilityBar = new HBox(10);
        abilityBar.setAlignment(Pos.CENTER);
        abilityBar.setPadding(new Insets(10));

        abilityLabels = new Label[3];
        for (int i = 0; i < 3; i++) {
            abilityLabels[i] = new Label("[" + (i + 1) + "] ---");
            abilityLabels[i].setStyle("-fx-text-fill: white; -fx-font-size: 13; " +
                    "-fx-background-color: rgba(0,0,0,0.5); -fx-padding: 5 10;");
            abilityBar.getChildren().add(abilityLabels[i]);
        }
        setBottom(abilityBar);
    }

    /**
     * Updates all HUD elements. Should be called each frame.
     */
    public void update() {
        Player player = engine.getPlayer();
        if (player == null) return;

        healthBar.setProgress(player.getHealthPercent());
        livesLabel.setText("Lives: " + player.getLives());
        floorLabel.setText("Floor: " + (engine.getTower().getCurrentFloorNumber() + 1));

        Ability[] abilities = player.getAbilities();
        for (int i = 0; i < 3; i++) {
            if (abilities[i] != null) {
                String status = abilities[i].isReady() ? "READY" :
                        String.format("%.1fs", abilities[i].getCurrentCooldown());
                abilityLabels[i].setText("[" + (i + 1) + "] " + abilities[i].getName() + " " + status);
            }
        }
    }
}