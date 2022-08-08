package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HealerSuitTest {

    @Test
    @DisplayName("test")
    void smokeTest() {

//        var freelancer = new Lancer();
//        var vampire = new Vampire();
//        var priest = new Healer();
//
//        assert Battle.fight(freelancer, vampire) == true;
//        assert freelancer.isAlive() == true;
//        assert freelancer.getHealth() == 14;
//        priest.heal(freelancer);
//        assert freelancer.getHealth() == 16;

        var my_army = new Army();
        my_army.addUnits(Defender::new, 1);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Vampire::new, 2);
        my_army.addUnits(Lancer::new, 2);
        my_army.addUnits(Healer::new, 1);
        my_army.addUnits(Warrior::new, 1);

        var enemy_army = new Army();
        enemy_army.addUnits(Warrior::new, 2);
        enemy_army.addUnits(Lancer::new, 4);
        enemy_army.addUnits(Healer::new, 1);
        enemy_army.addUnits(Defender::new, 2);
        enemy_army.addUnits(Vampire::new, 3);
        enemy_army.addUnits(Healer::new, 1);

        var army_3 = new Army();
        army_3.addUnits(Warrior::new, 1);
        army_3.addUnits(Lancer::new, 1);
        army_3.addUnits(Healer::new, 1);
        army_3.addUnits(Defender::new, 2);

        var army_4 = new Army();
        army_4.addUnits(Vampire::new, 3);
        army_4.addUnits(Warrior::new, 1);
        army_4.addUnits(Healer::new, 1);
        army_4.addUnits(Lancer::new, 2);

        assert Battle.fight(my_army, enemy_army) == false;
//        assert Battle.fight(army_3, army_4) == true;
    }
 }
