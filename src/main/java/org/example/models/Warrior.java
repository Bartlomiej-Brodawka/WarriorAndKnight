package org.example.models;

public class Warrior implements Unit, Cloneable{

    public static final int INITIAL_HEALTH = 50;
    public static final int ATTACK = 5;
    private int health;
    private int attack;
    private int defense;

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

    protected Warrior(int health, int attack, int defense) {
        this.health = health;
        this.attack = attack;
        this.defense = defense;
    }

    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return attack;
    }

    int getHealth() {
        return health;
    }
    int getDefense() {
        return defense;
    }

    public void hit(Warrior enemy) {
        if(enemy.defense > 0 && (getAttack() - enemy.defense) > 0) {
            enemy.health = enemy.health - (getAttack() - enemy.defense);
        } else if (enemy.defense < 0){
            enemy.health -= getAttack();
        }
    }
}
