package com.recoveryenhancementsolutions.volition;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;
import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.core.AllOf.allOf;

import android.arch.persistence.room.Room;
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
public class CreateProfileActivityDatabaseTest {

  @Rule
  public final ActivityTestRule<ProfileActivity> activityTestRule = new ActivityTestRule<>(
      ProfileActivity.class);

  /**
   * Creates a temporary, in-memory database to use for testing the edit profile activity.
   */
  @Before
  public void createTestEnvironment() {
    // Set the activity to test mode.
    activityTestRule.getActivity().setTestMode(db);
  }

  /**
   * Test if creating the profile works properly.
   */
  @Test
  public void testProfileCreate() {
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

    // Check that the name has been updated.
    assertEquals("Sarah Sample", db.demographicDataDao().queryPatientName());

    // Check that the date of birth is March 14, 2015.
    final Calendar dob = Calendar.getInstance();
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

    // Check that the substance use disorder is ""Opioid Use Disorder."
    assertTrue(db.demographicDataDao().queryIsHavingOpioidDisorder());

    // Check that the last use date is February 2, 2019.
    final Calendar lastUse = Calendar.getInstance();
    try {
      lastUse.setTime(
          LiveDataTestUtility.getNestedLiveDataObj(db.demographicDataDao().queryLastCleanDate()));
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
    assertEquals(2019, lastUse.get(Calendar.YEAR));
    assertEquals(1, lastUse.get(Calendar.MONTH));
    assertEquals(2, lastUse.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Test that if the user enters other drug, the text box appears and the user can input
   * information into the database
   */
  @Test
  public void testOtherDrugUse() {
    onView(withId(R.id.radioOther)).perform(scrollTo(), click());
    onView(withId(R.id.enter_other)).perform(scrollTo(), replaceText("other drug"));
    onView(withId(R.id.record_button)).perform(scrollTo(), click());
    assertEquals("other drug", db.demographicDataDao().queryOtherUsedDrugs());
  }

  private static final String TAG = "CreateProfileActivityDatabaseTest";
  final private VolitionDatabase db = Room
      .inMemoryDatabaseBuilder(InstrumentationRegistry.getTargetContext(), VolitionDatabase.class)
      .allowMainThreadQueries().build();

}
