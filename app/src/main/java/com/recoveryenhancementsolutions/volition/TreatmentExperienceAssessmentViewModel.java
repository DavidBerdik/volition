package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import java.util.ArrayList;

/**
 * This class abstracts the database away from the view severity level activity and allows it to
 * view LiveData objects taken from the database.
 */
public class TreatmentExperienceAssessmentViewModel extends AndroidViewModel {
  /**
   * Fills the answer array list with temp values to be changed later, mostly to set the length of
   * the array to have values set later
   */
  private void fillTeaAnswers() {
    for (int i = 0; i < 4; i++) {
      teaAnswers.add(0);
    }
  }

  /**
   * This method creates the database.
   *
   * @param application passes in the application to set the view model.
   */
  public TreatmentExperienceAssessmentViewModel(Application application) {
    super(application);
    createDb();
  }

  /**
   * This method sets the test database when we run the test code.
   *
   * @param db passes in the database.
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.db = db;
  }

  /**
   * Creates the database.
   */

  private void createDb() {
    db = VolitionDatabase.getDatabase(this.getApplication());
    fillTeaAnswers();
  }

  public static void addTreatmentExperienceAssessment(VolitionDatabase db,
      final ArrayList<Integer> teaAnswers, String remarksString) {

    teaActivityEntity.setSubstanceInt(teaAnswers.get(0));
    teaActivityEntity.setHealthInt(teaAnswers.get(1));
    teaActivityEntity.setLifestyleInt(teaAnswers.get(2));
    teaActivityEntity.setCommunityInt(teaAnswers.get(3));
    teaActivityEntity.setRemarksString(remarksString);

    new PopulateDbAsync(db).execute(teaActivityEntity);
  }

  /**
   * Asynchronously processes the database.
   */
  private static class PopulateDbAsync extends
      AsyncTask<TreatmentExperienceAssessmentEntity, Void, Void> {

    private PopulateDbAsync(final VolitionDatabase db) {
      this.db = db;
    }

    @Override
    protected Void doInBackground(final TreatmentExperienceAssessmentEntity... params) {
      this.db.treatmentExperienceAssessmentDao().insertTEA(params[0]);
      return null;
    }
    private VolitionDatabase db;
  }
  private static ArrayList<Integer> teaAnswers = new ArrayList<>();
  private VolitionDatabase db;
  private static TreatmentExperienceAssessmentEntity teaActivityEntity = new TreatmentExperienceAssessmentEntity();
}