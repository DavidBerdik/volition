package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Database entity for storing the QuestionnaireActivityEntity information.
 */
@Entity(primaryKeys = {"id"})
public class QuestionnaireActivityEntity {

  @NonNull
  public int id;
  public String totalYes;
  public boolean q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
  public String severityLevel;

  /**
   * sets id value in the database
   *
   * @param id primary key recieved to be set
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * sets total yes value in the database
   *
   * @param totalYes string value to be printed in the view severity level
   */
  public void setTotalYes(String totalYes) {
    this.totalYes = totalYes;
  }

  /**
   * sets specific question answer
   *
   * @param q1 boolean value for specific question representing either yes or no
   */
  public void setQ1(Boolean q1) {
    this.q1 = q1;
  }

  /**
   * sets specific question answer
   *
   * @param q2 boolean value for specific question representing either yes or no
   */
  public void setQ2(Boolean q2) {
    this.q2 = q2;
  }

  /**
   * sets specific question answer
   *
   * @param q3 boolean value for specific question representing either yes or no
   */
  public void setQ3(Boolean q3) {
    this.q3 = q3;
  }

  /**
   * sets specific question answer
   *
   * @param q4 boolean value for specific question representing either yes or no
   */
  public void setQ4(Boolean q4) {
    this.q4 = q4;
  }

  /**
   * sets specific question answer
   *
   * @param q5 boolean value for specific question representing either yes or no
   */
  public void setQ5(Boolean q5) {
    this.q5 = q5;
  }

  /**
   * sets specific question answer
   *
   * @param q6 boolean value for specific question representing either yes or no
   */
  public void setQ6(Boolean q6) {
    this.q6 = q6;
  }

  /**
   * sets specific question answer
   *
   * @param q7 boolean value for specific question representing either yes or no
   */
  public void setQ7(Boolean q7) {
    this.q7 = q7;
  }

  /**
   * sets specific question answer
   *
   * @param q8 boolean value for specific question representing either yes or no
   */
  public void setQ8(Boolean q8) {
    this.q8 = q8;
  }

  /**
   * sets specific question answer
   *
   * @param q9 boolean value for specific question representing either yes or no
   */
  public void setQ9(Boolean q9) {
    this.q9 = q9;
  }

  /**
   * sets specific question answer
   *
   * @param q10 boolean value for specific question representing either yes or no
   */
  public void setQ10(Boolean q10) {
    this.q10 = q10;
  }

  /**
   * sets specific question answer
   *
   * @param q11 boolean value for specific question representing either yes or no
   */
  public void setQ11(Boolean q11) {
    this.q11 = q11;
  }

  /**
   * sets string that represents severity level either mild, moderate, or severe
   *
   * @param severityLevel string to be displayed in view severity level
   */
  public void setSeverityLevel(String severityLevel) {
    this.severityLevel = severityLevel;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of id for this specific object
   */
  public int getId() {
    return id;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q1 for this specific object
   */
  public Boolean getQ1() {
    return q1;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q2 for this specific object
   */
  public Boolean getQ2() {
    return q2;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q3 for this specific object
   */
  public Boolean getQ3() {
    return q3;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q4 for this specific object
   */
  public Boolean getQ4() {
    return q4;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q5 for this specific object
   */
  public Boolean getQ5() {
    return q5;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q6 for this specific object
   */
  public Boolean getQ6() {
    return q6;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q7 for this specific object
   */
  public Boolean getQ7() {
    return q7;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q8 for this specific object
   */
  public Boolean getQ8() {
    return q8;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q9 for this specific object
   */
  public Boolean getQ9() {
    return q9;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q10 for this specific object
   */
  public Boolean getQ10() {
    return q10;
  }

  /**
   * method to return value of this object
   *
   * @return returns value of q11 for this specific object
   */
  public Boolean getQ11() {
    return q11;
  }

}