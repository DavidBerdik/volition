package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;


import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * DAO for interacting with the MedicationDosageEntity
 */

@Dao
public interface MedicationDosageDao {

  /**
   * Retrieves a dosage selection from the database.
   *
   * @return a LiveData object containing an int of the chosen medicine dose for one day
   */

  @Query("SELECT * FROM MedicationDosageEntity")
  LiveData<MedicationDosageEntity> getDosage();

  /**
   * Inserts a medication dosage selection into the database.
   *
   * @param dosage a MedicationDosageEntity object containing the int to be inserted
   */

  @Insert(onConflict = IGNORE)
  void insertDosage(final MedicationDosageEntity dosage);

  /**
   * Updates the dosage selection in the database.
   *
   * @param dosage a MedicationDosageEntity object containing the int to be inserted.
   */

  @Update(onConflict = REPLACE)
  void updateDosage(final MedicationDosageEntity dosage);
}