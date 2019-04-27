package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
@SuppressWarnings("WeakerAccess")

public class TreatmentExperienceAssessmentViewModel extends AndroidViewModel {

  /**
   * Fills the answer array list with temp values to be changed later, mostly to set the length of
   * the array to have values set later
   */
  public void fillTeaAnswers() {
    for (int i = 0; i < 4; i++) {
      teaAnswers.add(0);
    }
  }

  /**
   * allows for the activity to change the value of the remarks while protecting from
   * reconstruction
   *
   * @param remarks String entered by user to be stored in database
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * allows the activity access to the private variable displayState stored in view model to protect
   * from activity reconstruction
   *
   * @return int representing how many questions of the questionnaire have been answered
   */
  public int getDisplayState() {
    return displayState;
  }

  /**
   * allows for the activity to change the value of the display state
   *
   * @param displayState int representing how many questions have been answered
   */
  public void setDisplayState(int displayState) {
    this.displayState = displayState;
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
   * Sets a specific location in the teaAnswers array list to a value given by user and sent by the
   * activity
   *
   * @param value int representing number entered by user
   */
  public void setTeaAnswers(int value) {
    teaAnswers.set(displayState, value);
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
   * This method creates a task to query the database from an asynchronous thread.
   */
  public void insTEA() {
    final InsertActivityAsync task = new InsertActivityAsync(db, teaAnswers, remarks);
    task.execute();
  }

  /**
   * Creates the database.
   */
  private void createDb() {
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Adds both a TEA entity and a user activity entity to the database.
   *
   * @param teaAnswers tea list array answers
   * @param remarksString string entered.
   */
  private static void addTreatmentExperienceAssessment(VolitionDatabase db,
      final ArrayList<Integer> teaAnswers, final String remarksString) {

    final TreatmentExperienceAssessmentEntity teaActivityEntity =
        new TreatmentExperienceAssessmentEntity();
    teaActivityEntity.setPatientId(1);
    teaActivityEntity.setSubstanceInt(teaAnswers.get(0));
    teaActivityEntity.setHealthInt(teaAnswers.get(1));
    teaActivityEntity.setLifestyleInt(teaAnswers.get(2));
    teaActivityEntity.setCommunityInt(teaAnswers.get(3));
    teaActivityEntity.setRemarksString(remarksString);
    db.treatmentExperienceAssessmentDao().insertTEA(teaActivityEntity);

    Date date = Calendar.getInstance().getTime();
    final UserActivityEntity entity = new UserActivityEntity();
    entity.setDate(date);
    entity.setDesc("TEA completed");
    entity.setNotes(remarksString);
    db.userActivitiesDao().insertActivity(entity);
  }


  /**
   * Asynchronously inserts TEA completion activity into database.
   */
  private static class InsertActivityAsync extends AsyncTask<Void, Void, Void> {

    private InsertActivityAsync(VolitionDatabase db, ArrayList<Integer> teaAnswers, String remarks) {
      this.db = db;
      this.remarks = remarks;
      this.teaAnswers = teaAnswers;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      addTreatmentExperienceAssessment(db, teaAnswers, remarks);
      return null;
    }

    private ArrayList<Integer> teaAnswers;
    private VolitionDatabase db;
    private String remarks;
  }

  private ArrayList<Integer> teaAnswers = new ArrayList<>();
  private VolitionDatabase db;
  private int displayState;
  private String remarks;

}