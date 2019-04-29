package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.Rule;
import org.junit.Test;

/**
 * Unit test for TempDatabaseInitiazlizer
 */
public class UserActivityMockDBTest {


  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Test to verify that the Db is being populated with the test data and that when queried, the Db
   * returns the correct test data
   */
  @Test
  public void populateDbWithTestData() {
    final UserActivityViewModel viewModel;
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(UserActivityViewModel.class);
    UserActivityMockDB testDb = new UserActivityMockDB(viewModel.getApplication(),
        context);

    //set the database in the userActivityViewModel class
    viewModel.setTestDatabase(testDb.getDb());
    //populate with test data
    testDb.populateDbWithTestData();

    // Allow the app time to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Query the database for all entries and check that the returned list contains 5 entries.
    try {
      assertEquals(5, LiveDataTestUtility.getNestedLiveDataObj(viewModel.getAllActivities()).size());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Query the database for the activity with ID 3 and check that it matches the original.
    try {
      assertEquals(3, LiveDataTestUtility.getNestedLiveDataObj(viewModel.getActivitiesByID(3)).getId());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Query the database for the activity with date August 13, 2017 and check that it matches the
    // original.
    try {
      assertEquals(2,
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getActivitiesByDate(2017, 8, 13)).get(0)
              .getId());
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

  }
  private final Context context = InstrumentationRegistry.getTargetContext();
  private static final String TAG = "UserActivityMockDBTest";

}