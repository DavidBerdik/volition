package com.recoveryenhancementsolutions.volition;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import org.junit.Test;
import org.mockito.Mock;

public class MockDataTest {

 private final MockData MOCK_DATA = new MockData();

  @Test
  public void getTempUserActivityList() {
    assertNotNull(MOCK_DATA.getTempUserActivityList());
  }

  @Test
  public void getStringArray() {
    assertEquals(3,MOCK_DATA.getStringArray().length);
  }

  @Test
  public void getStringArrayList() {
    assertEquals(1,MOCK_DATA.getStringArrayList().size());
  }

  @Test
  public void getDateAsString() {
    Date date = new Date();
    final String DATE_FORMAT_STRING = "E yyyy.MM.dd";
    SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING, Locale.US);
    String formattedDate = format.format(date);
    assertEquals(formattedDate,MOCK_DATA.getDateAsString());
  }

  @Test
  public void getDateAsDateObject() {
    assertNotNull(MOCK_DATA.getDateAsDateObject());
  }
}