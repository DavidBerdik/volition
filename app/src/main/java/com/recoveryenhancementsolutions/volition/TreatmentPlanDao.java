package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * DAO for interacting with the TreatmentPlanEntity
 */
@Dao
public interface TreatmentPlanDao {

  /**
   * Retrieves all treatment plans from the database.  There should only ever be one.
   *
   * @return A LiveData object containing a TreatmentPlan Entity.
   */
  @Query("SELECT * FROM TreatmentPlanEntity WHERE Id = 1")
  LiveData<TreatmentPlanEntity> loadTreatmentPlan();

  /**
   * Updates a TreatmentPlan Entity in the database.
   *
   * @param treatmentPlanEntity The TreatmentPlanEntity to be updated.
   */
  @Update(onConflict = REPLACE)
  void updateTreatmentPlanEntity(TreatmentPlanEntity treatmentPlanEntity);

  /**
   * Inserts a new TreatmentPlanEntity into the database. The treatmentPlanId should always be 1.
   *
   * @param treatmentPlanEntity The TreatmentPlanEntity to be inserted.
   */
  @Insert(onConflict = REPLACE)
  void insertTreatmentPlanEntity(TreatmentPlanEntity treatmentPlanEntity);

  /**
   * Returns the number of entries in the treatment plan table.
   *
   * @return The current number of TreatmentPlanEntities.
   */
  @Query("SELECT COUNT(*) FROM TreatmentPlanEntity")
  Integer getNumTreatmentPlans();

  /**
   * Removes all TreatmentPlanEntities from the database.
   */
  @Query("DELETE FROM TreatmentPlanEntity")
  void deleteAll();
}
