package org.example.models;

import org.example.models.interfaces.CanSteal;
import org.example.models.interfaces.IDamage;
import org.example.models.interfaces.IWarrior;
import org.example.models.interfaces.IWeapon;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Rogue extends Warrior implements CanSteal {

    public static final int INITIAL_HEALTH = 60;
    public static final int ATTACK = 3;
    private int initialHealth;
    private boolean hasStolen = false;

    private static final Logger log = LoggerFactory.getLogger(Rogue.class);

    public Rogue() {
        super(INITIAL_HEALTH, ATTACK);
        this.initialHealth = INITIAL_HEALTH;
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
        super.receiveHit(damage);
        if(!hasStolen && !damage.damageDealer().isWeaponryEmpty()) {
            log.trace("{} starts stealing a weapon!", getClass().getSimpleName());
            stealWeapons(damage.damageDealer());
        }
    }

    @Override
    public void stealWeapons(IWarrior victimOfRobbery) {
        for(IWeapon weapon : victimOfRobbery.getWeapons()) {
            log.trace("{} has stolen the {}",
                    getClass().getSimpleName(),
                    weapon.getName());
            equipWeapon(weapon);
            victimOfRobbery.looseWeaponBonuses(weapon);
        }
        victimOfRobbery.cleanWeaponry();
        rogueHasStolenWeapons();
    }

    @Override
    public void rogueHasStolenWeapons() {
        this.hasStolen = true;
    }
}
