package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.AsyncTask;

/**
 * Database entity for storing the Questionnaire information.
 */
@Entity
public class Questionnaire {

  @PrimaryKey

  public int id;
  public int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
  public String severityLevel;



  private static Questionnaire addQuestionnaire(final VolitionDatabase db, final int id,
      final int q1, final int q2,final int q3,final int q4,final int q5,final int q6,final int q7,
      final int q8,final int q9,final int q10,final int q11,final String severityLevel) {
    Questionnaire questionnaire = new Questionnaire();
    questionnaire.id = id;
    questionnaire.q1 = q1;
    questionnaire.q1 = q2;
    questionnaire.q1 = q3;
    questionnaire.q1 = q4;
    questionnaire.q1 = q5;
    questionnaire.q1 = q6;
    questionnaire.q1 = q7;
    questionnaire.q1 = q8;
    questionnaire.q1 = q9;
    questionnaire.q1 = q10;
    questionnaire.q1 = q11;
    questionnaire.severityLevel = severityLevel;
    db.questionnaireModel().insertQuestionnaire(questionnaire);
    return questionnaire;
  }

  private static void populateWithTestData(VolitionDatabase db) {
    db.questionnaireModel().deleteAll();


    addQuestionnaire(db, 1, 1, 1,1,1,1,1,1,1,1,1,1,"Severe");
    addQuestionnaire(db, 2, 1, 1,1,1,0,0,0,0,0,0,0,"Moderate");
    addQuestionnaire(db, 3, 1, 1,0,0,0,0,0,0,0,0,0,"Mild");


  }
}