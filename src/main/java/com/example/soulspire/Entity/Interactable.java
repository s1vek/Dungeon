package com.example.soulspire.Entity;


import com.example.soulspire.Entity.Player.Player;

public interface Interactable {

    /**
     * Called when the player presses the interaction key (E) while near this entity.
     *
     * @param player the player initiating the interaction
     */
    void onInteract(Player player);

    /**
     * Returns the interaction range — how close the player must be to interact.
     *
     * @return interaction range in pixels
     */
    double getInteractionRange();

    /**
     * Returns whether this entity can currently be interacted with.
     * For example, an opened chest returns false.
     *
     * @return true if interaction is possible
     */
    boolean canInteract();
}
