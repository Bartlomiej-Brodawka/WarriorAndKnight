package org.example.models;

public class Warrior implements Unit, Cloneable{

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

    public boolean isAlive() {
        return health > 0;
    }

    public int getAttack() {
        return attack;
    }

    int getHealth() {
        return health;
    }

    void setHealth(int health) {
        this.health = health;
    }

    public void hit(Warrior enemy) {
        if(enemy instanceof Defender defender && getAttack() > Defender.DEFENSE) {
            enemy.health = enemy.health -(getAttack() - defender.getDefense());
        }
        if(!(enemy instanceof Defender)) {
            enemy.health -= getAttack();
        }
    }
}
