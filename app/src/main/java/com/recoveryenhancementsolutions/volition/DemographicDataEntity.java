package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * A skeleton file for the DemographicDataEntity. It is being worked on by another group and they
 * should replace this file when they are done.
 */
@Entity
public class DemographicDataEntity {

  /**
   * Sets the name of the patient that is using this application.
   *
   * @param patientName A String object containing the patient's name.
   */
  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  /**
   * Fetches the last fate of being clean.
   *
   * @return A MM-DD-YYYY string representing the date.
   */
  public String getLastClean() {
    return lastClean;
  }

  /**
   * Sets the last date of being clean in the database
   *
   * @param date A MM-DD-YYYY string representing the date.
   */
  public void setLastClean(final String date) {
    lastClean = date;
  }

  @PrimaryKey
  @NonNull
  private String patientName;
  private String lastClean;//MM-DD-YYYY
}
