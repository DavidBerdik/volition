package com.recoveryenhancementsolutions.volition;

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

import static org.junit.Assert.*;

/**
 * Unit test for the Treatment Plan DAO
 */
@RunWith(AndroidJUnit4.class)
public class TreatmentPlanDaoTest {
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    /**
     * Creates the temporary test database
     */
    @Before
    public void createDb(){
        liveDataTest = new LiveDataTestUtility();
        final Context context = InstrumentationRegistry.getTargetContext();
        db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
                .build();
        treatmentPlanDao = db.treatmentPlanDao();
    }

    /**
     * Closes the temporary test database
     */
    @After
    public void closeDb(){db.close();}

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
        assertEquals(1, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumCounseling());
        assertEquals(2, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumSupportMeeting());
        assertEquals(4, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumTreatmentEffectivenessAssessment());
        assertEquals(3, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumLessons());
        assertEquals(8, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumMedManagement());
        assertEquals(5, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumOutcomeMeasures());
        assertEquals(7, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumReadingResponse());
        assertEquals(6, liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getNumTimeTracking());
        assertEquals("MONTHLY", liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getMedManagementFrequency());
        assertEquals("DAILY", liveDataTest.getNestedLiveDataObj(treatmentPlanDao
                .loadTreatmentPlan()).getOutcomeMeasureFrequency());
    }


    private LiveDataTestUtility liveDataTest;
    private TreatmentPlanDao treatmentPlanDao;
    private VolitionDatabase db;
}
