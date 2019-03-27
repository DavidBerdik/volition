package com.recoveryenhancementsolutions.volition;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

public class QuestionnaireActivityViewModel extends AndroidViewModel {

    //public final LiveData<List<QuestionnaireActivityEntity>> questionnaire;

    private VolitionDatabase modelDB;
    private VolitionDatabase mDb;

    public QuestionnaireActivityViewModel(Application application) {
        super(application);
        createDb();
        mDb = VolitionDatabase.getDatabase(this.getApplication());

       // questionnaire = modelDB.questionnaireModel().insertQuestionnaire(); //Needs what to pass
    }

    public void createDb() {
        modelDB = VolitionDatabase.getDatabase(this.getApplication());
    }
    public void addQuestionnaire(boolean qOneAnswer, boolean qTwoAnswer, boolean qThreeAnswer, boolean qFourAnswer, boolean qFiveAnswer, boolean qSixAnswer, boolean qSevenAnswer, boolean qEightAnswer, boolean qNineAnswer, boolean qTenAnswer, boolean qElevenAnswer, int yesAnswers, String severityString )
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
}
