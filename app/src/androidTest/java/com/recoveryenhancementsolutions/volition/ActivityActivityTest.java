package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class ActivityActivityTest {

  @Rule
  public ActivityTestRule<ActivityActivity> activityTestRule = new ActivityTestRule<>(
      ActivityActivity.class);

  /**
   * Declares a required initialization that allows Espresso to check for intent activity.
   */
  @Before
  public void declareIntent() {
    Intents.init();
  }

  /**
   * Releases a required initialization that allows Espresso to check for intent activity.
   */
  @After
  public void releaseIntent() {
    Intents.release();
  }

  @Test
  public void ActivityActivityTest_Multiple() {
    // Confirm that we are on the ActivityActivity page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    // Click on the TEA button
    onView(withId(R.id.TEA)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the TreatmentAssessmentActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentAssessmentActivity.class.getName());

    // Click the back button to test the other buttons
    espresso.pressBack();

    // Click on the Lesson button
    onView(withId(R.id.Lesson)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the LessonActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        LessonActivity.class.getName());

    // Click the back button to test the other buttons
    espresso.pressBack();

    // Click on the Journal button
    onView(withId(R.id.Journal)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the JournalActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        JournalActivity.class.getName());


  }
}


