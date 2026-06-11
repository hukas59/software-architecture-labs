package com.example.labs.group;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple in-memory {@link Drawing} used to drive the BDD scenarios end-to-end
 * (no mocks — the scenarios exercise real behaviour).
 */
public class SimpleDrawing implements Drawing {

    private final List<Object> figures = new ArrayList<>();

    @Override
    public void add(Object figure) {
        figures.add(figure);
    }

    @Override
    public void remove(Object figure) {
        figures.remove(figure);
    }

    @Override
    public int size() {
        return figures.size();
    }

    /** Read-only view of the top-level figures currently in the drawing. */
    public List<Object> figures() {
        return Collections.unmodifiableList(figures);
    }
}
