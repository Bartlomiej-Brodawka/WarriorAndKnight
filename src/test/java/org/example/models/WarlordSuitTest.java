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
