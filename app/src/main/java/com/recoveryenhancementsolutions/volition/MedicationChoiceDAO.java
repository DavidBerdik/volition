package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;
import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

/**
 * DAO used for interacting with the MedicationChoiceEntity
 */

@Dao
public interface MedicationChoiceDAO {

  /**
   * Retrieves a medication choice from the database.
   *
   * @return a LiveData object containing a string of the chosen medicine
   */

  @Query("SELECT * FROM MedicationChoiceEntity")
  LiveData<String> getMedication();

  /**
   * Inserts a medication choice into the database.
   *
   * @param medi