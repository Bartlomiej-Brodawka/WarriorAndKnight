package org.example.models.interfaces;

public interface HasHealth {
    int getHealth();
    void setHealth(int health);
    int getInitialHealth();

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
