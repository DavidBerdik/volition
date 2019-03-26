package com.recoveryenhancementsolutions.volition;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class ViewActivitiesActivityTest {

  @Rule
  public ActivityTestRule<PlanActivity> activityTestRule = new ActivityTestRule<>(
      PlanActivity.class);

  /**
   * Creates 7 dates (each with 2 activity descriptions) and adds them into the TextViews
   */
  @Test
  public void testActivityUpdating() {
    Calendar cal = Calendar.getInstance();
    ArrayList<String> sampleDesc = new ArrayList<String>();
    ArrayList<TextView> textViews = new ArrayList<TextView>();

    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_today));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_yesterday));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_2_days));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_3_days));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_4_days));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_5_days));
    textViews.add((TextView) activityTestRule.getActivity().findViewById(R.id.details_6_days));

    for (int i = 0; i < 7; i++) {
      sampleDesc.add("Sample" + i);
      activityTestRule.getActivity().updateDayActivities(cal, sampleDesc);
      String value = textViews.get(i).getText().toString();

      Log.i("UserActivityViewModelTest",
          "Label " + i + "; expect:\"" + sampleDesc.get(0) + "\" got:\"" + value + '"');

      Assert.assertEquals(sampleDesc.get(0), value);
      sampleDesc.clear();

      cal.add(Calendar.DAY_OF_MONTH, -1);
    }
  }
}
