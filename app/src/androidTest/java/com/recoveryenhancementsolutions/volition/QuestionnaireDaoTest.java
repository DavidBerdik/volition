package com.recoveryenhancementsolutions.volition;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;

import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;

/**
 *  Performs test on the QuestionnaireDao for retrieving questionnaire answers from the database.
 */

public class QuestionnaireDaoTest {


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
        questionnaireActivityDao = db.questionnaireModel();
    }

    /**
     * Closes the temporary test database.
     */
    @After
    public void closeDb() {
        db.close();
    }

    /**
     *  This test method will insert values into the temporary database for the questionnaire
     *  and test that the values retrieved are correct.
     * @throws Exception Throws exception for entering test data.
     */
    @Test
    public void testQuestionnaireActivityDao() throws Exception {
            QuestionnaireActivityEntity questionnaireActivityEntity = new QuestionnaireActivityEntity();
            questionnaireActivityEntity.setQ1(true);
        questionnaireActivityEntity.setQ1(true);
        questionnaireActivityEntity.setQ2(true);
        questionnaireActivityEntity.setQ3(true);
        questionnaireActivityEntity.setQ4(true);
        questionnaireActivityEntity.setQ5(true);
        questionnaireActivityEntity.setQ6(true);
        questionnaireActivityEntity.setQ7(true);
        questionnaireActivityEntity.setQ8(true);
        questionnaireActivityEntity.setQ9(true);
        questionnaireActivityEntity.setQ10(true);
        questionnaireActivityEntity.setQ11(true);
        questionnaireActivityEntity.setSeverityLevel("severe");
        questionnaireActivityEntity.setTotalYes("11");



        // Insert the entities.

            questionnaireActivityDao.insertQuestionnaire(questionnaireActivityEntity);


        // Check that the database is not null.
        assertNotNull(db);

        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ1()));

        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ2()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ3()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ4()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ5()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ6()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ7()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ8()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ9()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ10()));
        assertEquals(true,
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findQ11()));
        assertEquals("11",
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findTotalYes()));
        assertEquals("severe",
                LiveDataTestUtility.getNestedLiveDataObj(questionnaireActivityDao.findSeverityLevelString()));

    }




    private QuestionnaireDao questionnaireActivityDao;
    private VolitionDatabase db;
}