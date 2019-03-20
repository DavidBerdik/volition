package com.recoveryenhancementsolutions.volition;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;

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

  private VolitionDatabase db;
}
