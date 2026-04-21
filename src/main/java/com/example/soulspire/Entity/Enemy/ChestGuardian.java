package com.example.soulspire.Entity.Enemy;

import com.example.soulspire.Entity.Chest;
import com.example.soulspire.Entity.Player.Player;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * A special enemy guarding a {@link Chest} containing a SoulEcho.
 * Only aggros when the player is near the chest.
 * When killed, the guarded chest becomes interactable.
 */
public class ChestGuardian extends Enemy {

    private Chest guardedChest;

    /**
     * Creates a chest guardian near the given chest.
     *
     * @param x            spawn x position (should be near the chest)
     * @param y            spawn y position
     * @param floorNumber  current floor for scaling
     * @param guardedChest the chest this guardian protects
     */
    public ChestGuardian(double x, double y, int floorNumber, Chest guardedChest) {
        super(x, y, 32, 32,
                60,    // baseHealth
                15,    // baseAttack
                5,     // defense
                2.0,   // moveSpeed
                120,   // aggroRange (short — only near chest)
                40,    // attackRange
                1.0,   // attackCooldown
                floorNumber);
        this.guardedChest = guardedChest;
    }

    @Override
    public void updateAI(Player target, double deltaTime) {

    }

    @Override
    protected void onDeath() {

    }

    @Override
    public void render(GraphicsContext gc, double cameraX, double cameraY) {

    }
}