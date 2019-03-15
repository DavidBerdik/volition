package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.util.Date;

/**
 * Database entity for storing the User Activity log.
 */
@Entity
public class UserActivityEntity {
  /**
   * Stores the activity ID.
   */
  @PrimaryKey(autoGenerate = true)
  @NonNull
  protected int id;

  /**
   * Stores the date when the activity took place in the form of a timestamp.
   */
  @NonNull
  protected Date date;

  /**
   * Stores the activity description.
   */
  @NonNull
  protected String desc;

  public void setDate(Date date) {
    this.date = date;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }
}
