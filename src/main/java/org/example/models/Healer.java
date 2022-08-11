package org.example.models;

import org.example.models.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Healer extends Warrior implements CanHeal {

    private static final int ATTACK = 0;
    private static final int INITIAL_HEALTH = 60;
    private static final int HEAL_POWER = 2;

    private int healPower;
    private int initialHealth;

    private static final Logger log = LoggerFactory.getLogger(Healer.class);

    public Healer() {
        super(INITIAL_HEALTH, ATTACK);
        this.healPower = HEAL_POWER;
        this.initialHealth = INITIAL_HEALTH;
    }

    @Override
    public int getInitialHealth() {
        return initialHealth;
    }

    public int getHealPower() {
        return healPower;
    }

    @Override
    public void setHealPower(int healPower) {
        this.healPower = healPower;
    }

    @Override
    public void hit(IWarrior opponent) {}

    @Override
    public void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof HealCommand && sender instanceof Healer && sender.getWarriorInFrontOf()!=null) {
            heal(sender.getWarriorInFrontOf());
            log.trace("{} heals {} with {} points of healing, left points of life {}.",
                    this.getClass().getSimpleName(),
                    sender.getWarriorInFrontOf().getClass().getSimpleName(),
                    this.getHealPower(),
                    sender.getWarriorInFrontOf().getHealth()
                    );
        }
        super.processCommand(command, sender);
    }

    @Override
    public void equipWeapon(IWeapon weapon) {
        log.trace("{} is equipped with a {}",
                this.getClass().getSimpleName(),
                weapon
        );
        changeSoldierHealthByWeaponStats(weapon.getHealth());
        changeSoldierHealPowerByWeaponStats(weapon.getHealPower());
    }

    private void changeSoldierHealPowerByWeaponStats(int healPowerPoints) {
        setHealPower(Math.max(0, getHealPower()+healPowerPoints));
        log.trace("{} heal power has been increased by {} points, now it is {} points",
                this.getClass().getSimpleName(),
                healPowerPoints,
                getHealPower()
        );
    }

    private void changeSoldierHealthByWeaponStats(int initialHealthPoints) {
        var soldierHealthStatsWithWeaponStats = getHealth() + initialHealthPoints;
        setHealth(Math.max(0,soldierHealthStatsWithWeaponStats));
        log.trace("{} life has been increased by {} points, now it is {} points",
                this.getClass().getSimpleName(),
                initialHealthPoints,
                getHealth()
        );
    }
}
