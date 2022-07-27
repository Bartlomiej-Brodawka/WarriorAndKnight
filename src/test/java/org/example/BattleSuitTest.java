package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BattleSuitTest {

    @Test
    @DisplayName("Fight between Warrior and Knight - Warrior lose")
    void givenWarriorFightsKnights_WarriorLose() {
        var carl = new Warrior();
        var jim = new Knight();
        assertFalse(Battle.fight(carl, jim));
    }

    @Test
    @DisplayName("Fight between Knight and Warrior - Knight win")
    void givenKnightsFightsWarrior_KnightWin() {
        var ramon = new Knight();
        var slevin = new Warrior();
        assertTrue(Battle.fight(ramon, slevin));
    }

    @Test
    @DisplayName("Fight between two Warriors - first Warrior is alive")
    void givenWarriorFightsWarrior_FirstWarriorWhichAttackWins() {
        var bob = new Warrior();
        var mars = new Warrior();
        Battle.fight(bob, mars);
        assertTrue(bob.isAlive());
    }

    @Test
    @DisplayName("Fight between Knight and Warrior - Knight is alive")
    void givenKnightFightsWarrior_KnightIsAlive() {
        var zeus = new Knight();
        var godKiller = new Warrior();
        Battle.fight(zeus, godKiller);
        assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("Fight between two Warriors - second Warrior lose")
    void givenWarriorFightsWarrior_SecondWarriorWhichAttackLose() {
        var husband = new Warrior();
        var wife = new Warrior();
        Battle.fight(husband, wife);
        assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("Fight between Warrior and Knight - Knight is alive")
    void givenWarriorFightsKnight_KnightIsAlive() {
        var dragon = new Warrior();
        var king = new Knight();
        Battle.fight(dragon, king);
        assertTrue(king.isAlive());
    }

    @Test
    @DisplayName("Battle between army of 1 Warrior versus army of 2 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_SmallerArmyLose() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 1);
        army2.addUnits(() -> new Warrior(), 2);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 2 Warriors versus army of 3 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_SmallerArmyLose_SecondExample() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 2);
        army2.addUnits(() -> new Warrior(), 3);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 5 Warriors versus army of 7 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_SmallerArmyLose_ThirdExample() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 5);
        army2.addUnits(() -> new Warrior(), 7);

        assertFalse(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 20 Warriors versus army of 21 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_FirstArmyWin() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 20);
        army2.addUnits(() -> new Warrior(), 21);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 10 Warriors versus army of 11 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_FirstArmyWin_SecondExample() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 10);
        army2.addUnits(() -> new Warrior(), 11);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 11 Warriors versus army of 7 Warriors")
    void givenWarriorsArmyFightWarriorsArmy_FirstArmyWin_ThirdExample() {
        var army1 = new Army();
        var army2 = new Army();
        army1.addUnits(() -> new Warrior(), 11);
        army2.addUnits(() -> new Warrior(), 7);

        assertTrue(Battle.fight(army1, army2));
    }

    @Test
    @DisplayName("Battle between army of 3 Knights and army of 3 Warriors")
    void givenKnightsArmyFightWarriorsArmy_ArmyOfKnightsWin() {
        var myArmy = new Army();
        var enemyArmy = new Army();
        myArmy.addUnits(() -> new Knight(), 3);
        enemyArmy.addUnits(() -> new Warrior(), 3);

        assertTrue(Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between army of 3 Warriors and Army of 3 Knights")
    void givenWarriorsArmyFightKnightsArmy_ArmyOfWarriorsLose() {
        var enemyArmy = new Army();
        var myArmy = new Army();
        myArmy.addUnits(() -> new Knight(), 3);
        enemyArmy.addUnits(() -> new Warrior(), 3);

        assertFalse(Battle.fight(enemyArmy, myArmy));
    }

    @Test
    @DisplayName("Battle between mixed army and army of Warriors")
    void givenWarriorsAndKnightsArmyFightWarriorsArmy_MixedArmyLose() {
        var army3 = new Army();
        army3.addUnits(() -> new Warrior(), 20);
        army3.addUnits(() -> new Knight(), 5);

        var army4 = new Army();
        army4.addUnits(() -> new Warrior(), 30);


        assertFalse(Battle.fight(army3, army4));
    }
}
