package org.example.models;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class  Army {

    List<Unit> troops = new ArrayList<>();

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

    public List<Unit> getTroops() {
        return troops;
    }
}
