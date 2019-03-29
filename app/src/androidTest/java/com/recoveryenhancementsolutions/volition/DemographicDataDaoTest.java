package com.recoveryenhancementsolutions.volition;

//import android.arch.core.executor.testing.InstantTaskExecutorRule;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;

@RunWith(AndroidJUnit4.class)
public class DemographicDataDaoTest {
  @Before
  /*
   * prepares test by creating the database
   */
  public void createDb() {
    final Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class)
        .allowMainThreadQueries().build();
    demographicDataDAO = db.demographicDataDao();
  }

  @After
  /*
   * Closes the database
   */
  public void closeDb() {
    db.close();
  }

  @Test
  /*
   * Runs the database test with several inserts and queries with the use of assertions
   */
  public void testDemographicDataDao() {
    DemographicDataEntity patient = new DemographicDataEntity();
    patient.setAge(15);
    patient.setPatientName("Bob");
    patient.setDisorderAlcohol(true);
    patient.setUseAlcohol(true);
    demographicDataDAO.insertDemographicInfo(patient);

    Assert.assertEquals(15, demographicDataDAO.queryPatientAge());
    Assert.assertNotEquals(51, demographicDataDAO.queryPatientAge());
    Assert.assertFalse(demographicDataDAO.queryIsUsingBenzo());
    Assert.assertTrue(demographicDataDAO.queryIsUsingAlcohol());
  }
  private DemographicDataDAO demographicDataDAO;
  private VolitionDatabase db;
}