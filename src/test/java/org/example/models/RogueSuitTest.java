package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

class RogueSuitTest {

    @Test
    @DisplayName("Rogue fights Warrior with weapons and steals weapons")
    void givenRogueFightsWarriorWithWeapons_thenStealsWeapons() {
        var ogre = new Warrior();
        var rogue = new Rogue();

        var sword = Weapon.newSword();
        var shield = Weapon.newShield();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        assertEquals(true, Battle.fight(rogue, ogre));
        assertEquals(rogue.getWeapons().get(1), shield);
    }

    @Test
    @DisplayName("Defender with weapons fights Rogue who steals Defender weapons")
    void givenDefenderWithWeaponsFightsRogueWithWeapons_thenRogueStealsDefenderWeapons() {
        var defender = new Defender();
        var rogue = new Rogue();

        var katana = Weapon.newKatana();
        var magicWand = Weapon.newMagicWand();
        var shield = Weapon.newShield();

        defender.equipWeapon(katana);
        defender.equipWeapon(magicWand);
        rogue.equipWeapon(shield);
        assertEquals(false, Battle.fight(defender, rogue));
        assertEquals(rogue.getWeapons().get(2), magicWand);
    }

    @Test
    @DisplayName("Defender with weapons fights Rogue who steals Defender weapons")
    void givenRogueWithWeaponsFightsRogueWithWeapons_thenRogueStealRogueWeapons() {
        var rogue1 = new Rogue();
        var rogue2 = new Rogue();

        var katana = Weapon.newKatana();
        var magicWand = Weapon.newMagicWand();
        var shield = Weapon.newShield();

        rogue1.equipWeapon(katana);
        rogue1.equipWeapon(magicWand);
        rogue2.equipWeapon(shield);
        assertEquals(true, Battle.fight(rogue1, rogue2));
        assertEquals(rogue1.getWeapons().get(2), magicWand);
    }

    @Test
    @DisplayName("Vampire with Weapons fights Rogue with weapons who steals Vampire weapons")
    void givenRogueWithWeaponsStealFromVampireWithWeapons_thenAssertResults() {
        var vampire = new Vampire();
        var rogue = new Rogue();

        var katana = Weapon.newKatana();
        var greatAxe = Weapon.newGreatAxe();

        vampire.equipWeapon(katana);
        vampire.equipWeapon(greatAxe);
        rogue.equipWeapon(katana);
        rogue.equipWeapon(greatAxe);

        rogue.stealWeapons(vampire);

        assertEquals(false, rogue.isAlive());
    }

