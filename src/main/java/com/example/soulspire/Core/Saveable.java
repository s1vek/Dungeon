package com.example.soulspire.Core;

import java.util.Map;

public interface Saveable {

    /**
     * Serializes the current state into a map of key-value pairs.
     * The map can then be converted to JSON by {@link SaveManager}.
     *
     * @return map representing the serialized state
     */
    Map<String, Object> toSaveData();

    /**
     * Restores the object's state from a previously saved data map.
     *
     * @param data the saved state to restore from
     */
    void loadSaveData(Map<String, Object> data);
}
