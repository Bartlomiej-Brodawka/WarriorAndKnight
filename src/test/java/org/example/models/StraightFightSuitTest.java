package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StraightFightSuitTest {

    @ParameterizedTest
    @MethodSource("provideArmyWarriors")
    @DisplayName("Straight fight battles between different kind of armies")
    void givenArmyStraightFightArmy_thenCompareResult(Army army1, Army army2, boolean expectedResult) {
        var actualResult = Battle.straightFight(army1, army2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideArmyWarriors() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 10),
                        new Army()
                                .addUnits(Warrior::new, 6)
                                .addUnits(Lancer::new, 5),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 5)
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
                                .addUnits(Defender::new,2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new,4)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new,4),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new,1)
                                .addUnits(Warrior::new,4)
                                .addUnits(Healer::new,1)
                                .addUnits(Defender::new,2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new,4),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Defender::new, 4)
                                .addUnits(Healer::new,1)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4),
                        true
                )
        );
    }

}
