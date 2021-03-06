package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the StartupLandingPage activity and it's interactions with other pages.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class StartupLandingActivityTest {

  @Rule
  public ActivityTestRule<StartupLandingActivity> activityTestRule = new ActivityTestRule<>(
      StartupLandingActivity.class);

  /**
   * Test the continued use of the Create Profile button and back-out navigation.
   */
  @Test
  public void startupLandingActivityTest() {
    // Check that we're on the StartupLandingActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        StartupLandingActivity.class.getName());

    onView(withId(R.id.start_landing_button_create)).perform(click());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Check that we're on the CreateProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ProfileActivity.class.getName());

    // Until we figure out why Espresso is failing on some computers but not all,
    // Dr. Jackson has requested that we leave this portion commented out.

    /* // Clear the automatic text entry and open the back-menu.
    Espresso.pressBack();
    Espresso.pressBack();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Click the no button.
    onView(withId(android.R.id.button2)).perform(click());

    // Check that we're on the CreateProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        CreateProfileActivity.class.getName());

    // Open the cancel Menu again.
    pressBack();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Click the yes button.
    onView(withId(android.R.id.button1)).perform(click());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Check that we're on the StartupLandingActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        StartupLandingActivity.class.getName());

    onView(withId(R.id.start_landing_button_create)).perform(click());

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    // Check that we're on the CreateProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        CreateProfileActivity.class.getName()); */
  }

}
