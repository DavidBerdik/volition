package com.recoveryenhancementsolutions.volition;

import android.app.Application;

import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.recoveryenhancementsolutions.volition.Questionnaire;

import java.util.List;

public class QuestionnaireActivityViewModel extends AndroidViewModel {

    public final LiveData<List<Questionnaire>> questionnaire;

    private VolitionDatabase modelDB;

    public QuestionnaireActivityViewModel(Application application) {
        super(application);
        createDb();

        questionnaire = modelDB.questionnaireModel().insertQuestionnaire(); //Needs what to pass
    }

    public void createDb() {
        modelDB = VolitionDatabase.getDatabase(this.getApplication());
    }
}