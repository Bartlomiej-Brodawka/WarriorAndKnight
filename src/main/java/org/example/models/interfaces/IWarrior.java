package org.example.models.interfaces;

public interface IWarrior extends CanAttack, HasHealth, Weaponry{
    void hit(IWarrior opponent);
    void receiveHit(IDamage damage);
    IWarrior getWarriorBehind();
    void setWarriorBehind(IWarrior warriorBehind);
    IWarrior getWarriorInFrontOf();
    void setWarriorInFrontOf(IWarrior warriorInFrontOf);
    default void processCommand(ICommand command, IWarrior warrior) {
        var behind = warrior.getWarriorBehind();
        if(behind != null) {
            behind.processCommand(command, this);
        }
    }
}
