package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.arch.persistence.room.Room;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EditProfileActivityTest {

  @Rule
  public ActivityTestRule<EditProfileActivity> activityTestRule = new ActivityTestRule<>(
      EditProfileActivity.class);

  /**
   * Creates a temporary, in-memory database to use for testing EditProfileActivity.
   */
  @Before
  public void createTestEnvironment() {
    // Create a test database to use in place of the app's real database.
    final VolitionDatabase db = Room
        .inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), VolitionDatabase.class)
        .allowMainThreadQueries().build();

    // Fill the database with test information.
    DemographicDataEntity data = new DemographicDataEntity();
    data.setPatientName("John Doe");
    data.setDateOfBirth(1970, 1, 1);
    data.setGender("Male");
    data.setLastClean(2038, 1, 19);
    db.demographicDataDao().insertDemographicInfo(data);

    // Set test mode on the activity.
    activityTestRule.getActivity().setTestMode(db);

    /*
     * Delay the execution of any tests for 1 second. This is done to prevent any tests from trying
     * to verify the content of the activity fields before the insertion is complete. There may be
     * a better way to handle this, but this is the best that I could come up with.
     */
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Test if the profile data is displayed properly in the activity.
   */
  @Test
  public void testProfileDisplay() {
    // Check that the patient name is John Doe.
    onView(withId(R.id.name)).check(matches(withText("John Doe")));

    // Check that the date of birth is Jan 1, 1970.
    onView(withId(R.id.date_of_birth)).check(matches(withText("Jan 1, 1970")));

    onView(withId(R.id.gender_spinner)).check(matches(withSpinnerText("Male")));

    // Check that the clean date is Jan 19, 2038.
    onView(withId(R.id.clean_date)).check(matches(withText("Jan 19, 2038")));
  }

  private static final String TAG = "EditProfileActivityTest";

}
