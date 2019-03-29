package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.*;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the Medication Choice DAO
 */
@RunWith(AndroidJUnit4.class)
public class MedicationChoiceDAOTest {

  @Rule
  public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  /**
   * Creates the temporary test database.
   */
  @Before
  public void createDb() {

    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Performs several tests involving the Medication Choice DAO Tests inserting a medication choice
   * into the database and checks that the values match
   */


  @Test
  public void insertMedicationTest() {
    final MedicationChoiceEntity mc = new MedicationChoiceEntity();
    mc.medication = "this is a test";
    db.medicationChoiceDAO().insertMedication(mc);

    //check that db is not empty
    assertNotNull(db);

    //check that entered value matched the entered test data
    try {
      assertEquals("this is a test",
          LiveDataTestUtility
              .getNestedLiveDataObj(db.medicationChoiceDAO().getMedication()).medication);
    } catch (InterruptedException e) {
      Log.v(TAG, e.toString());
    }
  }

  private VolitionDatabase db;
  private static final String TAG = "MedicationChoiceDAOTest";

}