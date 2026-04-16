package com.example.soulspire.Entity.Npc;

import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Util.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * NPC found in safe zone floors. Allows the player to repair
 * equipment or trade materials.
 */
public class Merchant extends NPC {

    private static final GameLogger logger = GameLogger.getLogger(Merchant.class);

    public Merchant(double x, double y) {
        super("Merchant", x, y, 32, 32);
        this.dialogueLines = new String[]{
                "Looking to trade?",
                "I have rare goods from the lower floors."
        };
    }

    @Override
    public void onInteract(Player player) {
        logger.info(player.getName() + " interacted with Merchant");
        // TODO: trigger Shop UI through GameEngine/ScreenManager
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        gc.setFill(Color.DARKGREEN);
        gc.fillRect(screenX, screenY, width, height);
        gc.setFill(Color.WHITE);
        gc.fillText(name, screenX, screenY - 5);
    }
}