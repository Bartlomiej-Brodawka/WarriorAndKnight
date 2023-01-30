package org.example.models.interfaces;

public interface CanHeal {
    default void heal(IWarrior warrior) {
        warrior.setHealth(
                Math.min(
                        warrior.getInitialHealth(),
                        warrior.getHealth() + getHealPower()
                )
        );
    }

    int getHealPower();
    void setHealPower(int healPower);
}
