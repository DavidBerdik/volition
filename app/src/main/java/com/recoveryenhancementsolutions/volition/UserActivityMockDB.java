package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

/**
 * Initialize the database with temporary data for UI testing purposes
 */
public class UserActivityMockDB {

  /**
   * Constructor for the database initializer. Creates a temporary in memory database
   *
   * @param application Application object for the UserActivityViewModel
   * @param context Context object for the UserActivityViewModel
   */
  public UserActivityMockDB(final Application application, final Context context) {
    userActivityViewModel = new UserActivityViewModel(application);
    db = Room.inMemoryDatabaseBuilder(context, VolitionDatabase.class).allowMainThreadQueries()
        .build();
    userActivityViewModel.setTestDatabase(db);
  }

  /**
   * Method to populate the database with test data
   */
  public void populateDbWithTestData() {

    final int[] userActivityYear = {2019, 2017, 2001, 1970, 2038};
    final int[] userActivityMonth = {3, 8, 10, 1, 1};
    final int[] userActivityDay = {15, 13, 9, 1, 19};

    // Create 5 User Activity Descriptions
    final String[] userActivityDesc = {"Act 1", "Act 2", "Act 3", "Act 4", "Act 5"};

    // Create 5 User Activity Notes
    final String[] userActivityNotes = {"This is a", "test of the", "temporary", "database",
        "initializer."};

    // Insert the entities.
    for (int x = 0; x < 5; x++) {
      userActivityViewModel
          .insertActivity(userActivityYear[x], userActivityMonth[x], userActivityDay[x],
              userActivityDesc[x], userActivityNotes[x]);
    }
  }

  /**
   * Get the VolitionDatabase
   *
   * @return VolitionDatabase
   */
  public VolitionDatabase getDb() {
    return db;
  }


  private UserActivityViewModel userActivityViewModel;
  private VolitionDatabase db;
}
