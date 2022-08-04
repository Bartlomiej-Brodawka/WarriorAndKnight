package org.example.models;

public class Knight extends Warrior {
    public static final int ATTACK = 7;

    public Knight() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public int getAttack() {
        return ATTACK;
    }
}
