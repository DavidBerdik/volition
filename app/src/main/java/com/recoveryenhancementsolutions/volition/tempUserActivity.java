package com.recoveryenhancementsolutions.volition;

import java.util.Date;

public class tempUserActivity {



  /*this is a temporary class creating user activities objects to populate the UI with,
  we don't know what exactly the data will look like but this is a baseline just to test */

  private String activityName;
  private int activityId;
  private Date activityDate;



  public tempUserActivity(String activityName, int activityNumber, Date activityDate) {
    this.activityName = activityName;
    this.activityId = activityNumber;
    this.activityDate = activityDate;
  }

  public String getActivityName() {
    return activityName;
  }

  public void setActivityName(String activityName) {
    this.activityName = activityName;
  }

  public int getActivityId() {
    return activityId;
  }

  public void setActivityId(int activityId) {
    this.activityId = activityId;
  }

  public Date getActivityDate() {
    return activityDate;
  }

  public void setActivityDate(Date activityDate) {
    this.activityDate = activityDate;
  }
}
