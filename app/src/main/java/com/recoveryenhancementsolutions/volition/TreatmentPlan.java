package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * TreatmentPlan Class represents the treatmentPlan table in the Volition database.  All entries are
 * integer values representing the number of times the patient should do the labeled event per week.
 */

@Entity
public class TreatmentPlan {
  @PrimaryKey
  public int treatmentPlanID;  //used to manage the treatment plan and prevent duplicates.

  public int numCounseling;
  public int numMedManagement;
  public int numSupportMeeting;
  public int numLessons;
  public int numTreatmentEffectivenessAssessment;
  public int numOutcomeMeasures;
  public int numTimeTracking;
  public int numReadingResponse;

}
