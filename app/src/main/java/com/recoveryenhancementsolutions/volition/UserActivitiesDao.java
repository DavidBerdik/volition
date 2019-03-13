package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.Date;
import java.util.List;

@Dao
public interface UserActivitiesDao {

  /**
   * Inserts a user activity in the database.
   * @param userActivitiesTemp A UserActivitiesTemp object containing the activity
   * to be inserted.
   */
  @Insert
  void insertActivity(UserActivitiesTemp userActivitiesTemp);

  /**
   * Retrieves all user activities from the database.
   * @return A LiveData object containing a list of all user activities.
   */
  @Query("SELECT * FROM UserActivitiesTemp")
  LiveData<List<UserActivitiesTemp>> getAllActivities();

  /**
   * Retrieves all user activities that took place on a given date from the database.
   * @param date The day to retrieve desired activities for.
   * @return A LiveData object containing a list of all user activities that took place on
   * the day defined by the value of "date."
   */
  @Query("SELECT * FROM UserActivitiesTemp WHERE date = :date")
  LiveData<List<UserActivitiesTemp>> getActivitiesByDate(Date date);

  /**
   * Retrieves the user activity with the given ID.
   * @param actID The ID of the activity to retrieve.
   * @return A LiveData object containing a list of all user activities with the given ID.
   */
  @Query("SELECT * FROM UserActivitiesTemp WHERE actID = :actID")
  LiveData<UserActivitiesTemp> getActivitiesByID(int actID);
}
