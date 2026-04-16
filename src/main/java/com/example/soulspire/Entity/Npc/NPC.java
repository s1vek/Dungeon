package com.example.soulspire.Entity.Npc;

import com.example.soulspire.Entity.Entity;
import com.example.soulspire.Entity.Interactable;
import com.example.soulspire.Entity.Player.Player;
import javafx.scene.canvas.GraphicsContext;

/**
 * Abstract base class for non-player characters found in safe zones.
 * NPCs cannot move or take damage — they stand in place and wait
 * for the player to interact with them using the E key.
 *
 * <p>Implements {@link Interactable} so that {@link Player#interact} can
 * detect and trigger NPC dialogue/actions.</p>
 *
 * <p>Subclasses:</p>
 * <ul>
 *   <li>{@link Blacksmith} — opens crafting UI to forge equipment</li>
 *   <li>{@link Merchant} — opens shop UI to buy/sell items</li>
 * </ul>
 */
public abstract class NPC extends Entity implements Interactable {

    /** Default interaction range for all NPCs in pixels. */
    protected static final double DEFAULT_INTERACTION_RANGE = 80.0;

    /** Display name shown above the NPC's head. */
    protected String name;

    /** Dialogue lines shown when the player interacts. */
    protected String[] dialogueLines;

    /**
     * Creates a new NPC at the given position.
     *
     * @param name   display name of the NPC
     * @param x      x position on the map
     * @param y      y position on the map
     * @param width  hitbox width
     * @param height hitbox height
     */
    protected NPC(String name, double x, double y, double width, double height) {
        super(x, y, width, height);
        this.name = name;
        this.dialogueLines = new String[0];
    }

    /**
     * NPCs do not update their state — they are static entities.
     * Override in subclasses if you want idle animations.
     */
    @Override
    public void update(double deltaTime) {
        // NPCs are static — no update logic needed
    }

    /**
     * Renders the NPC sprite and their name above their head.
     */
    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {

    }

    @Override
    public double getInteractionRange() {
        return DEFAULT_INTERACTION_RANGE;
    }

    @Override
    public boolean canInteract() {
        return true;
    }

    // --- Getters ---

    public String getName() { return name; }
    public String[] getDialogueLines() { return dialogueLines; }
}