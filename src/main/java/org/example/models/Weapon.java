package org.example.models;

import lombok.Builder;
import lombok.Getter;
import lombok.ToString;
import org.example.models.interfaces.IWeapon;

@Builder
@Getter
@ToString
public class Weapon implements IWeapon {

    private static final String SWORD = "Sword";
    private static final String GREATAXE = "Great Axe";
    private static final String SHIELD = "Shield";
    private static final String KATANA = "Katana";
    private static final String MAGICWAND = "Magic Wand";

    private int health;
    private int attack;
    private int defense;
    private int vampirism;
    private int healPower;
    private String name;

    private static final WeaponBuilder Sword =
            Weapon.builder()
                    .health(5)
                    .attack(2)
                    .name(SWORD);
    private static final Weapon.WeaponBuilder Shield =
            Weapon.builder()
                    .health(20)
                    .attack(-1)
                    .defense(2)
                    .name(SHIELD);
    private static final Weapon.WeaponBuilder GreatAxe =
            Weapon.builder()
                    .health(-15)
                    .attack(5)
                    .defense(-2)
                    .vampirism(10)
                    .name(GREATAXE);
    private static final Weapon.WeaponBuilder Katana =
            Weapon.builder()
                    .health(-20)
                    .attack(6)
                    .defense(-5)
                    .vampirism(50)
                    .name(KATANA);
    private static final Weapon.WeaponBuilder MagicWand =
            Weapon.builder()
                    .health(30)
                    .attack(3)
                    .healPower(3)
                    .name(MAGICWAND);

    public static IWeapon newSword(){
        return Sword.build();
    }
    public static IWeapon newShield(){
        return Shield.build();
    }
    public static IWeapon newGreatAxe(){
        return GreatAxe.build();
    }
    public static IWeapon newKatana(){
        return Katana.build();
    }
    public static IWeapon newMagicWand(){
        return MagicWand.build();
    }
}
