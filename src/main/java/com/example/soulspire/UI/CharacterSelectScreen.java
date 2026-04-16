package com.example.soulspire.UI;

import com.example.soulspire.Core.GameEngine;
import com.example.soulspire.Core.GameState;
import com.example.soulspire.Entity.Player.PlayerType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Character selection screen where the player picks a class and enters a name.
 * Built programmatically without Scene Builder (assignment requirement).
 */
public class CharacterSelectScreen extends VBox {

    public CharacterSelectScreen(GameEngine engine, ScreenManager screenManager) {
        setAlignment(Pos.CENTER);
        setSpacing(20);
        setPadding(new Insets(40));
        setStyle("-fx-background-color: rgba(0,0,0,0.85);");

        Label title = new Label("Choose Your Hero");
        title.setStyle("-fx-text-fill: white; -fx-font-size: 28;");

        TextField nameField = new TextField("Hero");
        nameField.setMaxWidth(200);
        nameField.setPromptText("Enter name");

        HBox classButtons = new HBox(15);
        classButtons.setAlignment(Pos.CENTER);

        for (PlayerType type : PlayerType.values()) {
            VBox card = new VBox(5);
            card.setAlignment(Pos.CENTER);
            card.setPadding(new Insets(15));
            card.setStyle("-fx-background-color: #333; -fx-background-radius: 8;");

            Label className = new Label(type.getDisplayName());
            className.setStyle("-fx-text-fill: white; -fx-font-size: 16; -fx-font-weight: bold;");

            Label stats = new Label(
                    "HP: " + type.getBaseHealth() +
                            "  ATK: " + type.getBaseAttack() +
                            "  DEF: " + type.getBaseDefense()
            );
            stats.setStyle("-fx-text-fill: #aaa; -fx-font-size: 11;");

            Button selectBtn = new Button("Select");
            selectBtn.setOnAction(e -> {
                String name = nameField.getText().isEmpty() ? "Hero" : nameField.getText();
                engine.initNewGame(type, name);
                screenManager.showScreen(GameState.PLAYING);
            });

            card.getChildren().addAll(className, stats, selectBtn);
            classButtons.getChildren().add(card);
        }

        getChildren().addAll(title, nameField, classButtons);
    }
}