package com.recoveryenhancementsolutions.volition;

import android.arch.core.executor.testing.InstantTaskExecutorRule;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import com.recoveryenhancementsolutions.volition.utilities.LiveDataTestUtility;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Unit test for the User Activities DAO
 */
@RunWith(AndroidJUnit4.class)
public class UserActivitiesDaoTest {

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
    userActivitiesDao = db.userActivitiesDao();
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() {
    db.close();
  }

  /**
   * Performs several tests involving the User Activities DAO
   */
  @Test
  public void testUserActivitiesDao() throws Exception {
    // Create 5 User Activity Entities
    final UserActivityEntity[] userActivityEntity = new UserActivityEntity[5];

    // Create 5 User Activity Dates
    final int[] userActivityYear = {Calendar.getInstance().get(Calendar.YEAR), 2017, 2001, 1970,
        2038};
    final int[] userActivityMonth = {3, 8, 10, 1, 1};
    final int[] userActivityDay = {15, 13, 9, 1, 19};

    // Create 5 User Activity Descriptions
    final String[] userActivityDesc = {"Act 1", "Act 2", "Act 3", "Act 4", "Act 5"};

    // Create 5 User Activity Notes
    final String[] userActivityNotes = {"This is a", "test of the", "emergency", "broadcast",
        "system."};

    // Set times and descriptions for each of the 5 entities.
    for (int x = 0; x < 5; x++) {
      userActivityEntity[x] = new UserActivityEntity();
      userActivityEntity[x].setDate(userActivityYear[x], userActivityMonth[x], userActivityDay[x]);
      userActivityEntity[x].setDesc(userActivityDesc[x]);
      userActivityEntity[x].setNotes(userActivityNotes[x]);
    }

    // Insert the entities.
    for (final UserActivityEntity entity : userActivityEntity) {
      userActivitiesDao.insertActivity(entity);
    }

    // Check that the database is not null.
    assertNotNull(db);

    // Query the database for all entries and check that the returned list contains 5 entries.
    assertEquals(5,
        LiveDataTestUtility.getNestedLiveDataObj(userActivitiesDao.getAllActivities()).size());

    // Query the database for the activity with ID 3 and check that it matches the original.
    assertEquals(3,
        LiveDataTestUtility.getNestedLiveDataObj(userActivitiesDao.getActivitiesByID(3)).getId());

    // Query the database for the activity with date August 13, 2017 and check that it matches the
    // original.
    final Date aug13 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-8-13");
    assertEquals(userActivityEntity[1].getDate(),
        LiveDataTestUtility.getNestedLiveDataObj(userActivitiesDao.getActivitiesByDate(aug13))
            .get(0).getDate());

    // Prepare to query the database for activities that take place in March.
    // Create a calendar to generate two dates with.
    final Calendar cal = Calendar.getInstance();

    // Create a lower bound date. (The first second of first day of the month.)
    cal.set(Calendar.getInstance().get(Calendar.YEAR), 2, 1, 0, 0, 0);
    final Date lowerDate = cal.getTime();

    // Create an upper bound date. (The last second of the last day of the month.)
    cal.set(Calendar.getInstance().get(Calendar.YEAR), 2,
        cal.getActualMaximum(Calendar.DAY_OF_MONTH),
        23, 59, 59);
    final Date upperDate = cal.getTime();

    // Finally, query the database for activities that take place in March and verify the result.
    assertEquals(1, LiveDataTestUtility
        .getNestedLiveDataObj(userActivitiesDao.getActivitiesByMonth(lowerDate, upperDate)).get(0)
        .getId());
  }

  private UserActivitiesDao userActivitiesDao;
  private VolitionDatabase db;
}