package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
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
  @Query("select * from TreatmentPlanEntity where treatmentPlanID = 1")
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
  @Insert(onConflict = IGNORE)
  void insertTreatmentPlanEntity(TreatmentPlanEntity treatmentPlanEntity);

  /**
   * Removes all TreatmentPlanEntities from the database.
   */
  @Query("DELETE FROM TreatmentPlan")
  void deleteAll();
}
