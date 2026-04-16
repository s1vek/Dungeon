package com.example.soulspire.Entity;

/**
 * Directions of movement
 */

public enum Direction {
    UP(0, -1),
    DOWN(0, 1),
    LEFT(-1, 0),
    RIGHT(1, 0);

    private final int dx;
    private final int dy;

    Direction(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * @return horizontal component of the direction vector (-1, 0, or 1)
     */
    public int getDx() { return dx; }

    /**
     * @return vertical component of the direction vector (-1, 0, or 1)
     */
    public int getDy() { return dy; }

    /**
     * Returns the opposite direction (e.g. UP returns DOWN).
     * Useful for knockback and leap-backwards mechanics.
     *
     * @return the opposite direction
     */
    public Direction getOpposite() {
        return switch (this) {
            case UP -> DOWN;
            case DOWN -> UP;
            case LEFT -> RIGHT;
            case RIGHT -> LEFT;
        };
    }
}
