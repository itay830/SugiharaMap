package com.example.sugiharamap.utils.cssUtil;

import javafx.beans.property.ObjectPropertyBase;
import javafx.scene.Node;

import java.util.HashSet;
import java.util.Set;

public class EnumPseudoClassProperty<T extends PseudoClassProvider> extends ObjectPropertyBase<T> {
    public void addSubscriber(Node node) {
        node.pseudoClassStateChanged(this.getValue().getPseudoClass(), true);
        this.subs.add(node);
    }

    private final Set<Node> subs = new HashSet<>();
    private PseudoClassProvider oldValue;

    public EnumPseudoClassProperty(T initValue) {
        this.set(initValue);
    }

    public EnumPseudoClassProperty(Node sub) {
        subs.add(sub);
    }

    public EnumPseudoClassProperty(Node sub, T initValue) {
        subs.add(sub);
        this.set(initValue);
    }

    public boolean removeSubscriber(Node sub) {
        return subs.remove(sub);
    }

    @Override
    protected void invalidated() {
        subs.forEach((sub) -> {
            if (oldValue != null) {
                sub.pseudoClassStateChanged(oldValue.getPseudoClass(), false);
            }
            if (getValue() != null) {
                sub.pseudoClassStateChanged(getValue().getPseudoClass(), true);
            }
        });
        oldValue = getValue();
    }

    @Override
    public Object getBean() {
        return null;
    }

    @Override
    public String getName() {
        return "";
    }
}