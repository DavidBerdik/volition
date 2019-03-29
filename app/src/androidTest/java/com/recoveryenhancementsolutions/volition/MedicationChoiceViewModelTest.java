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
 * Unit test for the Medication Choice Activity View Model.
 */

@RunWith(AndroidJUnit4.class)
public class MedicationChoiceViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary, in-memory database for testing.
   */

  @Before
  public void loadViewModel() {
    // Load the ViewModel
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(MedicationChoiceViewModel.class);

    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    viewModel.setTestDatabase(db);

    final MedicationChoiceEntity medication = new MedicationChoiceEntity();
    medication.insertMed("Abstain");
    viewModel.insertMedication(medication);
  }

  /**
   * Performs a test with the Medication Choice View Model
   */
  @Test
  public void testMedicationChoiceViewModel() {

    try {
      Thread.sleep(1000);
      assertEquals("Abstain",
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getMedication()).medication);
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }

  private MedicationChoiceViewModel viewModel;
  private static final String TAG = "MedicationChoiceViewModelTest";
}
