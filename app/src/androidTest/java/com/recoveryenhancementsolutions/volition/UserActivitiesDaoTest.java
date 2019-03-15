package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.annotation.Nullable;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Unit test for the User Activities DAO
 */
@RunWith(AndroidJUnit4.class)
public class UserActivitiesDaoTest {

  private UserActivitiesDao userActivitiesDao;
  private VolitionDatabase db;

  /**
   * Creates the temporary test database.
   */
  @Before
  public void createDb() {
    Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).build();
    userActivitiesDao = db.userActivitiesDao();
  }

  /**
   * Closes the temporary test database.
   */
  @After
  public void closeDb() throws IOException {
    db.close();
  }

  /**
   * Performs several tests involving the User Activities DAO
   */
  @Test
  public void testUserActivitiesDao() throws Exception {
    // Create 5 User Activity Entities
    UserActivityEntity[] userActivityEntity = new UserActivityEntity[5];

    // Create 5 User Activity Dates
    int[] userActivityYear = {2019, 2017, 2001, 1970, 2038};
    int[] userActivityMonth = {3, 8, 10, 1, 1};
    int[] userActivityDay = {15, 13, 9, 1, 19};

    // Create 5 User Activity Descriptions
    String[] userActivityDesc = {"This is a", "test of the", "emergency", "broadcast", "system."};

    // Set times and descriptions for each of the 5 entities.
    for (int x = 0; x < 5; x++) {
      userActivityEntity[x] = new UserActivityEntity();
      userActivityEntity[x].setDate(userActivityYear[x], userActivityMonth[x], userActivityDay[x]);
      userActivityEntity[x].setDesc(userActivityDesc[x]);
    }

    // Insert the entities.
    for (UserActivityEntity entity : userActivityEntity) {
      userActivitiesDao.insertActivity(entity);
    }

    // Check that the database is not null.
    assertNotNull(db);

    // Query the database for all entries and check that the returned list contains 5 entries.
    assertEquals(5, getNestedLiveDataObj(userActivitiesDao.getAllActivities()).size());

    // Query the database for the activity with ID 3 and check that it matches the original.
    assertEquals(3, getNestedLiveDataObj(userActivitiesDao.getActivitiesByID(3)).getId());

    // Query the database for the activity with date August 13, 2017 and check that it matches the
    // original.
    Date aug13 = new SimpleDateFormat("yyyy-MM-dd").parse("2017-8-13");
    assertEquals(userActivityEntity[1].getDate(), getNestedLiveDataObj(
        userActivitiesDao.getActivitiesByDate(aug13)).get(0).getDate());
  }

  /**
   * Extracts the object contained within a LiveData object.
   *
   * @param liveData The LiveData object to extract from.
   * @return The object contained within liveData.
   */
  public static <T> T getNestedLiveDataObj(final LiveData<T> liveData) throws InterruptedException {
    // Acquired from https://stackoverflow.com/a/49693724/2941352
    final Object[] objects = new Object[1];
    final CountDownLatch latch = new CountDownLatch(1);

    Observer observer = new Observer() {
      @Override
      public void onChanged(@Nullable Object o) {
        objects[0] = o;
        latch.countDown();
        liveData.removeObserver(this);
      }
    };
    liveData.observeForever(observer);
    latch.await(2, TimeUnit.SECONDS);
    return (T) objects[0];
  }
}
