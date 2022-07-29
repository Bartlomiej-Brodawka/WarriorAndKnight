package org.example.models;

public class Defender extends Warrior{
    public static final int INITIAL_HEALTH = 60;
    public static final int ATTACK = 3;
    public static final int DEFENSE = 2;

    public Defender() {
        super(INITIAL_HEALTH, ATTACK, DEFENSE);
    }
}
