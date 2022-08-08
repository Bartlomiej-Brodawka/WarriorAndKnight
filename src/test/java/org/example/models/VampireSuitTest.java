package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class VampireSuitTest {

    @Test
    @DisplayName("Fight between Knight and Warrior - Knight is alive")
    void givenKnightFightsWarrior_thenKnightIsAlive() {
        var zeus = new Knight();
        var godKiller = new Warrior();
        Battle.fight(zeus, godKiller);
        assertTrue(zeus.isAlive());
    }

    @Test
    @DisplayName("Fight between two Warriors - second Warrior lose")
    void givenWarriorFightsWarrior_thenSecondWarriorWhichAttackLose() {
        var husband = new Warrior();
        var wife = new Warrior();
        Battle.fight(husband, wife);
        assertFalse(wife.isAlive());
    }

    @Test
    @DisplayName("Fight between Warrior and Knight - Knight is alive")
    void givenWarriorFightsKnight_thenKnightIsAlive() {
        var dragon = new Warrior();
        var king = new Knight();
        Battle.fight(dragon, king);
        assertTrue(king.isAlive());
    }

    @Test
    @DisplayName("Fight between Defender and Rookie - Defender Life Is Not Changed")
    void givenDefenderFightsRookie_thenDefenderLifeIsNotChanged() {
        var unit1 = new Defender();
        var unit2 = new Rookie();
        Battle.fight(unit1, unit2);
        int actualHealth = unit1.getHealth();
        assertEquals(60, actualHealth);
    }

    @ParameterizedTest
    @MethodSource("provideWarriors")
    @DisplayName("Battles between different kind of warriors")
    void givenTypeOfWarriorFightGivenTypeOfWarrior_thenCompareResultOfFight(Warrior warrior1, Warrior warrior2, boolean expectedResult) {
        var actualResult = Battle.fight(warrior1, warrior2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideWarriors() {
        return Stream.of(
                Arguments.of(new Warrior(), new Knight(), false),
                Arguments.of(new Knight(), new Warrior(), true),
                Arguments.of(new Warrior(), new Warrior(), true),
                Arguments.of(new Knight(), new Knight(), true),
                Arguments.of(new Defender(), new Knight(), false),
                Arguments.of(new Defender(), new Warrior(), true),
                Arguments.of(new Vampire(), new Defender(), false),
                Arguments.of(new Warrior(), new Vampire(), true),
                Arguments.of(new Defender(), new Rookie(), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmyWarriors")
    @DisplayName("Battles between different kind of armies")
    void givenArmyFightArmy_thenCompareResult(Army army1, Army army2, boolean expectedResult) {
        var actualResult = Battle.fight(army1, army2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideArmyWarriors() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 20),
                        new Army()
                                .addUnits(Warrior::new, 21),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 11),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 11),
                        new Army()
                                .addUnits(Warrior::new, 7),
                        true),
                Arguments.of(
                        new Army()
                            .addUnits(Warrior::new, 5)
                            .addUnits(Defender::new, 4)
                            .addUnits(Defender::new, 5),
                        new Army()
                                .addUnits(Warrior::new, 4),
                        true),
                Arguments.of(
                        new Army()
                            .addUnits(Warrior::new, 5)
                            .addUnits(Defender::new, 20),
                        new Army()
                            .addUnits(Warrior::new, 21)
                            .addUnits(Defender::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Defender::new, 4),
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 2),
                        true)
        );
    }

    @Test
    @DisplayName("Vampire with 39 points of health hits a Warrior - Vampire heals itself no more than 40")
    void givenVampireWith39PointsOfHealthHitsWarrior_thanHealsItselfNoMoreThanItsInitialHealthValue() {
        var vampire = new Vampire();
        vampire.setHealth(39);
        var warrior = new Warrior();
        vampire.hit(warrior);
        assertEquals(Vampire.INITIAL_HEALTH, vampire.getHealth());
    }

    @Test
    @DisplayName("Vampire with 37 points of health hits a Warrior with 1 point of health - Vampire heals itself to 39 points of health")
    void givenVampireWith37PointsOfHealthHitsWarriorWith1PointOfHealth_thenHealsItself() {
        var vampire = new Vampire();
        vampire.setHealth(37);
        var warrior = new Warrior();
        warrior.setHealth(1);
        vampire.hit(warrior);

        assertEquals(39, vampire.getHealth());
    }
}
