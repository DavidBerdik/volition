package com.recoveryenhancementsolutions.volition.View_Severity_Level;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import com.recoveryenhancementsolutions.volition.VolitionDatabase;
import java.util.List;

public class ViewSeverityLevelViewModel extends AndroidViewModel {

  public final LiveData<String> severity;
  private VolitionDatabase mDb;
  public ViewSeverityLevelViewModel(Application application) {
    super(application);
    //createDb();


    // Books is a LiveData object so updates are observed.
    severity=mDb.questionnaireModel().findSeverityLevelString();

  }


 /* public void createDb() {
    mDb = VolitionDatabase.getInMemoryDatabase(this.getApplication());

    // Populate it with initial data
    DatabaseInitializer.populateAsync(mDb);
  }*/
}
