package org.example.models;

import org.example.models.interfaces.HasHealth;
import org.example.models.interfaces.IWarrior;
import org.example.models.interfaces.IWeapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class  Army {

    List<IWarrior> troops = new ArrayList<>();

    public Iterator<IWarrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<IWarrior> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()){
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public IWarrior next() {
            if(!hasNext()){
                throw  new NoSuchElementException();
            }
            return troops.get(cursor);
        }
    }

    public Army lineup() {
        for(int i = 0; i < troops.size(); i++) {
            if (i + 1 < troops.size()) {
                troops.get(i).setWarriorBehind(troops.get(i + 1));
            }
            if (i - 1 >= 0) {
                troops.get(i).setWarriorInFrontOf(troops.get(i - 1));
            }
        }
        return this;
    }

    Army addUnits(Supplier<Warrior> factory, int quantity) {
        if (factory.get() instanceof Warlord warlord) {
            if (troops.stream().noneMatch(Warlord.class::isInstance)) {
                troops.add(warlord);
            }
            return this;
        }

        for (int i = 0; i < quantity; i++) {
            IWarrior next = factory.get();
            troops.add(next);
        }
        return this;
    }

    public int getSize() {
        return troops.size();
    }

    public boolean isEmpty() {
        return troops.isEmpty();
    }

    public IWarrior get(int i) {
        return troops.get(i);
    }

    public void removeDeadSoldiers() {
        int size = troops.size();

        for(int i = 0; i< size; i++) {

            if(troops.get(i).getHealth()<=0) {
                troops.remove(i--);
                size--;
            }
        }
    }

    public void equipWarriorAtPosition(int index, IWeapon weapon) {
        troops.get(index).equipWeapon(weapon);
    }

    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }

    public boolean isWarlordInArmy() {
        return troops.stream()
                .filter(HasHealth::isAlive)
                .anyMatch(Warlord.class::isInstance);
    }

    public boolean isLancerInArmy() {
        return troops.stream()
                .filter(HasHealth::isAlive)
                .anyMatch(Lancer.class::isInstance);
    }

    public boolean isHealerInArmy() {
        return troops.stream()
                .filter(HasHealth::isAlive)
                .anyMatch(Healer.class::isInstance);
    }

    public void moveFirstOtherSoldierToTheFrontInArmy() {

        Iterator<IWarrior> it1 = troops.iterator();

        while(it1.hasNext()) {
            var soldier = it1.next();
            if(!(soldier instanceof Healer) && !(soldier instanceof Warlord) && soldier.isAlive()) {
                var temp = troops.indexOf(soldier);
                troops.remove(temp);
                troops.add(0, soldier);
                break;
            }
        }
    }

    public void moveWarlordToTheEnd() {
        if(!(troops.get(getSize()-1) instanceof Warlord)) {
            var warlord = troops.stream()
                    .filter(Warlord.class::isInstance)
                    .findAny()
                    .orElseThrow(NoSuchElementException::new);

            troops.remove(warlord);
            troops.add(warlord);
        }
    }

    public void moveLancersToTheFront() {
        Iterator<IWarrior> it1 = troops.iterator();

        while(it1.hasNext()) {
            var soldier = it1.next();
            if(soldier instanceof Lancer lancer && lancer.isAlive()) {
                var temp = troops.indexOf(lancer);
                troops.remove(temp);
                troops.add(0, lancer);
                break;
            }
        }
    }

    public void moveHealersToTheSecondPosition() {
        Iterator<IWarrior> it1 = troops.iterator();

        while(it1.hasNext()) {
            var soldier = it1.next();
            if(soldier instanceof Healer healer && healer.isAlive()) {
                var temp = troops.indexOf(healer);
                troops.remove(temp);
                troops.add(1, healer);
                break;
            }
        }
    }

    public void moveUnits() {
        if(isWarlordInArmy()) {
            moveWarlordToTheEnd();
            if (isLancerInArmy()) {
                moveLancersToTheFront();
            } else {
                moveFirstOtherSoldierToTheFrontInArmy();
            }
            if (isHealerInArmy()) {
                moveHealersToTheSecondPosition();
            }
        }
    }
}
