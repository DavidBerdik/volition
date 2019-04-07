package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
 * Unit test for the Medication Dosage Dao
 */
@RunWith(AndroidJUnit4.class)
public class MedicationDosageDaoTest {

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
   * Performs several tests involving the Medication Dosage DAO Tests inserting a medication dose
   * into the database and checks that the values match
   */


  @Test
  public void insertDosageTest() {
    final MedicationDosageEntity dc = new MedicationDosageEntity();
    dc.dosage = 3;
    db.medicationDosageDao().insertDosage(dc);

    //check that db is not empty
    assertNotNull(db);

    //check that entered value matched the entered test data
    try {
      assertEquals(3,
          LiveDataTestUtility
              .getNestedLiveDataObj(db.medicationDosageDao().getDosage()).dosage);
    } catch (InterruptedException e) {
      Log.v(TAG, e.toString());
    }
  }

  private VolitionDatabase db;
  private static final String TAG = "MedicationDosageDaoTest";

}