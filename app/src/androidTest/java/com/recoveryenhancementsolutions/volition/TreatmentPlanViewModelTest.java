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
    public void loadViewModel(){
        viewModel = ViewModelProviders.of(activityTestRule.getActivity()).get(
                TreatmentPlanViewModel.class);

        //Set the ViewModel to use a test database instead of the app's real database
        final Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
                .build();

        // Load the LiveData test utility.
        liveDataTest = new LiveDataTestUtility();
    }

    /**
     * Performs several test involving the Treatment Plan ViewModel
     */
    @Test
    public void testTreatmentPlanViewModel(){
        //Fill in supplementary database entries
        MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
        medicationChoiceEntity.setMedication("ABSTAIN");
        db.medicationChoiceDao().insertMedication(medicationChoiceEntity);

        QuestionnaireEntity questionnaireEntity = new QuestionnaireEntity();
        questionnaireEntity.setSeverityLevel("MODERATE");
        db.questionnaireDao().insertQuestionnaire(questionnaireEntity);

        //Switch the viewModel's db to the test db. Doing this also forces viewModel to create a new
        //treatment plan if one did not previously exist.
        viewModel.setTestDatabase(db);

        viewModel.generateTreatmentPlan();

        //Assert that only one treatment plan was created
            assertEquals(1, db.treatmentPlanDao().getNumTreatmentPlans().intValue());


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
        }catch (InterruptedException e){
            Log.e(TAG, Log.getStackTraceString(e));
        }

        //Update treatment plan data in the viewModel
        viewModel.treatmentPlan.setNumCounseling(10);
        viewModel.treatmentPlan.setNumMedManagement(10);
        viewModel.treatmentPlan.setNumReadingResponse(10);
        viewModel.treatmentPlan.setNumTimeTracking(10);
        viewModel.treatmentPlan.setNumOutcomeMeasures(10);
        viewModel.treatmentPlan.setNumTreatmentEffectivenessAssessment(10);
        viewModel.treatmentPlan.setNumLessons(10);
        viewModel.treatmentPlan.setNumSupportMeeting(10);
        viewModel.treatmentPlan.setMedManagementWeekly();
        viewModel.treatmentPlan.setOutcomeMeasureWeekly();

        //Test updateDb method of viewModel
        viewModel.updateDb();

        //Check if the values of the db's treatment plan successfully updated.
        try {
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumCounseling());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumSupportMeeting());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumLessons());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumTreatmentEffectivenessAssessment());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumOutcomeMeasures());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumTimeTracking());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumReadingResponse());
            assertEquals(10, liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getNumMedManagement());
            assertEquals("WEEKLY", liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getMedManagementFrequency());
            assertEquals("WEEKLY", liveDataTest.getNestedLiveDataObj(db.treatmentPlanDao()
                    .loadTreatmentPlan()).getOutcomeMeasureFrequency());
        }catch (InterruptedException e){
            Log.e(TAG, Log.getStackTraceString(e));
        }
    }

    private TreatmentPlanViewModel viewModel;
    private LiveDataTestUtility liveDataTest;
    private VolitionDatabase db;
    private static final String TAG = "TreatmentPlanViewModelTest";
}
