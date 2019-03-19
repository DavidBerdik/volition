package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Unit test for the HomeActivity class. Interacts with several buttons and confirms their output.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReportUseActivityTest {

  @Rule
  public ActivityTestRule<ReportUseActivity> activityTestRule = new ActivityTestRule<>(
      ReportUseActivity.class);

  /**
   * Tests that the Yes and No buttons are responding to single clicks properly.
   */
  @Test
  public void reportUseActivityTest_Single() {
    assertEquals(0, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
  }

  /**
   * Tests that the Yes and No buttons are responding to multiple clicks properly.
   */
  @Test
  public void reportUseActivityTest_Multiple() {
    assertEquals(0, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
  }
}
