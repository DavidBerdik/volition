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
   * Constructor method to initialize a new TreatmentPlanViewModel
   *
   * @param application The application creating the TreatmentPlanViewModel.
   */
  public TreatmentPlanViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
    treatmentPlanDao = db.treatmentPlanDao();
  }

  /**
   * Inserts a new treatment plan into the database.
   */
  public static void insertTreatmentPlan(TreatmentPlanEntity treatmentPlanEntity) {
    new insertAsyncTask(treatmentPlanDao).execute(treatmentPlanEntity);
  }

  /**
   * Inserts a new treatment plan into the database.
   */
  public static void updateTreatmentPlan(TreatmentPlanEntity treatmentPlanEntity) {
    new updateAsyncTask(treatmentPlanDao).execute(treatmentPlanEntity);
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
    treatmentPlanDao = db.treatmentPlanDao();
  }

  /**
   * Returns the live data object containing the treatment plan.
   *
   * @return A live data object containing a treatmentPlanEntity.
   */
  public LiveData<TreatmentPlanEntity> getTreatmentPlan() {
    return db.treatmentPlanDao().getTreatmentPlan();
  }

  /**
   * Returns a live data object containing the user's medication choice.
   *
   * @return A live data object containing the user's medication choice.
   */
  public LiveData<MedicationChoiceEntity> getMedicationChoiceEntity() {
    return db.medicationChoiceDAO().getMedication();
  }

  /**
   * Returns a live data object containing the user's severity level as a string.
   *
   * @return A string representing the user's severity level.
   */
  public LiveData<QuestionnaireEntity> getQuestionnaireEntity() {
    return db.questionnaireDao().getQuestionnaire();
  }

  /**
   * Used to insert data into the database asynchronously
   */
  private static class insertAsyncTask extends AsyncTask<TreatmentPlanEntity, Void, Void> {

    insertAsyncTask(final TreatmentPlanDao dao) {
      asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final TreatmentPlanEntity... params) {
      asyncTaskDao.insertTreatmentPlanEntity(params[0]);
      return null;
    }

    private final TreatmentPlanDao asyncTaskDao;
  }

  /**
   * Used to update data into the database asynchronously
   */
  private static class updateAsyncTask extends AsyncTask<TreatmentPlanEntity, Void, Void> {

    updateAsyncTask(final TreatmentPlanDao dao) {
      asyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final TreatmentPlanEntity... params) {
      asyncTaskDao.updateTreatmentPlanEntity(params[0]);
      return null;
    }

    private final TreatmentPlanDao asyncTaskDao;
  }

  /**
   * The apps loaded Database.
   */
  private VolitionDatabase db;

  /**
   * The treatmentPlan's Dao
   */
  private static TreatmentPlanDao treatmentPlanDao;
}