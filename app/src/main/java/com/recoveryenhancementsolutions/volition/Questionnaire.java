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
public String totalYes;
  public boolean q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11;
  public String severityLevel;


  public void setId(int id) {
    this.id = id;
  }
  public void setTotalYes(String totalYes) {
    this.totalYes =totalYes;
  }
  public void setQ1(Boolean q1) {
    this.q1 = q1;
  }
  public void setQ2(Boolean q2) {
    this.q2 = q2;
  }
  public void setQ3(Boolean q3) {
    this.q3 = q3;
  }
  public void setQ4(Boolean q4) {
    this.q4 = q4;
  }
  public void setQ5(Boolean q5) {
    this.q5 = q5;
  }
  public void setQ6(Boolean q6) {
    this.q6 = q6;
  }
  public void setQ7(Boolean q7) {
    this.q7 = q7;
  }
  public void setQ8(Boolean q8) {
    this.q8 = q8;
  }
  public void setQ9(Boolean q9) {
    this.q9 = q9;
  }
  public void setQ10(Boolean q10) {
    this.q10 = q10;
  }
  public void setq11(Boolean q11) {
    this.q11 = q11;
  }
  public int getId() {
    return id;
  }
  public Boolean getQ1(){return q1;}
  public Boolean getQ2(){return q2;}
  public Boolean getQ3(){return q3;}
  public Boolean getQ4(){return q4;}
  public Boolean getQ5(){return q5;}
  public Boolean getQ6(){return q6;}
  public Boolean getQ7(){return q7;}
  public Boolean getQ8(){return q8;}
  public Boolean getQ9(){return q9;}
  public Boolean getQ10(){return q10;}
  public Boolean getQ11(){return q11;}

}