package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import com.recoveryenhancementsolutions.volition.utilities.NumberPickerTestUtility;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests the functionality of the DailyWellnessActivity without database interaction.
 */
@RunWith(AndroidJUnit4.class)
public class DailyWellnessActivityTest {

  @Rule
  public ActivityTestRule<DailyWellnessActivity> activityTestRule = new ActivityTestRule<>(
      DailyWellnessActivity.class);

  /**
   * Tests the functionality of the NumberPicker and its live updates.
   */
  @Test
  public void dailyWellnessActivityTest() {
    // Confirm the default page setting.
    assertEquals("Your Rating: 5 (Moderate)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to ten and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 10, activityTestRule);
    assertEquals("Your Rating: 10 (Great)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to one and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 1, activityTestRule);
    assertEquals("Your Rating: 1 (Poor)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to six and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 6, activityTestRule);
    assertEquals("Your Rating: 6 (Moderate)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to seven and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 7, activityTestRule);
    assertEquals("Your Rating: 7 (Moderate)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to two and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 2, activityTestRule);
    assertEquals("Your Rating: 2 (Poor)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to eight and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 8, activityTestRule);
    assertEquals("Your Rating: 8 (Great)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to three and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 3, activityTestRule);
    assertEquals("Your Rating: 3 (Poor)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to four and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 4, activityTestRule);
    assertEquals("Your Rating: 4 (Moderate)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Scroll to nine and test.
    NumberPickerTestUtility.selectValue(R.id.daily_wellness_number_picker, 9, activityTestRule);
    assertEquals("Your Rating: 9 (Great)",
        activityTestRule.getActivity().getDailyWellnessResultsText());

    // Click to the Activity activity.
    onView(withId(R.id.activity_back_navigation_button)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ActivityActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ActivityActivity.class.getName());
  }
}
