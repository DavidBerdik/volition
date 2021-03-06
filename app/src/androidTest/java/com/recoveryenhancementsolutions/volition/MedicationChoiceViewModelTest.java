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

  //Will be replaced with the correct activity when it is created
  @Rule
  public final ActivityTestRule<MedicationChoiceActivity> activityTestRule = new ActivityTestRule<>(
      MedicationChoiceActivity.class);

  /**
   * Loads the ViewModel and sets it to use a temporary, in-memory database for testing.
   */

  @Before
  public void loadViewModel() {
    // Load the ViewModel
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(MedicationChoiceViewModel.class);
    viewModel.setSeverityLevel("MODERATE");

    final Context context = InstrumentationRegistry.getTargetContext();
    final VolitionDatabase db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    viewModel.setTestDatabase(db);

    final MedicationChoiceEntity medication = new MedicationChoiceEntity();
    medication.medication = "Abstain";
    medication.dosage = 3;
    medication.milligramsBuprenorphine = 2.6;
    medication.milligramsNaloxone = 0.8;
    medication.type = "sublingual";
    viewModel.setSeverityLevel("MODERATE");
    viewModel.insertMedication(medication);
    viewModel.updateDosage(medication);
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

    try {
      assertEquals(3,
          LiveDataTestUtility
              .getNestedLiveDataObj(viewModel.getDosage()).dosage);
      assertEquals("sublingual",
          LiveDataTestUtility
              .getNestedLiveDataObj(viewModel.getDosage()).type);
      assertEquals(0.8,
          LiveDataTestUtility
              .getNestedLiveDataObj(viewModel.getDosage()).milligramsNaloxone, 0.001);
      assertEquals(2.6,
          LiveDataTestUtility
              .getNestedLiveDataObj(viewModel.getDosage()).milligramsBuprenorphine, 0.001);
    } catch (InterruptedException e) {
      Log.v(TAG, e.toString());
    }
  }

  private MedicationChoiceViewModel viewModel;
  private static final String TAG = "MedicationChoiceViewModelTest";
}
