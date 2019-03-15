package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
  private int id;

  /**
   * Stores the date when the activity took place in the form of a timestamp.
   */
  @NonNull
  private Date date;

  /**
   * Stores the activity description.
   */
  @NonNull
  private String desc;

  public void setId(int id) {
    this.id = id;
  }

  public void setDate(Date date) {
    this.date = date;
  }

  public void setDate(int year, int month, int day) throws ParseException {
    this.date = new SimpleDateFormat("yyyy-MM-dd").parse(year + "-" + month + "-"
        + day);
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public int getId() {
    return id;
  }

  public Date getDate() {
    return date;
  }

  public String getDesc() {
    return desc;
  }
}
