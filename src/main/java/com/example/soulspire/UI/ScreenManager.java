package com.example.soulspire.UI;

import com.example.soulspire.Core.GameEngine;
import com.example.soulspire.Core.GameState;
import javafx.scene.layout.StackPane;

/**
 * Manages switching between different UI screens based on the current {@link GameState}.
 * Overlays screens on top of the game canvas.
 */
public class ScreenManager {

    private final StackPane root;
    private final GameEngine engine;

    private MainMenuScreen mainMenu;
    private CharacterSelectScreen characterSelect;
    private PauseMenu pauseMenu;
    private GameOverScreen gameOver;
    private VictoryScreen victory;

    public ScreenManager(StackPane root, GameEngine engine, StackPane root1, GameEngine engine1) {
        this.root = root1;
        this.engine = engine1;
    }

    /**
     * Initializes all screens. Call once after construction.
     */
    public void initScreens() {

    }

    /**
     * Shows the appropriate screen overlay for the given game state.
     */
    public void showScreen(GameState state) {

    }

    /**
     * Removes all screen overlays.
     */
    public void hideAll() {

    }
}