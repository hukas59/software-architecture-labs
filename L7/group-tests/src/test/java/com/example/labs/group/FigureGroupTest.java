package com.example.labs.group;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * JUnit 4 unit tests for the {@link FigureGroup} entity (no collaborators, so no
 * mocks are needed). Covers the best case and the empty boundary case.
 */
public class FigureGroupTest {

    @Test
    public void newGroup_isEmpty() {
        assertTrue(new FigureGroup().isEmpty());
    }

    @Test
    public void add_keepsChildrenInInsertionOrder() {
        FigureGroup group = new FigureGroup();
        group.add("rectangle");
        group.add("ellipse");

        assertEquals(2, group.size());
        assertEquals(Arrays.asList("rectangle", "ellipse"), group.getChildren());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void getChildren_isUnmodifiable() {
        new FigureGroup().getChildren().add("illegal");
    }
}
