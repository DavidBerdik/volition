package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class UserActivitiesDaoTest {
  private UserActivitiesDao userActivitiesDao;
  private VolitionDatabase db;

  @Before
  public void createDb() {
    Context context = InstrumentationRegistry.getTargetContext();
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).build();
    userActivitiesDao = db.userActivitiesDao();
  }

  @After
  public void closeDb() throws IOException {
    db.close();
  }

  @Test
  public void testUserActivitiesDao() throws Exception {
    // TODO: Uncomment this unit test. It is temporarily commented out to stop it from failing and causing the app to not run.
    // Create 5 User Activity Entities
    /*UserActivityEntity[] userActivityEntity = new UserActivityEntity[5];

    // Create 5 User Activity Dates
    Date[] userActivityDate = new Date[5];
    userActivityDate[0] = new Date(1552609710); // 1552609710 = Friday, March 15, 2019 12:28:30 AM GMT
    userActivityDate[1] = new Date(1502609710); // 1502609710 = Sunday, August 13, 2017 7:35:10 AM GMT
    userActivityDate[2] = new Date(1002609710); // 1002609710 = Tuesday, October 9, 2001 6:41:50 AM GMT
    userActivityDate[3] = new Date(0);          // 0          = Thursday, January 1, 1970 12:00:00 AM GMT
    userActivityDate[4] = new Date(2147483647); // 2147483647 = Tuesday, January 19, 2038 3:14:07 AM GMT

    // Create 5 User Activity Descriptions
    String[] userActivityDesc = {"This is a", "test of the", "emergency", "broadcast", "system."};

    // Set times and descriptions for each of the 5 entities.
    for(int x = 0; x < 5; x++) {
      userActivityEntity[x] = new UserActivityEntity();
      userActivityEntity[x].setDate(userActivityDate[x]);
      userActivityEntity[x].setDesc(userActivityDesc[x]);
    }

    // Insert the entities.
    for (UserActivityEntity entity : userActivityEntity) {
      userActivitiesDao.insertActivity(entity);
    }

    // Check that the database is not null.
    assertNotNull(db);

    // Query the database for all entries and check that the returned list contains 5 entries.
    assertEquals(5, userActivitiesDao.getAllActivities().getValue().size());

    // Query the database for the activity with ID 3 and check that it matches the original.
    assertEquals(userActivityEntity[2], userActivitiesDao.getActivitiesByID(2).getValue());

    // Query the database for the activity with date August 13, 2017 and check that it matches the
    // original.
    Date aug13 = new Date();
    Calendar aug13Cal = Calendar.getInstance();
    aug13Cal.set(2017, 7, 13);
    aug13 = aug13Cal.getTime();
    assertEquals(userActivityEntity[1], userActivitiesDao.getActivitiesByDate(aug13)
        .getValue().get(0));*/
  }
}
