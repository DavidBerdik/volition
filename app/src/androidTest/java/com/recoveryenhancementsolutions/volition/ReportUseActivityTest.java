package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.base.InterruptableUiController;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import android.widget.DatePicker;


import org.hamcrest.Matchers;
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
    DemographicDataEntity demographicDataEntity;
    // Create a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();

    //Configures the viewModel to work with the test database
    activityTestRule.getActivity().setTestEnvironment(true);
    activityTestRule.getActivity().setTestDatabase(db);
    viewModel = activityTestRule.getActivity().getViewModel();

    //viewModel.getLastCleanDate().observe(activityTestRule.getActivity(), cleanObserver);
    viewModel.getLastReportDate().observe(activityTestRule.getActivity(), reportObserver);
    demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Client");
    //Assume that on Jan 1, the client hit "Yes"
    //demographicDataEntity.setLastClean(initialLogDay, initialLogDay);
    db.demographicDataDao().insertDemographicInfo(demographicDataEntity);

  }

  /**
   * Tests if pressing the yes and no buttons correctly updates the database
   */
  @Test
  public void updateDatabaseTest() {
    try {
      Calendar cal = Calendar.getInstance();
      Calendar cal2 = Calendar.getInstance();
      cal.setTime(initialLogDay);
      //Update the database
      viewModel.updateLastCleanDate(cal, cal);
      //Give the database a second to update
      sleep();
      //Initial state: both the last clean and last report should be the initial day
      assertEquals(initialLogDay, activityTestRule.getActivity().prevUseDate);
      assertEquals(prevReportDay, initialLogDay);
      Log.e(TAG, "Test 1 completed");

      //click No: Last clean should be the same as the initial, last report should be set to today
      onView(withId(R.id.report_use_no)).perform(click());
      cal.setTime(prevReportDay);
      sleep();
      assertEquals(initialLogDay, activityTestRule.getActivity().prevUseDate);
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
      Log.e(TAG, "Test 2 completed");

      //click Yes: Last clean should be updated if a valid date is entered
      //Valid date; Last clean should update to January 20, 2019
      recordYesActivity(2019, 1, 20);
      Date d = new Date(1547989200000L);
      cal.setTime(activityTestRule.getActivity().prevUseDate);
      cal2.setTime(d);
      sleep();
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), cal2.get(Calendar.DAY_OF_YEAR));
      Log.e(TAG, "Test 3 completed");

      //Invalid date (occurs before prior use date); Last clean should stay as January 20, 2019
      recordYesActivity(2019, 1, 19);
      cal.setTime(activityTestRule.getActivity().prevUseDate);
      sleep();
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), cal2.get(Calendar.DAY_OF_YEAR));
      Log.e(TAG, "Test 4 completed");

      //Invalid date (occurs after the current date); Last clean should stay as January 20, 2019
      Calendar tomorrow = Calendar.getInstance();
      Log.e(TAG, "today: " +tomorrow.getTime().toString());
      tomorrow.add(Calendar.DAY_OF_MONTH, 1);
      Log.e(TAG, "tomorrow: "+tomorrow.getTime().toString());
      recordYesActivity(tomorrow.get(Calendar.YEAR), tomorrow.get(Calendar.MONTH)+1, tomorrow.get(Calendar.DAY_OF_MONTH));
      cal.setTime(activityTestRule.getActivity().prevUseDate);
      sleep();
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), cal2.get(Calendar.DAY_OF_YEAR));
      Log.e(TAG, "All tests completed");
    } catch (Exception e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Pauses the thread for 1 second
   */
  private void sleep(){
    try{
      Thread.sleep(1000);
    }
    catch(InterruptedException e){
      Thread.currentThread().interrupt();
    }
  }

  /**
   * Hits the yes button then records a date using the Date Picker
   * @param year The year being selected
   * @param month The month being selected
   * @param day The day being selected
   */
  private void recordYesActivity(final int year, int month, final int day){
    onView(withId(R.id.report_use_yes)).perform(click());
    Log.e(TAG, "Year: "+year +" Month: " +month +" Day: "+day);
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName()))).perform(
        PickerActions.setDate(year, month, day));
    onView(withId(android.R.id.button1)).perform(click());
  }


  /**
   * Observer for retrieving the "Last report" Date
   */
  private Observer<Date> reportObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
      try{
        prevReportDay = date;
      }
      catch(NullPointerException e){
        e.printStackTrace();
      }
    }
  };

  private final Calendar today = Calendar.getInstance();
  private final Date initialLogDay = new Date(1546318800000L); //January 1st, 2019
  private Date prevReportDay;
  private DemographicDataViewModel viewModel;
  private final String TAG = "RepUseActivityTest";
}

