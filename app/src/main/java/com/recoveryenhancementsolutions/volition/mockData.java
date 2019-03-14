package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class mockData {


  public static List<tempUserActivity> getTempUserActivityList() {

    List<tempUserActivity> listOfActivites = new ArrayList<>();
    tempUserActivity tempUserActivity1 = new tempUserActivity("Name1",1, new Date());
    tempUserActivity tempUserActivity2 = new tempUserActivity("Name2",2, new Date());
    tempUserActivity tempUserActivity3 = new tempUserActivity("Name3",3, new Date());

    listOfActivites.add(tempUserActivity1);
    listOfActivites.add(tempUserActivity2);
    listOfActivites.add(tempUserActivity3);

    return listOfActivites;
  }

  public String[] getStringArray(){

    String[] arrayOfString = new String[1];
    arrayOfString[0] = "This is a sample string for testing the UI.";
    return arrayOfString;
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
