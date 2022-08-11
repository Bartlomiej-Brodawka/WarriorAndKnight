package org.example.models.interfaces;

public interface CanHeal {
    default void heal(IWarrior warrior) {
        var currentHealth = warrior.getHealth();
        int healthToSet = Math.min(warrior.getInitialHealth(), currentHealth + getHealPower());

        warrior.setHealth(healthToSet);
    }

    int getHealPower();
    void setHealPower(int healPower);
}
