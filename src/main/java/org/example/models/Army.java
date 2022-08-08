package org.example.models;

import org.example.models.interfaces.IWarrior;

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
        for (int i = 0; i < quantity; i++) {
            IWarrior next = factory.get();
            troops.add(next);
        }
        lineup();
        return this;
    }
}
