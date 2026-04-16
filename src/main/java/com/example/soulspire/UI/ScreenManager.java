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

    public ScreenManager(StackPane root, GameEngine engine) {
        this.root = root;
        this.engine = engine;
    }

    /**
     * Initializes all screens. Call once after construction.
     */
    public void initScreens() {
        mainMenu = new MainMenuScreen(engine, this);
        characterSelect = new CharacterSelectScreen(engine, this);
        pauseMenu = new PauseMenu(engine, this);
        gameOver = new GameOverScreen(engine, this);
        victory = new VictoryScreen(engine, this);
    }

    /**
     * Shows the appropriate screen overlay for the given game state.
     */
    public void showScreen(GameState state) {
        hideAll();
        switch (state) {
            case MAIN_MENU -> { root.getChildren().add(mainMenu); mainMenu.setVisible(true); }
            case CHARACTER_SELECT -> { root.getChildren().add(characterSelect); characterSelect.setVisible(true); }
            case PAUSED -> { root.getChildren().add(pauseMenu); pauseMenu.setVisible(true); }
            case GAME_OVER -> { root.getChildren().add(gameOver); gameOver.setVisible(true); }
            case VICTORY -> { root.getChildren().add(victory); victory.setVisible(true); }
            default -> {} // PLAYING, SAFE_ZONE — no overlay
        }
    }

    /**
     * Removes all screen overlays.
     */
    public void hideAll() {
        root.getChildren().removeIf(node ->
                node instanceof MainMenuScreen || node instanceof CharacterSelectScreen ||
                        node instanceof PauseMenu || node instanceof GameOverScreen ||
                        node instanceof VictoryScreen
        );
    }
}