package com.recoveryenhancementsolutions.volition.db;

import android.arch.persistence.room.Entity;
import android.support.annotation.NonNull;

/**
 * This class is a temporary database Entity intended for testing UserActivitiesDao.
 */
@Entity(primaryKeys = {"date", "actNum"})
public class UserActivitiesEntityTemp {

  /**
   * Stores the date when the activity took place in the form of a timestamp.
   */
  @NonNull
  public long date;

  /**
   * Stores the activity number for the day.
   */
  @NonNull
  public int actNum;

  /**
   * Stores the activity description.
   */
  public String desc;
}
