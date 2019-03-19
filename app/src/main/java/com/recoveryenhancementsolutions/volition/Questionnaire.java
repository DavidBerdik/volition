package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Questionnaire {
  @PrimaryKey

  public int id;
  public Boolean q1, q2,q3,q4,q5,q6,q7,q8,q9,q10,q11;
  public String severityLevel;


}
