package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * DAO for the questionnaire for interacting with the questionnaire entity
 */
@Dao
public interface QuestionnaireDao {

  /**
   * takes questionnaire entity and enters it into database
   *
   * @param questionnaireActivityEntity object containing all the values of the questions and other
   * relevant information
   */
  @Insert(onConflict = REPLACE)
  void insertQuestionnaire(QuestionnaireActivityEntity questionnaireActivityEntity);

  /**
   * method to delete everything from the database
   */
  @Query("DELETE FROM QuestionnaireActivityEntity")
  void deleteAll();

  /**
   * query to return severity level
   *
   * @return finds a string of the severity level
   */
  @Query("SELECT severityLevel FROM QuestionnaireActivityEntity")
  LiveData<String> findSeverityLevelString();

  /**
   * returns a string of a number of total yes values given in the questionnaire
   *
   * @return finds string of total yes
   */
  @Query("SELECT totalYes FROM QuestionnaireActivityEntity")
  LiveData<String> findTotalYes();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q1 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ1();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q2 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ2();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q3 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ3();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q4 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ4();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q5 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ5();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q6 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ6();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q7 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ7();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q8 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ8();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q9 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ9();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q10 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ10();

  /**
   * finds boolean of a specific question
   *
   * @return finds value of specific question
   */
  @Query("SELECT q11 FROM QuestionnaireActivityEntity")
  LiveData<Boolean> findQ11();


}