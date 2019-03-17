package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * ViewModel for the "User Activity History" activity.
 */
public class UserActivityHistoryViewModel extends AndroidViewModel {

  private VolitionDatabase db;

  /**
   * Constructor for the "User Activity History" ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public UserActivityHistoryViewModel(Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param year Activity's year
   * @param month Activity's month
   * @param day Activity's day
   * @param desc Description of the activity.
   */
  public void insertActivity(int year, int month, int day, String desc) {
    UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(year, month, day);
    entity.setDesc(desc);
    insertActivity(entity);
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param date Date object containing the date on which the activity took place.
   * @param desc Description of the activity.
   */
  public void insertActivity(Date date, String desc) {
    UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(date);
    entity.setDesc(desc);
    insertActivity(entity);
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param userActivityEntity A UserActivityEntity object containing the activity to be inserted.
   */
  public void insertActivity(UserActivityEntity userActivityEntity) {
    db.userActivitiesDao().insertActivity(userActivityEntity);
  }

  /**
   * Retrieves all user activities from the database.
   *
   * @return A LiveData object containing a list of all user activities.
   */
  public LiveData<List<UserActivityEntity>> getAllActivities() {
    return db.userActivitiesDao().getAllActivities();
  }

  /**
   * Retrieves the user activity with the given ID.
   *
   * @param id The ID of the activity to retrieve.
   * @return A LiveData object containing the user activities with the given ID.
   */
  public LiveData<UserActivityEntity> getActivitiesByID(int id) {
    return db.userActivitiesDao().getActivitiesByID(id);
  }

  /**
   * Retrieves all user activities that took place on a given date from the database.
   *
   * @param year Activitys' year
   * @param month Activitys' month
   * @param day Activitys' day
   * @return A LiveData object containing a list of all user activities that took place on the day
   * defined by the value of "date."
   */
  public LiveData<List<UserActivityEntity>> getActivitiesByDate(int year, int month, int day) {
    Date date = null;
    try {
      date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(year + "-"
          + month + "-" + day);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return getActivitiesByDate(date);
  }

  /**
   * Retrieves all user activities that took place on a given date from the database.
   *
   * @param date The day to retrieve desired activities for.
   * @return A LiveData object containing a list of all user activities that took place on the day
   * defined by the value of "date."
   */
  public LiveData<List<UserActivityEntity>> getActivitiesByDate(Date date) {
    return db.userActivitiesDao().getActivitiesByDate(date);
  }
}
