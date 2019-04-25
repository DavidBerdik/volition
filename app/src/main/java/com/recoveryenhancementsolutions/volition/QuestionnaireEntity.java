package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Stores the user's severity level based on their answers to the questionnaire.
 */
@Entity
public class QuestionnaireEntity {

  /**
   * Updates the value of severity level
   *
   * @param severityLevel The new severity level
   */
  public void setSeverityLevel(final String severityLevel) {
    this.severityLevel = severityLevel;
  }

  /**
   * Sets severity level to severe
   */
  public void setSevere(){
    severityLevel = "SEVERE";
  }

  /**
   * Sets severity level to moderate
   */
  public void setModerate(){
    severityLevel = "MODERATE";
  }

  /**
   * Sets severity level to mild
   */
  public void setMild(){
    this.severityLevel = "MILD";
  }

  /**
   * Retrieves the current severityLevel
   */
  public String getSeverityLevel() {
    return severityLevel;
  }

  /**
   * The severity level for this patient. Should be "MILD", "MODERATE", or "SEVERE"
   */
  @NonNull
  @PrimaryKey
  private String severityLevel;
}