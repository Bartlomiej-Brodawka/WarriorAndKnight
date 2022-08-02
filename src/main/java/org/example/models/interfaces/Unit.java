package org.example.models.interfaces;

import org.example.models.Defender;
import org.example.models.Knight;
import org.example.models.Warrior;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER;
    }

    static Unit newUnit(UnitType type) {
        return switch(type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Knight();
            case DEFENDER -> new Defender();
            default -> throw new IllegalArgumentException();
        };
    }
}
