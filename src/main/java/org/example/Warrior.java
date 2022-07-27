package org.example;

public class Warrior {

    static final int INITIAL_HEALTH = 50;
    private int health = INITIAL_HEALTH;
    static final int ATTACK = 5;

    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return ATTACK;
    }

    int getHealth() {
        return health;
    }

    public void hit(Warrior enemy) {
        enemy.health -= getAttack();
    }
}
