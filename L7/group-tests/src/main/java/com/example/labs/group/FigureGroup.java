package com.example.labs.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A composite figure that holds a collection of child figures, modelling
 * JHotDraw's {@code GroupFigure}. Children are kept in insertion (z) order.
 */
public class FigureGroup {

    private final List<String> children = new ArrayList<>();

    /** Adds a child figure to this group. */
    public void add(String figure) {
        children.add(figure);
    }

    /** Read-only view of the children, preserving insertion (z) order. */
    public List<String> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public int size() {
        return children.size();
    }

    public boolean isEmpty() {
        return children.isEmpty();
    }
}
