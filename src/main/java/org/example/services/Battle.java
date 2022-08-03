package org.example.services;

import org.example.models.Army;
import org.example.models.interfaces.IWarrior;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Battle {

    private static final Logger log = LoggerFactory.getLogger(Battle.class);

    private Battle() {
        throw new IllegalStateException();
    }

    public static boolean fight(IWarrior warrior1, IWarrior warrior2) {
        log.debug("Duel between {} and {} begins!", warrior1.getClass().getSimpleName(), warrior2.getClass().getSimpleName());
        log.debug("{} stats: Health {}, Attack {}", warrior1.getClass().getSimpleName(), warrior1.getHealth(), warrior1.getAttack());
        log.debug("{} stats: Health {}, Attack {}", warrior2.getClass().getSimpleName(), warrior2.getHealth(), warrior2.getAttack());

        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        if(warrior1.isAlive()) {
            log.debug("Duel is over! {} win with stats: Health {}", warrior1.getClass().getSimpleName(), warrior1.getHealth());
        } else {
            log.debug("Duel is over! {} win with stats: Health {}", warrior2.getClass().getSimpleName(), warrior2.getHealth());
        }

        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        log.debug("Battle between armies has began");
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();


        while(it1.hasNext() && it2.hasNext()) {
            fight(it1.next(), it2.next());
        }

        if(it1.hasNext()) {
            log.debug("First army win the battle.");
        } else {
            log.debug("Second army win the battle.");
        }

        return  it1.hasNext();
    }

}
