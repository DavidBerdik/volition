package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.os.AsyncTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * This class abstracts the database away from the view severity level activity and allows it to
 * view LiveData objects taken from the database.
 */
public class TreatmentExperienceAssessmentViewModel extends AndroidViewModel {

  public LiveData<List<UserActivityEntity>> data;
  public UserActivityViewModel activityViewModel;
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

  /**
   * Adds both a TEA entity and a user activity entity to the database.
   * @param teaAnswers tea list array answers
   * @param remarksString string entered.
   */
  public void addTreatmentExperienceAssessment(final ArrayList<Integer> teaAnswers, final String remarksString) {

    final TreatmentExperienceAssessmentEntity teaActivityEntity = new TreatmentExperienceAssessmentEntity();
    teaActivityEntity.setSubstanceInt(teaAnswers.get(0));
    teaActivityEntity.setHealthInt(teaAnswers.get(1));
    teaActivityEntity.setLifestyleInt(teaAnswers.get(2));
    teaActivityEntity.setCommunityInt(teaAnswers.get(3));
    teaActivityEntity.setRemarksString(remarksString);

    new PopulateDbAsync(db.treatmentExperienceAssessmentDao()).execute(teaActivityEntity);

    Date date = Calendar.getInstance().getTime();
    final UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(date);
    entity.setDesc("TEA completed");
    new InsertActivityAsync(db.userActivitiesDao()).execute(entity);
  }


  /**
   * Asynchronously processes the database.
   */
  private static class PopulateDbAsync extends
      AsyncTask<TreatmentExperienceAssessmentEntity, Void, Void> {

    private PopulateDbAsync(final TreatmentExperienceAssessmentDao d) {
      dao = d;
    }

    @Override
    protected Void doInBackground(final TreatmentExperienceAssessmentEntity... params) {
      dao.insertTEA(params[0]);
      return null;
    }
    private TreatmentExperienceAssessmentDao dao;
  }

  /**
   * Asynchronously inserts TEA completion activity into database.
   */
  private static class InsertActivityAsync extends
          AsyncTask<UserActivityEntity, Void, Void> {

    private InsertActivityAsync(final UserActivitiesDao d) {
      dao = d;
    }

    @Override
    protected Void doInBackground(final UserActivityEntity... params) {
      dao.insertActivity(params[0]);
      return null;
    }
    private UserActivitiesDao dao;
  }
  private static ArrayList<Integer> teaAnswers = new ArrayList<>();
  public VolitionDatabase db;

}