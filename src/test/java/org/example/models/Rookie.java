package org.example.models;

public class Rookie extends Warrior {

    public static final int ATTACK = 1;

    public Rookie() {
        super(50, ATTACK);
    }

    @Override
    public int getAttack() {
        return 1;
    }
}
