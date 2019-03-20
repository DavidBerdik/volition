package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the User Treatment Plan.
 */
@Entity
public class TreatmentPlanEntity {
  /**
   * Returns the ID of this treatmentPlan.
   *
   * @return the treatment plan's ID
   */
  public int getId(){return id;}

  /**
   * Returns the number of counseling sessions the user should attend per week;
   *
   * @return The number of counseling sessions the user should attend per week.
   */
  public int getNumCounseling(){return numCounseling;}

  /**
   * Returns the number of times the user should do a medManagement per month.
   *
   * @return The number of times the user should do a medManagement per month.
   */
  public int getNumMedManagement(){return numMedManagement;}

  /**
   * Returns the number of times the user should attend support meetings per week.
   *
   * @return The number of times the user should attend support meetings per week.
   */
  public int getNumSupportMeeting(){return numSupportMeeting;}

  /**
   * Returns the number of times the user should attend lessons per week.
   *
   * @return The number of times the user should attend lessons per week.
   */
  public int getNumLessons(){return numLessons;}

  /**
   * Returns the number of times the user should take a treatment plan effectiveness assessment.
   *
   * @return The number of times the user should take a treatment plan effectiveness assessment.
   */
  public int getNumTreatmentEffectivenessAssessment(){return numTreatmentEffectivenessAssessment;}

  /**
   * Returns the number of times the user should take outcome measures per week.
   *
   * @return The number of times the user should take outcome measures per week.
   */
  public int getNumOutcomeMeasures(){return numOutcomeMeasures;}

  /**
   * Returns the number of times the user should take time tracking per week.
   *
   * @return The number of times the user should take time tracking per week.
   */
  public int getNumTimeTracking(){return numTimeTracking;}

  /**
   * Returns the number of times the user should take a reading response per week.
   *
   * @return The number of times the user should take a reading response per week.
   */
  public int getNumReadingResponse(){return numReadingResponse;}

  /**
   * Sets the ID to a new value.  This changes the primary key of the treatment plan so its use is
   * discouraged.
   *
   * @param id The ID of the treatment plan.
   */
  public void setId(int id){ this.id = id;}

  /**
   * Sets the number of times the user should attend counseling meetings per week.
   *
   * @param numCounseling The number of times the user should attend counseling meetings per week.
   */
  public void setNumCounseling(int numCounseling){this.numCounseling = numCounseling;}

  /**
   * Sets the number of times the user should take medications per week.
   *
   * @param numMedManagement The number of times the user should do a medManagement per month.
   */
  public void setNumMedManagement(int numMedManagement){this.numMedManagement = numMedManagement;}

  /**
   * Sets the number of times the user should attend support meetings per week.
   *
   * @param numSupportMeeting The number of times the user should attend support meetings per week.
   */
  public void setNumSupportMeeting(int numSupportMeeting){
    this.numSupportMeeting = numSupportMeeting;
  }

  /**
   * Sets the number of times the user should attend lessons per week.
   *
   * @param numLessons The number of times the user should attend lessons per week.
   */
  public void setNumLessons(int numLessons){this.numLessons =numLessons;}

  /**
   * Sets the number of times the user should take a treatment plan effectiveness assessment per
   * week.
   *
   * @param numTreatmentEffectivenessAssessment The number of times the user should take a treatment
   * plan effectiveness assessment per week.
   */
  public void setNumTreatmentEffectivenessAssessment(int numTreatmentEffectivenessAssessment){
    this.numTreatmentEffectivenessAssessment = numTreatmentEffectivenessAssessment;
  }

  /**
   * Sets the number of outcome measures the user should take per week.
   *
   * @param numOutcomeMeasures The number of outcome measures the user should take per week.
   */
  public void setNumOutcomeMeasures(int numOutcomeMeasures){
    this.numOutcomeMeasures = numOutcomeMeasures;
  }

  /**
   * Sets the number of times the user should record time tracking per week.
   *
   * @param numTimeTracking The number of times the user should record time tracking per week.
   */
  public void setNumTimeTracking(int numTimeTracking){this.numTimeTracking =numTimeTracking;}

  /**
   * Sets the number of time the user should do a reading response per week.
   * @param numReadingResponse The number of time the user should do a reading response per week.
   */
  public void setNumReadingResponse(int numReadingResponse){
    this.numReadingResponse = numReadingResponse;
  }

  /**
   * Stores the ID for this treatment plan.
   */
  @PrimaryKey
  private int id;  //used to manage the treatment plan and prevent duplicates.

  /**
   * Stores the number of times the user should go to counseling per week.
   */
  @NonNull
  private int numCounseling;

  /**
   * Stores the number of times the user should do a medManagement per month.
   */
  @NonNull
  private int numMedManagement;

  /**
   * Stores the number of times the user should attend a support meeting per week.
   */
  @NonNull
  private int numSupportMeeting;

  /**
   * Stores the number of times the user should attend lessons per week.
   */
  @NonNull
  private int numLessons;

  /**
   * Stores the number of times the user should take a treatment plan effectiveness assessment per
   * week.
   */
  @NonNull
  private int numTreatmentEffectivenessAssessment;

  /**
   * Stores the number of times the user should record outcome measures per week.
   */
  @NonNull
  private int numOutcomeMeasures;

  /**
   * Stores the number of times the user should record time tracking per week.
   */
  @NonNull
  private int numTimeTracking;

  /**
   * Stores the number of times the user should take a reading response per week.
   */
  @NonNull
  private int numReadingResponse;
}