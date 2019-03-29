package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.*;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import java.util.Date;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the HomeActivity class. Interacts with several buttons and confirms their output.
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class HomeActivityTest {

  @Rule
  public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Creates a testing environment to be used to the HomeActivity class with a test database.
   */
  @Before
  public void loadTestEnvironment() {
    // Create a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();

    // Sets up some entry data.
    demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Patient");
    demographicDataEntity
        .setLastClean(new Date(new Date().getTime() - DAYS_CLEAN * 24 * 60 * 60 * 1000));

    // Tell the activity to use the testing database.
    activityTestRule.getActivity().onCreateTest(db);

    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(DemographicDataViewModel.class);
    viewModel.setTestDatabase(db);
    viewModel.insertDemographicData(demographicDataEntity);
  }

  /**
   * Tests that the activity is fetching the existing data and displaying it.
   */
  @Test
  public void homeActivityTest_ViewModel() {
    // Fetching the data...
    try {
      assertEquals(demographicDataEntity.getLastClean(),
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()));
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Converting the data...
    try {
      assertEquals(DAYS_CLEAN, DateConverter
          .daysBetween(LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()),
              new Date()));
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Allow the UI one second to update.
    try {
      Thread.sleep(1000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Updating the TextView...
    assertEquals("Days Clean: " + DAYS_CLEAN, activityTestRule.getActivity().getDaysCleanText());
  }

  private DemographicDataEntity demographicDataEntity;
  private DemographicDataViewModel viewModel;
  private final int DAYS_CLEAN = 5;
  private static final String TAG = "HomeActivityTest";
}