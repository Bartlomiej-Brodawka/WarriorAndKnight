package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SuitTest {

    @Test
    @DisplayName("Battle between two Warriors - first Warrior wins")
    void givenWarriorFightsWarrior_SecondWarriorIsDefeatedByFirstWarrior() {
        var chuck = new Warrior();
        var bruce = new Warrior();

        assertTrue(Battle.fight(chuck, bruce));
    }

    @Test
    @DisplayName("Battle between two Warriors - first Warrior which attack is alive")
    void givenWarriorFightsWarrior_FirstWarriorWhichAttackWins() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        Battle.fight(chuck, bruce);
        assertTrue(chuck.isAlive());
    }

    @Test
    @DisplayName("Battle between two Warriors - second Warrior which attack is dead")
    void givenWarriorFightsWarrior_SecondWarriorWhichAttackLose() {
        var chuck = new Warrior();
        var bruce = new Warrior();
        Battle.fight(chuck, bruce);
        assertFalse(bruce.isAlive());
    }

    @Test
    @DisplayName("Battle between Warrior and Knight - Knight wins")
    void givenKnightsFightsWarrior_KnightsWins() {
        var carl = new Knight();
        var dave = new Warrior();

        assertTrue(Battle.fight(carl, dave));
    }

    @Test
    @DisplayName("Battle between Warrior and Knight - Warrior lose")
    void givenWarriorFightsKnights_WarriorLose() {
        var carl = new Knight();
        var dave = new Warrior();

        assertFalse(Battle.fight(dave, carl));
    }

    @Test
    @DisplayName("Knight Hits Warrior, Warrior health is reduced by 7")
    void givenKnightHitsWarrior_WarriorHealthIsReducedByKnightAttack() {
        Knight knight = new Knight();
        Warrior warrior = new Warrior();

        knight.hit(warrior);
        int actualHealth = warrior.getHealth();

        int expectedHealth = Warrior.INITIAL_HEALTH - Knight.ATTACK;
        assertEquals(expectedHealth, actualHealth);
    }

    @Test
    @DisplayName("Warrior Hits Knights, Knights health is reduced by 5")
    void givenWarriorHitsKnights_KnightsHealthIsReducedByWarriorAttack() {
        Knight knight = new Knight();
        Warrior warrior = new Warrior();

        warrior.hit(knight);
        int actualHealth = knight.getHealth();

        int expectedHealth = Knight.INITIAL_HEALTH - Warrior.ATTACK;
        assertEquals(expectedHealth, actualHealth);
    }
}
