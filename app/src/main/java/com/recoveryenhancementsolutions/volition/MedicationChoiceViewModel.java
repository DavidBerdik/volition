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
  public LiveData<MedicationChoiceEntity> getMedication() {
    return db.medicationChoiceDAO().getMedication();
  }

  /**
   * @return Returns LiveData of type MedicationChoiceEntity
   */
  public LiveData<MedicationChoiceEntity> getDosage() {
    return db.medicationChoiceDAO().getDosage();
  }

  /**
   * Inserts a medication into the MedicationChoice table. Calls treatmentPlanEntity to generate and
   * insert a new Treatment plan.
   *
   * @param medication Medication object for the View Model.
   */
  public void insertMedication(final MedicationChoiceEntity medication) {
    insertTreatmentPlan(
        TreatmentPlanViewModel.generateTreatmentPlan(medication, severityLevel));
    new insertAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }


  /**
   * Sets the severity level. Statically called from QuestionnaireActivityViewModel.
   *
   * @param newSeverityLevel A String representing the user's severity level. Passed from the
   * Questionnaire.
   */
  public void setSeverityLevel(String newSeverityLevel) {
    severityLevel = newSeverityLevel;
  }

  /**
   * Inserts a dose into the MedicationChoice table.
   *
   * @param dosage Medication object for the View Model.
   */
  public void updateDosage(final MedicationChoiceEntity dosage) {
    new updateDosageAsync(db.medicationChoiceDAO()).execute(dosage);
  }


  /**
   * Class for running insert asynchronously
   */
  private static class insertAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void> {

    /**
     * creates the insertAsync task
     *
     * @param dao Dao object for insertAsyncTask method
     */
    insertAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the insert run on a separate thread
     *
     * @param params Paremeters
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.insertMedication(params[0]);
      return null;
    }


    private MedicationChoiceDAO asyncTaskDao;
  }


  /**
   * Updates the medication in the MedicationChoice table
   *
   * @param medication Medication object for the ViewModel
   */
  public void updateMedication(final MedicationChoiceEntity medication) {
    new updateAsyncTask(db.medicationChoiceDAO()).execute(medication);
  }


  /**
   * Class for running update asynchronously
   */
  private static class updateAsyncTask extends AsyncTask<MedicationChoiceEntity, Void, Void> {

    private MedicationChoiceDAO asyncTaskDao;

    /**
     * creates the updateAsync task
     *
     * @param dao Dao object for this method
     */
    updateAsyncTask(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the update run on a separate thread
     *
     * @param params Parameters for this method
     * @return Returns null
     */
    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      asyncTaskDao.updateMedication(params[0]);
      return null;
    }

  }

  /**
   * Inserts a new treatmentPlanEntity into the database. Static method for insertion of the
   * treatmentPlan from another class.
   *
   * @param treatmentPlanEntity The new treatment plan to be inserted.
   */
  public void insertTreatmentPlan(TreatmentPlanEntity treatmentPlanEntity) {
    new insertTreatmentPlanAsync(db.treatmentPlanDao()).execute(treatmentPlanEntity);
  }

  /**
   * Used to insert treatmentPlan data into the database asynchronously.
   */
  private static class insertTreatmentPlanAsync extends AsyncTask<TreatmentPlanEntity, Void, Void> {

    insertTreatmentPlanAsync(final TreatmentPlanDao dao) {
      asyncTreatmentPlanDao = dao;
    }

    @Override
    protected Void doInBackground(final TreatmentPlanEntity... params) {
      asyncTreatmentPlanDao.insertTreatmentPlanEntity(params[0]);
      return null;
    }

    private final TreatmentPlanDao asyncTreatmentPlanDao;
  }

  /**
   * Class for running update asynchronously
   */
  private static class updateDosageAsync extends AsyncTask<MedicationChoiceEntity, Void, Void> {

    private MedicationChoiceDAO asyncTaskDao;

    updateDosageAsync(final MedicationChoiceDAO dao) {
      asyncTaskDao = dao;
    }

    /**
     * Makes the update run on a separate thread
     *
     * @param params Parameters for this method
     * @return returns null
     */
    @Override
    protected Void doInBackground(final MedicationChoiceEntity... params) {
      params[0] = new MedicationChoiceEntity();
      final int dose = params[0].dosage;
      final String med = params[0].medication;
      final double milligramsNaloxone = params[0].milligramsNaloxone;
      final double milligramsBuprenorphine = params[0].milligramsBuprenorphine;
      final String type = params[0].type;

      asyncTaskDao.updateDosage(type, milligramsNaloxone, milligramsBuprenorphine, dose, med);
      return null;

    }
  }

  /**
   * The app's database
   */
  private VolitionDatabase db;

  /**
   * The user's severity level. Statically passed to this class from QuestionnaireActivityViewModel.
   */
  private String severityLevel;

}
