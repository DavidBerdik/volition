package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Calendar;
import java.util.Date;

/**
 * ViewModel for "DemographicDataEntity" in the database.
 */
public class DemographicDataViewModel extends AndroidViewModel {

  /**
   * Constructor for the ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public DemographicDataViewModel(final Application application) {
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
   * Inserts a new DemographicDataEntity into the database.
   *
   * @param demographicDataEntity The entity to be inserted.
   */
  public void insertDemographicData(final DemographicDataEntity demographicDataEntity) {
    new insertAsyncTask(db.demographicDataDao()).execute(demographicDataEntity);
  }

    /**
     * Updates the date of last use in the database.
     *
     * @param day A Calendar object representing the date of last use
     */
  public void updateLastUseDate(final Calendar day){
    new UpdateDaysCleanAsync(db.demographicDataDao()).execute(day);
    }

  /**
   * Retrieves the last date clean as stored in the database.
   *
   * @return A LiveData object containing a Date representing the last listed date of being clean.
   */
  public LiveData<Date> getLastUseDate() {
    return db.demographicDataDao().queryLastUseDate();
  }

  private static class insertAsyncTask extends AsyncTask<DemographicDataEntity, Void, Void> {

    insertAsyncTask(final DemographicDataDAO dao) {
      demographicDataDao = dao;
    }

    @Override
    protected Void doInBackground(final DemographicDataEntity... params) {
      demographicDataDao.insertDemographicInfo(params[0]);
      return null;
    }

    private DemographicDataDAO demographicDataDao;
  }

  /**
   * Asynchronous task for updating the last clean date
   */
  private class UpdateDaysCleanAsync extends AsyncTask<Calendar, Void, Void> {

    UpdateDaysCleanAsync(final DemographicDataDAO dao) {
      demographicDataDAO = dao;
    }

    @Override
    protected Void doInBackground(final Calendar... params) {
      demographicDataDAO.queryUpdateLastUseDate(params[0].getTime());
      return null;
    }

    private DemographicDataDAO demographicDataDAO;
  }

  private VolitionDatabase db;
}
