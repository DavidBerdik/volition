package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;

/**
 * Unit test for the ReportUseActivity class. Interacts with several buttons and confirms their
 * output.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class ReportUseActivityTest {

  @Rule
  public ActivityTestRule<ReportUseActivity> activityTestRule = new ActivityTestRule<>(
      ReportUseActivity.class);

  /**
   * Creates a testing environment for the ReportUseActivity to use
   */
  @Before
  public void loadTestEnvironment() {
    // Create a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
            .allowMainThreadQueries().build();

    activityTestRule.getActivity().setTestEnvironment(true);
    activityTestRule.getActivity().setTestDatabase(db);
    viewModel = activityTestRule.getActivity().getViewModel();
    //viewModel.setTestDatabase(db);

    demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Client");
    //Assume that on Jan 1, the client hit "Yes"
    demographicDataEntity.setLastClean(initialLogDay, initialLogDay);
    db.demographicDataDao().insertDemographicInfo(demographicDataEntity);
    viewModel.getLastCleanDate().observe(activityTestRule.getActivity(), dateObserver);
  }
  private Observer<Date> dateObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
    }
  };
  /**
   * Tests if pressing the yes and no buttons update the database correctly
   */
  @Test
  public void updateDatabaseTest() {
    try{
      Calendar cal = Calendar.getInstance();
      //Initial state: both the last clean and last report should be the initial day
      assertTrue(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()).equals(initialLogDay));
      assertTrue(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastReportDate()).equals(initialLogDay));

      //click No: Last clean should be the same, last report should be updated
      onView(withId(R.id.report_use_no)).perform(click());
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastReportDate()));
      assertEquals(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()), initialLogDay);
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));

      //click Yes: Both Last clean and last report should be updated
      onView(withId(R.id.report_use_yes)).perform(click());
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()));
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastReportDate()));
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
    }
    catch(Exception e){
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Tests that the Yes and No buttons are responding to single clicks properly.
   */
  @Test
  public void reportUseActivityTest_Single() {
    assertEquals(0, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    Log.e(TAG, "Hit yes");
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    Log.e(TAG, "Hit no");
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
  }

  /**
   * Tests that the Yes and No buttons are responding to multiple clicks properly.
   */
  @Test
  public void reportUseActivityTest_Multiple() {
    assertEquals(0, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_no)).perform(click());
    assertEquals(2, activityTestRule.getActivity().getLastClickedItem());
    onView(withId(R.id.report_use_yes)).perform(click());
    assertEquals(1, activityTestRule.getActivity().getLastClickedItem());
  }

  private final Calendar today = Calendar.getInstance();
  private final Date initialLogDay = new Date(1546318800000L); //January 1st, 2019
  private DemographicDataEntity demographicDataEntity;
  private DemographicDataViewModel viewModel;
  private final String TAG = "RepUseActivityTest";
}
