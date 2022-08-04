package org.example.models;

import org.example.models.interfaces.HasDefence;
import org.example.models.interfaces.IDamage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Defender extends Warrior implements HasDefence {
    public static final int INITIAL_HEALTH = 60;
    public static final int ATTACK = 3;
    public static final int DEFENSE = 2;
    private int defense;

    private static final Logger log = LoggerFactory.getLogger(Defender.class);

    public Defender() {
        super(INITIAL_HEALTH, ATTACK);
        this.defense = DEFENSE;
    }

    @Override
    public int getDefense() {
        return defense;
    }

    @Override
    public void receiveHit(IDamage damage) {
        log.trace("Defender has {} points of defense. Reduce damage to {} points.",
                getDefense(), damage.hitPoints()-getDefense());
        setHealth(getHealth() -(Math.max(0, (damage.hitPoints() - getDefense()))));
        log.trace("{} receive {} points of damage. {} points of life left.", this.getClass().getSimpleName(), damage.hitPoints()-getDefense(), this.getHealth());
    }
}