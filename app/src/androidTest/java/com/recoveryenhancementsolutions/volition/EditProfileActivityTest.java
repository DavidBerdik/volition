package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isChecked;
import static android.support.test.espresso.matcher.ViewMatchers.isNotChecked;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import android.widget.DatePicker;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import java.util.Calendar;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class EditProfileActivityTest {

  @Rule
  public final ActivityTestRule<CreateProfileActivity> activityTestRule = new ActivityTestRule<>(
      CreateProfileActivity.class, false, false);

  final private VolitionDatabase db = Room
      .inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), VolitionDatabase.class)
      .allowMainThreadQueries().build();

  /**
   * Creates a temporary, in-memory database to use for testing the edit profile activity.
   */
  @Before
  public void createTestEnvironment() {
    // Inject the "editMode" flag to set the activity to edit mode.
    final Intent i = new Intent();
    i.putExtra("editMode", true);

    // Fill the database with test information.
    final DemographicDataEntity data = new DemographicDataEntity();
    data.setPatientName("John Doe");
    data.setDateOfBirth(1970, 1, 1);
    data.setGender("Male");
    data.setPersonInRecovery(true);
    data.setUseOther("Caffeine/Java");
    data.setDisorderAlcohol(true);
    data.setLastClean(2038, 1, 19);
    db.demographicDataDao().insertDemographicInfo(data);

    // Launch the activity and then set it to test mode.
    activityTestRule.launchActivity(i);
    activityTestRule.getActivity().setTestMode(db);

    /*
     * Delay the execution of any tests for 1 second. This is done to prevent any tests from trying
     * to verify the content of the activity fields before the insertion is complete. There may be
     * a better way to handle this, but this is the best that I could come up with.
     */
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
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

    // Check that the gender is "Male."
    onView(withId(R.id.gender_spinner)).check(matches(withSpinnerText("Male")));

    // Check that the person type is "person in recovery."
    onView(withId(R.id.radioSupport)).check(matches(isNotChecked()));
    onView(withId(R.id.radioClient)).check(matches(isChecked()));

    // Check that the drug of choice is "other" and that the EditText for displaying the custom
    // drug type is "Caffeine/Java."
    onView(withId(R.id.radioHeroin)).check(matches(isNotChecked()));
    onView(withId(R.id.radioOpiates)).check(matches(isNotChecked()));
    onView(withId(R.id.radioAlcohol)).check(matches(isNotChecked()));
    onView(withId(R.id.radioCocaine)).check(matches(isNotChecked()));
    onView(withId(R.id.radioMarijuana)).check(matches(isNotChecked()));
    onView(withId(R.id.radioMeth)).check(matches(isNotChecked()));
    onView(withId(R.id.radioBen)).check(matches(isNotChecked()));
    onView(withId(R.id.radioTranquilizers)).check(matches(isNotChecked()));
    onView(withId(R.id.radioSedatives)).check(matches(isNotChecked()));
    onView(withId(R.id.radioInhalants)).check(matches(isNotChecked()));
    onView(withId(R.id.radioOther)).check(matches(isChecked()));
    onView(withId(R.id.enter_other)).check(matches(withText("Caffeine/Java")));

    // Check that the disorder type is "Alcohol Use Disorder."
    onView(withId(R.id.use_type_spinner)).check(matches(withSpinnerText("Alcohol Use Disorder")));

    // Check that the clean date is Jan 19, 2038.
    onView(withId(R.id.clean_date)).check(matches(withText("Jan 19, 2038")));
  }

  /**
   * Test if updating the profile works properly.
   */
  @Test
  public void testProfileUpdate() {
    // Set the user's name to Sarah Sample.
    onView(withId(R.id.name)).perform(scrollTo(), replaceText("Sarah Sample"));

    // Set the user's date of birth to March 14, 2015.
    onView(withId(R.id.date_of_birth)).perform(scrollTo(), click());
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
        .perform(PickerActions.setDate(2015, 3, 14));
    onView(withId(android.R.id.button1)).perform(click());

    // Set the user's gender to "Female."
    onView(withId(R.id.gender_spinner)).perform(scrollTo(), click());
    onData(allOf(is(instanceOf(String.class)), is("Female"))).perform(click());

    // Set the user type to "Family or Support Person."
    onView(withId(R.id.radioSupport)).perform(scrollTo(), click());

    // Set the Drug of Choice to "Marijuana."
    onView(withId(R.id.radioMarijuana)).perform(scrollTo(), click());
    onView(withId(R.id.enter_other)).perform(scrollTo(), replaceText(""));

    // Set the use disorder to "Opioid Use Disorder."
    onView(withId(R.id.use_type_spinner)).perform(scrollTo(), click());
    onData(allOf(is(instanceOf(String.class)), is("Opioid Use Disorder"))).perform(click());

    // Set the user's last use date to February 2, 2019.
    onView(withId(R.id.clean_date)).perform(scrollTo(), click());
    onView(withClassName(Matchers.equalTo(DatePicker.class.getName())))
        .perform(PickerActions.setDate(2019, 2, 2));
    onView(withId(android.R.id.button1)).perform(click());

    // Press the "Update Profile" button to insert the data in the database.
    onView(withId(R.id.record_button)).perform(scrollTo(), click());

    /*
     * Delay the execution of any tests for 1 second. This is done to prevent any tests from trying
     * to verify the content of the database before the insertion is complete. There may be
     * a better way to handle this, but this is the best that I could come up with.
     */
    try {
      Thread.sleep(1000);
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    // Check that the name has been updated.
    assertEquals("Sarah Sample", db.demographicDataDao().queryPatientName());

    // Check that the date of birth is March 14, 2015.
    Calendar dob = Calendar.getInstance();
    dob.setTime(db.demographicDataDao().queryDoB());
    assertEquals(2015, dob.get(Calendar.YEAR));
    assertEquals(2, dob.get(Calendar.MONTH));
    assertEquals(14, dob.get(Calendar.DAY_OF_MONTH));

    // Check that the gender is Female.
    assertEquals("Female", db.demographicDataDao().queryPatientGender());

    // Check that the person type is "Family or Support Person."
    assertFalse(db.demographicDataDao().queryIsInRecovery());

    // Check that the drug of choice is "Marijuana."
    assertTrue(db.demographicDataDao().queryIsUsingMarijuana());
    assertEquals("", db.demographicDataDao().queryOtherUsedDrugs());

    // Check that the substance use disorder is ""Opioid Use Disorder."
    assertTrue(db.demographicDataDao().queryIsHavingOpioidDisorder());

    // Check that the last use date is February 2, 2019.
    Calendar lastUse = Calendar.getInstance();
    try {
      lastUse.setTime(
          LiveDataTestUtility.getNestedLiveDataObj(db.demographicDataDao().queryLastCleanDate()));
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
    assertEquals(2019, lastUse.get(Calendar.YEAR));
    assertEquals(1, lastUse.get(Calendar.MONTH));
    assertEquals(2, lastUse.get(Calendar.DAY_OF_MONTH));
  }

  private static final String TAG = "EditProfileActivityTest";

}
