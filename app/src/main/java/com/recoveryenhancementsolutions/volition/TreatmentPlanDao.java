package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * TreatmentPlanDao handles the TreatmentPlan table in the volition database.
 */

@Dao
public interface TreatmentPlanDao {

  @Query("select * from TreatmentPlan where treatmentPlanID = 1")
  LiveData<TreatmentPlan> loadTreatmentPlan();

  @Update(onConflict = REPLACE)
  void updateTreatmentPlan(TreatmentPlan treatmentPlan);

  @Insert(onConflict = IGNORE)
  void insertTreatmentPlan(TreatmentPlan treatmentPlan);
}
