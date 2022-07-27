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
        return false;
    }
}
