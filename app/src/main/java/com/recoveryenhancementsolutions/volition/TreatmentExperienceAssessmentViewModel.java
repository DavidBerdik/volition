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
   * allows the activity access to the private variable displayState stored in view model to protect
   * from activity reconstruction
   *
   * @return int representing how many questions of the questionnaire have been answered
   */

  public int getSubstanceInt() {
    return substanceInt;
  }

  public int getHealthInt() {
    return healthInt;
  }
  /**
   * allows for the activity to change the value of the display state
   *
   * @param displayState int representing how many questions have been answered
   */
  public void setDisplayState(int displayState) {
    this.displayState = displayState;
  }
  public int getLifestyleInt() {
    return lifestyleInt;
  }

  public int getPatientId() {
    return patientId;
  }

  /**
   * gets an int between 1-10 regarding the objects community question
   *
   * @return the answer recorded for the community question
   */
  public int getCommunityInt() {
    return communityInt;
  }

  /**
   * gets an string of personal thoughts regarding the objects substance use questions
   *
   * @return the answer recorded for the substance use questions
   */
  public String getRemarksString() {
    return remarksString;
  }



  /**
   * Fills the answer array list with temp values to be changed later, mostly to set the length of
   * the array to have values set later
   */
  public void fillTeaAnswers() {
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
    teaAnswers.add(false);
  }

  /**
   * Sets a specific location in the questionnaireAnswers array list to a value given by user and
   * sent by the activity
   *
   * @param value boolean representing yes or no given by user
   */
  public void setQuestionnaireAnswers(Boolean value) {
    teaAnswers.set(displayState, value);
  }

  /**
   * This method creates a task to query the database from an asynchronous thread.
   */
  public void insertTreatmentExperienceAssessment() {

    final PopulateDbAsync task = new PopulateDbAsync(db, teaAnswers, remarksString);
    task.execute();
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

  public void createDb() {
    db = VolitionDatabase.getDatabase(this.getApplication());
  }


  public static void addTreatmentExperienceAssessment(VolitionDatabase db,
      final ArrayList<Integer> teaAnswers) {

    teaActivityEntity.setSubstanceInt(teaAnswers.get(0));
    teaActivityEntity.setHealthInt(teaAnswers.get(1));
    teaActivityEntity.setLifestyleInt(teaAnswers.get(2));
    teaActivityEntity.setCommunityInt(teaAnswers.get(3));





    //db.treatmentExperienceAssessmentDao().insertTEA(teaActivityEntity);
  }

  public static void addRemarks(VolitionDatabase db, String remarksString) {
    teaActivityEntity.setRemarksString(remarksString);
    db.treatmentExperienceAssessmentDao().insertTEA(teaActivityEntity);
  }


  /**
   * Asynchronously processes the database.
   */
  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    public PopulateDbAsync(VolitionDatabase db, ArrayList<Boolean> teaAnswers,
        String remarksString) {
      this.db = db;
      this.remarksString = remarksString;
      this.teaAnswers = teaAnswers;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      addTreatmentExperienceAssessment(db, TreatmentExperienceAssessmentActivity.teaAnswers);
      return null;
    }

    private VolitionDatabase db;
    private ArrayList<Boolean> teaAnswers;
    private String remarksString;
  }

  private static ArrayList<Boolean> teaAnswers = new ArrayList<>();
  private VolitionDatabase db;
  private int displayState = 0;
  private int substanceInt = 0;
  private int healthInt = 0;
  private int lifestyleInt = 0;
  private int communityInt = 0;
  private int patientId = 0;
  private static String remarksString;
  private static TreatmentExperienceAssessmentEntity teaActivityEntity = new TreatmentExperienceAssessmentEntity();
}