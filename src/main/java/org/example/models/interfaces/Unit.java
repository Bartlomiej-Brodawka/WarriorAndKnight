package org.example.models.interfaces;

import org.example.models.Defender;
import org.example.models.Knight;
import org.example.models.Vampire;
import org.example.models.Warrior;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER, VAMPIRE;
    }

    static Unit newUnit(UnitType type) {
        return switch(type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Knight();
            case DEFENDER -> new Defender();
            case VAMPIRE -> new Vampire();
            default -> throw new IllegalArgumentException();
        };
    }
}
