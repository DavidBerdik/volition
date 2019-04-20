package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * A testing object used to monitor the functionality of the ActivityNavigationHandler. Uses the
 * ActivityActivity class as the starting Activity.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityNavigationHandlerTest {

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

  /**
   * Checks that the ActivityNavigationHandler properly handled touches.
   */
  @Test
  public void activityNavigationTest() {
    // Confirm that we are on the ActivityActivity page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    // Click to the Daily Wellness activity.
    onView(withId(R.id.DailyWellness)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the Daily Wellness page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        DailyWellnessActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Click to the TEA activity.
    onView(withId(R.id.TEA)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the TEA page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentExperienceAssessmentActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Click to the Clean Tracker activity.
    onView(withId(R.id.CleanTracker)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the Clean Tracker page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ReportUseActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Click to the EDU activity.
    onView(withId(R.id.Edu)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the EDU page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        EDUActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Click to the Journal activity.
    onView(withId(R.id.Journal)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the Journal page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        JournalActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Click to the Lesson activity.
    onView(withId(R.id.Lesson)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the Lesson page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        LessonActivity.class.getName());

    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Confirm that we are on the ActivityActivity page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());
  }
}
