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
import org.junit.After;
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
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();

    // Sets up some entry data.
    demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Patient");
    demographicDataEntity.setLastClean(new Date(new Date().getTime() - 10 * 24 * 60 * 60 * 1000));

    // Tell the activity to use the testing database.
    activityTestRule.getActivity().onCreateTest(db);

    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(DemographicDataViewModel.class);
    viewModel.setTestDatabase(db);
    viewModel.insertDemographicData(demographicDataEntity);

    activityTestRule.getActivity().onCreateTest(db);
  }

  /**
   * Cleans up the testing environment.
   */
  @After
  public void cleanTestEnvironment() {
    db.close();
  }

  /**
   * Tests that the DemographicDataViewModel is functioning once.
   */
  @Test
  public void homeActivityTest_SingleUpdate() {
    try {
      assertEquals(demographicDataEntity.getLastClean(),
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getLastCleanDate()));
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    assertEquals("Days Clean: 10", activityTestRule.getActivity().getDaysCleanText());
  }

  private DemographicDataEntity demographicDataEntity;
  private DemographicDataViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "HomeActivityTest";
}