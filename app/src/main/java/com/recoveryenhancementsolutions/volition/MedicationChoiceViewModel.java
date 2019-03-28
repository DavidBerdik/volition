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
   * Asynchronously populate the database.
   *
   * @param db the Volition Database to use for testing.
   */

  public static void populateAsync(final VolitionDatabase db){
    PopulateDbAsync task = new PopulateDbAsync(db);
    task.execute();
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

  public void insertMedication(MedicationChoiceEntity medication){
    db.medicationChoiceDAO().insertMedication(medication);
  }

  /**
   * Adds a medication to the database.
   *
   * @param medAnswer String that holds the user's medication choice.
   */

  public static void addMedication(String medAnswer){
    MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.insertMed(medAnswer);
    db.medicationChoiceDAO().insertMedication(medicationChoiceEntity);
  }

  /**
   * Updates the medication in the database
   *
   * @param medAnswer String that holds the user's medication choice.
   */

  public static void updateMedication(String medAnswer){
    MedicationChoiceEntity medicationChoiceEntity = new MedicationChoiceEntity();
    medicationChoiceEntity.insertMed(medAnswer);
    db.medicationChoiceDAO().updateMedication(medicationChoiceEntity);
  }

  /**
   * Populate the test database with data.
   *
   * @param db Volition Database to use for testing.
   */

  public static void populateWithData(VolitionDatabase db){
    MedicationChoiceActivity medicationChoiceActivity = new MedicationChoiceActivity();
    addMedication(medicationChoiceActivity.medAnswer);
    if(!db.equals(null)) {
      updateMedication(medicationChoiceActivity.medAnswer);
    }
  }

  /**
   * Creates a background thread to asynchronously insert and update the MedicationChoice table.
   */

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    VolitionDatabase db;

    /**
     * Populates the database asynchronously.
     *
     * @param db Volition database for testing.
     */
    PopulateDbAsync(VolitionDatabase db) {
      db = db;
    }

    /**
     * Runs the insert and update threads in the background
     *
     * @param params Parameters for the doInBackground method.
     * @return Returns null
     */

    @Override
    protected Void doInBackground(final Void... params) {
      populateWithData(db);
      return null;
    }
  }
  private static VolitionDatabase db;
}
