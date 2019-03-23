package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

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

  /**
   * Sets the activity's ID. Since the activity ID is an auto-increment value set by the database,
   * use of this setter is discouraged since the value set will be changed.
   *
   * @param id The ID of the activity.
   */
  public void setId(int id) {
    this.id = id;
  }

  /**
   * Sets the activity's date.
   *
   * @param date Date object containing the activity's date.
   */
  public void setDate(Date date) {
    final Calendar cal = Calendar.getInstance();
    cal.setTime(date);
    setDate(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DAY_OF_MONTH));
  }

  /**
   * Sets the activity's date.
   *
   * @param year Activity's year
   * @param month Activity's month
   * @param day Activity's day
   */
  public void setDate(int year, int month, int day) {
    try {
      this.date = new SimpleDateFormat("yyyy-MM-dd", Locale.US).parse(year + "-"
          + month + "-" + day);
    } catch (ParseException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sets the activity's description.
   *
   * @param desc Activity's description
   */
  public void setDesc(String desc) {
    this.desc = desc;
  }

  /**
   * Returns the activity's ID.
   *
   * @return The activity's ID
   */
  public int getId() {
    return id;
  }

  /**
   * Returns the activity's date.
   *
   * @return The activity's date
   */
  public Date getDate() {
    return date;
  }

  /**
   * Returns the activity's description.
   *
   * @return The activity's description
   */
  public String getDesc() {
    return desc;
  }
}
