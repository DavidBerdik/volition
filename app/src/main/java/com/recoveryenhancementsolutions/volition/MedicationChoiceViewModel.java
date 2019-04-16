package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import java.util.Calendar;
import java.util.Date;

/**
 * View Model for the Medication Choice Activity.
 */
public class MedicationChoiceViewModel extends AndroidViewModel {

  /**
   * Sets the severity level variable used for generating a treatment plan.
   */
  public static void setSeverityLevel(String severityLevel) {
    MedicationChoiceViewModel.severityLevel = severityLevel;
  }

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
   * Inserts a medication into the MedicationChoice table.
   *
   * @param medication Medication object for the View Model.
   */
  public void insertMedication(final MedicationChoiceEntity medication) {
    medicationChoiceEntity = medication;
    new insertAsyncTask(db.medicationChoiceDAO()).execute(medication);
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
   * Generates a new treatmentPlan.
   */
  public void generateTreatmentPlan() {

    String medicationChoice = medicationChoiceEntity.medication;

    //A new treatmentPlanEntity to add to the database
    TreatmentPlanEntity newTreatmentPlan = new TreatmentPlanEntity();

    switch (severityLevel) {
      case "MILD":  //There is no mild Buprenorphine plan currently
        newTreatmentPlan.setNumCounseling(1);
        newTreatmentPlan.setNumSupportMeeting(1);
        newTreatmentPlan.setNumLessons(1);
        newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        newTreatmentPlan.setNumOutcomeMeasures(1);
        newTreatmentPlan.setNumTimeTracking(1);
        newTreatmentPlan.setNumReadingResponse(1);
        newTreatmentPlan.setNumMedManagement(0);
        newTreatmentPlan.setMedManagementMonthly();
        newTreatmentPlan.setOutcomeMeasureWeekly();
        //handles differences in treatment plans
        if (medicationChoice.toUpperCase().equals("ABSTAIN")) {
          newTreatmentPlan.setNumMedManagement(0);
        } else {
          newTreatmentPlan.setNumMedManagement(2);
        }
        break;
      case "MODERATE":
        newTreatmentPlan.setNumCounseling(3);
        newTreatmentPlan.setNumSupportMeeting(3);
        newTreatmentPlan.setNumLessons(2);
        newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        newTreatmentPlan.setNumOutcomeMeasures(3);
        newTreatmentPlan.setNumTimeTracking(2);
        newTreatmentPlan.setNumReadingResponse(2);
        newTreatmentPlan.setMedManagementMonthly();
        newTreatmentPlan.setOutcomeMeasureDaily();

        //handles differences in treatment plans
        if (medicationChoice.toUpperCase().equals("ABSTAIN")) {
          newTreatmentPlan.setNumMedManagement(0);
        } else {
          newTreatmentPlan.setNumMedManagement(2);
        }
        break;
      case "SEVERE":  //Severe severity level
        newTreatmentPlan.setNumCounseling(5);
        newTreatmentPlan.setNumSupportMeeting(5);
        newTreatmentPlan.setNumLessons(3);
        newTreatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        newTreatmentPlan.setNumOutcomeMeasures(5);
        newTreatmentPlan.setNumTimeTracking(5);
        newTreatmentPlan.setNumReadingResponse(3);
        newTreatmentPlan.setMedManagementWeekly();
        newTreatmentPlan.setOutcomeMeasureDaily();

        //handles differences in treatment plans
        if (medicationChoice.toUpperCase().equals("ABSTAIN")) {
          newTreatmentPlan.setNumMedManagement(0);
        } else {
          newTreatmentPlan.setNumMedManagement(1);
        }
        break;
      default:  //default case
        newTreatmentPlan.setNumCounseling(7);
        newTreatmentPlan.setNumSupportMeeting(7);
        newTreatmentPlan.setNumLessons(7);
        newTreatmentPlan.setNumTreatmentEffectivenessAssessment(7);
        newTreatmentPlan.setNumOutcomeMeasures(7);
        newTreatmentPlan.setNumTimeTracking(7);
        newTreatmentPlan.setNumReadingResponse(7);
        newTreatmentPlan.setMedManagementWeekly();
        newTreatmentPlan.setOutcomeMeasureDaily();

        //handles differences in treatment plans
        if (medicationChoice.toUpperCase().equals("ABSTAIN")) {
          newTreatmentPlan.setNumMedManagement(0);
        } else {
          newTreatmentPlan.setNumMedManagement(1);
        }
        break;
    }
    //Set time and update cool-down information. NOTE: cool down must be positive
    Date date = Calendar.getInstance().getTime();
    long time = date.getTime() - (1000 * 60 * 60 * 24);
    date.setTime(time);
    newTreatmentPlan.setCoolDownTime(coolDownTime);
    newTreatmentPlan.setLastUpdate(date);

    //Passes the new treatment plan to the Treatment plan view model for insertion
    TreatmentPlanViewModel.insertTreatmentPlan(newTreatmentPlan, db);
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
      asyncTaskDao.updateDosage(dose, med);
      return null;

    }
  }

  /**
   * A int representing the number of hours between modifications to the treatment Plan
   */
  private final int coolDownTime = 8;

  /**
   * The app's database
   */
  private VolitionDatabase db;

  /**
   * The user's severity level
   */
  private static String severityLevel;

  /**
   * The user's medication choice
   */
  private static MedicationChoiceEntity medicationChoiceEntity;
}
