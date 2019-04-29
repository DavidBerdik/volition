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
    new updateAsyncTask(db.userActivitiesDao()).execute(userActivityEntity);
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
    // Calculate the lower bound month. It should be the month before the one passed in.
    int lowerBoundMonth = month - 2;
    int lowerBoundYear = Calendar.getInstance().get(Calendar.YEAR);
    if (lowerBoundMonth == -1) {
      // Wrap around to December of the previous year.
      lowerBoundMonth = 11;
      lowerBoundYear--;
    }

    // Create a lower bound calendar. (The last second of the last day of the previous month.)
    final Calendar startCal = Calendar.getInstance();
    startCal
        .set(lowerBoundYear, lowerBoundMonth, getNumberOfDays(lowerBoundYear, lowerBoundMonth), 23,
            59, 59);

    // Calculate the upper bound month. It should be the month after the one passed in.
    int upperBoundMonth = month;
    int upperBoundYear = Calendar.getInstance().get(Calendar.YEAR);
    if (upperBoundMonth == 12) {
      // Wrap around to January of the next year.
      upperBoundMonth = 0;
      upperBoundYear++;
    }

    // Create an upper bound calendar. (The first second of the first day of the next month.)
    final Calendar endCal = Calendar.getInstance();
    endCal.set(upperBoundYear, upperBoundMonth, 1, 0, 0, 0);

    // Return the LiveData object containing the list of qualifying activities.
    return db.userActivitiesDao().getActivitiesByMonth(startCal.getTime(), endCal.getTime());
  }

  /**
   * Determine the number of days in a month.
   *
   * @param year The desired year.
   * @param month The desired month to find the number of days for. (1 = January)
   * @return The number of days in the given month.
   */
  private int getNumberOfDays(final int year, final int month) {
    Calendar cal = Calendar.getInstance();
    cal.set(year, month - 1, 1);
    return cal.getActualMaximum(Calendar.DAY_OF_MONTH);
  }

  private VolitionDatabase db;
  private static final String TAG = "UserActivityViewModel";
}
