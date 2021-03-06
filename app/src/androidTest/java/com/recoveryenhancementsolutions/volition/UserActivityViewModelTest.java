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
import java.util.Calendar;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "User Activity History" ViewModel.
 */
@RunWith(AndroidJUnit4.class)
public class UserActivityViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary, in-memory database for testing.
   */
  @Before
  public void loadViewModel() {
    // Load the ViewModel
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(UserActivityViewModel.class);

    // Set the ViewModel to use a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    viewModel.setTestDatabase(db);
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Performs several tests involving the "User Activity History" ViewModel
   */
  @Test
  public void testUserActivityHistoryViewModel() {
    // Create 5 User Activity Dates
    final int[] userActivityYear = {2019, 2017, 2001, Calendar.getInstance().get(Calendar.YEAR),
        2038};
    final int[] userActivityMonth = {3, 8, 10, 9, 1};
    final int[] userActivityDay = {15, 13, 9, 1, 19};

    // Create 5 User Activity Descriptions
    final String[] userActivityDesc = {"Act 1", "Act 2", "Act 3", "Act 4", "Act 5"};

    // Create 5 User Activity Notes
    final String[] userActivityNotes = {"This is a", "test of the", "emergency", "broadcast",
        "system."};

    // Insert the entities.
    for (int x = 0; x < 5; x++) {
      viewModel.insertActivity(userActivityYear[x], userActivityMonth[x], userActivityDay[x],
          userActivityDesc[x], userActivityNotes[x]);
    }

    // Allow the app time to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Query the database for all entries and check that the returned list contains 5 entries.
    try {
      assertEquals(5,
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getAllActivities()).size());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Query the database for the activity with ID 3 and check that it matches the original.
    try {
      assertEquals(3,
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getActivitiesByID(3)).getId());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Query the database for the activity with date August 13, 2017 and check that it matches the
    // original.
    try {
      assertEquals(2,
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getActivitiesByDate(2017, 8, 13))
              .get(0).getId());
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Query the database for activities that take place in September and check that it has the
    // correct ID.
    try {
      assertEquals(4,
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getActivitiesByMonth(9)).get(0)
              .getId());
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  private UserActivityViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "UserActivityViewModelTest";
}