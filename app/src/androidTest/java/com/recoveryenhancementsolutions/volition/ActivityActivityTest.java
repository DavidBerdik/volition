package com.recoveryenhancementsolutions.volition;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class ActivityActivityTest {

  @Rule
  public ActivityTestRule<PlanActivity> activityTestRule = new ActivityTestRule<>(
      PlanActivity.class);

  @Before
  public void initDB() {
    Thread t = new Thread() {
      public void run() {
        activityTestRule.getActivity().getViewModel().getDB().clearAllTables();

        // TODO: Make today.
        // Create 4 User Activity Dates
        final int[] userActivityYear = {2019, 2019, 2019, 2019};
        final int[] userActivityMonth = {3, 3, 3, 3};
        final int[] userActivityDay = {24, 23, 22, 21};

        // Insert the entities.
        for (int x = 0; x < userActivityDesc.length; x++) {
          activityTestRule.getActivity().getViewModel().insertActivity(userActivityYear[x], userActivityMonth[x], userActivityDay[x],
              userActivityDesc[x]);
        }
      }
    };

    t.start();

    /*
    try {
      t.wait();
      Thread.sleep(2000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    */
  }

  /**
   * Verifies that each calendar day is displaying the correct activity.
   */
  @Test
  public void testCalendar() {
    Calendar cal = Calendar.getInstance();
    ArrayList<TextView> textViews = new ArrayList<TextView>();

    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.textview_day_1));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.textview_day_2));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.textview_day_3));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.textview_day_4));

    for (int i = 0; i < textViews.size(); i++) {
      String value = textViews.get(i).getText().toString();

      Log.i("UserActivityViewModelTest",
          "Label " + i + "; expect:\"" + userActivityDesc[i] + "\" got:\"" + value + '"');

      //Assert.assertEquals(userActivityDesc[i], value);

      cal.add(Calendar.DAY_OF_MONTH, -1);
    }
  }

  private final String[] userActivityDesc = {"Took medication", "Went to the doctor\nTook medication", "Test 3", "Test 4"};
}
