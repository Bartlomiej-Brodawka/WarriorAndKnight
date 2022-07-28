package org.example.services;

import org.example.models.Army;
import org.example.models.Warrior;

public class Battle {
    private Battle() {
        throw new IllegalStateException();
    }

    public static boolean fight(Warrior warrior1, Warrior warrior2) {
        while (warrior1.isAlive() && warrior2.isAlive()) {
            warrior1.hit(warrior2);
            if (warrior2.isAlive()) {
                warrior2.hit(warrior1);
            }
        }
        return warrior1.isAlive();
    }

    public static boolean fight(Army army1, Army army2) {
        int i = 0;
        int j = 0;

        while(i < army1.getTroops().size() && j < army2.getTroops().size()) {
            if(fight((Warrior) army1.getTroops().get(i), (Warrior) army2.getTroops().get(j))) {
                j++;
                if(j >= army2.getTroops().size()) return true;
            } else {
                i++;
            }
        }
        return false;
    }

}
