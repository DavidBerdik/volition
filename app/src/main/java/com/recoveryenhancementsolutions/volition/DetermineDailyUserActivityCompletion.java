package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.List;

public class DetermineDailyUserActivityCompletion extends AndroidViewModel {


  public DetermineDailyUserActivityCompletion(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Retrieves all user activities from the database.
   *
   * @return A LiveData object containing a list of all user activities.
   */
  public LiveData<List<UserActivityEntity>> getAllActivities() {
    return db.userActivitiesDao().getAllActivities();
  }

  public void testName(){

    LiveData<List<UserActivityEntity>> activities = getAllActivities();

    activities.observe(this, new Observer<List<UserActivityEntity>>() {
      @Override
      public void onChanged(@Nullable List<UserActivityEntity> userActivityEntities) {
        testName2(userActivityEntities);
      }
    });

  }

  public void testName2(final List<UserActivityEntity> activities){

    for(UserActivityEntity userActivityEntity: activities){
        String description = userActivityEntity.getDesc();
        //do this if/else for every catagory.
        if(description.contains("TEA")){
            //find TEA incomplete icon element and set to show
            //find TEA complete icon element and set to not show
        }
        else
          //find TEA incomplete icon element and set to not show
          //find TEA complete icon and set to show
    }
  }


  private VolitionDatabase db;
}
