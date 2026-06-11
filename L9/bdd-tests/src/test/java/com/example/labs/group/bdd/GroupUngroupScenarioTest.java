package com.example.labs.group.bdd;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.junit.Test;

/**
 * BDD scenarios for the Group/Ungroup user story, automated with JGiven.
 *
 * <p>User story: <i>As a user creating a diagram, I want to group several
 * selected figures into a single object (and ungroup it again later), so that I
 * can move, resize and arrange related shapes together as one unit.</i>
 */
public class GroupUngroupScenarioTest
        extends ScenarioTest<GivenDrawing, WhenUser, ThenOutcome> {

    @Test
    public void selected_figures_can_be_grouped_into_a_single_object() {
        given().a_drawing_with_figures("rectangle", "ellipse", "line");
        when().I_group_the_selected_figures();
        then().the_drawing_contains_a_single_group()
                .and().the_group_contains_$_figures(3);
    }

    @Test
    public void a_group_can_be_ungrouped_back_into_its_figures() {
        given().a_drawing_containing_a_group_of("rectangle", "ellipse");
        when().I_ungroup_the_group();
        then().the_drawing_contains_the_figures("rectangle", "ellipse")
                .and().the_drawing_contains_no_group()
                .and().the_released_figures_are("rectangle", "ellipse");
    }

    @Test
    public void grouping_an_empty_selection_yields_an_empty_group() {
        given().an_empty_drawing();
        when().I_group_the_selected_figures();
        then().the_group_is_empty();
    }
}
