package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.Date;

/**
 * ViewModel for the "HomeActivity" class. Retrieves the number of days clean from the database.
 */
public class HomeActivityViewModel extends AndroidViewModel {

  /**
   * Constructor for the "HomeActivity" class' ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public HomeActivityViewModel(final Application application) {
    super(application);
    db = VolitionDatabase.getDatabase(this.getApplication());
  }

  /**
   * Retrieves the last date clean as stored in the database.
   *
   * @return A LiveData object containing a String representing the last listed date of being clean.
   */
  public LiveData<String> getLastCleanDate() {
    return db.demographicDataDAO().queryLastCleanDate();
  }

  private VolitionDatabase db;
}
