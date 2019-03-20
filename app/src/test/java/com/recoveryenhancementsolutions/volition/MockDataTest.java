package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;

/**
 * Unit testing for the MockData class
 */
public class MockDataTest {

  /**
   * test to make sure the userActivityList is non-null
   */
  @Test
  public void getTempUserActivityList() {
    assertNotNull(MOCK_DATA.getTempUserActivityList());
  }

  /**
   * test the length of the string array
   */
  @Test
  public void getStringArray() {
    assertEquals(3,MOCK_DATA.getStringArray().length);
  }

  /**
   * test the length of the string arraylist
   */
  @Test
  public void getStringArrayList() {
    assertEquals(1,MOCK_DATA.getStringArrayList().size());
  }

  /**
   * test to make sure the date is produced corectly
   */
  @Test
  public void getDateAsString() {
    final Date date = new Date();
    final String DATE_FORMAT_STRING = "yyyy.MM.dd";
    final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING, Locale.US);
    final String formattedDate = format.format(date);
    assertEquals(formattedDate,MOCK_DATA.getDateAsString());
  }

  /**
   * tests to make sure the date object is non-null
   */
  @Test
  public void getDateAsDateObject() {
    assertNotNull(MOCK_DATA.getDateAsDateObject());
  }

  /**
   * instance of MockData class
   */
  private final MockData MOCK_DATA = new MockData();
}

