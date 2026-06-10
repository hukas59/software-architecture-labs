package com.example.labs.group;

/**
 * The drawing (model) that owns figures. This is the collaborator that the
 * grouping use case depends on; in the unit tests it is replaced by a Mockito
 * mock so each test exercises a single code-path through a single method.
 *
 * <p>It mirrors the subset of JHotDraw's {@code Drawing} that
 * {@code GroupAction.groupFigures} / {@code ungroupFigures} actually use.
 */
public interface Drawing {

    /** Adds a figure (or a group, which is itself a figure) to the drawing. */
    void add(Object figure);

    /** Removes a figure (or a group) from the drawing. */
    void remove(Object figure);

    /** Number of top-level figures currently in the drawing. */
    int size();
}
