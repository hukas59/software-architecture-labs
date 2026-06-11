package com.example.labs.group.bdd;

import com.example.labs.group.FigureGroup;
import com.example.labs.group.SimpleDrawing;
import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.Quoted;
import com.tngtech.jgiven.annotation.ScenarioState.Resolution;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/** The "Then" stage: domain-specific assertions via AssertJ. */
public class ThenOutcome extends Stage<ThenOutcome> {

    @ExpectedScenarioState(resolution = Resolution.NAME)
    SimpleDrawing drawing;

    @ExpectedScenarioState(resolution = Resolution.NAME)
    FigureGroup createdGroup;

    @ExpectedScenarioState(resolution = Resolution.NAME)
    List<String> released;

    public ThenOutcome the_drawing_contains_a_single_group() {
        assertThat(drawing.figures()).hasSize(1);
        assertThat(drawing.figures().get(0)).isInstanceOf(FigureGroup.class);
        return self();
    }

    public ThenOutcome the_group_contains_$_figures(int count) {
        assertThat(createdGroup.size()).isEqualTo(count);
        return self();
    }

    public ThenOutcome the_group_is_empty() {
        assertThat(createdGroup.isEmpty()).isTrue();
        return self();
    }

    public ThenOutcome the_drawing_contains_the_figures(@Quoted String... figures) {
        assertThat(drawing.figures()).contains((Object[]) figures);
        return self();
    }

    public ThenOutcome the_drawing_contains_no_group() {
        assertThat(drawing.figures()).noneMatch(figure -> figure instanceof FigureGroup);
        return self();
    }

    public ThenOutcome the_released_figures_are(@Quoted String... figures) {
        assertThat(released).containsExactly(figures);
        return self();
    }
}
