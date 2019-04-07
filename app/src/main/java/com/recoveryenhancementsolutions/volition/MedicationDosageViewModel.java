package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * View Model for the Medication Dosage Activity.
 */
public class MedicationDosageViewModel extends AndroidViewModel {

  /**
   * Constructor for the Medication Dosage View Model.
   *
   * @param application Application object for the View Model.
   */
  public MedicationDosageViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Set the test database for this View Model.
   *
   * @param db the Volition Database to use for testing.
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Retrieves the medication from the MedicationDosage table.
   *
   * @return Returns LiveData of type MedicationDosageEntity.
   */
  public LiveData<MedicationDosageEntity> getDosage() {
    return db.medicationDosageDao().getDosage();
  }

  /**
   * Inserts a dose into the MedicationDosage table.
   *
   * @param dosage Medication object for the View Model.
   */
  public void insertDosage(final MedicationDosageEntity dosage) {
    new insertAsyncTask(db.medicationDosageDao()).execute(dosage);
  }

  /**
   * Class for running insert asynchronously
   */
  private static class insertAsyncTask extends AsyncTask<MedicationDosageEntity, Void, Void> {

    /**
     * creates the insertAsync task
     *
     * @param dao Dao object for insertAsyncTask method
     */
    insertAsyncTask(final MedicationDosageDao dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the insert run on a separate thread
     *
     * @param params Paremeters
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationDosageEntity... params) {
      asyncTaskDao.insertDosage(params[0]);
      return null;
    }

    private MedicationDosageDao asyncTaskDao;
  }

  /**
   * Updates the dosage in the MedicationDosage table
   *
   * @param dosage Dosage object for the ViewModel
   */
  public void updateDosage(final MedicationDosageEntity dosage) {
    new updateAsyncTask(db.medicationDosageDao()).execute(dosage);
  }

  /**
   * Class for running update asynchronously
   */
  private static class updateAsyncTask extends AsyncTask<MedicationDosageEntity, Void, Void> {

    private MedicationDosageDao asyncTaskDao;

    /**
     * creates the updateAsync task
     *
     * @param dao Dao object for this method
     */
    updateAsyncTask(final MedicationDosageDao dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the update run on a separate thread
     *
     * @param params Parameters for this method
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationDosageEntity... params) {
      asyncTaskDao.updateDosage(params[0]);
      return null;
    }
  }

  private VolitionDatabase db;
}
