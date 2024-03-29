package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class HealerSuitTest {

    @ParameterizedTest
    @MethodSource("provideWarriors")
    @DisplayName("Battles between different kind of warriors")
    void givenTypeOfWarriorFightGivenTypeOfWarrior_thenCompareResultOfFight(Warrior warrior1, Warrior warrior2, boolean expectedResult) {
        var actualResult = Battle.fight(warrior1, warrior2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideWarriors() {
        return Stream.of(
                Arguments.of(new Lancer(), new Vampire(), true),
                Arguments.of(new Lancer(), new Warrior(), true),
                Arguments.of(new Lancer(), new Knight(), false),
                Arguments.of(new Lancer(), new Defender(), true),
                Arguments.of(new Lancer(), new Lancer(), true),
                Arguments.of(new Defender(), new Lancer(), false),
                Arguments.of(new Knight(), new Lancer(), true),
                Arguments.of(new Warrior(), new Lancer(), false),
                Arguments.of(new Lancer(), new Vampire(), true),
                Arguments.of(new Warrior(), new Healer(), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmyWarriors")
    @DisplayName("Battles between different kind of armies")
    void givenArmyFightArmy_thenCompareResult(Army army1, Army army2, boolean expectedResult) {
        army1.lineup();
        army2.lineup();
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
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Lancer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Lancer::new, 2),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Knight::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 3)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Lancer::new, 2),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 1)
                                .addUnits(Lancer::new, 1)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 1)
                                .addUnits(Healer::new, 1)
                                .addUnits(Lancer::new, 2),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 4),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 1)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new,4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new,4),
                        false
                )
        );
    }

    @Test
    @DisplayName("Duel between Lancer and Vampire - after fight Healer heal Lancer")
    void givenLancerFightHealer_thenHealerHealsLancer() {

        var freelancer = new Lancer();
        var vampire = new Vampire();
        var priest = new Healer();

        assertEquals(true, Battle.fight(freelancer, vampire));
        assertTrue(freelancer.isAlive());
        var actualHealthOfLancer = freelancer.getHealth();
        priest.heal(freelancer);
        var lancerHealthAfterHealing = freelancer.getHealth();
        assertEquals(actualHealthOfLancer+priest.getHealPower(), lancerHealthAfterHealing);
    }
 }
