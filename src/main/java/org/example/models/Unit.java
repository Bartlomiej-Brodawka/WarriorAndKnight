package org.example.models;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, DEFENDER;
    }

    static Unit newUnit(UnitType type) {
        return switch(type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Warrior(50, 7);
            case DEFENDER -> new Warrior(60, 3, 2);
            default -> throw new IllegalArgumentException();
        };
    }
}
