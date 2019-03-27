package com.recoveryenhancementsolutions.volition;



import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface QuestionnaireDao {

  @Insert(onConflict = REPLACE)
  void insertQuestionnaire(QuestionnaireActivityEntity questionnaireActivityEntity);
  @Query("DELETE FROM QuestionnaireActivityEntity")
  void deleteAll();

  @Query("SELECT * FROM QuestionnaireActivityEntity")
  LiveData<List<QuestionnaireActivityEntity>> findQuestionnaire();

  @Query("SELECT severityLevel FROM QuestionnaireActivityEntity")
  String findSeverityLevel();

  @Query("SELECT severityLevel FROM QuestionnaireActivityEntity")
  LiveData<String> findSeverityLevelString();

  @Query("SELECT totalYes FROM QuestionnaireActivityEntity")
  LiveData<String> findTotalYes();

  @Query("SELECT q1 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ1();

  @Query("SELECT q2 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ2();

  @Query("SELECT q3 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ3();

  @Query("SELECT q4 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ4();

  @Query("SELECT q5 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ5();

  @Query("SELECT q6 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ6();

  @Query("SELECT q7 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ7();

  @Query("SELECT q8 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ8();

  @Query("SELECT q9 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ9();

  @Query("SELECT q10 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ10();

  @Query("SELECT q11 FROM QuestionnaireActivityEntity")
  LiveData<Integer> findQ11();


}