    @Test
    @DisplayName("Defender with Weapons fight Rogue with Weapons")
    void givenDefenderWithWeaponsFightsRogueWithWeapons_thenAssertResult() {
        var unit_1 = new Defender();
        var unit_2 = new Rogue();
        var weapon_1 = Weapon.newShield();
        var weapon_2 = Weapon.newSword();
        var weapon_3 = Weapon.newKatana();
        var weapon_4 = Weapon.newGreatAxe();
        var weapon_5 = Weapon.builder()
                .health(10)
                .attack(-16)
                .defense(0)
                .vampirism(40)
                .healPower(0)
                .name("Custom weapon")
                .build();
        unit_1.equipWeapon(weapon_1);
        unit_1.equipWeapon(weapon_2);
        unit_2.equipWeapon(weapon_3);
        unit_2.equipWeapon(weapon_4);
        unit_2.equipWeapon(weapon_5);
        assertEquals(true, Battle.fight(unit_1, unit_2));
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
                Arguments.of(new Rogue(), new Vampire(), false),
                Arguments.of(new Rogue(), new Warrior(), false),
                Arguments.of(new Lancer(), new Rogue(), true),
                Arguments.of(new Rogue(), new Defender(), false),
                Arguments.of(new Rogue(), new Rogue(), true),
                Arguments.of(new Defender(), new Rogue(), true),
                Arguments.of(new Knight(), new Rogue(), true),
                Arguments.of(new Rogue(), new Lancer(), false),
                Arguments.of(new Vampire(), new Rogue(), true),
                Arguments.of(new Warrior(), new Rogue(), true)
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
                                .addUnits(Rogue::new, 1),
                        new Army()
                                .addUnits(Warrior::new, 2),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 2),
                        new Army()
                                .addUnits(Rogue::new, 3),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5),
                        new Army()
                                .addUnits(Rogue::new, 7),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Rogue::new, 20),
                        new Army()
                                .addUnits(Rogue::new, 21),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Warrior::new, 5)
                                .addUnits(Defender::new, 4)
                                .addUnits(Defender::new, 5),
                        new Army()
                                .addUnits(Rogue::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Rogue::new, 5)
                                .addUnits(Defender::new, 20),
                        new Army()
                                .addUnits(Rogue::new, 21)
                                .addUnits(Defender::new, 4),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Rogue::new, 1),
                        new Army()
                                .addUnits(Rogue::new, 2)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3),
                        false),
                Arguments.of(
                        new Army()
                                .addUnits(Rogue::new, 1)
                                .addUnits(Defender::new, 4),
                        new Army()
                                .addUnits(Vampire::new, 3)
                                .addUnits(Rogue::new, 2),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Rogue::new, 2)
                                .addUnits(Vampire::new, 2)
                                .addUnits(Lancer::new, 4)
                                .addUnits(Warrior::new, 1),
                        new Army()
                                .addUnits(Rogue::new, 2)
                                .addUnits(Lancer::new, 2)
                                .addUnits(Defender::new, 2)
                                .addUnits(Vampire::new, 3),
                        true),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Healer::new, 1)
                                .addUnits(Rogue::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Defender::new, 2),
                        new Army()
                                .addUnits(Rogue::new, 4)
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
                                .addUnits(Rogue::new, 1)
                                .addUnits(Knight::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Rogue::new,4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new,4),
                        false
                )
        );
    }

    @ParameterizedTest
    @MethodSource("provideArmyWarriorsForStraightFightBattles")
    @DisplayName("Straight fight battles between different kind of armies")
    void givenArmyStraightFightArmy_thenCompareResult(Army army1, Army army2, boolean expectedResult) {
        var actualResult = Battle.straightFight(army1, army2);
        assertEquals(expectedResult, actualResult);
    }

    private static Stream<Arguments> provideArmyWarriorsForStraightFightBattles() {
        return Stream.of(
                Arguments.of(
                        new Army()
                                .addUnits(Rogue::new, 10),
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
                                .addUnits(Rogue::new, 2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Rogue::new, 4)
                                .addUnits(Vampire::new, 6)
                                .addUnits(Lancer::new, 5),
                        false
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Warrior::new, 4)
                                .addUnits(Rogue::new,2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Rogue::new,4)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new,4),
                        true
                ),
                Arguments.of(
                        new Army()
                                .addUnits(Lancer::new, 7)
                                .addUnits(Vampire::new, 3)
                                .addUnits(Rogue::new,1)
                                .addUnits(Warrior::new,4)
                                .addUnits(Healer::new,1)
                                .addUnits(Rogue::new,2),
                        new Army()
                                .addUnits(Warrior::new, 4)
                                .addUnits(Rogue::new, 4)
                                .addUnits(Healer::new, 1)
                                .addUnits(Vampire::new,6)
                                .addUnits(Lancer::new,4),
                        true
                )
        );
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - first test")
    void givenRogueLancerArmyFightVampireHealerArmyWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newMagicWand();
        var weapon_2 = Weapon.newGreatAxe();

        var myArmy = new Army()
                .addUnits(Rogue::new, 1)
                .addUnits(Lancer::new, 1)
                .lineup();

        var enemyArmy = new Army()
                .addUnits(Vampire::new, 1)
                .addUnits(Healer::new, 1)
                .lineup();

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - second test")
    void givenDefenderWarriorArmyFightRogueHealerWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newSword();
        var weapon_2 = Weapon.newGreatAxe();

        var myArmy = new Army()
                .addUnits(Defender::new, 1)
                .addUnits(Warrior::new, 1)
                .lineup();

        var enemyArmy = new Army()
                .addUnits(Rogue::new, 1)
                .addUnits(Healer::new, 1)
                .lineup();

        myArmy.equipWarriorAtPosition(0, weapon_2);
        myArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_1);

        assertEquals(false, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - third test")
    void givenVampiresArmyStraightFightWarriorRogueArmyWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.builder()
                .health(-20)
                .attack(1)
                .defense(1)
                .vampirism(40)
                .healPower(-2)
                .build();
        var weapon_2 = Weapon.builder()
                .health(20)
                .attack(2)
                .defense(2)
                .vampirism(-55)
                .healPower(3)
                .build();
        var myArmy = new Army()
                .addUnits(Vampire::new, 3);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Rogue::new, 2);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        myArmy.equipWarriorAtPosition(2, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(2, weapon_2);

        assertEquals(false, Battle.straightFight(myArmy, enemyArmy));
    }
}
