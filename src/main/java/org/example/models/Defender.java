package org.example.models;

public class Defender extends Warrior{
    public static final int INITIAL_HEALTH = 60;
    public static final int ATTACK = 3;
    public static final int DEFENSE = 2;
    private int defense;

    public Defender() {
        super(INITIAL_HEALTH, ATTACK);
        this.defense = DEFENSE;
    }

    public int getDefense() {
        return defense;
    }

    public void hit(Defender enemy) {
        if((getAttack() - enemy.defense) > 0) {
            enemy.setHealth(enemy.getHealth() -(getAttack() - enemy.defense));
        }
    }
}