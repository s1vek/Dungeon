package com.example.soulspire.Core;

import com.example.soulspire.Entity.Player.Player;
import com.example.soulspire.Util.GameLogger;
import com.example.soulspire.World.Tower;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

/**
 * Handles saving and loading game state to/from a JSON file.
 * Saves player stats, inventory, and tower progress.
 *
 * <p>Auto-save is triggered when entering a safe zone, using a
 * {@link javafx.concurrent.Task} to avoid blocking the UI thread
 * (required by assignment for threading demonstration).</p>
 */
public class SaveManager {

    private static final GameLogger logger = GameLogger.getLogger(SaveManager.class);

    private SaveManager() {}

    /**
     * Saves the game state to a file. Collects data from Player and Tower
     * via their {@link Saveable} interface.
     *
     * @param player the player to save
     * @param tower  the tower to save
     */
    public static void save(Player player, Tower tower) {
        try {
            Map<String, Object> saveData = new HashMap<>();
            saveData.put("player", player.toSaveData());
            saveData.put("tower", tower.toSaveData());

            // Simple serialization — write as string representation
            // TODO: Replace with Gson/Jackson for proper JSON serialization
            try (ObjectOutputStream oos = new ObjectOutputStream(
                    new FileOutputStream(GameConfig.SAVE_FILE_PATH))) {
                oos.writeObject(saveData);
            }
            logger.info("Game saved to " + GameConfig.SAVE_FILE_PATH);
        } catch (IOException e) {
            logger.error("Failed to save game", e);
        }
    }

    /**
     * Loads save data from file.
     *
     * @return the loaded data map, or null if no save exists
     */
    @SuppressWarnings("unchecked")
    public static Map<String, Object> load() {
        try {
            if (!hasSaveFile()) return null;

            try (ObjectInputStream ois = new ObjectInputStream(
                    new FileInputStream(GameConfig.SAVE_FILE_PATH))) {
                return (Map<String, Object>) ois.readObject();
            }
        } catch (IOException | ClassNotFoundException e) {
            logger.error("Failed to load save", e);
            return null;
        }
    }

    /**
     * Creates an auto-save using a background Task (threading requirement).
     * Should be called when the player enters a safe zone.
     */
    public static javafx.concurrent.Task<Void> createAutoSaveTask(Player player, Tower tower) {
        return new javafx.concurrent.Task<>() {
            @Override
            protected Void call() {
                updateMessage("Saving...");
                save(player, tower);
                updateMessage("Saved!");
                return null;
            }
        };
    }

    /**
     * @return true if a save file exists on disk
     */
    public static boolean hasSaveFile() {
        return Files.exists(Path.of(GameConfig.SAVE_FILE_PATH));
    }

    /**
     * Deletes the save file.
     */
    public static void deleteSave() {
        try {
            Files.deleteIfExists(Path.of(GameConfig.SAVE_FILE_PATH));
            logger.info("Save file deleted");
        } catch (IOException e) {
            logger.error("Failed to delete save", e);
        }
    }
}