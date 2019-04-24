package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.Date;
import java.util.List;

/**
 * DAO for interacting with the UserActivityEntity.
 */
@Dao
public interface UserActivitiesDao {

  /**
   * Inserts a user activity in the database.
   *
   * @param userActivityEntity A UserActivityEntity object containing the activity to be inserted.
   */
  @Insert
  void insertActivity(final UserActivityEntity userActivityEntity);

  /**
   * Retrieves all user activities from the database.
   *
   * @return A LiveData object containing a list of all user activities.
   */
  @Query("SELECT * FROM UserActivityEntity")
  LiveData<List<UserActivityEntity>> getAllActivities();

  /**
   * Retrieves the user activity with the given ID.
   *
   * @param id The ID of the activity to retrieve.
   * @return A LiveData object containing the user activities with the given ID.
   */
  @Query("SELECT * FROM UserActivityEntity WHERE id = :id")
  LiveData<UserActivityEntity> getActivitiesByID(final int id);

  /**
   * Retrieves all user activities that took place on a given date from the database.
   *
   * @param date The day to retrieve desired activities for.
   * @return A LiveData object containing a list of all user activities that took place on the day
   * defined by the value of "date."
   */
  @Query("SELECT * FROM UserActivityEntity WHERE date = :date")
  LiveData<List<UserActivityEntity>> getActivitiesByDate(final Date date);

  /**
   * Retrieves all user activities that took place on a given month this year from the database.
   *
   * @param lowerDate The lower bound date (first second of the first day of the month).
   * @param upperDate The upper bound date (last second of the last day of the month).
   * @return A LiveData object containing a list of all user activities that took place on the month
   * defined within the range between lowerDate and upperDate.
   */
  @Query("SELECT * FROM UserActivityEntity WHERE date >= :lowerDate AND date <= :upperDate")
  LiveData<List<UserActivityEntity>> getActivitiesByMonth(final Date lowerDate,
      final Date upperDate);
}