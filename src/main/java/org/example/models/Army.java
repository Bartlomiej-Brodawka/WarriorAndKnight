package org.example.models;

import org.example.models.interfaces.Unit;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.function.Supplier;

public class  Army {

    List<Warrior> troops = new ArrayList<>();

    public Iterator<Warrior> firstAlive() {
        return new FirstAliveIterator();
    }

    private class FirstAliveIterator implements Iterator<Warrior> {
        int cursor = 0;
        @Override
        public boolean hasNext() {
            while (cursor < troops.size() && !troops.get(cursor).isAlive()){
                cursor++;
            }
            return cursor < troops.size();
        }

        @Override
        public Warrior next() {
            if(!hasNext()){
                throw  new NoSuchElementException();
            }
            return troops.get(cursor);
        }
    }

    //enum example
    void addUnits(Unit.UnitType type, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add((Warrior) Unit.newUnit(type));
        }
    }

    //cloning example
    void addUnits(Warrior prototype, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(prototype.clone());
        }
    }

    //fluent interface example
    Army addUnits(Supplier<Warrior> factory, int quantity) {
        for (int i = 0; i < quantity; i++) {
            troops.add(factory.get());
        }
        return this;
    }

    //reflection example
    public void addUnits(Class<? extends Unit> cls, int quantity){
        try {
            var constractor =  cls.getDeclaredConstructor();
            for(int i=0; i< quantity; i++) {
                var o = constractor.newInstance();
                troops.add((Warrior) o);
            }
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

}
