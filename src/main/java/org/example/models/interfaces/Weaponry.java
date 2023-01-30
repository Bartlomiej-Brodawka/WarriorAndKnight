package org.example.models.interfaces;

import java.util.List;

public interface Weaponry {
    void equipWeapon(IWeapon weapon);
    List<IWeapon> getWeapons();
    void cleanWeaponry();
    boolean isWeaponryEmpty();
    void looseWeaponBonuses(IWeapon weapon);
}
