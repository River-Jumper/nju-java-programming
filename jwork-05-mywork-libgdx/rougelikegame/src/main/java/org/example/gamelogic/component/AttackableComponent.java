package org.example.gamelogic.component;

import com.badlogic.ashley.core.Component;

public class AttackableComponent implements Component {
    public int attack;
    public AttackableComponent(int attack) {
        this.attack = attack;
    }
}
