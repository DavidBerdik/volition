package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface QuestionnaireDao {

  @Insert(onConflict = REPLACE)
  void insertQuestionnaire(Questionnaire questionnaire);

  @Query("SELECT * FROM Questionnaire")
  LiveData<List<Questionnaire>> findQuestionnaire();

  @Query("SELECT severityLevel FROM Questionnaire")
  String findSeverityLevel();

  @Query("SELECT severityLevel FROM Questionnaire")
  LiveData<String> findSeverityLevelString();

  @Query("SELECT q1 FROM Questionnaire")
  LiveData<Boolean> findQ1();

  @Query("SELECT q2 FROM Questionnaire")
  LiveData<Boolean> findQ2();

  @Query("SELECT q3 FROM Questionnaire")
  LiveData<Boolean> findQ3();

  @Query("SELECT q4 FROM Questionnaire")
  LiveData<Boolean> findQ4();

  @Query("SELECT q5 FROM Questionnaire")
  LiveData<Boolean> findQ5();

  @Query("SELECT q6 FROM Questionnaire")
  LiveData<Boolean> findQ6();

  @Query("SELECT q7 FROM Questionnaire")
  LiveData<Boolean> findQ7();

  @Query("SELECT q8 FROM Questionnaire")
  LiveData<Boolean> findQ8();

  @Query("SELECT q9 FROM Questionnaire")
  LiveData<Boolean> findQ9();

  @Query("SELECT q10 FROM Questionnaire")
  LiveData<Boolean> findQ10();

  @Query("SELECT q11 FROM Questionnaire")
  LiveData<Boolean> findQ11();


}
