package com.recoveryenhancementsolutions.volition;

import android.arch.persistence.room.TypeConverter;
import java.util.Date;

/**
 * Performs specific operations on dates using both the Date object and timestamp (Long) formats.
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

  /**
   * Takes two Date objects and calculates the number of days between them.
   * @param start Date object representing the starting date.
   * @param end Date object representing the ending date.
   * @return Integer representing the number of days between start and end.
   */
  public static int daysBetween(Date start, Date end) {
    return (int)((end.getTime() - start.getTime()) / (1000 * 60 * 60 * 24));
  }
}