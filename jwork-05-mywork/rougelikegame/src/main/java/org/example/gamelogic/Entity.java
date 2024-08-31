package org.example.gamelogic;

import org.example.gamelogic.component.Component;

import java.util.HashMap;

public class Entity {
    private HashMap<Class<? extends Component>, Component> components = new HashMap<>();
    private String typeLabel;

    public Entity(String typeLabel) {
        this.typeLabel = typeLabel;
    }

    public boolean hasComponent(Class<? extends Component> componentClass) {
        return components.containsKey(componentClass);
    }

    public <T extends Component> T getComponent(Class<? extends Component> componentClass) {
        return (T) components.get(componentClass);
    }

    public String getTypeLabel() {return this.typeLabel; }
}
