package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

/**
 * DAO for interacting with the TEAEntity
 */
@Dao
public interface TreatmentExperienceAssessmentDao {

  /**
   * Inserts a TEA into the database.
   *
   * @param teaEntity a TEAEntity object containing the object to be inserted.
   */
  @Insert(onConflict = REPLACE)
  void insertTEA(final TreatmentExperienceAssessmentEntity teaEntity);

  /**
   * Retrieves all given answers from the database.
   *
   * @return a LiveData object containing an object of responses
   */
  @Query("SELECT * FROM TreatmentExperienceAssessmentEntity")
  LiveData<TreatmentExperienceAssessmentEntity> getTEA();
}