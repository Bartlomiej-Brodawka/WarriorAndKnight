package org.example.models.interfaces;

import org.example.models.*;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER, VAMPIRE, LANCER, HEALER;
    }

    static Unit newUnit(UnitType type) {
        return switch(type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Knight();
            case DEFENDER -> new Defender();
            case VAMPIRE -> new Vampire();
            case LANCER -> new Lancer();
            case HEALER -> new Healer();
            default -> throw new IllegalArgumentException();
        };
    }
}
