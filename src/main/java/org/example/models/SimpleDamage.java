package org.example.models;

import org.example.models.interfaces.IDamage;
import org.example.models.interfaces.IWarrior;

record SimpleDamage (int hitPoints, IWarrior damageDealer) implements IDamage {}

