package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class DemographicDataEntity {

  /**
   * Gets the name of the patient that is using this application.
   *
   * @return A String object containing the patient's name.
   */
  public String getPatientName() {
    return patientName;
  }

  /**
   * Sets the name of the patient that is using this application.
   *
   * @param patientName A String object containing the patient's name.
   */
  public void setPatientName(final @NonNull String patientName) {
    this.patientName = patientName;
  }

  /**
   * Fetches the last fate of being clean.
   *
   * @return A Date object representing the date.
   */
  public Date getLastClean() {
    return lastClean;
  }

  /**
   * Sets the last date of being clean in the database
   *
   * @param date A Date object representing the date.
   */
  public void setLastClean(final @NonNull Date date) {
    lastClean = date;
  }

  @PrimaryKey
  @NonNull
  private String patientName;
  private Date lastClean;
>>>>>>> 44f4c049bc3b5d55f9315e87e4499aa5a7211ebe
}
