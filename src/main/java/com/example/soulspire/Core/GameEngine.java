package com.example.soulspire.Core;

import com.example.soulspire.Combat.CollisionDetector;
import com.example.soulspire.Combat.CombatSystem;
import com.example.soulspire.Entity.Entity;
import com.example.soulspire.Entity.Enemy.Enemy;
import com.example.soulspire.Entity.Enemy.RangedEnemy;
import com.example.soulspire.Entity.LivingEntity;
import com.example.soulspire.Entity.Player.*;
import com.example.soulspire.Entity.Projectile;
import com.example.soulspire.Entity.Direction;
import com.example.soulspire.Util.GameLogger;
import com.example.soulspire.World.Floor;
import com.example.soulspire.World.Tower;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;

/**
 * Central game engine that coordinates all game systems.
 * Handles the game loop cycle: input → update → collision → render.
 */
public class GameEngine {

    private static final GameLogger logger = GameLogger.getLogger(GameEngine.class);

    private Tower tower;
    private Player player;
    private GameStateManager stateManager;
    private CombatSystem combatSystem;
    private InputHandler inputHandler;
    private double cameraX;
    private double cameraY;

    public GameEngine(InputHandler inputHandler) {
        this.inputHandler = inputHandler;
        this.stateManager = new GameStateManager();
        this.combatSystem = new CombatSystem();
        this.tower = new Tower();
        this.cameraX = 0;
        this.cameraY = 0;
    }

    /**
     * Initializes a new game with the selected character type.
     *
     * @param type the chosen character class
     * @param name the player's name
     */
    public void initNewGame(PlayerType type, String name) {
        tower.generateFloors();
        Floor startFloor = tower.getCurrentFloor();
        double sx = startFloor.getSpawnX();
        double sy = startFloor.getSpawnY();

        player = switch (type) {
            case WARRIOR -> new Warrior(name, sx, sy);
            case MAGE -> new Mage(name, sx, sy);
            case HUNTER -> new Hunter(name, sx, sy);
            case SHAMAN -> new Shaman(name, sx, sy);
        };

        stateManager.setState(GameState.PLAYING);
        logger.info("New game started: " + name + " (" + type.getDisplayName() + ")");
    }

    /**
     * Main update method called every frame by the GameLoop.
     */
    public void update(double deltaTime) {
        if (stateManager.isPaused()) return;

        // Handle pause toggle
        if (inputHandler.isKeyJustPressed(KeyCode.ESCAPE)) {
            stateManager.togglePause();
            return;
        }

        if (stateManager.isPlaying() || stateManager.isInSafeZone()) {
            handlePlayerInput(deltaTime);
            Floor currentFloor = tower.getCurrentFloor();

            // Update player
            player.update(deltaTime);

            // Update floor (all entities)
            currentFloor.update(deltaTime);

            // Update enemy AI
            for (Entity entity : currentFloor.getEntities()) {
                if (entity instanceof Enemy enemy && enemy.isActive()) {
                    enemy.update(deltaTime);
                    // Pass player target to AI
                    if (!player.isDead()) {
                        enemy.updateAI(player, deltaTime);
                    }
                    // Spawn projectiles from ranged enemies
                    if (enemy instanceof RangedEnemy ranged) {
                        Projectile p = ranged.consumeProjectile();
                        if (p != null) currentFloor.addEntity(p);
                    }
                }
            }

            // Check collisions
            checkCollisions(currentFloor);
            updateCamera();

            // Check player death
            if (player.isDead()) {
                handlePlayerDeath();
            }
        }

        inputHandler.update();
    }

