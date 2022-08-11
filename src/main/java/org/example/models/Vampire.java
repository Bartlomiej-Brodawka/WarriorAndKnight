package org.example.models;

import org.example.models.interfaces.HasVampirism;
import org.example.models.interfaces.IWarrior;
import org.example.models.interfaces.IWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vampire extends Warrior implements HasVampirism {
    public static final int INITIAL_HEALTH = 40;
    public static final int ATTACK = 4;
    public static final int VAMPIRISM = 50;
    private int vampirism;
    private int initialHealth;

    private static final Logger log = LoggerFactory.getLogger(Vampire.class);

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
        this.vampirism = VAMPIRISM;
        this.initialHealth = INITIAL_HEALTH;
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    @Override
    public void setVampirism(int vampirism) {
        this.vampirism = vampirism;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    @Override
    public void setInitialHealth(int initialHealth) {
        this.initialHealth = initialHealth;
    }

    @Override
    public void hit(IWarrior opponent) {
        super.hit(opponent);
        if(opponent instanceof Defender defender) {
            setHealth(getHealth() + ((getAttack() - defender.getDefense()) * vampirism/100));
            checkLevelOfHealth();
            log.trace("{} drain {} points of life from {}. {} points of life left.",
                    getClass().getSimpleName(),
                    ((getAttack() - defender.getDefense()) * vampirism/100),
                    opponent.getClass().getSimpleName(),
                    getHealth()
            );
        } else {
            setHealth(getHealth() + (getAttack() * vampirism / 100));
            checkLevelOfHealth();
            log.trace("{} drain {} points of life from {}. {} points of life left.",
                    getClass().getSimpleName(),
                    getAttack() * vampirism/100,
                    opponent.getClass().getSimpleName(),
                    getHealth()
            );
        }

    }

    private void checkLevelOfHealth() {
        if(getHealth() > getInitialHealth()) {
            setHealth(getInitialHealth());
        }
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
        changeSoldierVampirismByWeaponStats(weapon.getVampirism());
    }

    private void changeSoldierVampirismByWeaponStats(int vampirismPoints) {
        setVampirism(Math.max(0,getVampirism()+vampirismPoints));
        log.trace("{} heal power has been increased by {} points, now it is {} points",
                this.getClass().getSimpleName(),
                vampirismPoints,
                getVampirism()
        );
    }
}
