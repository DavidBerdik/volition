package com.recoveryenhancementsolutions.volition;

<<<<<<< HEAD
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

=======
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

  @SuppressWarnings("NullableProblems")
  @NonNull
  @ColumnInfo(name = "medication")
  public String medication;

>>>>>>> d0f83ad49baf02d5741fb9f859dd9956e988f2b7
}
