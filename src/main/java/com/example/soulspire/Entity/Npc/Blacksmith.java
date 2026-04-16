package com.example.soulspire.Entity.Npc;

import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Util.GameLogger;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * NPC found in safe zone floors. Allows the player to craft
 * equipment from collected materials (Iron Ore, Ethereal Dust).
 */
public class Blacksmith extends NPC {

    private static final GameLogger logger = GameLogger.getLogger(Blacksmith.class);

    public Blacksmith(double x, double y) {
        super("Blacksmith", x, y, 32, 32);
        this.dialogueLines = new String[]{
                "Need something forged?",
                "Bring me materials and I'll make you stronger."
        };
    }

    @Override
    public void onInteract(Player player) {
        logger.info(player.getName() + " interacted with Blacksmith");
        // TODO: trigger CraftingUI screen through GameEngine/ScreenManager
    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {
        double screenX = x - cameraX;
        double screenY = y - cameraY;
        gc.setFill(Color.SADDLEBROWN);
        gc.fillRect(screenX, screenY, width, height);
        gc.setFill(Color.WHITE);
        gc.fillText(name, screenX, screenY - 5);
    }
}