    /**
     * Processes keyboard input for player movement, attacks, and abilities.
     */
    private void handlePlayerInput(double deltaTime) {
        // Movement — WASD
        if (inputHandler.isKeyPressed(KeyCode.W)) tryMove(Direction.UP, deltaTime);
        if (inputHandler.isKeyPressed(KeyCode.S)) tryMove(Direction.DOWN, deltaTime);
        if (inputHandler.isKeyPressed(KeyCode.A)) tryMove(Direction.LEFT, deltaTime);
        if (inputHandler.isKeyPressed(KeyCode.D)) tryMove(Direction.RIGHT, deltaTime);

        // Basic attack — Space
        if (inputHandler.isKeyPressed(KeyCode.SPACE)) {
            double mx = inputHandler.getMouseX() + cameraX;
            double my = inputHandler.getMouseY() + cameraY;
            player.attack(mx, my);
        }

        // Abilities — 1, 2, 3
        double mx = inputHandler.getMouseX() + cameraX;
        double my = inputHandler.getMouseY() + cameraY;
        if (inputHandler.isKeyJustPressed(KeyCode.DIGIT1)) player.useAbility(0, mx, my);
        if (inputHandler.isKeyJustPressed(KeyCode.DIGIT2)) player.useAbility(1, mx, my);
        if (inputHandler.isKeyJustPressed(KeyCode.DIGIT3)) player.useAbility(2, mx, my);

        // Interact — E
        if (inputHandler.isKeyJustPressed(KeyCode.E)) {
            player.interact(tower.getCurrentFloor().getEntities());
        }
    }

    /**
     * Attempts to move the player, checking tile collisions first.
     */
    private void tryMove(Direction dir, double deltaTime) {
        double newX = player.getX() + dir.getDx() * player.getMoveSpeed() * deltaTime;
        double newY = player.getY() + dir.getDy() * player.getMoveSpeed() * deltaTime;

        Floor floor = tower.getCurrentFloor();
        if (!CollisionDetector.checkTileCollision(player, floor, newX, player.getY())) {
            player.setX(newX);
        }
        if (!CollisionDetector.checkTileCollision(player, floor, player.getX(), newY)) {
            player.setY(newY);
        }
        player.setFacing(dir);
    }

    /**
     * Checks all projectile-entity collisions on the current floor.
     */
    private void checkCollisions(Floor floor) {
        for (Entity entity : floor.getEntities()) {
            if (!(entity instanceof Projectile projectile) || !entity.isActive()) continue;

            for (Entity target : floor.getEntities()) {
                if (target == projectile || target == projectile.getOwner()) continue;
                if (target instanceof LivingEntity living && living.isActive()) {
                    if (CollisionDetector.checkCollision(projectile, living)) {
                        combatSystem.processProjectileHit(projectile, living);
                    }
                }
            }

            // Also check projectile vs player (if enemy fired it)
            if (!(projectile.getOwner() instanceof Player) && !player.isDead()) {
                if (CollisionDetector.checkCollision(projectile, player)) {
                    combatSystem.processProjectileHit(projectile, player);
                }
            }
        }
    }

    /**
     * Centers the camera on the player.
     */
    private void updateCamera() {
        cameraX = player.getCenterX() - GameConfig.WINDOW_WIDTH / 2.0;
        cameraY = player.getCenterY() - GameConfig.WINDOW_HEIGHT / 2.0;
    }

    /**
     * Handles player death: respawn or game over.
     */
    private void handlePlayerDeath() {
        Floor floor = tower.getCurrentFloor();
        player.respawn(floor.getSpawnX(), floor.getSpawnY());

        if (player.isGameOver()) {
            stateManager.setState(GameState.GAME_OVER);
            logger.info("Game over! All lives lost.");
        }
    }

    /**
     * Renders the current game state.
     */
    public void render(GraphicsContext gc) {
        gc.clearRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, GameConfig.WINDOW_WIDTH, GameConfig.WINDOW_HEIGHT);

        if (stateManager.isPlaying() || stateManager.isInSafeZone() || stateManager.isPaused()) {
            tower.getCurrentFloor().render(gc, cameraX, cameraY);
            player.render(gc, cameraX, cameraY);
        }
    }

    // --- Getters for UI and other systems ---

    public Player getPlayer() { return player; }
    public Tower getTower() { return tower; }
    public GameStateManager getStateManager() { return stateManager; }
    public CombatSystem getCombatSystem() { return combatSystem; }
    public InputHandler getInputHandler() { return inputHandler; }
}