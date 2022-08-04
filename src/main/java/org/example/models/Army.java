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
        for( int i = 1; i < troops.size(); i++) {
            troops.get(i-1).setWarriorBehind(troops.get(i));
        }
        return this;
    }

    Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            IWarrior next = factory.get();
            if(!troops.isEmpty()) {
                troops.get(troops.size() - 1).setWarriorBehind(next);
            }
            troops.add(next);
        }
        return this;
    }
}
