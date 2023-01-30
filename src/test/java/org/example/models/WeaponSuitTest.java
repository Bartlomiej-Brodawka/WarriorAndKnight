package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeaponSuitTest {

    @Test
    @DisplayName("Asserting individual statistics after adding a weapon to given type of soldier")
    void givenSoldierIsEquippedWithWeapon_thenStatsAreAsserted() {
        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();

        var sword = Weapon.newSword();
        var shield = Weapon.newShield();
        var axe = Weapon.newGreatAxe();
        var katana = Weapon.newKatana();
        var wand = Weapon.newMagicWand();
        var superWeapon = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8)
                .build();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertAll(
                () -> assertEquals(125, ogre.getHealth()),
                () -> assertEquals(17, lancelot.getAttack()),
                () -> assertEquals(4, richard.getDefense()),
                () -> assertEquals(200, eric.getVampirism()),
                () -> assertEquals(15, freelancer.getHealth()),
                () -> assertEquals(5, priest.getHealPower())
        );
    }

    @Test
    @DisplayName("Duels between different kind of warriors with weapons")
    void givenTypeOfWarriorWithWeaponFightGivenTypeOfWarriorWithWeapon_thenCompareResultOfFight() {

        var sword = Weapon.newSword();
        var shield = Weapon.newShield();
        var axe = Weapon.newGreatAxe();
        var katana = Weapon.newKatana();
        var wand = Weapon.newMagicWand();
        var superWeapon = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8)
                .build();

        var ogre = new Warrior();
        var lancelot = new Knight();
        var richard = new Defender();
        var eric = new Vampire();
        var freelancer = new Lancer();
        var priest = new Healer();

        ogre.equipWeapon(sword);
        ogre.equipWeapon(shield);
        ogre.equipWeapon(superWeapon);
        lancelot.equipWeapon(superWeapon);
        richard.equipWeapon(shield);
        eric.equipWeapon(superWeapon);
        freelancer.equipWeapon(axe);
        freelancer.equipWeapon(katana);
        priest.equipWeapon(wand);
        priest.equipWeapon(shield);

        assertAll(
                () -> assertEquals(false, Battle.fight(ogre, eric)),
                () -> assertEquals(false, Battle.fight(priest, richard)),
                () -> assertEquals(true, Battle.fight(lancelot, freelancer))
        );
    }

    @Test
    @DisplayName("Warrior with Weapon fight Vampire with Sword")
    void givenWarriorWithWeaponFightVampireWithSword_thenAssertResult() {
        var unit_1 = new Warrior();
        var unit_2 = new Vampire();
        var weapon_1 = Weapon.builder()
                .health(-10)
                .attack(5)
                .defense(0)
                .vampirism(40)
                .healPower(0)
                .build();
        var weapon_2 = Weapon.newSword();
        unit_1.equipWeapon(weapon_1);
        unit_2.equipWeapon(weapon_2);
        assertEquals(true, Battle.fight(unit_1, unit_2));
    }

    @Test
    @DisplayName("Defender with Shield fights Lancer with GreatAxe")
    void givenDefenderWithShieldFightsLancerWithGreatAxe_theAssertResult() {
        var unit_1 = new Defender();
        var unit_2 = new Lancer();
        var weapon_1 = Weapon.newShield();
        var weapon_2 = Weapon.newGreatAxe();
        unit_1.equipWeapon(weapon_1);
        unit_2.equipWeapon(weapon_2);
        assertEquals(false, Battle.fight(unit_1, unit_2));
    }

    @Test
    @DisplayName("Healer with Magic Wand fight Knight with Katana")
    void givenHealerWithMagicWandFightKnightWithKatana_theAssertResult() {
        var unit_1 = new Healer();
        var unit_2 = new Knight();
        var weapon_1 = Weapon.newMagicWand();
        var weapon_2 = Weapon.newKatana();
        unit_1.equipWeapon(weapon_1);
        unit_2.equipWeapon(weapon_2);
        assertEquals(false, Battle.fight(unit_1, unit_2));
    }

    @Test
    @DisplayName("Defender with Shield and Magic Wand fight Vampire with Shield and Katana")
    void givenDefenderWithShieldMagicWandFightsVampireWithShieldKatana_thenAssertResult() {
        var unit_1 = new Defender();
        var unit_2 = new Vampire();
        var weapon_1 = Weapon.newShield();
        var weapon_2 = Weapon.newMagicWand();
        var weapon_3 = Weapon.newShield();
        var weapon_4 = Weapon.newKatana();
        unit_1.equipWeapon(weapon_1);
        unit_1.equipWeapon(weapon_2);
        unit_2.equipWeapon(weapon_3);
        unit_2.equipWeapon(weapon_4);
        assertEquals(false, Battle.fight(unit_1, unit_2));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - first test")
    void givenKnightLancerArmyFightVampireHealerArmyWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newMagicWand();
        var weapon_2 = Weapon.newGreatAxe();

        var myArmy = new Army()
                .addUnits(Knight::new, 1)
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
    void givenDefenderWarriorArmyFightKnightHealerWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newSword();
        var weapon_2 = Weapon.newGreatAxe();

        var myArmy = new Army()
                .addUnits(Defender::new, 1)
                .addUnits(Warrior::new, 1)
                .lineup();

        var enemyArmy = new Army()
                .addUnits(Knight::new, 1)
                .addUnits(Healer::new, 1)
                .lineup();

        myArmy.equipWarriorAtPosition(0, weapon_2);
        myArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_1);

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - third test")
    void givenDefendersArmyFightKnightVampireArmyWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newKatana();
        var weapon_2 = Weapon.newShield();

        var myArmy = new Army()
                .addUnits(Defender::new, 2)
                .lineup();

        var enemyArmy = new Army()
                .addUnits(Knight::new, 1)
                .addUnits(Vampire::new, 1)
                .lineup();

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_1);

        assertEquals(false, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - fourth test")
    void givenKnightsArmyFightWarriorDefenderArmyWithWeapons_thenAssertBattleResult() {
        var weapon_1 = Weapon.builder()
                .health(-20)
                .attack(6)
                .defense(1)
                .vampirism(40)
                .healPower(-2)
                .build();
        var weapon_2 = Weapon.builder()
                .health(20)
                .attack(-2)
                .defense(2)
                .vampirism(-55)
                .healPower(3)
                .build();
        var myArmy = new Army()
                .addUnits(Knight::new, 3)
                .lineup();
        var enemyArmy = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 2)
                .lineup();
        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        myArmy.equipWarriorAtPosition(2, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(2, weapon_2);

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - fifth test")
    void givenVampiresArmyStraightFightWarriorDefenderArmyWithWeapons_thenAssertResultOfBattle() {
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
                .addUnits(Defender::new, 2);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        myArmy.equipWarriorAtPosition(2, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(2, weapon_2);

        assertEquals(false, Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - fifth test")
    void givenVampireRookieArmyStraightFightWarriorDefenderArmyWithWeapons_thenAssertBattleResult() {
        var weapon_1 = Weapon.newKatana();
        var weapon_2 = Weapon.newShield();

        var myArmy = new Army()
                .addUnits(Vampire::new, 2)
                .addUnits(Rookie::new, 2);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 2);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        myArmy.equipWarriorAtPosition(2, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);
        enemyArmy.equipWarriorAtPosition(2, weapon_2);

        assertEquals(true, Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - sixth test")
    void givenVampiresArmyStraightFightWarriorDefenderArmyWIthWeapons_thenAssertBattleResult() {
        var weapon_1 = Weapon.newSword();
        var weapon_2 = Weapon.newGreatAxe();

        var myArmy = new Army()
                .addUnits(Vampire::new, 3);

        var enemyArmy = new Army()
                .addUnits(Warrior::new, 1)
                .addUnits(Defender::new, 1);

        myArmy.equipWarriorAtPosition(0, weapon_2);
        myArmy.equipWarriorAtPosition(1, weapon_2);
        myArmy.equipWarriorAtPosition(2, weapon_2);
        enemyArmy.equipWarriorAtPosition(0, weapon_1);
        enemyArmy.equipWarriorAtPosition(1, weapon_1);

        assertEquals(true, Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - seventh test")
    void givenRookiesArmyStraightFightDefenderHealerArmyWithWeapons_thenAssertResultOfBattle() {
        var weapon_1 = Weapon.newKatana();
        var weapon_2 = Weapon.newMagicWand();

        var myArmy = new Army()
                .addUnits(Rookie::new, 3);

        var enemyArmy = new Army()
                .addUnits(Defender::new, 1)
                .addUnits(Healer::new, 1);

        myArmy.equipWarriorAtPosition(0, weapon_1);
        myArmy.equipWarriorAtPosition(1, weapon_1);
        myArmy.equipWarriorAtPosition(2, weapon_1);
        enemyArmy.equipWarriorAtPosition(0, weapon_2);
        enemyArmy.equipWarriorAtPosition(1, weapon_2);

        assertEquals(false, Battle.straightFight(myArmy, enemyArmy));
    }

    @Test
    @DisplayName("Battle between two armies with different kind of weapons - eighth test")
    void givenKnightLancerArmyWithWeaponsFightVampireHealerArmyWithWeapons_thenAssertResultOfBattle() {

        var axe = Weapon.newGreatAxe();
        var katana = Weapon.newKatana();
        var wand = Weapon.newMagicWand();
        var superWeapon = Weapon.builder()
                .health(50)
                .attack(10)
                .defense(5)
                .vampirism(150)
                .healPower(8)
                .build();

        var myArmy = new Army()
                .addUnits(Knight::new, 1)
                .addUnits(Lancer::new, 1)
                .lineup();

        var enemyArmy = new Army()
                .addUnits(Vampire::new, 1)
                .addUnits(Healer::new, 1)
                .lineup();

        myArmy.equipWarriorAtPosition(0, axe);
        myArmy.equipWarriorAtPosition(1, superWeapon);

        enemyArmy.equipWarriorAtPosition(0, katana);
        enemyArmy.equipWarriorAtPosition(1, wand);

        assertEquals(true, Battle.fight(myArmy, enemyArmy));
    }
}
