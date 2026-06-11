package com.example.labs.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A composite figure that holds child figures in z-order, modelling JHotDraw's
 * {@code GroupFigure}.
 */
public class FigureGroup {

    private final List<String> children = new ArrayList<>();

    public void add(String figure) {
        children.add(figure);
    }

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
