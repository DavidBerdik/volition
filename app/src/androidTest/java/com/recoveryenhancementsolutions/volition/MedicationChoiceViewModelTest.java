package com.recoveryenhancementsolutions.volition;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;

import android.arch.lifecycle.LiveData;
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

@RunWith(AndroidJUnit4.class)
public class MedicationChoiceViewModelTest {

  @Rule
  public final ActivityTestRule<HomeActivity> activityTestRule = new ActivityTestRule<>(
      HomeActivity.class);

  @Before
  public void loadViewModel() {
    // Load the ViewModel
    viewModel = ViewModelProviders.of(activityTestRule.getActivity())
        .get(MedicationChoiceViewModel.class);

    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    viewModel.setTestDatabase(db);

    MedicationChoiceEntity medication = new MedicationChoiceEntity();
    medication.insertMed("Buprenorphine");
    viewModel.insertMedication(medication);
  }

  @After
  public void closeDb() {
    db.close();
  }

  @Test
  public void testMedicationChoiceViewModel() {

    try {
      assertEquals("Buprenorphine",
          LiveDataTestUtility.getNestedLiveDataObj(viewModel.getMedication()));
    } catch (InterruptedException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
  }
  private MedicationChoiceViewModel viewModel;
  private VolitionDatabase db;
  private static final String TAG = "MedicationChoiceViewModelTest";
}
