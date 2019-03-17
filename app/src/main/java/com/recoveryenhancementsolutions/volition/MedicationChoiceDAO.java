package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

@Dao
public interface MedicationChoiceDAO {

  @Query("SELECT * FROM MedicationChoice)
  LiveData<String<MedicationChoice>> getMedication (String medication);

  @Insert(onConflict = IGNORE)
  void insertMedication(MedicationChoice medication);
}