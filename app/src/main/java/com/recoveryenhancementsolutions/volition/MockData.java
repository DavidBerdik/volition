package com.recoveryenhancementsolutions.volition;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;

/**
 * Creation of mock data to test methods that will update the UI
 */

public class MockData {

  /**
   * Creates list containing UserActivityEntity objects for testing
   *
   * @return list of UserActivityEntity objects with populated fields
   */

  public List<UserActivityEntity> getTempUserActivityList() {

    final ArrayList<UserActivityEntity> listOfActivites = new ArrayList<>();

    final UserActivityEntity testActivity1 = new UserActivityEntity();
    final UserActivityEntity testActivity2 = new UserActivityEntity();
    final UserActivityEntity testActivity3 = new UserActivityEntity();

    testActivity1.setId(1);
    testActivity1.setDate(new Date());
    testActivity1.setDate(2019, 1, 1);
    testActivity1.setDesc("This is a description for user activity test1");

    testActivity2.setId(2);
    testActivity2.setDate(new Date());
    testActivity2.setDate(2019, 2, 2);
    testActivity2.setDesc("This is a description for user activity test2");

    testActivity3.setId(1);
    testActivity3.setDate(new Date());
    testActivity3.setDate(2019, 3, 3);
    testActivity3.setDesc("This is a description for user activity test3");

    listOfActivites.add(testActivity1);
    listOfActivites.add(testActivity2);
    listOfActivites.add(testActivity3);

    return listOfActivites;
  }

  /**
   * creates string array with random strings for testing the UI
   *
   * @return a string array containing 3 random test strings
   */
  public String[] getStringArray() {
    final Random rand = new Random();
    final int randomNumber1 = rand.nextInt();
    final int randomNumber2 = rand.nextInt();
    final int randomNumber3 = rand.nextInt();

    final String[] arrayOfString = new String[3];
    arrayOfString[0] =
        "This is a sample string for testing the UI with a random number: " + randomNumber1;
    arrayOfString[1] =
        "This is a sample string for testing the UI with a random number: " + randomNumber2;
    arrayOfString[2] =
        "This is a sample string for testing the UI with a random number: " + randomNumber3;

    return arrayOfString;
  }

  /**
   * creates string ArrayList with a sample string for testing the UI
   *
   * @return a string ArrayList containing a test string
   */
  public ArrayList<String> getStringArrayList() {
    final ArrayList<String> testStringList = new ArrayList();
    final String testString = "This is a sample string for testing the UI";
    testStringList.add(testString);
    return testStringList;

  }

  /**
   * Creates sample date object as string
   *
   * @return a formatted date as a string with pattern E yyyy.MM.dd
   */
  public String getDateAsString() {

    final Date date = new Date();
    final String DATE_FORMAT_STRING = "yyyy-MM-dd";
    final SimpleDateFormat format = new SimpleDateFormat(DATE_FORMAT_STRING, Locale.US);
    final String formattedDate = format.format(date);
    return formattedDate;

  }

  /**
   * Creates sample random date object
   *
   * @return sample random date object
   */
  public Date getDateAsDateObject() {
    final Random random = new Random();
    return new Date(Math.abs(System.currentTimeMillis() - random.nextLong()));

  }


}
