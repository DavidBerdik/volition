package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the users Medication Choice
 */

@Entity(primaryKeys = "medication")
public class MedicationChoiceEntity {

  /**
   * Sets the choice from the user as a String The string is passed from a button press in the
   * activity
   */

  @NonNull
  @ColumnInfo(name = "medication")
  public String medication;

}
