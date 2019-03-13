package com.recoveryenhancementsolutions.volition;

import android.arch.lifecycle.LiveData;
import java.util.ArrayList;
import java.util.List;

public class mockData {


  public List<tempUserActivity> getTempListLiveData() {

    List<tempUserActivity> listOfActivites = new ArrayList<>();
    tempUserActivity tempUserActivity1 = new tempUserActivity("Name1",1);
    tempUserActivity tempUserActivity2 = new tempUserActivity("Name2",2);
    tempUserActivity tempUserActivity3 = new tempUserActivity("Name3",3);

    listOfActivites.add(tempUserActivity1);


    return listOfActivites;
  }
}
