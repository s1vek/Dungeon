package com.example.soulspire.Combat;

import com.example.soulspire.Core.GameConfig;
import com.example.soulspire.Entity.Entity;
import com.example.soulspire.World.Floor;
import com.example.soulspire.World.Tile;

import java.util.ArrayList;
import java.util.List;

/**
 * Provides static collision detection methods for entity-entity
 * and entity-tile interactions.
 */
public class CollisionDetector {

    private CollisionDetector() {}

    /**
     * Checks if two entities' bounding boxes overlap.
     */
    public static boolean checkCollision(Entity a, Entity b) {
        return a.getBounds().intersects(b.getBounds());
    }

    /**
     * Checks if an entity would collide with a non-walkable tile
     * at the given position.
     *
     * @param entity the entity to check
     * @param floor  the floor containing the tile grid
     * @param newX   proposed x position
     * @param newY   proposed y position
     * @return true if the position is blocked
     */
    public static boolean checkTileCollision(Entity entity, Floor floor, double newX, double newY) {
        int tileSize = GameConfig.TILE_SIZE;

        // Check all four corners of the entity's hitbox
        int leftTile = (int)(newX / tileSize);
        int rightTile = (int)((newX + entity.getWidth() - 1) / tileSize);
        int topTile = (int)(newY / tileSize);
        int bottomTile = (int)((newY + entity.getHeight() - 1) / tileSize);

        for (int ty = topTile; ty <= bottomTile; ty++) {
            for (int tx = leftTile; tx <= rightTile; tx++) {
                Tile tile = floor.getTileAt(tx, ty);
                if (tile == null || !tile.isWalkable()) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns all entities within a circular area.
     */
    public static List<Entity> getEntitiesInArea(double centerX, double centerY,
                                                 double radius, List<Entity> entities) {
        List<Entity> result = new ArrayList<>();
        double r2 = radius * radius;
        for (Entity e : entities) {
            double dx = e.getCenterX() - centerX;
            double dy = e.getCenterY() - centerY;
            if (dx * dx + dy * dy <= r2) {
                result.add(e);
            }
        }
        return result;
    }
}