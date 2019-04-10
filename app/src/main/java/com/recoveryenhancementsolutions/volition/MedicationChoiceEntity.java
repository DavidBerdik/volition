package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Database entity for storing the users Medication Choice
 */

@Entity(primaryKeys = "medication")
public class MedicationChoiceEntity {


  @SuppressWarnings("NullableProblems")
  @NonNull
  @ColumnInfo(name = "medication")
  public String medication;

  @SuppressWarnings("NullableProblems")
  @ColumnInfo(name = "dosage")
  @Nullable
  public int dosage;

}
