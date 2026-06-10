package com.example.labs.group;

import java.util.ArrayList;
import java.util.List;

/**
 * The Group/Ungroup use case, modelling the business logic of JHotDraw's
 * {@code GroupAction.groupFigures} / {@code ungroupFigures}: grouping removes the
 * selected figures from the drawing and adds a single group in their place;
 * ungrouping removes the group and releases its children back into the drawing.
 */
public class GroupingService {

    /**
     * Groups {@code figures}: removes each from the drawing and returns a new
     * group containing them, which is added to the drawing.
     */
    public FigureGroup group(Drawing drawing, List<String> figures) {
        // Invariant: the service is never invoked without a drawing. This should
        // never happen, so it is an assertion, not a recoverable condition.
        assert drawing != null : "drawing must never be null";

        // A null figure list is a caller error the program can recover from,
        // so it is signalled with an exception (program keeps running).
        if (figures == null) {
            throw new IllegalArgumentException("figures must not be null");
        }

        FigureGroup group = new FigureGroup();
        for (String figure : figures) {
            drawing.remove(figure);
            group.add(figure);
        }
        drawing.add(group);

        // Post-condition invariant: every input figure ended up in the group.
        assert group.size() == figures.size() : "all figures must be grouped";
        return group;
    }

    /**
     * Ungroups {@code group}: removes it from the drawing and adds its children
     * back. Returns the released children.
     */
    public List<String> ungroup(Drawing drawing, FigureGroup group) {
        assert drawing != null : "drawing must never be null";

        if (group == null) {
            throw new IllegalArgumentException("group must not be null");
        }

        drawing.remove(group);
        List<String> released = new ArrayList<>(group.getChildren());
        for (String figure : released) {
            drawing.add(figure);
        }
        return released;
    }
}
