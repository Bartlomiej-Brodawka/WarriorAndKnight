package org.example;

public class Battle {
    private Battle() {}

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

        while(i < army1.troops.size() && j < army2.troops.size()) {
            if(fight((Warrior) army1.troops.get(i), (Warrior) army2.troops.get(j))) {
                ((Warrior) army1.troops.get(i)).setHealth(((Warrior) army1.troops.get(i)).getHealth());
                j++;
                if(j >= army2.troops.size()) return true;
            } else {
                ((Warrior) army2.troops.get(j)).setHealth(((Warrior) army2.troops.get(j)).getHealth());
                i++;
            }
        }
        return false;
    }

}
