package org.example.models;

import org.example.models.interfaces.IWarrior;
import org.example.models.interfaces.IWeapon;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class  Army {

    @Override
    public String toString() {
        return "Army{" +
                "troops=" + troops +
                '}';
    }

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
}
