package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.Nullable;

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
    medObserved = false;
    questionnaireObserved = false;
    db = VolitionDatabase.getDatabase(this.getApplication());
    treatmentPlan = db.treatmentPlanDao().getTreatmentPlan();
  }

  /**
   * Updates the database with the values of treatmentPlan.
   */
  public void insertDb(TreatmentPlanEntity treatmentPlanEntity) {
    db.treatmentPlanDao().insertTreatmentPlanEntity(treatmentPlanEntity);
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
    this.treatmentPlan = db.treatmentPlanDao().getTreatmentPlan();
  }

  /**
   * Returns the live data object containing the treatment plan.
   *
   * @return A live data object containing a treatmentPlanEntity.
   */
  public LiveData<TreatmentPlanEntity> getTreatmentPlan(){
    return treatmentPlan;
  }

  /**
   * Generates a new treatmentPlan.
   */
  private void generateTreatmentPlan() {
    String medicationChoice = "", severityLevel = "";

    //imports severity level and medication choice from the database. sets to uppercase to
    //prevent any issues with misnamed strings. Try catch statement generates a dummy treatment
    //plan set to moderate abstinence if the database tables containing medicationChoice and
    //severity level are null. This should ONLY occur during a JUnit test.
    try {
      severityLevel = db.questionnaireDao().getSeverityLevel().getValue();
      medicationChoice = db.medicationChoiceDAO().getMedication().getValue().medication;
    } catch (NullPointerException e) {
      if (medicationChoice.equals("")) {
        medicationChoice = "ABSTAIN";
      }
      if (severityLevel.equals("")) {
        severityLevel = "MODERATE";
      }
    }

    //A new treatmentPlanEntity to add to the database
    TreatmentPlanEntity newTreatmentPlan = new TreatmentPlanEntity();
    if (severityLevel.equals("MILD")) { //There is no mild Buprenorphine plan currently
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
    } else if (severityLevel.equals("MODERATE")) {
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
      if (medicationChoice.equals("ABSTAIN")) {
        newTreatmentPlan.setNumMedManagement(0);
      } else {
        newTreatmentPlan.setNumMedManagement(2);
      }
    } else { //Severe severity level
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
      if (medicationChoice.equals("ABSTAIN")) {
        newTreatmentPlan.setNumMedManagement(0);
      } else {
        newTreatmentPlan.setNumMedManagement(1);
      }
    }
    this.insertDb(newTreatmentPlan);
  }

  /**
   * Observes the medication choice table in the database. Generates a treatment plan if the
   * questionnaire has already been loaded as well.
   */
  private Observer<MedicationChoiceEntity> medObserver = new Observer<MedicationChoiceEntity>() {
    @Override
    public void onChanged(final MedicationChoiceEntity medicationChoiceEntity) {
      medObserved = true;
      if (questionnaireObserved) {
        generateTreatmentPlan();
      }
    }
  };

  /**
   * Observes the questionnaire table in the database. Generates a treatment plan if the medication
   * choice has already been loaded as well.
   */
  private Observer<QuestionnaireEntity> questionnaireObserver = new Observer<QuestionnaireEntity>() {
    @Override
    public void onChanged(@Nullable QuestionnaireEntity questionnaireEntity) {
      questionnaireObserved = true;
      if(medObserved){
        generateTreatmentPlan();
      }
    }
  };

  /**
   * Inserts treatment plan into the database using a background thread
   *
   * @param treatmentPlanEntity the entity to be added to the database.
   */
  void insert(final TreatmentPlanEntity treatmentPlanEntity) {
    new insertAsyncTask(treatmentPlanDao).execute(treatmentPlanEntity);
  }

  /**
   * Used to insert data into the database asynchronously
   */
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
   * A boolean tracking if the medicaton choice has loaded in.
   */
  private boolean medObserved;

  /**
   * A boolean tracking if the questionnaire has been loaded in.
   */
  private boolean questionnaireObserved;

  /**
   * Live data containing the treatment plan entity loaded from the database.
   */
  private LiveData<TreatmentPlanEntity> treatmentPlan;

  /**
   * A live data object storing the medication choice entity from the database.
   */
  private LiveData<MedicationChoiceEntity> medicationChoiceEntity;


  /**
   * A live data object storing the questionnaire from the database.
   */
  private LiveData<QuestionnaireEntity> questionnaireEntity;

  /**
   * The apps loaded Database.
   */
  private VolitionDatabase db;

  /**
   * The treatmentPlans Dao
   */
  private TreatmentPlanDao treatmentPlanDao;
}