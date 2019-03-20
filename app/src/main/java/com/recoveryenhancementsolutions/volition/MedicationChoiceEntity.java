package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

@Entity(primaryKeys = "medication")

public class MedicationChoiceEntity {

  @NonNull
  @ColumnInfo(name = "medication")
  public String medication;

}
