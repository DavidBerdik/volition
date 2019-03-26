package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * Temporary file, should be overriden by feature/VOL-43-access-database
 */

@Dao
public interface MedicationChoiceDAO {

  @Query("SELECT * FROM MedicationChoiceEntity")
  MedicationChoiceEntity getMedication();

  @Insert(onConflict = REPLACE)
  void insertMedication(MedicationChoiceEntity medication);

  @Update(onConflict = REPLACE)
  void updateMedication(MedicationChoiceEntity medication);
}