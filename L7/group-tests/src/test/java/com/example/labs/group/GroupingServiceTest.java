package com.example.labs.group;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

/**
 * JUnit 4 unit tests for {@link GroupingService}, the core business logic of the
 * Group/Ungroup feature. The {@link Drawing} collaborator is a Mockito mock, so
 * each test exercises a single code-path through a single method and verifies the
 * interactions with the dependency.
 */
@RunWith(MockitoJUnitRunner.class)
public class GroupingServiceTest {

    @Mock
    private Drawing drawing;

    private final GroupingService service = new GroupingService();

    // ---------- Best-case scenarios ----------

    @Test
    public void group_movesSelectedFiguresIntoANewGroup() {
        List<String> figures = Arrays.asList("rectangle", "ellipse", "line");

        FigureGroup group = service.group(drawing, figures);

        assertEquals(3, group.size());
        assertEquals(figures, group.getChildren());
        // Each figure was removed from the drawing and the group added in their place.
        verify(drawing).remove("rectangle");
        verify(drawing).remove("ellipse");
        verify(drawing).remove("line");
        verify(drawing).add(group);
        verifyNoMoreInteractions(drawing);
    }

    @Test
    public void ungroup_releasesChildrenBackIntoTheDrawing() {
        FigureGroup group = new FigureGroup();
        group.add("rectangle");
        group.add("ellipse");

        List<String> released = service.ungroup(drawing, group);

        assertEquals(Arrays.asList("rectangle", "ellipse"), released);
        verify(drawing).remove(group);
        verify(drawing).add("rectangle");
        verify(drawing).add("ellipse");
        verifyNoMoreInteractions(drawing);
    }

    // ---------- Boundary cases ----------

    @Test
    public void group_withSingleFigure_isStillGrouped() {
        FigureGroup group = service.group(drawing, Collections.singletonList("rectangle"));

        assertEquals(1, group.size());
        verify(drawing).remove("rectangle");
        verify(drawing).add(group);
    }

    @Test
    public void group_withEmptySelection_createsEmptyGroup_andRemovesNothing() {
        FigureGroup group = service.group(drawing, Collections.<String>emptyList());

        assertTrue(group.isEmpty());
        verify(drawing, never()).remove(any());
        verify(drawing).add(group);
    }

    @Test
    public void ungroup_emptyGroup_removesGroup_andAddsNoChildren() {
        FigureGroup group = new FigureGroup();

        List<String> released = service.ungroup(drawing, group);

        assertTrue(released.isEmpty());
        verify(drawing).remove(group);
        verify(drawing, never()).add(any(String.class));
    }

    // ---------- Error handling: exception (recoverable), not assertion ----------

    @Test(expected = IllegalArgumentException.class)
    public void group_withNullFigures_throwsException() {
        service.group(drawing, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void ungroup_withNullGroup_throwsException() {
        service.ungroup(drawing, null);
    }
}
