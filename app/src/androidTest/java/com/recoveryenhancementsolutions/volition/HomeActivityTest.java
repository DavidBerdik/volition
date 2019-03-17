package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the HomeActivity class. Interacts with several buttons and confirms their output.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  @Test
  public void homeActivityTest_Buttons() {
    onView(withId(R.id.menubar_activity)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Activity")));
    onView(withId(R.id.menubar_home)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Home")));
    onView(withId(R.id.menubar_activity)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Activity")));
    onView(withId(R.id.menubar_plan)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Plan")));
    onView(withId(R.id.menubar_activity)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Activity")));
    onView(withId(R.id.menubar_home)).perform(click());
    onView(withId(R.id.buttonTestItem)).check(matches(withText("Home")));
  }

}
