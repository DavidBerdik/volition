package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * The first test for the radio buttons on the UI
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class RadioButtonTest1 {

  @Rule
  public ActivityTestRule<ProfileActivity> mActivityTestRule = new ActivityTestRule<>(
      ProfileActivity.class);

  /**
   * This radio button test checks for the presence of the Family or Support Person button, presses
   * it, and verifies the remainder radio buttons existence
   */
  @Test
  public void radioButtonTest1() {
    onView(withId(R.id.radioSupport)).perform(scrollTo(), click());

    onView(withId(R.id.radioHeroin)).check(matches(isDisplayed()));

    onView(withId(R.id.radioOpiates)).check(matches(isDisplayed()));

    onView(withId(R.id.radioAlcohol)).check(matches(isDisplayed()));

    onView(withId(R.id.radioCocaine)).check(matches(isDisplayed()));

    onView(withId(R.id.radioMarijuana)).check(matches(isDisplayed()));

    onView(withId(R.id.radioMeth)).check(matches(isDisplayed()));

    onView(withId(R.id.radioBen)).check(matches(isDisplayed()));

    onView(withId(R.id.radioTranquilizers)).check(matches(isDisplayed()));
  }
}
