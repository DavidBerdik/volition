package com.recoveryenhancementsolutions.volition;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Local unit test to validate the functionality of the DateConverter object.
 */
public class DateConverterTest {

  /**
   * Takes a local String object represting a date and turns it into a Date object.
   *
   * @param date String object in a yyyy-MM-dd date format.
   * @return Date object derived from the provided String format.
   * @throws ParseException if the provided String object is not in yyyy-MM-dd format.
   */
  private static Date parseDateFromString(String date) throws ParseException {
    return new SimpleDateFormat("yyyy-MM-dd").parse(date);
  }

  @Test
  public void daysBetween_isCorrect() {
    Date janFirst2019, janTenth2019, decThirtyFirst2019, janFirst2020, decThirtyFirst2020;

    try {
      janFirst2019 = DateConverterTest.parseDateFromString("2019-01-01");
      janTenth2019 = DateConverterTest.parseDateFromString("2019-01-10");
      decThirtyFirst2019 = DateConverterTest.parseDateFromString("2019-12-31");
      janFirst2020 = DateConverterTest.parseDateFromString("2020-01-01");
      decThirtyFirst2020 = DateConverterTest.parseDateFromString("2020-12-31");

      assertEquals(9, DateConverter.daysBetween(janFirst2019, janTenth2019));
      assertEquals(365, DateConverter.daysBetween(janFirst2019, decThirtyFirst2019));
      assertEquals(366, DateConverter.daysBetween(janFirst2020, decThirtyFirst2020));
      assertEquals(357, DateConverter.daysBetween(janTenth2019, janFirst2020));
      assertEquals(0, DateConverter.daysBetween(janTenth2019, janFirst2019));
    } catch (ParseException e) {
      fail("Improper formatting used on DateConverterTest.parseDateFromString method.");
    }
  }
}