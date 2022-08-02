package org.example.models;

import org.example.models.interfaces.IWarrior;
import org.example.models.interfaces.Unit;

public class Warrior implements Unit, Cloneable, IWarrior {

    public static final int INITIAL_HEALTH = 50;
    public static final int ATTACK = 5;
    private int health;
    private int attack;

    @Override
    public Warrior clone() {
        try {
            return (Warrior) super.clone();
        } catch (CloneNotSupportedException ignored) {
            //ignored
        }
        return null;
    }

    public Warrior() {
        this(INITIAL_HEALTH, ATTACK);
    }

    protected Warrior(int health, int attack) {
        this.health = health;
        this.attack = attack;
    }
    @Override
    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return attack;
    }

    public int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    @Override
    public void reduceHealthBasedOnDamage(int damage) {
        setHealth(getHealth() - damage);
    }
}
