package com.example.labs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A minimal model of the JHotDraw "Group / Ungroup figures" feature.
 *
 * <p>It mirrors the essential behaviour located during the concept-location and
 * impact-analysis labs: a {@code GroupFigure} (here {@code FigureGroup}) holds a
 * collection of child figures, can have figures grouped into it, and can be
 * ungrouped to release its children. It exists to give the CI pipeline a real,
 * buildable unit with automated tests.
 */
public class FigureGroup {

    private final List<String> children = new ArrayList<>();

    /** Groups the given figures into this group. */
    public void group(List<String> figures) {
        children.addAll(figures);
    }

    /** Ungroups: returns a copy of the children and empties the group. */
    public List<String> ungroup() {
        List<String> released = new ArrayList<>(children);
        children.clear();
        return released;
    }

    /** Number of figures currently in the group. */
    public int size() {
        return children.size();
    }

    /** Read-only view of the grouped figures, preserving insertion (z) order. */
    public List<String> getChildren() {
        return Collections.unmodifiableList(children);
    }
}
