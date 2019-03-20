package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.AsyncTask;

/**
 * Temporary skeleton file.  Should be written over by VOL-50-View-Severity-Level
 */
@Entity
public class QuestionnaireEntity {

  /**
   * The severity level for this patient. Should be "MILD", "MODERATE", or "SEVERE"
   */
  public String severityLevel;
}