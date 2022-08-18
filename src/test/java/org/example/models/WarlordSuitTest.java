package org.example.models;

import org.example.services.Battle;
import org.junit.jupiter.api.Test;

class WarlordSuitTest {

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

// you can provide any other interface for testing the order
        assert myArmy.get(0).getClass() == Lancer.class;
        assert myArmy.get(1).getClass() == Healer.class;
// negative index means from the last position, so -1 == last
        assert myArmy.get(myArmy.getSize()-1).getClass() == Warlord.class;

        assert enemyArmy.get(0).getClass() == Vampire.class;
        assert enemyArmy.get(enemyArmy.getSize()-1).getClass() == Warlord.class;
        assert enemyArmy.get(enemyArmy.getSize()-2).getClass() == Knight.class;

//6, not 8, because only 1 Warlord per army could be
        assert enemyArmy.getSize() == 6;

//        assert Battle.fight(myArmy, enemyArmy) == true;
    }
}
