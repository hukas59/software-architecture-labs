package com.example.labs.group.bdd;

import com.example.labs.group.FigureGroup;
import com.example.labs.group.GroupingService;
import com.example.labs.group.SimpleDrawing;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;

import java.util.List;

/** The "When" stage: performs the group / ungroup action. */
public class WhenUser extends Stage<WhenUser> {

    @ExpectedScenarioState
    SimpleDrawing drawing;

    @ExpectedScenarioState
    GroupingService service;

    @ExpectedScenarioState
    List<String> selectedFigures;

    @ExpectedScenarioState
    FigureGroup existingGroup;

    @ProvidedScenarioState
    FigureGroup createdGroup;

    @ProvidedScenarioState
    List<String> released;

    public WhenUser I_group_the_selected_figures() {
        createdGroup = service.group(drawing, selectedFigures);
        return self();
    }

    public WhenUser I_ungroup_the_group() {
        released = service.ungroup(drawing, existingGroup);
        return self();
    }
}
