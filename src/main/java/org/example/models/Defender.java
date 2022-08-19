package org.example.models;

import org.example.models.interfaces.HasDefence;
import org.example.models.interfaces.IDamage;
import org.example.models.interfaces.IWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defender extends Warrior implements HasDefence {
    public static final int INITIAL_HEALTH = 60;
    public static final int ATTACK = 3;
    public static final int DEFENSE = 2;
    private int defense;
    private int initialHealth;

    private static final Logger log = LoggerFactory.getLogger(Defender.class);

    public Defender() {
        super(INITIAL_HEALTH, ATTACK);
        this.defense = DEFENSE;
        this.initialHealth = INITIAL_HEALTH;
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

    @Override
    public void receiveHit(IDamage damage) {
        log.trace("{} has {} points of defense. Reduce damage to {} points.",
                this.getClass().getSimpleName(),
                getDefense(),
                damage.hitPoints()-getDefense());
        setHealth(getHealth() -(Math.max(0, (damage.hitPoints() - getDefense()))));
        log.trace("{} receive {} points of damage. {} points of life left.",
                this.getClass().getSimpleName(),
                damage.hitPoints()-getDefense(),
                this.getHealth());
    }



    @Override
    public void equipWeapon(IWeapon weapon) {
        super.equipWeapon(weapon);
        changeSoldierDefenseByWeaponStats(weapon.getDefense());
    }

    @Override
    public void looseWeaponBonuses(IWeapon weapon) {
        super.looseWeaponBonuses(weapon);
        reduceSoldierDefenseByWeaponStats(weapon.getDefense());
    }

    private void changeSoldierDefenseByWeaponStats(int defensePoints) {
        setDefense(Math.max(0,getDefense()+defensePoints));
        log.trace("{} defense has been increased by {} points, now it is {} points",
                this.getClass().getSimpleName(),
                defensePoints,
                getDefense()
        );
    }

    private void reduceSoldierDefenseByWeaponStats(int defensePoints) {
        setDefense(Math.max(0,getDefense() - defensePoints));
        log.trace("{} defense has been decreased by {} points, now it is {} points",
                this.getClass().getSimpleName(),
                defensePoints,
                getDefense()
        );
    }
}