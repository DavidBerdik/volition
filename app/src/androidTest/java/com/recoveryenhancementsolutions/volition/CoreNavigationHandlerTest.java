package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
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
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Declares a required initialization that allows Espresso to check for intent activity.
   */
  @Before
  public void declareIntent() {
    Intents.init();
  }

  /**
   * Checks that the CoreNavigationHandler properly switches activities once.
   */
  @Test
  public void coreNavigationHandlerTest_Single() {
    // Confirm that we are on the HomeActivity page.
    onView(withId(R.id.welcome)).check(matches(withText(R.string.home_welcome)));

    // Click to the plan activity.
    onView(withId(R.id.core_navigation_plan)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the PlanActivity class.
    intended(hasComponent(PlanActivity.class.getName()));
  }
}
