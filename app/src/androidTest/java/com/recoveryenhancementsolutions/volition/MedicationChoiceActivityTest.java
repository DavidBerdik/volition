package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;

import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MedicationChoiceActivityTest {

  @Rule
  public ActivityTestRule<MedicationChoiceActivity> mActivityTestRule = new ActivityTestRule<>(
      MedicationChoiceActivity.class);

  @Test
  public void medicationChoiceActivityTest() {
    ViewInteraction button = onView(
        allOf(withId(R.id.medication),isDisplayed()));
    button.check(matches(isDisplayed()));

    ViewInteraction button2 = onView(
        allOf(withId(R.id.abstain),isDisplayed()));
    button2.check(matches(isDisplayed()));
  }
}
