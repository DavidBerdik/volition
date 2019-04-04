package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

/**
 * Class manages relationship between the TreatmentPlanActivity and the Volition Database.
 */
public class TreatmentPlanViewModel extends AndroidViewModel {

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
  public void loadTreatmentPlan() {
    treatmentPlan = db.treatmentPlanDao().getTreatmentPlan().getValue();
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
    this.treatmentPlan = db.treatmentPlanDao().getTreatmentPlan().getValue();
  }

  /**
   * Generates a new treatmentPlan.
   */
  public void generateTreatmentPlan() {
    String medicationChoice = "", severityLevel = "";

    //imports severity level and medication choice from the database. sets to uppercase to
    //prevent any issues with misnamed strings. Try catch statement generates a dummy treatment
    //plan set to moderate abstinence if the database tables containing medicationChoice and
    //severity level are null. This should ONLY occur during a JUnit test.
    try {
      severityLevel = db.questionnaireDao().getSeverityLevel().getValue();
      medicationChoice = db.medicationChoiceDAO().getMedication().getValue().medication;
    } catch (NullPointerException e) {
      if(medicationChoice.equals("")){
        medicationChoice = "ABSTAIN";
      }
      if(severityLevel.equals("")) {
        severityLevel = "MODERATE";
      }
    }

    //A new treatmentPlanEntity to add to the database
    this.treatmentPlan = new TreatmentPlanEntity();
    if (severityLevel.equals("MILD")) { //There is no mild Buprenorphine plan currently
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
    } else if (severityLevel.equals("MODERATE")) {
      treatmentPlan.setNumCounseling(3);
      treatmentPlan.setNumSupportMeeting(3);
      treatmentPlan.setNumLessons(2);
      treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
      treatmentPlan.setNumOutcomeMeasures(3);
      treatmentPlan.setNumTimeTracking(2);
      treatmentPlan.setNumReadingResponse(2);
      treatmentPlan.setMedManagementMonthly();
      treatmentPlan.setOutcomeMeasureDaily();

      //handles differences in treatment plans
      if (medicationChoice.equals("ABSTAIN")) {
        treatmentPlan.setNumMedManagement(0);
      } else {
        treatmentPlan.setNumMedManagement(2);
      }
    } else { //Severe severity level
      treatmentPlan.setNumCounseling(5);
      treatmentPlan.setNumSupportMeeting(5);
      treatmentPlan.setNumLessons(3);
      treatmentPlan.setNumTreatmentEffectivenessAssessment(1);
      treatmentPlan.setNumOutcomeMeasures(5);
      treatmentPlan.setNumTimeTracking(5);
      treatmentPlan.setNumReadingResponse(3);
      treatmentPlan.setMedManagementWeekly();
      treatmentPlan.setOutcomeMeasureDaily();

      //handles differences in treatment plans
      if (medicationChoice.equals("ABSTAIN")) {
        treatmentPlan.setNumMedManagement(0);
      } else {
        treatmentPlan.setNumMedManagement(1);
      }
    }
    treatmentPlan.setId(1); //Prevents the creation of multiple treatment plans
    this.insertDb();
  }

  /**
   * Returns the number of recommended counseling sessions to attend.
   *
   * @return An integer representing the number of counseling sessions to attend.
   */
  public int getNumCounseling() {
    return treatmentPlan.getNumCounseling();
  }

  /**
   * Returns the number of recommended support meetings.
   *
   * @return An integer representing the number of support meetings to attend.
   */
  public int getNumSupportMeeting() {
    return treatmentPlan.getNumSupportMeeting();
  }

  /**
   * Returns the number of recommended lessons.
   *
   * @return An integer representing the number of lessons to attend.
   */
  public int getNumLessons() {
    return treatmentPlan.getNumLessons();
  }

  /**
   * Returns the number of recommended treatmentplan effectiveness assessments.
   *
   * @return An integer representing the number of treatmentplan effectiveness assessments to take.
   */
  public int getNumTreatmentEffectivenessAssessment() {
    return treatmentPlan.getNumTreatmentEffectivenessAssessment();
  }

  /**
   * Returns the number of recommended reading responses to complete.
   *
   * @return An integer representing the number of reading responses to complete.
   */
  public int getNumReadingResponse() {
    return treatmentPlan.getNumReadingResponse();
  }

  /**
   * Returns the number of recommended medication managements.
   *
   * @return An integer representing the number of medication managements to do.
   */
  public int getNumMedManagement() {
    return treatmentPlan.getNumMedManagement();
  }

  /**
   * Returns the frequency of recommended medication management.
   *
   * @return A string representing the frequency of medication management.
   */
  public String getMedManagementFrequency() {
    return treatmentPlan.getMedManagementFrequency();
  }

  /**
   * Returns the frequency of recommended outcome measures.
   *
   * @return A string representing the frequency of outcome measures.
   */
  public String getOutcomMeasureFrequency() {
    return treatmentPlan.getOutcomeMeasureFrequency();
  }

  /**
   * Returns the loaded treatmentPlan
   *
   * @return The treatmentPlanEntity loaded from the database.
   */
  public TreatmentPlanEntity getTreatmentPlan() {
    return treatmentPlan;
  }

  /**
   * Inserts treatment plan into the database using a background thread
   *
   * @param treatmentPlanEntity the entity to be added to the database.
   */
  void insert(final TreatmentPlanEntity treatmentPlanEntity) {
    new insertAsyncTask(treatmentPlanDao).execute(treatmentPlanEntity);
  }

  private static class insertAsyncTask extends AsyncTask<TreatmentPlanEntity, Void, Void> {

    private final TreatmentPlanDao mAsyncTaskDao;

    insertAsyncTask(final TreatmentPlanDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final TreatmentPlanEntity... params) {
      mAsyncTaskDao.insertTreatmentPlanEntity(params[0]);
      return null;
    }
  }

  /**
   * The treatment plan entity loaded from the database.
   */
  private TreatmentPlanEntity treatmentPlan;

  /**
   * The apps loaded Database.
   */
  private VolitionDatabase db;

  /**
   * The treatmentPlans Dao
   */
  private TreatmentPlanDao treatmentPlanDao;
}
