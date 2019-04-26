package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import android.util.Log;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * ViewModel for the "User Activity History" activity.
 */
public class UserActivityViewModel extends AndroidViewModel {

  /**
   * Constructor for the "User Activity History" ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public UserActivityViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Sets a test database for the ViewModel. This should only be used for unit testing this
   * ViewModel.
   *
   * @param db The VolitionDatabase to use for testing the ViewModel
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param year Activity's year
   * @param month Activity's month
   * @param day Activity's day
   * @param desc Description of the activity.
   */
  public void insertActivity(final int year, final int month, final int day,
      final String desc, final String notes) {
    final UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(year, month, day);
    entity.setDesc(desc);
    entity.setNotes(notes);
    insertActivity(entity);
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param date Date object containing the date on which the activity took place.
   * @param desc Description of the activity.
   */
  public void insertActivity(final Date date, final String desc, final String notes) {
    final UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(date);
    entity.setDesc(desc);
    entity.setNotes(notes);
    insertActivity(entity);
  }

  /**
   * Inserts a user activity in the database.
   *
   * @param userActivityEntity A UserActivityEntity object containing the activity to be inserted.
   */
  public void insertActivity(final UserActivityEntity userActivityEntity) {
    new updateAsyncTask(db.userActivitiesDao()).doInBackground(userActivityEntity);
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
  public LiveData<UserActivityEntity> getActivitiesByID(final int id) {
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
  public LiveData<List<UserActivityEntity>> getActivitiesByDate(final int year, final int month,
      final int day) {
    try {
      final Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.US)
          .parse(year + "-" + month + "-" + day);
      return getActivitiesByDate(date);
    } catch (ParseException e) {
      Log.e(TAG, Log.getStackTraceString(e));
    }
    return null;
  }

  /**
   * Retrieves all user activities that took place on a given date from the database.
   *
   * @param date The day to retrieve desired activities for.
   * @return A LiveData object containing a list of all user activities that took place on the day
   * defined by the value of "date."
   */
  public LiveData<List<UserActivityEntity>> getActivitiesByDate(final Date date) {
    return db.userActivitiesDao().getActivitiesByDate(date);
  }

   /**
   * Used to update data into the database asynchronously
   */
  private static class updateAsyncTask extends AsyncTask<UserActivityEntity, Void, Void> {
     private static UserActivitiesDao asyncTaskDao;

     updateAsyncTask(final UserActivitiesDao dao) {
       asyncTaskDao = dao;
     }

     @Override
     protected Void doInBackground(final UserActivityEntity... params) {
       asyncTaskDao.insertActivity(params[0]);
       return null;
     }
   }

   /**
   * Retrieves all user activities from the database that took place on a given month this year.
   *
   * @param month An integer representing the desired month. (January = 1, December = 12)
   * @return A LiveData object containing a list of user activities that took place on the month
   * defined by the value of "month."
   */
  public LiveData<List<UserActivityEntity>> getActivitiesByMonth(final int month) {
    // Create a lower bound calendar. (The first second of the first day of the month.)
    final Calendar startCal = Calendar.getInstance();
    startCal.set(startCal.get(Calendar.YEAR), month - 1, 1, 0, 0, 0);

    // Create an upper bound calendar. (The last second of the last day of the month.)
    final Calendar endCal = Calendar.getInstance();
    endCal.set(endCal.get(Calendar.YEAR), month - 1, endCal.getActualMaximum(Calendar.DAY_OF_MONTH),
        23, 59, 59);

    // Return the LiveData object containing the list of qualifying activities.
    return db.userActivitiesDao().getActivitiesByMonth(startCal.getTime(), endCal.getTime());
  }

  private VolitionDatabase db;
  private static final String TAG = "UserActivityViewModel";
}
