package com.example.labs.group;

/**
 * The drawing (model) that owns figures — the collaborator used by the grouping
 * use case. Mirrors the subset of JHotDraw's {@code Drawing} used by
 * {@code GroupAction.groupFigures} / {@code ungroupFigures}.
 */
public interface Drawing {

    /** Adds a figure (or a group, which is itself a figure) to the drawing. */
    void add(Object figure);

    /** Removes a figure (or a group) from the drawing. */
    void remove(Object figure);

    /** Number of top-level figures currently in the drawing. */
    int size();
}
