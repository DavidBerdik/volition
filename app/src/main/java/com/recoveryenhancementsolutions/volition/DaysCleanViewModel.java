package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import java.util.Date;

/**
 * ViewModel for "DaysClean" in the database. Handles the number of days clean from the database.
 */
public class DaysCleanViewModel extends AndroidViewModel {

  /**
   * Constructor for the ViewModel.
   *
   * @param application Application object for the ViewModel.
   */
  public DaysCleanViewModel(final Application application) {
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
