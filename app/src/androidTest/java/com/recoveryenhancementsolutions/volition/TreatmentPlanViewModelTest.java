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
    public ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
            HomeActivity.class);

    /**
     * Loads the ViewModel and sets it to use a temporary database for testing
     */
    @Before
    public void loadViewModel() {
        viewModel = ViewModelProviders.of(activityTestRule.getActivity())
                .get(TreatmentPlanViewModel.class);

        //Set the ViewModel to use a test database instead of the app's real database
        final Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
                .build();

        // Load the LiveData test utility.
        liveDataTest = new LiveDataTestUtility();

        //Fill in supplementary database entries
        MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
        medicationChoiceEntity.setMedication("ABSTAIN");
        db.medicationChoiceDao().insertMedication(medicationChoiceEntity);

        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setSeverityLevel("MODERATE");
        db.questionnaireDao().insertQuestionnaire(questionnaireEntity);
        viewModel.setTestDatabase(db);
        viewModel.generateTreatmentPlan();
        db.treatmentPlanDao().insertTreatmentPlanEntity(viewModel.treatmentPlan);
    }

    /**
     * Performs several test involving the Treatment Plan ViewModel
     */
    @Test
    public void testTreatmentPlanViewModel() {

        //Check the values of the treatment plan. Should match the moderate abstinence plan.
        try {
            assertEquals(3, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumCounseling());
            assertEquals(3, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumSupportMeeting());
            assertEquals(2, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumLessons());
            assertEquals(1, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumTreatmentEffectivenessAssessment());
            assertEquals(3, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumOutcomeMeasures());
            assertEquals(2, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumTimeTracking());
            assertEquals(2, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumReadingResponse());
            assertEquals(0, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumMedManagement());
            assertEquals("MONTHLY", liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getMedManagementFrequency());
            assertEquals("DAILY", liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getOutcomeMeasureFrequency());
        } catch (InterruptedException e) {
            Log.e(TAG, Log.getStackTraceString(e));
        }

    }

    private TreatmentPlanViewModel viewModel;
    private LiveDataTestUtility liveDataTest;
    private VolitionDatabase db;
    private static final String TAG = "TreatmentPlanViewModelTest";
}
