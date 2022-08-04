package org.example.models.interfaces;

public interface IWarrior extends CanAttack, HasHealth{
    void hit(IWarrior opponent);
    void receiveHit(IDamage damage);
    IWarrior getWarriorBehind();
    void setWarriorBehind(IWarrior warrior);
}
