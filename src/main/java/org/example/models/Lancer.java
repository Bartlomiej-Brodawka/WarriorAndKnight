package org.example.models;

import org.example.models.interfaces.IWarrior;

public class Lancer extends Warrior{
    public static final int ATTACK = 6;

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
            nextBehind.receiveHit(
                    new SimpleDamage(damageToSecond, this)
            );
        }
    }
}
