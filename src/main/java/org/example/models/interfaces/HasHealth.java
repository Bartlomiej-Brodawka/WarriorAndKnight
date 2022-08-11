package org.example.models.interfaces;

public interface HasHealth {
    int getHealth();
    void setHealth(int health);
    int getInitialHealth();
    void setInitialHealth(int initialHealth);

    default boolean isAlive() {
        return getHealth() > 0;
    }
}
