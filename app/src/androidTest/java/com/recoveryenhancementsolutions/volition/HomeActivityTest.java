package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import java.util.Date;
import java.util.Locale;
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
   * Loads the ViewModel and sets it to use a temporary, in-memory database for testing.
   */
  @Before
  public void loadViewModel() {
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(DaysCleanViewModel.class);

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
   * Tests that the LiveData information is being pulled at least once.
   */
  @Test
  public void homeActivityTest_Single() {
    // Check to make sure we load the default state
    onView(withId(R.id.clean)).check(
        matches(withText(String.format(Locale.getDefault(), "%s", R.string.home_clean))));

    DemographicDataEntity demographicDataEntity = new DemographicDataEntity();
    demographicDataEntity.setPatientName("Example Name");
    demographicDataEntity.setLastClean(new Date(new Date().getTime() - 8 * 24 * 60 * 60 * 1000));

    // Sets a new demographic data object with a clean date of 8 days ago.
    db.demographicDataDao().insertDemographicInfo(demographicDataEntity);

    // Check to make sure it works properly.
    onView(withId(R.id.clean)).check(
        matches(withText(String.format(Locale.getDefault(), "%s %d", R.string.home_clean, 8))));
  }

  private DaysCleanViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "HomeActivityTest";
}
