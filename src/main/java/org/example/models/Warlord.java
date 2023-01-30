package org.example.models;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Warlord extends Defender{
    public static final int INITIAL_HEALTH = 100;
    public static final int ATTACK = 4;
    public static final int DEFENSE = 2;
    private int defense;
    private int health;
    private int attack;
    private int initialHealth;

    private static final Logger log = LoggerFactory.getLogger(Warlord.class);

    public Warlord() {
        this.health = INITIAL_HEALTH;
        this.attack = ATTACK;
        this.defense = DEFENSE;
        this.initialHealth = INITIAL_HEALTH;
    }

    @Override
    public boolean isAlive() {
        return health > 0;
    }

    @Override
    public int getAttack() {
        return attack;
    }

    @Override
    public void setAttack(int attack) {
        this.attack = attack;
    }

    @Override
    public int getHealth() {
        return health;
    }

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void setDefense(int defense) {
        this.defense = defense;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

}
