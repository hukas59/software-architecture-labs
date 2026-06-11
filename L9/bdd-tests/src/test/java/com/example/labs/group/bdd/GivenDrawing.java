package com.example.labs.group.bdd;

import com.example.labs.group.FigureGroup;
import com.example.labs.group.GroupingService;
import com.example.labs.group.SimpleDrawing;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.annotation.ScenarioState.Resolution;

import java.util.ArrayList;
import java.util.List;

/** The "Given" stage: sets up the drawing and selection for a scenario. */
public class GivenDrawing extends Stage<GivenDrawing> {

    @ProvidedScenarioState(resolution = Resolution.NAME)
    SimpleDrawing drawing = new SimpleDrawing();

    @ProvidedScenarioState(resolution = Resolution.NAME)
    GroupingService service = new GroupingService();

    @ProvidedScenarioState(resolution = Resolution.NAME)
    List<String> selectedFigures = new ArrayList<>();

    @ProvidedScenarioState(resolution = Resolution.NAME)
    FigureGroup existingGroup;

    public GivenDrawing a_drawing_with_figures(@Quoted String... figures) {
        for (String figure : figures) {
            drawing.add(figure);
            selectedFigures.add(figure);
        }
        return self();
    }

    public GivenDrawing an_empty_drawing() {
        return self();
    }

    public GivenDrawing a_drawing_containing_a_group_of(@Quoted String... figures) {
        existingGroup = new FigureGroup();
        for (String figure : figures) {
            existingGroup.add(figure);
        }
        drawing.add(existingGroup);
        return self();
    }
}
