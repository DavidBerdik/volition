package com.recoveryenhancementsolutions.volition;

public class tempUserActivity {



  /*this is a temporary class creating user activites objects to populate the UI with,
  we don't know what exactly the data will look like but this is a baseline just to test */

  private String activityName;
  private int activityNumber;

  public tempUserActivity(String activityName, int activityNumber) {
    this.activityName = activityName;
    this.activityNumber = activityNumber;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public int getActivityNumber() {
    return activityNumber;
  }

  public void setActivityNumber(int activityNumber) {
    this.activityNumber = activityNumber;
  }
}
