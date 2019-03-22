package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class ViewActivitiesActivityTest {

  @Rule
  public ActivityTestRule<ViewActivitiesActivity> activityTestRule = new ActivityTestRule<>(
      ViewActivitiesActivity.class);

  /**
   * Creates 7 dates (each with 2 activity descriptions) and adds them into the TextViews
   */
  @Test
  public void testActivityUpdating() {
    Calendar cal = Calendar.getInstance();
    ArrayList<String> sampleDescs = new ArrayList<String>();
    for (int i = 0; i < 7; i++) {
      sampleDescs.add("Sample " +i + " days ago 1");
      sampleDescs.add("Sample " +i + " days ago 2");

      activityTestRule.getActivity().updateDayActivities(cal, sampleDescs);
      cal.add(Calendar.DAY_OF_MONTH, -1);
      sampleDescs.clear();
    }
  }
}
