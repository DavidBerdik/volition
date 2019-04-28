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
  LiveData<MedicationChoiceEntity> getMedication();

  /**
   * Inserts a medication choice into the database.
   *
   * @param medication a MedicationChoiceEntity object containing the string to be inserted.
   */

  @Insert(onConflict = IGNORE)
  void insertMedication(final MedicationChoiceEntity medication);

  /**
   * Updates the medication choice in the database.
   *
   * @param medication a MedicationChoiceEntity object containing the string to be inserted.
   */

  @Update(onConflict = REPLACE)
  void updateMedication(final MedicationChoiceEntity medication);

  /**
   * Query's the dosage information
   *
   * @return a live data of MedicationChoiceEntity
   */
  @Query("SELECT * FROM MedicationChoiceEntity")
  LiveData<MedicationChoiceEntity> getDosage();

  /**
   * Inserts a medication dosage selection into the database.
   *
   * @param dose a MedicationDosageEntity object containing the int to be inserted
   */

  @Query("UPDATE MedicationChoiceEntity SET dosage = :dose, milligramsBuprenorphine =:milligramsBuprenorphine, milligramsNaloxone =:milligramsNaloxone, type =:type WHERE medication = :medication")
  void updateDosage(final String type, final double milligramsNaloxone,
      final double milligramsBuprenorphine, final int dose, final String medication);

  /**
   * Returns the dosage associated with a specific medication.
   *
   * @param medication The medication to search for.
   * @return A LiveData object containing a MedicationChoiceEntity with the corresponding medication
   * entry.
   */
  @Query("SELECT * FROM MedicationChoiceEntity WHERE medication = :medication")
  LiveData<MedicationChoiceEntity> getDosage(final String medication);
}
