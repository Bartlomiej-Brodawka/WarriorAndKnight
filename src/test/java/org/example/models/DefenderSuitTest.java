package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DefenderSuitTest {

    @Test
    @DisplayName("Fight between Defender versus Rookie")
    void givenDefenderFightsRookie_thenDefenderLifeDoesNotChange() {
        var bruce = new Defender();
        var carl = new Rookie();

        Battle.fight(bruce, carl);
        assertEquals(60, bruce.getHealth());
    }

    @Test
    @DisplayName("Fight between Defender versus Warrior")
    void givenDefenderFightWarrior_thenDefenderWin() {
        var lancelot = new Defender();
        var rog = new Warrior();

        assertTrue(Battle.fight(lancelot, rog));
    }

    @Test
    @DisplayName("Battle between two armies")
    void givenWarriorsDefendersArmyFightsWarriorsArmy_thenFirstArmyWin () {
        var army1 = new Army();
        var army2 = new Army();

        army1.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 4);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Warrior::new, 4);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between two armies")
    void givenWarriorsDefendersArmyFightsWarriorsDefendersArmy_thenFirstArmyWin () {
        var army1 = new Army();
        var army2 = new Army();

        army1.addUnits(Warrior::new, 5);
        army1.addUnits(Defender::new, 20);
        army2.addUnits(Warrior::new, 21);
        army1.addUnits(Defender::new, 4);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between two armies")
    void givenWarriorsDefendersArmyFightsWarriorsDefendersArmy_thenFirstArmyWin_secondExample () {
        var army1 = new Army();
        var army2 = new Army();

        army1.addUnits(Warrior::new, 10);
        army1.addUnits(Defender::new, 5);
        army2.addUnits(Defender::new, 5);
        army1.addUnits(Warrior::new, 10);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between two armies")
    void givenWarriorsDefendersArmyFightsWarriorsDefendersArmy_thenFirstArmyLose () {
        var army1 = new Army();
        var army2 = new Army();

        army1.addUnits(Warrior::new, 2);
        army1.addUnits(Defender::new, 1);
        army2.addUnits(Warrior::new, 1);
        army1.addUnits(Defender::new, 5);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Fights between different units")
    void givenWarriorsDefendersAndKnightsFightsEachOther_thenAssertWhoWinOrWhoIsAlive() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        var carl = new Knight();
        var dave = new Warrior();
        var mark = new Warrior();
        var bob = new Defender();
        var rog = new Warrior();
        var mike = new Knight();
        var lancelot = new Defender();

        assertTrue(Battle.fight(chuck, bruce));
        assertFalse(Battle.fight(dave, carl));
        assertTrue(chuck.isAlive());
        assertFalse(bruce.isAlive());
        assertTrue(carl.isAlive());
        assertTrue(Battle.fight(lancelot, rog));
        assertFalse(dave.isAlive());
        assertFalse(Battle.fight(carl, mark));
        assertFalse(carl.isAlive());
        assertFalse(Battle.fight(bob, mike));
    }

    @Test
    @DisplayName("Battles between different armies")
    void givenArmiesFightEachOther_thenAssertWhoWinOrLose() {
        var myArmy = new Army();
        myArmy.addUnits(Defender::new, 1);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warrior::new, 2);

        var army3 = new Army();
        army3.addUnits(Warrior::new, 1);
        army3.addUnits(Defender::new, 1);

        var army4 = new Army();
        army4.addUnits(Warrior::new, 2);

        assertFalse(Battle.fight(myArmy, enemyArmy));
        assertTrue(Battle.fight(army3, army4));
    }
}
