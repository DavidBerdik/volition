package com.recoveryenhancementsolutions.volition;

import static android.arch.persistence.room.OnConflictStrategy.IGNORE;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import java.util.List;

@Dao
public interface MedicationChoiceDAO {

  @Query("SELECT * FROM MedicationChoiceEntity")
  LiveData<String> getMedication(String medication);

  @Insert(onConflict = IGNORE)
  void insertMedication(MedicationChoiceEntity medication);
}