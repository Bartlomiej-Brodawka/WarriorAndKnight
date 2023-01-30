package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WarlordSuitTest {

    @ParameterizedTest
    @MethodSource("provideWarriors")
    @DisplayName("Battles between different kind of warriors")
    void givenTypeOfWarriorFightGivenTypeOfWarrior_thenCompareResultOfFight(Warrior warrior1, Warrior warrior2, boolean expectedResult) {
        var actualResult = Battle.fight(warrior1, warrior2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideWarriors() {
        return Stream.of(
                Arguments.of(new Defender(), new Warlord(), false),
                Arguments.of(new Warlord(), new Vampire(), true),
                Arguments.of(new Warlord(), new Knight(), true)
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmyWarriors")
    @DisplayName("Battles between different kind of armies")
    void givenArmyFightArmy_thenCompareResult(Army army1, Army army2, boolean expectedResult) {
        army1.moveUnits();
        army2.moveUnits();
        army1.lineup();
        army2.lineup();
        var actualResult = Battle.fight(army1, army2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideArmyWarriors() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Warlord::new, 1)
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Healer::new, 2),
                        new Army()
                                .addUnits(Warlord::new, 1)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 2)
                                .addUnits(Knight::new, 2),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 1)
                                .addUnits(Warlord::new,3),
                        new Army()
                                .addUnits(Warlord::new, 2)
                                .addUnits(Vampire::new, 1)
                                .addUnits(Healer::new, 5)
                                .addUnits(Knight::new, 2),
                        false

                )
        );
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - first test")
    void givenFirstArmyFightSecondArmyWithWeapons_thenAssertResultOfFirstBattle() {
        var weapon_1 = Weapon.newSword();
        var weapon_2 = Weapon.newShield();

        var myArmy = new Army()
                .addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 4);

        var enemyArmy = new Army()
                .addUnits(Warlord::new, 1)
                .addUnits(Vampire::new, 1)
                .addUnits(Rookie::new, 1)
                .addUnits(Knight::new, 1);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(0, weapon_2);

        myArmy.moveUnits();
        myArmy.lineup();
        enemyArmy.moveUnits();
        enemyArmy.lineup();

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - second test")
    void givenFirstArmyFightSecondArmyWithWeapons_thenAssertResultOfSecondBattle() {
        var weapon_1 = Weapon.newSword();
        var weapon_2 = Weapon.newShield();

        var myArmy = new Army()
                .addUnits(Warrior::new, 2)
                .addUnits(Lancer::new, 3)
                .addUnits(Defender::new, 1)
                .addUnits(Warlord::new, 1);

        var enemyArmy = new Army()
                .addUnits(Warlord::new, 5)
                .addUnits(Vampire::new, 1)
                .addUnits(Rookie::new, 1)
                .addUnits(Knight::new,1);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(0, weapon_2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();

        assertEquals(true, Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    void smokeTest() {
        var ronald = new Warlord();
        var heimdall = new Knight();

        assert Battle.fight(heimdall, ronald) == false;

        var myArmy = new Army();
        myArmy.addUnits(Warlord::new, 1);
        myArmy.addUnits(Warrior::new, 2);
        myArmy.addUnits(Lancer::new, 2);
        myArmy.addUnits(Healer::new, 2);

        var enemyArmy = new Army();
        enemyArmy.addUnits(Warlord::new, 3);
        enemyArmy.addUnits(Vampire::new, 1);
        enemyArmy.addUnits(Healer::new, 2);
        enemyArmy.addUnits(Knight::new, 2);

        myArmy.moveUnits();
        enemyArmy.moveUnits();
        myArmy.lineup();
        enemyArmy.lineup();

        assert myArmy.get(0).getClass() == Lancer.class;
        assert myArmy.get(1).getClass() == Healer.class;

        assert myArmy.get(myArmy.getSize()-1).getClass() == Warlord.class;

        assert enemyArmy.get(0).getClass() == Vampire.class;
        assert enemyArmy.get(enemyArmy.getSize()-1).getClass() == Warlord.class;
        assert enemyArmy.get(enemyArmy.getSize()-2).getClass() == Knight.class;

        assert enemyArmy.getSize() == 6;

        assert Battle.fight(myArmy, enemyArmy) == true;
    }
}
