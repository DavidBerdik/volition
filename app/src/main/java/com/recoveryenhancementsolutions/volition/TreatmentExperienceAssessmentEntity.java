package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the users TEA responses
 */
@Entity(primaryKeys = "patientId")
public class TreatmentExperienceAssessmentEntity {

  /**
   * Sets the primary key, probably shouldn't be used in code as id should already exist in other
   * places
   *
   * @param id foreign key and primary key to recognize user
   */
  public void setPatientId(final int id) {
    patientId = id;
  }

  /**
   * Sets the integer value for the question regarding substance use in the TEA activity
   *
   * @param substanceI answer to the substance use question
   */
  public void setSubstanceInt(final int substanceI) {
    substanceInt = substanceI;
  }

  /**
   * Sets the integer value for the question regarding health in the TEA activity
   *
   * @param healthI answer to the health question
   */
  public void setHealthInt(final int healthI) {
    healthInt = healthI;
  }

  /**
   * Sets the integer value for the question regarding Lifestyle in the TEA activity
   *
   * @param lifestyleI answer to the leftstyle question
   */
  public void setLifestyleInt(final int lifestyleI) {
    lifestyleInt = lifestyleI;
  }

  /**
   * Sets the integer value for the question regarding community in the TEA activity
   *
   * @param communityI answer to the community question
   */
  public void setCommunityInt(final int communityI) {
    communityInt = communityI;
  }

  /**
   * Sets the String value for the question regarding substance use in the TEA activity
   *
   * @param remarksS string of personal notes from substance use questions
   */
  public void setRemarksString(final String remarksS) {
    remarksString = remarksS;
  }

  public int getPatientId() {
    return patientId;
  }

  /**
   * gets an int between 1-10 regarding the objects substance use question
   *
   * @return the answer recorded for the substance use question
   */
  public int getSubstanceInt() {
    return substanceInt;
  }

  /**
   * gets an int between 1-10 regarding the objects health question
   *
   * @return the answer recorded for the health question
   */
  public int getHealthInt() {
    return healthInt;
  }

  /**
   * gets an int between 1-10 regarding the objects lifestyle question
   *
   * @return the answer recorded for the lifestyle question
   */
  public int getLifestyleInt() {
    return lifestyleInt;
  }

  /**
   * gets an int between 1-10 regarding the objects community question
   *
   * @return the answer recorded for the community question
   */
  public int getCommunityInt() {
    return communityInt;
  }

  /**
   * gets an string of personal thoughts regarding the objects substance use questions
   *
   * @return the answer recorded for the substance use questions
   */
  public String getRemarksString() {
    return remarksString;
  }

  @SuppressWarnings("NullableProblems")
  @NonNull
  private int patientId;

  private int substanceInt, healthInt, lifestyleInt, communityInt;
  private String remarksString;

}