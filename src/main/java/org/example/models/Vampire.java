package org.example.models;

import org.example.models.interfaces.HasVampirism;
import org.example.models.interfaces.IWarrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Vampire extends Warrior implements HasVampirism {
    public static final int INITIAL_HEALTH = 40;
    public static final int ATTACK = 4;
    public static final int VAMPIRISM = 50;
    private int vampirism;

    private static final Logger log = LoggerFactory.getLogger(Vampire.class);

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
        this.vampirism = VAMPIRISM;
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    @Override
    public int getInitialHealth() {
        return INITIAL_HEALTH;
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
        if(getHealth() > INITIAL_HEALTH) {
            setHealth(INITIAL_HEALTH);
        }
    }
}
