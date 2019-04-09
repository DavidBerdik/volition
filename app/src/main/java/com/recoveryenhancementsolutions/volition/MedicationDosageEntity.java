package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the users Dosage selection
 */

@Entity(primaryKeys = "dosage")
public class MedicationDosageEntity {

  /**
   * Sets the selection from the user as an int. The int is passed from a button press in the
   * activity
   */

  @SuppressWarnings("NullableProblems")
  @NonNull
  @ColumnInfo(name = "dosage")
  public int dosage;

}
