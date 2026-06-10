package com.example.labs;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Automated tests for {@link FigureGroup}. These are executed by the CI
 * pipeline on every push and pull request.
 */
class FigureGroupTest {

    @Test
    void groupAddsAllFiguresInOrder() {
        FigureGroup group = new FigureGroup();
        group.group(Arrays.asList("rectangle", "ellipse", "line"));

        assertEquals(3, group.size());
        assertEquals(Arrays.asList("rectangle", "ellipse", "line"), group.getChildren());
    }

    @Test
    void ungroupReturnsChildrenAndEmptiesGroup() {
        FigureGroup group = new FigureGroup();
        group.group(Arrays.asList("rectangle", "ellipse"));

        List<String> released = group.ungroup();

        assertEquals(Arrays.asList("rectangle", "ellipse"), released);
        assertEquals(0, group.size());
    }

    @Test
    void ungroupOnEmptyGroupReturnsEmptyList() {
        FigureGroup group = new FigureGroup();
        assertTrue(group.ungroup().isEmpty());
    }
}
