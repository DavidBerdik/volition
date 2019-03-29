package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

/**
 * Class manages relationship between the TreatmentPlanActivity and the Volition Database.
 */
public class TreatmentPlanViewModel extends AndroidViewModel {

  /**
   * The treatment plan stored in the database.  Should only be used to update values, not read
   * them.
   */
  public TreatmentPlanEntity treatmentPlan;

  /**
   * Constructor method to initialize a new TreatmentPlanViewModel
   *
   * @param application The application creating the TreatmentPlanViewModel.
   */
  public TreatmentPlanViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Loads in a pre-existing treatmentPlan
   */
  public void loadTreatmentPlan(){
    treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan().getValue();
    treatmentPlanDao = db.treatmentPlanDao();
  }

  /**
   * Updates the database with the values of treatmentPlan.
   */
  public void insertDb() {
    db.treatmentPlanDao().insertTreatmentPlanEntity(treatmentPlan);
  }

  /**
   * Sets a test database for the ViewModel. This should only be used for unit testing this
   * ViewModel.
   *
   * @param db The VolitionDatabase to use for testing the ViewModel
   */
  public void setTestDatabase(final VolitionDatabase db) {
    db.close();
    this.db = db;
    this.treatmentPlan = db.treatmentPlanDao().loadTreatmentPlan().getValue();
  }

  /**
   * Returns a live data object containing the user's treatment plan.
   */
  public LiveData<TreatmentPlanEntity> getTreatmentPlan() {
    return db.treatmentPlanDao().loadTreatmentPlan();
  }

  /**
   * Generates a new treatmentPlan.
   */
  public void generateTreatmentPlan() {
    String medicationChoice, severityLevel;

    //imports severity level and medication choice from the database. sets to uppercase to
    //prevent any issues with misnamed strings. Try catch statement generates a dummy treatment
    //plan set to mild abstinence if the database tables containing medicationChoice and
    //severity level are null. This should ONLY occur during a JUnit test.
    try {
      severityLevel = db.questionnaireDao().findSeverityLevel().getValue();
      medicationChoice = db.medicationChoiceDAO().getMedication().getValue();
      if(medicationChoice.equals("ABSTAIN")){}
      if(severityLevel.equals("MODERATE")){}
    } catch (NullPointerException e) {
      medicationChoice = "ABSTAIN";
      severityLevel = "MODERATE";
    }

    //A new treatmentPlanEntity to add to the database
    this.treatmentPlan = new TreatmentPlanEntity();

    if (medicationChoice.equals("ABSTAIN")) {
      if (severityLevel.equals("MILD")) { //Mild Abstinence
        treatmentPlan.setNumCounseling(1);
        treatmentPlan.setNumSupportMeeting(1);
        treatmentPlan.setNumLessons(1);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(1);
        treatmentPlan.setNumTimeTracking(1);
        treatmentPlan.setNumReadingResponse(1);
        treatmentPlan.setNumMedManagement(0);
        treatmentPlan.setMedManagementMonthly();
        treatmentPlan.setOutcomeMeasureWeekly();
      } else if (severityLevel.equals("MODERATE")) { //Moderate Abstinence
        treatmentPlan.setNumCounseling(3);
        treatmentPlan.setNumSupportMeeting(3);
        treatmentPlan.setNumLessons(2);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(3);
        treatmentPlan.setNumTimeTracking(2);
        treatmentPlan.setNumReadingResponse(2);
        treatmentPlan.setNumMedManagement(0);
        treatmentPlan.setMedManagementMonthly();
        treatmentPlan.setOutcomeMeasureDaily();
      } else { //Severe Abstinence
        treatmentPlan.setNumCounseling(5);
        treatmentPlan.setNumSupportMeeting(5);
        treatmentPlan.setNumLessons(3);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(5);
        treatmentPlan.setNumTimeTracking(5);
        treatmentPlan.setNumReadingResponse(3);
        treatmentPlan.setNumMedManagement(0);
        treatmentPlan.setMedManagementWeekly();
        treatmentPlan.setOutcomeMeasureDaily();
      }
    } else if (medicationChoice.equals("BUPRENORPHINE")) {
      //Mild Buprenorphine plan does not exist, it will be set to the same as mild abstinence.
      if (severityLevel.equals("MILD")) {
        treatmentPlan.setNumCounseling(1);
        treatmentPlan.setNumSupportMeeting(1);
        treatmentPlan.setNumLessons(1);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(1);
        treatmentPlan.setNumTimeTracking(1);
        treatmentPlan.setNumReadingResponse(1);
        treatmentPlan.setNumMedManagement(0);
        treatmentPlan.setMedManagementMonthly();
        treatmentPlan.setOutcomeMeasureWeekly();
      } else if (severityLevel.equals("MODERATE")) { //Moderate Buprenorphine
        treatmentPlan.setNumCounseling(3);
        treatmentPlan.setNumSupportMeeting(3);
        treatmentPlan.setNumLessons(2);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(3);
        treatmentPlan.setNumTimeTracking(2);
        treatmentPlan.setNumReadingResponse(2);
        treatmentPlan.setNumMedManagement(2);
        treatmentPlan.setMedManagementMonthly();
        treatmentPlan.setOutcomeMeasureDaily();
      } else { //Severe Buprenorphine
        treatmentPlan.setNumCounseling(5);
        treatmentPlan.setNumSupportMeeting(5);
        treatmentPlan.setNumLessons(3);
        treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
        treatmentPlan.setNumOutcomeMeasures(5);
        treatmentPlan.setNumTimeTracking(5);
        treatmentPlan.setNumReadingResponse(3);
        treatmentPlan.setNumMedManagement(1);
        treatmentPlan.setMedManagementWeekly();
        treatmentPlan.setOutcomeMeasureDaily();
      }
    }
    treatmentPlan.setId(1);
    this.insertDb();
  }

  /**
   * Inserts treatment plan into the database using a background thread
   *
   * @param treatmentPlanEntity the entity to be added to the database.
   */
  void insert(TreatmentPlanEntity treatmentPlanEntity) {
    new insertAsyncTask(treatmentPlanDao).execute(treatmentPlanEntity);
  }

  private static class insertAsyncTask extends AsyncTask<TreatmentPlanEntity, Void, Void> {

    private TreatmentPlanDao mAsyncTaskDao;

    insertAsyncTask(TreatmentPlanDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final TreatmentPlanEntity... params) {
      mAsyncTaskDao.insertTreatmentPlanEntity(params[0]);
      return null;
    }
  }

  /**
   * The apps loaded Database.
   */
  private VolitionDatabase db;

  /**
   * The treatmentPlans Dao
   */
  private TreatmentPlanDao treatmentPlanDao;
}
