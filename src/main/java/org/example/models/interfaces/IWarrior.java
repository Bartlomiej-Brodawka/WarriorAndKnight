package org.example.models.interfaces;

public interface IWarrior extends CanAttack, HasHealth{
    default void hit(IWarrior opponent) {
        opponent.receiveHit(this);
    }
    default void receiveHit(CanAttack damageDealer) {
        reduceHealthBasedOnDamage(damageDealer.getAttack());
    }
}
