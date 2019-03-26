package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Temporary skeleton file.  Should be written over by VOL-50-View-Severity-Level
 */
@Entity
public class QuestionnaireEntity {

  /**
   * Updates the value of severity level
   *
   * @param severityLevel The new severity level
   */
  public void setSeverityLevel(String severityLevel){
    this.severityLevel = severityLevel;
  }

  /**
   * Retrieves the current severityLevel
   * @return
   */
  public String getSeverityLevel(){
    return severityLevel;
  }

  /**
   * The severity level for this patient. Should be "MILD", "MODERATE", or "SEVERE"
   */
  @PrimaryKey
  @NonNull
  private String severityLevel;


}