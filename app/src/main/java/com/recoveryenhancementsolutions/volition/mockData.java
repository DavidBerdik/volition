package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class mockData {


  public static List<UserActivityEntity> getTempUserActivityList() {

    ArrayList<UserActivityEntity> listOfActivites = new ArrayList<>();

    UserActivityEntity test1 = new UserActivityEntity();
    UserActivityEntity test2 = new UserActivityEntity();
    UserActivityEntity test3 = new UserActivityEntity();

    test1.setId(1);
    test1.setDate(new Date());
    test1.setDate(2019,1,1);
    test1.setDesc("This is a description for user activity test1");

    test1.setId(2);
    test1.setDate(new Date());
    test1.setDate(2019,2,2);
    test1.setDesc("This is a description for user activity test2");

    test3.setId(1);
    test3.setDate(new Date());
    test3.setDate(2019,3,3);
    test3.setDesc("This is a description for user activity test3");

    listOfActivites.add(test1);
    listOfActivites.add(test2);
    listOfActivites.add(test3);

    return listOfActivites;
  }

  public String[] getStringArray(){

    String[] arrayOfString = new String[1];
    arrayOfString[0] = "This is a sample string for testing the UI.";
    return arrayOfString;
  }

  public ArrayList<String> getStringArrayList(){
    ArrayList<String> testList = new ArrayList();
    String testString = "This is a sample string for testing the UI";
    testList.add(testString);
    return testList;

  }

  public String getDateAsString(){

    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("E yyyy.MM.dd");
    String formattedDate = format.format(date);
    return formattedDate;

  }

  public Date getDateAsDateObject(){
    Date date = new Date();
    return date;

  }



}
