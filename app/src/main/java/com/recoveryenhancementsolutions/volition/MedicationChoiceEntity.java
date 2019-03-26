package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;


/**
 * Temporary skeleton file.  Should be written over by feature/VOL-43-access-database
 */
@Entity
public class MedicationChoiceEntity {

  /**
   * the medication being used
   */
  @PrimaryKey
  @NonNull
  public String medication;

  public void setMedication(String medication){this.medication = medication;}
  public String getMedication(){ return medication; }

}
