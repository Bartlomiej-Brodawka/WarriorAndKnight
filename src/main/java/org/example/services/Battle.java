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
        log.debug("Duel between {} and {} begins!",
                warrior1.getClass().getSimpleName(),
                warrior2.getClass().getSimpleName());
        log.debug("{} stats: Health {}, Attack {}",
                warrior1.getClass().getSimpleName(),
                warrior1.getHealth(),
                warrior1.getAttack());
        log.debug("{} stats: Health {}, Attack {}",
                warrior2.getClass().getSimpleName(),
                warrior2.getHealth(),
                warrior2.getAttack());

        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }

        log.debug("Duel is over! {} win with stats: Health {}",
                (warrior1.isAlive() ? warrior1 : warrior2).getClass().getSimpleName(),
                (warrior1.isAlive() ? warrior1 : warrior2).getHealth());

        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        log.debug("Battle between armies has began");
        var it1 = army1.firstAlive();
        var it2 = army2.firstAlive();

        while(it1.hasNext() && it2.hasNext()) {
            if(fight(it1.next(), it2.next())) {
                army2.moveUnits();
                army2.lineup();
            } else {
                army1.moveUnits();
                army1.lineup();
            }
        }

        log.debug("{} army won the battle.", it1.hasNext() ? "First" : "Second");

        return  it1.hasNext();
    }

    public static boolean straightFight(Army army1, Army army2) {
        log.debug("Straight fight battle between armies has began");

        while (!army1.isEmpty() && !army2.isEmpty()) {
            int size = Math.min(army1.getSize(), army2.getSize());
            for(int i = 0; i<size; i++) {
                if(army1.get(i) != null && army2.get(i)!=null) {
                    fight(army1.get(i),army2.get(i));
                }
            }
            army1.removeDeadSoldiers();
            army2.removeDeadSoldiers();
            army1.moveUnits();
            army2.moveUnits();
        }

        log.debug("{} army won the battle.", !army1.isEmpty() ? "First" : "Second");

        return !army1.isEmpty();
    }

}
