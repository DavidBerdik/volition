package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * View Model for the Medication Choice Activity.
 */

public class MedicationChoiceViewModel extends AndroidViewModel {

  /**
   * Constructor for the Medication Choice View Model.
   *
   * @param application Application object for the View Model.
   */

  public MedicationChoiceViewModel(final Application application) {
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
   * Retrieves the medication from the MedicationChoice table.
   *
   * @return Returns LiveData of type MedicationChoiceEntity.
   */

  public LiveData<MedicationChoiceEntity> getMedication(){
    return db.medicationChoiceDAO().getMedication();
  }

  /**
   * Inserts a medication into the MedicationChoice table.
   *
   * @param medication Medication object for the View Model.
   */

  public void insertMedication(final MedicationChoiceEntity medication) {
    new insertAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }

  /**
   * Class for running insert asynchronously
   */
  private static class insertAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void> {

    private MedicationChoiceDAO asyncTaskDao;

    /**
     * creates the insertAsync task
     * @param dao Dao object for insertAsyncTask method
     */
    insertAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the insert run on a separate thread
     * @param params Paremeters
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.insertMedication(params[0]);
      return null;
    }
  }

  /**
   * Updates the medication in the MedicationChoice table
   * @param medication Medication object for the ViewModel
   */
  public void updateMedication(final MedicationChoiceEntity medication){
    new updateAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }

  /**
   * Class for running update asynchronously
   */
  private static class updateAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void>{

    private MedicationChoiceDAO asyncTaskDao;

    /**
     * creates the updateAsync task
     * @param dao Dao object for this method
     */
    updateAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the update run on a separate thread
     * @param params Parameters fro this method
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.updateMedication(params[0]);
      return null;
    }
  }
  private static MedicationChoiceDAO med;
  private static VolitionDatabase db;
}
