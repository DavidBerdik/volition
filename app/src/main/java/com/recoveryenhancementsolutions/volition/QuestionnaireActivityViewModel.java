package com.recoveryenhancementsolutions.volition;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;

import android.os.AsyncTask;

/**
 * This class abstracts the database away from the view severity level activity and allows it to
 * view LiveData objects taken from the database.
 */
public class QuestionnaireActivityViewModel extends AndroidViewModel {

  /**
   * This method creates a task to query the database from an asynchronous thread.
   *
   * @param db connection to the database class
   */
  public static void populateAsync(final VolitionDatabase db) {

    PopulateDbAsync task = new PopulateDbAsync(db);
    task.execute();
  }


  /**
   * This method creates the database.
   * @param application passes in the application to set the view model.
   */
  public QuestionnaireActivityViewModel(Application application) {
    super(application);
    createDb();
  }

  /**
   * This method sets the test database when we run the test code.
   * @param db passes in the database.
   */
  public void setTestDatabase(final VolitionDatabase db) {
    this.mDb = db;
  }

  /**
   * Creates the database.
   */

  public void createDb() {
    mDb = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Receives boolean variables of answers to each questions in the questionnaire and stores into
   * the entity.
   * @param questionOneAnswer answer to question 1.
   * @param questionTwoAnswer answer to question 2.
   * @param questionThreeAnswer answer to question 3.
   * @param questionFourAnswer answer to question 4.
   * @param questionFiveAnswer answer to question 5.
   * @param questionSixAnswer answer to question 6.
   * @param questionSevenAnswer answer to question 7.
   * @param questionEightAnswer answer to question 8.
   * @param questionNineAnswer answer to question 9.
   * @param questionTenAnswer answer to question 10.
   * @param questionElevenAnswer answer to question 11.
   * @param yesAnswers number of yes answers in the questionnaire.
   * @param severityString name of the severity level based on yes answers in the questionnaire.
   */
  public static void addQuestionnaire(final boolean questionOneAnswer, final boolean questionTwoAnswer,final boolean questionThreeAnswer, final
      boolean questionFourAnswer, final boolean questionFiveAnswer, final boolean questionSixAnswer,final
      boolean questionSevenAnswer, final boolean questionEightAnswer, final boolean questionNineAnswer, final boolean questionTenAnswer, final
      boolean questionElevenAnswer, final int yesAnswers, final String severityString) {
    QuestionnaireActivityEntity questionnaireActivityEntity = new QuestionnaireActivityEntity();
    questionnaireActivityEntity.setQ1(questionOneAnswer);
    questionnaireActivityEntity.setQ2(questionTwoAnswer);
    questionnaireActivityEntity.setQ3(questionThreeAnswer);
    questionnaireActivityEntity.setQ4(questionFourAnswer);
    questionnaireActivityEntity.setQ5(questionFiveAnswer);
    questionnaireActivityEntity.setQ6(questionSixAnswer);
    questionnaireActivityEntity.setQ7(questionSevenAnswer);
    questionnaireActivityEntity.setQ8(questionEightAnswer);
    questionnaireActivityEntity.setQ9(questionNineAnswer);
    questionnaireActivityEntity.setQ10(questionTenAnswer);
    questionnaireActivityEntity.setQ11(questionElevenAnswer);
    String totalYes = Integer.toString(yesAnswers);
    questionnaireActivityEntity.setTotalYes(totalYes);
    questionnaireActivityEntity.setId(1);

    questionnaireActivityEntity.setSeverityLevel(severityString);
    mDb.questionnaireModel().insertQuestionnaire(questionnaireActivityEntity);
  }

  /**
   * Calls add questionnaire and sends in the answers to the entity.
   * @param db is the database.
   */
  public static void populateWithData(final VolitionDatabase db) {
    addQuestionnaire(QuestionnaireActivity.qOneAnswer, QuestionnaireActivity.qTwoAnswer,
        QuestionnaireActivity.qThreeAnswer, QuestionnaireActivity.qFourAnswer,
        QuestionnaireActivity.qFiveAnswer,
        QuestionnaireActivity.qSixAnswer, QuestionnaireActivity.qSevenAnswer,
        QuestionnaireActivity.qEightAnswer, QuestionnaireActivity.qNineAnswer,
        QuestionnaireActivity.qTenAnswer
        , QuestionnaireActivity.qElevenAnswer, QuestionnaireActivity.yesAnswers,
        QuestionnaireActivity.severityString);

  }

  /**
   * Asynchronously processes the database.
   */
  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {


    private final VolitionDatabase mDb;

    PopulateDbAsync(VolitionDatabase db) {
      mDb = db;
    }

    @Override
    protected Void doInBackground(final Void... params) {
      populateWithData(mDb);
      return null;
    }

  }

  private static VolitionDatabase mDb;
}
