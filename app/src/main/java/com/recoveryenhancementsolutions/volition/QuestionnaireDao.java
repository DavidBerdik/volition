package com.recoveryenhancementsolutions.volition;

/**
 * Temporary file taken from feature/VOL-50-View-Severity-Level
 * <p>
 * Should be written over by feature/VOL-50-View-Security-Level
 */

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface QuestionnaireDao {

  @Insert(onConflict = REPLACE)
  void insertQuestionnaire(QuestionnaireEntity questionnaire);

  @Query("DELETE FROM QuestionnaireEntity")
  void deleteAll();

  @Query("SELECT * FROM QuestionnaireEntity")
  LiveData<List<QuestionnaireEntity>> findQuestionnaire();

  @Query("SELECT severityLevel FROM QuestionnaireEntity")
  LiveData<String> getSeverityLevel();

}
