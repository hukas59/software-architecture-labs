package com.example.labs.group;

import java.util.ArrayList;
import java.util.List;

/**
 * The Group/Ungroup use case (see Testing Lab). Grouping removes the selected
 * figures from the drawing and adds a single group in their place; ungrouping
 * removes the group and releases its children back into the drawing.
 */
public class GroupingService {

    public FigureGroup group(Drawing drawing, List<String> figures) {
        assert drawing != null : "drawing must never be null";
        if (figures == null) {
            throw new IllegalArgumentException("figures must not be null");
        }

        FigureGroup group = new FigureGroup();
        for (String figure : figures) {
            drawing.remove(figure);
            group.add(figure);
        }
        drawing.add(group);

        assert group.size() == figures.size() : "all figures must be grouped";
        return group;
    }

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
