package org.example.models;

import org.example.models.interfaces.IWarrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Lancer extends Warrior{
    public static final int ATTACK = 6;

    private static final Logger log = LoggerFactory.getLogger(Lancer.class);

    public Lancer() {
        super(INITIAL_HEALTH, ATTACK);
    }

    @Override
    public void hit(IWarrior opponent) {
        int healthBefore = opponent.getHealth();
        super.hit(opponent);
        int damageDealtToTheFirst = healthBefore - opponent.getHealth();

        IWarrior nextBehind = opponent.getWarriorBehind();

        if(nextBehind != null) {
            final int piercePower = 50;
            int damageToSecond = damageDealtToTheFirst*piercePower/100;
            log.trace("Lancer hits {} behind {} with {} points of attack.",
                    nextBehind.getClass().getSimpleName(),
                    opponent.getClass().getSimpleName(),
                    damageToSecond);
            nextBehind.receiveHit(
                    new SimpleDamage(damageToSecond, this)
            );

        }
    }
}
