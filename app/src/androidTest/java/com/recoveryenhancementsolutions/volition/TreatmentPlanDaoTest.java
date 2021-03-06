package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Unit test for the Treatment Plan DAO
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanDaoTest {

  @Rule
  public final InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  /**
   * Creates the temporary test database
   */
  @Before
  public void createDb() {
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();
    treatmentPlanDao = db.treatmentPlanDao();
  }

  /**
   * Closes the temporary test database
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Performs several tests involving the Treatment Plan DAO
   */
  @Test
  public void testTreatmentPlanDao() throws Exception {
    //Create 1 treatment plan entities
    final TreatmentPlanEntity treatmentPlanEntity = new TreatmentPlanEntity();

    //Set values for entity
    treatmentPlanEntity.setNumCounseling(1);
    treatmentPlanEntity.setNumSupportMeeting(2);
    treatmentPlanEntity.setNumLessons(3);
    treatmentPlanEntity.setNumTreatmentEffectivenessAssessment(4);
    treatmentPlanEntity.setNumOutcomeMeasures(5);
    treatmentPlanEntity.setNumTimeTracking(6);
    treatmentPlanEntity.setNumReadingResponse(7);
    treatmentPlanEntity.setNumMedManagement(8);
    treatmentPlanEntity.setMedManagementMonthly();
    treatmentPlanEntity.setOutcomeMeasureDaily();

    //Insert the entity into the database
    treatmentPlanDao.insertTreatmentPlanEntity(treatmentPlanEntity);

    //Check that the database is not null.
    assertNotNull(db);

    //Check if treatment plan entity has the correct values
    assertEquals(1, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumCounseling());
    assertEquals(2, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumSupportMeeting());
    assertEquals(4, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumTreatmentEffectivenessAssessment());
    assertEquals(3, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumLessons());
    assertEquals(8, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumMedManagement());
    assertEquals(5, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumOutcomeMeasures());
    assertEquals(7, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumReadingResponse());
    assertEquals(6, LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getNumTimeTracking());
    assertEquals("MONTHLY", LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getMedManagementFrequency());
    assertEquals("DAILY", LiveDataTestUtility.getNestedLiveDataObj(treatmentPlanDao
        .getTreatmentPlan()).getOutcomeMeasureFrequency());
  }

  private TreatmentPlanDao treatmentPlanDao;
  private VolitionDatabase db;
}
