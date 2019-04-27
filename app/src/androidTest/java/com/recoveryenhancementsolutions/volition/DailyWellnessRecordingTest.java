package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import com.recoveryenhancementsolutions.volition.utilities.NumberPickerTestUtility;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the functionality of the DailyWellnessActivity with database interaction.
 */
@RunWith(AndroidJUnit4.class)
public class DailyWellnessRecordingTest {

  @Rule
  public ActivityTestRule<ActivityActivity> activityTestRule = new ActivityTestRule<>(
      ActivityActivity.class);

  /**
   * Prepares the testing environment with a testing database.
   */
  @Before
  public void prepare() {
    VolitionDatabase.setTestDatabase(activityTestRule.getActivity());
  }

  /**
   * Tests the functionality of logging something and its live updates.
   */
  @Test
  public void dailyWellnessActivityTest() {

    onView(withId(R.id.DailyWellness)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Scroll to 7 and click enter..
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 7, EspressoTestUtility.getCurrentActivity());
    onView(withId(R.id.daily_wellness_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Navigate to Plan screen after allowing some update time.
    onView(withId(R.id.core_navigation_plan)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(2000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    while(true) {}
  }
}
