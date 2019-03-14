package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Converts dates
 */
public class DateConverter {

  /**
   * Converts dates from timestamp (Long) format to Date object format.
   * @param value Date in timestamp (Long) format.
   * @return Date in Date object format.
   */
  @TypeConverter
  public static Date toDate(Long value) {
    return new Date(value);
  }

  /**
   * Converts dates from Date object format to timestamp (Long) format.
   * @param value Date in Date object format.
   * @return Date in timestamp (Long) format.
   */
  @TypeConverter
  public static Long toLong(Date value) {
    return value.getTime();
  }
}