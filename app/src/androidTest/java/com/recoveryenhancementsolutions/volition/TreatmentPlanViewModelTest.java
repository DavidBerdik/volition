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
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the "Treatment Plan" viewModel
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

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
  public void closeDb(){db.close();}

  /**
   * Performs several test involving the Treatment Plan ViewModel
   */
  @Test
  public void testTreatmentPlanViewModel() {
    // Allow the database one second to update.
    try {
      Thread.sleep(1000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    TreatmentPlanEntity treatmentPlanEntity = new TreatmentPlanEntity();
    treatmentPlanEntity.setNumCounseling(1);
    treatmentPlanEntity.setNumSupportMeeting(1);
    treatmentPlanEntity.setNumLessons(1);
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(1);
    treatmentPlanEntity.setNumOutcomeMeasures(1);
    treatmentPlanEntity.setNumTimeTracking(1);
    treatmentPlanEntity.setNumReadingResponse(1);
    treatmentPlanEntity.setNumMedManagement(0);
    treatmentPlanEntity.setMedManagementMonthly();
    treatmentPlanEntity.setOutcomeMeasureWeekly();
    treatmentPlanEntity.setId(1);
    viewModel.insertTreatmentPlan(treatmentPlanEntity);

    // Allow the database one second to update.
    try {
      Thread.sleep(1000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    //Creates a test treatmentPlan to check data
    TreatmentPlanEntity testTreatmentPlan = new TreatmentPlanEntity();
    try {
      testTreatmentPlan = LiveDataTestUtility
          .getNestedLiveDataObj(viewModel.getTreatmentPlan());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    //Check the values of the treatment plan. Should match the moderate abstinence plan.
    assertEquals(1, testTreatmentPlan.getNumCounseling());
    assertEquals(1, testTreatmentPlan.getNumSupportMeeting());
    assertEquals(1, testTreatmentPlan.getNumLessons());
    assertEquals(1, testTreatmentPlan.getNumTreatmentEffectivenessAssessment());
    assertEquals(1, testTreatmentPlan.getNumOutcomeMeasures());
    assertEquals(1, testTreatmentPlan.getNumTimeTracking());
    assertEquals(1, testTreatmentPlan.getNumReadingResponse());
    assertEquals(0, testTreatmentPlan.getNumMedManagement());
    assertEquals("MONTHLY", testTreatmentPlan.getMedManagementFrequency());
    assertEquals("WEEKLY", testTreatmentPlan.getOutcomeMeasureFrequency());

    //Test updating the treatmentPlan
    treatmentPlanEntity.setNumCounseling(5);
    treatmentPlanEntity.setNumSupportMeeting(5);
    treatmentPlanEntity.setNumLessons(3);
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(1);
    treatmentPlanEntity.setNumOutcomeMeasures(5);
    treatmentPlanEntity.setNumTimeTracking(5);
    treatmentPlanEntity.setNumReadingResponse(3);
    treatmentPlanEntity.setMedManagementWeekly();
    treatmentPlanEntity.setOutcomeMeasureDaily();
    viewModel.updateTreatmentPlan(treatmentPlanEntity);

    // Allow the database one second to update.
    try {
      Thread.sleep(1000);
    } catch(InterruptedException ex) {
      Thread.currentThread().interrupt();
    }

    //Updates testTreatmentPlan
    try {
      testTreatmentPlan = LiveDataTestUtility
          .getNestedLiveDataObj(db.treatmentPlanDao().getTreatmentPlan());
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }

    assertEquals(1, testTreatmentPlan.getNumCounseling());
    assertEquals(1, testTreatmentPlan.getNumSupportMeeting());
    assertEquals(1, testTreatmentPlan.getNumLessons());
    assertEquals(1, testTreatmentPlan.getNumTreatmentEffectivenessAssessment());
    assertEquals(1, testTreatmentPlan.getNumOutcomeMeasures());
    assertEquals(1, testTreatmentPlan.getNumTimeTracking());
    assertEquals(1, testTreatmentPlan.getNumReadingResponse());
    assertEquals(0, testTreatmentPlan.getNumMedManagement());
    assertEquals("MONTHLY", testTreatmentPlan.getMedManagementFrequency());
    assertEquals("WEEKLY", testTreatmentPlan.getOutcomeMeasureFrequency());

  }

  private TreatmentPlanViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "TreatmentPlanViewModelTest";
}
