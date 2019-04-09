package com.recoveryenhancementsolutions.volition;


import static android.support.constraint.Constraints.TAG;
import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
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

    // Sets up some entry data. With this testing data, TEA should be marked incomplete, Lessons should be marked complete
    treatmentPlanEntity = new TreatmentPlanEntity();
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(5);
    treatmentPlanEntity.setNumLessons(1);
    TreatmentAssessmentActivity.numberCompleted = 2;
    LessonActivity.numberCompleted=1;

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

  private TreatmentPlanEntity treatmentPlanEntity;
  private TreatmentPlanViewModel viewModel;

}
