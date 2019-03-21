package com.recoveryenhancementsolutions.volition;

import android.app.Application;

/**
 * Initialize the database with temporary data for UI testing purposes
 */
public class TempDatabaseInitializer {

  /**
   * Constructor for the database initializer
   * @param application Application object for the UserActivityViewModel
   */
  public TempDatabaseInitializer(Application application) {
    userActivityViewModel = new UserActivityViewModel(application);
  }

  /**
   * Method to populate the database with test data
   */
  public void populateDbWithTestData() {

    final int[] userActivityYear = {2019, 2017, 2001, 1970, 2038};
    final int[] userActivityMonth = {3, 8, 10, 1, 1};
    final int[] userActivityDay = {15, 13, 9, 1, 19};

    // Create 5 User Activity Descriptions
    final String[] userActivityDesc = {"This is a", "test of the", "emergency", "broadcast",
        "system."};

    // Insert the entities.
    for (int x = 0; x < 5; x++) {
      userActivityViewModel
          .insertActivity(userActivityYear[x], userActivityMonth[x], userActivityDay[x],
              userActivityDesc[x]);
    }
  }
  private UserActivityViewModel userActivityViewModel;
}
