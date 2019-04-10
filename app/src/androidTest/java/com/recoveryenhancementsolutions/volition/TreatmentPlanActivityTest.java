package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit text for TreatmentPlanActivity
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanActivityTest {

  @Rule
  public final ActivityTestRule<TreatmentPlanActivity> activityTestRule = new ActivityTestRule<>(
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

    TreatmentPlanEntity treatmentPlanEntity = new TreatmentPlanEntity();
    treatmentPlanEntity.setNumLessons(3);

    activityTestRule.getActivity().onCreateTest(db);

    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
  }

  @Test
  public void testTreatmentPlanViewModel() {
    // Allow the database one second to update.

    //New Text Data

  }

  private TreatmentPlanViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "TreatmentPlanViewModelTest";
}