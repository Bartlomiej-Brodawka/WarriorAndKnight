package org.example.models;

import org.example.models.interfaces.HasVampirism;
import org.example.models.interfaces.IWarrior;

public class Vampire extends Warrior implements HasVampirism {
    public static final int INITIAL_HEALTH = 40;
    public static final int ATTACK = 4;
    public static final int VAMPIRISM = 50;
    private int vampirism;

    public Vampire() {
        super(INITIAL_HEALTH, ATTACK);
        this.vampirism = VAMPIRISM;
    }

    @Override
    public int getVampirism() {
        return vampirism;
    }

    @Override
    public void hit(IWarrior opponent) {
        super.hit(opponent);
        if(opponent instanceof Defender defender) {
            setHealth(getHealth() + ((getAttack() - defender.getDefense()) * vampirism/100));
        } else {
            setHealth(getHealth() + (getAttack() * vampirism / 100));
        }
        if(getHealth() > INITIAL_HEALTH) {
            setHealth(INITIAL_HEALTH);
        }
    }
}
