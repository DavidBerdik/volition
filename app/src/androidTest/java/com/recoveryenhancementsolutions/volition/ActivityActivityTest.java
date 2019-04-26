package com.recoveryenhancementsolutions.volition;

import static android.support.constraint.Constraints.TAG;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.intent.Intents;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.EspressoTestUtility;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;


public class ActivityActivityTest {

  /**
   * Test that the intents are working correctly for each button Probably an espresso test
   */


  @Rule
  public ActivityTestRule<ActivityActivity> activityTestRule = new ActivityTestRule<>(
      ActivityActivity.class);

  /**
   * Creates a testing environment to be used to the HomeActivity class with a test database.
   */
  @Before
  public void loadTestEnvironment() {
    // Create a test database instead of the app's real database.
    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();

    // Sets up some entry data. Current data TEA should be green, Lessons should be red, Report use should be green, journal should be red, daily wellness should be green
    treatmentPlanEntity = new TreatmentPlanEntity();
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(5);
    treatmentPlanEntity.setNumLessons(5);
    treatmentPlanEntity.setNumTimeTracking(5);
    treatmentPlanEntity.setNumReadingResponse(5);
    treatmentPlanEntity.setNumOutcomeMeasures(5);

    TreatmentExperienceAssessmentActivity.numberCompleted = 10;
    LessonActivity.numberCompleted = 1;
    ReportUseActivity.numberCompleted = 10;
    JournalActivity.numberCompleted = 1;
    DailyWellnessActivity.numberCompleted = 10;

    // Tell the activity to use the testing database.
    activityTestRule.getActivity().onCreateTest(db);
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
    db.treatmentPlanDao().insertTreatmentPlanEntity(treatmentPlanEntity);
  }

  /**
   * Test to determine if the methodology being used in ActivityActivity is correct
   */
  @Test
  public void activityActivityTest() {
    // Allow the database one second to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }
    // Fetching the data...
    try {
      assertEquals(treatmentPlanEntity.getNumTreatmentEffectivenessAssessment(),
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getTreatmentPlan())
              .getNumTreatmentEffectivenessAssessment());
    } catch (final InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  /**
   * Declares a required initialization that allows Espresso to check for intent activity.
   */
  @Before
  public void declareIntent() {
    Intents.init();
  }

  /**
   * Releases a required initialization that allows Espresso to check for intent activity.
   */
  @After
  public void releaseIntent() {
    Intents.release();
  }

  /**
   * Test that tests each button in ActivityActivity
   */
  @Test
  public void ActivityActivityTest_Multiple() {
    // Click on the TEA button
    onView(withId(R.id.TEA)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the TreatmentExperienceAssessmentActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        TreatmentExperienceAssessmentActivity.class.getName());

    // Click the back button to test the other buttons
    Espresso.pressBack();

    // Click on the Lesson button
    onView(withId(R.id.Lesson)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the LessonActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        LessonActivity.class.getName());

    // Click the back button to test the other buttons
    Espresso.pressBack();

    // Click on the Journal button
    onView(withId(R.id.Journal)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the JournalActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        JournalActivity.class.getName());

    // Click the back button to test the other buttons
    Espresso.pressBack();

    // Click on the EDU button
    onView(withId(R.id.Edu)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the EDUActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        EDUActivity.class.getName());

    // Click the back button to test the other buttons
    Espresso.pressBack();

    // Click on the DailyWellness button
    onView(withId(R.id.DailyWellness)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the DailyWellnessActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        DailyWellnessActivity.class.getName());

    // Click the back button to test the other buttons
    Espresso.pressBack();

    // Click on the CleanTracker button
    onView(withId(R.id.CleanTracker)).perform(click());

    // Allow the slower devices/emulators to update.
    try {
      Thread.sleep(1000);
    } catch (InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    // Check that we're on the ReportUseActivity class.
    assertEquals(EspressoTestUtility.getCurrentActivity().getClass().getName(),
        ReportUseActivity.class.getName());
  }

  private TreatmentPlanEntity treatmentPlanEntity;
  private TreatmentPlanViewModel viewModel;
}


