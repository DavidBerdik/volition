package com.recoveryenhancementsolutions.volition;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanActivityTest {

  @Rule
  public ActivityTestRule<TreatmentPlanActivity> activityTestRule = new ActivityTestRule<>(
      TreatmentPlanActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary database for testing
   */
  @Before
  public void loadViewModel() {
    //Set the ViewModel to use a test database instead of the app's real database
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();

    //Fill in supplementary database entries
    final MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.medication = "ABSTAIN";
    db.medicationChoiceDAO().insertMedication(medicationChoiceEntity);

    final QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
    questionnaireEntity.setSeverityLevel("MODERATE");
    db.questionnaireDao().insertQuestionnaire(questionnaireEntity);

    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(TreatmentPlanViewModel.class);

    viewModel.setTestDatabase(db);
  }

  /**
   * Closes the temporary database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Test to ensure all data displayed on screen is accurate with database
   */
  @Test
  public void treatmentPlanActivityTest() {

    // Allow the database one second to update.
    try {
      Thread.sleep(2000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    /**
     * Verifies textview for couselingview
     */
    ViewInteraction textView = onView(
        allOf(withId(R.id.counselingView), withText("3"), isDisplayed()));
    textView.check(matches(withText("3")));

    /**
     * Verifies textview for medManagementView
     */
    ViewInteraction textView2 = onView(
        allOf(withId(R.id.medManagementView), withText("2"), isDisplayed()));
    textView2.check(matches(withText("2")));

    /**
     * Verifies textview for supportMeetingView
     */
    ViewInteraction textView3 = onView(
        allOf(withId(R.id.supportMeetingView), withText("3"), isDisplayed()));
    textView3.check(matches(withText("3")));

    /**
     * Verifies textview for outcomeMeasureView
     */
    ViewInteraction textView4 = onView(
        allOf(withId(R.id.outcomeMeasureView), withText("3"), isDisplayed()));
    textView4.check(matches(withText("3")));

    /**
     * Verifies textview for lessonView
     */
    ViewInteraction textView5 = onView(
        allOf(withId(R.id.lessonView), withText("2"), isDisplayed()));
    textView5.check(matches(withText("2")));

    /**
     * Verifies textview for treatmentEffectivenessView
     */
    ViewInteraction textView6 = onView(
        allOf(withId(R.id.treatmentEffectiveView), withText("1"), isDisplayed()));
    textView6.check(matches(withText("1")));

    /**
     * Verifies textview for timeTrackingView
     */
    ViewInteraction textView7 = onView(
        allOf(withId(R.id.timeTrackingView), withText("2"), isDisplayed()));
    textView7.check(matches(withText("2")));

    /**
     * Verifies textview for readingResponceView
     */
    ViewInteraction textView8 = onView(
        allOf(withId(R.id.readingResponseView), withText("2"), isDisplayed()));
    textView8.check(matches(withText("2")));
  }

  private TreatmentPlanViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "TreatmentPlanActivityTest";
}
