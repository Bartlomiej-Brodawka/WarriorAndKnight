package org.example.models;

import org.example.models.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Warrior implements Unit, Cloneable, IWarrior {

    public static final int INITIAL_HEALTH = 50;
    public static final int ATTACK = 5;
    private int health;
    private int attack;
    private IWarrior warriorBehind;
    private IWarrior warriorInFrontOf;
    private static final Logger log = LoggerFactory.getLogger(Warrior.class);

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

    @Override
    public void setHealth(int health) {
        this.health = health;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
    }

    @Override
    public void hit(IWarrior opponent) {
        log.trace("{} hits {} with {} point of attack with {} point of health.",
                this.getClass().getSimpleName(),
                opponent.getClass().getSimpleName(),
                this.getAttack(),
                this.getHealth());
        opponent.receiveHit(
                new SimpleDamage(getAttack(), this)
        );
        processCommand(new HealCommand(), this);
    }

    @Override
    public void receiveHit(IDamage damage) {
        health -= damage.hitPoints();
        log.trace("{} receive {} points of damage. {} points of life left.", this.getClass().getSimpleName(), damage.hitPoints(), this.getHealth());
    }

    @Override
    public IWarrior getWarriorBehind() {
        return warriorBehind;
    }

    @Override
    public void setWarriorBehind(IWarrior warrior) {
        warriorBehind = warrior;
    }

    @Override
    public IWarrior getWarriorInFrontOf() {
        return warriorInFrontOf;
    }

    @Override
    public void setWarriorInFrontOf(IWarrior warrior) {
        warriorInFrontOf = warrior;
    }

    @Override
    public void equipWeapon(IWeapon weapon) {

    }
}
