package org.example.models;

import org.example.models.interfaces.CanAttack;
import org.example.models.interfaces.HasDefence;
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
    public void receiveHit(CanAttack damageDealer) {
        log.trace("Defender has {} points of defense. Reduce damage to {} points.",
                getDefense(), damageDealer.getAttack()-getDefense());
        super.receiveHit(() ->
                Math.max(0, damageDealer.getAttack() - getDefense())
        );
    }
}