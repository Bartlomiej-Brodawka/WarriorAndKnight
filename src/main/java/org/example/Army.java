package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class  Army {

    List<Unit> troops = new ArrayList<Unit>();

    void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(Unit.newUnit(type));
        }
    }

    void addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(prototype.clone());
        }
    }

    void addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(factory.get());
        }
    }

    public static  void main(String[] args) {
        var army = new Army();
        army.addUnits(() -> new Warrior(), 10);
        army.addUnits(Warrior::new, 10);
        Warrior prototype = new Warrior();
        army.addUnits(prototype::clone, 10);
        army.addUnits(() -> prototype.clone(), 10);
        army.addUnits(() ->
                (Warrior) Unit.newUnit(Unit.UnitType.WARRIOR), 10);

        army.addUnits(new Knight(), 3);
    }
}
