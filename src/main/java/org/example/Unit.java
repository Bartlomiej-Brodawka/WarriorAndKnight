package org.example;

public interface Unit {

    enum UnitType {
        KNIGHT, WARRIOR, OTHER;
    }

    static Unit newUnit(UnitType type) {
        return switch(type) {
            case WARRIOR -> new Warrior();
            case KNIGHT -> new Warrior(50, 7);
            default -> throw new IllegalArgumentException();
        };
    }
}
