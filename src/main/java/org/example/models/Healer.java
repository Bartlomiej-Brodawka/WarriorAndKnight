package org.example.models;

import org.example.models.interfaces.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Healer extends Warrior implements CanHeal {

    private static final int ATTACK = 0;
    private static final int INITIAL_HEALTH = 60;
    private static final int HEAL_POWER = 2;

    private int healPower;

    private static final Logger log = LoggerFactory.getLogger(Healer.class);

    public Healer() {
        super(INITIAL_HEALTH, ATTACK);
        this.healPower = HEAL_POWER;
    }

    @Override
    public void hit(IWarrior opponent) {}

    @Override
    public void processCommand(ICommand command, IWarrior sender) {
        if (command instanceof HealCommand && sender instanceof Healer && sender.getWarriorInFrontOf()!=null) {
            heal(sender.getWarriorInFrontOf());
            log.trace(" {} health {}, initial {}", sender.getWarriorInFrontOf().getClass().getSimpleName(), sender.getWarriorInFrontOf().getHealth(), sender.getWarriorInFrontOf().getInitialHealth());
        }
        super.processCommand(command, sender);
    }

    public int getHealPower() {
        return healPower;
    }
}
