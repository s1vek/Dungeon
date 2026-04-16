package com.example.soulspire.UI;

import com.example.soulspire.Core.GameConfig;
import com.example.soulspire.Core.GameEngine;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;

/**
 * The main game screen containing the Canvas for rendering
 * and the HUD overlay for player stats.
 */
public class GameScreen extends StackPane {

    private Canvas canvas;
    private HUDOverlay hud;
    private GraphicsContext gc;

    public GameScreen(GameEngine engine) {
        canvas = new Canvas(GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        gc = canvas.getGraphicsContext2D();
        hud = new HUDOverlay(engine);

        getChildren().addAll(canvas, hud);
    }

    /**
     * @return the graphics context for game rendering
     */
    public GraphicsContext getGraphicsContext() { return gc; }

    /**
     * @return the HUD overlay
     */
    public HUDOverlay getHud() { return hud; }
}