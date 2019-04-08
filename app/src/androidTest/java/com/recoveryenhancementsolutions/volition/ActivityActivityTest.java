package com.recoveryenhancementsolutions.volition;


import android.arch.lifecycle.ViewModelProviders;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import java.util.Date;
import org.junit.Before;
import org.junit.Rule;

public class ActivityActivityTest {
  /**
   * Test that the intents are working correctly for each button
   * Probably an espresso test
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

    // Sets up some entry data.
    treatmentPlanEntity = new TreatmentPlanEntity();
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(1);

    // Tell the activity to use the testing database.
    activityTestRule.getActivity().onCreateTest(db);
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(TreatmentPlanViewModel.class);
    viewModel.setTestDatabase(db);
    db.treatmentPlanDao().insertTreatmentPlanEntity(treatmentPlanEntity);
  }

  private TreatmentPlanEntity treatmentPlanEntity;
  private TreatmentPlanViewModel viewModel;

}
