package com.recoveryenhancementsolutions.volition;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.Date;
import java.util.List;

public class QuestionnaireActivityViewModel extends AndroidViewModel {

    //public final LiveData<List<QuestionnaireActivityEntity>> questionnaire;
    public static void populateAsync(final VolitionDatabase db) {

        PopulateDbAsync task = new PopulateDbAsync(db);
        task.execute();
    }
    private VolitionDatabase modelDB;
    private static VolitionDatabase mDb;

    public QuestionnaireActivityViewModel(Application application) {
        super(application);
        createDb();
        mDb = VolitionDatabase.getDatabase(this.getApplication());

       // questionnaire = modelDB.questionnaireModel().insertQuestionnaire(); //Needs what to pass
    }

    public void createDb() {
        modelDB = VolitionDatabase.getDatabase(this.getApplication());
    }

   private static void insert(QuestionnaireActivityEntity questionnaireActivityEntity)
   {
       mDb.questionnaireModel().insertQuestionnaire(questionnaireActivityEntity);
   }
    public static void addQuestionnaire(boolean qOneAnswer, boolean qTwoAnswer, boolean qThreeAnswer, boolean qFourAnswer, boolean qFiveAnswer, boolean qSixAnswer,
                                         boolean qSevenAnswer, boolean qEightAnswer, boolean qNineAnswer, boolean qTenAnswer, boolean qElevenAnswer, int yesAnswers, String severityString )
    {
        QuestionnaireActivityEntity questionnaireActivityEntity = new QuestionnaireActivityEntity();
        questionnaireActivityEntity.setQ1(qOneAnswer);
        questionnaireActivityEntity.setQ2(qTwoAnswer);
        questionnaireActivityEntity.setQ3(qThreeAnswer);
        questionnaireActivityEntity.setQ4(qFourAnswer);
        questionnaireActivityEntity.setQ5(qFiveAnswer);
        questionnaireActivityEntity.setQ6(qSixAnswer);
        questionnaireActivityEntity.setQ7(qSevenAnswer);
        questionnaireActivityEntity.setQ8(qEightAnswer);
        questionnaireActivityEntity.setQ9(qNineAnswer);
        questionnaireActivityEntity.setQ10(qTenAnswer);
        questionnaireActivityEntity.setQ11(qElevenAnswer);
        String totalYes= Integer.toString(yesAnswers);
        questionnaireActivityEntity.setTotalYes(totalYes);
        questionnaireActivityEntity.setId(1);

        questionnaireActivityEntity.setSeverityLevel(severityString);
        mDb.questionnaireModel().insertQuestionnaire(questionnaireActivityEntity);
    }
    public static void populateWithData(VolitionDatabase db) {
addQuestionnaire(QuestionnaireActivity.qOneAnswer,QuestionnaireActivity.qTwoAnswer,QuestionnaireActivity.qThreeAnswer,QuestionnaireActivity.qFourAnswer,QuestionnaireActivity.qFiveAnswer,
        QuestionnaireActivity.qSixAnswer,QuestionnaireActivity.qSevenAnswer,QuestionnaireActivity.qEightAnswer,QuestionnaireActivity.qNineAnswer,QuestionnaireActivity.qTenAnswer
,QuestionnaireActivity.qElevenAnswer,QuestionnaireActivity.yesAnswers,QuestionnaireActivity.severityString);

    }
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
}
