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

/**
 * A testing object used to monitor the functionality of the CoreNavigationHandler. Uses the
 * HomeActivity class as the starting Activity.
 */
@RunWith(AndroidJUnit4.class)
public class CoreNavigationHandlerTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

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
   * Checks that the CoreNavigationHandler properly switches activities once.
   */
  @Test
  public void coreNavigationHandlerTest_Single() {
    // Confirm that we are on the HomeActivity page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        HomeActivity.class.getName());

    // Click to the plan activity.
    onView(withId(R.id.core_navigation_plan)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());
  }

  /**
   * Checks that the CoreNavigationHandler properly handles multiple activities at once.
   */
  @Test
  public void coreNavigationHandlerTest_Multiple() {
    // Confirm that we are on the HomeActivity page.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        HomeActivity.class.getName());

    // Click to the profile activity.
    onView(withId(R.id.core_navigation_profile)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ProfileActivity.class.getName());

    // Click to the plan activity.
    onView(withId(R.id.core_navigation_plan)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());

    // Click to the Activity activity.
    onView(withId(R.id.core_navigation_activity)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ActivityActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    // Click to the Activity activity (should not function).
    onView(withId(R.id.core_navigation_activity)).perform(click());

    // Check that we're on the ActivityActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    Espresso.pressBack();

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());

    Espresso.pressBack();

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ProfileActivity.class.getName());

    Espresso.pressBack();

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the HomeActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        HomeActivity.class.getName());

    // Click to the Activity activity.
    onView(withId(R.id.core_navigation_activity)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ActivityActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());

    // Click to the Plan activity.
    onView(withId(R.id.core_navigation_plan)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());

    // Click to the Profile activity.
    onView(withId(R.id.core_navigation_profile)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ProfileActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ProfileActivity.class.getName());

    Espresso.pressBack();

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        PlanActivity.class.getName());

    // Click to the Home activity.
    onView(withId(R.id.core_navigation_home)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the HomeActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        HomeActivity.class.getName());
  }
}
