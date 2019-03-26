package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the Questionnaire information.
 */
@Entity(primaryKeys = {"id"})
public class Questionnaire {

  @NonNull
  public int id;

  public boolean q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
  public String severityLevel;

}