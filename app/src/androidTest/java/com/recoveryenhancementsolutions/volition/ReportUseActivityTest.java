package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Calendar;
import java.util.Date;

import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
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

    demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Client");
    //Assume that on Jan 1, the client hit "Yes"
    demographicDataEntity.setLastClean(initialLogDay, initialLogDay);
    db.demographicDataDao().insertDemographicInfo(demographicDataEntity);
    viewModel.getLastCleanDate().observe(activityTestRule.getActivity(), cleanObserver);
    viewModel.getLastReportDate().observe(activityTestRule.getActivity(), reportObserver);
  }

  /**
   * Tests if pressing the yes and no buttons correctly updates the database
   */
  @Test
  public void updateDatabaseTest() {
    try {
      Calendar cal = Calendar.getInstance();
      //Initial state: both the last clean and last report should be the initial day
      assertEquals(prevUseDay, initialLogDay);
      assertEquals(prevReportDay, initialLogDay);

      //click No: Last clean should be the same, last report should be updated
      onView(withId(R.id.report_use_no)).perform(click());
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastReportDate()));
      assertEquals(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()),
          initialLogDay);
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));

      //click Yes: Both Last clean and last report should be updated
      recordYesActivity();
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()));
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
      cal.setTime(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastReportDate()));
      assertEquals(cal.get(Calendar.DAY_OF_YEAR), today.get(Calendar.DAY_OF_YEAR));
    } catch (Exception e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Hits the yes button and then records a date using the Date Picker
   */
  public void recordYesActivity() {
    ViewInteraction appCompatButton = onView(
        allOf(withId(R.id.report_use_yes), withText("Yes"),
            childAtPosition(
                allOf(withId(R.id.container),
                    childAtPosition(
                        withId(android.R.id.content),
                        0)),
                0),
            isDisplayed()));
    appCompatButton.perform(click());

    ViewInteraction appCompatButton2 = onView(
        allOf(withId(android.R.id.button1), withText("OK"),
            childAtPosition(
                childAtPosition(
                    withClassName(is("android.widget.ScrollView")),
                    0),
                3)));
    appCompatButton2.perform(scrollTo(), click());
  }

  private static Matcher<View> childAtPosition(
      final Matcher<View> parentMatcher, final int position) {

    return new TypeSafeMatcher<View>() {
      @Override
      public void describeTo(Description description) {
        description.appendText("Child at position " + position + " in parent ");
        parentMatcher.describeTo(description);
      }

      @Override
      public boolean matchesSafely(View view) {
        ViewParent parent = view.getParent();
        return parent instanceof ViewGroup && parentMatcher.matches(parent)
            && view.equals(((ViewGroup) parent).getChildAt(position));
      }
    };
  }

  /**
   * Observer for retrieving the "Last clean" Date
   */
  private Observer<Date> cleanObserver = new Observer<Date>() {
    @Override
    public void onChanged(final Date date) {
      try {
        prevUseDay = date;
      }
      catch(NullPointerException e) {
        e.printStackTrace();
      }
    }
  };

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
  private Date prevUseDay, prevReportDay;
  private DemographicDataViewModel viewModel;
  private final String TAG = "RepUseActivityTest";
}

